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
import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.commerce.qualifier.rest.dto.v1_0.DefaultSetting;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = "dto.class.name=com.liferay.commerce.qualifier.rest.internal.dto.v1_0.DefaultSetting",
	service = {DefaultSettingDTOConverter.class, DTOConverter.class}
)
public class DefaultSettingDTOConverter
	implements DTOConverter<CommerceDefaultSetting, DefaultSetting> {

	@Override
	public String getContentType() {
		return DefaultSetting.class.getSimpleName();
	}

	@Override
	public DefaultSetting toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceDefaultSetting commerceDefaultSetting =
			_commerceDefaultSettingService.getCommerceDefaultSetting(
				(Long)dtoConverterContext.getId());

		ExpandoBridge expandoBridge = commerceDefaultSetting.getExpandoBridge();

		return new DefaultSetting() {
			{
				actions = dtoConverterContext.getActions();
				customFields = expandoBridge.getAttributes();
				id = commerceDefaultSetting.getCommerceDefaultSettingId();
				name = commerceDefaultSetting.getName();
			}
		};
	}

	@Reference
	private CommerceDefaultSettingService _commerceDefaultSettingService;

	@Reference
	private CommerceQualifierMetadataRegistry
		_commerceQualifierMetadataRegistry;

}