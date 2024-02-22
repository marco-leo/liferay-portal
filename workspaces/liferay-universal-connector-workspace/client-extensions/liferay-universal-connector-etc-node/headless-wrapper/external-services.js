/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import axios from 'axios';

export function mapEntryToMatchExternalSystemSchema(configuration, entry) {
	const schemaMapping = configuration.objectProperties;

	const mappedDataItem = {};

	schemaMapping.forEach((property) => {
		mappedDataItem[property.value] = entry[property.objectDefinitionKey];
	});

	return mappedDataItem;
}

export function mapResultSingleItem(configuration, item) {
	const schemaMapping = configuration.objectProperties;

	const methodMapping = configuration.methodMapping.getPageAPI.response;

	const mappedDataItem = {
		externalReferenceCode: item[methodMapping.itemId],
		id: item[methodMapping.itemId],
	};

	schemaMapping.forEach((property) => {
		mappedDataItem[property.objectDefinitionKey] = item[property['value']];
	});

	return mappedDataItem;
}

export function mapResultPage(configuration, result) {
	const totalCount = result.length;

	const items = result;

	return {
		items: items.map((item) => mapResultSingleItem(configuration, item)),
		totalCount,
	};
}

function buildGetPageAPI(configuration, page, pageSize) {
	const methodConfig = configuration.getPageAPI;

	let endPointUrl = `${configuration.connectionConfig.host}${methodConfig.path}`;

	if (methodConfig && methodConfig.schema && methodConfig.schema.parameters) {
		const methodParameters = methodConfig.schema.parameters
			? methodConfig.schema.parameters
			: [];

		const methodParametersMapping = Object.keys(
			configuration.methodMapping.getPageAPI.parameters
		).map((parameter) => ({
			key: parameter,
			originalParameter:
				configuration.methodMapping.getPageAPI.parameters[parameter],
		}));

		const queryParameters = methodParameters.filter(
			(parameter) => parameter.in === 'query'
		);

		const pathParameters = methodParameters.filter(
			(parameter) => parameter.in === 'path'
		);

		const mappedQueryParameters = [];

		const mappedPathParameters = [];

		methodParametersMapping.forEach((parameter) => {
			if (parameter) {
				if (queryParameters.length) {
					const param = queryParameters.find(
						(queryParam) =>
							queryParam.name === parameter.originalParameter
					);

					if (param) {
						switch (parameter.key) {
							case 'pageIndex':
								mappedQueryParameters.push(
									`${parameter.originalParameter}=${page}`
								);
								break;

							case 'pageSize':
								mappedQueryParameters.push(
									`${parameter.originalParameter}=${pageSize}`
								);
								break;

							default:
								break;
						}
					}
				}

				if (pathParameters.length) {
					switch (parameter.key) {
						case 'pageIndex':
							mappedPathParameters.push({
								key: parameter.originalParameter,
								value: page,
							});
							break;
						case 'pageSize':
							mappedPathParameters.push({
								key: parameter.originalParameter,
								value: pageSize,
							});
							break;
						default:
							break;
					}
				}
			}
		});

		endPointUrl = mappedQueryParameters.length
			? `${endPointUrl}?${mappedQueryParameters.join('&')}`
			: endPointUrl;

		if (mappedPathParameters.length) {
			mappedPathParameters.forEach((pathParameter) => {
				endPointUrl.replaceAll(
					`{${pathParameter.key}`,
					pathParameter.value
				);
			});
		}
	}

	return endPointUrl;
}

