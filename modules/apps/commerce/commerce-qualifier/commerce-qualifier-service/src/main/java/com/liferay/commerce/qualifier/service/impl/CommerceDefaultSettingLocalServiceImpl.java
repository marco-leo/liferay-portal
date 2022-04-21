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

import com.liferay.commerce.qualifier.model.CommerceDefaultSetting;
import com.liferay.commerce.qualifier.model.CommerceDefaultSettingTable;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingRelLocalService;
import com.liferay.commerce.qualifier.service.base.CommerceDefaultSettingLocalServiceBaseImpl;
import com.liferay.expando.kernel.service.ExpandoRowLocalService;
import com.liferay.petra.sql.dsl.DSLFunctionFactoryUtil;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.petra.sql.dsl.query.FromStep;
import com.liferay.petra.sql.dsl.query.GroupByStep;
import com.liferay.petra.sql.dsl.query.JoinStep;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = "model.class.name=com.liferay.commerce.qualifier.model.CommerceDefaultSetting",
	service = AopService.class
)
public class CommerceDefaultSettingLocalServiceImpl
	extends CommerceDefaultSettingLocalServiceBaseImpl {

	@Override
	public CommerceDefaultSetting addCommerceDefaultSetting(
			long userId, String name, ServiceContext serviceContext)
		throws PortalException {

		CommerceDefaultSetting commerceDefaultSetting =
			commerceDefaultSettingPersistence.create(
				counterLocalService.increment());

		User user = userLocalService.getUser(userId);

		commerceDefaultSetting.setCompanyId(user.getCompanyId());
		commerceDefaultSetting.setUserId(user.getUserId());
		commerceDefaultSetting.setUserName(user.getFullName());

		commerceDefaultSetting.setName(name);

		commerceDefaultSetting.setExpandoBridgeAttributes(serviceContext);

		commerceDefaultSetting = commerceDefaultSettingPersistence.update(
			commerceDefaultSetting);

		resourceLocalService.addModelResources(
			commerceDefaultSetting, serviceContext);

		return commerceDefaultSetting;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceDefaultSetting deleteCommerceDefaultSetting(
			CommerceDefaultSetting commerceDefaultSetting)
		throws PortalException {

		_commerceDefaultSettingRelLocalService.deleteCommerceDefaultSettingRels(
			commerceDefaultSetting.getCommerceDefaultSettingId());

		resourceLocalService.deleteResource(
			commerceDefaultSetting.getCompanyId(),
			CommerceDefaultSetting.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			commerceDefaultSetting.getCommerceDefaultSettingId());

		_expandoRowLocalService.deleteRows(
			commerceDefaultSetting.getCommerceDefaultSettingId());

		return commerceDefaultSettingPersistence.remove(commerceDefaultSetting);
	}

	@Override
	public CommerceDefaultSetting deleteCommerceDefaultSetting(
			long commerceDefaultSettingId)
		throws PortalException {

		CommerceDefaultSetting commerceDefaultSetting =
			commerceDefaultSettingPersistence.findByPrimaryKey(
				commerceDefaultSettingId);

		return commerceDefaultSettingLocalService.deleteCommerceDefaultSetting(
			commerceDefaultSetting);
	}

	@Override
	public List<CommerceDefaultSetting> getCommerceDefaultSettings(
		long companyId, String keywords, int start, int end) {

		return dslQuery(
			_getGroupByStep(
				companyId,
				DSLQueryFactoryUtil.selectDistinct(
					CommerceDefaultSettingTable.INSTANCE),
				keywords
			).limit(
				start, end
			));
	}

	@Override
	public int getCommerceDefaultSettingsCount(
		long companyId, String keywords) {

		return dslQueryCount(
			_getGroupByStep(
				companyId,
				DSLQueryFactoryUtil.countDistinct(
					CommerceDefaultSettingTable.INSTANCE.
						commerceDefaultSettingId),
				keywords));
	}

	@Override
	public CommerceDefaultSetting updateCommerceDefaultSetting(
			long commerceDefaultSettingId, String name,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceDefaultSetting commerceDefaultSetting =
			commerceDefaultSettingPersistence.findByPrimaryKey(
				commerceDefaultSettingId);

		commerceDefaultSetting.setName(name);

		commerceDefaultSetting.setExpandoBridgeAttributes(serviceContext);

		return commerceDefaultSettingPersistence.update(commerceDefaultSetting);
	}

	private GroupByStep _getGroupByStep(
		long companyId, FromStep fromStep, String keywords) {

		JoinStep joinStep = fromStep.from(CommerceDefaultSettingTable.INSTANCE);

		return joinStep.where(
			() -> CommerceDefaultSettingTable.INSTANCE.companyId.eq(
				companyId
			).and(
				() -> {
					if (Validator.isNotNull(keywords)) {
						return Predicate.withParentheses(
							_customSQL.getKeywordsPredicate(
								DSLFunctionFactoryUtil.lower(
									CommerceDefaultSettingTable.INSTANCE.name),
								_customSQL.keywords(keywords, true)));
					}

					return null;
				}
			));
	}

	@Reference
	private CommerceDefaultSettingRelLocalService
		_commerceDefaultSettingRelLocalService;

	@Reference
	private CustomSQL _customSQL;

	@Reference
	private ExpandoRowLocalService _expandoRowLocalService;

}