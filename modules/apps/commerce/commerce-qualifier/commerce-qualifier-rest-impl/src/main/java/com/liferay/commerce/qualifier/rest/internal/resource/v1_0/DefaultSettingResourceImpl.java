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
import com.liferay.commerce.qualifier.rest.dto.v1_0.DefaultSetting;
import com.liferay.commerce.qualifier.rest.dto.v1_0.DefaultSettingEntity;
import com.liferay.commerce.qualifier.rest.internal.dto.v1_0.converter.DefaultSettingDTOConverter;
import com.liferay.commerce.qualifier.rest.resource.v1_0.DefaultSettingResource;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingRelService;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingService;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.io.Serializable;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	properties = "OSGI-INF/liferay/rest/v1_0/default-setting.properties",
	scope = ServiceScope.PROTOTYPE, service = DefaultSettingResource.class
)
public class DefaultSettingResourceImpl extends BaseDefaultSettingResourceImpl {

	@Override
	public void deleteDefaultSetting(Long id) throws Exception {
		_commerceDefaultSettingService.deleteCommerceDefaultSetting(id);
	}

	@Override
	public DefaultSetting getDefaultSetting(Long id) throws Exception {
		return _toDefaultSetting(GetterUtil.getLong(id));
	}

	@Override
	public Page<DefaultSetting> getDefaultSettingsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return Page.of(
			TransformUtil.transform(
				_commerceDefaultSettingService.getCommerceDefaultSettings(
					contextCompany.getCompanyId(), search,
					pagination.getStartPosition(), pagination.getEndPosition()),
				commerceDefaultSetting -> _toDefaultSetting(
					commerceDefaultSetting)),
			pagination,
			_commerceDefaultSettingService.getCommerceDefaultSettingsCount(
				contextCompany.getCompanyId(), search));
	}

	@Override
	public DefaultSetting patchDefaultSetting(
			Long id, DefaultSetting defaultSetting)
		throws Exception {

		return _toDefaultSetting(
			_updateDefaultSetting(
				_commerceDefaultSettingService.getCommerceDefaultSetting(id),
				defaultSetting));
	}

	@Override
	public DefaultSetting postDefaultSetting(DefaultSetting defaultSetting)
		throws Exception {

		CommerceDefaultSetting commerceDefaultSetting =
			_addCommerceDefaultSetting(defaultSetting);

		return _toDefaultSetting(
			commerceDefaultSetting.getCommerceDefaultSettingId());
	}

	private CommerceDefaultSetting _addCommerceDefaultSetting(
			DefaultSetting defaultSetting)
		throws Exception {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceDefaultSetting.class.getName(), contextHttpServletRequest);

		Map<String, ?> customFields = defaultSetting.getCustomFields();

		if ((customFields != null) && !customFields.isEmpty()) {
			serviceContext.setExpandoBridgeAttributes(
				(Map<String, Serializable>)customFields);
		}

		CommerceDefaultSetting commerceDefaultSetting =
			_commerceDefaultSettingService.addCommerceDefaultSetting(
				defaultSetting.getName(), serviceContext);

		return _updateNestedResources(commerceDefaultSetting, defaultSetting);
	}

	private Map<String, Map<String, String>> _getActions(
			CommerceDefaultSetting commerceDefaultSetting)
		throws Exception {

		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"DELETE", commerceDefaultSetting.getCommerceDefaultSettingId(),
				"deleteDefaultSetting",
				_commerceDefaultSettingModelResourcePermission)
		).put(
			"get",
			addAction(
				"VIEW", commerceDefaultSetting.getCommerceDefaultSettingId(),
				"getDefaultSetting",
				_commerceDefaultSettingModelResourcePermission)
		).put(
			"permissions",
			addAction(
				"PERMISSIONS",
				commerceDefaultSetting.getCommerceDefaultSettingId(),
				"patchDefaultSetting",
				_commerceDefaultSettingModelResourcePermission)
		).put(
			"update",
			addAction(
				"UPDATE", commerceDefaultSetting.getCommerceDefaultSettingId(),
				"patchDefaultSetting",
				_commerceDefaultSettingModelResourcePermission)
		).build();
	}

	private DefaultSetting _toDefaultSetting(
			CommerceDefaultSetting commerceDefaultSetting)
		throws Exception {

		return _toDefaultSetting(
			commerceDefaultSetting.getCommerceDefaultSettingId());
	}

	private DefaultSetting _toDefaultSetting(Long commerceDefaultSettingId)
		throws Exception {

		CommerceDefaultSetting commerceDefaultSetting =
			_commerceDefaultSettingService.getCommerceDefaultSetting(
				commerceDefaultSettingId);

		return _defaultSettingDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(commerceDefaultSetting), _dtoConverterRegistry,
				commerceDefaultSettingId,
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	private CommerceDefaultSetting _updateDefaultSetting(
			CommerceDefaultSetting commerceDefaultSetting,
			DefaultSetting defaultSetting)
		throws Exception {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceDefaultSetting.class.getName(), contextHttpServletRequest);

		Map<String, ?> customFields = defaultSetting.getCustomFields();

		if ((customFields != null) && !customFields.isEmpty()) {
			serviceContext.setExpandoBridgeAttributes(
				(Map<String, Serializable>)customFields);
		}

		commerceDefaultSetting =
			_commerceDefaultSettingService.updateCommerceDefaultSetting(
				commerceDefaultSetting.getCommerceDefaultSettingId(),
				GetterUtil.getString(
					defaultSetting.getName(), commerceDefaultSetting.getName()),
				serviceContext);

		return _updateNestedResources(commerceDefaultSetting, defaultSetting);
	}

	private CommerceDefaultSetting _updateNestedResources(
			CommerceDefaultSetting commerceDefaultSetting,
			DefaultSetting defaultSetting)
		throws Exception {

		DefaultSettingEntity[] defaultSettingEntities =
			defaultSetting.getDefaultSettingEntities();

		if (defaultSettingEntities != null) {
			_commerceDefaultSettingRelService.deleteCommerceDefaultSettingRels(
				defaultSetting.getId());

			for (DefaultSettingEntity defaultSettingEntity :
					defaultSettingEntities) {

				_commerceDefaultSettingRelService.addCommerceDefaultSettingRel(
					defaultSetting.getId(), defaultSettingEntity.getClassName(),
					defaultSettingEntity.getClassId(),
					defaultSettingEntity.getPriority(),
					defaultSettingEntity.getType());
			}
		}

		return commerceDefaultSetting;
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
	private DefaultSettingDTOConverter _defaultSettingDTOConverter;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

}