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
 * Provides a wrapper for {@link CommerceQualifierEntryService}.
 *
 * @author Riccardo Alberti
 * @see CommerceQualifierEntryService
 * @generated
 */
public class CommerceQualifierEntryServiceWrapper
	implements CommerceQualifierEntryService,
			   ServiceWrapper<CommerceQualifierEntryService> {

	public CommerceQualifierEntryServiceWrapper() {
		this(null);
	}

	public CommerceQualifierEntryServiceWrapper(
		CommerceQualifierEntryService commerceQualifierEntryService) {

		_commerceQualifierEntryService = commerceQualifierEntryService;
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceQualifierEntry
			addCommerceQualifierEntry(
				String sourceClassName, long sourceClassPK,
				String targetClassName, long targetClassPK,
				boolean targetDefault)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualifierEntryService.addCommerceQualifierEntry(
			sourceClassName, sourceClassPK, targetClassName, targetClassPK,
			targetDefault);
	}

	@Override
	public void deleteCommerceQualifierEntriesBySource(
			String sourceClassName, long sourceClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceQualifierEntryService.deleteCommerceQualifierEntriesBySource(
			sourceClassName, sourceClassPK);
	}

	@Override
	public void deleteCommerceQualifierEntriesBySource(
			String sourceClassName, long sourceClassPK, String targetClassName)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceQualifierEntryService.deleteCommerceQualifierEntriesBySource(
			sourceClassName, sourceClassPK, targetClassName);
	}

	@Override
	public void deleteCommerceQualifierEntriesByTarget(
			String targetClassName, long targetClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceQualifierEntryService.deleteCommerceQualifierEntriesByTarget(
			targetClassName, targetClassPK);
	}

	@Override
	public void deleteCommerceQualifierEntriesByTarget(
			String sourceClassName, String targetClassName, long targetClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceQualifierEntryService.deleteCommerceQualifierEntriesByTarget(
			sourceClassName, targetClassName, targetClassPK);
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceQualifierEntry
			deleteCommerceQualifierEntry(long commerceQualifierEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualifierEntryService.deleteCommerceQualifierEntry(
			commerceQualifierEntryId);
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceQualifierEntry
			fetchCommerceQualifierEntry(
				String sourceClassName, long sourceClassPK,
				String targetClassName, long targetClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualifierEntryService.fetchCommerceQualifierEntry(
			sourceClassName, sourceClassPK, targetClassName, targetClassPK);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.qualifier.model.CommerceQualifierEntry>
				getCommerceQualifierEntriesBySource(
					long companyId, String sourceClassName, long sourceClassPK,
					String targetClassName, String keywords, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualifierEntryService.
			getCommerceQualifierEntriesBySource(
				companyId, sourceClassName, sourceClassPK, targetClassName,
				keywords, start, end);
	}

	@Override
	public int getCommerceQualifierEntriesBySourceCount(
			long companyId, String sourceClassName, long sourceClassPK,
			String targetClassName, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualifierEntryService.
			getCommerceQualifierEntriesBySourceCount(
				companyId, sourceClassName, sourceClassPK, targetClassName,
				keywords);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.qualifier.model.CommerceQualifierEntry>
				getCommerceQualifierEntriesByTarget(
					long companyId, String sourceClassName,
					String targetClassName, long targetClassPK, String keywords,
					int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualifierEntryService.
			getCommerceQualifierEntriesByTarget(
				companyId, sourceClassName, targetClassName, targetClassPK,
				keywords, start, end);
	}

	@Override
	public int getCommerceQualifierEntriesByTargetCount(
			long companyId, String sourceClassName, String targetClassName,
			long targetClassPK, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualifierEntryService.
			getCommerceQualifierEntriesByTargetCount(
				companyId, sourceClassName, targetClassName, targetClassPK,
				keywords);
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceQualifierEntry
			getCommerceQualifierEntry(long commerceQualifierEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualifierEntryService.getCommerceQualifierEntry(
			commerceQualifierEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceQualifierEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceQualifierEntry
			updateCommerceQualifierEntry(
				long commerceQualifierEntryId, boolean targetDefault)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualifierEntryService.updateCommerceQualifierEntry(
			commerceQualifierEntryId, targetDefault);
	}

	@Override
	public CommerceQualifierEntryService getWrappedService() {
		return _commerceQualifierEntryService;
	}

	@Override
	public void setWrappedService(
		CommerceQualifierEntryService commerceQualifierEntryService) {

		_commerceQualifierEntryService = commerceQualifierEntryService;
	}

	private CommerceQualifierEntryService _commerceQualifierEntryService;

}