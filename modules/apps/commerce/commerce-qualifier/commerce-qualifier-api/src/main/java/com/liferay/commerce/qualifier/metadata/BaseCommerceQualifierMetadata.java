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

package com.liferay.commerce.qualifier.metadata;

import com.liferay.commerce.qualifier.configuration.CommerceQualifierConfiguration;
import com.liferay.commerce.qualifier.deployer.CommerceQualifierDeployer;
import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRelTable;
import com.liferay.commerce.qualifier.model.CommerceQualifierEntryTable;
import com.liferay.commerce.qualifier.service.CommerceDefaultSettingRelLocalService;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryLocalService;
import com.liferay.commerce.qualifier.util.CommerceQualifierUtil;
import com.liferay.petra.sql.dsl.DSLFunctionFactoryUtil;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.petra.sql.dsl.expression.step.WhenThenStep;
import com.liferay.petra.sql.dsl.query.sort.OrderByExpression;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
public abstract class BaseCommerceQualifierMetadata<T extends BaseModel<T>>
	extends BaseModelListener<T>
	implements CommerceQualifierMetadata<T>, ConfigurationBeanDeclaration {

	@Override
	public String[][] getAllowedTargetClassNameGroups() {
		try {
			CommerceQualifierConfiguration commerceQualifierConfiguration =
				_getCommerceQualifierConfiguration();

			String[] allowedTargetClassNameGroupsArray =
				commerceQualifierConfiguration.
					allowedTargetClassNameGroupsArray();

			String[][] allowedTargetClassNameGroups =
				new String[allowedTargetClassNameGroupsArray.length][];

			for (int i = 0; i < allowedTargetClassNameGroupsArray.length; i++) {
				allowedTargetClassNameGroups[i] =
					allowedTargetClassNameGroupsArray[i].split(
						StringPool.POUND);
			}

			return allowedTargetClassNameGroups;
		}
		catch (ConfigurationException configurationException) {
			if (_log.isDebugEnabled()) {
				_log.debug(configurationException);
			}
		}

		return new String[0][0];
	}

	@Override
	public String getDisplayCategory() {
		return "uncategorized";
	}

	@Override
	public String getExternalReferenceCode(long id) {
		return StringPool.BLANK;
	}

	@Override
	public OrderByExpression[] getOrderByExpressions(
		Map<String, ?> targetAttributes) {

		if (targetAttributes == null) {
			return null;
		}

		try {
			CommerceDefaultSettingRelTable
				sourceCommerceDefaultSettingRelTableAlias =
					CommerceDefaultSettingRelTable.INSTANCE.as(
						"sourceCommerceDefaultSettingRelTableAlias");

			WhenThenStep<Integer> whenThenStep = _getWhenThenStep(
				sourceCommerceDefaultSettingRelTableAlias);

			Set<String> targetAttributeKeySet = targetAttributes.keySet();

			CommerceQualifierConfiguration commerceQualifierConfiguration =
				_getCommerceQualifierConfiguration();

			String[] targetClassNameOrderByGroupsArray =
				commerceQualifierConfiguration.
					targetClassNameOrderByGroupsArray();

			int targetClassNameOrderByGroupsArrayLength =
				targetClassNameOrderByGroupsArray.length;

			for (int i = 0; i < targetClassNameOrderByGroupsArrayLength; i++) {
				String[] targetClassNameOrderByGroup =
					targetClassNameOrderByGroupsArray[i].split(
						StringPool.POUND);

				if (!targetAttributeKeySet.containsAll(
						Arrays.asList(targetClassNameOrderByGroup))) {

					continue;
				}

				Predicate predicate = null;

				for (String targetClassNameOrderBy :
						targetClassNameOrderByGroup) {

					Predicate subpredicate = _getPredicate(
						targetClassNameOrderBy,
						targetAttributes.get(targetClassNameOrderBy));

					if (predicate == null) {
						predicate = subpredicate;
					}
					else {
						predicate = predicate.and(subpredicate);
					}
				}

				if (predicate == null) {
					continue;
				}

				whenThenStep = whenThenStep.whenThen(
					predicate, targetClassNameOrderByGroupsArrayLength - i);
			}

			return ArrayUtil.append(
				new OrderByExpression[] {
					whenThenStep.elseEnd(
						-1
					).descending(),
					sourceCommerceDefaultSettingRelTableAlias.priority.
						descending()
				},
				getAdditionalOrderByExpressions(targetAttributes));
		}
		catch (ConfigurationException configurationException) {
			if (_log.isDebugEnabled()) {
				_log.debug(configurationException);
			}
		}

		return new OrderByExpression[0];
	}

	@Override
	public String getPortletId() {
		return PortletProviderUtil.getPortletId(
			getModelClassName(), PortletProvider.Action.EDIT);
	}

	@Override
	public Map<String, String> getRESTInfo(long id) {
		return Collections.emptyMap();
	}

	@Override
	public Map<String, String> getRESTInfoColumNames(String className) {
		return Collections.emptyMap();
	}

	@Override
	public void onAfterCreate(T model) {
		commerceQualifierEntryLocalService.cleanCommerceQualifierEntryCache(
			CompanyThreadLocal.getCompanyId(), model.getModelClassName());
	}

	@Override
	public void onAfterUpdate(T originalModel, T model) {
		commerceQualifierEntryLocalService.cleanCommerceQualifierEntryCache(
			CompanyThreadLocal.getCompanyId(),
			originalModel.getModelClassName());
	}

	@Override
	public void onBeforeRemove(T model) {
		try {
			commerceQualifierEntryLocalService.
				deleteCommerceQualifierEntriesBySource(
					model.getModelClassName(), (Long)model.getPrimaryKeyObj());

			commerceQualifierEntryLocalService.
				deleteCommerceQualifierEntriesByTarget(
					model.getModelClassName(), (Long)model.getPrimaryKeyObj());

			commerceDefaultSettingRelLocalService.
				deleteCommerceDefaultSettingRels(
					model.getModelClassName(), (Long)model.getPrimaryKeyObj());

			commerceQualifierEntryLocalService.cleanCommerceQualifierEntryCache(
				CompanyThreadLocal.getCompanyId(), model.getModelClassName());
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(portalException);
			}
		}
	}

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		commerceQualifierDeployer.deploy(this);
	}

	@Deactivate
	protected void deactivate() {
		commerceQualifierDeployer.undeploy(this);
	}

	protected abstract OrderByExpression[] getAdditionalOrderByExpressions(
		Map<String, ?> targetAttributes);

	@Reference(unbind = "-")
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {

		_configurationProvider = configurationProvider;
	}

	@Reference
	protected CommerceDefaultSettingRelLocalService
		commerceDefaultSettingRelLocalService;

	@Reference
	protected CommerceQualifierDeployer commerceQualifierDeployer;

	@Reference
	protected CommerceQualifierEntryLocalService
		commerceQualifierEntryLocalService;

	private CommerceQualifierConfiguration _getCommerceQualifierConfiguration()
		throws ConfigurationException {

		return (CommerceQualifierConfiguration)
			_configurationProvider.getCompanyConfiguration(
				getConfigurationBeanClass(), CompanyThreadLocal.getCompanyId());
	}

	private Predicate _getPredicate(String targetClassName, Object value) {
		CommerceQualifierEntryTable tableAlias =
			CommerceQualifierUtil.getCommerceQualifierTableAlias(
				getModelClassName(), targetClassName);

		if (value == null) {
			return tableAlias.commerceQualifierEntryId.isNull();
		}

		Class<?> clazz = value.getClass();

		if (clazz.isArray()) {
			Long[] longValueArray = (Long[])value;

			if (longValueArray.length == 0) {
				longValueArray = new Long[] {0L};
			}

			return tableAlias.targetClassPK.in(longValueArray);
		}

		return tableAlias.targetClassPK.eq((Long)value);
	}

	private WhenThenStep<Integer> _getWhenThenStep(
		CommerceDefaultSettingRelTable
			sourceCommerceDefaultSettingRelTableAlias) {

		return DSLFunctionFactoryUtil.caseWhenThen(
			sourceCommerceDefaultSettingRelTableAlias.
				commerceDefaultSettingRelId.isNotNull(),
			0);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseCommerceQualifierMetadata.class);

	private ConfigurationProvider _configurationProvider;

}