function buildGetItemByIdAPI(configuration, requestParameters) {
	const methodConfig = configuration.getItemByIdAPI;

	const methodParameters = methodConfig.schema.parameters
		? methodConfig.schema.parameters
		: [];

	const methodParametersMapping = Object.keys(
		configuration.methodMapping.getItemById.parameters
	).map((parameter) => ({
		key: parameter,
		originalParameter:
			configuration.methodMapping.getItemById.parameters[parameter],
	}));

	let endPointUrl = `${configuration.connectionConfig.host}${methodConfig.path}`;

	const queryParameters = methodParameters.filter(
		(parameter) => parameter.in === 'query'
	);

	const pathParameters = methodParameters.filter(
		(parameter) => parameter.in === 'path'
	);

	const mappedQueryParameters = [];

	const mappedPathParameters = [];

	queryParameters.forEach((queryParameter) => {
		if (
			methodParametersMapping.find(
				(parameter) => parameter.key === queryParameter.name
			)
		) {
			const mappedParameter = methodParametersMapping.find(
				(parameter) => parameter.key === queryParameter.name
			);

			mappedQueryParameters.push(
				`${mappedParameter.key}=${
					requestParameters[mappedParameter.originalParameter]
				}`
			);
		}
	});

	pathParameters.forEach((pathParameter) => {
		if (
			methodParametersMapping.find(
				(parameter) => parameter.key === pathParameter.name
			)
		) {
			const mappedParameter = methodParametersMapping.find(
				(parameter) => parameter.key === pathParameter.name
			);

			endPointUrl = endPointUrl.replaceAll(
				`{${mappedParameter.key}}`,
				requestParameters[mappedParameter.originalParameter]
			);
		}
	});

	endPointUrl = mappedQueryParameters.length
		? `${endPointUrl}?${mappedQueryParameters.join('&')}`
		: endPointUrl;

	if (mappedPathParameters.length) {
		mappedPathParameters.forEach((pathParameter) => {
			endPointUrl.replaceAll(
				`{${pathParameter.key}`,
				pathParameter.value
			);
		});
	}

	return endPointUrl;
}

function buildDeleteItemByIdAPI(configuration, requestParameters) {
	const methodConfig = configuration.deleteItemByIdAPI;

	const methodParameters = methodConfig.schema.parameters
		? methodConfig.schema.parameters
		: [];

	const methodParametersMapping = Object.keys(
		configuration.methodMapping.deleteItemByIdAPI.parameters
	).map((parameter) => ({
		key: parameter,
		originalParameter:
			configuration.methodMapping.deleteItemByIdAPI.parameters[parameter],
	}));

	let endPointUrl = `${configuration.connectionConfig.host}${methodConfig.path}`;

	const queryParameters = methodParameters.filter(
		(parameter) => parameter.in === 'query'
	);

	const pathParameters = methodParameters.filter(
		(parameter) => parameter.in === 'path'
	);

	const mappedQueryParameters = [];

	const mappedPathParameters = [];

	queryParameters.forEach((queryParameter) => {
		if (
			methodParametersMapping.find(
				(parameter) => parameter.key === queryParameter.name
			)
		) {
			const mappedParameter = methodParametersMapping.find(
				(parameter) => parameter.key === queryParameter.name
			);

			mappedQueryParameters.push(
				`${mappedParameter.key}=${
					requestParameters[mappedParameter.originalParameter]
				}`
			);
		}
	});

	pathParameters.forEach((pathParameter) => {
		if (
			methodParametersMapping.find(
				(parameter) => parameter.key === pathParameter.name
			)
		) {
			const mappedParameter = methodParametersMapping.find(
				(parameter) => parameter.key === pathParameter.name
			);

			endPointUrl = endPointUrl.replaceAll(
				`{${mappedParameter.key}}`,
				requestParameters[mappedParameter.originalParameter]
			);
		}
	});

	endPointUrl = mappedQueryParameters.length
		? `${endPointUrl}?${mappedQueryParameters.join('&')}`
		: endPointUrl;

	if (mappedPathParameters.length) {
		mappedPathParameters.forEach((pathParameter) => {
			endPointUrl.replaceAll(
				`{${pathParameter.key}`,
				pathParameter.value
			);
		});
	}

	return endPointUrl;
}

function buildUpdateItemByIdAPI(configuration, requestParameters) {
	const methodConfig = configuration.updateItemByIdAPI;

	const methodParameters = methodConfig.schema.parameters
		? methodConfig.schema.parameters
		: [];

	const methodParametersMapping = Object.keys(
		configuration.methodMapping.updateItemByIdAPI.parameters
	).map((parameter) => ({
		key: parameter,
		originalParameter:
			configuration.methodMapping.updateItemByIdAPI.parameters[parameter],
	}));

	let endPointUrl = `${configuration.connectionConfig.host}${methodConfig.path}`;

	const queryParameters = methodParameters.filter(
		(parameter) => parameter.in === 'query'
	);

	const pathParameters = methodParameters.filter(
		(parameter) => parameter.in === 'path'
	);

	const mappedQueryParameters = [];

	const mappedPathParameters = [];

	queryParameters.forEach((queryParameter) => {
		if (
			methodParametersMapping.find(
				(parameter) => parameter.key === queryParameter.name
			)
		) {
			const mappedParameter = methodParametersMapping.find(
				(parameter) => parameter.key === queryParameter.name
			);

			mappedQueryParameters.push(
				`${mappedParameter.key}=${
					requestParameters[mappedParameter.originalParameter]
				}`
			);
		}
	});

	pathParameters.forEach((pathParameter) => {
		if (
			methodParametersMapping.find(
				(parameter) => parameter.key === pathParameter.name
			)
		) {
			const mappedParameter = methodParametersMapping.find(
				(parameter) => parameter.key === pathParameter.name
			);

			endPointUrl = endPointUrl.replaceAll(
				`{${mappedParameter.key}}`,
				requestParameters[mappedParameter.originalParameter]
			);
		}
	});

	endPointUrl = mappedQueryParameters.length
		? `${endPointUrl}?${mappedQueryParameters.join('&')}`
		: endPointUrl;

	if (mappedPathParameters.length) {
		mappedPathParameters.forEach((pathParameter) => {
			endPointUrl.replaceAll(
				`{${pathParameter.key}`,
				pathParameter.value
			);
		});
	}

	return endPointUrl;
}

