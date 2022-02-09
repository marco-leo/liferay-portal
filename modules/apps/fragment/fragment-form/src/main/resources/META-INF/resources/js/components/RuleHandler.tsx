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

import PropTypes from 'prop-types';
import React, {useEffect} from 'react';

import {
	RuleDefinitionType,
	getFormId,
	initializeLiferayForm,
} from '../utilities/forms';

const RULES: RuleDefinitionType[] = [
	{
		actionTarget: 'second-input',
		exceptionType: 'disabled',
		listener: 'first-input',
		match: 'a',
		operator: 'contains',
	},
	{
		actionTarget: 'third-input',
		exceptionType: 'hidden',
		listener: 'second-input',
		match: 'test',
		operator: 'eq',
	},
];

type TFormProps = {
	reactDOMContainer: HTMLElement;
};

function Form({reactDOMContainer}: TFormProps) {
	useEffect(() => {
		initializeLiferayForm(getFormId(reactDOMContainer), RULES);
	}, [reactDOMContainer]);

	return <>handler</>;
}

Form.propTypes = {
	reactDOMContainer: PropTypes.instanceOf(Element).isRequired,
};

export default Form;
