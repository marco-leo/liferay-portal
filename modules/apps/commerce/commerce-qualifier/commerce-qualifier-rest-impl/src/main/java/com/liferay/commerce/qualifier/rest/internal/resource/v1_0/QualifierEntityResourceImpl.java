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

import com.liferay.commerce.qualifier.model.CommerceQualifierEntry;
import com.liferay.commerce.qualifier.rest.dto.v1_0.Qualifier;
import com.liferay.commerce.qualifier.rest.dto.v1_0.QualifierEntity;
import com.liferay.commerce.qualifier.rest.internal.dto.v1_0.converter.QualifierEntityDTOConverter;
import com.liferay.commerce.qualifier.rest.resource.v1_0.QualifierEntityResource;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryService;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldSupport;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	properties = "OSGI-INF/liferay/rest/v1_0/qualifier-entity.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {NestedFieldSupport.class, QualifierEntityResource.class}
)
public class QualifierEntityResourceImpl
	extends BaseQualifierEntityResourceImpl implements NestedFieldSupport {

	@NestedField(parentClass = Qualifier.class, value = "qualifierEntity")
	@Override
	public QualifierEntity getQualifierIdQualifierEntity(Long id)
		throws Exception {

		CommerceQualifierEntry commerceQualifierEntry =
			_commerceQualifierEntryService.getCommerceQualifierEntry(id);

		return _qualifierEntityDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(), null,
				_dtoConverterRegistry,
				commerceQualifierEntry.getCommerceQualifierEntryId(),
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	@Reference
	private CommerceQualifierEntryService _commerceQualifierEntryService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private QualifierEntityDTOConverter _qualifierEntityDTOConverter;

}