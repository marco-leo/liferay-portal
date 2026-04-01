/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.fips.FIPSModeUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.Mac;

/**
 * @author Mika Koivisto
 */
public class CryptoStartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) {
		if (FIPSModeUtil.isFIPSModeEnabled()) {
			_initFIPSMode();
		}
		else {
			_initStandardMode();
		}
	}

	private void _initFIPSMode() {
		if (_log.isInfoEnabled()) {
			_log.info("FIPS mode is enabled, validating FIPS crypto provider");
		}

		try {
			Provider[] providers = Security.getProviders();

			boolean fipsProviderFound = false;

			for (Provider provider : providers) {
				String providerName = provider.getName();

				if (providerName.contains("FIPS") ||
					providerName.contains("BCFIPS")) {

					fipsProviderFound = true;

					if (_log.isInfoEnabled()) {
						_log.info(
							"Found FIPS security provider: " + providerName);
					}

					break;
				}
			}

			if (!fipsProviderFound) {
				_log.error(
					"FIPS mode is enabled but no FIPS security provider is " +
						"registered. Please configure a FIPS-certified " +
							"security provider (e.g., Bouncy Castle FIPS) " +
								"in the JVM security configuration.");
			}
		}
		catch (Exception exception) {
			_log.error(
				"Unable to check FIPS security providers", exception);
		}

		_testAlgorithm("Mac", "HmacSHA256");
		_testAlgorithm("Cipher", "AES");
		_testAlgorithm("MessageDigest", "SHA-256");
	}

	private void _initStandardMode() {
		try {
			Mac.getInstance("HmacSHA1");
		}
		catch (NoSuchAlgorithmException noSuchAlgorithmException) {
			_log.error(
				"Unable to get Mac instance for algorithm HmacSHA1",
				noSuchAlgorithmException);
		}
	}

	private void _testAlgorithm(String type, String algorithm) {
		try {
			switch (type) {
				case "Cipher":
					Cipher.getInstance(algorithm);

					break;

				case "Mac":
					Mac.getInstance(algorithm);

					break;

				case "MessageDigest":
					MessageDigest.getInstance(algorithm);

					break;
			}

			if (_log.isInfoEnabled()) {
				_log.info(
					"FIPS crypto self-test passed: " + type + "/" + algorithm);
			}
		}
		catch (Exception exception) {
			_log.error(
				"FIPS crypto self-test failed: " + type + "/" + algorithm +
					". This algorithm is required for FIPS mode.",
				exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CryptoStartupAction.class);

}
