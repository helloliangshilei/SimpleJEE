package com.wickedhobo.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationInit extends Configuration {
	
	private static Logger log = LoggerFactory.getLogger(ConfigurationInit.class);
	private Map<String, String> properties = new HashMap<String, String>();
	
	public ConfigurationInit() {
    initialize();
  }
	
	public ConfigurationInit(Map<String, String> hm) {
		if (hm != null && !hm.isEmpty()) {
			properties = hm;
		}
		initialize();
	}

  private void initialize() {
    try {
      try {
        super.load(properties);
        log.info("Initialization of configuration variables complete!");
      }
      catch (Exception e) {
        log.error("Initialization has failed.");
      }
    }
    catch (Exception e) {
      log.error("I");
    }
  }


}
