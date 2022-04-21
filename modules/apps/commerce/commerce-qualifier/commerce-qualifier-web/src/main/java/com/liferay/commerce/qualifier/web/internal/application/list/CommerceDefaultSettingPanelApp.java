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

package com.liferay.commerce.qualifier.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.commerce.application.list.constants.CommercePanelCategoryKeys;
import com.liferay.commerce.qualifier.web.internal.constants.CommerceDefaultSettingPortletKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"panel.app.order:Integer=150",
		"panel.category.key=" + CommercePanelCategoryKeys.COMMERCE_SETTINGS
	},
	service = PanelApp.class
)
public class CommerceDefaultSettingPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return CommerceDefaultSettingPortletKeys.
			COMMERCE_DEFAULT_SETTING_PORTLET_NAME;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + CommerceDefaultSettingPortletKeys.COMMERCE_DEFAULT_SETTING_PORTLET_NAME + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}