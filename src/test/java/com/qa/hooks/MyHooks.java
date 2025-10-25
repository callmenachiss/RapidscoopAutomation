package com.qa.hooks;

import com.qa.config.CapabilitiesManager;
import com.qa.config.DriverManager;
import com.qa.config.PropertyManager;
import com.qa.config.ServerManager;
import com.qa.retryMechanism.Retry;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.ThreadContext;
import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Properties;

import static com.qa.utils.Constants.Config.*;


public class MyHooks {

    @Before
    public void initialize() throws Exception {
        Properties props = new PropertyManager().getProps();
        DesiredCapabilities caps = new CapabilitiesManager().getCaps();
        ThreadContext.put(ROUTINGKEY, props.getProperty(IOS_PLATFORM_NAME) + "_" + props.getProperty(IOS_DEVICE_NAME));
        new ServerManager().startServer();
        DriverManager driverManager = new DriverManager();
        AppiumDriver driver = driverManager.initializeDriver(caps);
        driverManager.LaunchApp(driver, caps);
    }



    @After
    public void quit(Scenario scenario) throws EmailException {
        DriverManager driverManager = new DriverManager();
        if (scenario.isFailed()) {
            byte[] screenshot = new DriverManager().getWebDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, IMAGE_PNG, scenario.getName());
            driverManager.captureScreenShot(scenario.getName());
        }
    }
}
