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

package com.liferay.commerce.qualifier.web.internal.display.context;

import com.liferay.commerce.frontend.model.HeaderActionModel;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadata;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel;
import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRelModel;
import com.liferay.commerce.qualifier.parameters.CommerceDefaultSettingParametersJSPContributor;
import com.liferay.commerce.qualifier.parameters.CommerceDefaultSettingParametersJSPContributorRegistry;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingRelService;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingService;
import com.liferay.commerce.qualifier.web.internal.constants.CommerceDefaultSettingPortletKeys;
import com.liferay.commerce.qualifier.web.internal.display.context.helper.CommerceDefaultSettingRequestHelper;
import com.liferay.frontend.data.set.model.FDSActionDropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Riccardo Alberti
 */
public class CommerceDefaultSettingDisplayContext {

	public CommerceDefaultSettingDisplayContext(
		CommerceDefaultSettingService commerceDefaultSettingService,
		CommerceDefaultSettingRelService commerceDefaultSettingRelService,
		CommerceDefaultSettingParametersJSPContributorRegistry
			commerceDefaultSettingParametersJSPContributorRegistry,
		CommerceQualifierMetadataRegistry commerceQualifierMetadataRegistry,
		ModelResourcePermission<CommerceDefaultSetting>
			commerceDefaultSettingModelResourcePermission,
		HttpServletRequest httpServletRequest, Portal portal) {

		_commerceDefaultSettingService = commerceDefaultSettingService;
		_commerceDefaultSettingRelService = commerceDefaultSettingRelService;
		_commerceDefaultSettingParametersJSPContributorRegistry =
			commerceDefaultSettingParametersJSPContributorRegistry;
		_commerceQualifierMetadataRegistry = commerceQualifierMetadataRegistry;
		_commerceDefaultSettingModelResourcePermission =
			commerceDefaultSettingModelResourcePermission;
		_portal = portal;

		_commerceDefaultSettingRequestHelper =
			new CommerceDefaultSettingRequestHelper(httpServletRequest);
	}

	public String getAddCommerceDefaultSettingRenderURL() throws Exception {
		return PortletURLBuilder.createRenderURL(
			_commerceDefaultSettingRequestHelper.getLiferayPortletResponse()
		).setMVCRenderCommandName(
			"/default_settings/add_commerce_default_setting"
		).setWindowState(
			LiferayWindowState.POP_UP
		).buildString();
	}

	public CommerceDefaultSetting getCommerceDefaultSetting()
		throws PortalException {

		long commerceDefaultSettingId = ParamUtil.getLong(
			_commerceDefaultSettingRequestHelper.getRequest(),
			"commerceDefaultSettingId");

		if (commerceDefaultSettingId == 0) {
			return null;
		}

		return _commerceDefaultSettingService.fetchCommerceDefaultSetting(
			commerceDefaultSettingId);
	}

	public CommerceDefaultSettingParametersJSPContributor
			getCommerceDefaultSettingParametersJSPContributor()
		throws PortalException {

		return _commerceDefaultSettingParametersJSPContributorRegistry.
			getCommerceDefaultSettingParametersJSPContributor(
				_getCommerceDefaultSettingParametersJSPContributorKey());
	}

	public String getCommerceDefaultSettingParameterValue(String key)
		throws PortalException {

		CommerceDefaultSetting commerceDefaultSetting =
			getCommerceDefaultSetting();

		UnicodeProperties parameterSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				commerceDefaultSetting.getParameterSettings()
			).build();

