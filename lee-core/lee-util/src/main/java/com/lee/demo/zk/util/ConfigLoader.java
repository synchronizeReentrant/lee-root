package com.lee.demo.zk.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * note:用来加载.properties的配置文件，单例模式的一个类
 */
public class ConfigLoader {

    public static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    public static final String APP_MAINCONF_FILE = "config/app_config.properties";


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
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(APP_MAINCONF_FILE);
                properties.load(is);
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
