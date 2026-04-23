/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import express from 'express';
import jsonpath from 'jsonpath';
import cache from 'memory-cache';

import {
	buildRequest,
	mapResultPage,
	mapResultSingleItem,
} from '../../headless-wrapper/external-services.js';
import {getProxyObjectDefinition} from '../../headless-wrapper/liferayServices.js';
import {getConfigByKey} from '../../util/config-util.js';
import {applicationSpecificConfigKeys} from '../../util/constants.js';
import {getServerToken} from '../../util/silent-authorization.js';

const router = express.Router();

function getSubsetByPage(array, pageIndex, pageSize) {
	const startIndex = Number(pageIndex) * Number(pageSize);

	const endIndex = Number(startIndex) + Number(pageSize);

	return array.slice(startIndex, endIndex);
}

router.get(
	'/:objectDefinitionExternalReferenceCode',
	async (request, response) => {
		const {page, pageSize} = request.query;

		try {
			const objectDefinitionExternalReferenceCode =
				request.params.objectDefinitionExternalReferenceCode;

			const cacheKey = `${objectDefinitionExternalReferenceCode}_${page}_${pageSize}`;

			const cachedData = cache.get(cacheKey);

			if (cachedData) {
				response.status(200).json(cachedData);
			}
			else {
				const token = await getServerToken();

				const proxy = await getProxyObjectDefinition(
					objectDefinitionExternalReferenceCode,
					token
				);

				if (
					proxy &&
					proxy.data &&
					proxy.data.items &&
					proxy.data.items.length === 1
				) {
					let {configuration} = proxy.data.items[0];

					configuration = JSON.parse(configuration);

					const methodConfig = configuration.methodMapping.getPageAPI;

					const {
						items,
					} = configuration.methodMapping.getPageAPI.response;

					buildRequest(configuration, 'GetPageAPI', page, pageSize)
						.then(
							(result) => {
								const dataPage = mapResultPage(
									configuration,
									jsonpath.query(result.data, items)
								);

								dataPage.page = page;

								if (
									!methodConfig.parameters['pageIndex'] ||
									!methodConfig.parameters['pageSize']
								) {
									dataPage.items = getSubsetByPage(
										dataPage.items,
										page - 1,
										pageSize
									);
								}

								cache.put(
									cacheKey,
									dataPage,
									getConfigByKey(
										applicationSpecificConfigKeys.PROXY_OBJECTS_CACHE_DURATION
									) * 60000
								);

								response.status(200).json(dataPage);
							},
							(error) => {
								// eslint-disable-next-line no-console
								console.log(error.message);

								response
									.status(error.response.status)
									.json(null);
							}
						)
						.catch((error) => {
							// eslint-disable-next-line no-console
							console.log(error.message);

							response.status(500).json(null);
						});
				}
			}
		}
		catch (error) {
			// eslint-disable-next-line no-console
			console.log(error);

			response.status(500).json(null);
		}
	}
);

router.get(
	'/:objectDefinitionExternalReferenceCode/:externalReferenceCode',
	async (request, response) => {
		const {
			externalReferenceCode,
			objectDefinitionExternalReferenceCode,
		} = request.params;

		try {
			const cacheKey = `${objectDefinitionExternalReferenceCode}_${externalReferenceCode}`;

			const cachedData = cache.get(cacheKey);

			if (cachedData) {
				response.status(200).json(cachedData);
			}
			else {
				const token = await getServerToken();

				const proxy = await getProxyObjectDefinition(
					objectDefinitionExternalReferenceCode,
					token
				);

				if (
					proxy &&
					proxy.data &&
					proxy.data.items &&
					proxy.data.items.length === 1
				) {
					const {configuration} = proxy.data.items[0];

					buildRequest(
						JSON.parse(configuration),
						'GetItemByIdAPI',
						0,
						0,
						request.params
					)
						.then(
							(result) => {
								const dataItem = mapResultSingleItem(
									JSON.parse(configuration),
									result.data
								);

								cache.put(
									cacheKey,
									dataItem,
									getConfigByKey(
										applicationSpecificConfigKeys.PROXY_OBJECTS_CACHE_DURATION
									) * 60000
								);

								response.status(200).json(dataItem);
							},
							(error) => {
								// eslint-disable-next-line no-console
								console.log(error.message);

								response
									.status(error.response.status)
									.json(null);
							}
						)
						.catch((error) => {
							// eslint-disable-next-line no-console
							console.log(error.message);

							response.status(error.response.status).json(null);
						});
				}
			}
		}
		catch (error) {
			// eslint-disable-next-line no-console
			console.log(error.message);

			response.status(500).json(null);
		}
	}
);