function buildPostItemAPI(configuration) {
	const methodConfig = configuration.postItemAPI;

	const endPointUrl = `${configuration.connectionConfig.host}${methodConfig.path}`;

	return endPointUrl;
}

function buildAuthToken(connectionInformation) {
	switch (connectionInformation.authType) {
		case 'basic':
			return (
				'Basic ' +
				Buffer.from(
					`${connectionInformation.username}:${connectionInformation.password}`
				).toString('base64')
			);

		case 'bearer_token':
			return `Bearer ${connectionInformation.token}`;

		case 'none':
			return ``;

		default:
			break;
	}
}

function buildRequestUrl(
	configuration,
	method,
	page = 0,
	pageSize = 0,
	requestParameters = null
) {
	switch (method) {
		case 'GetPageAPI':
			return buildGetPageAPI(configuration, page, pageSize);

		case 'GetItemByIdAPI':
			return buildGetItemByIdAPI(configuration, requestParameters);

		case 'DeleteItemByIdAPI':
			return buildDeleteItemByIdAPI(configuration, requestParameters);

		case 'PostItemAPI':
			return buildPostItemAPI(configuration);

		case 'UpdateItemByIdAPI':
			return buildUpdateItemByIdAPI(configuration, requestParameters);
		default:
			return null;
	}
}

export function buildRequest(
	configuration,
	method,
	page = 0,
	pageSize = 0,
	requestParameters = null,
	entry = null
) {
	let config = null;
	switch (method) {
		case 'GetPageAPI':
			config = {
				headers: {
					'Accept': 'application/json',
					'Authorization': buildAuthToken(
						configuration.connectionConfig
					),
					'Content-Type': 'application/json',
				},
				method: 'get',
				url: buildRequestUrl(configuration, method, page, pageSize),
			};
			break;

		case 'GetItemByIdAPI':
			config = {
				headers: {
					'Accept': 'application/json',
					'Authorization': buildAuthToken(
						configuration.connectionConfig
					),
					'Content-Type': 'application/json',
				},
				method: 'get',
				url: buildRequestUrl(
					configuration,
					method,
					0,
					0,
					requestParameters
				),
			};

			break;

		case 'DeleteItemByIdAPI':
			config = {
				headers: {
					'Accept': 'application/json',
					'Authorization': buildAuthToken(
						configuration.connectionConfig
					),
					'Content-Type': 'application/json',
				},
				method: 'delete',
				url: buildRequestUrl(
					configuration,
					method,
					0,
					0,
					requestParameters
				),
			};

			break;

		case 'PostItemAPI':
			config = {
				data: mapEntryToMatchExternalSystemSchema(configuration, entry),
				headers: {
					'Accept': 'application/json',
					'Authorization': buildAuthToken(
						configuration.connectionConfig
					),
					'Content-Type': 'application/json',
				},
				method: 'post',
				url: buildRequestUrl(
					configuration,
					method,
					0,
					0,
					requestParameters
				),
			};

			break;

		case 'UpdateItemByIdAPI':
			config = {
				data: mapEntryToMatchExternalSystemSchema(configuration, entry),
				headers: {
					'Accept': 'application/json',
					'Authorization': buildAuthToken(
						configuration.connectionConfig
					),
					'Content-Type': 'application/json',
				},
				method: 'put',
				url: buildRequestUrl(
					configuration,
					method,
					0,
					0,
					requestParameters
				),
			};

			break;

		default:
			break;
	}

	return axios.request(config);
}
