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

package com.liferay.commerce.qualifier.service.impl;

import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.commerce.qualifier.service.base.CommerceDefaultSettingServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = {
		"json.web.service.context.name=commerce",
		"json.web.service.context.path=CommerceDefaultSetting"
	},
	service = AopService.class
)
public class CommerceDefaultSettingServiceImpl
	extends CommerceDefaultSettingServiceBaseImpl {

	@Override
	public CommerceDefaultSetting addCommerceDefaultSetting(
			String name, ServiceContext serviceContext)
		throws PortalException {

		PortletResourcePermission portletResourcePermission =
			_commerceDefaultSettingModelResourcePermission.
				getPortletResourcePermission();

		portletResourcePermission.check(
			getPermissionChecker(), null, "ADD_COMMERCE_DEFAULT_SETTING");

		return commerceDefaultSettingLocalService.addCommerceDefaultSetting(
			getUserId(), name, serviceContext);
	}

	@Override
	public CommerceDefaultSetting deleteCommerceDefaultSetting(
			long commerceDefaultSettingId)
		throws PortalException {

		_commerceDefaultSettingModelResourcePermission.check(
			getPermissionChecker(), commerceDefaultSettingId,
			ActionKeys.DELETE);

		return commerceDefaultSettingLocalService.deleteCommerceDefaultSetting(
			commerceDefaultSettingId);
	}

	@Override
	public CommerceDefaultSetting fetchCommerceDefaultSetting(
			long commerceDefaultSettingId)
		throws PortalException {

		CommerceDefaultSetting commerceDefaultSetting =
			commerceDefaultSettingLocalService.fetchCommerceDefaultSetting(
				commerceDefaultSettingId);

		if (commerceDefaultSetting != null) {
			_commerceDefaultSettingModelResourcePermission.check(
				getPermissionChecker(), commerceDefaultSettingId,
				ActionKeys.VIEW);
		}

		return commerceDefaultSetting;
	}

	@Override
	public CommerceDefaultSetting getCommerceDefaultSetting(
			long commerceDefaultSettingId)
		throws PortalException {

		_commerceDefaultSettingModelResourcePermission.check(
			getPermissionChecker(), commerceDefaultSettingId, ActionKeys.VIEW);

		return commerceDefaultSettingLocalService.getCommerceDefaultSetting(
			commerceDefaultSettingId);
	}

	@Override
	public List<CommerceDefaultSetting> getCommerceDefaultSettings(
			long companyId, String keywords, int start, int end)
		throws PortalException {

		PortletResourcePermission portletResourcePermission =
			_commerceDefaultSettingModelResourcePermission.
				getPortletResourcePermission();

		portletResourcePermission.check(
			getPermissionChecker(), null, "VIEW_COMMERCE_DEFAULT_SETTINGS");

		return commerceDefaultSettingLocalService.getCommerceDefaultSettings(
			companyId, keywords, start, end);
	}

	@Override
	public int getCommerceDefaultSettingsCount(long companyId, String keywords)
		throws PortalException {

		PortletResourcePermission portletResourcePermission =
			_commerceDefaultSettingModelResourcePermission.
				getPortletResourcePermission();

		portletResourcePermission.check(
			getPermissionChecker(), null, "VIEW_COMMERCE_DEFAULT_SETTINGS");

		return commerceDefaultSettingLocalService.
			getCommerceDefaultSettingsCount(companyId, keywords);
	}

	@Override
	public CommerceDefaultSetting updateCommerceDefaultSetting(
			long commerceDefaultSettingId, String name,
			ServiceContext serviceContext)
		throws PortalException {

		_commerceDefaultSettingModelResourcePermission.check(
			getPermissionChecker(), commerceDefaultSettingId,
			ActionKeys.UPDATE);

		return commerceDefaultSettingLocalService.updateCommerceDefaultSetting(
			commerceDefaultSettingId, name, serviceContext);
	}

	private static volatile ModelResourcePermission<CommerceDefaultSetting>
		_commerceDefaultSettingModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceDefaultSettingServiceImpl.class,
				"_commerceDefaultSettingModelResourcePermission",
				CommerceDefaultSetting.class);

}