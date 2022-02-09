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

export declare type TRegisteredInput = {
	setDisabled: (value: boolean) => void;
	setHidden: (value: boolean) => void;
	value: string;
};
export declare type TFormId = string;
export declare type TInputName = string;
export declare type TRegisteredInputMap = Map<TInputName, TRegisteredInput>;
export declare type TReadOnlyRegisteredInputMap =
	| Map<TInputName, TRegisteredInput>
	| ReadonlyMap<TInputName, TRegisteredInput>;
export declare type TInputsLibraryMap = Map<TFormId, TRegisteredInputMap>;
export declare type TReadonlyInputsLibraryMap = ReadonlyMap<
	TFormId,
	TReadOnlyRegisteredInputMap
>;
declare const inputsLibraryAtom: {
	readonly 'default': ReadonlyMap<
		string,
		ReadonlyMap<
			string,
			{
				readonly setDisabled: (value: boolean) => void;
				readonly setHidden: (value: boolean) => void;
				readonly value: string;
			}
		>
	>;
	readonly 'key': string;
	readonly 'Liferay.State.ATOM': true;
};
export default inputsLibraryAtom;
