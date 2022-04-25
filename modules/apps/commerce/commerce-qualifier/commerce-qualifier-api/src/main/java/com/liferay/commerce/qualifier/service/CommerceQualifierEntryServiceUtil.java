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

import com.liferay.commerce.qualifier.model.CommerceQualifierEntry;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for CommerceQualifierEntry. This utility wraps
 * <code>com.liferay.commerce.qualifier.service.impl.CommerceQualifierEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Riccardo Alberti
 * @see CommerceQualifierEntryService
 * @generated
 */
public class CommerceQualifierEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.qualifier.service.impl.CommerceQualifierEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceQualifierEntry addCommerceQualifierEntry(
			String sourceClassName, long sourceClassPK, String targetClassName,
			long targetClassPK, boolean targetDefault)
		throws PortalException {

		return getService().addCommerceQualifierEntry(
			sourceClassName, sourceClassPK, targetClassName, targetClassPK,
			targetDefault);
	}

	public static void deleteCommerceQualifierEntriesBySource(
			String sourceClassName, long sourceClassPK)
		throws PortalException {

		getService().deleteCommerceQualifierEntriesBySource(
			sourceClassName, sourceClassPK);
	}

	public static void deleteCommerceQualifierEntriesBySource(
			String sourceClassName, long sourceClassPK, String targetClassName)
		throws PortalException {

		getService().deleteCommerceQualifierEntriesBySource(
			sourceClassName, sourceClassPK, targetClassName);
	}

	public static void deleteCommerceQualifierEntriesByTarget(
			String targetClassName, long targetClassPK)
		throws PortalException {

		getService().deleteCommerceQualifierEntriesByTarget(
			targetClassName, targetClassPK);
	}

	public static void deleteCommerceQualifierEntriesByTarget(
			String sourceClassName, String targetClassName, long targetClassPK)
		throws PortalException {

		getService().deleteCommerceQualifierEntriesByTarget(
			sourceClassName, targetClassName, targetClassPK);
	}

	public static CommerceQualifierEntry deleteCommerceQualifierEntry(
			long commerceQualifierEntryId)
		throws PortalException {

		return getService().deleteCommerceQualifierEntry(
			commerceQualifierEntryId);
	}

	public static CommerceQualifierEntry fetchCommerceQualifierEntry(
			String sourceClassName, long sourceClassPK, String targetClassName,
			long targetClassPK)
		throws PortalException {

		return getService().fetchCommerceQualifierEntry(
			sourceClassName, sourceClassPK, targetClassName, targetClassPK);
	}

	public static List<CommerceQualifierEntry>
			getCommerceQualifierEntriesBySource(
				long companyId, String sourceClassName, long sourceClassPK,
				String targetClassName, String keywords, int start, int end)
		throws PortalException {

		return getService().getCommerceQualifierEntriesBySource(
			companyId, sourceClassName, sourceClassPK, targetClassName,
			keywords, start, end);
	}

	public static int getCommerceQualifierEntriesBySourceCount(
			long companyId, String sourceClassName, long sourceClassPK,
			String targetClassName, String keywords)
		throws PortalException {

		return getService().getCommerceQualifierEntriesBySourceCount(
			companyId, sourceClassName, sourceClassPK, targetClassName,
			keywords);
	}

	public static List<CommerceQualifierEntry>
			getCommerceQualifierEntriesByTarget(
				long companyId, String sourceClassName, String targetClassName,
				long targetClassPK, String keywords, int start, int end)
		throws PortalException {

		return getService().getCommerceQualifierEntriesByTarget(
			companyId, sourceClassName, targetClassName, targetClassPK,
			keywords, start, end);
	}

	public static int getCommerceQualifierEntriesByTargetCount(
			long companyId, String sourceClassName, String targetClassName,
			long targetClassPK, String keywords)
		throws PortalException {

		return getService().getCommerceQualifierEntriesByTargetCount(
			companyId, sourceClassName, targetClassName, targetClassPK,
			keywords);
	}

	public static CommerceQualifierEntry getCommerceQualifierEntry(
			long commerceQualifierEntryId)
		throws PortalException {

		return getService().getCommerceQualifierEntry(commerceQualifierEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceQualifierEntry updateCommerceQualifierEntry(
			long commerceQualifierEntryId, boolean targetDefault)
		throws PortalException {

		return getService().updateCommerceQualifierEntry(
			commerceQualifierEntryId, targetDefault);
	}

	public static CommerceQualifierEntryService getService() {
		return _service;
	}

	private static volatile CommerceQualifierEntryService _service;

}