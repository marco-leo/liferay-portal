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
import com.liferay.commerce.qualifier.exception.NoSuchCommerceDefaultSettingRelException;
import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingRelLocalServiceUtil;
import com.liferay.commerce.qualifier.service.persistence.CommerceDefaultSettingRelPersistence;
import com.liferay.commerce.qualifier.service.persistence.CommerceDefaultSettingRelUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
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
public class CommerceDefaultSettingRelPersistenceTest {

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
		_persistence = CommerceDefaultSettingRelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceDefaultSettingRel> iterator =
			_commerceDefaultSettingRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceDefaultSettingRel commerceDefaultSettingRel =
			_persistence.create(pk);

		Assert.assertNotNull(commerceDefaultSettingRel);

		Assert.assertEquals(commerceDefaultSettingRel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceDefaultSettingRel newCommerceDefaultSettingRel =
			addCommerceDefaultSettingRel();

		_persistence.remove(newCommerceDefaultSettingRel);

		CommerceDefaultSettingRel existingCommerceDefaultSettingRel =
			_persistence.fetchByPrimaryKey(
				newCommerceDefaultSettingRel.getPrimaryKey());

		Assert.assertNull(existingCommerceDefaultSettingRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceDefaultSettingRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceDefaultSettingRel newCommerceDefaultSettingRel =
			_persistence.create(pk);

		newCommerceDefaultSettingRel.setMvccVersion(RandomTestUtil.nextLong());

		newCommerceDefaultSettingRel.setCompanyId(RandomTestUtil.nextLong());

		newCommerceDefaultSettingRel.setUserId(RandomTestUtil.nextLong());

		newCommerceDefaultSettingRel.setUserName(RandomTestUtil.randomString());

		newCommerceDefaultSettingRel.setCreateDate(RandomTestUtil.nextDate());

		newCommerceDefaultSettingRel.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceDefaultSettingRel.setClassNameId(RandomTestUtil.nextLong());

		newCommerceDefaultSettingRel.setClassPK(RandomTestUtil.nextLong());

		newCommerceDefaultSettingRel.setCommerceDefaultSettingId(
			RandomTestUtil.nextLong());

		newCommerceDefaultSettingRel.setPriority(RandomTestUtil.nextDouble());

		newCommerceDefaultSettingRel.setType(RandomTestUtil.randomString());

		_commerceDefaultSettingRels.add(
			_persistence.update(newCommerceDefaultSettingRel));

		CommerceDefaultSettingRel existingCommerceDefaultSettingRel =
			_persistence.findByPrimaryKey(
				newCommerceDefaultSettingRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceDefaultSettingRel.getMvccVersion(),
			newCommerceDefaultSettingRel.getMvccVersion());
		Assert.assertEquals(
			existingCommerceDefaultSettingRel.getCommerceDefaultSettingRelId(),
			newCommerceDefaultSettingRel.getCommerceDefaultSettingRelId());
		Assert.assertEquals(
			existingCommerceDefaultSettingRel.getCompanyId(),
			newCommerceDefaultSettingRel.getCompanyId());
		Assert.assertEquals(
			existingCommerceDefaultSettingRel.getUserId(),
			newCommerceDefaultSettingRel.getUserId());
		Assert.assertEquals(
			existingCommerceDefaultSettingRel.getUserName(),
			newCommerceDefaultSettingRel.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceDefaultSettingRel.getCreateDate()),
			Time.getShortTimestamp(
				newCommerceDefaultSettingRel.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceDefaultSettingRel.getModifiedDate()),
			Time.getShortTimestamp(
				newCommerceDefaultSettingRel.getModifiedDate()));
		Assert.assertEquals(
			existingCommerceDefaultSettingRel.getClassNameId(),
			newCommerceDefaultSettingRel.getClassNameId());
		Assert.assertEquals(
			existingCommerceDefaultSettingRel.getClassPK(),
			newCommerceDefaultSettingRel.getClassPK());
		Assert.assertEquals(
			existingCommerceDefaultSettingRel.getCommerceDefaultSettingId(),
			newCommerceDefaultSettingRel.getCommerceDefaultSettingId());
		AssertUtils.assertEquals(
			existingCommerceDefaultSettingRel.getPriority(),
			newCommerceDefaultSettingRel.getPriority());
		Assert.assertEquals(
			existingCommerceDefaultSettingRel.getType(),
			newCommerceDefaultSettingRel.getType());
	}

