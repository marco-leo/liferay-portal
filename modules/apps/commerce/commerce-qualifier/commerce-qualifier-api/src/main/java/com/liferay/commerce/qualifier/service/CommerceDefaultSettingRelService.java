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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for CommerceDefaultSettingRel. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Riccardo Alberti
 * @see CommerceDefaultSettingRelServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CommerceDefaultSettingRelService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.commerce.qualifier.service.impl.CommerceDefaultSettingRelServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the commerce default setting rel remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link CommerceDefaultSettingRelServiceUtil} if injection and service tracking are not available.
	 */
	public CommerceDefaultSettingRel addCommerceDefaultSettingRel(
			long commerceDefaultSettingId, String className, long classPK,
			double priority, String type)
		throws PortalException;

	public void deleteCommerceDefaultSettingRel(
			long commerceDefaultSettingRelId)
		throws PortalException;

	public void deleteCommerceDefaultSettingRels(long commerceDefaultSettingId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceDefaultSettingRel getCommerceDefaultSettingRel(
			long commerceDefaultSettingRelId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceDefaultSettingRel> getCommerceDefaultSettingRels(
			long commerceDefaultSettingId, String type)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public CommerceDefaultSettingRel updateCommerceDefaultSettingRel(
			long commerceDefaultSettingRelId, double priority)
		throws PortalException;

}