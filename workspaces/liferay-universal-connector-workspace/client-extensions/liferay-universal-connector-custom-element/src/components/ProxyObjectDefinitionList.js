/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import {Body, Cell, Head, Row, Table} from '@clayui/core';
import ClayEmptyState from '@clayui/empty-state';
import ClayIcon from '@clayui/icon';
import ClayModal, {useModal} from '@clayui/modal';
import {ClayPaginationBarWithBasicItems} from '@clayui/pagination-bar';
import ClayToolbar from '@clayui/toolbar';
import moment from 'moment';
import {useEffect, useRef, useState} from 'react';

import {
	deleteProxyObjectDefinition,
	getProxyObjectDefinitionsPage,
} from '../services/ProxyObjectService';
import DiscoverAPIWizard from './DiscoverAPIWizard';

const DELTAS = [{label: 5}, {label: 10}, {label: 20}, {label: 40}];

const HEADERS = [
	{
		key: 'id',
		label: 'ID',
	},
	{
		expanded: false,
		key: 'name',
		label: 'Name',
	},
	{
		expanded: true,
		key: 'proxyObjectName',
		label: 'Proxy Object Name',
	},
	{
		key: 'dateCreated',
		label: 'Created Date',
	},
	{
		key: 'actions',
		label: 'Actions',
	},
];

