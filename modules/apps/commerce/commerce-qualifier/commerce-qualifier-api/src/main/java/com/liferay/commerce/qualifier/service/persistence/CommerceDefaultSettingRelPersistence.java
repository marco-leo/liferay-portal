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

import com.liferay.commerce.qualifier.exception.NoSuchCommerceDefaultSettingRelException;
import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the commerce default setting rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommerceDefaultSettingRelUtil
 * @generated
 */
@ProviderType
public interface CommerceDefaultSettingRelPersistence
	extends BasePersistence<CommerceDefaultSettingRel> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceDefaultSettingRelUtil} to access the commerce default setting rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the commerce default setting rels where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @return the matching commerce default setting rels
	 */
	public java.util.List<CommerceDefaultSettingRel>
		findByCommerceDefaultSettingId(long commerceDefaultSettingId);

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
	public java.util.List<CommerceDefaultSettingRel>
		findByCommerceDefaultSettingId(
			long commerceDefaultSettingId, int start, int end);

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
	public java.util.List<CommerceDefaultSettingRel>
		findByCommerceDefaultSettingId(
			long commerceDefaultSettingId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceDefaultSettingRel> orderByComparator);

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
	public java.util.List<CommerceDefaultSettingRel>
		findByCommerceDefaultSettingId(
			long commerceDefaultSettingId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceDefaultSettingRel> orderByComparator,
			boolean useFinderCache);

	/**
	 * Returns the first commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a matching commerce default setting rel could not be found
	 */
	public CommerceDefaultSettingRel findByCommerceDefaultSettingId_First(
			long commerceDefaultSettingId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException;

	/**
	 * Returns the first commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	public CommerceDefaultSettingRel fetchByCommerceDefaultSettingId_First(
		long commerceDefaultSettingId,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceDefaultSettingRel> orderByComparator);

	/**
	 * Returns the last commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a matching commerce default setting rel could not be found
	 */
	public CommerceDefaultSettingRel findByCommerceDefaultSettingId_Last(
			long commerceDefaultSettingId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException;

	/**
	 * Returns the last commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	public CommerceDefaultSettingRel fetchByCommerceDefaultSettingId_Last(
		long commerceDefaultSettingId,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceDefaultSettingRel> orderByComparator);

	/**
	 * Returns the commerce default setting rels before and after the current commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingRelId the primary key of the current commerce default setting rel
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a commerce default setting rel with the primary key could not be found
	 */
	public CommerceDefaultSettingRel[]
			findByCommerceDefaultSettingId_PrevAndNext(
				long commerceDefaultSettingRelId, long commerceDefaultSettingId,
				com.liferay.portal.kernel.util.OrderByComparator
					<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException;

	/**
	 * Removes all the commerce default setting rels where commerceDefaultSettingId = &#63; from the database.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 */
	public void removeByCommerceDefaultSettingId(long commerceDefaultSettingId);

	/**
	 * Returns the number of commerce default setting rels where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @return the number of matching commerce default setting rels
	 */
	public int countByCommerceDefaultSettingId(long commerceDefaultSettingId);

	/**
	 * Returns all the commerce default setting rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching commerce default setting rels
	 */
	public java.util.List<CommerceDefaultSettingRel> findByC_C(
		long classNameId, long classPK);

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
	public java.util.List<CommerceDefaultSettingRel> findByC_C(
		long classNameId, long classPK, int start, int end);

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
	public java.util.List<CommerceDefaultSettingRel> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceDefaultSettingRel> orderByComparator);

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
	public java.util.List<CommerceDefaultSettingRel> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceDefaultSettingRel> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first commerce default setting rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a matching commerce default setting rel could not be found
	 */
	public CommerceDefaultSettingRel findByC_C_First(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException;

	/**
	 * Returns the first commerce default setting rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	public CommerceDefaultSettingRel fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceDefaultSettingRel> orderByComparator);

	/**
	 * Returns the last commerce default setting rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a matching commerce default setting rel could not be found
	 */
	public CommerceDefaultSettingRel findByC_C_Last(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException;

	/**
	 * Returns the last commerce default setting rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	public CommerceDefaultSettingRel fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceDefaultSettingRel> orderByComparator);

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
	public CommerceDefaultSettingRel[] findByC_C_PrevAndNext(
			long commerceDefaultSettingRelId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException;

	/**
	 * Removes all the commerce default setting rels where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByC_C(long classNameId, long classPK);

	/**
	 * Returns the number of commerce default setting rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching commerce default setting rels
	 */
	public int countByC_C(long classNameId, long classPK);

	/**
	 * Returns all the commerce default setting rels where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @return the matching commerce default setting rels
	 */
	public java.util.List<CommerceDefaultSettingRel> findByC_T(
		long commerceDefaultSettingId, String type);

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
	public java.util.List<CommerceDefaultSettingRel> findByC_T(
		long commerceDefaultSettingId, String type, int start, int end);

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
	public java.util.List<CommerceDefaultSettingRel> findByC_T(
		long commerceDefaultSettingId, String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceDefaultSettingRel> orderByComparator);

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
	public java.util.List<CommerceDefaultSettingRel> findByC_T(
		long commerceDefaultSettingId, String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceDefaultSettingRel> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a matching commerce default setting rel could not be found
	 */
	public CommerceDefaultSettingRel findByC_T_First(
			long commerceDefaultSettingId, String type,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException;

	/**
	 * Returns the first commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	public CommerceDefaultSettingRel fetchByC_T_First(
		long commerceDefaultSettingId, String type,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceDefaultSettingRel> orderByComparator);

	/**
	 * Returns the last commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a matching commerce default setting rel could not be found
	 */
	public CommerceDefaultSettingRel findByC_T_Last(
			long commerceDefaultSettingId, String type,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException;

	/**
	 * Returns the last commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	public CommerceDefaultSettingRel fetchByC_T_Last(
		long commerceDefaultSettingId, String type,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceDefaultSettingRel> orderByComparator);

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
	public CommerceDefaultSettingRel[] findByC_T_PrevAndNext(
			long commerceDefaultSettingRelId, long commerceDefaultSettingId,
			String type,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException;

	/**
	 * Removes all the commerce default setting rels where commerceDefaultSettingId = &#63; and type = &#63; from the database.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 */
	public void removeByC_T(long commerceDefaultSettingId, String type);

	/**
	 * Returns the number of commerce default setting rels where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @return the number of matching commerce default setting rels
	 */
	public int countByC_T(long commerceDefaultSettingId, String type);

	/**
	 * Caches the commerce default setting rel in the entity cache if it is enabled.
	 *
	 * @param commerceDefaultSettingRel the commerce default setting rel
	 */
	public void cacheResult(
		CommerceDefaultSettingRel commerceDefaultSettingRel);

	/**
	 * Caches the commerce default setting rels in the entity cache if it is enabled.
	 *
	 * @param commerceDefaultSettingRels the commerce default setting rels
	 */
	public void cacheResult(
		java.util.List<CommerceDefaultSettingRel> commerceDefaultSettingRels);

	/**
	 * Creates a new commerce default setting rel with the primary key. Does not add the commerce default setting rel to the database.
	 *
	 * @param commerceDefaultSettingRelId the primary key for the new commerce default setting rel
	 * @return the new commerce default setting rel
	 */
	public CommerceDefaultSettingRel create(long commerceDefaultSettingRelId);

	/**
	 * Removes the commerce default setting rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDefaultSettingRelId the primary key of the commerce default setting rel
	 * @return the commerce default setting rel that was removed
	 * @throws NoSuchCommerceDefaultSettingRelException if a commerce default setting rel with the primary key could not be found
	 */
	public CommerceDefaultSettingRel remove(long commerceDefaultSettingRelId)
		throws NoSuchCommerceDefaultSettingRelException;

	public CommerceDefaultSettingRel updateImpl(
		CommerceDefaultSettingRel commerceDefaultSettingRel);

	/**
	 * Returns the commerce default setting rel with the primary key or throws a <code>NoSuchCommerceDefaultSettingRelException</code> if it could not be found.
	 *
	 * @param commerceDefaultSettingRelId the primary key of the commerce default setting rel
	 * @return the commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a commerce default setting rel with the primary key could not be found
	 */
	public CommerceDefaultSettingRel findByPrimaryKey(
			long commerceDefaultSettingRelId)
		throws NoSuchCommerceDefaultSettingRelException;

	/**
	 * Returns the commerce default setting rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceDefaultSettingRelId the primary key of the commerce default setting rel
	 * @return the commerce default setting rel, or <code>null</code> if a commerce default setting rel with the primary key could not be found
	 */
	public CommerceDefaultSettingRel fetchByPrimaryKey(
		long commerceDefaultSettingRelId);

	/**
	 * Returns all the commerce default setting rels.
	 *
	 * @return the commerce default setting rels
	 */
	public java.util.List<CommerceDefaultSettingRel> findAll();

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
	public java.util.List<CommerceDefaultSettingRel> findAll(
		int start, int end);

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
	public java.util.List<CommerceDefaultSettingRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceDefaultSettingRel> orderByComparator);

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
	public java.util.List<CommerceDefaultSettingRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceDefaultSettingRel> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the commerce default setting rels from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of commerce default setting rels.
	 *
	 * @return the number of commerce default setting rels
	 */
	public int countAll();

}