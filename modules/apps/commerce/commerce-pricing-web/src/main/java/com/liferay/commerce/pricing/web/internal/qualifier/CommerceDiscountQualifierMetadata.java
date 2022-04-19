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

import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountTable;
import com.liferay.commerce.discount.service.CommerceDiscountLocalService;
import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.qualifier.metadata.BaseCommerceQualifierMetadata;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadata;
import com.liferay.petra.sql.dsl.Column;
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
public class CommerceDiscountQualifierMetadata
	extends BaseCommerceQualifierMetadata<CommerceDiscount> {

	@Override
	public String[] getAllowedTargetClassNames() {
		return new String[] {
			CommerceChannel.class.getName(), CommerceOrderType.class.getName()
		};
	}

	@Override
	public Column<?, String> getKeywordsColumn() {
		return CommerceDiscountTable.INSTANCE.title;
	}

	@Override
	public String getLabel() {
		return "discount";
	}

	@Override
	public Class<CommerceDiscount> getModelClass() {
		return CommerceDiscount.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceDiscount.class.getName();
	}

	@Override
	public ModelResourcePermission<CommerceDiscount>
		getModelResourcePermission() {

		return _commerceDiscountModelResourcePermission;
	}

	@Override
	public String getMVCRenderCommandName() {
		return "/commerce_discounts/edit_commerce_discount";
	}

	@Override
	public OrderByExpression[] getOrderByExpressions() {
		return new OrderByExpression[] {
			CommerceDiscountTable.INSTANCE.commerceDiscountId.descending()
		};
	}

	@Override
	public PersistedModelLocalService getPersistedModelLocalService() {
		return _commerceDiscountLocalService;
	}

	@Override
	public String getPluralLabel() {
		return "discounts";
	}

	@Override
	public Map<String, String[]> getPortletParameters() {
		return HashMapBuilder.put(
			"commerceDiscountId", new String[] {"{qualifierEntity.id}"}
		).build();
	}

	@Override
	public PortletProvider.Action getPortletProviderAction() {
		return PortletProvider.Action.MANAGE;
	}

	@Override
	public Column<?, Long> getPrimaryKeyColumn() {
		return CommerceDiscountTable.INSTANCE.commerceDiscountId;
	}

	@Override
	public String getRESTContextPath() {
		return "/o/headless-commerce-admin-pricing/v2.0/discounts";
	}

	@Override
	public String getRESTModelName() {
		return "discount";
	}

	@Override
	public String getRESTName(long id) {
		CommerceDiscount commerceDiscount =
			_commerceDiscountLocalService.fetchCommerceDiscount(id);

		return commerceDiscount.getTitle();
	}

	@Override
	public String getRESTSchema() {
		return "[{\"fieldName\": \"title\"}]";
	}

	@Override
	public Table getTable() {
		return CommerceDiscountTable.INSTANCE;
	}

	@Override
	public boolean isSidePanel() {
		return true;
	}

	@Reference
	private CommerceDiscountLocalService _commerceDiscountLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.discount.model.CommerceDiscount)"
	)
	private ModelResourcePermission<CommerceDiscount>
		_commerceDiscountModelResourcePermission;

}