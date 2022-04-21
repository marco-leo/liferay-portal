/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.qualifier.model.impl;

import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceDefaultSetting in entity cache.
 *
 * @author Riccardo Alberti
 * @generated
 */
public class CommerceDefaultSettingCacheModel
	implements CacheModel<CommerceDefaultSetting>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceDefaultSettingCacheModel)) {
			return false;
		}

		CommerceDefaultSettingCacheModel commerceDefaultSettingCacheModel =
			(CommerceDefaultSettingCacheModel)object;

		if ((commerceDefaultSettingId ==
				commerceDefaultSettingCacheModel.commerceDefaultSettingId) &&
			(mvccVersion == commerceDefaultSettingCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, commerceDefaultSettingId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", commerceDefaultSettingId=");
		sb.append(commerceDefaultSettingId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceDefaultSetting toEntityModel() {
		CommerceDefaultSettingImpl commerceDefaultSettingImpl =
			new CommerceDefaultSettingImpl();

		commerceDefaultSettingImpl.setMvccVersion(mvccVersion);
		commerceDefaultSettingImpl.setCommerceDefaultSettingId(
			commerceDefaultSettingId);
		commerceDefaultSettingImpl.setCompanyId(companyId);
		commerceDefaultSettingImpl.setUserId(userId);

		if (userName == null) {
			commerceDefaultSettingImpl.setUserName("");
		}
		else {
			commerceDefaultSettingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceDefaultSettingImpl.setCreateDate(null);
		}
		else {
			commerceDefaultSettingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceDefaultSettingImpl.setModifiedDate(null);
		}
		else {
			commerceDefaultSettingImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			commerceDefaultSettingImpl.setName("");
		}
		else {
			commerceDefaultSettingImpl.setName(name);
		}

		commerceDefaultSettingImpl.resetOriginalValues();

		return commerceDefaultSettingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		commerceDefaultSettingId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(commerceDefaultSettingId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long mvccVersion;
	public long commerceDefaultSettingId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;

}