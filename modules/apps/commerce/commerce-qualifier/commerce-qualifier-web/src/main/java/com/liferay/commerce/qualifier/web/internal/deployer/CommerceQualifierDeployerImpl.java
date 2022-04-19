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

package com.liferay.commerce.qualifier.web.internal.deployer;

import com.liferay.commerce.qualifier.deployer.CommerceQualifierDeployer;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadata;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryService;
import com.liferay.commerce.qualifier.web.internal.frontend.taglib.data.set.view.table.TargetCommerceQualifierEntryFDSView;
import com.liferay.commerce.qualifier.web.internal.portlet.action.EditCommerceQualifierEntryMVCActionCommand;
import com.liferay.frontend.data.set.view.FDSView;
import com.liferay.frontend.data.set.view.table.FDSTableSchemaBuilderFactory;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true, service = CommerceQualifierDeployer.class
)
public class CommerceQualifierDeployerImpl
	implements CommerceQualifierDeployer {

	@Override
	public void deploy(CommerceQualifierMetadata commerceQualifierMetadata) {
		String[] allowedTargets =
			commerceQualifierMetadata.getAllowedTargetClassNames();

		List<ServiceRegistration<?>> serviceRegistrations =
			new CopyOnWriteArrayList<>();

		for (String allowedTarget : allowedTargets) {
			serviceRegistrations.add(
				_bundleContext.registerService(
					FDSView.class,
					new TargetCommerceQualifierEntryFDSView(
						_fdsTableSchemaBuilderFactory),
					HashMapDictionaryBuilder.put(
						"frontend.data.set.name",
						_getCommerceQualifierFDSName(
							commerceQualifierMetadata.getModelClassName(),
							allowedTarget)
					).build()));
		}

		if (Validator.isBlank(commerceQualifierMetadata.getPortletId())) {
			_serviceRegistrationsMaps.put(
				commerceQualifierMetadata, serviceRegistrations);

			return;
		}

		serviceRegistrations.add(
			_bundleContext.registerService(
				MVCActionCommand.class,
				new EditCommerceQualifierEntryMVCActionCommand(
					_commerceQualifierEntryService,
					_commerceQualifierMetadataRegistry),
				HashMapDictionaryBuilder.<String, Object>put(
					"javax.portlet.name",
					commerceQualifierMetadata.getPortletId()
				).put(
					"mvc.command.name", "/commerce_qualifier/edit_qualifiers"
				).build()));

		_serviceRegistrationsMaps.put(
			commerceQualifierMetadata, serviceRegistrations);
	}

	@Override
	public void undeploy(CommerceQualifierMetadata commerceQualifierMetadata) {
		List<ServiceRegistration<?>> serviceRegistrations =
			_serviceRegistrationsMaps.get(commerceQualifierMetadata);

		for (ServiceRegistration<?> serviceRegistration :
				serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	private String _getCommerceQualifierFDSName(
		String modelClassName, String allowedTarget) {

		return StringBundler.concat(modelClassName, "-", allowedTarget);
	}

	private BundleContext _bundleContext;

	@Reference
	private CommerceQualifierEntryService _commerceQualifierEntryService;

	@Reference
	private CommerceQualifierMetadataRegistry
		_commerceQualifierMetadataRegistry;

	@Reference
	private FDSTableSchemaBuilderFactory _fdsTableSchemaBuilderFactory;

	private final Map<CommerceQualifierMetadata, List<ServiceRegistration<?>>>
		_serviceRegistrationsMaps = Collections.synchronizedMap(
			new LinkedHashMap<>());

}