	@Test
	public void testCountByCommerceDefaultSettingId() throws Exception {
		_persistence.countByCommerceDefaultSettingId(RandomTestUtil.nextLong());

		_persistence.countByCommerceDefaultSettingId(0L);
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testCountByC_T() throws Exception {
		_persistence.countByC_T(RandomTestUtil.nextLong(), "");

		_persistence.countByC_T(0L, "null");

		_persistence.countByC_T(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceDefaultSettingRel newCommerceDefaultSettingRel =
			addCommerceDefaultSettingRel();

		CommerceDefaultSettingRel existingCommerceDefaultSettingRel =
			_persistence.findByPrimaryKey(
				newCommerceDefaultSettingRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceDefaultSettingRel, newCommerceDefaultSettingRel);
	}

	@Test(expected = NoSuchCommerceDefaultSettingRelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommerceDefaultSettingRel>
		getOrderByComparator() {

		return OrderByComparatorFactoryUtil.create(
			"CommerceDefaultSettingRel", "mvccVersion", true,
			"commerceDefaultSettingRelId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"classNameId", true, "classPK", true, "commerceDefaultSettingId",
			true, "priority", true, "type", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceDefaultSettingRel newCommerceDefaultSettingRel =
			addCommerceDefaultSettingRel();

		CommerceDefaultSettingRel existingCommerceDefaultSettingRel =
			_persistence.fetchByPrimaryKey(
				newCommerceDefaultSettingRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceDefaultSettingRel, newCommerceDefaultSettingRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceDefaultSettingRel missingCommerceDefaultSettingRel =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceDefaultSettingRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommerceDefaultSettingRel newCommerceDefaultSettingRel1 =
			addCommerceDefaultSettingRel();
		CommerceDefaultSettingRel newCommerceDefaultSettingRel2 =
			addCommerceDefaultSettingRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceDefaultSettingRel1.getPrimaryKey());
		primaryKeys.add(newCommerceDefaultSettingRel2.getPrimaryKey());

		Map<Serializable, CommerceDefaultSettingRel>
			commerceDefaultSettingRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(2, commerceDefaultSettingRels.size());
		Assert.assertEquals(
			newCommerceDefaultSettingRel1,
			commerceDefaultSettingRels.get(
				newCommerceDefaultSettingRel1.getPrimaryKey()));
		Assert.assertEquals(
			newCommerceDefaultSettingRel2,
			commerceDefaultSettingRels.get(
				newCommerceDefaultSettingRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceDefaultSettingRel>
			commerceDefaultSettingRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertTrue(commerceDefaultSettingRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommerceDefaultSettingRel newCommerceDefaultSettingRel =
			addCommerceDefaultSettingRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceDefaultSettingRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceDefaultSettingRel>
			commerceDefaultSettingRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(1, commerceDefaultSettingRels.size());
		Assert.assertEquals(
			newCommerceDefaultSettingRel,
			commerceDefaultSettingRels.get(
				newCommerceDefaultSettingRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceDefaultSettingRel>
			commerceDefaultSettingRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertTrue(commerceDefaultSettingRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommerceDefaultSettingRel newCommerceDefaultSettingRel =
			addCommerceDefaultSettingRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceDefaultSettingRel.getPrimaryKey());

		Map<Serializable, CommerceDefaultSettingRel>
			commerceDefaultSettingRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(1, commerceDefaultSettingRels.size());
		Assert.assertEquals(
			newCommerceDefaultSettingRel,
			commerceDefaultSettingRels.get(
				newCommerceDefaultSettingRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommerceDefaultSettingRelLocalServiceUtil.
				getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommerceDefaultSettingRel>() {

				@Override
				public void performAction(
					CommerceDefaultSettingRel commerceDefaultSettingRel) {

					Assert.assertNotNull(commerceDefaultSettingRel);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommerceDefaultSettingRel newCommerceDefaultSettingRel =
			addCommerceDefaultSettingRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceDefaultSettingRel.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceDefaultSettingRelId",
				newCommerceDefaultSettingRel.getCommerceDefaultSettingRelId()));

		List<CommerceDefaultSettingRel> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceDefaultSettingRel existingCommerceDefaultSettingRel =
			result.get(0);

		Assert.assertEquals(
			existingCommerceDefaultSettingRel, newCommerceDefaultSettingRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceDefaultSettingRel.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceDefaultSettingRelId", RandomTestUtil.nextLong()));

		List<CommerceDefaultSettingRel> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommerceDefaultSettingRel newCommerceDefaultSettingRel =
			addCommerceDefaultSettingRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceDefaultSettingRel.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceDefaultSettingRelId"));

		Object newCommerceDefaultSettingRelId =
			newCommerceDefaultSettingRel.getCommerceDefaultSettingRelId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceDefaultSettingRelId",
				new Object[] {newCommerceDefaultSettingRelId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceDefaultSettingRelId = result.get(0);

		Assert.assertEquals(
			existingCommerceDefaultSettingRelId,
			newCommerceDefaultSettingRelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceDefaultSettingRel.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceDefaultSettingRelId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceDefaultSettingRelId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CommerceDefaultSettingRel addCommerceDefaultSettingRel()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		CommerceDefaultSettingRel commerceDefaultSettingRel =
			_persistence.create(pk);

		commerceDefaultSettingRel.setMvccVersion(RandomTestUtil.nextLong());

		commerceDefaultSettingRel.setCompanyId(RandomTestUtil.nextLong());

		commerceDefaultSettingRel.setUserId(RandomTestUtil.nextLong());

		commerceDefaultSettingRel.setUserName(RandomTestUtil.randomString());

		commerceDefaultSettingRel.setCreateDate(RandomTestUtil.nextDate());

		commerceDefaultSettingRel.setModifiedDate(RandomTestUtil.nextDate());

		commerceDefaultSettingRel.setClassNameId(RandomTestUtil.nextLong());

		commerceDefaultSettingRel.setClassPK(RandomTestUtil.nextLong());

		commerceDefaultSettingRel.setCommerceDefaultSettingId(
			RandomTestUtil.nextLong());

		commerceDefaultSettingRel.setPriority(RandomTestUtil.nextDouble());

		commerceDefaultSettingRel.setType(RandomTestUtil.randomString());

		_commerceDefaultSettingRels.add(
			_persistence.update(commerceDefaultSettingRel));

		return commerceDefaultSettingRel;
	}

	private List<CommerceDefaultSettingRel> _commerceDefaultSettingRels =
		new ArrayList<CommerceDefaultSettingRel>();
	private CommerceDefaultSettingRelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}