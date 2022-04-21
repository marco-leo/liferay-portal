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

import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for CommerceDefaultSettingRel. This utility wraps
 * <code>com.liferay.commerce.qualifier.service.impl.CommerceDefaultSettingRelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Riccardo Alberti
 * @see CommerceDefaultSettingRelService
 * @generated
 */
public class CommerceDefaultSettingRelServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.qualifier.service.impl.CommerceDefaultSettingRelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceDefaultSettingRel addCommerceDefaultSettingRel(
			long commerceDefaultSettingId, String className, long classPK,
			double priority, String type)
		throws PortalException {

		return getService().addCommerceDefaultSettingRel(
			commerceDefaultSettingId, className, classPK, priority, type);
	}

	public static void deleteCommerceDefaultSettingRel(
			long commerceDefaultSettingRelId)
		throws PortalException {

		getService().deleteCommerceDefaultSettingRel(
			commerceDefaultSettingRelId);
	}

	public static void deleteCommerceDefaultSettingRels(
			long commerceDefaultSettingId)
		throws PortalException {

		getService().deleteCommerceDefaultSettingRels(commerceDefaultSettingId);
	}

	public static CommerceDefaultSettingRel getCommerceDefaultSettingRel(
			long commerceDefaultSettingRelId)
		throws PortalException {

		return getService().getCommerceDefaultSettingRel(
			commerceDefaultSettingRelId);
	}

	public static List<CommerceDefaultSettingRel> getCommerceDefaultSettingRels(
			long commerceDefaultSettingId, String type)
		throws PortalException {

		return getService().getCommerceDefaultSettingRels(
			commerceDefaultSettingId, type);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceDefaultSettingRel updateCommerceDefaultSettingRel(
			long commerceDefaultSettingRelId, double priority)
		throws PortalException {

		return getService().updateCommerceDefaultSettingRel(
			commerceDefaultSettingRelId, priority);
	}

	public static CommerceDefaultSettingRelService getService() {
		return _service;
	}

	private static volatile CommerceDefaultSettingRelService _service;

}