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

package com.liferay.commerce.qualifier.service.impl;

import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadata;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingService;
import com.liferay.commerce.qualifier.service.base.CommerceDefaultSettingRelServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = {
		"json.web.service.context.name=commerce",
		"json.web.service.context.path=CommerceDefaultSettingRel"
	},
	service = AopService.class
)
public class CommerceDefaultSettingRelServiceImpl
	extends CommerceDefaultSettingRelServiceBaseImpl {

	@Override
	public CommerceDefaultSettingRel addCommerceDefaultSettingRel(
			long commerceDefaultSettingId, String className, long classPK,
			double priority, String type)
		throws PortalException {

		_checkPermission(className, classPK, ActionKeys.UPDATE);

		return commerceDefaultSettingRelLocalService.
			addCommerceDefaultSettingRel(
				getUserId(), commerceDefaultSettingId, className, classPK,
				priority, type);
	}

	@Override
	public void deleteCommerceDefaultSettingRel(
			long commerceDefaultSettingRelId)
		throws PortalException {

		CommerceDefaultSettingRel commerceDefaultSettingRel =
			commerceDefaultSettingRelLocalService.getCommerceDefaultSettingRel(
				commerceDefaultSettingRelId);

		_commerceDefaultSettingModelResourcePermission.check(
			getPermissionChecker(),
			commerceDefaultSettingRel.getCommerceDefaultSettingId(),
			ActionKeys.UPDATE);

		commerceDefaultSettingRelLocalService.deleteCommerceDefaultSettingRel(
			commerceDefaultSettingRelId);
	}

	@Override
	public void deleteCommerceDefaultSettingRels(long commerceDefaultSettingId)
		throws PortalException {

		_commerceDefaultSettingModelResourcePermission.check(
			getPermissionChecker(), commerceDefaultSettingId,
			ActionKeys.UPDATE);

		commerceDefaultSettingRelLocalService.deleteCommerceDefaultSettingRels(
			commerceDefaultSettingId);
	}

	@Override
	public CommerceDefaultSettingRel getCommerceDefaultSettingRel(
			long commerceDefaultSettingRelId)
		throws PortalException {

		CommerceDefaultSettingRel commerceDefaultSettingRel =
			commerceDefaultSettingRelLocalService.getCommerceDefaultSettingRel(
				commerceDefaultSettingRelId);

		_checkPermission(
			commerceDefaultSettingRel.getClassName(),
			commerceDefaultSettingRel.getClassPK(), ActionKeys.VIEW);

		return commerceDefaultSettingRelLocalService.
			getCommerceDefaultSettingRel(commerceDefaultSettingRelId);
	}

	@Override
	public List<CommerceDefaultSettingRel> getCommerceDefaultSettingRels(
			long commerceDefaultSettingId, String type)
		throws PortalException {

		CommerceDefaultSetting commerceDefaultSetting =
			_commerceDefaultSettingService.getCommerceDefaultSetting(
				commerceDefaultSettingId);

		return commerceDefaultSettingRelLocalService.
			getCommerceDefaultSettingRels(
				commerceDefaultSetting.getCommerceDefaultSettingId(), type);
	}

	@Override
	public CommerceDefaultSettingRel updateCommerceDefaultSettingRel(
			long commerceDefaultSettingRelId, double priority)
		throws PortalException {

		CommerceDefaultSettingRel commerceDefaultSettingRel =
			commerceDefaultSettingRelLocalService.getCommerceDefaultSettingRel(
				commerceDefaultSettingRelId);

		_checkPermission(
			commerceDefaultSettingRel.getClassName(),
			commerceDefaultSettingRel.getClassPK(), ActionKeys.UPDATE);

		return commerceDefaultSettingRelLocalService.
			updateCommerceDefaultSettingRel(
				commerceDefaultSettingRelId, priority);
	}

	private void _checkPermission(String className, long classPK, String action)
		throws PortalException {

		CommerceQualifierMetadata commerceQualifierMetadata =
			_commerceQualifierMetadataRegistry.getCommerceQualifierMetadata(
				className);

		ModelResourcePermission<?> modelResourcePermission =
			commerceQualifierMetadata.getModelResourcePermission();

		modelResourcePermission.check(getPermissionChecker(), classPK, action);
	}

	private static volatile ModelResourcePermission<CommerceDefaultSetting>
		_commerceDefaultSettingModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceDefaultSettingRelServiceImpl.class,
				"_commerceDefaultSettingModelResourcePermission",
				CommerceDefaultSetting.class);

	@Reference
	private CommerceDefaultSettingService _commerceDefaultSettingService;

	@Reference
	private CommerceQualifierMetadataRegistry
		_commerceQualifierMetadataRegistry;

}