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

package com.liferay.commerce.qualifier.web.internal.portlet;

import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingRelService;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingService;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryService;
import com.liferay.commerce.qualifier.web.internal.constants.CommerceDefaultSettingPortletKeys;
import com.liferay.commerce.qualifier.web.internal.display.context.CommerceDefaultSettingDisplayContext;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.preferences-owned-by-group=false",
		"com.liferay.portlet.preferences-unique-per-layout=false",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.scopeable=true",
		"javax.portlet.display-name=Default Settings",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CommerceDefaultSettingPortletKeys.COMMERCE_DEFAULT_SETTING_PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.version=3.0"
	},
	service = {CommerceDefaultSettingPortlet.class, Portlet.class}
)
public class CommerceDefaultSettingPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			renderRequest);

		CommerceDefaultSettingDisplayContext
			commerceDefaultSettingDisplayContext =
				new CommerceDefaultSettingDisplayContext(
					_commerceDefaultSettingService,
					_commerceDefaultSettingRelService,
					_commerceQualifierEntryService,
					_commerceQualifierMetadataRegistry,
					_commerceDefaultSettingModelResourcePermission,
					httpServletRequest, _portal);

		renderRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			commerceDefaultSettingDisplayContext);

		super.render(renderRequest, renderResponse);
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
	private CommerceQualifierEntryService _commerceQualifierEntryService;

	@Reference
	private CommerceQualifierMetadataRegistry
		_commerceQualifierMetadataRegistry;

	@Reference
	private Portal _portal;

}