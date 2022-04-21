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

import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel;
import com.liferay.commerce.qualifier.service.base.CommerceDefaultSettingRelLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = "model.class.name=com.liferay.commerce.qualifier.model.CommerceDefaultSettingRel",
	service = AopService.class
)
public class CommerceDefaultSettingRelLocalServiceImpl
	extends CommerceDefaultSettingRelLocalServiceBaseImpl {

	@Override
	public CommerceDefaultSettingRel addCommerceDefaultSettingRel(
			long userId, long commerceDefaultSettingId, String className,
			long classPK, double priority, String type)
		throws PortalException {

		CommerceDefaultSettingRel commerceDefaultSettingRel =
			commerceDefaultSettingRelPersistence.create(
				counterLocalService.increment());

		User user = userLocalService.getUser(userId);

		commerceDefaultSettingRel.setCompanyId(user.getCompanyId());
		commerceDefaultSettingRel.setUserId(user.getUserId());
		commerceDefaultSettingRel.setUserName(user.getFullName());

		commerceDefaultSettingRel.setClassNameId(
			classNameLocalService.getClassNameId(className));
		commerceDefaultSettingRel.setClassPK(classPK);
		commerceDefaultSettingRel.setCommerceDefaultSettingId(
			commerceDefaultSettingId);

		commerceDefaultSettingRel.setPriority(priority);

		commerceDefaultSettingRel.setType(type);

		return commerceDefaultSettingRelPersistence.update(
			commerceDefaultSettingRel);
	}

	@Override
	public void deleteCommerceDefaultSettingRels(long commerceDefaultSettingId)
		throws PortalException {

		List<CommerceDefaultSettingRel> commerceDefaultSettingRels =
			commerceDefaultSettingRelPersistence.findByCommerceDefaultSettingId(
				commerceDefaultSettingId);

		for (CommerceDefaultSettingRel commerceDefaultSettingRel :
				commerceDefaultSettingRels) {

			commerceDefaultSettingRelLocalService.
				deleteCommerceDefaultSettingRel(commerceDefaultSettingRel);
		}
	}

	@Override
	public void deleteCommerceDefaultSettingRels(String className, long classPK)
		throws PortalException {

		List<CommerceDefaultSettingRel> commerceDefaultSettingRels =
			commerceDefaultSettingRelPersistence.findByC_C(
				classNameLocalService.getClassNameId(className), classPK);

		for (CommerceDefaultSettingRel commerceDefaultSettingRel :
				commerceDefaultSettingRels) {

			commerceDefaultSettingRelLocalService.
				deleteCommerceDefaultSettingRel(commerceDefaultSettingRel);
		}
	}

	@Override
	public List<CommerceDefaultSettingRel> getCommerceDefaultSettingRels(
			long commerceDefaultSettingId, String type)
		throws PortalException {

		return commerceDefaultSettingRelPersistence.findByC_T(
			commerceDefaultSettingId, type);
	}

	@Override
	public CommerceDefaultSettingRel updateCommerceDefaultSettingRel(
			long commerceDefaultSettingRelId, double priority)
		throws PortalException {

		CommerceDefaultSettingRel commerceDefaultSettingRel =
			commerceDefaultSettingRelPersistence.findByPrimaryKey(
				commerceDefaultSettingRelId);

		commerceDefaultSettingRel.setPriority(priority);

		return commerceDefaultSettingRelPersistence.update(
			commerceDefaultSettingRel);
	}

}