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
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for CommerceDefaultSetting. This utility wraps
 * <code>com.liferay.commerce.qualifier.service.impl.CommerceDefaultSettingLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Riccardo Alberti
 * @see CommerceDefaultSettingLocalService
 * @generated
 */
public class CommerceDefaultSettingLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.qualifier.service.impl.CommerceDefaultSettingLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce default setting to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceDefaultSettingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceDefaultSetting the commerce default setting
	 * @return the commerce default setting that was added
	 */
	public static CommerceDefaultSetting addCommerceDefaultSetting(
		CommerceDefaultSetting commerceDefaultSetting) {

		return getService().addCommerceDefaultSetting(commerceDefaultSetting);
	}

	public static CommerceDefaultSetting addCommerceDefaultSetting(
			long userId, String name,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceDefaultSetting(
			userId, name, serviceContext);
	}

	/**
	 * Creates a new commerce default setting with the primary key. Does not add the commerce default setting to the database.
	 *
	 * @param commerceDefaultSettingId the primary key for the new commerce default setting
	 * @return the new commerce default setting
	 */
	public static CommerceDefaultSetting createCommerceDefaultSetting(
		long commerceDefaultSettingId) {

		return getService().createCommerceDefaultSetting(
			commerceDefaultSettingId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the commerce default setting from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceDefaultSettingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceDefaultSetting the commerce default setting
	 * @return the commerce default setting that was removed
	 * @throws PortalException
	 */
	public static CommerceDefaultSetting deleteCommerceDefaultSetting(
			CommerceDefaultSetting commerceDefaultSetting)
		throws PortalException {

		return getService().deleteCommerceDefaultSetting(
			commerceDefaultSetting);
	}

	/**
	 * Deletes the commerce default setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceDefaultSettingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceDefaultSettingId the primary key of the commerce default setting
	 * @return the commerce default setting that was removed
	 * @throws PortalException if a commerce default setting with the primary key could not be found
	 */
	public static CommerceDefaultSetting deleteCommerceDefaultSetting(
			long commerceDefaultSettingId)
		throws PortalException {

		return getService().deleteCommerceDefaultSetting(
			commerceDefaultSettingId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.qualifier.model.impl.CommerceDefaultSettingModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.qualifier.model.impl.CommerceDefaultSettingModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static CommerceDefaultSetting fetchCommerceDefaultSetting(
		long commerceDefaultSettingId) {

		return getService().fetchCommerceDefaultSetting(
			commerceDefaultSettingId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce default setting with the primary key.
	 *
	 * @param commerceDefaultSettingId the primary key of the commerce default setting
	 * @return the commerce default setting
	 * @throws PortalException if a commerce default setting with the primary key could not be found
	 */
	public static CommerceDefaultSetting getCommerceDefaultSetting(
			long commerceDefaultSettingId)
		throws PortalException {

		return getService().getCommerceDefaultSetting(commerceDefaultSettingId);
	}

	/**
	 * Returns a range of all the commerce default settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.qualifier.model.impl.CommerceDefaultSettingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce default settings
	 * @param end the upper bound of the range of commerce default settings (not inclusive)
	 * @return the range of commerce default settings
	 */
	public static List<CommerceDefaultSetting> getCommerceDefaultSettings(
		int start, int end) {

		return getService().getCommerceDefaultSettings(start, end);
	}

	public static List<CommerceDefaultSetting> getCommerceDefaultSettings(
		long companyId, String keywords, int start, int end) {

		return getService().getCommerceDefaultSettings(
			companyId, keywords, start, end);
	}

	/**
	 * Returns the number of commerce default settings.
	 *
	 * @return the number of commerce default settings
	 */
	public static int getCommerceDefaultSettingsCount() {
		return getService().getCommerceDefaultSettingsCount();
	}

	public static int getCommerceDefaultSettingsCount(
		long companyId, String keywords) {

		return getService().getCommerceDefaultSettingsCount(
			companyId, keywords);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the commerce default setting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceDefaultSettingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceDefaultSetting the commerce default setting
	 * @return the commerce default setting that was updated
	 */
	public static CommerceDefaultSetting updateCommerceDefaultSetting(
		CommerceDefaultSetting commerceDefaultSetting) {

		return getService().updateCommerceDefaultSetting(
			commerceDefaultSetting);
	}

	public static CommerceDefaultSetting updateCommerceDefaultSetting(
			long commerceDefaultSettingId, String name,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCommerceDefaultSetting(
			commerceDefaultSettingId, name, serviceContext);
	}

	public static CommerceDefaultSettingLocalService getService() {
		return _service;
	}

	private static volatile CommerceDefaultSettingLocalService _service;

}