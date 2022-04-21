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

import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadata;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryService;
import com.liferay.commerce.qualifier.util.CommerceQualifierUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Riccardo Alberti
 */
public class EditCommerceQualifiersMVCActionCommand
	extends BaseMVCActionCommand {

	public EditCommerceQualifiersMVCActionCommand(
		CommerceQualifierEntryService commerceQualifierEntryService,
		CommerceQualifierMetadataRegistry commerceQualifierMetadataRegistry) {

		_commerceQualifierEntryService = commerceQualifierEntryService;
		_commerceQualifierMetadataRegistry = commerceQualifierMetadataRegistry;
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				_updateCommerceQualifiers(actionRequest);
			}
		}
		catch (Exception exception) {
			SessionErrors.add(actionRequest, exception.getClass());

			actionResponse.setRenderParameter("mvcPath", "/error.jsp");
		}
	}

	private void _updateCommerceQualifiers(ActionRequest actionRequest)
		throws Exception {

		String sourceClassName = ParamUtil.getString(
			actionRequest, "sourceClassName");
		long sourceClassPK = ParamUtil.getLong(actionRequest, "sourceClassPK");

		CommerceQualifierMetadata commerceQualifierMetadata =
			_commerceQualifierMetadataRegistry.getCommerceQualifierMetadata(
				sourceClassName);

		String[][] allowedTargetClassNameGroups =
			commerceQualifierMetadata.getAllowedTargetClassNameGroups();

		for (String[] allowedTargetClassNameGroup :
				allowedTargetClassNameGroups) {

			if (allowedTargetClassNameGroup.length <= 1) {
				continue;
			}

			String allowedTargetClassNameGroupName =
				CommerceQualifierUtil.getAllowedTargetClassNameGroupName(
					allowedTargetClassNameGroup);

			String activeQualifiers = ParamUtil.getString(
				actionRequest, allowedTargetClassNameGroupName + "-option");

			for (String allowedTargetClassName : allowedTargetClassNameGroup) {
				if (!Objects.equals(allowedTargetClassName, activeQualifiers)) {
					_commerceQualifierEntryService.
						deleteCommerceQualifierEntriesBySource(
							sourceClassName, sourceClassPK,
							allowedTargetClassName);
				}
			}
		}
	}

	private final CommerceQualifierEntryService _commerceQualifierEntryService;
	private final CommerceQualifierMetadataRegistry
		_commerceQualifierMetadataRegistry;

}