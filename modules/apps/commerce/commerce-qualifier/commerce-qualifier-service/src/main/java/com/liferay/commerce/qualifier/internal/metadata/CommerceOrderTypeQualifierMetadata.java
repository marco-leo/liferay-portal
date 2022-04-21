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

import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.model.CommerceOrderTypeTable;
import com.liferay.commerce.qualifier.configuration.CommerceOrderTypeQualifierConfiguration;
import com.liferay.commerce.qualifier.metadata.BaseCommerceQualifierMetadata;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadata;
import com.liferay.commerce.service.CommerceOrderTypeLocalService;
import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.Table;
import com.liferay.petra.sql.dsl.query.sort.OrderByExpression;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	configurationPid = "com.liferay.commerce.qualifier.configuration.CommerceOrderTypeQualifierConfiguration",
	enabled = false, immediate = true,
	service = {
		CommerceQualifierMetadata.class, ConfigurationBeanDeclaration.class,
		ModelListener.class
	}
)
public class CommerceOrderTypeQualifierMetadata
	extends BaseCommerceQualifierMetadata<CommerceOrderType> {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return CommerceOrderTypeQualifierConfiguration.class;
	}

	@Override
	public String getDisplayCategory() {
		return "order";
	}

	@Override
	public Column<?, String> getKeywordsColumn() {
		return CommerceOrderTypeTable.INSTANCE.name;
	}

	@Override
	public String getLabel() {
		return "order-type";
	}

	@Override
	public Class<CommerceOrderType> getModelClass() {
		return CommerceOrderType.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceOrderType.class.getName();
	}

	@Override
	public ModelResourcePermission<CommerceOrderType>
		getModelResourcePermission() {

		return _commerceOrderTypeModelResourcePermission;
	}

	@Override
	public String getMVCRenderCommandName() {
		return "/commerce_order_type/edit_commerce_order_type";
	}

	@Override
	public PersistedModelLocalService getPersistedModelLocalService() {
		return _commerceOrderTypeLocalService;
	}

	@Override
	public String getPluralLabel() {
		return "order-types";
	}

	@Override
	public Map<String, String[]> getPortletParameters() {
		return HashMapBuilder.put(
			"commerceOrderTypeId", new String[] {"{qualifierEntity.id}"}
		).build();
	}

	@Override
	public PortletProvider.Action getPortletProviderAction() {
		return PortletProvider.Action.MANAGE;
	}

	@Override
	public Column<?, Long> getPrimaryKeyColumn() {
		return CommerceOrderTypeTable.INSTANCE.commerceOrderTypeId;
	}

	@Override
	public String getRESTContextPath() {
		return "/o/headless-commerce-admin-order/v1.0/order-types";
	}

	@Override
	public Map<String, String> getRESTInfo(long id) {
		return HashMapBuilder.put(
			"info1",
			() -> {
				CommerceOrderType commerceOrderType =
					_commerceOrderTypeLocalService.fetchCommerceOrderType(id);

				return commerceOrderType.getDescription();
			}
		).build();
	}

	@Override
	public String getRESTModelName() {
		return "order-type";
	}

	@Override
	public String getRESTName(long id) {
		CommerceOrderType commerceOrderType =
			_commerceOrderTypeLocalService.fetchCommerceOrderType(id);

		return commerceOrderType.getName(LocaleUtil.getDefault());
	}

	@Override
	public String getRESTSchema() {
		return "[{\"fieldName\": [\"name\", \"LANG\"]}]";
	}

	@Override
	public Table getTable() {
		return CommerceOrderTypeTable.INSTANCE;
	}

	@Override
	public boolean isSidePanel() {
		return false;
	}

	@Override
	protected OrderByExpression[] getAdditionalOrderByExpressions(
		Map<String, ?> targetAttributes) {

		return new OrderByExpression[] {
			CommerceOrderTypeTable.INSTANCE.commerceOrderTypeId.descending()
		};
	}

	@Reference
	private CommerceOrderTypeLocalService _commerceOrderTypeLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceOrderType)"
	)
	private ModelResourcePermission<CommerceOrderType>
		_commerceOrderTypeModelResourcePermission;

}