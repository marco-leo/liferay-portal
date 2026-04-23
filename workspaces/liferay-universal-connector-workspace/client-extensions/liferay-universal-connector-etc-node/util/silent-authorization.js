/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import axios from 'axios';

import {getConfigByKey, getOAuthConfigByKey} from './config-util.js';
import {
	applicationExternalReferenceCodes,
	environmentConfigKeys,
	oauthServerConfigKeys,
} from './constants.js';

const clientId = getOAuthConfigByKey(
	applicationExternalReferenceCodes.OAUTH_SERVER_EXTERNAL_REFERENCE_CODE,
	oauthServerConfigKeys._OAUTH2_HEADLESS_SERVER_CLIENT_ID
);

const clientSecret = getOAuthConfigByKey(
	applicationExternalReferenceCodes.OAUTH_SERVER_EXTERNAL_REFERENCE_CODE,
	oauthServerConfigKeys._OAUTH2_HEADLESS_SERVER_CLIENT_SECRET
);

const lxcDXPMainDomain = getConfigByKey(
	environmentConfigKeys.COM_LIFERAY_LXC_DXP_MAIN_DOMAIN
);

const lxcDXPServerProtocol = getConfigByKey(
	environmentConfigKeys.COM_LIFERAY_LXC_DXP_SERVER_PROTOCOL
);

const uri = getOAuthConfigByKey(
	applicationExternalReferenceCodes.OAUTH_SERVER_EXTERNAL_REFERENCE_CODE,
	oauthServerConfigKeys._OAUTH2_TOKEN_URI
);

const tokenEndpoint = `${lxcDXPServerProtocol}://${lxcDXPMainDomain}${uri}`;

export function getServerToken() {
	const prom = new Promise((resolve, reject) => {
		try {
			const requestBody = {
				client_id: clientId,
				client_secret: clientSecret,
				grant_type: 'client_credentials',
			};

			const headers = {
				'Content-Type': 'application/x-www-form-urlencoded',
			};

			axios
				.post(tokenEndpoint, requestBody, {headers})
				.then((response) => {
					const token = response.data.access_token;

					resolve(token);
				})
				.catch((error) => {
					console.error(`Error obtaining authorization code.`, error);

					reject('No valid token!');
				});
		}
		catch (error) {
			reject('No valid token!');
		}
	});

	return prom;
}
