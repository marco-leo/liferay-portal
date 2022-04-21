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

package com.liferay.commerce.qualifier.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.qualifier.exception.NoSuchCommerceDefaultSettingException;
import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingLocalServiceUtil;
import com.liferay.commerce.qualifier.service.persistence.CommerceDefaultSettingPersistence;
import com.liferay.commerce.qualifier.service.persistence.CommerceDefaultSettingUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CommerceDefaultSettingPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.commerce.qualifier.service"));

	@Before
	public void setUp() {
		_persistence = CommerceDefaultSettingUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceDefaultSetting> iterator =
			_commerceDefaultSettings.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceDefaultSetting commerceDefaultSetting = _persistence.create(pk);

		Assert.assertNotNull(commerceDefaultSetting);

		Assert.assertEquals(commerceDefaultSetting.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceDefaultSetting newCommerceDefaultSetting =
			addCommerceDefaultSetting();

		_persistence.remove(newCommerceDefaultSetting);

		CommerceDefaultSetting existingCommerceDefaultSetting =
			_persistence.fetchByPrimaryKey(
				newCommerceDefaultSetting.getPrimaryKey());

		Assert.assertNull(existingCommerceDefaultSetting);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceDefaultSetting();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceDefaultSetting newCommerceDefaultSetting = _persistence.create(
			pk);

		newCommerceDefaultSetting.setMvccVersion(RandomTestUtil.nextLong());

		newCommerceDefaultSetting.setCompanyId(RandomTestUtil.nextLong());

		newCommerceDefaultSetting.setUserId(RandomTestUtil.nextLong());

		newCommerceDefaultSetting.setUserName(RandomTestUtil.randomString());

		newCommerceDefaultSetting.setCreateDate(RandomTestUtil.nextDate());

		newCommerceDefaultSetting.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceDefaultSetting.setName(RandomTestUtil.randomString());

		_commerceDefaultSettings.add(
			_persistence.update(newCommerceDefaultSetting));

		CommerceDefaultSetting existingCommerceDefaultSetting =
			_persistence.findByPrimaryKey(
				newCommerceDefaultSetting.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceDefaultSetting.getMvccVersion(),
			newCommerceDefaultSetting.getMvccVersion());
		Assert.assertEquals(
			existingCommerceDefaultSetting.getCommerceDefaultSettingId(),
			newCommerceDefaultSetting.getCommerceDefaultSettingId());
		Assert.assertEquals(
			existingCommerceDefaultSetting.getCompanyId(),
			newCommerceDefaultSetting.getCompanyId());
		Assert.assertEquals(
			existingCommerceDefaultSetting.getUserId(),
			newCommerceDefaultSetting.getUserId());
		Assert.assertEquals(
			existingCommerceDefaultSetting.getUserName(),
			newCommerceDefaultSetting.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceDefaultSetting.getCreateDate()),
			Time.getShortTimestamp(newCommerceDefaultSetting.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceDefaultSetting.getModifiedDate()),
			Time.getShortTimestamp(
				newCommerceDefaultSetting.getModifiedDate()));
		Assert.assertEquals(
			existingCommerceDefaultSetting.getName(),
			newCommerceDefaultSetting.getName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceDefaultSetting newCommerceDefaultSetting =
			addCommerceDefaultSetting();

		CommerceDefaultSetting existingCommerceDefaultSetting =
			_persistence.findByPrimaryKey(
				newCommerceDefaultSetting.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceDefaultSetting, newCommerceDefaultSetting);
	}

	@Test(expected = NoSuchCommerceDefaultSettingException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommerceDefaultSetting> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"CommerceDefaultSetting", "mvccVersion", true,
			"commerceDefaultSettingId", true, "companyId", true, "userId", true,
			"userName", true, "createDate", true, "modifiedDate", true, "name",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceDefaultSetting newCommerceDefaultSetting =
			addCommerceDefaultSetting();

		CommerceDefaultSetting existingCommerceDefaultSetting =
			_persistence.fetchByPrimaryKey(
				newCommerceDefaultSetting.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceDefaultSetting, newCommerceDefaultSetting);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceDefaultSetting missingCommerceDefaultSetting =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceDefaultSetting);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommerceDefaultSetting newCommerceDefaultSetting1 =
			addCommerceDefaultSetting();
		CommerceDefaultSetting newCommerceDefaultSetting2 =
			addCommerceDefaultSetting();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceDefaultSetting1.getPrimaryKey());
		primaryKeys.add(newCommerceDefaultSetting2.getPrimaryKey());

		Map<Serializable, CommerceDefaultSetting> commerceDefaultSettings =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceDefaultSettings.size());
		Assert.assertEquals(
			newCommerceDefaultSetting1,
			commerceDefaultSettings.get(
				newCommerceDefaultSetting1.getPrimaryKey()));
		Assert.assertEquals(
			newCommerceDefaultSetting2,
			commerceDefaultSettings.get(
				newCommerceDefaultSetting2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceDefaultSetting> commerceDefaultSettings =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceDefaultSettings.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommerceDefaultSetting newCommerceDefaultSetting =
			addCommerceDefaultSetting();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceDefaultSetting.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceDefaultSetting> commerceDefaultSettings =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceDefaultSettings.size());
		Assert.assertEquals(
			newCommerceDefaultSetting,
			commerceDefaultSettings.get(
				newCommerceDefaultSetting.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceDefaultSetting> commerceDefaultSettings =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceDefaultSettings.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommerceDefaultSetting newCommerceDefaultSetting =
			addCommerceDefaultSetting();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceDefaultSetting.getPrimaryKey());

		Map<Serializable, CommerceDefaultSetting> commerceDefaultSettings =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceDefaultSettings.size());
		Assert.assertEquals(
			newCommerceDefaultSetting,
			commerceDefaultSettings.get(
				newCommerceDefaultSetting.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommerceDefaultSettingLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommerceDefaultSetting>() {

				@Override
				public void performAction(
					CommerceDefaultSetting commerceDefaultSetting) {

					Assert.assertNotNull(commerceDefaultSetting);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommerceDefaultSetting newCommerceDefaultSetting =
			addCommerceDefaultSetting();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceDefaultSetting.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceDefaultSettingId",
				newCommerceDefaultSetting.getCommerceDefaultSettingId()));

		List<CommerceDefaultSetting> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceDefaultSetting existingCommerceDefaultSetting = result.get(0);

		Assert.assertEquals(
			existingCommerceDefaultSetting, newCommerceDefaultSetting);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceDefaultSetting.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceDefaultSettingId", RandomTestUtil.nextLong()));

		List<CommerceDefaultSetting> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommerceDefaultSetting newCommerceDefaultSetting =
			addCommerceDefaultSetting();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceDefaultSetting.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceDefaultSettingId"));

		Object newCommerceDefaultSettingId =
			newCommerceDefaultSetting.getCommerceDefaultSettingId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceDefaultSettingId",
				new Object[] {newCommerceDefaultSettingId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceDefaultSettingId = result.get(0);

		Assert.assertEquals(
			existingCommerceDefaultSettingId, newCommerceDefaultSettingId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceDefaultSetting.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceDefaultSettingId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceDefaultSettingId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CommerceDefaultSetting addCommerceDefaultSetting()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		CommerceDefaultSetting commerceDefaultSetting = _persistence.create(pk);

		commerceDefaultSetting.setMvccVersion(RandomTestUtil.nextLong());

		commerceDefaultSetting.setCompanyId(RandomTestUtil.nextLong());

		commerceDefaultSetting.setUserId(RandomTestUtil.nextLong());

		commerceDefaultSetting.setUserName(RandomTestUtil.randomString());

		commerceDefaultSetting.setCreateDate(RandomTestUtil.nextDate());

		commerceDefaultSetting.setModifiedDate(RandomTestUtil.nextDate());

		commerceDefaultSetting.setName(RandomTestUtil.randomString());

		_commerceDefaultSettings.add(
			_persistence.update(commerceDefaultSetting));

		return commerceDefaultSetting;
	}

	private List<CommerceDefaultSetting> _commerceDefaultSettings =
		new ArrayList<CommerceDefaultSetting>();
	private CommerceDefaultSettingPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}