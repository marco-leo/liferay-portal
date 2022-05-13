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

package com.liferay.commerce.qualifier.service;

import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for CommerceDefaultSetting. This utility wraps
 * <code>com.liferay.commerce.qualifier.service.impl.CommerceDefaultSettingServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Riccardo Alberti
 * @see CommerceDefaultSettingService
 * @generated
 */
public class CommerceDefaultSettingServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.qualifier.service.impl.CommerceDefaultSettingServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceDefaultSetting addCommerceDefaultSetting(
			String name, String parameterSettings,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceDefaultSetting(
			name, parameterSettings, serviceContext);
	}

	public static CommerceDefaultSetting deleteCommerceDefaultSetting(
			long commerceDefaultSettingId)
		throws PortalException {

		return getService().deleteCommerceDefaultSetting(
			commerceDefaultSettingId);
	}

	public static CommerceDefaultSetting fetchCommerceDefaultSetting(
			long commerceDefaultSettingId)
		throws PortalException {

		return getService().fetchCommerceDefaultSetting(
			commerceDefaultSettingId);
	}

	public static CommerceDefaultSetting getCommerceDefaultSetting(
			long commerceDefaultSettingId)
		throws PortalException {

		return getService().getCommerceDefaultSetting(commerceDefaultSettingId);
	}

	public static List<CommerceDefaultSetting> getCommerceDefaultSettings(
			long companyId, String keywords, int start, int end)
		throws PortalException {

		return getService().getCommerceDefaultSettings(
			companyId, keywords, start, end);
	}

	public static int getCommerceDefaultSettingsCount(
			long companyId, String keywords)
		throws PortalException {

		return getService().getCommerceDefaultSettingsCount(
			companyId, keywords);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceDefaultSetting updateCommerceDefaultSetting(
			long commerceDefaultSettingId, String name,
			String parameterSettings,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCommerceDefaultSetting(
			commerceDefaultSettingId, name, parameterSettings, serviceContext);
	}

	public static CommerceDefaultSettingService getService() {
		return _service;
	}

	private static volatile CommerceDefaultSettingService _service;

}