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

import itemFinder from 'commerce-frontend-js/components/item_finder/entry';
import {UPDATE_DATASET_DISPLAY} from 'commerce-frontend-js/utilities/eventsDefinitions';

import AJAX from '../utils/AJAX/index';

export default function ({
	datasetId,
	inputPlaceHolderLabel,
	itemFinderRootName,
	itemSelectedPlaceHolderLabel,
	panelHeaderLabelLabel,
	rootPortletId,
	sourceId,
	sourceName,
	targetName,
	targetRESTContextPath,
	targetRESTSchema,
	titleLabelLabel,
}) {
	function selectItem(targetEntity) {
		const qualifier = {
			sourceId,
			sourceName,
			targetId: targetEntity.id,
			targetName,
		};

		return AJAX.POST(
			`/o/headless-commerce-qualifier/v1.0/qualifiers`,
			qualifier
		).then(() => {
			Liferay.fire(UPDATE_DATASET_DISPLAY, {
				id: datasetId,
			});
		});
	}

	itemFinder('itemFinder', itemFinderRootName, {
		apiUrl: targetRESTContextPath,
		getSelectedItems: () => Promise.resolve([]),
		inputPlaceholder: inputPlaceHolderLabel,
		itemCreation: false,
		itemSelectedMessage: itemSelectedPlaceHolderLabel,
		itemsKey: 'id',
		linkedDatasetsId: [datasetId],
		onItemSelected: selectItem,
		pageSize: 10,
		panelHeaderLabel: panelHeaderLabelLabel,
		portletId: rootPortletId,
		schema: JSON.parse(targetRESTSchema),
		titleLabel: titleLabelLabel,
	});
}
