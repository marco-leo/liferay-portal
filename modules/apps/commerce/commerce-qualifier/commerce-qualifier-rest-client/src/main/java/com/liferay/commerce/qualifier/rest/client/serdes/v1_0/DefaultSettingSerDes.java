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

import com.liferay.commerce.qualifier.rest.client.dto.v1_0.DefaultSetting;
import com.liferay.commerce.qualifier.rest.client.dto.v1_0.DefaultSettingEntity;
import com.liferay.commerce.qualifier.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Riccardo Alberti
 * @generated
 */
@Generated("")
public class DefaultSettingSerDes {

	public static DefaultSetting toDTO(String json) {
		DefaultSettingJSONParser defaultSettingJSONParser =
			new DefaultSettingJSONParser();

		return defaultSettingJSONParser.parseToDTO(json);
	}

	public static DefaultSetting[] toDTOs(String json) {
		DefaultSettingJSONParser defaultSettingJSONParser =
			new DefaultSettingJSONParser();

		return defaultSettingJSONParser.parseToDTOs(json);
	}

	public static String toJSON(DefaultSetting defaultSetting) {
		if (defaultSetting == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (defaultSetting.getActions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(defaultSetting.getActions()));
		}

		if (defaultSetting.getCustomFields() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"customFields\": ");

			sb.append(_toJSON(defaultSetting.getCustomFields()));
		}

		if (defaultSetting.getDefaultSettingEntities() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"defaultSettingEntities\": ");

			sb.append("[");

			for (int i = 0;
				 i < defaultSetting.getDefaultSettingEntities().length; i++) {

				sb.append(
					String.valueOf(
						defaultSetting.getDefaultSettingEntities()[i]));

				if ((i + 1) <
						defaultSetting.getDefaultSettingEntities().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (defaultSetting.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(defaultSetting.getId());
		}

		if (defaultSetting.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(defaultSetting.getName()));

			sb.append("\"");
		}

		if (defaultSetting.getParameterSettings() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"parameterSettings\": ");

			sb.append("\"");

			sb.append(_escape(defaultSetting.getParameterSettings()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		DefaultSettingJSONParser defaultSettingJSONParser =
			new DefaultSettingJSONParser();

		return defaultSettingJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(DefaultSetting defaultSetting) {
		if (defaultSetting == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (defaultSetting.getActions() == null) {
			map.put("actions", null);
		}
		else {
			map.put("actions", String.valueOf(defaultSetting.getActions()));
		}

		if (defaultSetting.getCustomFields() == null) {
			map.put("customFields", null);
		}
		else {
			map.put(
				"customFields",
				String.valueOf(defaultSetting.getCustomFields()));
		}

		if (defaultSetting.getDefaultSettingEntities() == null) {
			map.put("defaultSettingEntities", null);
		}
		else {
			map.put(
				"defaultSettingEntities",
				String.valueOf(defaultSetting.getDefaultSettingEntities()));
		}

		if (defaultSetting.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(defaultSetting.getId()));
		}

		if (defaultSetting.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(defaultSetting.getName()));
		}

		if (defaultSetting.getParameterSettings() == null) {
			map.put("parameterSettings", null);
		}
		else {
			map.put(
				"parameterSettings",
				String.valueOf(defaultSetting.getParameterSettings()));
		}

		return map;
	}

	public static class DefaultSettingJSONParser
		extends BaseJSONParser<DefaultSetting> {

		@Override
		protected DefaultSetting createDTO() {
			return new DefaultSetting();
		}

		@Override
		protected DefaultSetting[] createDTOArray(int size) {
			return new DefaultSetting[size];
		}

		@Override
		protected void setField(
			DefaultSetting defaultSetting, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actions")) {
				if (jsonParserFieldValue != null) {
					defaultSetting.setActions(
						(Map)DefaultSettingSerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "customFields")) {
				if (jsonParserFieldValue != null) {
					defaultSetting.setCustomFields(
						(Map)DefaultSettingSerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "defaultSettingEntities")) {

				if (jsonParserFieldValue != null) {
					defaultSetting.setDefaultSettingEntities(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> DefaultSettingEntitySerDes.toDTO(
								(String)object)
						).toArray(
							size -> new DefaultSettingEntity[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					defaultSetting.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					defaultSetting.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "parameterSettings")) {
				if (jsonParserFieldValue != null) {
					defaultSetting.setParameterSettings(
						(String)jsonParserFieldValue);
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