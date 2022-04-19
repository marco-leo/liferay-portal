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

package com.liferay.commerce.qualifier.service.impl;

import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadata;
import com.liferay.commerce.qualifier.metadata.CommerceQualifierMetadataRegistry;
import com.liferay.commerce.qualifier.model.CommerceQualifierEntry;
import com.liferay.commerce.qualifier.model.CommerceQualifierEntryTable;
import com.liferay.commerce.qualifier.service.base.CommerceQualifierEntryLocalServiceBaseImpl;
import com.liferay.commerce.qualifier.util.CommerceQualifierUtil;
import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.DSLFunctionFactoryUtil;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.Table;
import com.liferay.petra.sql.dsl.expression.Expression;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.petra.sql.dsl.query.FromStep;
import com.liferay.petra.sql.dsl.query.GroupByStep;
import com.liferay.petra.sql.dsl.query.HavingStep;
import com.liferay.petra.sql.dsl.query.JoinStep;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = "model.class.name=com.liferay.commerce.qualifier.model.CommerceQualifierEntry",
	service = AopService.class
)
public class CommerceQualifierEntryLocalServiceImpl
	extends CommerceQualifierEntryLocalServiceBaseImpl {

	@Override
	public CommerceQualifierEntry addCommerceQualifierEntry(
			long userId, String sourceClassName, long sourceClassPK,
			String targetClassName, long targetClassPK, boolean targetDefault)
		throws PortalException {

		CommerceQualifierEntry commerceQualifierEntry =
			commerceQualifierEntryPersistence.create(
				counterLocalService.increment());

		User user = userLocalService.getUser(userId);

		commerceQualifierEntry.setCompanyId(user.getCompanyId());
		commerceQualifierEntry.setUserId(user.getUserId());
		commerceQualifierEntry.setUserName(user.getFullName());

		commerceQualifierEntry.setSourceClassNameId(
			classNameLocalService.getClassNameId(sourceClassName));
		commerceQualifierEntry.setSourceClassPK(sourceClassPK);
		commerceQualifierEntry.setTargetClassNameId(
			classNameLocalService.getClassNameId(targetClassName));
		commerceQualifierEntry.setTargetClassPK(targetClassPK);
		commerceQualifierEntry.setTargetDefault(targetDefault);

		commerceQualifierEntry = commerceQualifierEntryPersistence.update(
			commerceQualifierEntry);

		reindexCommerceQualifierSource(sourceClassName, sourceClassPK);

		return commerceQualifierEntry;
	}

	@Override
	public void deleteCommerceQualifierEntriesBySource(
			String sourceClassName, long sourceClassPK)
		throws PortalException {

		List<CommerceQualifierEntry> commerceQualifiers =
			commerceQualifierEntryPersistence.findByS_S(
				classNameLocalService.getClassNameId(sourceClassName),
				sourceClassPK);

		for (CommerceQualifierEntry commerceQualifierEntry :
				commerceQualifiers) {

			commerceQualifierEntryLocalService.deleteCommerceQualifierEntry(
				commerceQualifierEntry);
		}
	}

	@Override
	public void deleteCommerceQualifierEntriesBySource(
			String sourceClassName, long sourceClassPK, String targetClassName)
		throws PortalException {

		List<CommerceQualifierEntry> commerceQualifiers =
			commerceQualifierEntryPersistence.findByS_S_T(
				classNameLocalService.getClassNameId(sourceClassName),
				sourceClassPK,
				classNameLocalService.getClassNameId(targetClassName));

		for (CommerceQualifierEntry commerceQualifierEntry :
				commerceQualifiers) {

			commerceQualifierEntryLocalService.deleteCommerceQualifierEntry(
				commerceQualifierEntry);
		}
	}

	@Override
	public void deleteCommerceQualifierEntriesByTarget(
			String targetClassName, long targetClassPK)
		throws PortalException {

		List<CommerceQualifierEntry> commerceQualifiers =
			commerceQualifierEntryPersistence.findByT_T(
				classNameLocalService.getClassNameId(targetClassName),
				targetClassPK);

		for (CommerceQualifierEntry commerceQualifierEntry :
				commerceQualifiers) {

			commerceQualifierEntryLocalService.deleteCommerceQualifierEntry(
				commerceQualifierEntry);
		}
	}

	@Override
	public void deleteCommerceQualifierEntriesByTarget(
			String sourceClassName, String targetClassName, long targetClassPK)
		throws PortalException {

		List<CommerceQualifierEntry> commerceQualifiers =
			commerceQualifierEntryPersistence.findByS_T_T(
				classNameLocalService.getClassNameId(sourceClassName),
				classNameLocalService.getClassNameId(targetClassName),
				targetClassPK);

		for (CommerceQualifierEntry commerceQualifierEntry :
				commerceQualifiers) {

			commerceQualifierEntryLocalService.deleteCommerceQualifierEntry(
				commerceQualifierEntry);
		}
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceQualifierEntry deleteCommerceQualifierEntry(
			CommerceQualifierEntry commerceQualifierEntry)
		throws PortalException {

		commerceQualifierEntryPersistence.remove(commerceQualifierEntry);

		reindexCommerceQualifierSource(
			commerceQualifierEntry.getSourceClassNameId(),
			commerceQualifierEntry.getSourceClassPK());

		return commerceQualifierEntry;
	}

	@Override
	public CommerceQualifierEntry deleteCommerceQualifierEntry(
			long commerceQualifierEntryId)
		throws PortalException {

		CommerceQualifierEntry commerceQualifierEntry =
			commerceQualifierEntryPersistence.findByPrimaryKey(
				commerceQualifierEntryId);

		return commerceQualifierEntryLocalService.deleteCommerceQualifierEntry(
			commerceQualifierEntry);
	}

	@Override
	public CommerceQualifierEntry fetchCommerceQualifierEntry(
		String sourceClassName, long sourceClassPK, String targetClassName,
		long targetClassPK) {

		return commerceQualifierEntryPersistence.fetchByS_S_T_T(
			classNameLocalService.getClassNameId(sourceClassName),
			sourceClassPK,
			classNameLocalService.getClassNameId(targetClassName),
			targetClassPK);
	}

	@Override
	public List<CommerceQualifierEntry> getCommerceQualifierEntriesBySource(
			long companyId, String sourceClassName, long sourceClassPK,
			String targetClassName, String keywords, int start, int end)
		throws PortalException {

		CommerceQualifierMetadata targetCommerceQualifierMetadata =
			_commerceQualifierMetadataRegistry.getCommerceQualifierMetadata(
				targetClassName);

		if (targetCommerceQualifierMetadata == null) {
			return Collections.emptyList();
		}

		Column<?, Long> primaryKeyColumn =
			targetCommerceQualifierMetadata.getPrimaryKeyColumn();

		return dslQuery(
			_getGroupByStep(
				companyId,
				DSLQueryFactoryUtil.selectDistinct(
					CommerceQualifierEntryTable.INSTANCE),
				targetCommerceQualifierMetadata.getTable(),
				primaryKeyColumn.eq(
					CommerceQualifierEntryTable.INSTANCE.targetClassPK),
				false, sourceClassName, sourceClassPK,
				targetCommerceQualifierMetadata.getModelClassName(), keywords,
				targetCommerceQualifierMetadata.getKeywordsColumn()
			).limit(
				start, end
			));
	}

	@Override
	public int getCommerceQualifierEntriesBySourceCount(
			long companyId, String sourceClassName, long sourceClassPK,
			String targetClassName, String keywords)
		throws PortalException {

		CommerceQualifierMetadata targetCommerceQualifierMetadata =
			_commerceQualifierMetadataRegistry.getCommerceQualifierMetadata(
				targetClassName);

		if (targetCommerceQualifierMetadata == null) {
			return 0;
		}

		Column<?, Long> primaryKeyColumn =
			targetCommerceQualifierMetadata.getPrimaryKeyColumn();

		return dslQueryCount(
			_getGroupByStep(
				companyId,
				DSLQueryFactoryUtil.countDistinct(
					CommerceQualifierEntryTable.INSTANCE.
						commerceQualifierEntryId),
				targetCommerceQualifierMetadata.getTable(),
				primaryKeyColumn.eq(
					CommerceQualifierEntryTable.INSTANCE.targetClassPK),
				false, sourceClassName, sourceClassPK,
				targetCommerceQualifierMetadata.getModelClassName(), keywords,
				targetCommerceQualifierMetadata.getKeywordsColumn()));
	}

	@Override
	public List<CommerceQualifierEntry> getCommerceQualifierEntriesByTarget(
			long companyId, String sourceClassName, String targetClassName,
			long targetClassPK, String keywords, int start, int end)
		throws PortalException {

		CommerceQualifierMetadata sourceCommerceQualifierMetadata =
			_commerceQualifierMetadataRegistry.getCommerceQualifierMetadata(
				sourceClassName);

		if (sourceCommerceQualifierMetadata == null) {
			return Collections.emptyList();
		}

		Column<?, Long> primaryKeyColumn =
			sourceCommerceQualifierMetadata.getPrimaryKeyColumn();

		return dslQuery(
			_getGroupByStep(
				companyId,
				DSLQueryFactoryUtil.selectDistinct(
					CommerceQualifierEntryTable.INSTANCE),
				sourceCommerceQualifierMetadata.getTable(),
				primaryKeyColumn.eq(
					CommerceQualifierEntryTable.INSTANCE.sourceClassPK),
				true, targetClassName, targetClassPK,
				sourceCommerceQualifierMetadata.getModelClassName(), keywords,
				sourceCommerceQualifierMetadata.getKeywordsColumn()
			).limit(
				start, end
			));
	}

	@Override
	public int getCommerceQualifierEntriesByTargetCount(
			long companyId, String sourceClassName, String targetClassName,
			long targetClassPK, String keywords)
		throws PortalException {

		CommerceQualifierMetadata sourceCommerceQualifierMetadata =
			_commerceQualifierMetadataRegistry.getCommerceQualifierMetadata(
				sourceClassName);

		if (sourceCommerceQualifierMetadata == null) {
			return 0;
		}

		Column<?, Long> primaryKeyColumn =
			sourceCommerceQualifierMetadata.getPrimaryKeyColumn();

		return dslQueryCount(
			_getGroupByStep(
				companyId,
				DSLQueryFactoryUtil.countDistinct(
					CommerceQualifierEntryTable.INSTANCE.
						commerceQualifierEntryId),
				sourceCommerceQualifierMetadata.getTable(),
				primaryKeyColumn.eq(
					CommerceQualifierEntryTable.INSTANCE.sourceClassPK),
				true, targetClassName, targetClassPK,
				sourceCommerceQualifierMetadata.getModelClassName(), keywords,
				sourceCommerceQualifierMetadata.getKeywordsColumn()));
	}

	@Override
	public <E> List<E> getCommerceQualifierEntriesSourcesByTargets(
		long companyId, boolean exclusive, Class<E> sourceClass,
		Map<String, Object> sourceExtraParameterMap,
		Map<String, Object> targetCommerceQualifierMap) {

		CommerceQualifierMetadata sourceCommerceQualifierMetadata =
			_commerceQualifierMetadataRegistry.getCommerceQualifierMetadata(
				sourceClass.getName());

		if (sourceCommerceQualifierMetadata == null) {
			return Collections.emptyList();
		}

		PersistedModelLocalService persistedModelLocalService =
			sourceCommerceQualifierMetadata.getPersistedModelLocalService();

		return (List<E>)persistedModelLocalService.dslQuery(
			_getHavingStep(
				companyId, exclusive,
				DSLQueryFactoryUtil.select(
					sourceCommerceQualifierMetadata.getTable()),
				sourceCommerceQualifierMetadata, sourceExtraParameterMap,
				targetCommerceQualifierMap
			).orderBy(
				sourceCommerceQualifierMetadata.getOrderByExpressions()
			));
	}

	@Override
	public CommerceQualifierEntry updateCommerceQualifierEntry(
			long commerceQualifierEntryId, boolean targetDefault)
		throws PortalException {

		CommerceQualifierEntry commerceQualifierEntry =
			commerceQualifierEntryPersistence.findByPrimaryKey(
				commerceQualifierEntryId);

		commerceQualifierEntry.setTargetDefault(targetDefault);

		return commerceQualifierEntryPersistence.update(commerceQualifierEntry);
	}

	protected void reindexCommerceQualifierSource(
			long sourceClassNameId, long sourceClassPK)
		throws PortalException {

		ClassName sourceClassName = classNameLocalService.getClassName(
			sourceClassNameId);

		reindexCommerceQualifierSource(
			sourceClassName.getClassName(), sourceClassPK);
	}

	protected void reindexCommerceQualifierSource(
			String sourceClassName, long sourceClassPK)
		throws PortalException {

		Indexer<?> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			sourceClassName);

		indexer.reindex(sourceClassName, sourceClassPK);
	}

	private GroupByStep _getGroupByStep(
		long companyId, FromStep fromStep, Table innerJoinTable,
		Predicate innerJoinPredicate, boolean target, String className1,
		Long classPK1, String className2, String keywords,
		Expression<String> keywordsPredicateExpression) {

		JoinStep joinStep = fromStep.from(
			CommerceQualifierEntryTable.INSTANCE
		).innerJoinON(
			innerJoinTable, innerJoinPredicate
		);

		return joinStep.where(
			() -> CommerceQualifierEntryTable.INSTANCE.companyId.eq(
				companyId
			).and(
				() -> {
					if (target) {
						return CommerceQualifierEntryTable.INSTANCE.
							targetClassNameId.eq(
								classNameLocalService.getClassNameId(className1)
							).and(
								CommerceQualifierEntryTable.INSTANCE.
									targetClassPK.eq(classPK1)
							).and(
								CommerceQualifierEntryTable.INSTANCE.
									sourceClassNameId.eq(
										classNameLocalService.getClassNameId(
											className2))
							);
					}

					return CommerceQualifierEntryTable.INSTANCE.
						sourceClassNameId.eq(
							classNameLocalService.getClassNameId(className1)
						).and(
							CommerceQualifierEntryTable.INSTANCE.sourceClassPK.
								eq(classPK1)
						).and(
							CommerceQualifierEntryTable.INSTANCE.
								targetClassNameId.eq(
									classNameLocalService.getClassNameId(
										className2))
						);
				}
			).and(
				() -> {
					if (Validator.isNotNull(keywords)) {
						return Predicate.withParentheses(
							_customSQL.getKeywordsPredicate(
								DSLFunctionFactoryUtil.lower(
									keywordsPredicateExpression),
								_customSQL.keywords(keywords, true)));
					}

					return null;
				}
			));
	}

	private HavingStep _getHavingStep(
		long companyId, boolean exclusive, FromStep fromStep,
		CommerceQualifierMetadata sourceCommerceQualifierMetadata,
		Map<String, Object> sourceExtraParameterMap,
		Map<String, Object> targetCommerceQualifierMap) {

		Table sourceTable = sourceCommerceQualifierMetadata.getTable();

		JoinStep joinStep = fromStep.from(sourceTable);

		Predicate predicate = sourceTable.getColumn(
			"companyId"
		).eq(
			companyId
		);

		for (Map.Entry<String, Object> sourceExtraParameterEntry :
				sourceExtraParameterMap.entrySet()) {

			predicate = predicate.and(
				() -> {
					Object value = sourceExtraParameterEntry.getValue();

					if (value instanceof Object[]) {
						return sourceTable.getColumn(
							sourceExtraParameterEntry.getKey()
						).in(
							(Object[])value
						);
					}

					return sourceTable.getColumn(
						sourceExtraParameterEntry.getKey()
					).eq(
						value
					);
				});
		}

		List<Expression<?>> groupByExpressions = new ArrayList<>(
			sourceTable.getColumns());

		String[] allowedTargetClassNames =
			sourceCommerceQualifierMetadata.getAllowedTargetClassNames();

		for (String allowedTargetClassName : allowedTargetClassNames) {
			CommerceQualifierMetadata targetCommerceQualifierMetadata =
				_commerceQualifierMetadataRegistry.getCommerceQualifierMetadata(
					allowedTargetClassName);

			if (targetCommerceQualifierMetadata == null) {
				continue;
			}

			Table targetTable = targetCommerceQualifierMetadata.getTable();

			CommerceQualifierEntryTable aliasTable =
				CommerceQualifierUtil.getCommerceQualifierTableAlias(
					sourceTable.getName(), targetTable.getName());

			groupByExpressions.add(aliasTable.commerceQualifierEntryId);

			joinStep = joinStep.leftJoinOn(
				aliasTable,
				_getPredicate(
					aliasTable.sourceClassNameId,
					sourceCommerceQualifierMetadata.getModelClassName(),
					aliasTable.sourceClassPK,
					sourceCommerceQualifierMetadata.getPrimaryKeyColumn(),
					aliasTable.targetClassNameId, allowedTargetClassName));

			predicate = predicate.and(
				() -> {
					if ((targetCommerceQualifierMap == null) && exclusive) {
						return aliasTable.commerceQualifierEntryId.isNull();
					}

					if (targetCommerceQualifierMap == null) {
						return null;
					}

					Object value = targetCommerceQualifierMap.get(
						allowedTargetClassName);

					if ((value == null) && exclusive) {
						return aliasTable.commerceQualifierEntryId.isNull();
					}

					if (value == null) {
						return null;
					}

					if (value instanceof Long) {
						return aliasTable.targetClassPK.eq((Long)value);
					}
					else if (value instanceof long[]) {
						long[] valueArray = (long[])value;

						if (valueArray.length == 0) {
							valueArray = new long[] {0};
						}

						LongStream longStream = Arrays.stream(valueArray);

						return aliasTable.targetClassPK.in(
							longStream.boxed(
							).toArray(
								Long[]::new
							));
					}

					return null;
				});
		}

		return joinStep.where(
			predicate
		).groupBy(
			groupByExpressions.toArray(new Expression<?>[0])
		);
	}

	private Predicate _getPredicate(
		Column<CommerceQualifierEntryTable, Long> sourceClassNameIdColumn,
		String sourceClassName,
		Column<CommerceQualifierEntryTable, Long> sourceClassPKColumn,
		Column<?, Long> sourceCommerceQualifierPrimaryColumn,
		Column<CommerceQualifierEntryTable, Long> targetClassNameIdColumn,
		String targetClassName) {

		return targetClassNameIdColumn.eq(
			classNameLocalService.getClassNameId(targetClassName)
		).and(
			sourceClassNameIdColumn.eq(
				classNameLocalService.getClassNameId(sourceClassName))
		).and(
			sourceClassPKColumn.eq(sourceCommerceQualifierPrimaryColumn)
		);
	}

	@Reference
	private CommerceQualifierMetadataRegistry
		_commerceQualifierMetadataRegistry;

	@Reference
	private CustomSQL _customSQL;

}