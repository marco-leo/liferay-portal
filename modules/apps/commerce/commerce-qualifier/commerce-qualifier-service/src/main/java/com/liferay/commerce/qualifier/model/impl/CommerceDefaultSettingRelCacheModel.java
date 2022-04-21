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

import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel;
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
 * The cache model class for representing CommerceDefaultSettingRel in entity cache.
 *
 * @author Riccardo Alberti
 * @generated
 */
public class CommerceDefaultSettingRelCacheModel
	implements CacheModel<CommerceDefaultSettingRel>, Externalizable,
			   MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceDefaultSettingRelCacheModel)) {
			return false;
		}

		CommerceDefaultSettingRelCacheModel
			commerceDefaultSettingRelCacheModel =
				(CommerceDefaultSettingRelCacheModel)object;

		if ((commerceDefaultSettingRelId ==
				commerceDefaultSettingRelCacheModel.
					commerceDefaultSettingRelId) &&
			(mvccVersion == commerceDefaultSettingRelCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, commerceDefaultSettingRelId);

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
		StringBundler sb = new StringBundler(25);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", commerceDefaultSettingRelId=");
		sb.append(commerceDefaultSettingRelId);
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
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", commerceDefaultSettingId=");
		sb.append(commerceDefaultSettingId);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceDefaultSettingRel toEntityModel() {
		CommerceDefaultSettingRelImpl commerceDefaultSettingRelImpl =
			new CommerceDefaultSettingRelImpl();

		commerceDefaultSettingRelImpl.setMvccVersion(mvccVersion);
		commerceDefaultSettingRelImpl.setCommerceDefaultSettingRelId(
			commerceDefaultSettingRelId);
		commerceDefaultSettingRelImpl.setCompanyId(companyId);
		commerceDefaultSettingRelImpl.setUserId(userId);

		if (userName == null) {
			commerceDefaultSettingRelImpl.setUserName("");
		}
		else {
			commerceDefaultSettingRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceDefaultSettingRelImpl.setCreateDate(null);
		}
		else {
			commerceDefaultSettingRelImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceDefaultSettingRelImpl.setModifiedDate(null);
		}
		else {
			commerceDefaultSettingRelImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		commerceDefaultSettingRelImpl.setClassNameId(classNameId);
		commerceDefaultSettingRelImpl.setClassPK(classPK);
		commerceDefaultSettingRelImpl.setCommerceDefaultSettingId(
			commerceDefaultSettingId);
		commerceDefaultSettingRelImpl.setPriority(priority);

		if (type == null) {
			commerceDefaultSettingRelImpl.setType("");
		}
		else {
			commerceDefaultSettingRelImpl.setType(type);
		}

		commerceDefaultSettingRelImpl.resetOriginalValues();

		return commerceDefaultSettingRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		commerceDefaultSettingRelId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		commerceDefaultSettingId = objectInput.readLong();

		priority = objectInput.readDouble();
		type = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(commerceDefaultSettingRelId);

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

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(commerceDefaultSettingId);

		objectOutput.writeDouble(priority);

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}
	}

	public long mvccVersion;
	public long commerceDefaultSettingRelId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public long commerceDefaultSettingId;
	public double priority;
	public String type;

}