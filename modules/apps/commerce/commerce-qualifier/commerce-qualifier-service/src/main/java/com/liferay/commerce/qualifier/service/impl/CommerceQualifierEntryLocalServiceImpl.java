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
import com.liferay.commerce.qualifier.model.CommerceDefaultSettingRelTable;
import com.liferay.commerce.qualifier.model.CommerceQualifierEntry;
import com.liferay.commerce.qualifier.model.CommerceQualifierEntryTable;
import com.liferay.commerce.qualifier.search.context.CommerceQualifierSearchContext;
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
import com.liferay.petra.sql.dsl.query.JoinStep;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
			String targetClassName, long targetClassPK)
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

		commerceQualifierEntry = commerceQualifierEntryPersistence.update(
			commerceQualifierEntry);

		reindexCommerceQualifierSource(sourceClassName, sourceClassPK);

		return commerceQualifierEntry;
	}

	@Override
	public void cleanCommerceQualifierEntryCache(
		long companyId, String sourceClassName) {

		PortalCache<String, Serializable> portalCache =
			(PortalCache<String, Serializable>)_multiVMPool.getPortalCache(
				sourceClassName + StringPool.POUND + companyId);

		portalCache.removeAll();
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
		long companyId, Class<E> sourceClass,
		CommerceQualifierSearchContext commerceQualifierSearchContext) {

		CommerceQualifierMetadata sourceCommerceQualifierMetadata =
			_commerceQualifierMetadataRegistry.getCommerceQualifierMetadata(
				sourceClass.getName());

		if (sourceCommerceQualifierMetadata == null) {
			return Collections.emptyList();
		}

		PortalCache<String, Serializable> portalCache =
			(PortalCache<String, Serializable>)_multiVMPool.getPortalCache(
				sourceClass.getName() + StringPool.POUND + companyId);

		String cacheKey = String.valueOf(
			commerceQualifierSearchContext.hashCode());

		List<E> cachedCommerceQualifierEntriesSourcesByTargets =
			(List<E>)portalCache.get(cacheKey);

		if (cachedCommerceQualifierEntriesSourcesByTargets != null) {
			return cachedCommerceQualifierEntriesSourcesByTargets;
		}

		PersistedModelLocalService persistedModelLocalService =
			sourceCommerceQualifierMetadata.getPersistedModelLocalService();

		List<E> commerceQualifierEntriesSourcesByTargets =
			(List<E>)persistedModelLocalService.dslQuery(
				_groupByStep(
					companyId, commerceQualifierSearchContext.isExclusive(),
					DSLQueryFactoryUtil.select(
						sourceCommerceQualifierMetadata.getTable()),
					sourceCommerceQualifierMetadata,
					commerceQualifierSearchContext.
						getSourceAdditionalAttributes(),
					commerceQualifierSearchContext.getTargetAttributes()
				).orderBy(
					sourceCommerceQualifierMetadata.getOrderByExpressions(
						commerceQualifierSearchContext.getTargetAttributes())
				));

		portalCache.put(
			cacheKey, (Serializable)commerceQualifierEntriesSourcesByTargets);

		return commerceQualifierEntriesSourcesByTargets;
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

	private Predicate[] _getLeftJoinQueryPredicates(
		CommerceDefaultSettingRelTable commerceDefaultSettingRelTable,
		CommerceDefaultSettingRelTable
			innerJoinCommerceDefaultSettingRelTableAlias,
		Map<String, ?> targetCommerceQualifierMap) {

		Predicate predicate = null;

		List<Long> classNameIds = new LinkedList<>();
		List<Long> classPKs = new LinkedList<>();

		for (Map.Entry<String, ?> targetCommerceQualifierEntry :
				targetCommerceQualifierMap.entrySet()) {

			Object value = targetCommerceQualifierEntry.getValue();

			if (value == null) {
				continue;
			}

			long classNameId = classNameLocalService.getClassNameId(
				targetCommerceQualifierEntry.getKey());

			classNameIds.add(classNameId);

			Predicate subpredicate =
				innerJoinCommerceDefaultSettingRelTableAlias.classNameId.eq(
					classNameLocalService.getClassNameId(
						targetCommerceQualifierEntry.getKey()));

			Class<?> clazz = value.getClass();

			if (clazz.isArray()) {
				Long[] longValueArray = (Long[])value;

				if (longValueArray.length == 0) {
					longValueArray = new Long[] {0L};
				}

				subpredicate = subpredicate.and(
					innerJoinCommerceDefaultSettingRelTableAlias.classPK.in(
						longValueArray));

				Collections.addAll(classNameIds, longValueArray);
			}
			else {
				Long longValue = (Long)value;

				subpredicate = subpredicate.and(
					innerJoinCommerceDefaultSettingRelTableAlias.classPK.eq(
						longValue));

				classPKs.add(longValue);
			}

			if (predicate == null) {
				predicate = subpredicate.withParentheses();
			}
			else {
				predicate = predicate.or(subpredicate.withParentheses());
			}
		}

		return new Predicate[] {
			predicate.withParentheses(),
			Predicate.withParentheses(
				commerceDefaultSettingRelTable.classNameId.notIn(
					classNameIds.toArray(new Long[0])
				).or(
					commerceDefaultSettingRelTable.classPK.notIn(
						classPKs.toArray(new Long[0]))
				))
		};
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

	private Table<?> _getSourceCommerceDefaultSettingRelTableAlias(
		Map<String, ?> targetCommerceQualifierMap) {

		CommerceDefaultSettingRelTable
			innerJoinCommerceDefaultSettingRelTableAlias =
				CommerceDefaultSettingRelTable.INSTANCE.as(
					"innerJoinCommerceDefaultSettingRelTableAlias");

		CommerceDefaultSettingRelTable commerceDefaultSettingRelTableAlias =
			CommerceDefaultSettingRelTable.INSTANCE.as(
				"commerceDefaultSettingRelTableAlias");

		Predicate[] predicates = _getLeftJoinQueryPredicates(
			commerceDefaultSettingRelTableAlias,
			innerJoinCommerceDefaultSettingRelTableAlias,
			targetCommerceQualifierMap);

		Column<CommerceDefaultSettingRelTable, Long>
			innerJoinCommerceDefaultSettingRelTableAliasIdColumn =
				innerJoinCommerceDefaultSettingRelTableAlias.
					commerceDefaultSettingId;

		Column<CommerceDefaultSettingRelTable, String>
			innerJoinCommerceDefaultSettingRelTableAliasTypeColumn =
				innerJoinCommerceDefaultSettingRelTableAlias.type;

		Column<CommerceDefaultSettingRelTable, String>
			commerceDefaultSettingRelTableAliasTypeColumn =
				commerceDefaultSettingRelTableAlias.type;

		return DSLQueryFactoryUtil.selectDistinct(
			CommerceDefaultSettingRelTable.INSTANCE
		).from(
			CommerceDefaultSettingRelTable.INSTANCE
		).innerJoinON(
			innerJoinCommerceDefaultSettingRelTableAlias,
			innerJoinCommerceDefaultSettingRelTableAliasIdColumn.eq(
				CommerceDefaultSettingRelTable.INSTANCE.commerceDefaultSettingId
			).and(
				CommerceDefaultSettingRelTable.INSTANCE.type.eq("source")
			)
		).where(
			innerJoinCommerceDefaultSettingRelTableAliasTypeColumn.eq(
				"target"
			).and(
				predicates[0]
			).and(
				innerJoinCommerceDefaultSettingRelTableAliasIdColumn.notIn(
					DSLQueryFactoryUtil.selectDistinct(
						commerceDefaultSettingRelTableAlias.
							commerceDefaultSettingId
					).from(
						commerceDefaultSettingRelTableAlias
					).where(
						commerceDefaultSettingRelTableAliasTypeColumn.eq(
							"target"
						).and(
							innerJoinCommerceDefaultSettingRelTableAliasIdColumn.
								eq(
									commerceDefaultSettingRelTableAlias.
										commerceDefaultSettingId)
						).and(
							predicates[1]
						)
					))
			)
		).as(
			"sourceCommerceDefaultSettingRelTableAlias",
			CommerceDefaultSettingRelTable.INSTANCE
		);
	}

	private Predicate _getTargetPredicate(
		Column<?, Long> primaryKeyColumn, Column<?, Long> classPKColumn,
		Object value) {

		if (value == null) {
			return primaryKeyColumn.isNull();
		}

		Class<?> clazz = value.getClass();

		if (clazz.isArray()) {
			Long[] longValueArray = (Long[])value;

			if (longValueArray.length == 0) {
				longValueArray = new Long[] {0L};
			}

			return classPKColumn.in(
				longValueArray
			).or(
				primaryKeyColumn.isNull()
			).withParentheses();
		}

		return classPKColumn.eq(
			(Long)value
		).or(
			primaryKeyColumn.isNull()
		).withParentheses();
	}

	private GroupByStep _groupByStep(
		long companyId, boolean exclusive, FromStep fromStep,
		CommerceQualifierMetadata sourceCommerceQualifierMetadata,
		Map<String, ?> sourceExtraParameterMap,
		Map<String, ?> targetCommerceQualifierMap) {

		Table sourceTable = sourceCommerceQualifierMetadata.getTable();

		JoinStep joinStep = fromStep.from(sourceTable);

		Predicate predicate = sourceTable.getColumn(
			"companyId"
		).eq(
			companyId
		);

		if (sourceExtraParameterMap != null) {
			for (Map.Entry<String, ?> sourceExtraParameterEntry :
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
		}

		if (targetCommerceQualifierMap == null) {
			return joinStep.leftJoinOn(
				CommerceQualifierEntryTable.INSTANCE,
				CommerceQualifierEntryTable.INSTANCE.sourceClassNameId.eq(
					classNameLocalService.getClassNameId(
						sourceCommerceQualifierMetadata.getModelClassName())
				).and(
					CommerceQualifierEntryTable.INSTANCE.sourceClassPK.eq(
						sourceCommerceQualifierMetadata.getPrimaryKeyColumn())
				)
			).where(
				predicate.and(
					CommerceQualifierEntryTable.INSTANCE.
						commerceQualifierEntryId.isNull())
			);
		}

		Predicate subpredicate = null;

		String[] allowedTargetClassNames = Stream.of(
			sourceCommerceQualifierMetadata.getAllowedTargetClassNameGroups()
		).flatMap(
			Stream::of
		).toArray(
			String[]::new
		);

		for (String allowedTargetClassName : allowedTargetClassNames) {
			if (!(exclusive ||
				  targetCommerceQualifierMap.containsKey(
					  allowedTargetClassName))) {

				continue;
			}

			CommerceQualifierEntryTable tableAlias =
				CommerceQualifierUtil.getCommerceQualifierTableAlias(
					sourceCommerceQualifierMetadata.getModelClassName(),
					allowedTargetClassName);

			joinStep = joinStep.leftJoinOn(
				tableAlias,
				_getPredicate(
					tableAlias.sourceClassNameId,
					sourceCommerceQualifierMetadata.getModelClassName(),
					tableAlias.sourceClassPK,
					sourceCommerceQualifierMetadata.getPrimaryKeyColumn(),
					tableAlias.targetClassNameId, allowedTargetClassName));

			Predicate targetPredicate = _getTargetPredicate(
				tableAlias.commerceQualifierEntryId, tableAlias.targetClassPK,
				targetCommerceQualifierMap.get(allowedTargetClassName));

			if (subpredicate == null) {
				subpredicate = targetPredicate;
			}
			else {
				subpredicate = subpredicate.and(targetPredicate);
			}
		}

		Table sourceCommerceDefaultSettingRelTableAlias =
			_getSourceCommerceDefaultSettingRelTableAlias(
				targetCommerceQualifierMap);

		Column<?, Long> classNameIdColumn =
			sourceCommerceDefaultSettingRelTableAlias.getColumn("classNameId");

		Column<?, Long> classPKColumn =
			sourceCommerceDefaultSettingRelTableAlias.getColumn("classPK");

		joinStep = joinStep.leftJoinOn(
			sourceCommerceDefaultSettingRelTableAlias,
			classPKColumn.eq(
				sourceCommerceQualifierMetadata.getPrimaryKeyColumn()
			).and(
				classNameIdColumn.eq(
					classNameLocalService.getClassNameId(
						sourceCommerceQualifierMetadata.getModelClassName()))
			));

		if (subpredicate == null) {
			return joinStep.where(
				predicate.or(
					Predicate.and(
						classNameIdColumn.isNotNull(), classPKColumn.isNotNull()
					).withParentheses()));
		}

		return joinStep.where(
			predicate.and(
				subpredicate.withParentheses(
				).or(
					Predicate.and(
						classNameIdColumn.isNotNull(), classPKColumn.isNotNull()
					).withParentheses()
				)));
	}

	@Reference
	private CommerceQualifierMetadataRegistry
		_commerceQualifierMetadataRegistry;

	@Reference
	private CustomSQL _customSQL;

	@Reference
	private MultiVMPool _multiVMPool;

}