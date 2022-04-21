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

package com.liferay.commerce.qualifier.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingRelService;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingService;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryService;
import com.liferay.commerce.qualifier.web.internal.display.context.CommerceDefaultSettingDisplayContext;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = {
		"screen.navigation.category.order:Integer=10",
		"screen.navigation.entry.order:Integer=10"
	},
	service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
)
public class CommerceDefaultSettingDetailsScreenNavigationCategory
	implements ScreenNavigationCategory,
			   ScreenNavigationEntry<CommerceDefaultSetting> {

	@Override
	public String getCategoryKey() {
		return "details";
	}

	@Override
	public String getEntryKey() {
		return "details";
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "details");
	}

	@Override
	public String getScreenNavigationKey() {
		return "commerce.qualifier.default.setting.general";
	}

	@Override
	public boolean isVisible(
		User user, CommerceDefaultSetting commerceDefaultSetting) {

		if (commerceDefaultSetting == null) {
			return false;
		}

		boolean hasPermission = false;

		try {
			hasPermission =
				_commerceDefaultSettingModelResourcePermission.contains(
					PermissionThreadLocal.getPermissionChecker(),
					commerceDefaultSetting.getCommerceDefaultSettingId(),
					ActionKeys.UPDATE);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return hasPermission;
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		CommerceDefaultSettingDisplayContext
			commerceDefaultSettingDisplayContext =
				new CommerceDefaultSettingDisplayContext(
					_commerceDefaultSettingService,
					_commerceDefaultSettingRelService,
					_commerceQualifierEntryService,
					_commerceQualifierMetadataRegistry,
					_commerceDefaultSettingModelResourcePermission,
					httpServletRequest, _portal);

		httpServletRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			commerceDefaultSettingDisplayContext);

		_jspRenderer.renderJSP(
			httpServletRequest, httpServletResponse,
			"/default_settings/details.jsp");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDefaultSettingDetailsScreenNavigationCategory.class);

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
	private JSPRenderer _jspRenderer;

	@Reference
	private Portal _portal;

}