package com.wickedhobo.config;

import java.util.Map;

public abstract class Configuration {
	
	private static String hibernateConfigFile;
	private static String JNDISourceLocation;
	
	protected boolean initialized = false;

	public Configuration() {
	}
	
	public static String getHibernateConfigFile() {
		return hibernateConfigFile;
	}

	private static void setHibernateConfigFile(String string) {
		hibernateConfigFile = string;
	}
	
	public static String getJNDISourceLocation() {
		return JNDISourceLocation;
	}

	private static void setJNDISourceLocation(String string) {
		JNDISourceLocation = string;
	}
	
	public void load(Map<String, String> properties) throws Exception {
			setHibernateConfigFile(properties.get("HibernateConfigFile"));
			setJNDISourceLocation(properties.get("JNDISourceLocation"));
	}
}
