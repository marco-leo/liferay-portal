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

package com.liferay.commerce.qualifier.rest.client.serdes.v1_0;

import com.liferay.commerce.qualifier.rest.client.dto.v1_0.DefaultSettingEntity;
import com.liferay.commerce.qualifier.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Riccardo Alberti
 * @generated
 */
@Generated("")
public class DefaultSettingEntitySerDes {

	public static DefaultSettingEntity toDTO(String json) {
		DefaultSettingEntityJSONParser defaultSettingEntityJSONParser =
			new DefaultSettingEntityJSONParser();

		return defaultSettingEntityJSONParser.parseToDTO(json);
	}

	public static DefaultSettingEntity[] toDTOs(String json) {
		DefaultSettingEntityJSONParser defaultSettingEntityJSONParser =
			new DefaultSettingEntityJSONParser();

		return defaultSettingEntityJSONParser.parseToDTOs(json);
	}

	public static String toJSON(DefaultSettingEntity defaultSettingEntity) {
		if (defaultSettingEntity == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (defaultSettingEntity.getActions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(defaultSettingEntity.getActions()));
		}

		if (defaultSettingEntity.getClassId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"classId\": ");

			sb.append(defaultSettingEntity.getClassId());
		}

		if (defaultSettingEntity.getClassName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"className\": ");

			sb.append("\"");

			sb.append(_escape(defaultSettingEntity.getClassName()));

			sb.append("\"");
		}

		if (defaultSettingEntity.getDefaultSettingId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"defaultSettingId\": ");

			sb.append(defaultSettingEntity.getDefaultSettingId());
		}

		if (defaultSettingEntity.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(defaultSettingEntity.getId());
		}

		if (defaultSettingEntity.getPriority() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"priority\": ");

			sb.append(defaultSettingEntity.getPriority());
		}

		if (defaultSettingEntity.getType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(_escape(defaultSettingEntity.getType()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		DefaultSettingEntityJSONParser defaultSettingEntityJSONParser =
			new DefaultSettingEntityJSONParser();

		return defaultSettingEntityJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		DefaultSettingEntity defaultSettingEntity) {

		if (defaultSettingEntity == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (defaultSettingEntity.getActions() == null) {
			map.put("actions", null);
		}
		else {
			map.put(
				"actions", String.valueOf(defaultSettingEntity.getActions()));
		}

		if (defaultSettingEntity.getClassId() == null) {
			map.put("classId", null);
		}
		else {
			map.put(
				"classId", String.valueOf(defaultSettingEntity.getClassId()));
		}

		if (defaultSettingEntity.getClassName() == null) {
			map.put("className", null);
		}
		else {
			map.put(
				"className",
				String.valueOf(defaultSettingEntity.getClassName()));
		}

		if (defaultSettingEntity.getDefaultSettingId() == null) {
			map.put("defaultSettingId", null);
		}
		else {
			map.put(
				"defaultSettingId",
				String.valueOf(defaultSettingEntity.getDefaultSettingId()));
		}

		if (defaultSettingEntity.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(defaultSettingEntity.getId()));
		}

		if (defaultSettingEntity.getPriority() == null) {
			map.put("priority", null);
		}
		else {
			map.put(
				"priority", String.valueOf(defaultSettingEntity.getPriority()));
		}

		if (defaultSettingEntity.getType() == null) {
			map.put("type", null);
		}
		else {
			map.put("type", String.valueOf(defaultSettingEntity.getType()));
		}

		return map;
	}

	public static class DefaultSettingEntityJSONParser
		extends BaseJSONParser<DefaultSettingEntity> {

		@Override
		protected DefaultSettingEntity createDTO() {
			return new DefaultSettingEntity();
		}

		@Override
		protected DefaultSettingEntity[] createDTOArray(int size) {
			return new DefaultSettingEntity[size];
		}

		@Override
		protected void setField(
			DefaultSettingEntity defaultSettingEntity,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actions")) {
				if (jsonParserFieldValue != null) {
					defaultSettingEntity.setActions(
						(Map)DefaultSettingEntitySerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "classId")) {
				if (jsonParserFieldValue != null) {
					defaultSettingEntity.setClassId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "className")) {
				if (jsonParserFieldValue != null) {
					defaultSettingEntity.setClassName(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "defaultSettingId")) {
				if (jsonParserFieldValue != null) {
					defaultSettingEntity.setDefaultSettingId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					defaultSettingEntity.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "priority")) {
				if (jsonParserFieldValue != null) {
					defaultSettingEntity.setPriority(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				if (jsonParserFieldValue != null) {
					defaultSettingEntity.setType((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}