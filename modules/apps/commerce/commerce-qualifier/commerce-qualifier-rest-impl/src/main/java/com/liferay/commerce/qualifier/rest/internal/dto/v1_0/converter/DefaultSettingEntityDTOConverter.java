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

package com.liferay.commerce.qualifier.rest.internal.dto.v1_0.converter;

import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel;
import com.liferay.commerce.qualifier.rest.dto.v1_0.DefaultSettingEntity;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingRelService;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = "dto.class.name=com.liferay.commerce.qualifier.rest.internal.dto.v1_0.DefaultSettingEntity",
	service = {DefaultSettingEntityDTOConverter.class, DTOConverter.class}
)
public class DefaultSettingEntityDTOConverter
	implements DTOConverter<CommerceDefaultSettingRel, DefaultSettingEntity> {

	@Override
	public String getContentType() {
		return DefaultSettingEntity.class.getSimpleName();
	}

	@Override
	public DefaultSettingEntity toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceDefaultSettingRel commerceDefaultSettingRel =
			_commerceDefaultSettingRelService.getCommerceDefaultSettingRel(
				(Long)dtoConverterContext.getId());

		return new DefaultSettingEntity() {
			{
				actions = dtoConverterContext.getActions();
				classId = commerceDefaultSettingRel.getClassNameId();
				className = commerceDefaultSettingRel.getClassName();
				defaultSettingId =
					commerceDefaultSettingRel.getCommerceDefaultSettingId();
				id = commerceDefaultSettingRel.getCommerceDefaultSettingRelId();
				priority = commerceDefaultSettingRel.getPriority();
				type = commerceDefaultSettingRel.getType();
			}
		};
	}

	@Reference
	private CommerceDefaultSettingRelService _commerceDefaultSettingRelService;

	@Reference
	private CommerceQualifierMetadataRegistry
		_commerceQualifierMetadataRegistry;

}