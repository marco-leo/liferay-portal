/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export default {
	'com.liferay.lxc.dxp.domains': 'localhost:8080',
	'com.liferay.lxc.dxp.main.domain': 'localhost:8080',
	'com.liferay.lxc.dxp.server.protocol': 'http',
	'configTreePaths': [
		'process.env.LIFERAY_ROUTES_CLIENT_EXTENSION',
		'process.env.LIFERAY_ROUTES_DXP',
	],
	'liferay.oauth.application.external.reference.codes':
		'liferay-universal-connector-oauth-application-server,liferay-universal-connector-oauth-application-user-agent',
	'liferay.universal.connector.oauth.application.server.oauth2.headless.server.client.id':
		'id-521eb03a-9e90-1e3b-fff1-25ad359abd',
	'liferay.universal.connector.oauth.application.server.oauth2.headless.server.client.secret':
		'secret-e468de77-5e8f-e133-7036-4236443673',
	'liferay.universal.connector.oauth.application.server.oauth2.token.uri':
		'/o/oauth2/token',
	'liferay.universal.connector.oauth.application.user.agent.oauth2.jwks.uri':
		'/o/oauth2/jwks',
	'liferay.universal.connector.oauth.application.user.agent.oauth2.user.agent.client.id':
		'id-bcd1fb33-b73f-7791-e853-db2e36b2e9f',
	'proxy.objects.cache.duration': '1',
	'proxy.objects.end.point': '/o/c/t4t14proxyobjectdefinitions',
	'ready.path': '/ready',
	'server.port': 8050,
};
