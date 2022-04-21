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

import com.liferay.commerce.qualifier.rest.client.dto.v1_0.Qualifier;
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
public class QualifierSerDes {

	public static Qualifier toDTO(String json) {
		QualifierJSONParser qualifierJSONParser = new QualifierJSONParser();

		return qualifierJSONParser.parseToDTO(json);
	}

	public static Qualifier[] toDTOs(String json) {
		QualifierJSONParser qualifierJSONParser = new QualifierJSONParser();

		return qualifierJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Qualifier qualifier) {
		if (qualifier == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (qualifier.getActions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(qualifier.getActions()));
		}

		if (qualifier.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(qualifier.getId());
		}

		if (qualifier.getQualifierEntity() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"qualifierEntity\": ");

			sb.append(String.valueOf(qualifier.getQualifierEntity()));
		}

		if (qualifier.getSourceId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"sourceId\": ");

			sb.append(qualifier.getSourceId());
		}

		if (qualifier.getSourceName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"sourceName\": ");

			sb.append("\"");

			sb.append(_escape(qualifier.getSourceName()));

			sb.append("\"");
		}

		if (qualifier.getTargetId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"targetId\": ");

			sb.append(qualifier.getTargetId());
		}

		if (qualifier.getTargetName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"targetName\": ");

			sb.append("\"");

			sb.append(_escape(qualifier.getTargetName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		QualifierJSONParser qualifierJSONParser = new QualifierJSONParser();

		return qualifierJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Qualifier qualifier) {
		if (qualifier == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (qualifier.getActions() == null) {
			map.put("actions", null);
		}
		else {
			map.put("actions", String.valueOf(qualifier.getActions()));
		}

		if (qualifier.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(qualifier.getId()));
		}

		if (qualifier.getQualifierEntity() == null) {
			map.put("qualifierEntity", null);
		}
		else {
			map.put(
				"qualifierEntity",
				String.valueOf(qualifier.getQualifierEntity()));
		}

		if (qualifier.getSourceId() == null) {
			map.put("sourceId", null);
		}
		else {
			map.put("sourceId", String.valueOf(qualifier.getSourceId()));
		}

		if (qualifier.getSourceName() == null) {
			map.put("sourceName", null);
		}
		else {
			map.put("sourceName", String.valueOf(qualifier.getSourceName()));
		}

		if (qualifier.getTargetId() == null) {
			map.put("targetId", null);
		}
		else {
			map.put("targetId", String.valueOf(qualifier.getTargetId()));
		}

		if (qualifier.getTargetName() == null) {
			map.put("targetName", null);
		}
		else {
			map.put("targetName", String.valueOf(qualifier.getTargetName()));
		}

		return map;
	}

	public static class QualifierJSONParser extends BaseJSONParser<Qualifier> {

		@Override
		protected Qualifier createDTO() {
			return new Qualifier();
		}

		@Override
		protected Qualifier[] createDTOArray(int size) {
			return new Qualifier[size];
		}

		@Override
		protected void setField(
			Qualifier qualifier, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actions")) {
				if (jsonParserFieldValue != null) {
					qualifier.setActions(
						(Map)QualifierSerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					qualifier.setId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "qualifierEntity")) {
				if (jsonParserFieldValue != null) {
					qualifier.setQualifierEntity(
						QualifierEntitySerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "sourceId")) {
				if (jsonParserFieldValue != null) {
					qualifier.setSourceId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "sourceName")) {
				if (jsonParserFieldValue != null) {
					qualifier.setSourceName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "targetId")) {
				if (jsonParserFieldValue != null) {
					qualifier.setTargetId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "targetName")) {
				if (jsonParserFieldValue != null) {
					qualifier.setTargetName((String)jsonParserFieldValue);
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