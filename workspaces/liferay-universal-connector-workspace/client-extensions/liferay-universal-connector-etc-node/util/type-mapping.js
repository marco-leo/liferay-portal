/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const typeMappings = {
	'bigint': 'LongInteger',
	'boolean': 'Boolean',
	'char': 'Text',
	'character': 'Text',
	'character varying': 'LongText',
	'date': 'Date',
	'datetime': 'DateTime',
	'decimal': 'PrecisionDecimal',
	'double precision': 'Decimal',
	'enum': 'Text',
	'int': 'Integer',
	'integer': 'Integer',
	'longtext': 'LongText',
	'numeric': 'LongInteger',
	'real': 'Decimal',
	'smallint': 'Integer',
	'string': 'LongText',
	'text': 'LongText',
	'time': 'DateTime',
	'timestamp': 'Date',
	'timestamptz': 'DateTime',
	'uuid': 'Text',
	'varchar': 'LongText',
};
const liferayTypes = {
	Boolean: {
		businessType: 'Boolean',
		dbType: 'Boolean',
		type: 'Boolean',
	},
	Date: {
		businessType: 'Date',
		dbType: 'Date',
		objectFieldSettings: [{name: 'timeStorage', value: 'convertToUTC'}],
		type: 'Date',
	},
	DateTime: {
		businessType: 'DateTime',
		dbType: 'DateTime',
		objectFieldSettings: [{name: 'timeStorage', value: 'convertToUTC'}],
		type: 'DateTime',
	},
	Decimal: {
		businessType: 'Decimal',
		dbType: 'Double',
		type: 'Double',
	},
	Integer: {
		businessType: 'Integer',
		dbType: 'Integer',
		type: 'Integer',
	},
	LongInteger: {
		businessType: 'LongInteger',
		dbType: 'Long',
		type: 'Long',
	},
	LongText: {
		businessType: 'LongText',
		dbType: 'Clob',
		type: 'Clob',
	},
	PrecisionDecimal: {
		businessType: 'PrecisionDecimal',
		dbType: 'BigDecimal',
		type: 'BigDecimal',
	},
	RichText: {
		businessType: 'RichText',
		dbType: 'Clob',
		type: 'Clob',
	},
	Text: {
		businessType: 'Text',
		dbType: 'String',
		type: 'String',
	},
};

const systemFields = {
	createDate: 'yes',
	creator: 'yes',
	externalReferenceCode: 'yes',
	id: 'yes',
	modifiedDate: 'yes',
	status: 'yes',
};

export function isSystemField(fieldName) {
	return fieldName in systemFields;
}

export function mapToLiferayType(type) {
	return liferayTypes[typeMappings[type.toLowerCase()]];
}

export function convertDBColumnTypeToLiferayType(dbColumnType) {
	if (dbColumnType.toLowerCase() in typeMappings) {
		const liferayType = liferayTypes[typeMappings[dbColumnType]];

		liferayType['parsed'] = true;

		return liferayType;
	}
	else {
		const defaultType = liferayTypes['Text'];

		defaultType['parsed'] = false;

		return defaultType;
	}
}
