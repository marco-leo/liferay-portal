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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceDefaultSettingService}.
 *
 * @author Riccardo Alberti
 * @see CommerceDefaultSettingService
 * @generated
 */
public class CommerceDefaultSettingServiceWrapper
	implements CommerceDefaultSettingService,
			   ServiceWrapper<CommerceDefaultSettingService> {

	public CommerceDefaultSettingServiceWrapper() {
		this(null);
	}

	public CommerceDefaultSettingServiceWrapper(
		CommerceDefaultSettingService commerceDefaultSettingService) {

		_commerceDefaultSettingService = commerceDefaultSettingService;
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSetting
			addCommerceDefaultSetting(
				String name,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingService.addCommerceDefaultSetting(
			name, serviceContext);
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSetting
			deleteCommerceDefaultSetting(long commerceDefaultSettingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingService.deleteCommerceDefaultSetting(
			commerceDefaultSettingId);
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSetting
			fetchCommerceDefaultSetting(long commerceDefaultSettingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingService.fetchCommerceDefaultSetting(
			commerceDefaultSettingId);
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSetting
			getCommerceDefaultSetting(long commerceDefaultSettingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingService.getCommerceDefaultSetting(
			commerceDefaultSettingId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.qualifier.model.CommerceDefaultSetting>
				getCommerceDefaultSettings(
					long companyId, String keywords, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingService.getCommerceDefaultSettings(
			companyId, keywords, start, end);
	}

	@Override
	public int getCommerceDefaultSettingsCount(long companyId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingService.getCommerceDefaultSettingsCount(
			companyId, keywords);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceDefaultSettingService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSetting
			updateCommerceDefaultSetting(
				long commerceDefaultSettingId, String name,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingService.updateCommerceDefaultSetting(
			commerceDefaultSettingId, name, serviceContext);
	}

	@Override
	public CommerceDefaultSettingService getWrappedService() {
		return _commerceDefaultSettingService;
	}

	@Override
	public void setWrappedService(
		CommerceDefaultSettingService commerceDefaultSettingService) {

		_commerceDefaultSettingService = commerceDefaultSettingService;
	}

	private CommerceDefaultSettingService _commerceDefaultSettingService;

}