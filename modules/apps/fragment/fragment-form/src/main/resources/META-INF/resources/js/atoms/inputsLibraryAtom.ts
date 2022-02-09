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

export type TRegisteredInput = {
	setDisabled: (value: boolean) => void;
	setHidden: (value: boolean) => void;
	value: string;
};
export type TFormId = string;
export type TInputName = string;

export type TRegisteredInputMap = Map<TInputName, TRegisteredInput>;
export type TReadOnlyRegisteredInputMap =
	| Map<TInputName, TRegisteredInput>
	| ReadonlyMap<TInputName, TRegisteredInput>;

export type TInputsLibraryMap = Map<TFormId, TRegisteredInputMap>;
export type TReadonlyInputsLibraryMap = ReadonlyMap<
	TFormId,
	TReadOnlyRegisteredInputMap
>;

const defaultValue: TInputsLibraryMap = new Map<TFormId, TRegisteredInputMap>();

const inputsLibraryAtom = State.atom('formsInputsLibrary', defaultValue);

export default inputsLibraryAtom;
