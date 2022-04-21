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

package com.liferay.commerce.qualifier.display.context;

import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadata;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryService;
import com.liferay.frontend.data.set.model.FDSActionDropdownItem;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Riccardo Alberti
 */
public class CommerceQualifiersDisplayContext
	extends BaseCommerceQualifierDisplayContext {

	public CommerceQualifiersDisplayContext(
		CommerceQualifierEntryService commerceQualifierEntryService,
		CommerceQualifierMetadataRegistry commerceQualifierMetadataRegistry,
		String sourceClassName, long sourceClassPK,
		HttpServletRequest httpServletRequest) {

		super(
			commerceQualifierEntryService, commerceQualifierMetadataRegistry,
			sourceClassName, sourceClassPK, httpServletRequest);
	}

	public String getActiveQualifiers(String allowedTargetClassName)
		throws PortalException {

		int commerceQualifierEntriesCount =
			commerceQualifierEntryService.
				getCommerceQualifierEntriesBySourceCount(
					commerceQualifierEntryRequestHelper.getCompanyId(),
					className, classPK, allowedTargetClassName, null);

		if (commerceQualifierEntriesCount > 0) {
			return allowedTargetClassName;
		}

		return "all";
	}

	public String getActiveQualifiers(String[] allowedTargetClassNames)
		throws PortalException {

		for (String allowedTargetClassName : allowedTargetClassNames) {
			int commerceQualifierEntriesCount =
				commerceQualifierEntryService.
					getCommerceQualifierEntriesBySourceCount(
						commerceQualifierEntryRequestHelper.getCompanyId(),
						className, classPK, allowedTargetClassName, null);

			if (commerceQualifierEntriesCount > 0) {
				return allowedTargetClassName;
			}
		}

		return "all";
	}

	public String[][] getAllowedTargetClassNameGroups() {
		return commerceQualifierMetadata.getAllowedTargetClassNameGroups();
	}

	public String getDatasetId(String className) {
		return getDatasetId("source", className);
	}

	public List<FDSActionDropdownItem> getFDSActionDropdownItems(
			String allowedTargetClassName)
		throws PortalException {

		CommerceQualifierMetadata commerceQualifierMetadata =
			getCommerceQualifierMetadata(allowedTargetClassName);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		PortletURLBuilder.AfterRedirectStep afterRedirectStep;

		try {
			afterRedirectStep = PortletURLBuilder.create(
				PortletProviderUtil.getPortletURL(
					commerceQualifierEntryRequestHelper.getRequest(),
					commerceQualifierMetadata.getModelClassName(),
					commerceQualifierMetadata.getPortletProviderAction())
			).setMVCRenderCommandName(
				commerceQualifierMetadata.getMVCRenderCommandName()
			).setRedirect(
				commerceQualifierEntryRequestHelper.getCurrentURL()
			);
		}
		catch (Exception exception) {
			afterRedirectStep = PortletURLBuilder.create(
				PortalUtil.getControlPanelPortletURL(
					commerceQualifierEntryRequestHelper.getRequest(),
					commerceQualifierMetadata.getPortletId(),
					PortletRequest.RENDER_PHASE)
			).setMVCRenderCommandName(
				commerceQualifierMetadata.getMVCRenderCommandName()
			).setRedirect(
				commerceQualifierEntryRequestHelper.getCurrentURL()
			);
		}

		Map<String, String[]> portletParameters =
			commerceQualifierMetadata.getPortletParameters();

		for (Map.Entry<String, String[]> portletParameter :
				portletParameters.entrySet()) {

			afterRedirectStep.setParameter(
				portletParameter.getKey(), portletParameter.getValue());
		}

		return getFDSActionDropdownItems(
			afterRedirectStep.buildString(),
			commerceQualifierMetadata.isSidePanel());
	}

	public String getQualifierAPIURL(String allowedTargetClassName) {
		return getQualifierAPIURL("bySource", allowedTargetClassName);
	}

}