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

package com.liferay.commerce.qualifier.web.internal.frontend.taglib.data.set.view.table;

import com.liferay.frontend.data.set.view.table.BaseTableFDSView;
import com.liferay.frontend.data.set.view.table.FDSTableSchema;
import com.liferay.frontend.data.set.view.table.FDSTableSchemaBuilder;
import com.liferay.frontend.data.set.view.table.FDSTableSchemaBuilderFactory;
import com.liferay.frontend.data.set.view.table.FDSTableSchemaField;

import java.util.Locale;
import java.util.Map;

/**
 * @author Riccardo Alberti
 */
public class CommerceQualifierEntryFDSView extends BaseTableFDSView {

	public CommerceQualifierEntryFDSView(
		FDSTableSchemaBuilderFactory fdsTableSchemaBuilderFactory,
		Map<String, String> infoColumnNames, boolean showTargetDefault) {

		_fdsTableSchemaBuilderFactory = fdsTableSchemaBuilderFactory;
		_infoColumnNames = infoColumnNames;
		_showTargetDefault = showTargetDefault;
	}

	@Override
	public FDSTableSchema getFDSTableSchema(Locale locale) {
		FDSTableSchemaBuilder fdsTableSchemaBuilder =
			_fdsTableSchemaBuilderFactory.create();

		FDSTableSchemaField nameFDSTableSchemaField =
			fdsTableSchemaBuilder.addFDSTableSchemaField(
				"qualifierEntity.name", "name");

		nameFDSTableSchemaField.setContentRenderer("actionLink");

		for (Map.Entry<String, String> infoColumnName :
				_infoColumnNames.entrySet()) {

			fdsTableSchemaBuilder.addFDSTableSchemaField(
				"qualifierEntity." + infoColumnName.getKey(),
				infoColumnName.getValue());
		}

		if (_showTargetDefault) {
			FDSTableSchemaField targetDefault =
				fdsTableSchemaBuilder.addFDSTableSchemaField(
					"qualifierEntity.targetDefault", "default");

			targetDefault.setContentRenderer("boolean");
		}

		return fdsTableSchemaBuilder.build();
	}

	private final FDSTableSchemaBuilderFactory _fdsTableSchemaBuilderFactory;
	private final Map<String, String> _infoColumnNames;
	private final boolean _showTargetDefault;

}