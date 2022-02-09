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

declare type TOperators = 'eq' | 'contains';
declare type TExceptionType = 'disabled' | 'hidden';
export declare type RuleDefinitionType = {
	actionTarget: string;
	exceptionType: TExceptionType;
	listener: string;
	match: string;
	operator: TOperators;
};
export declare function deepClone<R, K, V>(
	map: Map<K, V> | ReadonlyMap<K, V>
): R;
export declare function evaluateForm(formId: string): void | null;
export declare function getFormId(
	domNode: HTMLElement,
	inputName?: string
): string;
export declare function registerInputToForm(
	formId: string,
	name: string,
	value: any,
	setDisabled: (disabled: boolean) => void,
	setHidden: (disabled: boolean) => void
): {
	cleanup: () => void;
	update: (value: string) => void;
};
export declare function initializeLiferayForm(
	formId: string,
	rules: RuleDefinitionType[]
): void;
export {};
