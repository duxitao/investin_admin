package cn.investin.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AppConfig {
	private Logger logger = Logger.getLogger(this.getClass());
	Properties properties = new Properties();

	private static AppConfig instance;

	public AppConfig(String fileName) {
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
			properties.load(in);
		} catch (Exception e) {
			logger.error("load properties error,fileName=" + fileName);
		}
		instance = this;
		if (instance == null) {
			instance = this;
		}
	}

	public Properties getProperties() {
		return properties;
	}

	public static AppConfig getInstance() {
		return instance;
	}

	public static void setInstance(AppConfig instance) {
		AppConfig.instance = instance;
	}

}
