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

import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadata;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.model.CommerceQualifierEntry;
import com.liferay.commerce.qualifier.rest.dto.v1_0.Qualifier;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryService;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = "dto.class.name=com.liferay.commerce.qualifier.rest.internal.dto.v1_0.Qualifier",
	service = {DTOConverter.class, QualifierDTOConverter.class}
)
public class QualifierDTOConverter
	implements DTOConverter<CommerceQualifierEntry, Qualifier> {

	@Override
	public String getContentType() {
		return Qualifier.class.getSimpleName();
	}

	@Override
	public Qualifier toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceQualifierEntry commerceQualifierEntry =
			_commerceQualifierEntryService.getCommerceQualifierEntry(
				(Long)dtoConverterContext.getId());

		return new Qualifier() {
			{
				actions = dtoConverterContext.getActions();
				id = commerceQualifierEntry.getCommerceQualifierEntryId();
				sourceId = commerceQualifierEntry.getSourceClassPK();
				sourceName = _getRESTModelClassName(
					commerceQualifierEntry.getSourceClassNameId());
				targetId = commerceQualifierEntry.getTargetClassPK();
				targetName = _getRESTModelClassName(
					commerceQualifierEntry.getTargetClassNameId());
			}
		};
	}

	private String _getRESTModelClassName(long classNameId) throws Exception {
		ClassName className = _classNameLocalService.getClassName(classNameId);

		CommerceQualifierMetadata commerceQualifierMetadata =
			_commerceQualifierMetadataRegistry.getCommerceQualifierMetadata(
				className.getClassName());

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return commerceQualifierMetadata.getRESTModelName();
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CommerceQualifierEntryService _commerceQualifierEntryService;

	@Reference
	private CommerceQualifierMetadataRegistry
		_commerceQualifierMetadataRegistry;

}