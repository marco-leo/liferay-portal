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
import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountEntryTable;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.commerce.qualifier.configuration.AccountEntryQualifierConfiguration;
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
	configurationPid = "com.liferay.commerce.qualifier.configuration.AccountEntryQualifierConfiguration",
	enabled = false, immediate = true,
	service = {
		CommerceQualifierMetadata.class, ConfigurationBeanDeclaration.class,
		ModelListener.class
	}
)
public class AccountEntryQualifierMetadata
	extends BaseCommerceQualifierMetadata<AccountEntry> {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return AccountEntryQualifierConfiguration.class;
	}

	@Override
	public Column<?, String> getKeywordsColumn() {
		return AccountEntryTable.INSTANCE.name;
	}

	@Override
	public String getLabel() {
		return "account";
	}

	@Override
	public Class<AccountEntry> getModelClass() {
		return AccountEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AccountEntry.class.getName();
	}

	@Override
	public ModelResourcePermission<AccountEntry> getModelResourcePermission() {
		return _accountEntryModelResourcePermission;
	}

	@Override
	public String getMVCRenderCommandName() {
		return "/account_admin/edit_account_entry";
	}

	@Override
	public PersistedModelLocalService getPersistedModelLocalService() {
		return _accountEntryLocalService;
	}

	@Override
	public String getPluralLabel() {
		return "accounts";
	}

	@Override
	public String getPortletId() {
		return AccountPortletKeys.ACCOUNT_ENTRIES_ADMIN;
	}

	@Override
	public Map<String, String[]> getPortletParameters() {
		return HashMapBuilder.put(
			"accountEntryId", new String[] {"{qualifierEntity.id}"}
		).build();
	}

	@Override
	public PortletProvider.Action getPortletProviderAction() {
		return PortletProvider.Action.VIEW;
	}

	@Override
	public Column<?, Long> getPrimaryKeyColumn() {
		return AccountEntryTable.INSTANCE.accountEntryId;
	}

	@Override
	public String getRESTContextPath() {
		return "/o/headless-admin-user/v1.0/accounts";
	}

	@Override
	public Map<String, String> getRESTInfo(long id) {
		return HashMapBuilder.put(
			"info1",
			() -> {
				AccountEntry accountEntry =
					_accountEntryLocalService.fetchAccountEntry(id);

				return accountEntry.getType();
			}
		).build();
	}

	@Override
	public String getRESTModelName() {
		return "account";
	}

	@Override
	public String getRESTName(long id) {
		AccountEntry accountEntry = _accountEntryLocalService.fetchAccountEntry(
			id);

		return accountEntry.getName();
	}

	@Override
	public String getRESTSchema() {
		return "[{\"fieldName\": \"name\"}]";
	}

	@Override
	public Table getTable() {
		return AccountEntryTable.INSTANCE;
	}

	@Override
	public boolean isSidePanel() {
		return false;
	}

	@Override
	protected OrderByExpression[] getAdditionalOrderByExpressions(
		Map<String, ?> targetAttributes) {

		return new OrderByExpression[] {
			AccountEntryTable.INSTANCE.accountEntryId.descending()
		};
	}

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.account.model.AccountEntry)"
	)
	private ModelResourcePermission<AccountEntry>
		_accountEntryModelResourcePermission;

}