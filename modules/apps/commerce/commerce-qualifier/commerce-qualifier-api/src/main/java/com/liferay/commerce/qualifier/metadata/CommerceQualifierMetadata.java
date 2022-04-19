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

package com.liferay.commerce.qualifier.metadata;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.Table;
import com.liferay.petra.sql.dsl.query.sort.OrderByExpression;
import com.liferay.portal.kernel.model.ClassedModel;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.PersistedModelLocalService;

import java.util.Map;

/**
 * @author Riccardo Alberti
 */
public interface CommerceQualifierMetadata<T extends ClassedModel> {

	public String[] getAllowedTargetClassNames();

	public String getExternalReferenceCode(long id);

	public Column<?, String> getKeywordsColumn();

	public String getLabel();

	public Class<T> getModelClass();

	public String getModelClassName();

	public ModelResourcePermission<T> getModelResourcePermission();

	public String getMVCRenderCommandName();

	public OrderByExpression[] getOrderByExpressions();

	public PersistedModelLocalService getPersistedModelLocalService();

	public String getPluralLabel();

	public String getPortletId();

	public Map<String, String[]> getPortletParameters();

	public PortletProvider.Action getPortletProviderAction();

	public Column<?, Long> getPrimaryKeyColumn();

	public String getRESTContextPath();

	public Map<String, String> getRESTInfo(long id);

	public String getRESTModelName();

	public String getRESTName(long id);

	public String getRESTSchema();

	public Table getTable();

	public boolean isSidePanel();

}