const ProxyObjectDefinitionList = () => {
	const [active, setActive] = useState(0);
	const [data, setData] = useState(null);
	const [delta, setDelta] = useState(5);
	const [isDeleting, setIsDeleting] = useState(false);
	const [isLoading, setIsLoading] = useState(false);
	const [pageIndex, setPageIndex] = useState(1);
	const [totalItems, setTotalItems] = useState(0);

	const {observer, onOpenChange, open} = useModal();

	const discoverAPIWizardComponentRef = useRef(null);

	const confirmDeleteItemModal = (proxyObjectDefinitionId) => {
		const deleteProxyObject = async () => {
			setIsDeleting(true);

			await deleteProxyObjectDefinition(proxyObjectDefinitionId);

			setIsDeleting(false);

			reload();
		};

		Liferay.Util.openConfirmModal({
			message:
				'Deleting a proxy object definition. This action is permanent and cannot be undone.',
			onConfirm: (isConfirmed) => {
				if (isConfirmed) {
					deleteProxyObject();
				}
			},
		});
	};

	const handleBackPage = () => {
		discoverAPIWizardComponentRef.current.handleBackPage();
	};

	const handleNextPage = () => {
		discoverAPIWizardComponentRef.current.handleNextPage();
	};

	const loadPage = async () => {
		setIsLoading(true);

		const results = await getProxyObjectDefinitionsPage(pageIndex, delta);

		setData(results.items);

		setTotalItems(results.totalCount);

		setIsLoading(false);
	};

	const openNewItemModal = () => {
		onOpenChange(true);
	};

	const reload = () => {
		setTotalItems(0);

		if (pageIndex === 1) {
			loadPage();
		}
		else {
			setPageIndex(1);
		}
	};

	useEffect(() => {
		const fetchData = async () => {
			const results = await getProxyObjectDefinitionsPage(
				pageIndex,
				delta
			);

			setData(results.items);

			setTotalItems(results.totalCount);
		};

		fetchData();
	}, [delta, pageIndex]);

	return (
		<>
			<ClayToolbar className="mb-3">
				<ClayToolbar.Nav>
					<ClayToolbar.Item className="text-left" expand>
						<ClayToolbar.Section>
							<label className="component-title text-6">
								Liferay Universal Connector
							</label>
						</ClayToolbar.Section>
					</ClayToolbar.Item>

					<ClayToolbar.Item></ClayToolbar.Item>

					<ClayToolbar.Item>
						<ClayToolbar.Section>
							{Liferay.ThemeDisplay.isSignedIn() && (
								<ClayButton
									aria-label="Create New Connection"
									className="lfr-portal-tooltip"
									disabled={isDeleting || isLoading}
									displayType="primary"
									onClick={openNewItemModal}
									size="sm"
									title="Create New Connection"
								>
									<span className="inline-item inline-item-before my-auto">
										<ClayIcon symbol="plus" />
									</span>

									<span>New</span>
								</ClayButton>
							)}
						</ClayToolbar.Section>
					</ClayToolbar.Item>
				</ClayToolbar.Nav>
			</ClayToolbar>

			{totalItems > 0 && (
				<>
					<Table columnsVisibility={false}>
						<Head items={HEADERS}>
							{(column) => (
								<Cell
									expanded={column.expanded}
									key={column.key}
									wrap={false}
								>
									{column.label}
								</Cell>
							)}
						</Head>

						<Body>
							{data &&
								data.map((row) => (
									<Row key={row['id']}>
										<Cell wrap={false}>{row['id']}</Cell>

										<Cell expanded={false} wrap={false}>
											{row['name']}
										</Cell>

										<Cell expanded={true} wrap={true}>
											{row['proxyObjectName']}
										</Cell>

										<Cell wrap={false}>
											{moment(row['dateCreated']).format(
												'MMMM D, YYYY'
											)}
										</Cell>

										<Cell textAlign="end" wrap={false}>
											<ClayButton
												aria-label="Delete Proxy Object Definition"
												className="lfr-portal-tooltip"
												displayType="danger"
												onClick={() =>
													confirmDeleteItemModal(
														row['id']
													)
												}
												size="sm"
												title="Delete Proxy Object Definition"
											>
												Delete
											</ClayButton>
										</Cell>
									</Row>
								))}
						</Body>
					</Table>

					<ClayPaginationBarWithBasicItems
						activeDelta={delta}
						defaultActive={1}
						deltas={DELTAS}
						ellipsisBuffer={3}
						onActiveChange={(page) => {
							setPageIndex(page);
						}}
						onDeltaChange={(delta) => {
							setDelta(delta);
						}}
						totalItems={totalItems}
					/>
				</>
			)}

			{totalItems <= 0 && !isLoading && (
				<ClayEmptyState
					description={null}
					imgProps={{alt: 'Alternative Text', title: 'Hello World!'}}
					imgSrc={`${Liferay.ThemeDisplay.getPathThemeImages()}/states/search_state.gif`}
					imgSrcReducedMotion={`${Liferay.ThemeDisplay.getPathThemeImages()}/states/search_state_reduced_motion.gif`}
					title="No Object Definition Found"
				>
					{Liferay.ThemeDisplay.isSignedIn() && (
						<ClayButton
							aria-label="Create New Connection"
							className="lfr-portal-tooltip"
							disabled={isDeleting || isLoading}
							displayType="primary"
							onClick={openNewItemModal}
							size="sm"
							title="Create New Connection"
						>
							<span className="inline-item inline-item-before my-auto">
								<ClayIcon symbol="plus" />
							</span>

							<span>Create New Connection</span>
						</ClayButton>
					)}
				</ClayEmptyState>
			)}

			{open && (
				<ClayModal observer={observer} size="lg">
					<ClayModal.Header>API Discover Wizard</ClayModal.Header>

					<ClayModal.Body>
						<DiscoverAPIWizard
							onActiveChange={setActive}
							onClose={onOpenChange}
							onSuccess={reload}
							ref={discoverAPIWizardComponentRef}
						/>
					</ClayModal.Body>

					<ClayModal.Footer
						last={
							<ClayButton.Group spaced>
								<ClayButton
									disabled={active === 0}
									displayType="secondary"
									onClick={handleBackPage}
								>
									Back
								</ClayButton>
								<ClayButton onClick={handleNextPage}>
									{active === 4 ? 'Submit' : 'Next'}
								</ClayButton>
							</ClayButton.Group>
						}
					/>
				</ClayModal>
			)}
		</>
	);
};

export default ProxyObjectDefinitionList;
