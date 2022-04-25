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

package com.liferay.commerce.pricing.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.pricing.web.internal.servlet.taglib.ui.constants.CommerceDiscountScreenNavigationConstants;
import com.liferay.commerce.qualifier.screen.navigator.BaseCommerceQualifierScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = {
		"screen.navigation.category.order:Integer=20",
		"screen.navigation.entry.order:Integer=10"
	},
	service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
)
public class CommerceDiscountQualifiersScreenNavigationCategory
	extends BaseCommerceQualifierScreenNavigationCategory<CommerceDiscount> {

	@Override
	public String getScreenNavigationKey() {
		return CommerceDiscountScreenNavigationConstants.
			SCREEN_NAVIGATION_KEY_DISCOUNT_GENERAL;
	}

	@Override
	public String getSourceClassPKParamName() {
		return "commerceDiscountId";
	}

	@Override
	public String getSourceName() {
		return CommerceDiscount.class.getName();
	}

	@Override
	public boolean isVisible(User user, CommerceDiscount commerceDiscount) {
		if (commerceDiscount == null) {
			return false;
		}

		boolean hasPermission = false;

		try {
			hasPermission = _commerceDiscountModelResourcePermission.contains(
				PermissionThreadLocal.getPermissionChecker(),
				commerceDiscount.getCommerceDiscountId(), ActionKeys.UPDATE);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return hasPermission;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDiscountQualifiersScreenNavigationCategory.class);

	@Reference(
		target = "(model.class.name=com.liferay.commerce.discount.model.CommerceDiscount)"
	)
	private ModelResourcePermission<CommerceDiscount>
		_commerceDiscountModelResourcePermission;

}