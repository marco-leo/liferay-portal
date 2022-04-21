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

package com.liferay.commerce.qualifier.internal.metadata;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceCurrencyTable;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.qualifier.configuration.CommerceCurrencyQualifierConfiguration;
import com.liferay.commerce.qualifier.metadata.BaseCommerceQualifierMetadata;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadata;
import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.Table;
import com.liferay.petra.sql.dsl.query.sort.OrderByExpression;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	configurationPid = "com.liferay.commerce.qualifier.configuration.CommerceCurrencyQualifierConfiguration",
	enabled = false, immediate = true,
	service = {
		CommerceQualifierMetadata.class, ConfigurationBeanDeclaration.class,
		ModelListener.class
	}
)
public class CommerceCurrencyQualifierMetadata
	extends BaseCommerceQualifierMetadata<CommerceCurrency> {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return CommerceCurrencyQualifierConfiguration.class;
	}

	@Override
	public String getDisplayCategory() {
		return "pricing";
	}

	@Override
	public Column<?, String> getKeywordsColumn() {
		return CommerceCurrencyTable.INSTANCE.name;
	}

	@Override
	public String getLabel() {
		return "currency";
	}

	@Override
	public Class<CommerceCurrency> getModelClass() {
		return CommerceCurrency.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceCurrency.class.getName();
	}

	@Override
	public ModelResourcePermission<CommerceCurrency>
		getModelResourcePermission() {

		return _commercePriceListModelResourcePermission;
	}

	@Override
	public String getMVCRenderCommandName() {
		return "/commerce_price_lists/edit_commerce_price_list";
	}

	@Override
	public PersistedModelLocalService getPersistedModelLocalService() {
		return _commerceCurrencyLocalService;
	}

	@Override
	public String getPluralLabel() {
		return "currencies";
	}

	@Override
	public Map<String, String[]> getPortletParameters() {
		return HashMapBuilder.put(
			"commerceCurrencyId", new String[] {"{qualifierEntity.id}"}
		).build();
	}

	@Override
	public PortletProvider.Action getPortletProviderAction() {
		return PortletProvider.Action.EDIT;
	}

	@Override
	public Column<?, Long> getPrimaryKeyColumn() {
		return CommerceCurrencyTable.INSTANCE.commerceCurrencyId;
	}

	@Override
	public String getRESTContextPath() {
		return "/o/headless-commerce-admin-pricing/v2.0/price-lists?filter=" +
			"catalogBasePriceList eq false";
	}

	@Override
	public String getRESTModelName() {
		return "currency";
	}

	@Override
	public String getRESTName(long id) {
		CommerceCurrency commerceCurrency =
			_commerceCurrencyLocalService.fetchCommerceCurrency(id);

		return commerceCurrency.getName();
	}

	@Override
	public String getRESTSchema() {
		return "[{\"fieldName\": \"name\"}]";
	}

	@Override
	public Table getTable() {
		return CommerceCurrencyTable.INSTANCE;
	}

	@Override
	public boolean isSidePanel() {
		return false;
	}

	@Override
	protected OrderByExpression[] getAdditionalOrderByExpressions(
		Map<String, ?> targetAttributes) {

		return new OrderByExpression[] {
			CommerceCurrencyTable.INSTANCE.primary.ascending(),
			CommerceCurrencyTable.INSTANCE.priority.descending()
		};
	}

	@Reference
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.price.list.model.CommercePriceList)"
	)
	private ModelResourcePermission<CommercePriceList>
		_commercePriceListModelResourcePermission;

}