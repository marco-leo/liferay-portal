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

import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountGroup;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupCommerceAccountRelLocalServiceUtil;
import com.liferay.commerce.account.service.CommerceAccountGroupLocalService;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.account.test.util.CommerceAccountTestUtil;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.price.list.constants.CommercePriceListConstants;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.commerce.qualifier.search.context.CommerceQualifierSearchContext;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingLocalService;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingRelLocalService;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryLocalService;
import com.liferay.commerce.test.util.CommerceAccountGroupTestUtil;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.io.Closeable;

import java.util.Calendar;
import java.util.List;

import org.frutilla.FrutillaRule;

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
public class CommercePriceListQualifiersTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_user = UserTestUtil.addUser();

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency(
			_group.getCompanyId());

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			_group.getCompanyId(), _group.getGroupId(), _user.getUserId());

		_commerceAccount1 =
			_commerceAccountLocalService.getPersonalCommerceAccount(
				_user.getUserId());

		_commerceAccountGroup =
			_commerceAccountGroupLocalService.addCommerceAccountGroup(
				_group.getCompanyId(), RandomTestUtil.randomString(), 0, false,
				null, _serviceContext);

		CommerceAccountGroupCommerceAccountRelLocalServiceUtil.
			addCommerceAccountGroupCommerceAccountRel(
				_commerceAccountGroup.getCommerceAccountGroupId(),
				_commerceAccount1.getCommerceAccountId(), _serviceContext);

		_commerceChannel1 = CommerceTestUtil.addCommerceChannel(
			_group.getGroupId(), _commerceCurrency.getCode());

		_catalog = _commerceCatalogLocalService.addCommerceCatalog(
			null, RandomTestUtil.randomString(), _commerceCurrency.getCode(),
			LocaleUtil.US.getDisplayLanguage(), _serviceContext);

		_commerceAccount2 = CommerceAccountTestUtil.addBusinessCommerceAccount(
			_user.getUserId(), "Business Account1", "example1@email.com",
			_serviceContext);
		_commerceAccount3 = CommerceAccountTestUtil.addBusinessCommerceAccount(
			_user.getUserId(), "Business Account2", "example1@email.com",
			_serviceContext);
		_commerceAccount4 = CommerceAccountTestUtil.addBusinessCommerceAccount(
			_user.getUserId(), "Business Account3", "example1@email.com",
			_serviceContext);
		_commerceAccount5 = CommerceAccountTestUtil.addBusinessCommerceAccount(
			_user.getUserId(), "Business Account4", "example1@email.com",
			_serviceContext);
		_commerceAccount6 = CommerceAccountTestUtil.addBusinessCommerceAccount(
			_user.getUserId(), "Business Account5", "example1@email.com",
			_serviceContext);
		_commerceAccount7 = CommerceAccountTestUtil.addBusinessCommerceAccount(
			_user.getUserId(), "Business Account6", "example1@email.com",
			_serviceContext);

		CommerceAccountGroupTestUtil.addCommerceAccountToAccountGroup(
			_group.getGroupId(), _commerceAccount4);
		CommerceAccountGroupTestUtil.addCommerceAccountToAccountGroup(
			_group.getGroupId(), _commerceAccount5);

		_commerceChannel2 = CommerceTestUtil.addCommerceChannel(
			_group.getGroupId(), _commerceCurrency.getCode());
		_commerceChannel3 = CommerceTestUtil.addCommerceChannel(
			_group.getGroupId(), _commerceCurrency.getCode());
		_commerceChannel4 = CommerceTestUtil.addCommerceChannel(
			_group.getGroupId(), _commerceCurrency.getCode());
		_commerceChannel5 = CommerceTestUtil.addCommerceChannel(
			_group.getGroupId(), _commerceCurrency.getCode());

		long[] commerceAccount3AccountGroups =
			_commerceAccountHelper.getCommerceAccountGroupIds(
				_commerceAccount4.getCommerceAccountId());
		long[] commerceAccount4AccountGroups =
			_commerceAccountHelper.getCommerceAccountGroupIds(
				_commerceAccount5.getCommerceAccountId());

		_commercePriceList1 = _addCommercePriceList(
			_catalog.getGroupId(), false, _TYPE, 1.0);
		_commercePriceList2 = _addCommercePriceList(
			_catalog.getGroupId(), false, _TYPE, 1.0);
		_commercePriceList3 = _addCommercePriceList(
			_catalog.getGroupId(), false, _TYPE, 1.0);
		_commercePriceList4 = _addCommercePriceList(
			_catalog.getGroupId(), false, _TYPE, 1.0);
		_commercePriceList5 = _addCommercePriceList(
			_catalog.getGroupId(), false, _TYPE, 1.0);

		_addCommerceQualifierEntryToCommercePriceList(
			_commercePriceList1.getCommercePriceListId(),
			AccountEntry.class.getName(),
			_commerceAccount2.getCommerceAccountId());

		_addCommerceQualifierEntryToCommercePriceList(
			_commercePriceList1.getCommercePriceListId(),
			AccountEntry.class.getName(),
			CommerceAccountConstants.ACCOUNT_ID_GUEST);

		_addCommerceQualifierEntryToCommercePriceList(
			_commercePriceList1.getCommercePriceListId(),
			CommerceChannel.class.getName(),
			_commerceChannel2.getCommerceChannelId());

		_addCommerceQualifierEntryToCommercePriceList(
			_commercePriceList1.getCommercePriceListId(),
			AccountEntry.class.getName(),
			_commerceAccount3.getCommerceAccountId());

		_addCommerceQualifierEntryToCommercePriceList(
			_commercePriceList2.getCommercePriceListId(),
			AccountGroup.class.getName(), commerceAccount3AccountGroups);

		_addCommerceQualifierEntryToCommercePriceList(
			_commercePriceList2.getCommercePriceListId(),
			CommerceChannel.class.getName(),
			_commerceChannel3.getCommerceChannelId());

		_addCommerceQualifierEntryToCommercePriceList(
			_commercePriceList3.getCommercePriceListId(),
			CommerceChannel.class.getName(),
			_commerceChannel2.getCommerceChannelId());

		_addCommerceQualifierEntryToCommercePriceList(
			_commercePriceList3.getCommercePriceListId(),
			AccountGroup.class.getName(), commerceAccount4AccountGroups);

		_addCommerceQualifierEntryToCommercePriceList(
			_commercePriceList4.getCommercePriceListId(),
			CommerceChannel.class.getName(),
			_commerceChannel4.getCommerceChannelId());
	}

	@Test
	public void testCleanCache() throws Exception {
		_commerceQualifierEntryLocalService.cleanCommerceQualifierEntryCache(
			40001, CommercePriceList.class.getName());
	}

	@Test
	public void testCreateCatalogBasePriceList() throws Exception {
		frutillaRule.scenario(
			"A price list is created and set as base pricelist for a catalog"
		).given(
			"A catalog"
		).when(
			"A price list is created with flag catalog base price list true"
		).then(
			"The price list is the default price list of the catalog"
		);

		try (Closeable closeable = _startTimer()) {
			CommerceCatalog catalog =
				_commerceCatalogLocalService.addCommerceCatalog(
					null, RandomTestUtil.randomString(),
					_commerceCurrency.getCode(),
					LocaleUtil.US.getDisplayLanguage(), _serviceContext);

			CommercePriceList commercePriceList =
				_commercePriceListLocalService.
					fetchCatalogBaseCommercePriceList(catalog.getGroupId());

			CommercePriceList discoveredPriceList = _getCommercePriceList(
				catalog.getGroupId(), 0, 0, 0, _TYPE);

			Assert.assertEquals(
				commercePriceList.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());
		}
	}

	@Test
	public void testRetrieveCorrectPriceListByHierarchy() throws Exception {
		frutillaRule.scenario(
			"When multiple price list are defined for the same catalog the " +
				"highest in the hierarchy shall be taken"
		).given(
			"A catalog with multiple price lists"
		).when(
			"The price list is discovered"
		).then(
			"The price list with highest rank is retrieved"
		);

		try (Closeable closeable = _startTimer()) {
			CommerceCatalog catalog =
				_commerceCatalogLocalService.addCommerceCatalog(
					null, RandomTestUtil.randomString(),
					_commerceCurrency.getCode(),
					LocaleUtil.US.getDisplayLanguage(), _serviceContext);

			CommercePriceList commerceUnqualifiedPriceList =
				_addCommercePriceList(catalog.getGroupId(), false, _TYPE, 1.0);

			CommercePriceList discoveredPriceList = _getCommercePriceList(
				catalog.getGroupId(), _commerceAccount1.getCommerceAccountId(),
				_commerceChannel1.getCommerceChannelId(), 0, _TYPE);

			Assert.assertEquals(
				commerceUnqualifiedPriceList.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());

			CommercePriceList commerceChannelPriceList = _addCommercePriceList(
				catalog.getGroupId(), false, _TYPE, 1.0);

			_addCommerceQualifierEntryToCommercePriceList(
				commerceChannelPriceList.getCommercePriceListId(),
				CommerceChannel.class.getName(),
				_commerceChannel1.getCommerceChannelId());

			discoveredPriceList = _getCommercePriceList(
				catalog.getGroupId(), _commerceAccount1.getCommerceAccountId(),
				_commerceChannel1.getCommerceChannelId(), 0, _TYPE);

			Assert.assertEquals(
				commerceChannelPriceList.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());

			long[] commerceAccountGroupIds =
				_commerceAccountHelper.getCommerceAccountGroupIds(
					_commerceAccount1.getCommerceAccountId());

			CommercePriceList commerceAccountGroupPriceList =
				_addCommercePriceList(catalog.getGroupId(), false, _TYPE, 1.0);

			_addCommerceQualifierEntryToCommercePriceList(
				commerceAccountGroupPriceList.getCommercePriceListId(),
				AccountGroup.class.getName(), commerceAccountGroupIds);

			discoveredPriceList = _getCommercePriceList(
				catalog.getGroupId(), _commerceAccount1.getCommerceAccountId(),
				_commerceChannel1.getCommerceChannelId(), 0, _TYPE);

			Assert.assertEquals(
				commerceAccountGroupPriceList.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());

			CommercePriceList commerceAccountGroupAndChannelPriceList =
				_addCommercePriceList(catalog.getGroupId(), false, _TYPE, 1.0);

			_addCommerceQualifierEntryToCommercePriceList(
				commerceAccountGroupAndChannelPriceList.
					getCommercePriceListId(),
				AccountGroup.class.getName(), commerceAccountGroupIds);

			_addCommerceQualifierEntryToCommercePriceList(
				commerceAccountGroupAndChannelPriceList.
					getCommercePriceListId(),
				CommerceChannel.class.getName(),
				_commerceChannel1.getCommerceChannelId());

			discoveredPriceList = _getCommercePriceList(
				catalog.getGroupId(), _commerceAccount1.getCommerceAccountId(),
				_commerceChannel1.getCommerceChannelId(), 0, _TYPE);

			Assert.assertEquals(
				commerceAccountGroupAndChannelPriceList.
					getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());

			CommercePriceList commerceAccountPriceList = _addCommercePriceList(
				catalog.getGroupId(), false, _TYPE, 1.0);

			_addCommerceQualifierEntryToCommercePriceList(
				commerceAccountPriceList.getCommercePriceListId(),
				AccountEntry.class.getName(),
				_commerceAccount1.getCommerceAccountId());

			discoveredPriceList = _getCommercePriceList(
				catalog.getGroupId(), _commerceAccount1.getCommerceAccountId(),
				_commerceChannel1.getCommerceChannelId(), 0, _TYPE);

			Assert.assertEquals(
				commerceAccountPriceList.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());

			CommercePriceList commerceAccountAndChannelPriceList =
				_addCommercePriceList(catalog.getGroupId(), false, _TYPE, 1.0);

			_addCommerceQualifierEntryToCommercePriceList(
				commerceAccountAndChannelPriceList.getCommercePriceListId(),
				AccountEntry.class.getName(),
				_commerceAccount1.getCommerceAccountId());

			_addCommerceQualifierEntryToCommercePriceList(
				commerceAccountAndChannelPriceList.getCommercePriceListId(),
				CommerceChannel.class.getName(),
				_commerceChannel1.getCommerceChannelId());

			discoveredPriceList = _getCommercePriceList(
				catalog.getGroupId(), _commerceAccount1.getCommerceAccountId(),
				_commerceChannel1.getCommerceChannelId(), 0, _TYPE);

			Assert.assertEquals(
				commerceAccountAndChannelPriceList.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());
		}
	}

	@Test
	public void testRetrieveCorrectPriceListByHierarchy1() throws Exception {
		frutillaRule.scenario(
			"A price list is qualified by an account and a channel is not " +
				"applicable to the same account on another channel"
		).given(
			"A catalog with a price list on account and channel"
		).when(
			"The price list is discovered given a different channel"
		).then(
			"Only the catalog base price list is returned"
		);

		try (Closeable closeable = _startTimer()) {
			CommerceCatalog catalog =
				_commerceCatalogLocalService.addCommerceCatalog(
					null, RandomTestUtil.randomString(),
					_commerceCurrency.getCode(),
					LocaleUtil.US.getDisplayLanguage(), _serviceContext);

			CommercePriceList commerceAccountAndChannelPriceList =
				_addCommercePriceList(catalog.getGroupId(), false, _TYPE, 1.0);

			_addCommerceQualifierEntryToCommercePriceList(
				commerceAccountAndChannelPriceList.getCommercePriceListId(),
				AccountEntry.class.getName(),
				_commerceAccount1.getCommerceAccountId());

			_addCommerceQualifierEntryToCommercePriceList(
				commerceAccountAndChannelPriceList.getCommercePriceListId(),
				CommerceChannel.class.getName(),
				_commerceChannel1.getCommerceChannelId());

			CommercePriceList discoveredPriceList = _getCommercePriceList(
				catalog.getGroupId(), _commerceAccount1.getCommerceAccountId(),
				RandomTestUtil.nextLong(), 0, _TYPE);

			CommercePriceList commercePriceList =
				_commercePriceListLocalService.
					fetchCatalogBaseCommercePriceList(catalog.getGroupId());

			Assert.assertEquals(
				commercePriceList.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());
		}
	}

	@Test
	public void testRetrieveCorrectPriceListByHierarchy2() throws Exception {
		frutillaRule.scenario(
			"A price list is qualified by an account group and a channel is " +
				"not applicable to the same account on another channel"
		).given(
			"A catalog with a price list on account group and channel"
		).when(
			"The price list is discovered given a different channel"
		).then(
			"Only the catalog base price list is returned"
		);

		try (Closeable closeable = _startTimer()) {
			CommerceCatalog catalog =
				_commerceCatalogLocalService.addCommerceCatalog(
					null, RandomTestUtil.randomString(),
					_commerceCurrency.getCode(),
					LocaleUtil.US.getDisplayLanguage(), _serviceContext);

			CommercePriceList commerceAccountGroupsAndChannelPriceList =
				_addCommercePriceList(catalog.getGroupId(), false, _TYPE, 1.0);

			_addCommerceQualifierEntryToCommercePriceList(
				commerceAccountGroupsAndChannelPriceList.
					getCommercePriceListId(),
				AccountGroup.class.getName(),
				_commerceAccountHelper.getCommerceAccountGroupIds(
					_commerceAccount1.getCommerceAccountId()));

			_addCommerceQualifierEntryToCommercePriceList(
				commerceAccountGroupsAndChannelPriceList.
					getCommercePriceListId(),
				CommerceChannel.class.getName(),
				_commerceChannel1.getCommerceChannelId());

			CommercePriceList discoveredPriceList = _getCommercePriceList(
				catalog.getGroupId(), _commerceAccount1.getCommerceAccountId(),
				RandomTestUtil.nextLong(), 0, _TYPE);

			CommercePriceList commercePriceList =
				_commercePriceListLocalService.
					fetchCatalogBaseCommercePriceList(catalog.getGroupId());

			Assert.assertEquals(
				commercePriceList.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());
		}
	}

	@Test
	public void testRetrievePriceListForAccount() throws Exception {
		frutillaRule.scenario(
			"When multiple price list are defined for the same catalog the " +
				"highest in the hierarchy shall be taken"
		).given(
			"A catalog with multiple price lists"
		).when(
			"The price list is discovered"
		).then(
			"The price list associated to account is retrieved"
		);

		try (Closeable closeable = _startTimer()) {
			CommercePriceList discoveredPriceList = _getCommercePriceList(
				_catalog.getGroupId(), _commerceAccount3.getCommerceAccountId(),
				_commerceChannel2.getCommerceChannelId(), 0, _TYPE);

			Assert.assertEquals(
				_commercePriceList1.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());
		}
	}

	@Test
	public void testRetrievePriceListForAccountGroups() throws Exception {
		frutillaRule.scenario(
			"When multiple price list are defined for the same catalog the " +
				"highest in the hierarchy shall be taken"
		).given(
			"A catalog with multiple price lists"
		).when(
			"The price list is discovered"
		).then(
			"The price list associated to account group is retrieved"
		);

		try (Closeable closeable = _startTimer()) {
			CommercePriceList discoveredPriceList = _getCommercePriceList(
				_catalog.getGroupId(), _commerceAccount5.getCommerceAccountId(),
				_commerceChannel2.getCommerceChannelId(), 0, _TYPE);

			Assert.assertEquals(
				_commercePriceList3.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());
		}
	}

	@Test
	public void testRetrievePriceListForAccountGroupsPlusChannel()
		throws Exception {

		frutillaRule.scenario(
			"When multiple price list are defined for the same catalog the " +
				"highest in the hierarchy shall be taken"
		).given(
			"A catalog with multiple price lists"
		).when(
			"The price list is discovered"
		).then(
			"The price list associated to account group and channel is " +
				"retrieved"
		);

		try (Closeable closeable = _startTimer()) {
			CommercePriceList discoveredPriceList = _getCommercePriceList(
				_catalog.getGroupId(), _commerceAccount4.getCommerceAccountId(),
				_commerceChannel3.getCommerceChannelId(), 0, _TYPE);

			Assert.assertEquals(
				_commercePriceList2.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());
		}
	}

	@Test
	public void testRetrievePriceListForAccountPlusChannel() throws Exception {
		frutillaRule.scenario(
			"When multiple price list are defined for the same catalog the " +
				"highest in the hierarchy shall be taken"
		).given(
			"A catalog with multiple price lists"
		).when(
			"The price list is discovered"
		).then(
			"The price list associated to account and channel is retrieved"
		);

		try (Closeable closeable = _startTimer()) {
			CommercePriceList discoveredPriceList = _getCommercePriceList(
				_catalog.getGroupId(), _commerceAccount2.getCommerceAccountId(),
				_commerceChannel2.getCommerceChannelId(), 0, _TYPE);

			Assert.assertEquals(
				_commercePriceList1.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());
		}
	}

	@Test
	public void testRetrievePriceListForAccountPlusChannelWithFallbackToDefault()
		throws Exception {

		frutillaRule.scenario(
			"When multiple price list are defined for the same catalog the " +
				"highest in the hierarchy shall be taken"
		).given(
			"A catalog with multiple price lists"
		).when(
			"The price list is discovered"
		).then(
			"The price list associated to account and channel is retrieved"
		);

		try (Closeable closeable = _startTimer()) {
			CommercePriceList commercePriceList = _addCommercePriceList(
				_catalog.getGroupId(), false, "price-list", 0);

			_addCommerceQualifierEntryToCommercePriceList(
				commercePriceList.getCommercePriceListId(),
				AccountEntry.class.getName(),
				_commerceAccount2.getCommerceAccountId());

			_addCommerceQualifierEntryToCommercePriceList(
				commercePriceList.getCommercePriceListId(),
				CommerceChannel.class.getName(),
				_commerceChannel1.getCommerceChannelId());

			_addCommerceDefaultSetting(
				_commerceAccount7.getCommerceAccountId(),
				_commerceChannel5.getCommerceChannelId(),
				commercePriceList.getCommercePriceListId());

			CommercePriceList discoveredPriceList1 = _getCommercePriceList(
				_catalog.getGroupId(), _commerceAccount7.getCommerceAccountId(),
				_commerceChannel5.getCommerceChannelId(), 0, _TYPE);

			Assert.assertEquals(
				commercePriceList.getCommercePriceListId(),
				discoveredPriceList1.getCommercePriceListId());

			CommercePriceList discoveredPriceList2 = _getCommercePriceList(
				_catalog.getGroupId(), _commerceAccount7.getCommerceAccountId(),
				RandomTestUtil.nextLong(), 0, _TYPE);

			Assert.assertNotEquals(
				commercePriceList.getCommercePriceListId(),
				discoveredPriceList2.getCommercePriceListId());
		}
	}

	@Test
	public void testRetrievePriceListForChannel() throws Exception {
		frutillaRule.scenario(
			"When multiple price list are defined for the same catalog the " +
				"highest in the hierarchy shall be taken"
		).given(
			"A catalog with multiple price lists"
		).when(
			"The price list is discovered"
		).then(
			"The price list associated to channel is retrieved"
		);

		try (Closeable closeable = _startTimer()) {
			CommercePriceList discoveredPriceList = _getCommercePriceList(
				_catalog.getGroupId(), _commerceAccount6.getCommerceAccountId(),
				_commerceChannel4.getCommerceChannelId(), 0, _TYPE);

			Assert.assertEquals(
				_commercePriceList4.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());
		}
	}

	@Test
	public void testRetrievePriceListForGuestAccount() throws Exception {
		frutillaRule.scenario(
			"When multiple price list are defined for the same catalog the " +
				"highest in the hierarchy shall be taken"
		).given(
			"A catalog with multiple price lists"
		).when(
			"The price list is discovered"
		).then(
			"The price list associated to the guest account is retrieved"
		);

		try (Closeable closeable = _startTimer()) {
			CommercePriceList discoveredPriceList = _getCommercePriceList(
				_catalog.getGroupId(),
				CommerceAccountConstants.ACCOUNT_ID_GUEST,
				_commerceChannel2.getCommerceChannelId(), 0, _TYPE);

			Assert.assertEquals(
				_commercePriceList1.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());
		}
	}

	@Test
	public void testRetrievePriceListUnqualified() throws Exception {
		frutillaRule.scenario(
			"When multiple price list are defined for the same catalog the " +
				"highest in the hierarchy shall be taken"
		).given(
			"A catalog with multiple price lists"
		).when(
			"The price list is discovered"
		).then(
			"The unqualified price list is retrieved"
		);

		try (Closeable closeable = _startTimer()) {
			CommercePriceList discoveredPriceList = _getCommercePriceList(
				_catalog.getGroupId(), _commerceAccount7.getCommerceAccountId(),
				_commerceChannel5.getCommerceChannelId(), 0, _TYPE);

			Assert.assertEquals(
				_commercePriceList5.getCommercePriceListId(),
				discoveredPriceList.getCommercePriceListId());
		}
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	private void _addCommerceDefaultSetting(
			long commerceAccountId, long commerceChannelId,
			long commercePriceListId)
		throws PortalException {

		CommerceDefaultSetting commerceDefaultSetting =
			_commerceDefaultSettingLocalService.addCommerceDefaultSetting(
				_user.getUserId(), RandomTestUtil.randomString(),
				_serviceContext);

		_commerceDefaultSettingRelLocalService.addCommerceDefaultSettingRel(
			_user.getUserId(),
			commerceDefaultSetting.getCommerceDefaultSettingId(),
			AccountEntry.class.getName(), commerceAccountId, 0, "target");

		_commerceDefaultSettingRelLocalService.addCommerceDefaultSettingRel(
			_user.getUserId(),
			commerceDefaultSetting.getCommerceDefaultSettingId(),
			CommerceChannel.class.getName(), commerceChannelId, 0, "target");

		_commerceDefaultSettingRelLocalService.addCommerceDefaultSettingRel(
			_user.getUserId(),
			commerceDefaultSetting.getCommerceDefaultSettingId(),
			CommercePriceList.class.getName(), commercePriceListId, 0,
			"source");
	}

	private CommercePriceList _addCommercePriceList(
			long groupId, boolean catalogBasePriceList, String type,
			double priority)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(
				serviceContext.getCompanyId());

		User user = _userLocalService.getDefaultUser(
			serviceContext.getCompanyId());

		Calendar calendar = CalendarFactoryUtil.getCalendar(user.getTimeZone());

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		return _commercePriceListLocalService.addCommercePriceList(
			null, groupId, user.getUserId(),
			commerceCurrency.getCommerceCurrencyId(), true, type, 0,
			catalogBasePriceList, RandomTestUtil.randomString(), priority,
			calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
			calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
			calendar.get(Calendar.MINUTE), calendar.get(Calendar.MONTH),
			calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR),
			calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
			true, serviceContext);
	}

	private void _addCommerceQualifierEntryToCommercePriceList(
			long commercePriceListId,
			String commerceQualifierEntryTargetClassName,
			long commerceQualifierEntryTargetClassPK)
		throws Exception {

		_commerceQualifierEntryLocalService.addCommerceQualifierEntry(
			_user.getUserId(), CommercePriceList.class.getName(),
			commercePriceListId, commerceQualifierEntryTargetClassName,
			commerceQualifierEntryTargetClassPK);
	}

	private void _addCommerceQualifierEntryToCommercePriceList(
			long commercePriceListId,
			String commerceQualifierEntryTargetClassName,
			long[] commerceQualifierEntryTargetClassPKs)
		throws Exception {

		for (long commerceQualifierEntryTargetClassPK :
				commerceQualifierEntryTargetClassPKs) {

			_addCommerceQualifierEntryToCommercePriceList(
				commercePriceListId, commerceQualifierEntryTargetClassName,
				commerceQualifierEntryTargetClassPK);
		}
	}

	private CommercePriceList _getCommercePriceList(
		long groupId, long commerceAccountId, long commerceChannelId,
		long commerceOrderTypeId, String type) {

		CommerceQualifierSearchContext.Builder
			commerceQualifierSearchContextBuilder =
				new CommerceQualifierSearchContext.Builder();

		CommerceQualifierSearchContext commerceQualifierSearchContext =
			commerceQualifierSearchContextBuilder.setExclusive(
				false
			).setSourceAdditionalAttribute(
				"groupId", groupId
			).setSourceAdditionalAttribute(
				"status", 0
			).setSourceAdditionalAttribute(
				"type_", type
			).setTargetAttribute(
				AccountEntry.class.getName(), commerceAccountId
			).setTargetAttribute(
				AccountGroup.class.getName(),
				_commerceAccountHelper.getCommerceAccountGroupIds(
					commerceAccountId)
			).setTargetAttribute(
				CommerceChannel.class.getName(), commerceChannelId
			).setTargetAttribute(
				CommerceOrderType.class.getName(), commerceOrderTypeId
			).build();

		List<CommercePriceList> commerceQualifierEntriesSourcesByTargets =
			_commerceQualifierEntryLocalService.
				getCommerceQualifierEntriesSourcesByTargets(
					_user.getCompanyId(), CommercePriceList.class,
					commerceQualifierSearchContext);

		return commerceQualifierEntriesSourcesByTargets.get(0);
	}

	private String _getInvokerName() {
		Thread thread = Thread.currentThread();

		StackTraceElement stackTraceElement = thread.getStackTrace()[3];

		return StringBundler.concat(
			stackTraceElement.getClassName(), StringPool.POUND,
			stackTraceElement.getMethodName());
	}

	private Closeable _startTimer() {
		String invokerName = _getInvokerName();

		long startTime = System.currentTimeMillis();

		return () -> System.out.println(
			StringBundler.concat(
				invokerName, " used ", System.currentTimeMillis() - startTime,
				"ms"));
	}

	private static final String _TYPE =
		CommercePriceListConstants.TYPE_PRICE_LIST;

	private static User _user;

	private CommerceCatalog _catalog;
	private CommerceAccount _commerceAccount1;
	private CommerceAccount _commerceAccount2;
	private CommerceAccount _commerceAccount3;
	private CommerceAccount _commerceAccount4;
	private CommerceAccount _commerceAccount5;
	private CommerceAccount _commerceAccount6;
	private CommerceAccount _commerceAccount7;
	private CommerceAccountGroup _commerceAccountGroup;

	@Inject
	private CommerceAccountGroupLocalService _commerceAccountGroupLocalService;

	@Inject
	private CommerceAccountHelper _commerceAccountHelper;

	@Inject
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Inject
	private CommerceCatalogLocalService _commerceCatalogLocalService;

	private CommerceChannel _commerceChannel1;
	private CommerceChannel _commerceChannel2;
	private CommerceChannel _commerceChannel3;
	private CommerceChannel _commerceChannel4;
	private CommerceChannel _commerceChannel5;

	@Inject
	private CommerceChannelLocalService _commerceChannelLocalService;

	private CommerceCurrency _commerceCurrency;

	@Inject
	private CommerceDefaultSettingLocalService
		_commerceDefaultSettingLocalService;

	@Inject
	private CommerceDefaultSettingRelLocalService
		_commerceDefaultSettingRelLocalService;

	private CommercePriceList _commercePriceList1;
	private CommercePriceList _commercePriceList2;
	private CommercePriceList _commercePriceList3;
	private CommercePriceList _commercePriceList4;
	private CommercePriceList _commercePriceList5;

	@Inject
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Inject
	private CommerceQualifierEntryLocalService
		_commerceQualifierEntryLocalService;

	private Group _group;

	@Inject
	private MultiVMPool _multiVMPool;

	private ServiceContext _serviceContext;

	@Inject
	private UserLocalService _userLocalService;

}