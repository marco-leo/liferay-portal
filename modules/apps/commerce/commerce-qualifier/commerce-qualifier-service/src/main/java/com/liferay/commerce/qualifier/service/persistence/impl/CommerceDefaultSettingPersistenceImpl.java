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

import com.liferay.commerce.qualifier.exception.NoSuchCommerceDefaultSettingException;
import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.commerce.qualifier.model.CommerceDefaultSettingTable;
import com.liferay.commerce.qualifier.model.impl.CommerceDefaultSettingImpl;
import com.liferay.commerce.qualifier.model.impl.CommerceDefaultSettingModelImpl;
import com.liferay.commerce.qualifier.service.persistence.CommerceDefaultSettingPersistence;
import com.liferay.commerce.qualifier.service.persistence.CommerceDefaultSettingUtil;
import com.liferay.commerce.qualifier.service.persistence.impl.constants.CommercePersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
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

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the commerce default setting service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @generated
 */
@Component(
	service = {CommerceDefaultSettingPersistence.class, BasePersistence.class}
)
public class CommerceDefaultSettingPersistenceImpl
	extends BasePersistenceImpl<CommerceDefaultSetting>
	implements CommerceDefaultSettingPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceDefaultSettingUtil</code> to access the commerce default setting persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceDefaultSettingImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public CommerceDefaultSettingPersistenceImpl() {
		setModelClass(CommerceDefaultSetting.class);

		setModelImplClass(CommerceDefaultSettingImpl.class);
		setModelPKClass(long.class);

		setTable(CommerceDefaultSettingTable.INSTANCE);
	}

	/**
	 * Caches the commerce default setting in the entity cache if it is enabled.
	 *
	 * @param commerceDefaultSetting the commerce default setting
	 */
	@Override
	public void cacheResult(CommerceDefaultSetting commerceDefaultSetting) {
		entityCache.putResult(
			CommerceDefaultSettingImpl.class,
			commerceDefaultSetting.getPrimaryKey(), commerceDefaultSetting);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the commerce default settings in the entity cache if it is enabled.
	 *
	 * @param commerceDefaultSettings the commerce default settings
	 */
	@Override
	public void cacheResult(
		List<CommerceDefaultSetting> commerceDefaultSettings) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (commerceDefaultSettings.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CommerceDefaultSetting commerceDefaultSetting :
				commerceDefaultSettings) {

			if (entityCache.getResult(
					CommerceDefaultSettingImpl.class,
					commerceDefaultSetting.getPrimaryKey()) == null) {

				cacheResult(commerceDefaultSetting);
			}
		}
	}

	/**
	 * Clears the cache for all commerce default settings.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceDefaultSettingImpl.class);

		finderCache.clearCache(CommerceDefaultSettingImpl.class);
	}

	/**
	 * Clears the cache for the commerce default setting.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceDefaultSetting commerceDefaultSetting) {
		entityCache.removeResult(
			CommerceDefaultSettingImpl.class, commerceDefaultSetting);
	}

	@Override
	public void clearCache(
		List<CommerceDefaultSetting> commerceDefaultSettings) {

		for (CommerceDefaultSetting commerceDefaultSetting :
				commerceDefaultSettings) {

			entityCache.removeResult(
				CommerceDefaultSettingImpl.class, commerceDefaultSetting);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CommerceDefaultSettingImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CommerceDefaultSettingImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new commerce default setting with the primary key. Does not add the commerce default setting to the database.
	 *
	 * @param commerceDefaultSettingId the primary key for the new commerce default setting
	 * @return the new commerce default setting
	 */
	@Override
	public CommerceDefaultSetting create(long commerceDefaultSettingId) {
		CommerceDefaultSetting commerceDefaultSetting =
			new CommerceDefaultSettingImpl();

		commerceDefaultSetting.setNew(true);
		commerceDefaultSetting.setPrimaryKey(commerceDefaultSettingId);

		commerceDefaultSetting.setCompanyId(CompanyThreadLocal.getCompanyId());

		return commerceDefaultSetting;
	}

	/**
	 * Removes the commerce default setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDefaultSettingId the primary key of the commerce default setting
	 * @return the commerce default setting that was removed
	 * @throws NoSuchCommerceDefaultSettingException if a commerce default setting with the primary key could not be found
	 */
	@Override
	public CommerceDefaultSetting remove(long commerceDefaultSettingId)
		throws NoSuchCommerceDefaultSettingException {

		return remove((Serializable)commerceDefaultSettingId);
	}

	/**
	 * Removes the commerce default setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce default setting
	 * @return the commerce default setting that was removed
	 * @throws NoSuchCommerceDefaultSettingException if a commerce default setting with the primary key could not be found
	 */
	@Override
	public CommerceDefaultSetting remove(Serializable primaryKey)
		throws NoSuchCommerceDefaultSettingException {

		Session session = null;

		try {
			session = openSession();

			CommerceDefaultSetting commerceDefaultSetting =
				(CommerceDefaultSetting)session.get(
					CommerceDefaultSettingImpl.class, primaryKey);

			if (commerceDefaultSetting == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCommerceDefaultSettingException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceDefaultSetting);
		}
		catch (NoSuchCommerceDefaultSettingException noSuchEntityException) {
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
	protected CommerceDefaultSetting removeImpl(
		CommerceDefaultSetting commerceDefaultSetting) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceDefaultSetting)) {
				commerceDefaultSetting = (CommerceDefaultSetting)session.get(
					CommerceDefaultSettingImpl.class,
					commerceDefaultSetting.getPrimaryKeyObj());
			}

			if (commerceDefaultSetting != null) {
				session.delete(commerceDefaultSetting);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (commerceDefaultSetting != null) {
			clearCache(commerceDefaultSetting);
		}

		return commerceDefaultSetting;
	}

	@Override
	public CommerceDefaultSetting updateImpl(
		CommerceDefaultSetting commerceDefaultSetting) {

		boolean isNew = commerceDefaultSetting.isNew();

		if (!(commerceDefaultSetting instanceof
				CommerceDefaultSettingModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceDefaultSetting.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceDefaultSetting);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceDefaultSetting proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceDefaultSetting implementation " +
					commerceDefaultSetting.getClass());
		}

		CommerceDefaultSettingModelImpl commerceDefaultSettingModelImpl =
			(CommerceDefaultSettingModelImpl)commerceDefaultSetting;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (commerceDefaultSetting.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceDefaultSetting.setCreateDate(date);
			}
			else {
				commerceDefaultSetting.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!commerceDefaultSettingModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceDefaultSetting.setModifiedDate(date);
			}
			else {
				commerceDefaultSetting.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(commerceDefaultSetting);
			}
			else {
				commerceDefaultSetting = (CommerceDefaultSetting)session.merge(
					commerceDefaultSetting);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CommerceDefaultSettingImpl.class, commerceDefaultSetting, false,
			true);

		if (isNew) {
			commerceDefaultSetting.setNew(false);
		}

		commerceDefaultSetting.resetOriginalValues();

		return commerceDefaultSetting;
	}

	/**
	 * Returns the commerce default setting with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce default setting
	 * @return the commerce default setting
	 * @throws NoSuchCommerceDefaultSettingException if a commerce default setting with the primary key could not be found
	 */
	@Override
	public CommerceDefaultSetting findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCommerceDefaultSettingException {

		CommerceDefaultSetting commerceDefaultSetting = fetchByPrimaryKey(
			primaryKey);

		if (commerceDefaultSetting == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCommerceDefaultSettingException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceDefaultSetting;
	}

	/**
	 * Returns the commerce default setting with the primary key or throws a <code>NoSuchCommerceDefaultSettingException</code> if it could not be found.
	 *
	 * @param commerceDefaultSettingId the primary key of the commerce default setting
	 * @return the commerce default setting
	 * @throws NoSuchCommerceDefaultSettingException if a commerce default setting with the primary key could not be found
	 */
	@Override
	public CommerceDefaultSetting findByPrimaryKey(
			long commerceDefaultSettingId)
		throws NoSuchCommerceDefaultSettingException {

		return findByPrimaryKey((Serializable)commerceDefaultSettingId);
	}

	/**
	 * Returns the commerce default setting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceDefaultSettingId the primary key of the commerce default setting
	 * @return the commerce default setting, or <code>null</code> if a commerce default setting with the primary key could not be found
	 */
	@Override
	public CommerceDefaultSetting fetchByPrimaryKey(
		long commerceDefaultSettingId) {

		return fetchByPrimaryKey((Serializable)commerceDefaultSettingId);
	}

	/**
	 * Returns all the commerce default settings.
	 *
	 * @return the commerce default settings
	 */
	@Override
	public List<CommerceDefaultSetting> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<CommerceDefaultSetting> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<CommerceDefaultSetting> findAll(
		int start, int end,
		OrderByComparator<CommerceDefaultSetting> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<CommerceDefaultSetting> findAll(
		int start, int end,
		OrderByComparator<CommerceDefaultSetting> orderByComparator,
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

		List<CommerceDefaultSetting> list = null;

		if (useFinderCache) {
			list = (List<CommerceDefaultSetting>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMMERCEDEFAULTSETTING);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEDEFAULTSETTING;

				sql = sql.concat(CommerceDefaultSettingModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CommerceDefaultSetting>)QueryUtil.list(
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
	 * Removes all the commerce default settings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceDefaultSetting commerceDefaultSetting : findAll()) {
			remove(commerceDefaultSetting);
		}
	}

	/**
	 * Returns the number of commerce default settings.
	 *
	 * @return the number of commerce default settings
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
					_SQL_COUNT_COMMERCEDEFAULTSETTING);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "commerceDefaultSettingId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMMERCEDEFAULTSETTING;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CommerceDefaultSettingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce default setting persistence.
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

		_setCommerceDefaultSettingUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setCommerceDefaultSettingUtilPersistence(null);

		entityCache.removeCache(CommerceDefaultSettingImpl.class.getName());
	}

	private void _setCommerceDefaultSettingUtilPersistence(
		CommerceDefaultSettingPersistence commerceDefaultSettingPersistence) {

		try {
			Field field = CommerceDefaultSettingUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, commerceDefaultSettingPersistence);
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

	private static final String _SQL_SELECT_COMMERCEDEFAULTSETTING =
		"SELECT commerceDefaultSetting FROM CommerceDefaultSetting commerceDefaultSetting";

	private static final String _SQL_COUNT_COMMERCEDEFAULTSETTING =
		"SELECT COUNT(commerceDefaultSetting) FROM CommerceDefaultSetting commerceDefaultSetting";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceDefaultSetting.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceDefaultSetting exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDefaultSettingPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private CommerceDefaultSettingModelArgumentsResolver
		_commerceDefaultSettingModelArgumentsResolver;

}