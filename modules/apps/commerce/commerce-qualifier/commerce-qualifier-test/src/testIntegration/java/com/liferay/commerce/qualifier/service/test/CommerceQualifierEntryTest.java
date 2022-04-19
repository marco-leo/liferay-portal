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

package com.liferay.commerce.qualifier.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryLocalService;
import com.liferay.commerce.service.CommerceOrderTypeLocalService;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Riccardo Alberti
 */
@RunWith(Arquillian.class)
public class CommerceQualifierEntryTest {

	@ClassRule
	@Rule
	public static AggregateTestRule aggregateTestRule = new AggregateTestRule(
		new LiferayIntegrationTestRule(),
		PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_user = UserTestUtil.addUser();

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency(
			_group.getCompanyId());

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			_group.getCompanyId(), _group.getGroupId(), _user.getUserId());

		_serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		_commerceCatalog = _commerceCatalogLocalService.addCommerceCatalog(
			null, RandomTestUtil.randomString(), _commerceCurrency.getCode(),
			LocaleUtil.US.getDisplayLanguage(), _serviceContext);

		_commerceChannel = CommerceTestUtil.addCommerceChannel(
			_group.getGroupId(), _commerceCurrency.getCode());

		Calendar calendar = CalendarFactoryUtil.getCalendar(
			_user.getTimeZone());

