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

import com.liferay.account.constants.AccountPortletKeys;
import com.liferay.account.model.AccountGroup;
import com.liferay.account.model.AccountGroupTable;
import com.liferay.account.service.AccountGroupLocalService;
import com.liferay.commerce.qualifier.configuration.AccountGroupQualifierConfiguration;
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
	configurationPid = "com.liferay.commerce.qualifier.configuration.AccountGroupQualifierConfiguration",
	enabled = false, immediate = true,
	service = {
		CommerceQualifierMetadata.class, ConfigurationBeanDeclaration.class,
		ModelListener.class
	}
)
public class AccountGroupQualifierMetadata
	extends BaseCommerceQualifierMetadata<AccountGroup> {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return AccountGroupQualifierConfiguration.class;
	}

	@Override
	public Column<?, String> getKeywordsColumn() {
		return AccountGroupTable.INSTANCE.name;
	}

	@Override
	public String getLabel() {
		return "account-group";
	}

	@Override
	public Class<AccountGroup> getModelClass() {
		return AccountGroup.class;
	}

	@Override
	public String getModelClassName() {
		return AccountGroup.class.getName();
	}

	@Override
	public ModelResourcePermission<AccountGroup> getModelResourcePermission() {
		return _accountGroupModelResourcePermission;
	}

	@Override
	public String getMVCRenderCommandName() {
		return "/account_admin/edit_account_entry";
	}

	@Override
	public PersistedModelLocalService getPersistedModelLocalService() {
		return _accountGroupLocalService;
	}

	@Override
	public String getPluralLabel() {
		return "account-groups";
	}

	@Override
	public String getPortletId() {
		return AccountPortletKeys.ACCOUNT_GROUPS_ADMIN;
	}

	@Override
	public Map<String, String[]> getPortletParameters() {
		return HashMapBuilder.put(
			"AccountGroupId", new String[] {"{qualifierEntity.id}"}
		).build();
	}

	@Override
	public PortletProvider.Action getPortletProviderAction() {
		return PortletProvider.Action.VIEW;
	}

	@Override
	public Column<?, Long> getPrimaryKeyColumn() {
		return AccountGroupTable.INSTANCE.accountGroupId;
	}

	@Override
	public String getRESTContextPath() {
		return "/o/headless-commerce-admin-account/v1.0/accountGroups";
	}

	@Override
	public Map<String, String> getRESTInfo(long id) {
		return HashMapBuilder.put(
			"info1",
			() -> {
				AccountGroup accountGroup =
					_accountGroupLocalService.fetchAccountGroup(id);

				return accountGroup.getType();
			}
		).build();
	}

	@Override
	public String getRESTModelName() {
		return "accountGroup";
	}

	@Override
	public String getRESTName(long id) {
		AccountGroup accountGroup = _accountGroupLocalService.fetchAccountGroup(
			id);

		return accountGroup.getName();
	}

	@Override
	public String getRESTSchema() {
		return "[{\"fieldName\": \"name\"}]";
	}

	@Override
	public Table getTable() {
		return AccountGroupTable.INSTANCE;
	}

	@Override
	public boolean isSidePanel() {
		return false;
	}

	@Override
	protected OrderByExpression[] getAdditionalOrderByExpressions(
		Map<String, ?> targetAttributes) {

		return new OrderByExpression[] {
			AccountGroupTable.INSTANCE.accountGroupId.descending()
		};
	}

	@Reference
	private AccountGroupLocalService _accountGroupLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.account.model.AccountGroup)"
	)
	private ModelResourcePermission<AccountGroup>
		_accountGroupModelResourcePermission;

}