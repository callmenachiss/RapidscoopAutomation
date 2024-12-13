package com.qa.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.Properties;

public class CapabilitiesManager {
    private static final Logger LOGGER = LogManager.getLogger(CapabilitiesManager.class);

    public DesiredCapabilities getCaps() throws IOException {
        Properties props = new PropertyManager().getProps();
        DesiredCapabilities caps = new DesiredCapabilities();
        try {
            LOGGER.info("getting capabilities");
            caps.setCapability("platformName", props.getProperty("iOSPlatformName"));
            caps.setCapability("deviceName", props.getProperty("iOSDeviceName"));
            caps.setCapability("platformVersion", props.getProperty("iOSPlatformVersion"));
            caps.setCapability("udid", props.getProperty("iOSUDID"));
            caps.setCapability("automationName", props.getProperty("iOSAutomationName"));
            caps.setCapability("xcodeSigningId", props.getProperty("iOSXcodeSigningId"));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.fatal("Failed to load capabilities. ABORT!!" + e);
        }
        return caps;
    }
}
