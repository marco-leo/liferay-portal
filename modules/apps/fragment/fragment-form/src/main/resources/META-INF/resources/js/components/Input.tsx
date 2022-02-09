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

import React from 'react';

import {getFormId} from '../utilities/forms';
import useForm from '../utilities/useForm';

type TInputProps = {
	name: string;
	reactDOMContainer: HTMLElement;
	value: string;
};

function Input({name, reactDOMContainer, value: initialValue}: TInputProps) {
	const {disabled, hidden, setValue, value} = useForm(
		getFormId(reactDOMContainer, name),
		name,
		initialValue
	);

	return (
		!hidden && (
			<div>
				{name}

				<input
					autoComplete="off"
					disabled={disabled}
					name={name}
					onChange={(event) => setValue(event.target.value)}
					type="text"
					value={value}
				/>
			</div>
		)
	);
}

Input.defaultProps = {
	value: '',
};

export default Input;
