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

import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadata;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.model.CommerceQualifierEntry;
import com.liferay.commerce.qualifier.rest.dto.v1_0.Qualifier;
import com.liferay.commerce.qualifier.rest.internal.dto.v1_0.converter.QualifierDTOConverter;
import com.liferay.commerce.qualifier.rest.resource.v1_0.QualifierResource;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	properties = "OSGI-INF/liferay/rest/v1_0/qualifier.properties",
	scope = ServiceScope.PROTOTYPE, service = QualifierResource.class
)
public class QualifierResourceImpl extends BaseQualifierResourceImpl {

	@Override
	public void deleteQualifier(Long id) throws Exception {
		_commerceQualifierEntryService.deleteCommerceQualifierEntry(id);
	}

	@Override
	public Page<Qualifier> getQualifiersQualifierEntityName2Page(
			String qualifierMode, String qualifierEntityName1,
			Long qualifierEntityId1, String qualifierEntityName2, String search,
			Pagination pagination)
		throws PortalException {

		Map<String, CommerceQualifierMetadata> commerceQualifiersMetadata =
			_commerceQualifierMetadataRegistry.
				getCommerceQualifiersMetadataRESTModelName();

		CommerceQualifierMetadata commerceQualifierMetadata1 =
			commerceQualifiersMetadata.get(qualifierEntityName1);

		CommerceQualifierMetadata commerceQualifierMetadata2 =
			commerceQualifiersMetadata.get(qualifierEntityName2);

		if ((commerceQualifierMetadata1 == null) ||
			(commerceQualifierMetadata2 == null) ||
			(!Objects.equals(qualifierMode, "bySource") &&
			 !Objects.equals(qualifierMode, "byTarget"))) {

			throw new UnsupportedOperationException();
		}

		if (Objects.equals(qualifierMode, "bySource")) {
			return Page.of(
				TransformUtil.transform(
					_commerceQualifierEntryService.
						getCommerceQualifierEntriesBySource(
							contextCompany.getCompanyId(),
							commerceQualifierMetadata1.getModelClassName(),
							qualifierEntityId1,
							commerceQualifierMetadata2.getModelClassName(),
							search, pagination.getStartPosition(),
							pagination.getEndPosition()),
					commerceQualifierEntry -> _toQualifier(
						commerceQualifierEntry)),
				pagination,
				_commerceQualifierEntryService.
					getCommerceQualifierEntriesBySourceCount(
						contextCompany.getCompanyId(),
						commerceQualifierMetadata1.getModelClassName(),
						qualifierEntityId1,
						commerceQualifierMetadata2.getModelClassName(),
						search));
		}

		return Page.of(
			TransformUtil.transform(
				_commerceQualifierEntryService.
					getCommerceQualifierEntriesByTarget(
						contextCompany.getCompanyId(),
						commerceQualifierMetadata2.getModelClassName(),
						commerceQualifierMetadata1.getModelClassName(),
						qualifierEntityId1, search,
						pagination.getStartPosition(),
						pagination.getEndPosition()),
				commerceQualifierEntry -> _toQualifier(commerceQualifierEntry)),
			pagination,
			_commerceQualifierEntryService.
				getCommerceQualifierEntriesByTargetCount(
					contextCompany.getCompanyId(),
					commerceQualifierMetadata2.getModelClassName(),
					commerceQualifierMetadata1.getModelClassName(),
					qualifierEntityId1, search));
	}

	@Override
	public Qualifier postQualifier(Qualifier qualifier) throws Exception {
		Map<String, CommerceQualifierMetadata> commerceQualifiersMetadata =
			_commerceQualifierMetadataRegistry.
				getCommerceQualifiersMetadataRESTModelName();

		CommerceQualifierMetadata sourceCommerceQualifierMetadata =
			commerceQualifiersMetadata.get(qualifier.getSourceName());

		CommerceQualifierMetadata targetCommerceQualifierMetadata =
			commerceQualifiersMetadata.get(qualifier.getTargetName());

		if ((sourceCommerceQualifierMetadata == null) ||
			(targetCommerceQualifierMetadata == null)) {

			throw new UnsupportedOperationException();
		}

		return _toQualifier(
			_commerceQualifierEntryService.addCommerceQualifierEntry(
				sourceCommerceQualifierMetadata.getModelClassName(),
				qualifier.getSourceId(),
				targetCommerceQualifierMetadata.getModelClassName(),
				qualifier.getTargetId()));
	}

	private Map<String, Map<String, String>> _getActions(
			CommerceQualifierEntry commerceQualifierEntry)
		throws Exception {

		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"DELETE", commerceQualifierEntry.getCommerceQualifierEntryId(),
				"deleteQualifier",
				_commerceQualifierEntryModelResourcePermission)
		).build();
	}

	private Qualifier _toQualifier(
			CommerceQualifierEntry commerceQualifierEntry)
		throws Exception {

		return _qualifierDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(commerceQualifierEntry), _dtoConverterRegistry,
				commerceQualifierEntry.getCommerceQualifierEntryId(),
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.qualifier.model.CommerceQualifierEntry)"
	)
	private ModelResourcePermission<CommerceQualifierEntry>
		_commerceQualifierEntryModelResourcePermission;

	@Reference
	private CommerceQualifierEntryService _commerceQualifierEntryService;

	@Reference
	private CommerceQualifierMetadataRegistry
		_commerceQualifierMetadataRegistry;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private QualifierDTOConverter _qualifierDTOConverter;

}