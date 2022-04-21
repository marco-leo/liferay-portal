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

package com.liferay.commerce.price.list.internal.discovery;

import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountGroup;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.price.list.discovery.CommercePriceListDiscovery;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.pricing.constants.CommercePricingConstants;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.qualifier.search.context.CommerceQualifierSearchContext;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = "commerce.price.list.discovery.key=" + CommercePricingConstants.ORDER_BY_HIERARCHY,
	service = CommercePriceListDiscovery.class
)
public class CommercePriceListHierarchyDiscoveryImpl
	implements CommercePriceListDiscovery {

	@Override
	public CommercePriceList getCommercePriceList(
			long groupId, long commerceAccountId, long commerceChannelId,
			long commerceOrderTypeId, String cPInstanceUuid, String type)
		throws PortalException {

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
					CompanyThreadLocal.getCompanyId(), CommercePriceList.class,
					commerceQualifierSearchContext);

		if (commerceQualifierEntriesSourcesByTargets.isEmpty()) {
			return null;
		}

		return commerceQualifierEntriesSourcesByTargets.get(0);

		/*CommercePriceList commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountAndChannelAndOrderTypeId(
					groupId, commerceAccountId, commerceChannelId,
					commerceOrderTypeId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountAndOrderTypeId(
					groupId, commerceAccountId, commerceOrderTypeId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountAndChannelId(
					groupId, commerceAccountId, commerceChannelId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.getCommercePriceListByAccountId(
				groupId, commerceAccountId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		long[] commerceAccountGroupIds =
			_commerceAccountHelper.getCommerceAccountGroupIds(
				commerceAccountId);

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountGroupsAndChannelAndOrderTypeId(
					groupId, commerceAccountGroupIds, commerceChannelId,
					commerceOrderTypeId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountGroupsAndOrderTypeId(
					groupId, commerceAccountGroupIds, commerceOrderTypeId,
					type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountGroupsAndChannelId(
					groupId, commerceAccountGroupIds, commerceChannelId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountGroupIds(
					groupId, commerceAccountGroupIds, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByChannelAndOrderTypeId(
					groupId, commerceChannelId, commerceOrderTypeId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.getCommercePriceListByOrderTypeId(
				groupId, commerceOrderTypeId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.getCommercePriceListByChannelId(
				groupId, commerceChannelId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.getCommercePriceListByUnqualified(
				groupId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		return null;*/
	}

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Reference
	private CommerceQualifierEntryLocalService
		_commerceQualifierEntryLocalService;

}