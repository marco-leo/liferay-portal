/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.encryptor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.encryptor.Encryptor;
import com.liferay.portal.kernel.encryptor.EncryptorException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.fips.CompanyKeyStoreUtil;
import com.liferay.portal.kernel.security.fips.FIPSModeUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.security.Key;
import java.security.SecureRandom;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 * @author Mika Koivisto
 */
@Component(service = Encryptor.class)
public class EncryptorImpl implements Encryptor {

	public static final String ENCODING = DigesterUtil.ENCODING;

	public static final String KEY_ALGORITHM = StringUtil.toUpperCase(
		GetterUtil.getString(
			PropsUtil.get(PropsKeys.COMPANY_ENCRYPTION_ALGORITHM)));

	public static final int KEY_SIZE = GetterUtil.getInteger(
		PropsUtil.get(PropsKeys.COMPANY_ENCRYPTION_KEY_SIZE));

	@Override
	public String decrypt(Key key, String encryptedString)
		throws EncryptorException {

		byte[] encryptedBytes = Base64.decode(encryptedString);

		return _decryptUnencodedAsString(key, encryptedBytes);
	}

	@Override
	public byte[] decryptUnencodedAsBytes(Key key, byte[] encryptedBytes)
		throws EncryptorException {

		try {
			if (_isGCMMode()) {
				return _decryptGCM(key, encryptedBytes);
			}

			return _decryptECB(key, encryptedBytes);
		}
		catch (Exception exception) {
			throw new EncryptorException(exception);
		}
	}

	@Override
	public Key deserializeKey(String base64StringOrAlias) {
		if (CompanyKeyStoreUtil.isKeyStoreAlias(base64StringOrAlias)) {
			Key key = CompanyKeyStoreUtil.getKey(base64StringOrAlias);

			if (key != null) {
				return key;
			}
		}

		byte[] bytes = Base64.decode(base64StringOrAlias);

		return new SecretKeySpec(bytes, EncryptorImpl.KEY_ALGORITHM);
	}

	@Override
	public String encrypt(Key key, String plainText) throws EncryptorException {
		if (key == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("Skip encrypting based on a null key");
			}

			return plainText;
		}

		byte[] encryptedBytes = encryptUnencoded(key, plainText);

