package com.lee.demo.zk.util;

import com.google.common.base.Strings;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigLoader {

    public static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    public static final String APP_MAINCONF_FILE = "config/start-conf.properties";

    public static final String COMMON_MAINCONF_FILE = "common_config/start-conf.properties";

    private static final String DEFAULT_CONFIG_FILE_KEY = "configFile";

    private static Properties properties = null;

    private ConfigLoader() {
    }

    public static ConfigLoader getInstance() {
        return ConfigLoader.ConfigLoaderHolder.instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    static {
        properties = new Properties();
        try {
            String confFileName = System.getProperty(DEFAULT_CONFIG_FILE_KEY);
            if (Strings.isNullOrEmpty(confFileName)) {
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(APP_MAINCONF_FILE);
                if (is == null) {
                    is = Thread.currentThread().getContextClassLoader().getResourceAsStream(COMMON_MAINCONF_FILE);
                }
                properties.load(is);
            } else {
                properties.load(new FileInputStream(new File(confFileName)));
            }
        } catch (IOException ex) {
            logger.error("load properties {} error", "configFile", ex);
            System.exit(-1);
        }
    }

    private static class ConfigLoaderHolder {
        private static final ConfigLoader instance = new ConfigLoader();
        private ConfigLoaderHolder() {
        }
    }
}
