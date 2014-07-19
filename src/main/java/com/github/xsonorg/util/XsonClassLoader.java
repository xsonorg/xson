package com.github.xsonorg.util;

import java.security.PrivilegedAction;

import com.github.xsonorg.XsonException;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class XsonClassLoader extends ClassLoader {

	private static java.security.ProtectionDomain DOMAIN;

	static {
		DOMAIN = (java.security.ProtectionDomain) java.security.AccessController
				.doPrivileged(new PrivilegedAction<Object>() {
					public Object run() {
						return XsonClassLoader.class.getProtectionDomain();
					}
				});
	}

	public XsonClassLoader() {
		super(Thread.currentThread().getContextClassLoader());
	}

	public Class<?> defineClassPublic(String name, byte[] b, int off, int len)
			throws ClassFormatError {
		Class<?> clazz = defineClass(name, b, off, len, DOMAIN);

		return clazz;
	}

	public static Class<?> forName(String className) {
		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			return classLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new XsonException("class nout found : " + className);
		}
	}

	public static boolean isExternalClass(Class<?> clazz) {
		ClassLoader classLoader = clazz.getClassLoader();

		if (classLoader == null) {
			return false;
		}

		ClassLoader current = Thread.currentThread().getContextClassLoader();
		while (current != null) {
			if (current == classLoader) {
				return false;
			}

			current = current.getParent();
		}

		return true;
	}
}
