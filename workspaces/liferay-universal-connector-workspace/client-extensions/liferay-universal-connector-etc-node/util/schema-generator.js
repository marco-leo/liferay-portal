/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {mapToLiferayType} from './type-mapping.js';

export function getProxyObjectSchema(
	objectProperties,
	proxyObjectLabel,
	proxyObjectName
) {
	const objectFields = objectProperties.map((property) => {
		const parsedType = mapToLiferayType(property.type);

		return {
			DBType: parsedType.dbType,
			businessType: parsedType.businessType,
			indexed: true,
			indexedAsKeyword: true,
			indexedLanguageId: '',
			label: {
				en_US: property.label,
			},
			name: property.objectDefinitionKey,
			objectFieldSettings: [],
			readOnly: 'true',
			readOnlyConditionExpression: '',
			state: false,
			system: false,
			type: parsedType.dbType,
		};
	});

	const schemaTemplate = {
		accountEntryRestricted: false,
		accountEntryRestrictedObjectFieldName: '',
		active: true,
		defaultLanguageId: 'en_US',
		enableCategorization: false,
		enableComments: false,
		enableObjectEntryHistory: false,
		externalReferenceCode: proxyObjectName,
		label: {
			en_US: proxyObjectLabel,
		},
		name: proxyObjectName,
		objectActions: [],
		objectFields,
		objectLayouts: [],
		objectRelationships: [],
		objectValidationRules: [],
		objectViews: [],
		panelCategoryKey: 'applications_menu.applications.custom.apps',
		parameterRequired: false,
		pluralLabel: {
			en_US: proxyObjectLabel,
		},
		portlet: true,
		scope: 'company',
		status: {
			code: 0,
			label: 'approved',
			label_i18n: 'Approved',
		},
		storageType:
			'function#liferay-universal-connector-object-manager-etc-node',
		system: false,
		titleObjectFieldName: 'id',
	};

	return schemaTemplate;
}
