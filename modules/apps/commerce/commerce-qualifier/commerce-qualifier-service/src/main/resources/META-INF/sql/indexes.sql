create index IX_7FA28C2A on CommerceDefaultSettingRel (classNameId, classPK);
create index IX_9C6B5C7 on CommerceDefaultSettingRel (commerceDefaultSettingId, type_[$COLUMN_LENGTH:75$]);

create unique index IX_82120033 on CommerceQualifierEntry (sourceClassNameId, sourceClassPK, targetClassNameId, targetClassPK);
create index IX_C11F2CFF on CommerceQualifierEntry (sourceClassNameId, targetClassNameId, targetClassPK);
create index IX_D4BE2EFE on CommerceQualifierEntry (targetClassNameId, targetClassPK);