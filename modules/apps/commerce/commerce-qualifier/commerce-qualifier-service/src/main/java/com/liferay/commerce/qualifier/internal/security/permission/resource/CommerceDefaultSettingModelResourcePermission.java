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

package com.liferay.commerce.qualifier.internal.security.permission.resource;

import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.commerce.qualifier.permission.CommerceDefaultSettingPermission;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = "model.class.name=com.liferay.commerce.qualifier.model.CommerceDefaultSetting",
	service = ModelResourcePermission.class
)
public class CommerceDefaultSettingModelResourcePermission
	implements ModelResourcePermission<CommerceDefaultSetting> {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceDefaultSetting commerceDefaultSetting, String actionId)
		throws PortalException {

		_commerceDefaultSettingPermission.check(
			permissionChecker,
			commerceDefaultSetting.getCommerceDefaultSettingId(), actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commerceDefaultSettingId,
			String actionId)
		throws PortalException {

		CommerceDefaultSetting commerceDefaultSetting =
			_commerceDefaultSettingLocalService.getCommerceDefaultSetting(
				commerceDefaultSettingId);

		_commerceDefaultSettingPermission.check(
			permissionChecker,
			commerceDefaultSetting.getCommerceDefaultSettingId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceDefaultSetting commerceDefaultSetting, String actionId)
		throws PortalException {

		return _commerceDefaultSettingPermission.contains(
			permissionChecker,
			commerceDefaultSetting.getCommerceDefaultSettingId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long commerceDefaultSettingId,
			String actionId)
		throws PortalException {

		CommerceDefaultSetting commerceDefaultSetting =
			_commerceDefaultSettingLocalService.getCommerceDefaultSetting(
				commerceDefaultSettingId);

		return _commerceDefaultSettingPermission.contains(
			permissionChecker,
			commerceDefaultSetting.getCommerceDefaultSettingId(), actionId);
	}

	@Override
	public String getModelName() {
		return CommerceDefaultSetting.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return _portletResourcePermission;
	}

	@Reference
	private CommerceDefaultSettingLocalService
		_commerceDefaultSettingLocalService;

	@Reference
	private CommerceDefaultSettingPermission _commerceDefaultSettingPermission;

	@Reference(
		target = "(resource.name=com.liferay.commerce.qualifier.default.setting)"
	)
	private PortletResourcePermission _portletResourcePermission;

}