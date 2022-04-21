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
 * Provides a wrapper for {@link CommerceDefaultSettingRelService}.
 *
 * @author Riccardo Alberti
 * @see CommerceDefaultSettingRelService
 * @generated
 */
public class CommerceDefaultSettingRelServiceWrapper
	implements CommerceDefaultSettingRelService,
			   ServiceWrapper<CommerceDefaultSettingRelService> {

	public CommerceDefaultSettingRelServiceWrapper() {
		this(null);
	}

	public CommerceDefaultSettingRelServiceWrapper(
		CommerceDefaultSettingRelService commerceDefaultSettingRelService) {

		_commerceDefaultSettingRelService = commerceDefaultSettingRelService;
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
			addCommerceDefaultSettingRel(
				long commerceDefaultSettingId, String className, long classPK,
				double priority, String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingRelService.addCommerceDefaultSettingRel(
			commerceDefaultSettingId, className, classPK, priority, type);
	}

	@Override
	public void deleteCommerceDefaultSettingRel(
			long commerceDefaultSettingRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceDefaultSettingRelService.deleteCommerceDefaultSettingRel(
			commerceDefaultSettingRelId);
	}

	@Override
	public void deleteCommerceDefaultSettingRels(long commerceDefaultSettingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceDefaultSettingRelService.deleteCommerceDefaultSettingRels(
			commerceDefaultSettingId);
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
			getCommerceDefaultSettingRel(long commerceDefaultSettingRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingRelService.getCommerceDefaultSettingRel(
			commerceDefaultSettingRelId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel>
				getCommerceDefaultSettingRels(
					long commerceDefaultSettingId, String type)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingRelService.getCommerceDefaultSettingRels(
			commerceDefaultSettingId, type);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceDefaultSettingRelService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
			updateCommerceDefaultSettingRel(
				long commerceDefaultSettingRelId, double priority)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingRelService.
			updateCommerceDefaultSettingRel(
				commerceDefaultSettingRelId, priority);
	}

	@Override
	public CommerceDefaultSettingRelService getWrappedService() {
		return _commerceDefaultSettingRelService;
	}

	@Override
	public void setWrappedService(
		CommerceDefaultSettingRelService commerceDefaultSettingRelService) {

		_commerceDefaultSettingRelService = commerceDefaultSettingRelService;
	}

	private CommerceDefaultSettingRelService _commerceDefaultSettingRelService;

}