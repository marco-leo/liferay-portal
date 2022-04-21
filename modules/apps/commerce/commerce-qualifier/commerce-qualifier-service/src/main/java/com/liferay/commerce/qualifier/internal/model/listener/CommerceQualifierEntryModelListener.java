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

package com.liferay.commerce.qualifier.internal.model.listener;

import com.liferay.commerce.qualifier.model.CommerceQualifierEntry;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ClassNameLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(enabled = false, immediate = true, service = ModelListener.class)
public class CommerceQualifierEntryModelListener
	extends BaseModelListener<CommerceQualifierEntry> {

	@Override
	public void onBeforeRemove(CommerceQualifierEntry commerceQualifierEntry) {
		try {
			_cleanCommerceQualifierEntryCache(
				commerceQualifierEntry.getCompanyId(),
				commerceQualifierEntry.getSourceClassNameId());
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(portalException);
			}
		}
	}

	private void _cleanCommerceQualifierEntryCache(
			long companyId, long sourceClassNameId)
		throws PortalException {

		ClassName className = _classNameLocalService.getClassName(
			sourceClassNameId);

		_commerceQualifierEntryLocalService.cleanCommerceQualifierEntryCache(
			companyId, className.getClassName());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceQualifierEntryModelListener.class);

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CommerceQualifierEntryLocalService
		_commerceQualifierEntryLocalService;

}