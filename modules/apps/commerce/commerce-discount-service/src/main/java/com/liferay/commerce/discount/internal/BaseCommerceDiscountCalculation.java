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

package com.liferay.commerce.discount.internal;

import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountGroup;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.discount.CommerceDiscountCalculation;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.service.CommerceDiscountLocalService;
import com.liferay.commerce.discount.service.CommerceDiscountRelLocalService;
import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.qualifier.search.context.CommerceQualifierSearchContext;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryLocalService;
import com.liferay.commerce.util.CommerceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
public abstract class BaseCommerceDiscountCalculation
	implements CommerceDiscountCalculation {

	protected List<CommerceDiscount> getOrderCommerceDiscountByHierarchy(
			long companyId, CommerceContext commerceContext,
			long commerceOrderTypeId, String target)
		throws PortalException {

		return _getOrderCommerceDiscountByHierarchy(
			companyId, CommerceUtil.getCommerceAccountId(commerceContext),
			commerceContext.getCommerceChannelId(), commerceOrderTypeId,
			target);
	}

	protected List<CommerceDiscount> getProductCommerceDiscountByHierarchy(
			long companyId, CommerceContext commerceContext,
			long commerceOrderTypeId, long cpDefinitionId, long cpInstanceId)
		throws PortalException {

		return _getProductCommerceDiscountByHierarchy(
			companyId, CommerceUtil.getCommerceAccountId(commerceContext),
			commerceContext.getCommerceChannelId(), commerceOrderTypeId,
			cpDefinitionId, cpInstanceId);
	}

	@Reference
	protected CommerceAccountHelper commerceAccountHelper;

	@Reference
	protected CommerceDiscountLocalService commerceDiscountLocalService;

	@Reference
	protected CommerceDiscountRelLocalService commerceDiscountRelLocalService;

	@Reference
	protected CommerceQualifierEntryLocalService
		commerceQualifierEntryLocalService;

	private List<CommerceDiscount> _getOrderCommerceDiscountByHierarchy(
			long companyId, long commerceAccountId, long commerceChannelId,
			long commerceOrderTypeId, String target)
		throws PortalException {

		CommerceQualifierSearchContext.Builder
			commerceQualifierSearchContextBuilder =
				new CommerceQualifierSearchContext.Builder();

		CommerceQualifierSearchContext commerceQualifierSearchContext =
			commerceQualifierSearchContextBuilder.setExclusive(
				false
			).setSourceAdditionalAttribute(
				"active_", true
			).setSourceAdditionalAttribute(
				"target", target
			).setTargetAttribute(
				AccountEntry.class.getName(), commerceAccountId
			).setTargetAttribute(
				AccountGroup.class.getName(),
				commerceAccountHelper.getCommerceAccountGroupIds(
					commerceAccountId)
			).setTargetAttribute(
				CommerceChannel.class.getName(), commerceChannelId
			).setTargetAttribute(
				CommerceOrderType.class.getName(), commerceOrderTypeId
			).build();

		return commerceQualifierEntryLocalService.
			getCommerceQualifierEntriesSourcesByTargets(
				companyId, CommerceDiscount.class,
				commerceQualifierSearchContext);

		/*List<CommerceDiscount> commerceDiscounts =
			commerceDiscountLocalService.
				getAccountAndChannelAndOrderTypeCommerceDiscounts(
					commerceAccountId, commerceChannelId, commerceOrderTypeId,
					target);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.
				getAccountAndOrderTypeCommerceDiscounts(
					commerceAccountId, commerceOrderTypeId, target);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.getAccountAndChannelCommerceDiscounts(
				commerceAccountId, commerceChannelId, target);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.getAccountCommerceDiscounts(
				commerceAccountId, target);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		long[] commerceAccountGroupIds =
			commerceAccountHelper.getCommerceAccountGroupIds(commerceAccountId);

		commerceDiscounts =
			commerceDiscountLocalService.
				getAccountGroupAndChannelAndOrderTypeCommerceDiscount(
					commerceAccountGroupIds, commerceChannelId,
					commerceOrderTypeId, target);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.
				getAccountGroupAndOrderTypeCommerceDiscount(
					commerceAccountGroupIds, commerceOrderTypeId, target);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.
				getAccountGroupAndChannelCommerceDiscount(
					commerceAccountGroupIds, commerceChannelId, target);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.getAccountGroupCommerceDiscount(
				commerceAccountGroupIds, target);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.
				getChannelAndOrderTypeCommerceDiscounts(
					commerceChannelId, commerceOrderTypeId, target);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.getOrderTypeCommerceDiscounts(
				commerceOrderTypeId, target);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.getChannelCommerceDiscounts(
				commerceChannelId, target);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		return commerceDiscountLocalService.getUnqualifiedCommerceDiscounts(
			companyId, target);*/
	}

	private List<CommerceDiscount> _getProductCommerceDiscountByHierarchy(
			long companyId, long commerceAccountId, long commerceChannelId,
			long commerceOrderTypeId, long cpDefinitionId, long cpInstanceId)
		throws PortalException {

		CommerceQualifierSearchContext.Builder
			commerceQualifierSearchContextBuilder =
				new CommerceQualifierSearchContext.Builder();

		CommerceQualifierSearchContext commerceQualifierSearchContext =
			commerceQualifierSearchContextBuilder.setExclusive(
				false
			).setSourceAdditionalAttribute(
				"active_", true
			).setTargetAttribute(
				AccountEntry.class.getName(), commerceAccountId
			).setTargetAttribute(
				AccountGroup.class.getName(),
				commerceAccountHelper.getCommerceAccountGroupIds(
					commerceAccountId)
			).setTargetAttribute(
				CommerceChannel.class.getName(), commerceChannelId
			).setTargetAttribute(
				CommerceOrderType.class.getName(), commerceOrderTypeId
			).build();

		List<CommerceDiscount> commerceQualifierEntriesSourcesByTargets =
			commerceQualifierEntryLocalService.
				getCommerceQualifierEntriesSourcesByTargets(
					companyId, CommerceDiscount.class,
					commerceQualifierSearchContext);

		Stream<CommerceDiscount> stream =
			commerceQualifierEntriesSourcesByTargets.stream();

		return stream.filter(
			commerceDiscount -> {
				int commerceDiscountRelsCount =
					commerceDiscountRelLocalService.
						getCommerceDiscountRelsCount(
							commerceDiscount.getCommerceDiscountId(),
							cpDefinitionId, cpInstanceId,
							commerceDiscount.getTarget());

				if (commerceDiscountRelsCount == 0) {
					return false;
				}

				return true;
			}
		).collect(
			Collectors.toList()
		);

		/*List<CommerceDiscount> commerceDiscounts =
			commerceDiscountLocalService.
				getAccountAndChannelAndOrderTypeCommerceDiscounts(
					commerceAccountId, commerceChannelId, commerceOrderTypeId,
					cpDefinitionId, cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.getAccountAndChannelCommerceDiscounts(
				commerceAccountId, commerceChannelId, cpDefinitionId,
				cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.getAccountCommerceDiscounts(
				commerceAccountId, cpDefinitionId, cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		long[] commerceAccountGroupIds =
			commerceAccountHelper.getCommerceAccountGroupIds(commerceAccountId);

		commerceDiscounts =
			commerceDiscountLocalService.
				getAccountGroupAndChannelAndOrderTypeCommerceDiscount(
					commerceAccountGroupIds, commerceChannelId,
					commerceOrderTypeId, cpDefinitionId, cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.
				getAccountGroupAndChannelCommerceDiscount(
					commerceAccountGroupIds, commerceChannelId, cpDefinitionId,
					cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.getAccountGroupCommerceDiscount(
				commerceAccountGroupIds, cpDefinitionId, cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.
				getChannelAndOrderTypeCommerceDiscounts(
					commerceChannelId, commerceOrderTypeId, cpDefinitionId,
					cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.getOrderTypeCommerceDiscounts(
				commerceOrderTypeId, cpDefinitionId, cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			commerceDiscountLocalService.getChannelCommerceDiscounts(
				commerceChannelId, cpDefinitionId, cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		return commerceDiscountLocalService.getUnqualifiedCommerceDiscounts(
			companyId, cpDefinitionId, cpInstanceId);*/
	}

}