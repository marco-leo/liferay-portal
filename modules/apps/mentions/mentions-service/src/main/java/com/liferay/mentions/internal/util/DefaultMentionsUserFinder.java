/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mentions.internal.util;

import com.liferay.mentions.util.MentionsUserFinder;
import com.liferay.portal.kernel.dao.orm.WildcardMode;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;
import com.liferay.portal.kernel.util.comparator.UserScreenNameComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(service = MentionsUserFinder.class)
public class DefaultMentionsUserFinder implements MentionsUserFinder {

	@Override
	public List<User> getUsers(
			long companyId, long groupId, long userId, String query)
		throws PortalException {

		return _userLocalService.search(
			companyId, query, WorkflowConstants.STATUS_APPROVED,
			LinkedHashMapBuilder.<String, Object>put(
				"wildcardMode", WildcardMode.TRAILING
			).build(),
			0, _MAX_USERS, UserScreenNameComparator.getInstance(false));
	}

	@Override
	public List<User> getUsers(long companyId, long userId, String query)
		throws PortalException {

		return getUsers(
			companyId, GroupConstants.DEFAULT_PARENT_GROUP_ID, userId, query);
	}

	private static final int _MAX_USERS = 20;

	@Reference
	private UserLocalService _userLocalService;

}
