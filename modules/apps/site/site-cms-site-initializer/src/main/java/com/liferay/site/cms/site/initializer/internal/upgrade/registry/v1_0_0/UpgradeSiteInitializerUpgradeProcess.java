/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.cms.site.initializer.internal.upgrade.registry.v1_0_0;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.site.initializer.SiteInitializer;

/**
 * @author Jürgen Kappler
 */
public class UpgradeSiteInitializerUpgradeProcess extends UpgradeProcess {

	public UpgradeSiteInitializerUpgradeProcess(
		CompanyLocalService companyLocalService,
		GroupLocalService groupLocalService, SiteInitializer siteInitializer) {

		_companyLocalService = companyLocalService;
		_groupLocalService = groupLocalService;
		_siteInitializer = siteInitializer;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_companyLocalService.forEachCompanyId(
			companyId -> _upgradeCMSGroup(companyId));
	}

	private void _upgradeCMSGroup(long companyId) throws Exception {
		Group group = _groupLocalService.fetchGroupByExternalReferenceCode(
			"L_CMS", companyId);

		if (group != null) {
			_siteInitializer.initialize(group.getGroupId());
		}
	}

	private final CompanyLocalService _companyLocalService;
	private final GroupLocalService _groupLocalService;
	private final SiteInitializer _siteInitializer;

}