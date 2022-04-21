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

import com.liferay.commerce.qualifier.exception.NoSuchCommerceDefaultSettingException;
import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the commerce default setting service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommerceDefaultSettingUtil
 * @generated
 */
@ProviderType
public interface CommerceDefaultSettingPersistence
	extends BasePersistence<CommerceDefaultSetting> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceDefaultSettingUtil} to access the commerce default setting persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the commerce default setting in the entity cache if it is enabled.
	 *
	 * @param commerceDefaultSetting the commerce default setting
	 */
	public void cacheResult(CommerceDefaultSetting commerceDefaultSetting);

	/**
	 * Caches the commerce default settings in the entity cache if it is enabled.
	 *
	 * @param commerceDefaultSettings the commerce default settings
	 */
	public void cacheResult(
		java.util.List<CommerceDefaultSetting> commerceDefaultSettings);

	/**
	 * Creates a new commerce default setting with the primary key. Does not add the commerce default setting to the database.
	 *
	 * @param commerceDefaultSettingId the primary key for the new commerce default setting
	 * @return the new commerce default setting
	 */
	public CommerceDefaultSetting create(long commerceDefaultSettingId);

	/**
	 * Removes the commerce default setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDefaultSettingId the primary key of the commerce default setting
	 * @return the commerce default setting that was removed
	 * @throws NoSuchCommerceDefaultSettingException if a commerce default setting with the primary key could not be found
	 */
	public CommerceDefaultSetting remove(long commerceDefaultSettingId)
		throws NoSuchCommerceDefaultSettingException;

	public CommerceDefaultSetting updateImpl(
		CommerceDefaultSetting commerceDefaultSetting);

	/**
	 * Returns the commerce default setting with the primary key or throws a <code>NoSuchCommerceDefaultSettingException</code> if it could not be found.
	 *
	 * @param commerceDefaultSettingId the primary key of the commerce default setting
	 * @return the commerce default setting
	 * @throws NoSuchCommerceDefaultSettingException if a commerce default setting with the primary key could not be found
	 */
	public CommerceDefaultSetting findByPrimaryKey(
			long commerceDefaultSettingId)
		throws NoSuchCommerceDefaultSettingException;

	/**
	 * Returns the commerce default setting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceDefaultSettingId the primary key of the commerce default setting
	 * @return the commerce default setting, or <code>null</code> if a commerce default setting with the primary key could not be found
	 */
	public CommerceDefaultSetting fetchByPrimaryKey(
		long commerceDefaultSettingId);

	/**
	 * Returns all the commerce default settings.
	 *
	 * @return the commerce default settings
	 */
	public java.util.List<CommerceDefaultSetting> findAll();

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
	public java.util.List<CommerceDefaultSetting> findAll(int start, int end);

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
	public java.util.List<CommerceDefaultSetting> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceDefaultSetting>
			orderByComparator);

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
	public java.util.List<CommerceDefaultSetting> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceDefaultSetting>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the commerce default settings from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of commerce default settings.
	 *
	 * @return the number of commerce default settings
	 */
	public int countAll();

}