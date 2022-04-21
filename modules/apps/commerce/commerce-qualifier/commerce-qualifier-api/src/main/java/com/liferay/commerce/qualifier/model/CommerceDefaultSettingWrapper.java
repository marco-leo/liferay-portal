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

package com.liferay.commerce.qualifier.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CommerceDefaultSetting}.
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommerceDefaultSetting
 * @generated
 */
public class CommerceDefaultSettingWrapper
	extends BaseModelWrapper<CommerceDefaultSetting>
	implements CommerceDefaultSetting, ModelWrapper<CommerceDefaultSetting> {

	public CommerceDefaultSettingWrapper(
		CommerceDefaultSetting commerceDefaultSetting) {

		super(commerceDefaultSetting);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put(
			"commerceDefaultSettingId", getCommerceDefaultSettingId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long commerceDefaultSettingId = (Long)attributes.get(
			"commerceDefaultSettingId");

		if (commerceDefaultSettingId != null) {
			setCommerceDefaultSettingId(commerceDefaultSettingId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public CommerceDefaultSetting cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the commerce default setting ID of this commerce default setting.
	 *
	 * @return the commerce default setting ID of this commerce default setting
	 */
	@Override
	public long getCommerceDefaultSettingId() {
		return model.getCommerceDefaultSettingId();
	}

	/**
	 * Returns the company ID of this commerce default setting.
	 *
	 * @return the company ID of this commerce default setting
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this commerce default setting.
	 *
	 * @return the create date of this commerce default setting
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the modified date of this commerce default setting.
	 *
	 * @return the modified date of this commerce default setting
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this commerce default setting.
	 *
	 * @return the mvcc version of this commerce default setting
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this commerce default setting.
	 *
	 * @return the name of this commerce default setting
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this commerce default setting.
	 *
	 * @return the primary key of this commerce default setting
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this commerce default setting.
	 *
	 * @return the user ID of this commerce default setting
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this commerce default setting.
	 *
	 * @return the user name of this commerce default setting
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this commerce default setting.
	 *
	 * @return the user uuid of this commerce default setting
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the commerce default setting ID of this commerce default setting.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID of this commerce default setting
	 */
	@Override
	public void setCommerceDefaultSettingId(long commerceDefaultSettingId) {
		model.setCommerceDefaultSettingId(commerceDefaultSettingId);
	}

	/**
	 * Sets the company ID of this commerce default setting.
	 *
	 * @param companyId the company ID of this commerce default setting
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this commerce default setting.
	 *
	 * @param createDate the create date of this commerce default setting
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the modified date of this commerce default setting.
	 *
	 * @param modifiedDate the modified date of this commerce default setting
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this commerce default setting.
	 *
	 * @param mvccVersion the mvcc version of this commerce default setting
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this commerce default setting.
	 *
	 * @param name the name of this commerce default setting
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this commerce default setting.
	 *
	 * @param primaryKey the primary key of this commerce default setting
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this commerce default setting.
	 *
	 * @param userId the user ID of this commerce default setting
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this commerce default setting.
	 *
	 * @param userName the user name of this commerce default setting
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this commerce default setting.
	 *
	 * @param userUuid the user uuid of this commerce default setting
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected CommerceDefaultSettingWrapper wrap(
		CommerceDefaultSetting commerceDefaultSetting) {

		return new CommerceDefaultSettingWrapper(commerceDefaultSetting);
	}

}