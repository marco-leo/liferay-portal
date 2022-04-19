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

package com.liferay.commerce.qualifier.service.http;

import com.liferay.commerce.qualifier.service.CommerceQualifierEntryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CommerceQualifierEntryServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Riccardo Alberti
 * @generated
 */
public class CommerceQualifierEntryServiceHttp {

	public static com.liferay.commerce.qualifier.model.CommerceQualifierEntry
			addCommerceQualifierEntry(
				HttpPrincipal httpPrincipal, String sourceClassName,
				long sourceClassPK, String targetClassName, long targetClassPK,
				boolean targetDefault)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceQualifierEntryServiceUtil.class,
				"addCommerceQualifierEntry",
				_addCommerceQualifierEntryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, sourceClassName, sourceClassPK, targetClassName,
				targetClassPK, targetDefault);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.commerce.qualifier.model.CommerceQualifierEntry)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deleteCommerceQualifierEntriesBySource(
			HttpPrincipal httpPrincipal, String sourceClassName,
			long sourceClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceQualifierEntryServiceUtil.class,
				"deleteCommerceQualifierEntriesBySource",
				_deleteCommerceQualifierEntriesBySourceParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, sourceClassName, sourceClassPK);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deleteCommerceQualifierEntriesBySource(
			HttpPrincipal httpPrincipal, String sourceClassName,
			long sourceClassPK, String targetClassName)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceQualifierEntryServiceUtil.class,
				"deleteCommerceQualifierEntriesBySource",
				_deleteCommerceQualifierEntriesBySourceParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, sourceClassName, sourceClassPK, targetClassName);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deleteCommerceQualifierEntriesByTarget(
			HttpPrincipal httpPrincipal, String targetClassName,
			long targetClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceQualifierEntryServiceUtil.class,
				"deleteCommerceQualifierEntriesByTarget",
				_deleteCommerceQualifierEntriesByTargetParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, targetClassName, targetClassPK);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deleteCommerceQualifierEntriesByTarget(
			HttpPrincipal httpPrincipal, String sourceClassName,
			String targetClassName, long targetClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceQualifierEntryServiceUtil.class,
				"deleteCommerceQualifierEntriesByTarget",
				_deleteCommerceQualifierEntriesByTargetParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, sourceClassName, targetClassName, targetClassPK);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.commerce.qualifier.model.CommerceQualifierEntry
			deleteCommerceQualifierEntry(
				HttpPrincipal httpPrincipal, long commerceQualifierEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceQualifierEntryServiceUtil.class,
				"deleteCommerceQualifierEntry",
				_deleteCommerceQualifierEntryParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceQualifierEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.commerce.qualifier.model.CommerceQualifierEntry)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.commerce.qualifier.model.CommerceQualifierEntry
			fetchCommerceQualifierEntry(
				HttpPrincipal httpPrincipal, String sourceClassName,
				long sourceClassPK, String targetClassName, long targetClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceQualifierEntryServiceUtil.class,
				"fetchCommerceQualifierEntry",
				_fetchCommerceQualifierEntryParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, sourceClassName, sourceClassPK, targetClassName,
				targetClassPK);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.commerce.qualifier.model.CommerceQualifierEntry)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.commerce.qualifier.model.CommerceQualifierEntry>
				getCommerceQualifierEntriesBySource(
					HttpPrincipal httpPrincipal, long companyId,
					String sourceClassName, long sourceClassPK,
					String targetClassName, String keywords, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceQualifierEntryServiceUtil.class,
				"getCommerceQualifierEntriesBySource",
				_getCommerceQualifierEntriesBySourceParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, sourceClassName, sourceClassPK,
				targetClassName, keywords, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.commerce.qualifier.model.CommerceQualifierEntry>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getCommerceQualifierEntriesBySourceCount(
			HttpPrincipal httpPrincipal, long companyId, String sourceClassName,
			long sourceClassPK, String targetClassName, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceQualifierEntryServiceUtil.class,
				"getCommerceQualifierEntriesBySourceCount",
				_getCommerceQualifierEntriesBySourceCountParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, sourceClassName, sourceClassPK,
				targetClassName, keywords);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.commerce.qualifier.model.CommerceQualifierEntry>
				getCommerceQualifierEntriesByTarget(
					HttpPrincipal httpPrincipal, long companyId,
					String sourceClassName, String targetClassName,
					long targetClassPK, String keywords, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceQualifierEntryServiceUtil.class,
				"getCommerceQualifierEntriesByTarget",
				_getCommerceQualifierEntriesByTargetParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, sourceClassName, targetClassName,
				targetClassPK, keywords, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.commerce.qualifier.model.CommerceQualifierEntry>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getCommerceQualifierEntriesByTargetCount(
			HttpPrincipal httpPrincipal, long companyId, String sourceClassName,
			String targetClassName, long targetClassPK, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceQualifierEntryServiceUtil.class,
				"getCommerceQualifierEntriesByTargetCount",
				_getCommerceQualifierEntriesByTargetCountParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, sourceClassName, targetClassName,
				targetClassPK, keywords);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.commerce.qualifier.model.CommerceQualifierEntry
			getCommerceQualifierEntry(
				HttpPrincipal httpPrincipal, long commerceQualifierEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceQualifierEntryServiceUtil.class,
				"getCommerceQualifierEntry",
				_getCommerceQualifierEntryParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceQualifierEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.commerce.qualifier.model.CommerceQualifierEntry)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.commerce.qualifier.model.CommerceQualifierEntry
			updateCommerceQualifierEntry(
				HttpPrincipal httpPrincipal, long commerceQualifierEntryId,
				boolean targetDefault)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceQualifierEntryServiceUtil.class,
				"updateCommerceQualifierEntry",
				_updateCommerceQualifierEntryParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceQualifierEntryId, targetDefault);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.commerce.qualifier.model.CommerceQualifierEntry)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceQualifierEntryServiceHttp.class);

	private static final Class<?>[] _addCommerceQualifierEntryParameterTypes0 =
		new Class[] {
			String.class, long.class, String.class, long.class, boolean.class
		};
	private static final Class<?>[]
		_deleteCommerceQualifierEntriesBySourceParameterTypes1 = new Class[] {
			String.class, long.class
		};
	private static final Class<?>[]
		_deleteCommerceQualifierEntriesBySourceParameterTypes2 = new Class[] {
			String.class, long.class, String.class
		};
	private static final Class<?>[]
		_deleteCommerceQualifierEntriesByTargetParameterTypes3 = new Class[] {
			String.class, long.class
		};
	private static final Class<?>[]
		_deleteCommerceQualifierEntriesByTargetParameterTypes4 = new Class[] {
			String.class, String.class, long.class
		};
	private static final Class<?>[]
		_deleteCommerceQualifierEntryParameterTypes5 = new Class[] {long.class};
	private static final Class<?>[]
		_fetchCommerceQualifierEntryParameterTypes6 = new Class[] {
			String.class, long.class, String.class, long.class
		};
	private static final Class<?>[]
		_getCommerceQualifierEntriesBySourceParameterTypes7 = new Class[] {
			long.class, String.class, long.class, String.class, String.class,
			int.class, int.class
		};
	private static final Class<?>[]
		_getCommerceQualifierEntriesBySourceCountParameterTypes8 = new Class[] {
			long.class, String.class, long.class, String.class, String.class
		};
	private static final Class<?>[]
		_getCommerceQualifierEntriesByTargetParameterTypes9 = new Class[] {
			long.class, String.class, String.class, long.class, String.class,
			int.class, int.class
		};
	private static final Class<?>[]
		_getCommerceQualifierEntriesByTargetCountParameterTypes10 =
			new Class[] {
				long.class, String.class, String.class, long.class, String.class
			};
	private static final Class<?>[] _getCommerceQualifierEntryParameterTypes11 =
		new Class[] {long.class};
	private static final Class<?>[]
		_updateCommerceQualifierEntryParameterTypes12 = new Class[] {
			long.class, boolean.class
		};

}