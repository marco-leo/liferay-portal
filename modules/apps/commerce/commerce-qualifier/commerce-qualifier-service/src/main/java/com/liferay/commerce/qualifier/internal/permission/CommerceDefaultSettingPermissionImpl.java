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

package com.liferay.commerce.qualifier.internal.permission;

import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.commerce.qualifier.permission.CommerceDefaultSettingPermission;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	service = CommerceDefaultSettingPermission.class
)
public class CommerceDefaultSettingPermissionImpl
	implements CommerceDefaultSettingPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceDefaultSetting commerceDefaultSetting, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceDefaultSetting, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceDefaultSetting.class.getName(),
				commerceDefaultSetting.getCommerceDefaultSettingId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commerceDefaultSettingId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceDefaultSettingId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceDefaultSetting.class.getName(),
				commerceDefaultSettingId, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceDefaultSetting commerceDefaultSetting, String actionId)
		throws PortalException {

		if (contains(
				permissionChecker,
				commerceDefaultSetting.getCommerceDefaultSettingId(),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long commerceDefaultSettingId,
			String actionId)
		throws PortalException {

		CommerceDefaultSetting commerceDefaultSetting =
			_commerceDefaultSettingLocalService.fetchCommerceDefaultSetting(
				commerceDefaultSettingId);

		if (commerceDefaultSetting == null) {
			return false;
		}

		return _contains(permissionChecker, commerceDefaultSetting, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			long[] commerceDefaultSettingIds, String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(commerceDefaultSettingIds)) {
			return false;
		}

		for (long commerceDefaultSettingId : commerceDefaultSettingIds) {
			if (!contains(
					permissionChecker, commerceDefaultSettingId, actionId)) {

				return false;
			}
		}

		return true;
	}

	private boolean _contains(
		PermissionChecker permissionChecker,
		CommerceDefaultSetting commerceDefaultSetting, String actionId) {

		if (permissionChecker.isCompanyAdmin(
				commerceDefaultSetting.getCompanyId()) ||
			permissionChecker.isOmniadmin() ||
			permissionChecker.hasOwnerPermission(
				commerceDefaultSetting.getCompanyId(),
				CommerceDefaultSetting.class.getName(),
				commerceDefaultSetting.getCommerceDefaultSettingId(),
				commerceDefaultSetting.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			null, CommerceDefaultSetting.class.getName(),
			commerceDefaultSetting.getCommerceDefaultSettingId(), actionId);
	}

	@Reference
	private CommerceDefaultSettingLocalService
		_commerceDefaultSettingLocalService;

}