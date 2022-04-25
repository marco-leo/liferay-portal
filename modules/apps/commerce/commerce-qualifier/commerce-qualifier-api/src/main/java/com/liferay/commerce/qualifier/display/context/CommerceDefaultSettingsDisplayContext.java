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

import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryService;
import com.liferay.frontend.data.set.model.FDSActionDropdownItem;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Riccardo Alberti
 */
public class CommerceDefaultSettingsDisplayContext
	extends BaseCommerceQualifierDisplayContext {

	public CommerceDefaultSettingsDisplayContext(
		CommerceQualifierEntryService commerceQualifierEntryService,
		CommerceQualifierMetadataRegistry commerceQualifierMetadataRegistry,
		String targetClassName, long targetClassPK,
		HttpServletRequest httpServletRequest) {

		super(
			commerceQualifierEntryService, commerceQualifierMetadataRegistry,
			targetClassName, targetClassPK, httpServletRequest);
	}

	public List<FDSActionDropdownItem> getFDSActionDropdownItems(
			String sourceClassName)
		throws PortalException {

		return getFDSActionDropdownItems(sourceClassName, true);
	}

	public String getQualifierAPIURL(String sourceClassName) {
		return getQualifierAPIURL("byTarget", sourceClassName);
	}

	public Map<String, Set<String>> getSourceClassNameMapByTargetClassName()
		throws PortalException {

		return commerceQualifierMetadataRegistry.
			getSourceClassNameMapByTargetClassName(
				commerceQualifierEntryRequestHelper.getCompanyId(), className);
	}

}