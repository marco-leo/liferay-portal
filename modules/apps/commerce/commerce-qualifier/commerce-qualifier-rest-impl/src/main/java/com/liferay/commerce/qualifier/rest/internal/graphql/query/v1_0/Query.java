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

package com.liferay.commerce.qualifier.rest.internal.graphql.query.v1_0;

import com.liferay.commerce.qualifier.rest.dto.v1_0.DefaultSetting;
import com.liferay.commerce.qualifier.rest.dto.v1_0.DefaultSettingEntity;
import com.liferay.commerce.qualifier.rest.dto.v1_0.Qualifier;
import com.liferay.commerce.qualifier.rest.dto.v1_0.QualifierEntity;
import com.liferay.commerce.qualifier.rest.resource.v1_0.DefaultSettingEntityResource;
import com.liferay.commerce.qualifier.rest.resource.v1_0.DefaultSettingResource;
import com.liferay.commerce.qualifier.rest.resource.v1_0.QualifierEntityResource;
import com.liferay.commerce.qualifier.rest.resource.v1_0.QualifierResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Riccardo Alberti
 * @generated
 */
@Generated("")
public class Query {

	public static void setDefaultSettingResourceComponentServiceObjects(
		ComponentServiceObjects<DefaultSettingResource>
			defaultSettingResourceComponentServiceObjects) {

		_defaultSettingResourceComponentServiceObjects =
			defaultSettingResourceComponentServiceObjects;
	}

	public static void setDefaultSettingEntityResourceComponentServiceObjects(
		ComponentServiceObjects<DefaultSettingEntityResource>
			defaultSettingEntityResourceComponentServiceObjects) {

		_defaultSettingEntityResourceComponentServiceObjects =
			defaultSettingEntityResourceComponentServiceObjects;
	}

	public static void setQualifierResourceComponentServiceObjects(
		ComponentServiceObjects<QualifierResource>
			qualifierResourceComponentServiceObjects) {

		_qualifierResourceComponentServiceObjects =
			qualifierResourceComponentServiceObjects;
	}

