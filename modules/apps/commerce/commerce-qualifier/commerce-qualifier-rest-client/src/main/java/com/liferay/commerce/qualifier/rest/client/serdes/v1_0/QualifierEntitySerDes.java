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

import com.liferay.commerce.qualifier.rest.client.dto.v1_0.QualifierEntity;
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
public class QualifierEntitySerDes {

	public static QualifierEntity toDTO(String json) {
		QualifierEntityJSONParser qualifierEntityJSONParser =
			new QualifierEntityJSONParser();

		return qualifierEntityJSONParser.parseToDTO(json);
	}

	public static QualifierEntity[] toDTOs(String json) {
		QualifierEntityJSONParser qualifierEntityJSONParser =
			new QualifierEntityJSONParser();

		return qualifierEntityJSONParser.parseToDTOs(json);
	}

	public static String toJSON(QualifierEntity qualifierEntity) {
		if (qualifierEntity == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (qualifierEntity.getExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(qualifierEntity.getExternalReferenceCode()));

			sb.append("\"");
		}

		if (qualifierEntity.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(qualifierEntity.getId());
		}

		if (qualifierEntity.getInfo1() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"info1\": ");

			sb.append("\"");

			sb.append(_escape(qualifierEntity.getInfo1()));

			sb.append("\"");
		}

		if (qualifierEntity.getInfo2() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"info2\": ");

			sb.append("\"");

			sb.append(_escape(qualifierEntity.getInfo2()));

			sb.append("\"");
		}

		if (qualifierEntity.getInfo3() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"info3\": ");

			sb.append("\"");

			sb.append(_escape(qualifierEntity.getInfo3()));

			sb.append("\"");
		}

		if (qualifierEntity.getInfo4() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"info4\": ");

			sb.append("\"");

			sb.append(_escape(qualifierEntity.getInfo4()));

			sb.append("\"");
		}

		if (qualifierEntity.getInfo5() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"info5\": ");

			sb.append("\"");

			sb.append(_escape(qualifierEntity.getInfo5()));

			sb.append("\"");
		}

		if (qualifierEntity.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(qualifierEntity.getName()));

			sb.append("\"");
		}

		if (qualifierEntity.getType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(_escape(qualifierEntity.getType()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		QualifierEntityJSONParser qualifierEntityJSONParser =
			new QualifierEntityJSONParser();

		return qualifierEntityJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(QualifierEntity qualifierEntity) {
		if (qualifierEntity == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (qualifierEntity.getExternalReferenceCode() == null) {
			map.put("externalReferenceCode", null);
		}
		else {
			map.put(
				"externalReferenceCode",
				String.valueOf(qualifierEntity.getExternalReferenceCode()));
		}

		if (qualifierEntity.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(qualifierEntity.getId()));
		}

		if (qualifierEntity.getInfo1() == null) {
			map.put("info1", null);
		}
		else {
			map.put("info1", String.valueOf(qualifierEntity.getInfo1()));
		}

		if (qualifierEntity.getInfo2() == null) {
			map.put("info2", null);
		}
		else {
			map.put("info2", String.valueOf(qualifierEntity.getInfo2()));
		}

		if (qualifierEntity.getInfo3() == null) {
			map.put("info3", null);
		}
		else {
			map.put("info3", String.valueOf(qualifierEntity.getInfo3()));
		}

		if (qualifierEntity.getInfo4() == null) {
			map.put("info4", null);
		}
		else {
			map.put("info4", String.valueOf(qualifierEntity.getInfo4()));
		}

		if (qualifierEntity.getInfo5() == null) {
			map.put("info5", null);
		}
		else {
			map.put("info5", String.valueOf(qualifierEntity.getInfo5()));
		}

		if (qualifierEntity.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(qualifierEntity.getName()));
		}

		if (qualifierEntity.getType() == null) {
			map.put("type", null);
		}
		else {
			map.put("type", String.valueOf(qualifierEntity.getType()));
		}

		return map;
	}

	public static class QualifierEntityJSONParser
		extends BaseJSONParser<QualifierEntity> {

		@Override
		protected QualifierEntity createDTO() {
			return new QualifierEntity();
		}

		@Override
		protected QualifierEntity[] createDTOArray(int size) {
			return new QualifierEntity[size];
		}

		@Override
		protected void setField(
			QualifierEntity qualifierEntity, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "externalReferenceCode")) {
				if (jsonParserFieldValue != null) {
					qualifierEntity.setExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					qualifierEntity.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "info1")) {
				if (jsonParserFieldValue != null) {
					qualifierEntity.setInfo1((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "info2")) {
				if (jsonParserFieldValue != null) {
					qualifierEntity.setInfo2((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "info3")) {
				if (jsonParserFieldValue != null) {
					qualifierEntity.setInfo3((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "info4")) {
				if (jsonParserFieldValue != null) {
					qualifierEntity.setInfo4((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "info5")) {
				if (jsonParserFieldValue != null) {
					qualifierEntity.setInfo5((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					qualifierEntity.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				if (jsonParserFieldValue != null) {
					qualifierEntity.setType((String)jsonParserFieldValue);
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