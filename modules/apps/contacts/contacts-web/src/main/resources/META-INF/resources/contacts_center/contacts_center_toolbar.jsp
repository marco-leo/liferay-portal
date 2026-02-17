<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long userId = ParamUtil.getLong(request, "userId");

User user2 = null;

if (userId > 0) {
	user2 = UserLocalServiceUtil.getUser(userId);
}

String userDisplayURL = StringPool.BLANK;

if (user2 != null) {
	userDisplayURL = user2.getDisplayURL(themeDisplay);
}
%>

<div class="lfr-button-column" id="<portlet:namespace />buttonColumn">
	<div class="lfr-button-column-content">
		<aui:button-row cssClass="btn-group edit-toolbar" id='<%= liferayPortletResponse.getNamespace() + "userToolbar" %>' />

		<div class="btn view-more-button">
			<liferay-ui:icon
				icon="ellipsis-h"
				markupView="lexicon"
			/>

			<liferay-ui:message key="more" />
		</div>
	</div>
</div>

<aui:script position="inline" use="aui-dialog-iframe-deprecated,aui-io-plugin-deprecated,aui-toolbar,liferay-util-window">
	var buttonRow = A.one('#<portlet:namespace />userToolbar');

	var contactsToolbarChildren = [];

	contactsToolbarChildren.push(
		new A.Button({
			cssClass: 'more',
			id: '<portlet:namespace />exportButton',
			label: '<%= UnicodeLanguageUtil.get(request, "vcard") %>',
			on: {
				click: function (event) {
					<c:choose>
						<c:when test="<%= user2 != null %>">
							location.href =
								'<liferay-portlet:resourceURL id="exportVCard"><portlet:param name="userId" value="<%= String.valueOf(user2.getUserId()) %>" /></liferay-portlet:resourceURL>';
						</c:when>
						<c:otherwise>
							location.href =
								'<liferay-portlet:resourceURL id="exportVCards" />&<portlet:namespace />userIds=' +
								A.all('.lfr-contact-grid-item input').val();
						</c:otherwise>
					</c:choose>
				},
			},
		})
	);

	var contactsToolbar = new A.Toolbar({
		activeState: false,
		boundingBox: buttonRow,
		children: contactsToolbarChildren,
	}).render();
</aui:script>