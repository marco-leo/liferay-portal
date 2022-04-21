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

import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the commerce default setting rel service. This utility wraps <code>com.liferay.commerce.qualifier.service.persistence.impl.CommerceDefaultSettingRelPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommerceDefaultSettingRelPersistence
 * @generated
 */
public class CommerceDefaultSettingRelUtil {

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
		CommerceDefaultSettingRel commerceDefaultSettingRel) {

		getPersistence().clearCache(commerceDefaultSettingRel);
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
	public static Map<Serializable, CommerceDefaultSettingRel>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommerceDefaultSettingRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceDefaultSettingRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceDefaultSettingRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceDefaultSettingRel update(
		CommerceDefaultSettingRel commerceDefaultSettingRel) {

		return getPersistence().update(commerceDefaultSettingRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceDefaultSettingRel update(
		CommerceDefaultSettingRel commerceDefaultSettingRel,
		ServiceContext serviceContext) {

		return getPersistence().update(
			commerceDefaultSettingRel, serviceContext);
	}

	/**
	 * Returns all the commerce default setting rels where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @return the matching commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel>
		findByCommerceDefaultSettingId(long commerceDefaultSettingId) {

		return getPersistence().findByCommerceDefaultSettingId(
			commerceDefaultSettingId);
	}

	/**
	 * Returns a range of all the commerce default setting rels where commerceDefaultSettingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingRelModelImpl</code>.
	 * </p>
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param start the lower bound of the range of commerce default setting rels
	 * @param end the upper bound of the range of commerce default setting rels (not inclusive)
	 * @return the range of matching commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel>
		findByCommerceDefaultSettingId(
			long commerceDefaultSettingId, int start, int end) {

		return getPersistence().findByCommerceDefaultSettingId(
			commerceDefaultSettingId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce default setting rels where commerceDefaultSettingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingRelModelImpl</code>.
	 * </p>
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param start the lower bound of the range of commerce default setting rels
	 * @param end the upper bound of the range of commerce default setting rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel>
		findByCommerceDefaultSettingId(
			long commerceDefaultSettingId, int start, int end,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return getPersistence().findByCommerceDefaultSettingId(
			commerceDefaultSettingId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce default setting rels where commerceDefaultSettingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingRelModelImpl</code>.
	 * </p>
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param start the lower bound of the range of commerce default setting rels
	 * @param end the upper bound of the range of commerce default setting rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel>
		findByCommerceDefaultSettingId(
			long commerceDefaultSettingId, int start, int end,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator,
			boolean useFinderCache) {

		return getPersistence().findByCommerceDefaultSettingId(
			commerceDefaultSettingId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a matching commerce default setting rel could not be found
	 */
	public static CommerceDefaultSettingRel
			findByCommerceDefaultSettingId_First(
				long commerceDefaultSettingId,
				OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws com.liferay.commerce.qualifier.exception.
			NoSuchCommerceDefaultSettingRelException {

		return getPersistence().findByCommerceDefaultSettingId_First(
			commerceDefaultSettingId, orderByComparator);
	}

	/**
	 * Returns the first commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	public static CommerceDefaultSettingRel
		fetchByCommerceDefaultSettingId_First(
			long commerceDefaultSettingId,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return getPersistence().fetchByCommerceDefaultSettingId_First(
			commerceDefaultSettingId, orderByComparator);
	}

	/**
	 * Returns the last commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a matching commerce default setting rel could not be found
	 */
	public static CommerceDefaultSettingRel findByCommerceDefaultSettingId_Last(
			long commerceDefaultSettingId,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws com.liferay.commerce.qualifier.exception.
			NoSuchCommerceDefaultSettingRelException {

		return getPersistence().findByCommerceDefaultSettingId_Last(
			commerceDefaultSettingId, orderByComparator);
	}

	/**
	 * Returns the last commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	public static CommerceDefaultSettingRel
		fetchByCommerceDefaultSettingId_Last(
			long commerceDefaultSettingId,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return getPersistence().fetchByCommerceDefaultSettingId_Last(
			commerceDefaultSettingId, orderByComparator);
	}

	/**
	 * Returns the commerce default setting rels before and after the current commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingRelId the primary key of the current commerce default setting rel
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a commerce default setting rel with the primary key could not be found
	 */
	public static CommerceDefaultSettingRel[]
			findByCommerceDefaultSettingId_PrevAndNext(
				long commerceDefaultSettingRelId, long commerceDefaultSettingId,
				OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws com.liferay.commerce.qualifier.exception.
			NoSuchCommerceDefaultSettingRelException {

		return getPersistence().findByCommerceDefaultSettingId_PrevAndNext(
			commerceDefaultSettingRelId, commerceDefaultSettingId,
			orderByComparator);
	}

	/**
	 * Removes all the commerce default setting rels where commerceDefaultSettingId = &#63; from the database.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 */
	public static void removeByCommerceDefaultSettingId(
		long commerceDefaultSettingId) {

		getPersistence().removeByCommerceDefaultSettingId(
			commerceDefaultSettingId);
	}

	/**
	 * Returns the number of commerce default setting rels where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @return the number of matching commerce default setting rels
	 */
	public static int countByCommerceDefaultSettingId(
		long commerceDefaultSettingId) {

		return getPersistence().countByCommerceDefaultSettingId(
			commerceDefaultSettingId);
	}

	/**
	 * Returns all the commerce default setting rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel> findByC_C(
		long classNameId, long classPK) {

		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	 * Returns a range of all the commerce default setting rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingRelModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of commerce default setting rels
	 * @param end the upper bound of the range of commerce default setting rels (not inclusive)
	 * @return the range of matching commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce default setting rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingRelModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of commerce default setting rels
	 * @param end the upper bound of the range of commerce default setting rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce default setting rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingRelModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of commerce default setting rels
	 * @param end the upper bound of the range of commerce default setting rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first commerce default setting rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a matching commerce default setting rel could not be found
	 */
	public static CommerceDefaultSettingRel findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws com.liferay.commerce.qualifier.exception.
			NoSuchCommerceDefaultSettingRelException {

		return getPersistence().findByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first commerce default setting rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	public static CommerceDefaultSettingRel fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return getPersistence().fetchByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last commerce default setting rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a matching commerce default setting rel could not be found
	 */
	public static CommerceDefaultSettingRel findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws com.liferay.commerce.qualifier.exception.
			NoSuchCommerceDefaultSettingRelException {

		return getPersistence().findByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last commerce default setting rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	public static CommerceDefaultSettingRel fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return getPersistence().fetchByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the commerce default setting rels before and after the current commerce default setting rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param commerceDefaultSettingRelId the primary key of the current commerce default setting rel
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a commerce default setting rel with the primary key could not be found
	 */
	public static CommerceDefaultSettingRel[] findByC_C_PrevAndNext(
			long commerceDefaultSettingRelId, long classNameId, long classPK,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws com.liferay.commerce.qualifier.exception.
			NoSuchCommerceDefaultSettingRelException {

		return getPersistence().findByC_C_PrevAndNext(
			commerceDefaultSettingRelId, classNameId, classPK,
			orderByComparator);
	}

	/**
	 * Removes all the commerce default setting rels where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	 * Returns the number of commerce default setting rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching commerce default setting rels
	 */
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	 * Returns all the commerce default setting rels where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @return the matching commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel> findByC_T(
		long commerceDefaultSettingId, String type) {

		return getPersistence().findByC_T(commerceDefaultSettingId, type);
	}

	/**
	 * Returns a range of all the commerce default setting rels where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingRelModelImpl</code>.
	 * </p>
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @param start the lower bound of the range of commerce default setting rels
	 * @param end the upper bound of the range of commerce default setting rels (not inclusive)
	 * @return the range of matching commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel> findByC_T(
		long commerceDefaultSettingId, String type, int start, int end) {

		return getPersistence().findByC_T(
			commerceDefaultSettingId, type, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce default setting rels where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingRelModelImpl</code>.
	 * </p>
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @param start the lower bound of the range of commerce default setting rels
	 * @param end the upper bound of the range of commerce default setting rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel> findByC_T(
		long commerceDefaultSettingId, String type, int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return getPersistence().findByC_T(
			commerceDefaultSettingId, type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce default setting rels where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingRelModelImpl</code>.
	 * </p>
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @param start the lower bound of the range of commerce default setting rels
	 * @param end the upper bound of the range of commerce default setting rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel> findByC_T(
		long commerceDefaultSettingId, String type, int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_T(
			commerceDefaultSettingId, type, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a matching commerce default setting rel could not be found
	 */
	public static CommerceDefaultSettingRel findByC_T_First(
			long commerceDefaultSettingId, String type,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws com.liferay.commerce.qualifier.exception.
			NoSuchCommerceDefaultSettingRelException {

		return getPersistence().findByC_T_First(
			commerceDefaultSettingId, type, orderByComparator);
	}

	/**
	 * Returns the first commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	public static CommerceDefaultSettingRel fetchByC_T_First(
		long commerceDefaultSettingId, String type,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return getPersistence().fetchByC_T_First(
			commerceDefaultSettingId, type, orderByComparator);
	}

	/**
	 * Returns the last commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a matching commerce default setting rel could not be found
	 */
	public static CommerceDefaultSettingRel findByC_T_Last(
			long commerceDefaultSettingId, String type,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws com.liferay.commerce.qualifier.exception.
			NoSuchCommerceDefaultSettingRelException {

		return getPersistence().findByC_T_Last(
			commerceDefaultSettingId, type, orderByComparator);
	}

	/**
	 * Returns the last commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	public static CommerceDefaultSettingRel fetchByC_T_Last(
		long commerceDefaultSettingId, String type,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return getPersistence().fetchByC_T_Last(
			commerceDefaultSettingId, type, orderByComparator);
	}

	/**
	 * Returns the commerce default setting rels before and after the current commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingRelId the primary key of the current commerce default setting rel
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a commerce default setting rel with the primary key could not be found
	 */
	public static CommerceDefaultSettingRel[] findByC_T_PrevAndNext(
			long commerceDefaultSettingRelId, long commerceDefaultSettingId,
			String type,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws com.liferay.commerce.qualifier.exception.
			NoSuchCommerceDefaultSettingRelException {

		return getPersistence().findByC_T_PrevAndNext(
			commerceDefaultSettingRelId, commerceDefaultSettingId, type,
			orderByComparator);
	}

	/**
	 * Removes all the commerce default setting rels where commerceDefaultSettingId = &#63; and type = &#63; from the database.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 */
	public static void removeByC_T(long commerceDefaultSettingId, String type) {
		getPersistence().removeByC_T(commerceDefaultSettingId, type);
	}

	/**
	 * Returns the number of commerce default setting rels where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @return the number of matching commerce default setting rels
	 */
	public static int countByC_T(long commerceDefaultSettingId, String type) {
		return getPersistence().countByC_T(commerceDefaultSettingId, type);
	}

	/**
	 * Caches the commerce default setting rel in the entity cache if it is enabled.
	 *
	 * @param commerceDefaultSettingRel the commerce default setting rel
	 */
	public static void cacheResult(
		CommerceDefaultSettingRel commerceDefaultSettingRel) {

		getPersistence().cacheResult(commerceDefaultSettingRel);
	}

	/**
	 * Caches the commerce default setting rels in the entity cache if it is enabled.
	 *
	 * @param commerceDefaultSettingRels the commerce default setting rels
	 */
	public static void cacheResult(
		List<CommerceDefaultSettingRel> commerceDefaultSettingRels) {

		getPersistence().cacheResult(commerceDefaultSettingRels);
	}

	/**
	 * Creates a new commerce default setting rel with the primary key. Does not add the commerce default setting rel to the database.
	 *
	 * @param commerceDefaultSettingRelId the primary key for the new commerce default setting rel
	 * @return the new commerce default setting rel
	 */
	public static CommerceDefaultSettingRel create(
		long commerceDefaultSettingRelId) {

		return getPersistence().create(commerceDefaultSettingRelId);
	}

	/**
	 * Removes the commerce default setting rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDefaultSettingRelId the primary key of the commerce default setting rel
	 * @return the commerce default setting rel that was removed
	 * @throws NoSuchCommerceDefaultSettingRelException if a commerce default setting rel with the primary key could not be found
	 */
	public static CommerceDefaultSettingRel remove(
			long commerceDefaultSettingRelId)
		throws com.liferay.commerce.qualifier.exception.
			NoSuchCommerceDefaultSettingRelException {

		return getPersistence().remove(commerceDefaultSettingRelId);
	}

	public static CommerceDefaultSettingRel updateImpl(
		CommerceDefaultSettingRel commerceDefaultSettingRel) {

		return getPersistence().updateImpl(commerceDefaultSettingRel);
	}

	/**
	 * Returns the commerce default setting rel with the primary key or throws a <code>NoSuchCommerceDefaultSettingRelException</code> if it could not be found.
	 *
	 * @param commerceDefaultSettingRelId the primary key of the commerce default setting rel
	 * @return the commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a commerce default setting rel with the primary key could not be found
	 */
	public static CommerceDefaultSettingRel findByPrimaryKey(
			long commerceDefaultSettingRelId)
		throws com.liferay.commerce.qualifier.exception.
			NoSuchCommerceDefaultSettingRelException {

		return getPersistence().findByPrimaryKey(commerceDefaultSettingRelId);
	}

	/**
	 * Returns the commerce default setting rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceDefaultSettingRelId the primary key of the commerce default setting rel
	 * @return the commerce default setting rel, or <code>null</code> if a commerce default setting rel with the primary key could not be found
	 */
	public static CommerceDefaultSettingRel fetchByPrimaryKey(
		long commerceDefaultSettingRelId) {

		return getPersistence().fetchByPrimaryKey(commerceDefaultSettingRelId);
	}

	/**
	 * Returns all the commerce default setting rels.
	 *
	 * @return the commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the commerce default setting rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce default setting rels
	 * @param end the upper bound of the range of commerce default setting rels (not inclusive)
	 * @return the range of commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the commerce default setting rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce default setting rels
	 * @param end the upper bound of the range of commerce default setting rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel> findAll(
		int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce default setting rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDefaultSettingRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce default setting rels
	 * @param end the upper bound of the range of commerce default setting rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce default setting rels
	 */
	public static List<CommerceDefaultSettingRel> findAll(
		int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commerce default setting rels from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of commerce default setting rels.
	 *
	 * @return the number of commerce default setting rels
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommerceDefaultSettingRelPersistence getPersistence() {
		return _persistence;
	}

	private static volatile CommerceDefaultSettingRelPersistence _persistence;

}