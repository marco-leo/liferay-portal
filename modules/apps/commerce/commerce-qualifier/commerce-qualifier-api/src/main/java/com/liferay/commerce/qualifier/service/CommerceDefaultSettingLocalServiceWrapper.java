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
 * Provides a wrapper for {@link CommerceDefaultSettingLocalService}.
 *
 * @author Riccardo Alberti
 * @see CommerceDefaultSettingLocalService
 * @generated
 */
public class CommerceDefaultSettingLocalServiceWrapper
	implements CommerceDefaultSettingLocalService,
			   ServiceWrapper<CommerceDefaultSettingLocalService> {

	public CommerceDefaultSettingLocalServiceWrapper() {
		this(null);
	}

	public CommerceDefaultSettingLocalServiceWrapper(
		CommerceDefaultSettingLocalService commerceDefaultSettingLocalService) {

		_commerceDefaultSettingLocalService =
			commerceDefaultSettingLocalService;
	}

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
	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSetting
		addCommerceDefaultSetting(
			com.liferay.commerce.qualifier.model.CommerceDefaultSetting
				commerceDefaultSetting) {

		return _commerceDefaultSettingLocalService.addCommerceDefaultSetting(
			commerceDefaultSetting);
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSetting
			addCommerceDefaultSetting(
				long userId, String name,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingLocalService.addCommerceDefaultSetting(
			userId, name, serviceContext);
	}

	/**
	 * Creates a new commerce default setting with the primary key. Does not add the commerce default setting to the database.
	 *
	 * @param commerceDefaultSettingId the primary key for the new commerce default setting
	 * @return the new commerce default setting
	 */
	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSetting
		createCommerceDefaultSetting(long commerceDefaultSettingId) {

		return _commerceDefaultSettingLocalService.createCommerceDefaultSetting(
			commerceDefaultSettingId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingLocalService.createPersistedModel(
			primaryKeyObj);
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
	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSetting
			deleteCommerceDefaultSetting(
				com.liferay.commerce.qualifier.model.CommerceDefaultSetting
					commerceDefaultSetting)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingLocalService.deleteCommerceDefaultSetting(
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
	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSetting
			deleteCommerceDefaultSetting(long commerceDefaultSettingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingLocalService.deleteCommerceDefaultSetting(
			commerceDefaultSettingId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _commerceDefaultSettingLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _commerceDefaultSettingLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceDefaultSettingLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _commerceDefaultSettingLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _commerceDefaultSettingLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _commerceDefaultSettingLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _commerceDefaultSettingLocalService.dynamicQueryCount(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _commerceDefaultSettingLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSetting
		fetchCommerceDefaultSetting(long commerceDefaultSettingId) {

		return _commerceDefaultSettingLocalService.fetchCommerceDefaultSetting(
			commerceDefaultSettingId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceDefaultSettingLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce default setting with the primary key.
	 *
	 * @param commerceDefaultSettingId the primary key of the commerce default setting
	 * @return the commerce default setting
	 * @throws PortalException if a commerce default setting with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSetting
			getCommerceDefaultSetting(long commerceDefaultSettingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingLocalService.getCommerceDefaultSetting(
			commerceDefaultSettingId);
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
	@Override
	public java.util.List
		<com.liferay.commerce.qualifier.model.CommerceDefaultSetting>
			getCommerceDefaultSettings(int start, int end) {

		return _commerceDefaultSettingLocalService.getCommerceDefaultSettings(
			start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.qualifier.model.CommerceDefaultSetting>
			getCommerceDefaultSettings(
				long companyId, String keywords, int start, int end) {

		return _commerceDefaultSettingLocalService.getCommerceDefaultSettings(
			companyId, keywords, start, end);
	}

	/**
	 * Returns the number of commerce default settings.
	 *
	 * @return the number of commerce default settings
	 */
	@Override
	public int getCommerceDefaultSettingsCount() {
		return _commerceDefaultSettingLocalService.
			getCommerceDefaultSettingsCount();
	}

	@Override
	public int getCommerceDefaultSettingsCount(
		long companyId, String keywords) {

		return _commerceDefaultSettingLocalService.
			getCommerceDefaultSettingsCount(companyId, keywords);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceDefaultSettingLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceDefaultSettingLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingLocalService.getPersistedModel(
			primaryKeyObj);
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
	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSetting
		updateCommerceDefaultSetting(
			com.liferay.commerce.qualifier.model.CommerceDefaultSetting
				commerceDefaultSetting) {

		return _commerceDefaultSettingLocalService.updateCommerceDefaultSetting(
			commerceDefaultSetting);
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSetting
			updateCommerceDefaultSetting(
				long commerceDefaultSettingId, String name,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingLocalService.updateCommerceDefaultSetting(
			commerceDefaultSettingId, name, serviceContext);
	}

	@Override
	public CommerceDefaultSettingLocalService getWrappedService() {
		return _commerceDefaultSettingLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceDefaultSettingLocalService commerceDefaultSettingLocalService) {

		_commerceDefaultSettingLocalService =
			commerceDefaultSettingLocalService;
	}

	private CommerceDefaultSettingLocalService
		_commerceDefaultSettingLocalService;

}