router.post(
	'/:objectDefinitionExternalReferenceCode',
	async (request, response) => {
		const objectDefinitionExternalReferenceCode =
			request.params.objectDefinitionExternalReferenceCode;

		try {
			const token = await getServerToken();

			const proxy = await getProxyObjectDefinition(
				objectDefinitionExternalReferenceCode,
				token
			);

			if (
				proxy &&
				proxy.data &&
				proxy.data.items &&
				proxy.data.items.length === 1
			) {
				const {configuration} = proxy.data.items[0];

				buildRequest(
					JSON.parse(configuration),
					'PostItemAPI',
					0,
					0,
					request.params,
					request.body.objectEntry
				)
					.then(
						(result) => {
							cache.clear();

							response
								.status(200)
								.json(
									mapResultSingleItem(
										JSON.parse(configuration),
										result.data
									)
								);
						},
						(error) => {
							// eslint-disable-next-line no-console
							console.log(error.message);

							response.status(error.response.status).json(null);
						}
					)
					.catch((error) => {
						// eslint-disable-next-line no-console
						console.log(error.message);

						response.status(error.response.status).json(null);
					});
			}
		}
		catch (error) {
			// eslint-disable-next-line no-console
			console.log(error.message);

			response.status(500).json(null);
		}
	}
);

router.put(
	'/:objectDefinitionExternalReferenceCode/:externalReferenceCode',
	async (request, response) => {
		const {objectDefinitionExternalReferenceCode} = request.params;

		try {
			const token = await getServerToken();

			const proxy = await getProxyObjectDefinition(
				objectDefinitionExternalReferenceCode,
				token
			);

			if (
				proxy &&
				proxy.data &&
				proxy.data.items &&
				proxy.data.items.length === 1
			) {
				const {configuration} = proxy.data.items[0];

				buildRequest(
					JSON.parse(configuration),
					'UpdateItemByIdAPI',
					0,
					0,
					request.params,
					request.body.objectEntry
				)
					.then(
						(result) => {
							cache.clear();

							response
								.status(200)
								.json(
									mapResultSingleItem(
										JSON.parse(configuration),
										result.data
									)
								);
						},
						(error) => {
							// eslint-disable-next-line no-console
							console.log(error.message);

							response.status(error.response.status).json(null);
						}
					)
					.catch((error) => {
						// eslint-disable-next-line no-console
						console.log(error.message);

						response.status(500).json(null);
					});
			}
		}
		catch (error) {
			// eslint-disable-next-line no-console
			console.log(error.message);

			response.status(500).json(null);
		}
	}
);

router.delete(
	'/:objectDefinitionExternalReferenceCode/:externalReferenceCode',
	async (request, response) => {
		const {objectDefinitionExternalReferenceCode} = request.params;

		try {
			const token = await getServerToken();

			const proxy = await getProxyObjectDefinition(
				objectDefinitionExternalReferenceCode,
				token
			);

			if (
				proxy &&
				proxy.data &&
				proxy.data.items &&
				proxy.data.items.length === 1
			) {
				const {configuration} = proxy.data.items[0];

				buildRequest(
					JSON.parse(configuration),
					'DeleteItemByIdAPI',
					0,
					0,
					request.params
				)
					.then(
						(result) => {
							cache.clear();

							response
								.status(200)
								.json(
									mapResultSingleItem(
										JSON.parse(configuration),
										result.data
									)
								);
						},
						(error) => {
							// eslint-disable-next-line no-console
							console.log(error.message);

							response.status(error.response.status).json(null);
						}
					)
					.catch((error) => {
						// eslint-disable-next-line no-console
						console.log(error.message);

						response.status(500).json(null);
					});
			}
		}
		catch (error) {
			// eslint-disable-next-line no-console
			console.log(error.message);

			response.status(500).json(null);
		}
	}
);

export default router;
