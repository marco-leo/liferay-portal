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

import {State} from '@liferay/frontend-js-state-web';

import formsEvaluatorsAtom, {TEvaluator} from '../atoms/formsEvaluatorsAtom';
import inputsLibraryAtom, {
	TInputsLibraryMap,
	TReadOnlyRegisteredInputMap,
	TReadonlyInputsLibraryMap,
	TRegisteredInput,
	TRegisteredInputMap,
} from '../atoms/inputsLibraryAtom';

type TOperators = 'eq' | 'contains';

type TExceptionType = 'disabled' | 'hidden';

export type RuleDefinitionType = {
	actionTarget: string;
	exceptionType: TExceptionType;
	listener: string;
	match: string;
	operator: TOperators;
};

export function deepClone<R, K, V>(map: Map<K, V> | ReadonlyMap<K, V>) {
	const deepCloned = new Map();

	for (const [key, value] of map) {
		deepCloned.set(key, value instanceof Map ? deepClone(value) : value);
	}

	return (deepCloned as unknown) as R;
}

export function evaluateForm(formId: string) {
	const inputs = State.read(inputsLibraryAtom).get(formId);
	const evaluate: TEvaluator | undefined = State.read(
		formsEvaluatorsAtom
	).get(formId);

	if (inputs && !!evaluate) {
		const inputsToBeEvaluated: TRegisteredInputMap = new Map(inputs);

		return evaluate(inputsToBeEvaluated);
	}

	return null;
}

export function getFormId(domNode: HTMLElement, inputName?: string) {
	const form = domNode.tagName === 'form' ? domNode : domNode.closest('form');

	if (!form && inputName) {
		throw new Error(`No wrapper form found for input '${inputName}'`);
	}

	if (!form && inputName) {
		throw new Error('No wrapper form found');
	}

	return form?.id || 'default';
}

function getWritableFormInputsMap(
	formId: string
): [TRegisteredInputMap, TInputsLibraryMap] {
	const formsInputs: TReadonlyInputsLibraryMap = State.read(
		inputsLibraryAtom
	);

	const updatedFormsInputs = deepClone<
		TInputsLibraryMap,
		string,
		TReadOnlyRegisteredInputMap
	>(formsInputs);

	const formInputs = updatedFormsInputs.get(formId);

	const updatedFormInputs: TRegisteredInputMap = formInputs || new Map();

	updatedFormsInputs.set(formId, updatedFormInputs);

	return [updatedFormInputs, updatedFormsInputs];
}

export function registerInputToForm(
	formId: string,
	name: string,
	value: any,
	setDisabled: (disabled: boolean) => void,
	setHidden: (disabled: boolean) => void
) {
	const [updatedFormInputs, updatedFormsInputs] = getWritableFormInputsMap(
		formId
	);

	const inputDetails = {
		setDisabled,
		setHidden,
		value,
	};

	updatedFormInputs.set(name, inputDetails);

	State.write(inputsLibraryAtom, updatedFormsInputs);

	return {
		cleanup: () => {
			updatedFormInputs.delete(name);
		},
		update: (value: string) => {
			const [
				updatedFormInputs,
				updatedFormsInputs,
			] = getWritableFormInputsMap(formId);

			updatedFormInputs.set(name, {
				...inputDetails,
				value,
			});

			State.write(inputsLibraryAtom, updatedFormsInputs);

			evaluateForm(formId);
		},
	};
}

const checkValidity = (data: string, operator: TOperators, match: string) => {
	switch (operator) {
		case 'eq':
			return data === match;
		case 'contains':
			return data.includes(match);
		default:
			break;
	}

	return false;
};

const exceptionsSettersMap = {
	disabled: 'setDisabled',
	hidden: 'setHidden',
} as const;

const updateInput = (
	exceptionType: TExceptionType,
	targetInputDetails: TRegisteredInput,
	checkPassed: boolean
) => {
	const setter = targetInputDetails[exceptionsSettersMap[exceptionType]];

	setter(!checkPassed);
};

const evaluateRule = (
	rule: RuleDefinitionType,
	inputs: Map<string, TRegisteredInput>
) => {
	const listenerDetails = inputs.get(rule.listener);
	const actionTargetDetails = inputs.get(rule.actionTarget);

	if (!actionTargetDetails) {
		return false;
	}

	const result = listenerDetails
		? checkValidity(listenerDetails.value, rule.operator, rule.match)
		: false;

	return updateInput(rule.exceptionType, actionTargetDetails, result);
};

const createFormEvaluator = (rules: RuleDefinitionType[]) => (
	inputs: Map<string, TRegisteredInput>
) => {
	rules.forEach((rule) => {
		evaluateRule(rule, inputs);
	});
};

export function initializeLiferayForm(
	formId: string,
	rules: RuleDefinitionType[]
) {
	const formsEvaluators = State.read(formsEvaluatorsAtom);

	const updatedFormsEvaluators = new Map(formsEvaluators);

	updatedFormsEvaluators.set(formId, createFormEvaluator(rules));

	State.write(formsEvaluatorsAtom, updatedFormsEvaluators);

	evaluateForm(formId);
}
