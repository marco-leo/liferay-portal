<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
boolean companyMentionsEnabled = GetterUtil.getBoolean(request.getAttribute(MentionsWebKeys.COMPANY_MENTIONS_ENABLED));
%>

<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<aui:input checked="<%= companyMentionsEnabled %>" id="mentionsEnabled" label='<%= LanguageUtil.get(resourceBundle, "allow-users-to-mention-other-users") %>' name="settings--mentionsEnabled--" type="checkbox" value="<%= companyMentionsEnabled %>" />
