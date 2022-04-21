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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Riccardo Alberti
 */
public class BaseCommerceQualifierDisplayContext {

	public BaseCommerceQualifierDisplayContext(
		CommerceQualifierEntryService commerceQualifierEntryService,
		CommerceQualifierMetadataRegistry commerceQualifierMetadataRegistry,
		String className, long classPK, HttpServletRequest httpServletRequest) {

		this.commerceQualifierEntryService = commerceQualifierEntryService;
		this.commerceQualifierMetadataRegistry =
			commerceQualifierMetadataRegistry;
		this.className = className;
		this.classPK = classPK;

		commerceQualifierEntryRequestHelper =
			new CommerceQualifierEntryRequestHelper(httpServletRequest);
		commerceQualifierMetadata = getCommerceQualifierMetadata(className);
	}

	public String getClassName() {
		return className;
	}

	public long getClassPK() {
		return classPK;
	}

	public String getInputPlaceHolderLabel(String className) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			getCommerceQualifierMetadata(className);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		String label = commerceQualifierMetadata.getLabel();

		if (_isStartWithVowel(label)) {
			return "find-an-" + label;
		}

		return "find-a-" + label;
	}

	public String getItemFinderRootName(String className) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			getCommerceQualifierMetadata(className);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return "item-finder-root-" + commerceQualifierMetadata.getLabel();
	}

	public String getItemSelectedPlaceHolderLabel(String className) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			getCommerceQualifierMetadata(className);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return commerceQualifierMetadata.getLabel() + "-selected";
	}

	public String getLabel(String className) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			getCommerceQualifierMetadata(className);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return commerceQualifierMetadata.getLabel();
	}

	public String getPanelHeaderLabelLabel(String className) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			getCommerceQualifierMetadata(className);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return "add-" + commerceQualifierMetadata.getPluralLabel();
	}

	public String getPluralLabel(String className) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			getCommerceQualifierMetadata(className);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return commerceQualifierMetadata.getPluralLabel();
	}

	public String getQualifierAPIURL(String qualifierType, String className) {
		return StringBundler.concat(
			"/o/headless-commerce-qualifier/v1.0/qualifiers/", qualifierType,
			"/", getRESTModelName1(), "/", String.valueOf(classPK), "/",
			getRESTModelName2(className), "?nestedFields=qualifierEntity");
	}

	public String getRESTContextPath(String className) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			getCommerceQualifierMetadata(className);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return commerceQualifierMetadata.getRESTContextPath();
	}

	public String getRESTModelName1() {
		return commerceQualifierMetadata.getRESTModelName();
	}

	public String getRESTModelName2(String className) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			getCommerceQualifierMetadata(className);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return commerceQualifierMetadata.getRESTModelName();
	}

	public String getRESTSchema(String className) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			getCommerceQualifierMetadata(className);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return commerceQualifierMetadata.getRESTSchema();
	}

	public String getTitleLabelLabel(String className) {
		CommerceQualifierMetadata commerceQualifierMetadata =
			getCommerceQualifierMetadata(className);

		if (commerceQualifierMetadata == null) {
			return null;
		}

		return "add-existing-" + commerceQualifierMetadata.getLabel();
	}

	public boolean hasPermission(String actionId) throws PortalException {
		ModelResourcePermission<?> modelResourcePermission =
			commerceQualifierMetadata.getModelResourcePermission();

		return modelResourcePermission.contains(
			commerceQualifierEntryRequestHelper.getPermissionChecker(), classPK,
			actionId);
	}

	protected CommerceQualifierMetadata getCommerceQualifierMetadata(
			long classNameId)
		throws PortalException {

		if (classNameId <= 0) {
			return null;
		}

		ClassName classNameFromClassId = ClassNameLocalServiceUtil.getClassName(
			classNameId);

		return commerceQualifierMetadataRegistry.getCommerceQualifierMetadata(
			classNameFromClassId.getClassName());
	}

	protected CommerceQualifierMetadata getCommerceQualifierMetadata(
		String className) {

		if (className == null) {
			return null;
		}

		return commerceQualifierMetadataRegistry.getCommerceQualifierMetadata(
			className);
	}

	protected String getDatasetId(String prefix, String className) {
		return StringBundler.concat(
			prefix, "-", commerceQualifierMetadata.getModelClassName(), "-",
			className);
	}

	protected List<FDSActionDropdownItem> getFDSActionDropdownItems(
			String portletURL, boolean sidePanel)
		throws PortalException {

		List<FDSActionDropdownItem> fdsActionDropdownItems = new ArrayList<>();

		FDSActionDropdownItem fdsActionDropdownItem = new FDSActionDropdownItem(
			portletURL, "pencil", "edit",
			LanguageUtil.get(
				commerceQualifierEntryRequestHelper.getRequest(), "edit"),
			"get", null, null);

		if (sidePanel) {
			fdsActionDropdownItem.setTarget("sidePanel");
		}

		fdsActionDropdownItems.add(fdsActionDropdownItem);

		fdsActionDropdownItems.add(
			new FDSActionDropdownItem(
				null, "trash", "delete",
				LanguageUtil.get(
					commerceQualifierEntryRequestHelper.getRequest(), "delete"),
				"delete", "delete", "headless"));

		return fdsActionDropdownItems;
	}

	protected final String className;
	protected final long classPK;
	protected final CommerceQualifierEntryRequestHelper
		commerceQualifierEntryRequestHelper;
	protected final CommerceQualifierEntryService commerceQualifierEntryService;
	protected final CommerceQualifierMetadata commerceQualifierMetadata;
	protected final CommerceQualifierMetadataRegistry
		commerceQualifierMetadataRegistry;

	private boolean _isStartWithVowel(String label) {
		if (label.length() == 0) {
			return false;
		}

		String labelLowerCase = StringUtil.toLowerCase(label);

		if (_VOWELS.indexOf(labelLowerCase.charAt(0)) > -1) {
			return true;
		}

		return false;
	}

	private static final String _VOWELS = "aeiou";

}