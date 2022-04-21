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

package com.liferay.commerce.qualifier.service.persistence;

import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the commerce default setting service. This utility wraps <code>com.liferay.commerce.qualifier.service.persistence.impl.CommerceDefaultSettingPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommerceDefaultSettingPersistence
 * @generated
 */
public class CommerceDefaultSettingUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(
		CommerceDefaultSetting commerceDefaultSetting) {

		getPersistence().clearCache(commerceDefaultSetting);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, CommerceDefaultSetting> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommerceDefaultSetting> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceDefaultSetting> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceDefaultSetting> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceDefaultSetting> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceDefaultSetting update(
		CommerceDefaultSetting commerceDefaultSetting) {

		return getPersistence().update(commerceDefaultSetting);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceDefaultSetting update(
		CommerceDefaultSetting commerceDefaultSetting,
		ServiceContext serviceContext) {

		return getPersistence().update(commerceDefaultSetting, serviceContext);
	}

	/**
	 * Caches the commerce default setting in the entity cache if it is enabled.
	 *
	 * @param commerceDefaultSetting the commerce default setting
	 */
	public static void cacheResult(
		CommerceDefaultSetting commerceDefaultSetting) {

		getPersistence().cacheResult(commerceDefaultSetting);
	}

	/**
	 * Caches the commerce default settings in the entity cache if it is enabled.
	 *
	 * @param commerceDefaultSettings the commerce default settings
	 */
	public static void cacheResult(
		List<CommerceDefaultSetting> commerceDefaultSettings) {

		getPersistence().cacheResult(commerceDefaultSettings);
	}

	/**
	 * Creates a new commerce default setting with the primary key. Does not add the commerce default setting to the database.
	 *
	 * @param commerceDefaultSettingId the primary key for the new commerce default setting
	 * @return the new commerce default setting
	 */
	public static CommerceDefaultSetting create(long commerceDefaultSettingId) {
		return getPersistence().create(commerceDefaultSettingId);
	}

	/**
	 * Removes the commerce default setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDefaultSettingId the primary key of the commerce default setting
	 * @return the commerce default setting that was removed
	 * @throws NoSuchCommerceDefaultSettingException if a commerce default setting with the primary key could not be found
	 */
	public static CommerceDefaultSetting remove(long commerceDefaultSettingId)
		throws com.liferay.commerce.qualifier.exception.
			NoSuchCommerceDefaultSettingException {

		return getPersistence().remove(commerceDefaultSettingId);
	}

	public static CommerceDefaultSetting updateImpl(
		CommerceDefaultSetting commerceDefaultSetting) {

		return getPersistence().updateImpl(commerceDefaultSetting);
	}

	/**
	 * Returns the commerce default setting with the primary key or throws a <code>NoSuchCommerceDefaultSettingException</code> if it could not be found.
	 *
	 * @param commerceDefaultSettingId the primary key of the commerce default setting
	 * @return the commerce default setting
	 * @throws NoSuchCommerceDefaultSettingException if a commerce default setting with the primary key could not be found
	 */
	public static CommerceDefaultSetting findByPrimaryKey(
			long commerceDefaultSettingId)
		throws com.liferay.commerce.qualifier.exception.
			NoSuchCommerceDefaultSettingException {

		return getPersistence().findByPrimaryKey(commerceDefaultSettingId);
	}

	/**
	 * Returns the commerce default setting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceDefaultSettingId the primary key of the commerce default setting
	 * @return the commerce default setting, or <code>null</code> if a commerce default setting with the primary key could not be found
	 */
	public static CommerceDefaultSetting fetchByPrimaryKey(
		long commerceDefaultSettingId) {

		return getPersistence().fetchByPrimaryKey(commerceDefaultSettingId);
	}

	/**
	 * Returns all the commerce default settings.
	 *
	 * @return the commerce default settings
	 */
	public static List<CommerceDefaultSetting> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the commerce default settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce default settings
	 * @param end the upper bound of the range of commerce default settings (not inclusive)
	 * @return the range of commerce default settings
	 */
	public static List<CommerceDefaultSetting> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the commerce default settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce default settings
	 * @param end the upper bound of the range of commerce default settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce default settings
	 */
	public static List<CommerceDefaultSetting> findAll(
		int start, int end,
		OrderByComparator<CommerceDefaultSetting> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce default settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce default settings
	 * @param end the upper bound of the range of commerce default settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce default settings
	 */
	public static List<CommerceDefaultSetting> findAll(
		int start, int end,
		OrderByComparator<CommerceDefaultSetting> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commerce default settings from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of commerce default settings.
	 *
	 * @return the number of commerce default settings
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommerceDefaultSettingPersistence getPersistence() {
		return _persistence;
	}

	private static volatile CommerceDefaultSettingPersistence _persistence;

}