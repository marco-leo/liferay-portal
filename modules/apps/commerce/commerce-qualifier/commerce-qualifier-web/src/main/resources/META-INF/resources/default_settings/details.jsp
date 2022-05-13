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
%>

<portlet:actionURL name="/default_settings/edit_commerce_default_setting" var="editCommerceDefaultSettingActionURL" />

<aui:form action="<%= editCommerceDefaultSettingActionURL %>" cssClass="pt-4" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceDefaultSetting == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceDefaultSettingId" type="hidden" value="<%= (commerceDefaultSetting == null) ? 0 : commerceDefaultSetting.getCommerceDefaultSettingId() %>" />

	<div class="row">
		<div class="col-12 col-xl-8">
			<commerce-ui:panel
				bodyClasses="flex-fill"
				collapsed="<%= false %>"
				collapsible="<%= false %>"
				title='<%= LanguageUtil.get(request, "details") %>'
			>
				<div class="row">
					<div class="col">
						<aui:input autoFocus="<%= true %>" label="name" name="name" required="<%= true %>" value="<%= commerceDefaultSetting.getName() %>" />
					</div>
				</div>

				<%@ include file="/default_settings/targets.jspf" %>
			</commerce-ui:panel>

			<commerce-ui:panel
				bodyClasses="flex-fill"
				collapsed="<%= false %>"
				collapsible="<%= false %>"
				title='<%= LanguageUtil.get(request, "default-values") %>'
			>
				<%@ include file="/default_settings/sources.jspf" %>
			</commerce-ui:panel>
		</div>

		<div class="col-12 col-xl-4">
			<commerce-ui:panel
				bodyClasses="flex-fill"
				title='<%= LanguageUtil.get(request, "additional-parameters") %>'
			>

				<%
				CommerceDefaultSettingParametersJSPContributor commerceDefaultSettingParametersJSPContributor = commerceDefaultSettingDisplayContext.getCommerceDefaultSettingParametersJSPContributor();

				if (commerceDefaultSettingParametersJSPContributor != null) {
					commerceDefaultSettingParametersJSPContributor.render(commerceDefaultSetting.getCommerceDefaultSettingId(), request, PipingServletResponseFactory.createPipingServletResponse(pageContext));
				}
				else {
				%>

					<div class="row">
						<div class="col">
							<%= LanguageUtil.get(request, "no-additional-parameters-defined") %>
						</div>
					</div>

				<%
				}
				%>

			</commerce-ui:panel>
		</div>
	</div>

	<div class="row">
		<div class="col-12">
			<%@ include file="/default_settings/custom_fields.jspf" %>
		</div>
	</div>
</aui:form>