	public static void setQualifierEntityResourceComponentServiceObjects(
		ComponentServiceObjects<QualifierEntityResource>
			qualifierEntityResourceComponentServiceObjects) {

		_qualifierEntityResourceComponentServiceObjects =
			qualifierEntityResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {defaultSettings(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public DefaultSettingPage defaultSettings(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_defaultSettingResourceComponentServiceObjects,
			this::_populateResourceContext,
			defaultSettingResource -> new DefaultSettingPage(
				defaultSettingResource.getDefaultSettingsPage(
					search,
					_filterBiFunction.apply(
						defaultSettingResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(
						defaultSettingResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {defaultSetting(id: ___){actions, customFields, defaultSettingEntities, id, name, parameterSettings}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public DefaultSetting defaultSetting(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_defaultSettingResourceComponentServiceObjects,
			this::_populateResourceContext,
			defaultSettingResource -> defaultSettingResource.getDefaultSetting(
				id));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {defaultSettingIdDefaultSettingEntities(id: ___, page: ___, pageSize: ___, search: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public DefaultSettingEntityPage defaultSettingIdDefaultSettingEntities(
			@GraphQLName("id") Long id, @GraphQLName("search") String search,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_defaultSettingEntityResourceComponentServiceObjects,
			this::_populateResourceContext,
			defaultSettingEntityResource -> new DefaultSettingEntityPage(
				defaultSettingEntityResource.
					getDefaultSettingIdDefaultSettingEntitiesPage(
						id, search, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {qualifiersQualifierEntityName2(page: ___, pageSize: ___, qualifierEntityId1: ___, qualifierEntityName1: ___, qualifierEntityName2: ___, qualifierMode: ___, search: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public QualifierPage qualifiersQualifierEntityName2(
			@GraphQLName("qualifierMode") String qualifierMode,
			@GraphQLName("qualifierEntityName1") String qualifierEntityName1,
			@GraphQLName("qualifierEntityId1") Long qualifierEntityId1,
			@GraphQLName("qualifierEntityName2") String qualifierEntityName2,
			@GraphQLName("search") String search,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_qualifierResourceComponentServiceObjects,
			this::_populateResourceContext,
			qualifierResource -> new QualifierPage(
				qualifierResource.getQualifiersQualifierEntityName2Page(
					qualifierMode, qualifierEntityName1, qualifierEntityId1,
					qualifierEntityName2, search,
					Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {qualifierIdQualifierEntity(id: ___){externalReferenceCode, id, info1, info2, info3, info4, info5, name, type}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public QualifierEntity qualifierIdQualifierEntity(
			@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_qualifierEntityResourceComponentServiceObjects,
			this::_populateResourceContext,
			qualifierEntityResource ->
				qualifierEntityResource.getQualifierIdQualifierEntity(id));
	}

	@GraphQLName("DefaultSettingPage")
	public class DefaultSettingPage {

		public DefaultSettingPage(Page defaultSettingPage) {
			actions = defaultSettingPage.getActions();

			items = defaultSettingPage.getItems();
			lastPage = defaultSettingPage.getLastPage();
			page = defaultSettingPage.getPage();
			pageSize = defaultSettingPage.getPageSize();
			totalCount = defaultSettingPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<DefaultSetting> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("DefaultSettingEntityPage")
	public class DefaultSettingEntityPage {

		public DefaultSettingEntityPage(Page defaultSettingEntityPage) {
			actions = defaultSettingEntityPage.getActions();

			items = defaultSettingEntityPage.getItems();
			lastPage = defaultSettingEntityPage.getLastPage();
			page = defaultSettingEntityPage.getPage();
			pageSize = defaultSettingEntityPage.getPageSize();
			totalCount = defaultSettingEntityPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<DefaultSettingEntity> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("QualifierPage")
	public class QualifierPage {

		public QualifierPage(Page qualifierPage) {
			actions = qualifierPage.getActions();

			items = qualifierPage.getItems();
			lastPage = qualifierPage.getLastPage();
			page = qualifierPage.getPage();
			pageSize = qualifierPage.getPageSize();
			totalCount = qualifierPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Qualifier> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("QualifierEntityPage")
	public class QualifierEntityPage {

		public QualifierEntityPage(Page qualifierEntityPage) {
			actions = qualifierEntityPage.getActions();

			items = qualifierEntityPage.getItems();
			lastPage = qualifierEntityPage.getLastPage();
			page = qualifierEntityPage.getPage();
			pageSize = qualifierEntityPage.getPageSize();
			totalCount = qualifierEntityPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<QualifierEntity> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(
			DefaultSettingResource defaultSettingResource)
		throws Exception {

		defaultSettingResource.setContextAcceptLanguage(_acceptLanguage);
		defaultSettingResource.setContextCompany(_company);
		defaultSettingResource.setContextHttpServletRequest(
			_httpServletRequest);
		defaultSettingResource.setContextHttpServletResponse(
			_httpServletResponse);
		defaultSettingResource.setContextUriInfo(_uriInfo);
		defaultSettingResource.setContextUser(_user);
		defaultSettingResource.setGroupLocalService(_groupLocalService);
		defaultSettingResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			DefaultSettingEntityResource defaultSettingEntityResource)
		throws Exception {

		defaultSettingEntityResource.setContextAcceptLanguage(_acceptLanguage);
		defaultSettingEntityResource.setContextCompany(_company);
		defaultSettingEntityResource.setContextHttpServletRequest(
			_httpServletRequest);
		defaultSettingEntityResource.setContextHttpServletResponse(
			_httpServletResponse);
		defaultSettingEntityResource.setContextUriInfo(_uriInfo);
		defaultSettingEntityResource.setContextUser(_user);
		defaultSettingEntityResource.setGroupLocalService(_groupLocalService);
		defaultSettingEntityResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(QualifierResource qualifierResource)
		throws Exception {

		qualifierResource.setContextAcceptLanguage(_acceptLanguage);
		qualifierResource.setContextCompany(_company);
		qualifierResource.setContextHttpServletRequest(_httpServletRequest);
		qualifierResource.setContextHttpServletResponse(_httpServletResponse);
		qualifierResource.setContextUriInfo(_uriInfo);
		qualifierResource.setContextUser(_user);
		qualifierResource.setGroupLocalService(_groupLocalService);
		qualifierResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			QualifierEntityResource qualifierEntityResource)
		throws Exception {

		qualifierEntityResource.setContextAcceptLanguage(_acceptLanguage);
		qualifierEntityResource.setContextCompany(_company);
		qualifierEntityResource.setContextHttpServletRequest(
			_httpServletRequest);
		qualifierEntityResource.setContextHttpServletResponse(
			_httpServletResponse);
		qualifierEntityResource.setContextUriInfo(_uriInfo);
		qualifierEntityResource.setContextUser(_user);
		qualifierEntityResource.setGroupLocalService(_groupLocalService);
		qualifierEntityResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<DefaultSettingResource>
		_defaultSettingResourceComponentServiceObjects;
	private static ComponentServiceObjects<DefaultSettingEntityResource>
		_defaultSettingEntityResourceComponentServiceObjects;
	private static ComponentServiceObjects<QualifierResource>
		_qualifierResourceComponentServiceObjects;
	private static ComponentServiceObjects<QualifierEntityResource>
		_qualifierEntityResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}