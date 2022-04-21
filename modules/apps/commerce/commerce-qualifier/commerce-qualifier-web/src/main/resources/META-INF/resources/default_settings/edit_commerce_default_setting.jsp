<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
CommerceDefaultSettingDisplayContext commerceDefaultSettingDisplayContext = (CommerceDefaultSettingDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceDefaultSetting commerceDefaultSetting = commerceDefaultSettingDisplayContext.getCommerceDefaultSetting();

portletDisplay.setShowBackIcon(true);

String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	portletDisplay.setURLBack(String.valueOf(renderResponse.createRenderURL()));
}
else {
	portletDisplay.setURLBack(redirect);
}
%>

<commerce-ui:header
	actions="<%= commerceDefaultSettingDisplayContext.getHeaderActionModels() %>"
	bean="<%= commerceDefaultSetting %>"
	beanIdLabel="id"
	model="<%= CommerceDefaultSetting.class %>"
	title="<%= commerceDefaultSetting.getName() %>"
/>

<liferay-frontend:screen-navigation
	containerWrapperCssClass="container"
	key="commerce.qualifier.default.setting.general"
	modelBean="<%= commerceDefaultSetting %>"
	portletURL="<%= currentURLObj %>"
/>