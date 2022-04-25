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

package com.liferay.commerce.account.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.account.constants.AccountConstants;
import com.liferay.account.model.AccountEntry;
import com.liferay.commerce.account.web.internal.constants.AccountEntryScreenNavigationEntryConstants;
import com.liferay.commerce.qualifier.screen.navigator.BaseCommerceDefaultSettingsScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.portal.kernel.model.User;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = {
		"screen.navigation.category.order:Integer=80",
		"screen.navigation.entry.order:Integer=10"
	},
	service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
)
public class AccountEntryCommerceDefaultSettingsScreenNavigationCategory
	extends BaseCommerceDefaultSettingsScreenNavigationCategory<AccountEntry> {

	@Override
	public String getScreenNavigationKey() {
		return AccountEntryScreenNavigationEntryConstants.
			SCREEN_NAVIGATION_KEY_ACCOUNT_ENTRY;
	}

	@Override
	public String getTargetClassPKParamName() {
		return "accountEntryId";
	}

	@Override
	public String getTargetName() {
		return AccountEntry.class.getName();
	}

	@Override
	public boolean isVisible(User user, AccountEntry accountEntry) {
		if ((accountEntry == null) ||
			!Objects.equals(
				accountEntry.getType(),
				AccountConstants.ACCOUNT_ENTRY_TYPE_BUSINESS)) {

			return false;
		}

		return true;
	}

}