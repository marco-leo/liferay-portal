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

package com.liferay.commerce.qualifier.rest.internal.graphql.mutation.v1_0;

import com.liferay.commerce.qualifier.rest.dto.v1_0.DefaultSetting;
import com.liferay.commerce.qualifier.rest.dto.v1_0.DefaultSettingEntity;
import com.liferay.commerce.qualifier.rest.dto.v1_0.Qualifier;
import com.liferay.commerce.qualifier.rest.resource.v1_0.DefaultSettingEntityResource;
import com.liferay.commerce.qualifier.rest.resource.v1_0.DefaultSettingResource;
import com.liferay.commerce.qualifier.rest.resource.v1_0.QualifierResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.batch.engine.resource.VulcanBatchEngineImportTaskResource;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Riccardo Alberti
 * @generated
 */
@Generated("")
public class Mutation {

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

	@GraphQLField
	public DefaultSetting createDefaultSetting(
			@GraphQLName("defaultSetting") DefaultSetting defaultSetting)
		throws Exception {

		return _applyComponentServiceObjects(
			_defaultSettingResourceComponentServiceObjects,
			this::_populateResourceContext,
			defaultSettingResource -> defaultSettingResource.postDefaultSetting(
				defaultSetting));
	}

	@GraphQLField
	public Response createDefaultSettingBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_defaultSettingResourceComponentServiceObjects,
			this::_populateResourceContext,
			defaultSettingResource ->
				defaultSettingResource.postDefaultSettingBatch(
					callbackURL, object));
	}

	@GraphQLField
	public boolean deleteDefaultSetting(@GraphQLName("id") Long id)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_defaultSettingResourceComponentServiceObjects,
			this::_populateResourceContext,
			defaultSettingResource ->
				defaultSettingResource.deleteDefaultSetting(id));

		return true;
	}

	@GraphQLField
	public Response deleteDefaultSettingBatch(
			@GraphQLName("id") Long id,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_defaultSettingResourceComponentServiceObjects,
			this::_populateResourceContext,
			defaultSettingResource ->
				defaultSettingResource.deleteDefaultSettingBatch(
					id, callbackURL, object));
	}

	@GraphQLField
	public DefaultSetting patchDefaultSetting(
			@GraphQLName("id") Long id,
			@GraphQLName("defaultSetting") DefaultSetting defaultSetting)
		throws Exception {

		return _applyComponentServiceObjects(
			_defaultSettingResourceComponentServiceObjects,
			this::_populateResourceContext,
			defaultSettingResource ->
				defaultSettingResource.patchDefaultSetting(id, defaultSetting));
	}

	@GraphQLField
	public boolean deleteDefaultSettingEntity(
			@GraphQLName("defaultSettingEntityId") Long defaultSettingEntityId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_defaultSettingEntityResourceComponentServiceObjects,
			this::_populateResourceContext,
			defaultSettingEntityResource ->
				defaultSettingEntityResource.deleteDefaultSettingEntity(
					defaultSettingEntityId));

		return true;
	}

	@GraphQLField
	public Response deleteDefaultSettingEntityBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_defaultSettingEntityResourceComponentServiceObjects,
			this::_populateResourceContext,
			defaultSettingEntityResource ->
				defaultSettingEntityResource.deleteDefaultSettingEntityBatch(
					callbackURL, object));
	}

	@GraphQLField
	public DefaultSettingEntity patchDefaultSettingEntity(
			@GraphQLName("id") Long id,
			@GraphQLName("defaultSettingEntity") DefaultSettingEntity
				defaultSettingEntity)
		throws Exception {

		return _applyComponentServiceObjects(
			_defaultSettingEntityResourceComponentServiceObjects,
			this::_populateResourceContext,
			defaultSettingEntityResource ->
				defaultSettingEntityResource.patchDefaultSettingEntity(
					id, defaultSettingEntity));
	}

	@GraphQLField
	public DefaultSettingEntity createDefaultSettingIdDefaultSettingEntity(
			@GraphQLName("id") Long id,
			@GraphQLName("defaultSettingEntity") DefaultSettingEntity
				defaultSettingEntity)
		throws Exception {

		return _applyComponentServiceObjects(
			_defaultSettingEntityResourceComponentServiceObjects,
			this::_populateResourceContext,
			defaultSettingEntityResource ->
				defaultSettingEntityResource.
					postDefaultSettingIdDefaultSettingEntity(
						id, defaultSettingEntity));
	}

	@GraphQLField
	public Response createDefaultSettingIdDefaultSettingEntityBatch(
			@GraphQLName("id") Long id,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_defaultSettingEntityResourceComponentServiceObjects,
			this::_populateResourceContext,
			defaultSettingEntityResource ->
				defaultSettingEntityResource.
					postDefaultSettingIdDefaultSettingEntityBatch(
						id, callbackURL, object));
	}

	@GraphQLField
	public Qualifier createQualifier(
			@GraphQLName("qualifier") Qualifier qualifier)
		throws Exception {

		return _applyComponentServiceObjects(
			_qualifierResourceComponentServiceObjects,
			this::_populateResourceContext,
			qualifierResource -> qualifierResource.postQualifier(qualifier));
	}

	@GraphQLField
	public Response createQualifierBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_qualifierResourceComponentServiceObjects,
			this::_populateResourceContext,
			qualifierResource -> qualifierResource.postQualifierBatch(
				callbackURL, object));
	}

	@GraphQLField
	public boolean deleteQualifier(@GraphQLName("id") Long id)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_qualifierResourceComponentServiceObjects,
			this::_populateResourceContext,
			qualifierResource -> qualifierResource.deleteQualifier(id));

		return true;
	}

	@GraphQLField
	public Response deleteQualifierBatch(
			@GraphQLName("id") Long id,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_qualifierResourceComponentServiceObjects,
			this::_populateResourceContext,
			qualifierResource -> qualifierResource.deleteQualifierBatch(
				id, callbackURL, object));
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

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
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

		defaultSettingResource.setVulcanBatchEngineImportTaskResource(
			_vulcanBatchEngineImportTaskResource);
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

		defaultSettingEntityResource.setVulcanBatchEngineImportTaskResource(
			_vulcanBatchEngineImportTaskResource);
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

		qualifierResource.setVulcanBatchEngineImportTaskResource(
			_vulcanBatchEngineImportTaskResource);
	}

	private static ComponentServiceObjects<DefaultSettingResource>
		_defaultSettingResourceComponentServiceObjects;
	private static ComponentServiceObjects<DefaultSettingEntityResource>
		_defaultSettingEntityResourceComponentServiceObjects;
	private static ComponentServiceObjects<QualifierResource>
		_qualifierResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;
	private VulcanBatchEngineImportTaskResource
		_vulcanBatchEngineImportTaskResource;

}