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

package com.liferay.commerce.qualifier.internal.metadata;

import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadata;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.util.CommerceQualifierUtil;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	service = CommerceQualifierMetadataRegistry.class
)
public class CommerceQualifierMetadataRegistryImpl
	implements CommerceQualifierMetadataRegistry {

	@Override
	public CommerceQualifierMetadata getCommerceQualifierMetadata(
		String className) {

		return _serviceTrackerMap.getService(className);
	}

	@Override
	public Map<String, CommerceQualifierMetadata>
		getCommerceQualifiersMetadataRESTModelName() {

		Map<String, CommerceQualifierMetadata>
			commerceQualifierMetadataHashMap = new HashMap<>();

		for (String key : _serviceTrackerMap.keySet()) {
			CommerceQualifierMetadata commerceQualifierMetadata =
				_serviceTrackerMap.getService(key);

			commerceQualifierMetadataHashMap.put(
				commerceQualifierMetadata.getRESTModelName(),
				_serviceTrackerMap.getService(key));
		}

		return Collections.unmodifiableMap(commerceQualifierMetadataHashMap);
	}

	@Override
	public Map<String, Set<String>> getSourceClassNameMapByTargetClassName(
		String targetClassName) {

		Map<String, Set<String>> sourceClassNameMap = new HashMap<>();

		for (String key : _serviceTrackerMap.keySet()) {
			CommerceQualifierMetadata commerceQualifierMetadata =
				_serviceTrackerMap.getService(key);

			String[][] allowedTargetClassNameGroups =
				commerceQualifierMetadata.getAllowedTargetClassNameGroups();

			for (String[] allowedTargetClassNameGroup :
					allowedTargetClassNameGroups) {

				if (ArrayUtil.contains(
						allowedTargetClassNameGroup, targetClassName)) {

					Set<String> targetClassNames =
						sourceClassNameMap.computeIfAbsent(
							commerceQualifierMetadata.getDisplayCategory(),
							displayCategory -> new HashSet<>());

					targetClassNames.add(
						commerceQualifierMetadata.getModelClassName());

					break;
				}
			}
		}

		return Collections.unmodifiableMap(sourceClassNameMap);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, CommerceQualifierMetadata.class, null,
			(serviceReference, emitter) -> {
				CommerceQualifierMetadata commerceQualifierMetadata =
					bundleContext.getService(serviceReference);

				try {
					if (commerceQualifierMetadata.getModelClassName() != null) {
						emitter.emit(
							commerceQualifierMetadata.getModelClassName());
					}
				}
				finally {
					bundleContext.ungetService(serviceReference);
				}
			});

		try {
			Field field = CommerceQualifierUtil.class.getDeclaredField(
				"_commerceQualifierMetadataRegistry");

			field.setAccessible(true);

			field.set(null, this);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private ServiceTrackerMap<String, CommerceQualifierMetadata>
		_serviceTrackerMap;

}