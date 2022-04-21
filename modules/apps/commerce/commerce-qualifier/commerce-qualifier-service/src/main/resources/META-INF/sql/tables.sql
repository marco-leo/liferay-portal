create table CommerceDefaultSetting (
	mvccVersion LONG default 0 not null,
	commerceDefaultSettingId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null
);

create table CommerceDefaultSettingRel (
	mvccVersion LONG default 0 not null,
	commerceDefaultSettingRelId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	commerceDefaultSettingId LONG,
	priority DOUBLE,
	type_ VARCHAR(75) null
);

create table CommerceQualifierEntry (
	mvccVersion LONG default 0 not null,
	commerceQualifierEntryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	sourceClassNameId LONG,
	sourceClassPK LONG,
	targetClassNameId LONG,
	targetClassPK LONG
);