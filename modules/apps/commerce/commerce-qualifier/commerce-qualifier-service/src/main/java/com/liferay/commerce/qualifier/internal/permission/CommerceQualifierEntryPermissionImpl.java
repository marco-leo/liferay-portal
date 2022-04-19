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

import com.liferay.commerce.qualifier.model.CommerceQualifierEntry;
import com.liferay.commerce.qualifier.permission.CommerceQualifierEntryPermission;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryLocalService;
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
	service = CommerceQualifierEntryPermission.class
)
public class CommerceQualifierEntryPermissionImpl
	implements CommerceQualifierEntryPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceQualifierEntry commerceQualifierEntry, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceQualifierEntry, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceQualifierEntry.class.getName(),
				commerceQualifierEntry.getCommerceQualifierEntryId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commerceQualifierEntryId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceQualifierEntryId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceQualifierEntry.class.getName(),
				commerceQualifierEntryId, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceQualifierEntry commerceQualifierEntry, String actionId)
		throws PortalException {

		if (contains(
				permissionChecker,
				commerceQualifierEntry.getCommerceQualifierEntryId(),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long commerceQualifierEntryId,
			String actionId)
		throws PortalException {

		CommerceQualifierEntry commerceQualifierEntry =
			_commerceQualifierEntryLocalService.fetchCommerceQualifierEntry(
				commerceQualifierEntryId);

		if (commerceQualifierEntry == null) {
			return false;
		}

		return _contains(permissionChecker, commerceQualifierEntry, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			long[] commerceQualifierEntryIds, String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(commerceQualifierEntryIds)) {
			return false;
		}

		for (long commerceQualifierEntryId : commerceQualifierEntryIds) {
			if (!contains(
					permissionChecker, commerceQualifierEntryId, actionId)) {

				return false;
			}
		}

		return true;
	}

	private boolean _contains(
		PermissionChecker permissionChecker,
		CommerceQualifierEntry commerceQualifierEntry, String actionId) {

		if (permissionChecker.isCompanyAdmin(
				commerceQualifierEntry.getCompanyId()) ||
			permissionChecker.isOmniadmin() ||
			permissionChecker.hasOwnerPermission(
				commerceQualifierEntry.getCompanyId(),
				CommerceQualifierEntry.class.getName(),
				commerceQualifierEntry.getCommerceQualifierEntryId(),
				commerceQualifierEntry.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			null, CommerceQualifierEntry.class.getName(),
			commerceQualifierEntry.getCommerceQualifierEntryId(), actionId);
	}

	@Reference
	private CommerceQualifierEntryLocalService
		_commerceQualifierEntryLocalService;

}