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

import com.liferay.commerce.qualifier.rest.dto.v1_0.Qualifier;
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

	public static void setQualifierResourceComponentServiceObjects(
		ComponentServiceObjects<QualifierResource>
			qualifierResourceComponentServiceObjects) {

		_qualifierResourceComponentServiceObjects =
			qualifierResourceComponentServiceObjects;
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

	@GraphQLField
	public Qualifier patchQualifier(
			@GraphQLName("id") Long id,
			@GraphQLName("qualifier") Qualifier qualifier)
		throws Exception {

		return _applyComponentServiceObjects(
			_qualifierResourceComponentServiceObjects,
			this::_populateResourceContext,
			qualifierResource -> qualifierResource.patchQualifier(
				id, qualifier));
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