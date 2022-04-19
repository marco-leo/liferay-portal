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

import com.liferay.commerce.qualifier.model.CommerceQualifierEntry;
import com.liferay.commerce.qualifier.permission.CommerceQualifierEntryPermission;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryLocalService;
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
	property = "model.class.name=com.liferay.commerce.qualifier.model.CommerceQualifierEntry",
	service = ModelResourcePermission.class
)
public class CommerceQualifierEntryModelResourcePermission
	implements ModelResourcePermission<CommerceQualifierEntry> {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceQualifierEntry commerceQualifierEntry, String actionId)
		throws PortalException {

		_commerceQualifierEntryPermission.check(
			permissionChecker,
			commerceQualifierEntry.getCommerceQualifierEntryId(), actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commerceQualifierEntryId,
			String actionId)
		throws PortalException {

		CommerceQualifierEntry commerceQualifierEntry =
			_commerceQualifierEntryLocalService.getCommerceQualifierEntry(
				commerceQualifierEntryId);

		_commerceQualifierEntryPermission.check(
			permissionChecker,
			commerceQualifierEntry.getCommerceQualifierEntryId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceQualifierEntry commerceQualifierEntry, String actionId)
		throws PortalException {

		return _commerceQualifierEntryPermission.contains(
			permissionChecker,
			commerceQualifierEntry.getCommerceQualifierEntryId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long commerceQualifierEntryId,
			String actionId)
		throws PortalException {

		CommerceQualifierEntry commerceQualifierEntry =
			_commerceQualifierEntryLocalService.getCommerceQualifierEntry(
				commerceQualifierEntryId);

		return _commerceQualifierEntryPermission.contains(
			permissionChecker,
			commerceQualifierEntry.getCommerceQualifierEntryId(), actionId);
	}

	@Override
	public String getModelName() {
		return CommerceQualifierEntry.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	private CommerceQualifierEntryLocalService
		_commerceQualifierEntryLocalService;

	@Reference
	private CommerceQualifierEntryPermission _commerceQualifierEntryPermission;

}