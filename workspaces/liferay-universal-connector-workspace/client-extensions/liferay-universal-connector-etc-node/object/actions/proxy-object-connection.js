/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import express from 'express';

import {
	deleteObjectDefinition,
	getObjectDefinition,
	postObjectDefinition,
} from '../../headless-wrapper/liferayServices.js';
import {getProxyObjectSchema} from '../../util/schema-generator.js';
import {getServerToken} from '../../util/silent-authorization.js';

const router = express.Router();

router.post('/disconnect', async (request, response) => {
	try {
		const {proxyObjectName} = request.body.objectEntry.values;

		getServerToken()
			.then(async (token) => {
				getObjectDefinition(proxyObjectName, token)
					.then((result) => {
						deleteObjectDefinition(result.data.id, token)
							.then(() => {
								// eslint-disable-next-line no-console
								console.log(
									'Proxy object has been disconnected.'
								);

								response.status(200).json({});
							})
							.catch((error) => {
								// eslint-disable-next-line no-console
								console.log(error.message);

								response
									.status(500)
									.json({error: error.message});
							});
					})
					.catch((error) => {
						// eslint-disable-next-line no-console
						console.log(error.message);

						response.status(500).json({error: error.message});
					});
			})
			.catch((error) => {
				// eslint-disable-next-line no-console
				console.log(error.message);
			});
	}
	catch (error) {
		// eslint-disable-next-line no-console
		console.log(error.message);

		response.status(500).json({error: error.message});
	}
});

router.post('/connect', async (request, response) => {
	try {
		const {
			configuration,
			name,
			proxyObjectName,
		} = request.body.objectEntry.values;

		const parsedConfiguration = JSON.parse(configuration);

		getServerToken()
			.then(async (token) => {
				const objectSchema = getProxyObjectSchema(
					parsedConfiguration.objectProperties,
					name,
					proxyObjectName
				);

				postObjectDefinition(objectSchema, token)
					.then(() => {
						// eslint-disable-next-line no-console
						console.log('Proxy object has been connected.');

						response.status(200).json({});
					})
					.catch((error) => {
						// eslint-disable-next-line no-console
						console.log(error.message);

						response.status(500).json({error: error.message});
					});
			})
			.catch((error) => {
				// eslint-disable-next-line no-console
				console.log(error.message);
			});
	}
	catch (error) {
		// eslint-disable-next-line no-console
		console.log(error.message);

		response.status(500).json({error: error.message});
	}
});

export default router;
