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

package com.liferay.commerce.qualifier.rest.client.dto.v1_0;

import com.liferay.commerce.qualifier.rest.client.function.UnsafeSupplier;
import com.liferay.commerce.qualifier.rest.client.serdes.v1_0.QualifierSerDes;

import java.io.Serializable;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Riccardo Alberti
 * @generated
 */
@Generated("")
public class Qualifier implements Cloneable, Serializable {

	public static Qualifier toDTO(String json) {
		return QualifierSerDes.toDTO(json);
	}

	public Map<String, Map<String, String>> getActions() {
		return actions;
	}

	public void setActions(Map<String, Map<String, String>> actions) {
		this.actions = actions;
	}

	public void setActions(
		UnsafeSupplier<Map<String, Map<String, String>>, Exception>
			actionsUnsafeSupplier) {

		try {
			actions = actionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, Map<String, String>> actions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long id;

	public QualifierEntity getQualifierEntity() {
		return qualifierEntity;
	}

	public void setQualifierEntity(QualifierEntity qualifierEntity) {
		this.qualifierEntity = qualifierEntity;
	}

	public void setQualifierEntity(
		UnsafeSupplier<QualifierEntity, Exception>
			qualifierEntityUnsafeSupplier) {

		try {
			qualifierEntity = qualifierEntityUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected QualifierEntity qualifierEntity;

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public void setSourceId(
		UnsafeSupplier<Long, Exception> sourceIdUnsafeSupplier) {

		try {
			sourceId = sourceIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long sourceId;

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public void setSourceName(
		UnsafeSupplier<String, Exception> sourceNameUnsafeSupplier) {

		try {
			sourceName = sourceNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String sourceName;

	public Boolean getTargetDefault() {
		return targetDefault;
	}

	public void setTargetDefault(Boolean targetDefault) {
		this.targetDefault = targetDefault;
	}

	public void setTargetDefault(
		UnsafeSupplier<Boolean, Exception> targetDefaultUnsafeSupplier) {

		try {
			targetDefault = targetDefaultUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean targetDefault;

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public void setTargetId(
		UnsafeSupplier<Long, Exception> targetIdUnsafeSupplier) {

		try {
			targetId = targetIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long targetId;

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public void setTargetName(
		UnsafeSupplier<String, Exception> targetNameUnsafeSupplier) {

		try {
			targetName = targetNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String targetName;

	@Override
	public Qualifier clone() throws CloneNotSupportedException {
		return (Qualifier)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Qualifier)) {
			return false;
		}

		Qualifier qualifier = (Qualifier)object;

		return Objects.equals(toString(), qualifier.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return QualifierSerDes.toJSON(this);
	}

}