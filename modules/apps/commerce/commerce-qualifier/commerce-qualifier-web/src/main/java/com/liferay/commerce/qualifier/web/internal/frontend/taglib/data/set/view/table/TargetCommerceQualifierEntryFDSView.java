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

/**
 * @author Riccardo Alberti
 */
public class TargetCommerceQualifierEntryFDSView extends BaseTableFDSView {

	public TargetCommerceQualifierEntryFDSView(
		FDSTableSchemaBuilderFactory fdsTableSchemaBuilderFactory) {

		_fdsTableSchemaBuilderFactory = fdsTableSchemaBuilderFactory;
	}

	@Override
	public FDSTableSchema getFDSTableSchema(Locale locale) {
		FDSTableSchemaBuilder fdsTableSchemaBuilder =
			_fdsTableSchemaBuilderFactory.create();

		FDSTableSchemaField nameFDSTableSchemaField =
			fdsTableSchemaBuilder.addFDSTableSchemaField(
				"qualifierEntity.name", "name");

		nameFDSTableSchemaField.setContentRenderer("actionLink");

		fdsTableSchemaBuilder.addFDSTableSchemaField(
			"qualifierEntity.info1", "info1");

		fdsTableSchemaBuilder.addFDSTableSchemaField(
			"qualifierEntity.info2", "info2");

		fdsTableSchemaBuilder.addFDSTableSchemaField(
			"qualifierEntity.info3", "info3");

		fdsTableSchemaBuilder.addFDSTableSchemaField(
			"qualifierEntity.info4", "info4");

		fdsTableSchemaBuilder.addFDSTableSchemaField(
			"qualifierEntity.info5", "info5");

		return fdsTableSchemaBuilder.build();
	}

	private final FDSTableSchemaBuilderFactory _fdsTableSchemaBuilderFactory;

}