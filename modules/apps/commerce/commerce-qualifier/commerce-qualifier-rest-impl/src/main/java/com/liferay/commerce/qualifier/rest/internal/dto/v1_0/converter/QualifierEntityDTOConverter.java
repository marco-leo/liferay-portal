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
import com.liferay.commerce.qualifier.rest.dto.v1_0.QualifierEntity;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = "dto.class.name=com.liferay.commerce.qualifier.rest.internal.dto.v1_0.QualifierEntity",
	service = {DTOConverter.class, QualifierEntityDTOConverter.class}
)
public class QualifierEntityDTOConverter
	implements DTOConverter<CommerceQualifierEntry, QualifierEntity> {

	@Override
	public String getContentType() {
		return QualifierEntity.class.getSimpleName();
	}

	@Override
	public QualifierEntity toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceQualifierEntry commerceQualifierEntry =
			_commerceQualifierEntryService.getCommerceQualifierEntry(
				(Long)dtoConverterContext.getId());

		String qualifierMode = _getQualifierMode(
			dtoConverterContext.getUriInfoOptional());

		ClassName className = _classNameLocalService.getClassName(
			_getClassNameId(commerceQualifierEntry, qualifierMode));

		CommerceQualifierMetadata commerceQualifierMetadata =
			_commerceQualifierMetadataRegistry.getCommerceQualifierMetadata(
				className.getClassName());

		if (commerceQualifierMetadata == null) {
			return null;
		}

		Map<String, String> info = commerceQualifierMetadata.getRESTInfo(
			_getClassPK(commerceQualifierEntry, qualifierMode));

		return new QualifierEntity() {
			{
				externalReferenceCode =
					commerceQualifierMetadata.getExternalReferenceCode(
						_getClassPK(commerceQualifierEntry, qualifierMode));
				id = _getClassPK(commerceQualifierEntry, qualifierMode);
				info1 = info.getOrDefault("info1", StringPool.BLANK);
				info2 = info.getOrDefault("info2", StringPool.BLANK);
				info3 = info.getOrDefault("info3", StringPool.BLANK);
				info4 = info.getOrDefault("info4", StringPool.BLANK);
				info5 = info.getOrDefault("info5", StringPool.BLANK);
				name = commerceQualifierMetadata.getRESTName(
					_getClassPK(commerceQualifierEntry, qualifierMode));
				type = commerceQualifierMetadata.getRESTModelName();
			}
		};
	}

	private long _getClassNameId(
		CommerceQualifierEntry commerceQualifierEntry, String qualifierMode) {

		if (Validator.isBlank(qualifierMode) ||
			Objects.equals(qualifierMode, "bySource")) {

			return commerceQualifierEntry.getTargetClassNameId();
		}

		return commerceQualifierEntry.getSourceClassNameId();
	}

	private long _getClassPK(
		CommerceQualifierEntry commerceQualifierEntry, String qualifierMode) {

		if (Validator.isBlank(qualifierMode) ||
			Objects.equals(qualifierMode, "bySource")) {

			return commerceQualifierEntry.getTargetClassPK();
		}

		return commerceQualifierEntry.getSourceClassPK();
	}

	private String _getQualifierMode(Optional<UriInfo> uriInfoOptional) {
		if (uriInfoOptional.isPresent()) {
			UriInfo uriInfo = uriInfoOptional.get();

			MultivaluedMap<String, String> pathParameters =
				uriInfo.getPathParameters();

			return pathParameters.getFirst("qualifierMode");
		}

		return null;
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CommerceQualifierEntryService _commerceQualifierEntryService;

	@Reference
	private CommerceQualifierMetadataRegistry
		_commerceQualifierMetadataRegistry;

}