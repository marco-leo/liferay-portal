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

import com.liferay.commerce.qualifier.deployer.CommerceQualifierDeployer;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;

import java.util.Collections;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
public abstract class BaseCommerceQualifierMetadata<T>
	implements CommerceQualifierMetadata {

	@Override
	public String getExternalReferenceCode(long id) {
		return StringPool.BLANK;
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

	@Activate
	protected void activate(BundleContext bundleContext) {
		commerceQualifierDeployer.deploy(this);
	}

	@Deactivate
	protected void deactivate() {
		commerceQualifierDeployer.undeploy(this);
	}

	@Reference
	protected CommerceQualifierDeployer commerceQualifierDeployer;

}