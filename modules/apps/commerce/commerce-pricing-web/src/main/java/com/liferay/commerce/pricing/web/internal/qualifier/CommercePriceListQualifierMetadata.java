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

package com.liferay.commerce.pricing.web.internal.qualifier;

import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.model.CommerceOrderTypeTable;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommercePriceListTable;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelTable;
import com.liferay.commerce.qualifier.metadata.BaseCommerceQualifierMetadata;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadata;
import com.liferay.commerce.qualifier.model.CommerceQualifierEntryTable;
import com.liferay.commerce.qualifier.util.CommerceQualifierUtil;
import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.DSLFunctionFactoryUtil;
import com.liferay.petra.sql.dsl.Table;
import com.liferay.petra.sql.dsl.query.sort.OrderByExpression;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true, service = CommerceQualifierMetadata.class
)
public class CommercePriceListQualifierMetadata
	extends BaseCommerceQualifierMetadata<CommercePriceList> {

	@Override
	public String[] getAllowedTargetClassNames() {
		return new String[] {
			CommerceChannel.class.getName(), CommerceOrderType.class.getName()
		};
	}

	@Override
	public Column<?, String> getKeywordsColumn() {
		return CommercePriceListTable.INSTANCE.name;
	}

	@Override
	public String getLabel() {
		return "price-list";
	}

	@Override
	public Class<CommercePriceList> getModelClass() {
		return CommercePriceList.class;
	}

	@Override
	public String getModelClassName() {
		return CommercePriceList.class.getName();
	}

	@Override
	public ModelResourcePermission<CommercePriceList>
		getModelResourcePermission() {

		return _commercePriceListModelResourcePermission;
	}

	@Override
	public String getMVCRenderCommandName() {
		return "/commerce_price_lists/edit_commerce_price_list";
	}

	@Override
	public OrderByExpression[] getOrderByExpressions() {
		CommerceQualifierEntryTable aliasCommercePriceListCommerceChannelTable =
			CommerceQualifierUtil.getCommerceQualifierTableAlias(
				CommercePriceListTable.INSTANCE.getName(),
				CommerceChannelTable.INSTANCE.getName());

		CommerceQualifierEntryTable
			aliasCommercePriceListCommerceOrderTypeTable =
				CommerceQualifierUtil.getCommerceQualifierTableAlias(
					CommercePriceListTable.INSTANCE.getName(),
					CommerceOrderTypeTable.INSTANCE.getName());

		return new OrderByExpression[] {
			aliasCommercePriceListCommerceChannelTable.targetDefault.
				descending(),
			DSLFunctionFactoryUtil.caseWhenThen(
				aliasCommercePriceListCommerceChannelTable.
					commerceQualifierEntryId.isNotNull(
					).and(
						aliasCommercePriceListCommerceOrderTypeTable.
							commerceQualifierEntryId.isNotNull()
					),
				3
			).whenThen(
				aliasCommercePriceListCommerceChannelTable.
					commerceQualifierEntryId.isNull(
					).and(
						aliasCommercePriceListCommerceOrderTypeTable.
							commerceQualifierEntryId.isNotNull()
					),
				2
			).whenThen(
				aliasCommercePriceListCommerceChannelTable.
					commerceQualifierEntryId.isNotNull(
					).and(
						aliasCommercePriceListCommerceOrderTypeTable.
							commerceQualifierEntryId.isNull()
					),
				1
			).elseEnd(
				0
			).descending(),
			CommercePriceListTable.INSTANCE.priority.descending()
		};
	}

	@Override
	public PersistedModelLocalService getPersistedModelLocalService() {
		return _commercePriceListLocalService;
	}

	@Override
	public String getPluralLabel() {
		return "price-lists";
	}

	@Override
	public Map<String, String[]> getPortletParameters() {
		return HashMapBuilder.put(
			"commercePriceListId", new String[] {"{qualifierEntity.id}"}
		).build();
	}

	@Override
	public PortletProvider.Action getPortletProviderAction() {
		return PortletProvider.Action.MANAGE;
	}

	@Override
	public Column<?, Long> getPrimaryKeyColumn() {
		return CommercePriceListTable.INSTANCE.commercePriceListId;
	}

	@Override
	public String getRESTContextPath() {
		return "/o/headless-commerce-admin-pricing/v2.0/price-lists";
	}

	@Override
	public String getRESTModelName() {
		return "price-list";
	}

	@Override
	public String getRESTName(long id) {
		CommercePriceList commercePriceList =
			_commercePriceListLocalService.fetchCommercePriceList(id);

		return commercePriceList.getName();
	}

	@Override
	public String getRESTSchema() {
		return "[{\"fieldName\": \"name\"}]";
	}

	@Override
	public Table getTable() {
		return CommercePriceListTable.INSTANCE;
	}

	@Override
	public boolean isSidePanel() {
		return true;
	}

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.price.list.model.CommercePriceList)"
	)
	private ModelResourcePermission<CommercePriceList>
		_commercePriceListModelResourcePermission;

}