		return Base64.encode(encryptedBytes);
	}

	@Override
	public byte[] encryptUnencoded(Key key, byte[] plainBytes)
		throws EncryptorException {

		try {
			if (_isGCMMode()) {
				return _encryptGCM(key, plainBytes);
			}

			return _encryptECB(key, plainBytes);
		}
		catch (Exception exception) {
			throw new EncryptorException(exception);
		}
	}

	@Override
	public byte[] encryptUnencoded(Key key, String plainText)
		throws EncryptorException {

		try {
			byte[] decryptedBytes = plainText.getBytes(ENCODING);

			return encryptUnencoded(key, decryptedBytes);
		}
		catch (Exception exception) {
			throw new EncryptorException(exception);
		}
	}

	@Override
	public Key generateKey() throws EncryptorException {
		if (FIPSModeUtil.isFIPSModeEnabled()) {
			_validateFIPSAlgorithm(KEY_ALGORITHM, KEY_SIZE);
		}

		return _generateKey(KEY_ALGORITHM);
	}

	@Override
	public String serializeKey(Key key) {
		return Base64.encode(key.getEncoded());
	}

	private byte[] _decryptECB(Key key, byte[] encryptedBytes)
		throws Exception {

		String algorithm = key.getAlgorithm();

		String cacheKey = algorithm + StringPool.POUND + key.toString();

		Cipher cipher = _decryptCipherMap.get(cacheKey);

		if (cipher == null) {
			cipher = Cipher.getInstance(algorithm);

			cipher.init(Cipher.DECRYPT_MODE, key);

			_decryptCipherMap.put(cacheKey, cipher);
		}

		synchronized (cipher) {
			return cipher.doFinal(encryptedBytes);
		}
	}

	private byte[] _decryptGCM(Key key, byte[] encryptedBytes)
		throws Exception {

		if (encryptedBytes.length < _GCM_IV_LENGTH) {
			throw new EncryptorException(
				"Encrypted data is too short for AES/GCM");
		}

		byte[] iv = new byte[_GCM_IV_LENGTH];

		System.arraycopy(encryptedBytes, 0, iv, 0, _GCM_IV_LENGTH);

		byte[] cipherText = new byte[encryptedBytes.length - _GCM_IV_LENGTH];

		System.arraycopy(
			encryptedBytes, _GCM_IV_LENGTH, cipherText, 0, cipherText.length);

		Cipher cipher = Cipher.getInstance(_GCM_TRANSFORMATION);

		cipher.init(
			Cipher.DECRYPT_MODE, key,
			new GCMParameterSpec(_GCM_TAG_LENGTH_BITS, iv));

		return cipher.doFinal(cipherText);
	}

	private String _decryptUnencodedAsString(Key key, byte[] encryptedBytes)
		throws EncryptorException {

		try {
			byte[] decryptedBytes = decryptUnencodedAsBytes(
				key, encryptedBytes);

			return new String(decryptedBytes, ENCODING);
		}
		catch (Exception exception) {
			throw new EncryptorException(exception);
		}
	}

	private byte[] _encryptECB(Key key, byte[] plainBytes) throws Exception {
		String algorithm = key.getAlgorithm();

		String cacheKey = algorithm + StringPool.POUND + key.toString();

		Cipher cipher = _encryptCipherMap.get(cacheKey);

		if (cipher == null) {
			cipher = Cipher.getInstance(algorithm);

			cipher.init(Cipher.ENCRYPT_MODE, key);

			_encryptCipherMap.put(cacheKey, cipher);
		}

		synchronized (cipher) {
			return cipher.doFinal(plainBytes);
		}
	}

	private byte[] _encryptGCM(Key key, byte[] plainBytes) throws Exception {
		byte[] iv = new byte[_GCM_IV_LENGTH];

		_secureRandom.nextBytes(iv);

		Cipher cipher = Cipher.getInstance(_GCM_TRANSFORMATION);

		cipher.init(
			Cipher.ENCRYPT_MODE, key,
			new GCMParameterSpec(_GCM_TAG_LENGTH_BITS, iv));

		byte[] cipherText = cipher.doFinal(plainBytes);

		byte[] encryptedBytes = new byte[_GCM_IV_LENGTH + cipherText.length];

		System.arraycopy(iv, 0, encryptedBytes, 0, _GCM_IV_LENGTH);
		System.arraycopy(
			cipherText, 0, encryptedBytes, _GCM_IV_LENGTH, cipherText.length);

		return encryptedBytes;
	}

	private Key _generateKey(String algorithm) throws EncryptorException {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);

			keyGenerator.init(KEY_SIZE, new SecureRandom());

			return keyGenerator.generateKey();
		}
		catch (Exception exception) {
			throw new EncryptorException(exception);
		}
	}

	private boolean _isGCMMode() {
		return FIPSModeUtil.isFIPSModeEnabled() &&
			"AES".equalsIgnoreCase(KEY_ALGORITHM);
	}

	private void _validateFIPSAlgorithm(String algorithm, int keySize)
		throws EncryptorException {

		boolean allowedAlgorithm = false;

		for (String fipsAlgorithm : _FIPS_ALLOWED_ALGORITHMS) {
			if (fipsAlgorithm.equalsIgnoreCase(algorithm)) {
				allowedAlgorithm = true;

				break;
			}
		}

		if (!allowedAlgorithm) {
			throw new EncryptorException(
				"Algorithm " + algorithm + " is not FIPS-approved. Use AES.");
		}

		boolean allowedKeySize = false;

		for (int fipsKeySize : _FIPS_ALLOWED_KEY_SIZES) {
			if (fipsKeySize == keySize) {
				allowedKeySize = true;

				break;
			}
		}

		if (!allowedKeySize) {
			throw new EncryptorException(
				"Key size " + keySize +
					" is not FIPS-approved. Use 128, 192, or 256.");
		}
	}

	private static final String[] _FIPS_ALLOWED_ALGORITHMS = {"AES"};

	private static final int[] _FIPS_ALLOWED_KEY_SIZES = {128, 192, 256};

	private static final int _GCM_IV_LENGTH = 12;

	private static final int _GCM_TAG_LENGTH_BITS = 128;

	private static final String _GCM_TRANSFORMATION = "AES/GCM/NoPadding";

	private static final Log _log = LogFactoryUtil.getLog(EncryptorImpl.class);

	private final Map<String, Cipher> _decryptCipherMap =
		new ConcurrentHashMap<>(1, 1F, 1);
	private final Map<String, Cipher> _encryptCipherMap =
		new ConcurrentHashMap<>(1, 1F, 1);
	private final SecureRandom _secureRandom = new SecureRandom();

}
