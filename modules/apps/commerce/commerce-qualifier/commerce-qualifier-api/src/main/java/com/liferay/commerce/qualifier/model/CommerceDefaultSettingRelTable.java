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

package com.liferay.commerce.qualifier.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;CommerceDefaultSettingRel&quot; database table.
 *
 * @author Riccardo Alberti
 * @see CommerceDefaultSettingRel
 * @generated
 */
public class CommerceDefaultSettingRelTable
	extends BaseTable<CommerceDefaultSettingRelTable> {

	public static final CommerceDefaultSettingRelTable INSTANCE =
		new CommerceDefaultSettingRelTable();

	public final Column<CommerceDefaultSettingRelTable, Long> mvccVersion =
		createColumn(
			"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<CommerceDefaultSettingRelTable, Long>
		commerceDefaultSettingRelId = createColumn(
			"commerceDefaultSettingRelId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<CommerceDefaultSettingRelTable, Long> companyId =
		createColumn(
			"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommerceDefaultSettingRelTable, Long> userId =
		createColumn("userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommerceDefaultSettingRelTable, String> userName =
		createColumn(
			"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommerceDefaultSettingRelTable, Date> createDate =
		createColumn(
			"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CommerceDefaultSettingRelTable, Date> modifiedDate =
		createColumn(
			"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CommerceDefaultSettingRelTable, Long> classNameId =
		createColumn(
			"classNameId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommerceDefaultSettingRelTable, Long> classPK =
		createColumn("classPK", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CommerceDefaultSettingRelTable, Long>
		commerceDefaultSettingId = createColumn(
			"commerceDefaultSettingId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<CommerceDefaultSettingRelTable, Double> priority =
		createColumn(
			"priority", Double.class, Types.DOUBLE, Column.FLAG_DEFAULT);
	public final Column<CommerceDefaultSettingRelTable, String> type =
		createColumn("type_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private CommerceDefaultSettingRelTable() {
		super("CommerceDefaultSettingRel", CommerceDefaultSettingRelTable::new);
	}

}