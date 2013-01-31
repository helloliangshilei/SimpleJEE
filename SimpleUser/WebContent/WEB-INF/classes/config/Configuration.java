package config;

import java.util.Map;

public abstract class Configuration {
	
	private static String persistenceType;
	private static String JNDISourceLocation;
	
	protected boolean initialized = false;

	public Configuration() {
	}
	
	public static String getPersistenceType() {
		return persistenceType;
	}

	private static void setPersistenceType(String string) {
		persistenceType = string;
	}
	
	public static String getJNDISourceLocation() {
		return JNDISourceLocation;
	}

	private static void setJNDISourceLocation(String string) {
		JNDISourceLocation = string;
	}
	
	public void load(Map<String, String> properties) throws Exception {
		setPersistenceType(properties.get("persistenceType"));
		setJNDISourceLocation(properties.get("JNDISourceLocation"));
	}
}