		return parameterSettingsUnicodeProperties.getProperty(key);
	}

	public List<CommerceDefaultSettingRel> getCommerceDefaultSettingRels(
			String type)
		throws PortalException {

		CommerceDefaultSetting commerceDefaultSetting =
			getCommerceDefaultSetting();

		if (commerceDefaultSetting == null) {
			return Collections.emptyList();
		}

		return _commerceDefaultSettingRelService.getCommerceDefaultSettingRels(
			commerceDefaultSetting.getCommerceDefaultSettingId(), type);
	}

	public List<CommerceQualifierMetadata> getCommerceQualifiersMetadata() {
		return _commerceQualifierMetadataRegistry.
			getCommerceQualifiersMetadata();
	}

	public CreationMenu getCreationMenu() throws Exception {
		CreationMenu creationMenu = new CreationMenu();

		if (hasAddPermission()) {
			creationMenu.addDropdownItem(
				dropdownItem -> {
					dropdownItem.setHref(
						getAddCommerceDefaultSettingRenderURL());
					dropdownItem.setLabel(
						LanguageUtil.get(
							_commerceDefaultSettingRequestHelper.getRequest(),
							"add-default-setting"));
					dropdownItem.setTarget("modal");
				});
		}

		return creationMenu;
	}

	public PortletURL getEditCommerceDefaultSettingRenderURL() {
		return PortletURLBuilder.create(
			_portal.getControlPanelPortletURL(
				_commerceDefaultSettingRequestHelper.getRequest(),
				CommerceDefaultSettingPortletKeys.
					COMMERCE_DEFAULT_SETTING_PORTLET_NAME,
				PortletRequest.RENDER_PHASE)
		).setMVCRenderCommandName(
			"/default_settings/edit_commerce_default_setting"
		).buildPortletURL();
	}

	public List<FDSActionDropdownItem> getFDSActionDropdownItems()
		throws PortalException {

		return ListUtil.fromArray(
			new FDSActionDropdownItem(
				PortletURLBuilder.create(
					PortletProviderUtil.getPortletURL(
						_commerceDefaultSettingRequestHelper.getRequest(),
						CommerceDefaultSetting.class.getName(),
						PortletProvider.Action.EDIT)
				).setMVCRenderCommandName(
					"/default_settings/edit_commerce_default_setting"
				).setRedirect(
					_commerceDefaultSettingRequestHelper.getCurrentURL()
				).setParameter(
					"commerceDefaultSettingId", "{id}"
				).buildString(),
				"pencil", "edit",
				LanguageUtil.get(
					_commerceDefaultSettingRequestHelper.getRequest(), "edit"),
				"get", null, null),
			new FDSActionDropdownItem(
				null, "trash", "delete",
				LanguageUtil.get(
					_commerceDefaultSettingRequestHelper.getRequest(),
					"delete"),
				"delete", "delete", "headless"),
			new FDSActionDropdownItem(
				_getManagePermissionsURL(), null, "permissions",
				LanguageUtil.get(
					_commerceDefaultSettingRequestHelper.getRequest(),
					"permissions"),
				"get", "permissions", "modal-permissions"));
	}

	public List<HeaderActionModel> getHeaderActionModels() throws Exception {
		List<HeaderActionModel> headerActionModels = new ArrayList<>();

		LiferayPortletResponse liferayPortletResponse =
			_commerceDefaultSettingRequestHelper.getLiferayPortletResponse();

		HeaderActionModel validateHeaderActionModel = new HeaderActionModel(
			null, liferayPortletResponse.getNamespace() + "fm",
			PortletURLBuilder.createActionURL(
				liferayPortletResponse
			).setActionName(
				"/default_settings/edit_commerce_default_setting"
			).buildString(),
			null, "validate");

		headerActionModels.add(validateHeaderActionModel);

		HeaderActionModel saveHeaderActionModel = new HeaderActionModel(
			"btn-primary", liferayPortletResponse.getNamespace() + "fm",
			PortletURLBuilder.createActionURL(
				liferayPortletResponse
			).setActionName(
				"/default_settings/edit_commerce_default_setting"
			).buildString(),
			null, "save");

		headerActionModels.add(saveHeaderActionModel);

		return headerActionModels;
	}

	public boolean hasAddPermission() throws PortalException {
		PortletResourcePermission portletResourcePermission =
			_commerceDefaultSettingModelResourcePermission.
				getPortletResourcePermission();

		return portletResourcePermission.contains(
			_commerceDefaultSettingRequestHelper.getPermissionChecker(), null,
			"ADD_COMMERCE_DEFAULT_SETTING");
	}

	private String _getCommerceDefaultSettingParametersJSPContributorKey()
		throws PortalException {

		List<CommerceDefaultSettingRel> commerceDefaultSettingRels =
			getCommerceDefaultSettingRels("target");

		Stream<CommerceDefaultSettingRel> stream =
			commerceDefaultSettingRels.stream();

		return stream.map(
			CommerceDefaultSettingRelModel::getClassName
		).sorted(
		).distinct(
		).collect(
			Collectors.joining("-")
		);
	}

	private String _getManagePermissionsURL() throws PortalException {
		return PortletURLBuilder.create(
			_portal.getControlPanelPortletURL(
				_commerceDefaultSettingRequestHelper.getRequest(),
				"com_liferay_portlet_configuration_web_portlet_" +
					"PortletConfigurationPortlet",
				ActionRequest.RENDER_PHASE)
		).setMVCPath(
			"/edit_permissions.jsp"
		).setRedirect(
			_commerceDefaultSettingRequestHelper.getCurrentURL()
		).setParameter(
			"modelResource", CommerceDefaultSetting.class.getName()
		).setParameter(
			"modelResourceDescription", "{name}"
		).setParameter(
			"resourcePrimKey", "{id}"
		).setWindowState(
			LiferayWindowState.POP_UP
		).buildString();
	}

	private final ModelResourcePermission<CommerceDefaultSetting>
		_commerceDefaultSettingModelResourcePermission;
	private final CommerceDefaultSettingParametersJSPContributorRegistry
		_commerceDefaultSettingParametersJSPContributorRegistry;
	private final CommerceDefaultSettingRelService
		_commerceDefaultSettingRelService;
	private final CommerceDefaultSettingRequestHelper
		_commerceDefaultSettingRequestHelper;
	private final CommerceDefaultSettingService _commerceDefaultSettingService;
	private final CommerceQualifierMetadataRegistry
		_commerceQualifierMetadataRegistry;
	private final Portal _portal;

}