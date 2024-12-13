package com.qa.config;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


public class ServerManager {
    private static final Logger LOGGER = LogManager.getLogger(ServerManager.class);

    private static final ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();

    public AppiumDriverLocalService getServer() {
        return server.get();
    }

    public void startServer() throws IOException {
        LOGGER.info("starting appium server");
        AppiumDriverLocalService server = MacGetAppiumService();
        server.start();
        if (!server.isRunning()) {
            LOGGER.fatal("Appium server not started. ABORT!!!");
            throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started. ABORT!!!");
        }
        server.clearOutPutStreams(); // -> Comment this if you want to see server logs in the console
        ServerManager.server.set(server);
        LOGGER.info("Appium server started");
    }

    public AppiumDriverLocalService MacGetAppiumService() throws IOException {
        Properties props = new PropertyManager().getProps();
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withLogFile(new File(props.getProperty("iOSPlatformName") + "_" + props.getProperty("iOSDeviceName") + File.separator + "Server.log")));
    }
}
