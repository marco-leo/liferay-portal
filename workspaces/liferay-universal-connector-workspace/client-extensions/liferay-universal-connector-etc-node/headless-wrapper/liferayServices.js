/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import axios from 'axios';

import {getConfigByKey} from '../util/config-util.js';
import {
	applicationSpecificConfigKeys,
	environmentConfigKeys,
} from '../util/constants.js';

const lxcDXPMainDomain = getConfigByKey(
	environmentConfigKeys.COM_LIFERAY_LXC_DXP_MAIN_DOMAIN
);

const lxcDXPServerProtocol = getConfigByKey(
	environmentConfigKeys.COM_LIFERAY_LXC_DXP_SERVER_PROTOCOL
);

const proxyObjectDefinitionsEndPoint = getConfigByKey(
	applicationSpecificConfigKeys.PROXY_OBJECTS_END_POINT
);

const oauth2JWKSURI = `${lxcDXPServerProtocol}://${lxcDXPMainDomain}`;

export function postObjectDefinition(postData, token) {
	const config = {
		data: JSON.stringify(postData),
		headers: {
			'Accept': 'application/json',
			'Authorization': `Bearer ${token}`,
			'Content-Type': 'application/json',
		},
		method: 'post',
		url: `${oauth2JWKSURI}/o/object-admin/v1.0/object-definitions`,
	};

	return axios.request(config);
}

export function deleteObjectDefinition(proxyObjecId, token) {
	const config = {
		headers: {
			'Accept': 'application/json',
			'Authorization': `Bearer ${token}`,
			'Content-Type': 'application/json',
		},
		method: 'delete',
		url: `${oauth2JWKSURI}/o/object-admin/v1.0/object-definitions/${proxyObjecId}`,
	};

	return axios.request(config);
}

export function getObjectDefinition(externalReferenceCode, token) {
	const config = {
		headers: {
			'Accept': 'application/json',
			'Authorization': `Bearer ${token}`,
			'Content-Type': 'application/json',
		},
		method: 'get',
		url: `${oauth2JWKSURI}/o/object-admin/v1.0/object-definitions/by-external-reference-code/${externalReferenceCode}`,
	};

	return axios.request(config);
}

export function getProxyObjectDefinition(proxyObjectName, token) {
	const config = {
		headers: {
			'Accept': 'application/json',
			'Authorization': `Bearer ${token}`,
			'Content-Type': 'application/json',
		},
		method: 'get',
		url: `${oauth2JWKSURI}${proxyObjectDefinitionsEndPoint}/?filter=proxyObjectName eq '${proxyObjectName}'`,
	};

	return axios.request(config);
}