		_commerceOrderType =
			_commerceOrderTypeLocalService.addCommerceOrderType(
				null, _user.getUserId(), RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(), true,
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), 0, calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), true, _serviceContext);
	}

	@Test
	public void testRetrievePriceListByQualifiersExclusive() throws Exception {
		Calendar calendar = CalendarFactoryUtil.getCalendar(
			_user.getTimeZone());

		CommercePriceList commercePriceList1 =
			_commercePriceListLocalService.addCommercePriceList(
				null, _commerceCatalog.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(), true, "price-list",
				0, false, RandomTestUtil.randomString(), 0,
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), true, _serviceContext);

		CommercePriceList commercePriceList2 =
			_commercePriceListLocalService.addCommercePriceList(
				null, _commerceCatalog.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(), true, "price-list",
				0, false, RandomTestUtil.randomString(), 0,
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), true, _serviceContext);

		_commercePriceListLocalService.addCommercePriceList(
			null, _commerceCatalog.getGroupId(), _user.getUserId(),
			_commerceCurrency.getCommerceCurrencyId(), true, "price-list", 0,
			false, RandomTestUtil.randomString(), 1,
			calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
			calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
			calendar.get(Calendar.MINUTE), calendar.get(Calendar.MONTH),
			calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR),
			calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
			true, _serviceContext);

		_commerceQualifierEntryLocalService.addCommerceQualifierEntry(
			_user.getUserId(), CommercePriceList.class.getName(),
			commercePriceList1.getCommercePriceListId(),
			CommerceOrderType.class.getName(),
			_commerceOrderType.getCommerceOrderTypeId(), false);

		_commerceQualifierEntryLocalService.addCommerceQualifierEntry(
			_user.getUserId(), CommercePriceList.class.getName(),
			commercePriceList1.getCommercePriceListId(),
			CommerceChannel.class.getName(),
			_commerceChannel.getCommerceChannelId(), false);

		_commerceQualifierEntryLocalService.addCommerceQualifierEntry(
			_user.getUserId(), CommercePriceList.class.getName(),
			commercePriceList2.getCommercePriceListId(),
			CommerceChannel.class.getName(),
			_commerceChannel.getCommerceChannelId(), false);

		List<CommercePriceList> commercePriceLists =
			_commerceQualifierEntryLocalService.
				getCommerceQualifierEntriesSourcesByTargets(
					_user.getCompanyId(), true, CommercePriceList.class,
					HashMapBuilder.<String, Object>put(
						"catalogBasePriceList", false
					).put(
						"groupId", _commerceCatalog.getGroupId()
					).put(
						"status", WorkflowConstants.STATUS_APPROVED
					).put(
						"type_", "price-list"
					).build(),
					HashMapBuilder.<String, Object>put(
						CommerceChannel.class.getName(),
						_commerceChannel.getCommerceChannelId()
					).build());

		Assert.assertTrue(commercePriceLists.isEmpty());
	}

	@Test
	public void testRetrievePriceListByQualifiersInclusive() throws Exception {
		Calendar calendar = CalendarFactoryUtil.getCalendar(
			_user.getTimeZone());

		CommercePriceList commercePriceList1 =
			_commercePriceListLocalService.addCommercePriceList(
				null, _commerceCatalog.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(), true, "price-list",
				0, false, RandomTestUtil.randomString(), 0,
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), true, _serviceContext);

		CommercePriceList commercePriceList2 =
			_commercePriceListLocalService.addCommercePriceList(
				null, _commerceCatalog.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(), true, "price-list",
				0, false, RandomTestUtil.randomString(), 0,
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), true, _serviceContext);

		_commercePriceListLocalService.addCommercePriceList(
			null, _commerceCatalog.getGroupId(), _user.getUserId(),
			_commerceCurrency.getCommerceCurrencyId(), true, "price-list", 0,
			false, RandomTestUtil.randomString(), 1,
			calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
			calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
			calendar.get(Calendar.MINUTE), calendar.get(Calendar.MONTH),
			calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR),
			calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
			true, _serviceContext);

		_commerceQualifierEntryLocalService.addCommerceQualifierEntry(
			_user.getUserId(), CommercePriceList.class.getName(),
			commercePriceList1.getCommercePriceListId(),
			CommerceOrderType.class.getName(),
			_commerceOrderType.getCommerceOrderTypeId(), false);

		_commerceQualifierEntryLocalService.addCommerceQualifierEntry(
			_user.getUserId(), CommercePriceList.class.getName(),
			commercePriceList1.getCommercePriceListId(),
			CommerceChannel.class.getName(),
			_commerceChannel.getCommerceChannelId(), false);

		_commerceQualifierEntryLocalService.addCommerceQualifierEntry(
			_user.getUserId(), CommercePriceList.class.getName(),
			commercePriceList2.getCommercePriceListId(),
			CommerceChannel.class.getName(),
			_commerceChannel.getCommerceChannelId(), false);

		List<CommercePriceList> commercePriceLists =
			_commerceQualifierEntryLocalService.
				getCommerceQualifierEntriesSourcesByTargets(
					_user.getCompanyId(), false, CommercePriceList.class,
					HashMapBuilder.<String, Object>put(
						"catalogBasePriceList", false
					).put(
						"groupId", _commerceCatalog.getGroupId()
					).put(
						"status", WorkflowConstants.STATUS_APPROVED
					).put(
						"type_", "price-list"
					).build(),
					HashMapBuilder.<String, Object>put(
						CommerceChannel.class.getName(),
						_commerceChannel.getCommerceChannelId()
					).build());

		CommercePriceList discoveredPriceList = commercePriceLists.get(0);

		Assert.assertEquals(
			commercePriceList1.getCommercePriceListId(),
			discoveredPriceList.getCommercePriceListId());
	}

	private static User _user;

	private CommerceCatalog _commerceCatalog;

	@Inject
	private CommerceCatalogLocalService _commerceCatalogLocalService;

	private CommerceChannel _commerceChannel;

	@Inject
	private CommerceChannelLocalService _commerceChannelLocalService;

	private CommerceCurrency _commerceCurrency;
	private CommerceOrderType _commerceOrderType;

	@Inject
	private CommerceOrderTypeLocalService _commerceOrderTypeLocalService;

	@Inject
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Inject
	private CommerceQualifierEntryLocalService
		_commerceQualifierEntryLocalService;

	private Group _group;
	private ServiceContext _serviceContext;

}