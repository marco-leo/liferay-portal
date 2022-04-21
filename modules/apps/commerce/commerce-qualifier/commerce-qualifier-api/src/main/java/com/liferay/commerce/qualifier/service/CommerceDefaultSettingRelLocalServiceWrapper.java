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
 * Provides a wrapper for {@link CommerceDefaultSettingRelLocalService}.
 *
 * @author Riccardo Alberti
 * @see CommerceDefaultSettingRelLocalService
 * @generated
 */
public class CommerceDefaultSettingRelLocalServiceWrapper
	implements CommerceDefaultSettingRelLocalService,
			   ServiceWrapper<CommerceDefaultSettingRelLocalService> {

	public CommerceDefaultSettingRelLocalServiceWrapper() {
		this(null);
	}

	public CommerceDefaultSettingRelLocalServiceWrapper(
		CommerceDefaultSettingRelLocalService
			commerceDefaultSettingRelLocalService) {

		_commerceDefaultSettingRelLocalService =
			commerceDefaultSettingRelLocalService;
	}

	/**
	 * Adds the commerce default setting rel to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceDefaultSettingRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceDefaultSettingRel the commerce default setting rel
	 * @return the commerce default setting rel that was added
	 */
	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
		addCommerceDefaultSettingRel(
			com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
				commerceDefaultSettingRel) {

		return _commerceDefaultSettingRelLocalService.
			addCommerceDefaultSettingRel(commerceDefaultSettingRel);
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
			addCommerceDefaultSettingRel(
				long userId, long commerceDefaultSettingId, String className,
				long classPK, double priority, String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingRelLocalService.
			addCommerceDefaultSettingRel(
				userId, commerceDefaultSettingId, className, classPK, priority,
				type);
	}

	/**
	 * Creates a new commerce default setting rel with the primary key. Does not add the commerce default setting rel to the database.
	 *
	 * @param commerceDefaultSettingRelId the primary key for the new commerce default setting rel
	 * @return the new commerce default setting rel
	 */
	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
		createCommerceDefaultSettingRel(long commerceDefaultSettingRelId) {

		return _commerceDefaultSettingRelLocalService.
			createCommerceDefaultSettingRel(commerceDefaultSettingRelId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingRelLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the commerce default setting rel from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceDefaultSettingRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceDefaultSettingRel the commerce default setting rel
	 * @return the commerce default setting rel that was removed
	 */
	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
		deleteCommerceDefaultSettingRel(
			com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
				commerceDefaultSettingRel) {

		return _commerceDefaultSettingRelLocalService.
			deleteCommerceDefaultSettingRel(commerceDefaultSettingRel);
	}

	/**
	 * Deletes the commerce default setting rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceDefaultSettingRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceDefaultSettingRelId the primary key of the commerce default setting rel
	 * @return the commerce default setting rel that was removed
	 * @throws PortalException if a commerce default setting rel with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
			deleteCommerceDefaultSettingRel(long commerceDefaultSettingRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingRelLocalService.
			deleteCommerceDefaultSettingRel(commerceDefaultSettingRelId);
	}

	@Override
	public void deleteCommerceDefaultSettingRels(long commerceDefaultSettingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceDefaultSettingRelLocalService.deleteCommerceDefaultSettingRels(
			commerceDefaultSettingId);
	}

	@Override
	public void deleteCommerceDefaultSettingRels(String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceDefaultSettingRelLocalService.deleteCommerceDefaultSettingRels(
			className, classPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingRelLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _commerceDefaultSettingRelLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _commerceDefaultSettingRelLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceDefaultSettingRelLocalService.dynamicQuery();
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

		return _commerceDefaultSettingRelLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.qualifier.model.impl.CommerceDefaultSettingRelModelImpl</code>.
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

		return _commerceDefaultSettingRelLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.qualifier.model.impl.CommerceDefaultSettingRelModelImpl</code>.
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

		return _commerceDefaultSettingRelLocalService.dynamicQuery(
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

		return _commerceDefaultSettingRelLocalService.dynamicQueryCount(
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

		return _commerceDefaultSettingRelLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
		fetchCommerceDefaultSettingRel(long commerceDefaultSettingRelId) {

		return _commerceDefaultSettingRelLocalService.
			fetchCommerceDefaultSettingRel(commerceDefaultSettingRelId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceDefaultSettingRelLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce default setting rel with the primary key.
	 *
	 * @param commerceDefaultSettingRelId the primary key of the commerce default setting rel
	 * @return the commerce default setting rel
	 * @throws PortalException if a commerce default setting rel with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
			getCommerceDefaultSettingRel(long commerceDefaultSettingRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingRelLocalService.
			getCommerceDefaultSettingRel(commerceDefaultSettingRelId);
	}

	/**
	 * Returns a range of all the commerce default setting rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.qualifier.model.impl.CommerceDefaultSettingRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce default setting rels
	 * @param end the upper bound of the range of commerce default setting rels (not inclusive)
	 * @return the range of commerce default setting rels
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel>
			getCommerceDefaultSettingRels(int start, int end) {

		return _commerceDefaultSettingRelLocalService.
			getCommerceDefaultSettingRels(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel>
				getCommerceDefaultSettingRels(
					long commerceDefaultSettingId, String type)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingRelLocalService.
			getCommerceDefaultSettingRels(commerceDefaultSettingId, type);
	}

	/**
	 * Returns the number of commerce default setting rels.
	 *
	 * @return the number of commerce default setting rels
	 */
	@Override
	public int getCommerceDefaultSettingRelsCount() {
		return _commerceDefaultSettingRelLocalService.
			getCommerceDefaultSettingRelsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceDefaultSettingRelLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceDefaultSettingRelLocalService.
			getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingRelLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the commerce default setting rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceDefaultSettingRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceDefaultSettingRel the commerce default setting rel
	 * @return the commerce default setting rel that was updated
	 */
	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
		updateCommerceDefaultSettingRel(
			com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
				commerceDefaultSettingRel) {

		return _commerceDefaultSettingRelLocalService.
			updateCommerceDefaultSettingRel(commerceDefaultSettingRel);
	}

	@Override
	public com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel
			updateCommerceDefaultSettingRel(
				long commerceDefaultSettingRelId, double priority)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDefaultSettingRelLocalService.
			updateCommerceDefaultSettingRel(
				commerceDefaultSettingRelId, priority);
	}

	@Override
	public CommerceDefaultSettingRelLocalService getWrappedService() {
		return _commerceDefaultSettingRelLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceDefaultSettingRelLocalService
			commerceDefaultSettingRelLocalService) {

		_commerceDefaultSettingRelLocalService =
			commerceDefaultSettingRelLocalService;
	}

	private CommerceDefaultSettingRelLocalService
		_commerceDefaultSettingRelLocalService;

}