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

package com.liferay.commerce.qualifier.rest.internal.resource.v1_0;

import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel;
import com.liferay.commerce.qualifier.rest.dto.v1_0.DefaultSetting;
import com.liferay.commerce.qualifier.rest.dto.v1_0.DefaultSettingEntity;
import com.liferay.commerce.qualifier.rest.internal.dto.v1_0.converter.DefaultSettingEntityDTOConverter;
import com.liferay.commerce.qualifier.rest.resource.v1_0.DefaultSettingEntityResource;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingRelService;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingService;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldSupport;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Collections;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	properties = "OSGI-INF/liferay/rest/v1_0/default-setting-entity.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {DefaultSettingEntityResource.class, NestedFieldSupport.class}
)
public class DefaultSettingEntityResourceImpl
	extends BaseDefaultSettingEntityResourceImpl implements NestedFieldSupport {

	@Override
	public void deleteDefaultSettingEntity(Long id) throws Exception {
		_commerceDefaultSettingRelService.deleteCommerceDefaultSettingRels(id);
	}

	@NestedField(
		parentClass = DefaultSetting.class, value = "defaultSettingEntities"
	)
	@Override
	public Page<DefaultSettingEntity>
			getDefaultSettingIdDefaultSettingEntitiesPage(
				Long id, String search, Pagination pagination)
		throws Exception {

		// TODO search with finders

		return Page.of(Collections.emptyList());
	}

	@Override
	public DefaultSettingEntity patchDefaultSettingEntity(
			Long id, DefaultSettingEntity defaultSettingEntity)
		throws Exception {

		CommerceDefaultSettingRel commerceDefaultSettingRel =
			_commerceDefaultSettingRelService.updateCommerceDefaultSettingRel(
				id, defaultSettingEntity.getPriority());

		return _toDefaultSettingEntity(commerceDefaultSettingRel);
	}

	@Override
	public DefaultSettingEntity postDefaultSettingIdDefaultSettingEntity(
			Long id, DefaultSettingEntity defaultSettingEntity)
		throws Exception {

		CommerceDefaultSetting commerceDefaultSetting =
			_commerceDefaultSettingService.getCommerceDefaultSetting(id);

		CommerceDefaultSettingRel commerceDefaultSettingRel =
			_commerceDefaultSettingRelService.addCommerceDefaultSettingRel(
				commerceDefaultSetting.getCommerceDefaultSettingId(),
				defaultSettingEntity.getClassName(),
				defaultSettingEntity.getClassId(),
				defaultSettingEntity.getPriority(),
				defaultSettingEntity.getType());

		return _toDefaultSettingEntity(commerceDefaultSettingRel);
	}

	private Map<String, Map<String, String>> _getActions(
			CommerceDefaultSettingRel commerceDefaultSettingRel)
		throws Exception {

		return HashMapBuilder.<String, Map<String, String>>put(
			"get",
			addAction(
				"VIEW", commerceDefaultSettingRel.getCommerceDefaultSettingId(),
				"getDefaultSettingEntity",
				_commerceDefaultSettingModelResourcePermission)
		).put(
			"update",
			addAction(
				"DELETE",
				commerceDefaultSettingRel.getCommerceDefaultSettingId(),
				"deleteDefaultSettingEntity",
				_commerceDefaultSettingModelResourcePermission)
		).build();
	}

	private DefaultSettingEntity _toDefaultSettingEntity(
			CommerceDefaultSettingRel commerceDefaultSettingRel)
		throws Exception {

		return _defaultSettingEntityDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(commerceDefaultSettingRel), _dtoConverterRegistry,
				commerceDefaultSettingRel.getCommerceDefaultSettingRelId(),
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.qualifier.model.CommerceDefaultSetting)"
	)
	private ModelResourcePermission<CommerceDefaultSetting>
		_commerceDefaultSettingModelResourcePermission;

	@Reference
	private CommerceDefaultSettingRelService _commerceDefaultSettingRelService;

	@Reference
	private CommerceDefaultSettingService _commerceDefaultSettingService;

	@Reference
	private DefaultSettingEntityDTOConverter _defaultSettingEntityDTOConverter;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

}