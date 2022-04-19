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
CommerceQualifierEntriesDisplayContext commerceQualifierEntriesDisplayContext = (CommerceQualifierEntriesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<portlet:actionURL name="/commerce_qualifier/edit_qualifiers" var="editQualifiersActionURL" />

<aui:form action="<%= editQualifiersActionURL %>" cssClass="pt-4" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="sourceClassName" type="hidden" value="<%= commerceQualifierEntriesDisplayContext.getSourceClassName() %>" />
	<aui:input name="sourceClassPK" type="hidden" value="<%= commerceQualifierEntriesDisplayContext.getSourceClassPK() %>" />
	<aui:input name="workflowAction" type="hidden" value="<%= String.valueOf(WorkflowConstants.ACTION_SAVE_DRAFT) %>" />

	<%
	String[] allowedTargetClassNames = commerceQualifierEntriesDisplayContext.getAllowedTargetClassNames();

	for (String allowedTargetClassName : allowedTargetClassNames) {
		String activeQualifiers = ParamUtil.getString(request, commerceQualifierEntriesDisplayContext.getLabel(allowedTargetClassName) + "-option", commerceQualifierEntriesDisplayContext.getActiveQualifiers(allowedTargetClassName));
	%>

		<aui:input name='<%= commerceQualifierEntriesDisplayContext.getLabel(allowedTargetClassName) + "-option" %>' type="hidden" value="<%= activeQualifiers %>" />

		<div class="row">
			<div class="col-12">
				<commerce-ui:panel
					bodyClasses="flex-fill"
					collapsed="<%= false %>"
					collapsible="<%= false %>"
					title='<%= LanguageUtil.get(request, commerceQualifierEntriesDisplayContext.getLabel(allowedTargetClassName) + "-eligibility") %>'
				>
					<div class="row">
						<aui:fieldset markupView="lexicon">
							<aui:input checked='<%= Objects.equals(activeQualifiers, "all") %>' label='<%= "all-" + commerceQualifierEntriesDisplayContext.getPluralLabel(allowedTargetClassName) %>' name='<%= "qualifiers--" + commerceQualifierEntriesDisplayContext.getLabel(allowedTargetClassName) %>' type="radio" value="all" />
							<aui:input checked='<%= Objects.equals(activeQualifiers, "specific") %>' label='<%= "specific-" + commerceQualifierEntriesDisplayContext.getPluralLabel(allowedTargetClassName) %>' name='<%= "qualifiers--" + commerceQualifierEntriesDisplayContext.getLabel(allowedTargetClassName) %>' type="radio" value="specific" />
						</aui:fieldset>
					</div>
				</commerce-ui:panel>
			</div>
		</div>

		<c:if test='<%= Objects.equals(activeQualifiers, "specific") %>'>
			<%@ include file="/target/targets.jspf" %>
		</c:if>

	<%
	}
	%>

</aui:form>

<%
String[] allowedTargetClassNames = commerceQualifierEntriesDisplayContext.getAllowedTargetClassNames();

for (String allowedTargetClassName : allowedTargetClassNames) {
%>

	<liferay-frontend:component
		context='<%=
			HashMapBuilder.<String, Object>put(
				"currentURL", currentURL
			).put(
				"searchParam", commerceQualifierEntriesDisplayContext.getLabel(allowedTargetClassName) + "-option"
			).put(
				"selector", "qualifiers--" + commerceQualifierEntriesDisplayContext.getLabel(allowedTargetClassName)
			).build()
		%>'
		module="js/qualifiers"
	/>

<%
}
%>