package com.qa.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.qa.utils.Constants.Config.CONFIG_PROPERTIES;

public class PropertyManager {
    private static final Logger LOGGER = LogManager.getLogger(PropertyManager.class);
    private static final Properties props = new Properties();

    public  Properties getProps() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)) {
            if (props.isEmpty()) {
                LOGGER.info("Loading config properties");
                if (inputStream != null) {
                    props.load(inputStream);
                } else {
                    LOGGER.fatal("Failed to load config properties. ABORT!! The properties file '" + CONFIG_PROPERTIES + "' not found in the classpath.");
                    throw new IOException("Properties file not found");
                }
            }
        } catch (IOException e) {
            LOGGER.fatal("Failed to load config properties. ABORT!! " + e);
            throw e;
        }
        return props;
    }
}

