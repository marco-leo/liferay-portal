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

package com.liferay.commerce.qualifier.search.context;

import java.io.Serializable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.LongStream;

/**
 * @author Riccardo Alberti
 */
public class CommerceQualifierSearchContext implements Serializable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceQualifierSearchContext)) {
			return false;
		}

		CommerceQualifierSearchContext that =
			(CommerceQualifierSearchContext)object;

		if ((_exclusive == that._exclusive) &&
			_sourceAdditionalAttributes.equals(
				that._sourceAdditionalAttributes) &&
			_targetAttributes.equals(that._targetAttributes)) {

			return true;
		}

		return false;
	}

	public Map<String, ?> getSourceAdditionalAttributes() {
		return _sourceAdditionalAttributes;
	}

	public Map<String, ?> getTargetAttributes() {
		return _targetAttributes;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = (31 * hash) + Objects.hashCode(_exclusive);
		hash = _getMapHashCode(hash, _sourceAdditionalAttributes);
		hash = _getMapHashCode(hash, _targetAttributes);

		return hash;
	}

	public boolean isExclusive() {
		return _exclusive;
	}

	public static class Builder {

		public Builder() {
			_exclusive = false;
		}

		public CommerceQualifierSearchContext build() {
			CommerceQualifierSearchContext commerceQualifierSearchContext =
				new CommerceQualifierSearchContext();

			commerceQualifierSearchContext._exclusive = _exclusive;
			commerceQualifierSearchContext._sourceAdditionalAttributes =
				_sourceAdditionalAttributes;
			commerceQualifierSearchContext._targetAttributes =
				_targetAttributes;

			return commerceQualifierSearchContext;
		}

		public Builder setExclusive(boolean exclusive) {
			_exclusive = exclusive;

			return this;
		}

		public Builder setSourceAdditionalAttribute(String key, Boolean value) {
			_sourceAdditionalAttributes.put(key, value);

			return this;
		}

		public Builder setSourceAdditionalAttribute(String key, Number value) {
			_sourceAdditionalAttributes.put(key, value);

			return this;
		}

		public Builder setSourceAdditionalAttribute(
			String key, Number[] value) {

			_sourceAdditionalAttributes.put(key, value);

			return this;
		}

		public Builder setSourceAdditionalAttribute(String key, String value) {
			_sourceAdditionalAttributes.put(key, value);

			return this;
		}

		public Builder setSourceAdditionalAttribute(
			String key, String[] value) {

			_sourceAdditionalAttributes.put(key, value);

			return this;
		}

		public Builder setTargetAttribute(String key, Long value) {
			_targetAttributes.put(key, value);

			return this;
		}

		public Builder setTargetAttribute(String key, long[] value) {
			LongStream longStream = Arrays.stream(value);

			_targetAttributes.put(
				key,
				longStream.boxed(
				).toArray(
					Long[]::new
				));

			return this;
		}

		public Builder setTargetAttribute(String key, Long[] value) {
			_targetAttributes.put(key, value);

			return this;
		}

		private boolean _exclusive;
		private final Map<String, Serializable> _sourceAdditionalAttributes =
			new HashMap<>();
		private final Map<String, Serializable> _targetAttributes =
			new HashMap<>();

	}

	private CommerceQualifierSearchContext() {
	}

	private int _getMapHashCode(int hash, Map<String, ?> map) {
		for (Map.Entry<String, ?> entry : map.entrySet()) {
			hash = (31 * hash) + Objects.hashCode(entry.getKey());

			Object value = entry.getValue();

			if (value != null) {
				Class<?> clazz = value.getClass();

				if (clazz.isArray()) {
					hash = (31 * hash) + Arrays.hashCode((Object[])value);

					continue;
				}
			}

			hash = (31 * hash) + Objects.hashCode(value);
		}

		return hash;
	}

	private boolean _exclusive;
	private Map<String, ?> _sourceAdditionalAttributes;
	private Map<String, ?> _targetAttributes;

}