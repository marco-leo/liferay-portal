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

package com.liferay.commerce.qualifier.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Riccardo Alberti
 */
@ExtendedObjectClassDefinition(
	category = "qualifiers", scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.commerce.qualifier.configuration.CommercePriceListQualifierConfiguration",
	localization = "content/Language",
	name = "commerce-price-list-qualifier-configuration-name"
)
public interface CommercePriceListQualifierConfiguration
	extends CommerceQualifierConfiguration {

	@Meta.AD(
		deflt = "com.liferay.account.model.AccountEntry#com.liferay.account.model.AccountGroup,com.liferay.commerce.product.model.CommerceChannel,com.liferay.commerce.model.CommerceOrderType",
		name = "allowed-target-class-name-group", required = false
	)
	public String[] allowedTargetClassNameGroupsArray();

	@Meta.AD(
		deflt = "com.liferay.account.model.AccountEntry#com.liferay.commerce.product.model.CommerceChannel#com.liferay.commerce.model.CommerceOrderType,com.liferay.account.model.AccountEntry#com.liferay.commerce.model.CommerceOrderType,com.liferay.account.model.AccountEntry#com.liferay.commerce.product.model.CommerceChannel,com.liferay.account.model.AccountEntry,com.liferay.account.model.AccountGroup#com.liferay.commerce.product.model.CommerceChannel#com.liferay.commerce.model.CommerceOrderType,com.liferay.account.model.AccountGroup#com.liferay.commerce.model.CommerceOrderType,com.liferay.account.model.AccountGroup#com.liferay.commerce.product.model.CommerceChannel,com.liferay.account.model.AccountGroup,com.liferay.commerce.product.model.CommerceChannel#com.liferay.commerce.model.CommerceOrderType,com.liferay.commerce.model.CommerceOrderType,com.liferay.commerce.product.model.CommerceChannel",
		name = "target-class-name-order-by-group", required = false
	)
	public String[] targetClassNameOrderByGroupsArray();

}