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

package com.liferay.commerce.qualifier.screen.navigator;

import com.liferay.commerce.qualifier.display.context.CommerceQualifiersDisplayContext;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryService;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
public abstract class BaseCommerceQualifierScreenNavigationCategory<T>
	implements ScreenNavigationCategory, ScreenNavigationEntry<T> {

	@Override
	public String getCategoryKey() {
		return "qualifiers";
	}

	@Override
	public String getEntryKey() {
		return "qualifiers";
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "eligibility");
	}

	public abstract String getSourceClassPKParamName();

	public abstract String getSourceName();

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		httpServletRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			new CommerceQualifiersDisplayContext(
				commerceQualifierEntryService,
				commerceQualifierMetadataRegistry, getSourceName(),
				ParamUtil.getLong(
					httpServletRequest, getSourceClassPKParamName()),
				httpServletRequest));

		jspRenderer.renderJSP(
			servletContext, httpServletRequest, httpServletResponse,
			"/qualifiers/qualifiers.jsp");
	}

	@Reference
	protected CommerceQualifierEntryService commerceQualifierEntryService;

	@Reference
	protected CommerceQualifierMetadataRegistry
		commerceQualifierMetadataRegistry;

	@Reference
	protected JSPRenderer jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.qualifier.web)"
	)
	protected ServletContext servletContext;

}