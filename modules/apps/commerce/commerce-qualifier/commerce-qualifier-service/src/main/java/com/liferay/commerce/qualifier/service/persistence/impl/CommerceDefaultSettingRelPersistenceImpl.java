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

package com.liferay.commerce.qualifier.service.persistence.impl;

import com.liferay.commerce.qualifier.exception.NoSuchCommerceDefaultSettingRelException;
import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel;
import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRelTable;
import com.liferay.commerce.qualifier.model.impl.CommerceDefaultSettingRelImpl;
import com.liferay.commerce.qualifier.model.impl.CommerceDefaultSettingRelModelImpl;
import com.liferay.commerce.qualifier.service.persistence.CommerceDefaultSettingRelPersistence;
import com.liferay.commerce.qualifier.service.persistence.CommerceDefaultSettingRelUtil;
import com.liferay.commerce.qualifier.service.persistence.impl.constants.CommercePersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the commerce default setting rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @generated
 */
@Component(
	service = {
		CommerceDefaultSettingRelPersistence.class, BasePersistence.class
	}
)
public class CommerceDefaultSettingRelPersistenceImpl
	extends BasePersistenceImpl<CommerceDefaultSettingRel>
	implements CommerceDefaultSettingRelPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceDefaultSettingRelUtil</code> to access the commerce default setting rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceDefaultSettingRelImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCommerceDefaultSettingId;
	private FinderPath
		_finderPathWithoutPaginationFindByCommerceDefaultSettingId;
	private FinderPath _finderPathCountByCommerceDefaultSettingId;

	/**
	 * Returns all the commerce default setting rels where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @return the matching commerce default setting rels
	 */
	@Override
	public List<CommerceDefaultSettingRel> findByCommerceDefaultSettingId(
		long commerceDefaultSettingId) {

		return findByCommerceDefaultSettingId(
			commerceDefaultSettingId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<CommerceDefaultSettingRel> findByCommerceDefaultSettingId(
		long commerceDefaultSettingId, int start, int end) {

		return findByCommerceDefaultSettingId(
			commerceDefaultSettingId, start, end, null);
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
	@Override
	public List<CommerceDefaultSettingRel> findByCommerceDefaultSettingId(
		long commerceDefaultSettingId, int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return findByCommerceDefaultSettingId(
			commerceDefaultSettingId, start, end, orderByComparator, true);
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
	@Override
	public List<CommerceDefaultSettingRel> findByCommerceDefaultSettingId(
		long commerceDefaultSettingId, int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByCommerceDefaultSettingId;
				finderArgs = new Object[] {commerceDefaultSettingId};
			}
		}
		else if (useFinderCache) {
			finderPath =
				_finderPathWithPaginationFindByCommerceDefaultSettingId;
			finderArgs = new Object[] {
				commerceDefaultSettingId, start, end, orderByComparator
			};
		}

		List<CommerceDefaultSettingRel> list = null;

		if (useFinderCache) {
			list = (List<CommerceDefaultSettingRel>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDefaultSettingRel commerceDefaultSettingRel :
						list) {

					if (commerceDefaultSettingId !=
							commerceDefaultSettingRel.
								getCommerceDefaultSettingId()) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_COMMERCEDEFAULTSETTINGREL_WHERE);

			sb.append(
				_FINDER_COLUMN_COMMERCEDEFAULTSETTINGID_COMMERCEDEFAULTSETTINGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommerceDefaultSettingRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(commerceDefaultSettingId);

				list = (List<CommerceDefaultSettingRel>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a matching commerce default setting rel could not be found
	 */
	@Override
	public CommerceDefaultSettingRel findByCommerceDefaultSettingId_First(
			long commerceDefaultSettingId,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException {

		CommerceDefaultSettingRel commerceDefaultSettingRel =
			fetchByCommerceDefaultSettingId_First(
				commerceDefaultSettingId, orderByComparator);

		if (commerceDefaultSettingRel != null) {
			return commerceDefaultSettingRel;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("commerceDefaultSettingId=");
		sb.append(commerceDefaultSettingId);

		sb.append("}");

		throw new NoSuchCommerceDefaultSettingRelException(sb.toString());
	}

	/**
	 * Returns the first commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	@Override
	public CommerceDefaultSettingRel fetchByCommerceDefaultSettingId_First(
		long commerceDefaultSettingId,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		List<CommerceDefaultSettingRel> list = findByCommerceDefaultSettingId(
			commerceDefaultSettingId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a matching commerce default setting rel could not be found
	 */
	@Override
	public CommerceDefaultSettingRel findByCommerceDefaultSettingId_Last(
			long commerceDefaultSettingId,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException {

		CommerceDefaultSettingRel commerceDefaultSettingRel =
			fetchByCommerceDefaultSettingId_Last(
				commerceDefaultSettingId, orderByComparator);

		if (commerceDefaultSettingRel != null) {
			return commerceDefaultSettingRel;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("commerceDefaultSettingId=");
		sb.append(commerceDefaultSettingId);

		sb.append("}");

		throw new NoSuchCommerceDefaultSettingRelException(sb.toString());
	}

	/**
	 * Returns the last commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	@Override
	public CommerceDefaultSettingRel fetchByCommerceDefaultSettingId_Last(
		long commerceDefaultSettingId,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		int count = countByCommerceDefaultSettingId(commerceDefaultSettingId);

		if (count == 0) {
			return null;
		}

		List<CommerceDefaultSettingRel> list = findByCommerceDefaultSettingId(
			commerceDefaultSettingId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CommerceDefaultSettingRel[]
			findByCommerceDefaultSettingId_PrevAndNext(
				long commerceDefaultSettingRelId, long commerceDefaultSettingId,
				OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException {

		CommerceDefaultSettingRel commerceDefaultSettingRel = findByPrimaryKey(
			commerceDefaultSettingRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceDefaultSettingRel[] array =
				new CommerceDefaultSettingRelImpl[3];

			array[0] = getByCommerceDefaultSettingId_PrevAndNext(
				session, commerceDefaultSettingRel, commerceDefaultSettingId,
				orderByComparator, true);

			array[1] = commerceDefaultSettingRel;

			array[2] = getByCommerceDefaultSettingId_PrevAndNext(
				session, commerceDefaultSettingRel, commerceDefaultSettingId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDefaultSettingRel
		getByCommerceDefaultSettingId_PrevAndNext(
			Session session,
			CommerceDefaultSettingRel commerceDefaultSettingRel,
			long commerceDefaultSettingId,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator,
			boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_COMMERCEDEFAULTSETTINGREL_WHERE);

		sb.append(
			_FINDER_COLUMN_COMMERCEDEFAULTSETTINGID_COMMERCEDEFAULTSETTINGID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CommerceDefaultSettingRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(commerceDefaultSettingId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDefaultSettingRel)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceDefaultSettingRel> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce default setting rels where commerceDefaultSettingId = &#63; from the database.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 */
	@Override
	public void removeByCommerceDefaultSettingId(
		long commerceDefaultSettingId) {

		for (CommerceDefaultSettingRel commerceDefaultSettingRel :
				findByCommerceDefaultSettingId(
					commerceDefaultSettingId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceDefaultSettingRel);
		}
	}

	/**
	 * Returns the number of commerce default setting rels where commerceDefaultSettingId = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @return the number of matching commerce default setting rels
	 */
	@Override
	public int countByCommerceDefaultSettingId(long commerceDefaultSettingId) {
		FinderPath finderPath = _finderPathCountByCommerceDefaultSettingId;

		Object[] finderArgs = new Object[] {commerceDefaultSettingId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMERCEDEFAULTSETTINGREL_WHERE);

			sb.append(
				_FINDER_COLUMN_COMMERCEDEFAULTSETTINGID_COMMERCEDEFAULTSETTINGID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(commerceDefaultSettingId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_COMMERCEDEFAULTSETTINGID_COMMERCEDEFAULTSETTINGID_2 =
			"commerceDefaultSettingRel.commerceDefaultSettingId = ?";

	private FinderPath _finderPathWithPaginationFindByC_C;
	private FinderPath _finderPathWithoutPaginationFindByC_C;
	private FinderPath _finderPathCountByC_C;

	/**
	 * Returns all the commerce default setting rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching commerce default setting rels
	 */
	@Override
	public List<CommerceDefaultSettingRel> findByC_C(
		long classNameId, long classPK) {

		return findByC_C(
			classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<CommerceDefaultSettingRel> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return findByC_C(classNameId, classPK, start, end, null);
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
	@Override
	public List<CommerceDefaultSettingRel> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return findByC_C(
			classNameId, classPK, start, end, orderByComparator, true);
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
	@Override
	public List<CommerceDefaultSettingRel> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_C;
				finderArgs = new Object[] {classNameId, classPK};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_C;
			finderArgs = new Object[] {
				classNameId, classPK, start, end, orderByComparator
			};
		}

		List<CommerceDefaultSettingRel> list = null;

		if (useFinderCache) {
			list = (List<CommerceDefaultSettingRel>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDefaultSettingRel commerceDefaultSettingRel :
						list) {

					if ((classNameId !=
							commerceDefaultSettingRel.getClassNameId()) ||
						(classPK != commerceDefaultSettingRel.getClassPK())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_COMMERCEDEFAULTSETTINGREL_WHERE);

			sb.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommerceDefaultSettingRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				queryPos.add(classPK);

				list = (List<CommerceDefaultSettingRel>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public CommerceDefaultSettingRel findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException {

		CommerceDefaultSettingRel commerceDefaultSettingRel = fetchByC_C_First(
			classNameId, classPK, orderByComparator);

		if (commerceDefaultSettingRel != null) {
			return commerceDefaultSettingRel;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchCommerceDefaultSettingRelException(sb.toString());
	}

	/**
	 * Returns the first commerce default setting rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	@Override
	public CommerceDefaultSettingRel fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		List<CommerceDefaultSettingRel> list = findByC_C(
			classNameId, classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CommerceDefaultSettingRel findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException {

		CommerceDefaultSettingRel commerceDefaultSettingRel = fetchByC_C_Last(
			classNameId, classPK, orderByComparator);

		if (commerceDefaultSettingRel != null) {
			return commerceDefaultSettingRel;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchCommerceDefaultSettingRelException(sb.toString());
	}

	/**
	 * Returns the last commerce default setting rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	@Override
	public CommerceDefaultSettingRel fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<CommerceDefaultSettingRel> list = findByC_C(
			classNameId, classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CommerceDefaultSettingRel[] findByC_C_PrevAndNext(
			long commerceDefaultSettingRelId, long classNameId, long classPK,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException {

		CommerceDefaultSettingRel commerceDefaultSettingRel = findByPrimaryKey(
			commerceDefaultSettingRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceDefaultSettingRel[] array =
				new CommerceDefaultSettingRelImpl[3];

			array[0] = getByC_C_PrevAndNext(
				session, commerceDefaultSettingRel, classNameId, classPK,
				orderByComparator, true);

			array[1] = commerceDefaultSettingRel;

			array[2] = getByC_C_PrevAndNext(
				session, commerceDefaultSettingRel, classNameId, classPK,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDefaultSettingRel getByC_C_PrevAndNext(
		Session session, CommerceDefaultSettingRel commerceDefaultSettingRel,
		long classNameId, long classPK,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_COMMERCEDEFAULTSETTINGREL_WHERE);

		sb.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_C_C_CLASSPK_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CommerceDefaultSettingRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(classNameId);

		queryPos.add(classPK);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDefaultSettingRel)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceDefaultSettingRel> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce default setting rels where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (CommerceDefaultSettingRel commerceDefaultSettingRel :
				findByC_C(
					classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceDefaultSettingRel);
		}
	}

	/**
	 * Returns the number of commerce default setting rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching commerce default setting rels
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = _finderPathCountByC_C;

		Object[] finderArgs = new Object[] {classNameId, classPK};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_COMMERCEDEFAULTSETTINGREL_WHERE);

			sb.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				queryPos.add(classPK);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 =
		"commerceDefaultSettingRel.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 =
		"commerceDefaultSettingRel.classPK = ?";

	private FinderPath _finderPathWithPaginationFindByC_T;
	private FinderPath _finderPathWithoutPaginationFindByC_T;
	private FinderPath _finderPathCountByC_T;

	/**
	 * Returns all the commerce default setting rels where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @return the matching commerce default setting rels
	 */
	@Override
	public List<CommerceDefaultSettingRel> findByC_T(
		long commerceDefaultSettingId, String type) {

		return findByC_T(
			commerceDefaultSettingId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<CommerceDefaultSettingRel> findByC_T(
		long commerceDefaultSettingId, String type, int start, int end) {

		return findByC_T(commerceDefaultSettingId, type, start, end, null);
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
	@Override
	public List<CommerceDefaultSettingRel> findByC_T(
		long commerceDefaultSettingId, String type, int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return findByC_T(
			commerceDefaultSettingId, type, start, end, orderByComparator,
			true);
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
	@Override
	public List<CommerceDefaultSettingRel> findByC_T(
		long commerceDefaultSettingId, String type, int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator,
		boolean useFinderCache) {

		type = Objects.toString(type, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_T;
				finderArgs = new Object[] {commerceDefaultSettingId, type};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_T;
			finderArgs = new Object[] {
				commerceDefaultSettingId, type, start, end, orderByComparator
			};
		}

		List<CommerceDefaultSettingRel> list = null;

		if (useFinderCache) {
			list = (List<CommerceDefaultSettingRel>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDefaultSettingRel commerceDefaultSettingRel :
						list) {

					if ((commerceDefaultSettingId !=
							commerceDefaultSettingRel.
								getCommerceDefaultSettingId()) ||
						!type.equals(commerceDefaultSettingRel.getType())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_COMMERCEDEFAULTSETTINGREL_WHERE);

			sb.append(_FINDER_COLUMN_C_T_COMMERCEDEFAULTSETTINGID_2);

			boolean bindType = false;

			if (type.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_T_TYPE_3);
			}
			else {
				bindType = true;

				sb.append(_FINDER_COLUMN_C_T_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommerceDefaultSettingRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(commerceDefaultSettingId);

				if (bindType) {
					queryPos.add(type);
				}

				list = (List<CommerceDefaultSettingRel>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public CommerceDefaultSettingRel findByC_T_First(
			long commerceDefaultSettingId, String type,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException {

		CommerceDefaultSettingRel commerceDefaultSettingRel = fetchByC_T_First(
			commerceDefaultSettingId, type, orderByComparator);

		if (commerceDefaultSettingRel != null) {
			return commerceDefaultSettingRel;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("commerceDefaultSettingId=");
		sb.append(commerceDefaultSettingId);

		sb.append(", type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchCommerceDefaultSettingRelException(sb.toString());
	}

	/**
	 * Returns the first commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	@Override
	public CommerceDefaultSettingRel fetchByC_T_First(
		long commerceDefaultSettingId, String type,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		List<CommerceDefaultSettingRel> list = findByC_T(
			commerceDefaultSettingId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CommerceDefaultSettingRel findByC_T_Last(
			long commerceDefaultSettingId, String type,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException {

		CommerceDefaultSettingRel commerceDefaultSettingRel = fetchByC_T_Last(
			commerceDefaultSettingId, type, orderByComparator);

		if (commerceDefaultSettingRel != null) {
			return commerceDefaultSettingRel;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("commerceDefaultSettingId=");
		sb.append(commerceDefaultSettingId);

		sb.append(", type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchCommerceDefaultSettingRelException(sb.toString());
	}

	/**
	 * Returns the last commerce default setting rel in the ordered set where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce default setting rel, or <code>null</code> if a matching commerce default setting rel could not be found
	 */
	@Override
	public CommerceDefaultSettingRel fetchByC_T_Last(
		long commerceDefaultSettingId, String type,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		int count = countByC_T(commerceDefaultSettingId, type);

		if (count == 0) {
			return null;
		}

		List<CommerceDefaultSettingRel> list = findByC_T(
			commerceDefaultSettingId, type, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CommerceDefaultSettingRel[] findByC_T_PrevAndNext(
			long commerceDefaultSettingRelId, long commerceDefaultSettingId,
			String type,
			OrderByComparator<CommerceDefaultSettingRel> orderByComparator)
		throws NoSuchCommerceDefaultSettingRelException {

		type = Objects.toString(type, "");

		CommerceDefaultSettingRel commerceDefaultSettingRel = findByPrimaryKey(
			commerceDefaultSettingRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceDefaultSettingRel[] array =
				new CommerceDefaultSettingRelImpl[3];

			array[0] = getByC_T_PrevAndNext(
				session, commerceDefaultSettingRel, commerceDefaultSettingId,
				type, orderByComparator, true);

			array[1] = commerceDefaultSettingRel;

			array[2] = getByC_T_PrevAndNext(
				session, commerceDefaultSettingRel, commerceDefaultSettingId,
				type, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDefaultSettingRel getByC_T_PrevAndNext(
		Session session, CommerceDefaultSettingRel commerceDefaultSettingRel,
		long commerceDefaultSettingId, String type,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_COMMERCEDEFAULTSETTINGREL_WHERE);

		sb.append(_FINDER_COLUMN_C_T_COMMERCEDEFAULTSETTINGID_2);

		boolean bindType = false;

		if (type.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_T_TYPE_3);
		}
		else {
			bindType = true;

			sb.append(_FINDER_COLUMN_C_T_TYPE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CommerceDefaultSettingRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(commerceDefaultSettingId);

		if (bindType) {
			queryPos.add(type);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDefaultSettingRel)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceDefaultSettingRel> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce default setting rels where commerceDefaultSettingId = &#63; and type = &#63; from the database.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 */
	@Override
	public void removeByC_T(long commerceDefaultSettingId, String type) {
		for (CommerceDefaultSettingRel commerceDefaultSettingRel :
				findByC_T(
					commerceDefaultSettingId, type, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceDefaultSettingRel);
		}
	}

	/**
	 * Returns the number of commerce default setting rels where commerceDefaultSettingId = &#63; and type = &#63;.
	 *
	 * @param commerceDefaultSettingId the commerce default setting ID
	 * @param type the type
	 * @return the number of matching commerce default setting rels
	 */
	@Override
	public int countByC_T(long commerceDefaultSettingId, String type) {
		type = Objects.toString(type, "");

		FinderPath finderPath = _finderPathCountByC_T;

		Object[] finderArgs = new Object[] {commerceDefaultSettingId, type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_COMMERCEDEFAULTSETTINGREL_WHERE);

			sb.append(_FINDER_COLUMN_C_T_COMMERCEDEFAULTSETTINGID_2);

			boolean bindType = false;

			if (type.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_T_TYPE_3);
			}
			else {
				bindType = true;

				sb.append(_FINDER_COLUMN_C_T_TYPE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(commerceDefaultSettingId);

				if (bindType) {
					queryPos.add(type);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_T_COMMERCEDEFAULTSETTINGID_2 =
		"commerceDefaultSettingRel.commerceDefaultSettingId = ? AND ";

	private static final String _FINDER_COLUMN_C_T_TYPE_2 =
		"commerceDefaultSettingRel.type = ?";

	private static final String _FINDER_COLUMN_C_T_TYPE_3 =
		"(commerceDefaultSettingRel.type IS NULL OR commerceDefaultSettingRel.type = '')";

	public CommerceDefaultSettingRelPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(CommerceDefaultSettingRel.class);

		setModelImplClass(CommerceDefaultSettingRelImpl.class);
		setModelPKClass(long.class);

		setTable(CommerceDefaultSettingRelTable.INSTANCE);
	}

	/**
	 * Caches the commerce default setting rel in the entity cache if it is enabled.
	 *
	 * @param commerceDefaultSettingRel the commerce default setting rel
	 */
	@Override
	public void cacheResult(
		CommerceDefaultSettingRel commerceDefaultSettingRel) {

		entityCache.putResult(
			CommerceDefaultSettingRelImpl.class,
			commerceDefaultSettingRel.getPrimaryKey(),
			commerceDefaultSettingRel);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the commerce default setting rels in the entity cache if it is enabled.
	 *
	 * @param commerceDefaultSettingRels the commerce default setting rels
	 */
	@Override
	public void cacheResult(
		List<CommerceDefaultSettingRel> commerceDefaultSettingRels) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (commerceDefaultSettingRels.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CommerceDefaultSettingRel commerceDefaultSettingRel :
				commerceDefaultSettingRels) {

			if (entityCache.getResult(
					CommerceDefaultSettingRelImpl.class,
					commerceDefaultSettingRel.getPrimaryKey()) == null) {

				cacheResult(commerceDefaultSettingRel);
			}
		}
	}

	/**
	 * Clears the cache for all commerce default setting rels.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceDefaultSettingRelImpl.class);

		finderCache.clearCache(CommerceDefaultSettingRelImpl.class);
	}

	/**
	 * Clears the cache for the commerce default setting rel.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceDefaultSettingRel commerceDefaultSettingRel) {

		entityCache.removeResult(
			CommerceDefaultSettingRelImpl.class, commerceDefaultSettingRel);
	}

	@Override
	public void clearCache(
		List<CommerceDefaultSettingRel> commerceDefaultSettingRels) {

		for (CommerceDefaultSettingRel commerceDefaultSettingRel :
				commerceDefaultSettingRels) {

			entityCache.removeResult(
				CommerceDefaultSettingRelImpl.class, commerceDefaultSettingRel);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CommerceDefaultSettingRelImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CommerceDefaultSettingRelImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new commerce default setting rel with the primary key. Does not add the commerce default setting rel to the database.
	 *
	 * @param commerceDefaultSettingRelId the primary key for the new commerce default setting rel
	 * @return the new commerce default setting rel
	 */
	@Override
	public CommerceDefaultSettingRel create(long commerceDefaultSettingRelId) {
		CommerceDefaultSettingRel commerceDefaultSettingRel =
			new CommerceDefaultSettingRelImpl();

		commerceDefaultSettingRel.setNew(true);
		commerceDefaultSettingRel.setPrimaryKey(commerceDefaultSettingRelId);

		commerceDefaultSettingRel.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return commerceDefaultSettingRel;
	}

	/**
	 * Removes the commerce default setting rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDefaultSettingRelId the primary key of the commerce default setting rel
	 * @return the commerce default setting rel that was removed
	 * @throws NoSuchCommerceDefaultSettingRelException if a commerce default setting rel with the primary key could not be found
	 */
	@Override
	public CommerceDefaultSettingRel remove(long commerceDefaultSettingRelId)
		throws NoSuchCommerceDefaultSettingRelException {

		return remove((Serializable)commerceDefaultSettingRelId);
	}

	/**
	 * Removes the commerce default setting rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce default setting rel
	 * @return the commerce default setting rel that was removed
	 * @throws NoSuchCommerceDefaultSettingRelException if a commerce default setting rel with the primary key could not be found
	 */
	@Override
	public CommerceDefaultSettingRel remove(Serializable primaryKey)
		throws NoSuchCommerceDefaultSettingRelException {

		Session session = null;

		try {
			session = openSession();

			CommerceDefaultSettingRel commerceDefaultSettingRel =
				(CommerceDefaultSettingRel)session.get(
					CommerceDefaultSettingRelImpl.class, primaryKey);

			if (commerceDefaultSettingRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCommerceDefaultSettingRelException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceDefaultSettingRel);
		}
		catch (NoSuchCommerceDefaultSettingRelException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CommerceDefaultSettingRel removeImpl(
		CommerceDefaultSettingRel commerceDefaultSettingRel) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceDefaultSettingRel)) {
				commerceDefaultSettingRel =
					(CommerceDefaultSettingRel)session.get(
						CommerceDefaultSettingRelImpl.class,
						commerceDefaultSettingRel.getPrimaryKeyObj());
			}

			if (commerceDefaultSettingRel != null) {
				session.delete(commerceDefaultSettingRel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (commerceDefaultSettingRel != null) {
			clearCache(commerceDefaultSettingRel);
		}

		return commerceDefaultSettingRel;
	}

	@Override
	public CommerceDefaultSettingRel updateImpl(
		CommerceDefaultSettingRel commerceDefaultSettingRel) {

		boolean isNew = commerceDefaultSettingRel.isNew();

		if (!(commerceDefaultSettingRel instanceof
				CommerceDefaultSettingRelModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceDefaultSettingRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceDefaultSettingRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceDefaultSettingRel proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceDefaultSettingRel implementation " +
					commerceDefaultSettingRel.getClass());
		}

		CommerceDefaultSettingRelModelImpl commerceDefaultSettingRelModelImpl =
			(CommerceDefaultSettingRelModelImpl)commerceDefaultSettingRel;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (commerceDefaultSettingRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceDefaultSettingRel.setCreateDate(date);
			}
			else {
				commerceDefaultSettingRel.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!commerceDefaultSettingRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceDefaultSettingRel.setModifiedDate(date);
			}
			else {
				commerceDefaultSettingRel.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(commerceDefaultSettingRel);
			}
			else {
				commerceDefaultSettingRel =
					(CommerceDefaultSettingRel)session.merge(
						commerceDefaultSettingRel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CommerceDefaultSettingRelImpl.class,
			commerceDefaultSettingRelModelImpl, false, true);

		if (isNew) {
			commerceDefaultSettingRel.setNew(false);
		}

		commerceDefaultSettingRel.resetOriginalValues();

		return commerceDefaultSettingRel;
	}

	/**
	 * Returns the commerce default setting rel with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce default setting rel
	 * @return the commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a commerce default setting rel with the primary key could not be found
	 */
	@Override
	public CommerceDefaultSettingRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCommerceDefaultSettingRelException {

		CommerceDefaultSettingRel commerceDefaultSettingRel = fetchByPrimaryKey(
			primaryKey);

		if (commerceDefaultSettingRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCommerceDefaultSettingRelException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceDefaultSettingRel;
	}

	/**
	 * Returns the commerce default setting rel with the primary key or throws a <code>NoSuchCommerceDefaultSettingRelException</code> if it could not be found.
	 *
	 * @param commerceDefaultSettingRelId the primary key of the commerce default setting rel
	 * @return the commerce default setting rel
	 * @throws NoSuchCommerceDefaultSettingRelException if a commerce default setting rel with the primary key could not be found
	 */
	@Override
	public CommerceDefaultSettingRel findByPrimaryKey(
			long commerceDefaultSettingRelId)
		throws NoSuchCommerceDefaultSettingRelException {

		return findByPrimaryKey((Serializable)commerceDefaultSettingRelId);
	}

	/**
	 * Returns the commerce default setting rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceDefaultSettingRelId the primary key of the commerce default setting rel
	 * @return the commerce default setting rel, or <code>null</code> if a commerce default setting rel with the primary key could not be found
	 */
	@Override
	public CommerceDefaultSettingRel fetchByPrimaryKey(
		long commerceDefaultSettingRelId) {

		return fetchByPrimaryKey((Serializable)commerceDefaultSettingRelId);
	}

	/**
	 * Returns all the commerce default setting rels.
	 *
	 * @return the commerce default setting rels
	 */
	@Override
	public List<CommerceDefaultSettingRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<CommerceDefaultSettingRel> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<CommerceDefaultSettingRel> findAll(
		int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<CommerceDefaultSettingRel> findAll(
		int start, int end,
		OrderByComparator<CommerceDefaultSettingRel> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CommerceDefaultSettingRel> list = null;

		if (useFinderCache) {
			list = (List<CommerceDefaultSettingRel>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMMERCEDEFAULTSETTINGREL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEDEFAULTSETTINGREL;

				sql = sql.concat(
					CommerceDefaultSettingRelModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CommerceDefaultSettingRel>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the commerce default setting rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceDefaultSettingRel commerceDefaultSettingRel : findAll()) {
			remove(commerceDefaultSettingRel);
		}
	}

	/**
	 * Returns the number of commerce default setting rels.
	 *
	 * @return the number of commerce default setting rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_COMMERCEDEFAULTSETTINGREL);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "commerceDefaultSettingRelId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMMERCEDEFAULTSETTINGREL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CommerceDefaultSettingRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce default setting rel persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByCommerceDefaultSettingId =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByCommerceDefaultSettingId",
				new String[] {
					Long.class.getName(), Integer.class.getName(),
					Integer.class.getName(), OrderByComparator.class.getName()
				},
				new String[] {"commerceDefaultSettingId"}, true);

		_finderPathWithoutPaginationFindByCommerceDefaultSettingId =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByCommerceDefaultSettingId",
				new String[] {Long.class.getName()},
				new String[] {"commerceDefaultSettingId"}, true);

		_finderPathCountByCommerceDefaultSettingId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceDefaultSettingId",
			new String[] {Long.class.getName()},
			new String[] {"commerceDefaultSettingId"}, false);

		_finderPathWithPaginationFindByC_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"classNameId", "classPK"}, true);

		_finderPathWithoutPaginationFindByC_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"classNameId", "classPK"}, true);

		_finderPathCountByC_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"classNameId", "classPK"}, false);

		_finderPathWithPaginationFindByC_T = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"commerceDefaultSettingId", "type_"}, true);

		_finderPathWithoutPaginationFindByC_T = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_T",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"commerceDefaultSettingId", "type_"}, true);

		_finderPathCountByC_T = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_T",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"commerceDefaultSettingId", "type_"}, false);

		_setCommerceDefaultSettingRelUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setCommerceDefaultSettingRelUtilPersistence(null);

		entityCache.removeCache(CommerceDefaultSettingRelImpl.class.getName());
	}

	private void _setCommerceDefaultSettingRelUtilPersistence(
		CommerceDefaultSettingRelPersistence
			commerceDefaultSettingRelPersistence) {

		try {
			Field field = CommerceDefaultSettingRelUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, commerceDefaultSettingRelPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCEDEFAULTSETTINGREL =
		"SELECT commerceDefaultSettingRel FROM CommerceDefaultSettingRel commerceDefaultSettingRel";

	private static final String _SQL_SELECT_COMMERCEDEFAULTSETTINGREL_WHERE =
		"SELECT commerceDefaultSettingRel FROM CommerceDefaultSettingRel commerceDefaultSettingRel WHERE ";

	private static final String _SQL_COUNT_COMMERCEDEFAULTSETTINGREL =
		"SELECT COUNT(commerceDefaultSettingRel) FROM CommerceDefaultSettingRel commerceDefaultSettingRel";

	private static final String _SQL_COUNT_COMMERCEDEFAULTSETTINGREL_WHERE =
		"SELECT COUNT(commerceDefaultSettingRel) FROM CommerceDefaultSettingRel commerceDefaultSettingRel WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceDefaultSettingRel.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceDefaultSettingRel exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceDefaultSettingRel exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDefaultSettingRelPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private CommerceDefaultSettingRelModelArgumentsResolver
		_commerceDefaultSettingRelModelArgumentsResolver;

}