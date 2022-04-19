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

import com.liferay.commerce.qualifier.display.context.helper.CommerceQualifierEntryRequestHelper;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadata;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryService;
import com.liferay.frontend.data.set.model.FDSActionDropdownItem;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Riccardo Alberti
 */
public class CommerceQualifierEntriesDisplayContext {

	public CommerceQualifierEntriesDisplayContext(
		CommerceQualifierEntryService commerceQualifierEntryService,
		CommerceQualifierMetadataRegistry commerceQualifierMetadataRegistry,
		String sourceClassName, long sourceClassPK,
		HttpServletRequest httpServletRequest) {

		_commerceQualifierEntryService = commerceQualifierEntryService;
		_commerceQualifierMetadataRegistry = commerceQualifierMetadataRegistry;

		_commerceQualifierEntryRequestHelper =
			new CommerceQualifierEntryRequestHelper(httpServletRequest);

		_sourceClassName = sourceClassName;
		_sourceClassPK = sourceClassPK;

		_commerceQualifierMetadata = _getCommerceQualifierMetadata(
			_sourceClassName);
	}

	public String getActiveQualifiers(String allowedTargetClassName)
		throws PortalException {

		int commerceQualifierEntriesCount =
			_commerceQualifierEntryService.
				getCommerceQualifierEntriesBySourceCount(
					_commerceQualifierEntryRequestHelper.getCompanyId(),
					_sourceClassName, _sourceClassPK, allowedTargetClassName,
					null);

		if (commerceQualifierEntriesCount > 0) {
			return "specific";
		}

		return "all";
	}

	public String[] getAllowedTargetClassNames() {
		return _commerceQualifierMetadata.getAllowedTargetClassNames();
	}

	public String getDatasetId(String allowedTargetClassName) {
		return StringBundler.concat(
			_commerceQualifierMetadata.getModelClassName(), "-",
			allowedTargetClassName);
	}

	public List<FDSActionDropdownItem> getFDSActionDropdownItems(
			String allowedTargetClassName)
		throws PortalException {

		CommerceQualifierMetadata commerceQualifierMetadata =
			_getCommerceQualifierMetadata(allowedTargetClassName);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		PortletURLBuilder.AfterRedirectStep afterRedirectStep =
			PortletURLBuilder.create(
				PortletProviderUtil.getPortletURL(
					_commerceQualifierEntryRequestHelper.getRequest(),
					commerceQualifierMetadata.getModelClassName(),
					commerceQualifierMetadata.getPortletProviderAction())
			).setMVCRenderCommandName(
				commerceQualifierMetadata.getMVCRenderCommandName()
			).setRedirect(
				_commerceQualifierEntryRequestHelper.getCurrentURL()
			);

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

	public String getInputPlaceHolderLabel(String allowedTargetClassName) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			_getCommerceQualifierMetadata(allowedTargetClassName);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return "find-a-" + commerceQualifierMetadata.getLabel();
	}

	public String getItemFinderRootName(String allowedTargetClassName) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			_getCommerceQualifierMetadata(allowedTargetClassName);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return "item-finder-root-" + commerceQualifierMetadata.getLabel();
	}

	public String getItemSelectedPlaceHolderLabel(
		String allowedTargetClassName) {

		CommerceQualifierMetadata commerceQualifierMetadata =
			_getCommerceQualifierMetadata(allowedTargetClassName);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return commerceQualifierMetadata.getLabel() + "-selected";
	}

	public String getLabel(String allowedTargetClassName) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			_getCommerceQualifierMetadata(allowedTargetClassName);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return commerceQualifierMetadata.getLabel();
	}

	public String getPanelHeaderLabelLabel(String allowedTargetClassName) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			_getCommerceQualifierMetadata(allowedTargetClassName);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return "add-" + commerceQualifierMetadata.getPluralLabel();
	}

	public String getPluralLabel(String allowedTargetClassName) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			_getCommerceQualifierMetadata(allowedTargetClassName);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return commerceQualifierMetadata.getPluralLabel();
	}

	public String getQualifierAPIURL(String allowedTargetClassName) {
		return StringBundler.concat(
			"/o/headless-commerce-qualifier/v1.0/qualifiers/bySource/",
			getSourceRESTModelName(), "/",
			String.valueOf(_sourceClassPK), "/",
			getTargetRESTModelName(allowedTargetClassName),
			"?nestedFields=qualifierEntity");
	}

	public String getRESTContextPath(String allowedTargetClassName) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			_getCommerceQualifierMetadata(allowedTargetClassName);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return commerceQualifierMetadata.getRESTContextPath();
	}

	public String getRESTSchema(String allowedTargetClassName) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			_getCommerceQualifierMetadata(allowedTargetClassName);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return commerceQualifierMetadata.getRESTSchema();
	}

	public String getSourceClassName() {
		return _sourceClassName;
	}

	public long getSourceClassPK() {
		return _sourceClassPK;
	}

	public String getSourceRESTModelName() {
		return _commerceQualifierMetadata.getRESTModelName();
	}

	public String getTargetRESTModelName(String allowedTargetClassName) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			_getCommerceQualifierMetadata(allowedTargetClassName);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return commerceQualifierMetadata.getRESTModelName();
	}

	public String getTitleLabelLabel(String allowedTargetClassName) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			_getCommerceQualifierMetadata(allowedTargetClassName);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return "add-existing-" + commerceQualifierMetadata.getLabel();
	}

	public boolean hasPermission(String actionId) throws PortalException {
		ModelResourcePermission<?> modelResourcePermission =
			_commerceQualifierMetadata.getModelResourcePermission();

		return modelResourcePermission.contains(
			_commerceQualifierEntryRequestHelper.getPermissionChecker(),
			_sourceClassPK, actionId);
	}

	protected List<FDSActionDropdownItem> getFDSActionDropdownItems(
		String portletURL, boolean sidePanel) {

		List<FDSActionDropdownItem> fdsActionDropdownItems = new ArrayList<>();

		FDSActionDropdownItem fdsActionDropdownItem = new FDSActionDropdownItem(
			portletURL, "pencil", "edit",
			LanguageUtil.get(
				_commerceQualifierEntryRequestHelper.getRequest(), "edit"),
			"get", null, null);

		if (sidePanel) {
			fdsActionDropdownItem.setTarget("sidePanel");
		}

		fdsActionDropdownItems.add(fdsActionDropdownItem);

		fdsActionDropdownItems.add(
			new FDSActionDropdownItem(
				null, "trash", "delete",
				LanguageUtil.get(
					_commerceQualifierEntryRequestHelper.getRequest(),
					"delete"),
				"delete", "delete", "headless"));

		return fdsActionDropdownItems;
	}

	private CommerceQualifierMetadata _getCommerceQualifierMetadata(
		String className) {

		return _commerceQualifierMetadataRegistry.getCommerceQualifierMetadata(
			className);
	}

	private final CommerceQualifierEntryRequestHelper
		_commerceQualifierEntryRequestHelper;
	private final CommerceQualifierEntryService _commerceQualifierEntryService;
	private final CommerceQualifierMetadata _commerceQualifierMetadata;
	private final CommerceQualifierMetadataRegistry
		_commerceQualifierMetadataRegistry;
	private final String _sourceClassName;
	private final long _sourceClassPK;

}