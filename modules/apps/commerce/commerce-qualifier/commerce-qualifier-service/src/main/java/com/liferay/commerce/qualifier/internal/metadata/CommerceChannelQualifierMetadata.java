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

import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelTable;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.qualifier.configuration.CommerceChannelQualifierConfiguration;
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
	configurationPid = "com.liferay.commerce.qualifier.configuration.CommerceChannelQualifierConfiguration",
	enabled = false, immediate = true,
	service = {
		CommerceQualifierMetadata.class, ConfigurationBeanDeclaration.class,
		ModelListener.class
	}
)
public class CommerceChannelQualifierMetadata
	extends BaseCommerceQualifierMetadata<CommerceChannel> {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return CommerceChannelQualifierConfiguration.class;
	}

	@Override
	public String getDisplayCategory() {
		return "product";
	}

	@Override
	public Column<?, String> getKeywordsColumn() {
		return CommerceChannelTable.INSTANCE.name;
	}

	@Override
	public String getLabel() {
		return "channel";
	}

	@Override
	public Class<CommerceChannel> getModelClass() {
		return CommerceChannel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceChannel.class.getName();
	}

	@Override
	public ModelResourcePermission<CommerceChannel>
		getModelResourcePermission() {

		return _commerceChannelModelResourcePermission;
	}

	@Override
	public String getMVCRenderCommandName() {
		return "/commerce_channels/edit_commerce_channel";
	}

	@Override
	public PersistedModelLocalService getPersistedModelLocalService() {
		return _commerceChannelLocalService;
	}

	@Override
	public String getPluralLabel() {
		return "channels";
	}

	@Override
	public Map<String, String[]> getPortletParameters() {
		return HashMapBuilder.put(
			"commerceChannelId", new String[] {"{qualifierEntity.id}"}
		).build();
	}

	@Override
	public PortletProvider.Action getPortletProviderAction() {
		return PortletProvider.Action.MANAGE;
	}

	@Override
	public Column<?, Long> getPrimaryKeyColumn() {
		return CommerceChannelTable.INSTANCE.commerceChannelId;
	}

	@Override
	public String getRESTContextPath() {
		return "/o/headless-commerce-admin-channel/v1.0/channels";
	}

	@Override
	public Map<String, String> getRESTInfo(long id) {
		return HashMapBuilder.put(
			"info1",
			() -> {
				CommerceChannel commerceChannel =
					_commerceChannelLocalService.fetchCommerceChannel(id);

				return commerceChannel.getType();
			}
		).build();
	}

	@Override
	public String getRESTModelName() {
		return "channel";
	}

	@Override
	public String getRESTName(long id) {
		CommerceChannel commerceChannel =
			_commerceChannelLocalService.fetchCommerceChannel(id);

		return commerceChannel.getName();
	}

	@Override
	public String getRESTSchema() {
		return "[{\"fieldName\": \"name\"}]";
	}

	@Override
	public Table getTable() {
		return CommerceChannelTable.INSTANCE;
	}

	@Override
	public boolean isSidePanel() {
		return false;
	}

	@Override
	protected OrderByExpression[] getAdditionalOrderByExpressions(
		Map<String, ?> targetAttributes) {

		return new OrderByExpression[] {
			CommerceChannelTable.INSTANCE.commerceChannelId.descending()
		};
	}

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.model.CommerceChannel)"
	)
	private ModelResourcePermission<CommerceChannel>
		_commerceChannelModelResourcePermission;

}