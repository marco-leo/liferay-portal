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

package com.liferay.commerce.qualifier.web.internal.portlet.action;

import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingRelService;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingService;
import com.liferay.commerce.qualifier.web.internal.constants.CommerceDefaultSettingPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"javax.portlet.name=" + CommerceDefaultSettingPortletKeys.COMMERCE_DEFAULT_SETTING_PORTLET_NAME,
		"mvc.command.name=/default_settings/edit_commerce_default_setting"
	},
	service = MVCActionCommand.class
)
public class EditCommerceDefaultSettingMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				long commerceDefaultSettingId = ParamUtil.getLong(
					actionRequest, "commerceDefaultSettingId");

				_updateCommerceDefaultSetting(
					commerceDefaultSettingId, actionRequest);

				_commerceDefaultSettingRelService.
					deleteCommerceDefaultSettingRels(commerceDefaultSettingId);

				_updateCommerceDefaultSettingSources(
					commerceDefaultSettingId, actionRequest);
				_updateCommerceDefaultSettingTargets(
					commerceDefaultSettingId, actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				long commerceDefaultSettingRelId = ParamUtil.getLong(
					actionRequest, "commerceDefaultSettingRelId");

				_commerceDefaultSettingRelService.
					deleteCommerceDefaultSettingRel(
						commerceDefaultSettingRelId);
			}
		}
		catch (Throwable throwable) {
			SessionErrors.add(actionRequest, throwable.getClass(), throwable);

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			sendRedirect(actionRequest, actionResponse, redirect);
		}
	}

	private String _getParameterSettings(ActionRequest actionRequest) {
		UnicodeProperties parameterSettingsUnicodeProperties =
			PropertiesParamUtil.getProperties(
				actionRequest, "parameter--settings--");

		return parameterSettingsUnicodeProperties.toString();
	}

	private void _updateCommerceDefaultSetting(
			long commerceDefaultSettingId, ActionRequest actionRequest)
		throws PortalException {

		String name = ParamUtil.getString(actionRequest, "name");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceDefaultSetting.class.getName(), actionRequest);

		if (commerceDefaultSettingId <= 0) {
			_commerceDefaultSettingService.addCommerceDefaultSetting(
				name, StringPool.BLANK, serviceContext);

			return;
		}

		_commerceDefaultSettingService.updateCommerceDefaultSetting(
			commerceDefaultSettingId, name,
			_getParameterSettings(actionRequest), serviceContext);
	}

	private void _updateCommerceDefaultSettingSources(
			long commerceDefaultSettingId, ActionRequest actionRequest)
		throws PortalException {

		Map<String, String> sourceTypes = PropertiesParamUtil.getProperties(
			actionRequest, "sourceType--");

		for (Map.Entry<String, String> sourceTypeEntry :
				sourceTypes.entrySet()) {

			long sourceId = ParamUtil.getLong(
				actionRequest,
				StringBundler.concat(
					"sourceId--", sourceTypeEntry.getKey(), "--"));

			if (sourceId <= 0) {
				continue;
			}

			double sourcePriority = ParamUtil.getDouble(
				actionRequest,
				StringBundler.concat(
					"sourcePriority--", sourceTypeEntry.getKey(), "--"));

			_commerceDefaultSettingRelService.addCommerceDefaultSettingRel(
				commerceDefaultSettingId, sourceTypeEntry.getValue(), sourceId,
				sourcePriority, "source");
		}
	}

	private void _updateCommerceDefaultSettingTargets(
			long commerceDefaultSettingId, ActionRequest actionRequest)
		throws PortalException {

		Map<String, String> targetTypes = PropertiesParamUtil.getProperties(
			actionRequest, "targetType--");

		for (Map.Entry<String, String> targetTypeEntry :
				targetTypes.entrySet()) {

			long targetId = ParamUtil.getLong(
				actionRequest,
				StringBundler.concat(
					"targetId--", targetTypeEntry.getKey(), "--"));

			if (targetId <= 0) {
				continue;
			}

			_commerceDefaultSettingRelService.addCommerceDefaultSettingRel(
				commerceDefaultSettingId, targetTypeEntry.getValue(), targetId,
				0, "target");
		}
	}

	@Reference
	private CommerceDefaultSettingRelService _commerceDefaultSettingRelService;

	@Reference
	private CommerceDefaultSettingService _commerceDefaultSettingService;

}