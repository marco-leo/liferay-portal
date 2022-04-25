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

import javax.servlet.http.HttpServletRequest;

/**
 * @author Riccardo Alberti
 */
public class CommerceQualifierEntriesDisplayContext
	extends BaseCommerceQualifierDisplayContext {

	public CommerceQualifierEntriesDisplayContext(
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
		return commerceQualifierMetadata.getAllowedTargetClassNameGroups(
			commerceQualifierEntryRequestHelper.getCompanyId());
	}

	public List<FDSActionDropdownItem> getFDSActionDropdownItems(
			String allowedTargetClassName)
		throws PortalException {

		return getFDSActionDropdownItems(allowedTargetClassName, false);
	}

	public String getQualifierAPIURL(String allowedTargetClassName) {
		return getQualifierAPIURL("bySource", allowedTargetClassName);
	}

}