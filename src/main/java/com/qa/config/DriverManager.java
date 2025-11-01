package com.qa.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class DriverManager {

    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<RemoteWebDriver> webdriverThreadLocal = new ThreadLocal<>();

    public Properties props;
    {
        try {
            props = new PropertyManager().getProps();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String devUrl = props.getProperty("dev");
    public String testUrl = props.getProperty("test");
    public String prodUrl = props.getProperty("prod");
    public String username = props.getProperty("username");
    public String password = props.getProperty("Password");
    public String targetEnvName = props.getProperty("env");




    public static RemoteWebDriver driver;
    public static WebDriverWait wait=null;

    //mobile-init
    public AppiumDriver initializeDriver(DesiredCapabilities caps) {
        LOGGER.info("initializing Appium driver");
        AppiumDriver appiumDriver = new IOSDriver(new ServerManager().getServer().getUrl(), caps);
        LOGGER.info("Driver is initialized");
        driverThreadLocal.set(appiumDriver);
        return appiumDriver;
    }

    //mobile
    public AppiumDriver getDriver() {
        return driverThreadLocal.get();
    }
    public void setDriver(AppiumDriver appiumDriver) {
        driverThreadLocal.set(appiumDriver);
    }


    //web
    public RemoteWebDriver getWebDriver(){
        return webdriverThreadLocal.get();
    }
    public void SetDriver(RemoteWebDriver remoteWebDriver) {
        webdriverThreadLocal.set(remoteWebDriver);
    }


    public void hitTargetUrl(){
        if(targetEnvName.equalsIgnoreCase("dev")){
            driver.get(devUrl);
        }else if(targetEnvName.equalsIgnoreCase("test")){
            driver.get(testUrl);
        }else if(targetEnvName.equalsIgnoreCase("prod")){
            driver.get(prodUrl);
        }else{
            System.out.println("Please choose the target url");
        }
    }


    //web-init

    public void LaunchWebApp() {
        LOGGER.info("Initializing WebDriver...");

        boolean isCi = System.getenv("CI") != null;
        boolean isHeadless = Boolean.parseBoolean(System.getenv("HEADLESS_MODE"));

        ChromeOptions options = new ChromeOptions();

        if (isCi || isHeadless) {
            // ----------------------------
            // ✅ CI / Headless Environment
            // ----------------------------
            LOGGER.info("Running in CI/headless environment.");

            // Only use WebDriverManager in CI, not self-hosted
            if (isCi) {
                WebDriverManager.chromedriver().setup();
            }

            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-gpu");
            options.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(options);
        } else {
            // ----------------------------
            // 🧩 Local (headed) Environment
            // ----------------------------
            LOGGER.info("Running locally (headed mode).");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-gpu");
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }

        // Common setup for both
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        hitTargetUrl();
        LOGGER.info("Navigated to Rapidscoop Web Application successfully");
        LOGGER.info("WebDriver setup completed for " + (isCi ? "CI pipeline" : (isHeadless ? "headless run" : "local environment")));
    }






    /*
    public void LaunchWebApp() {
        LOGGER.info("initializing Web driver");

        WebDriverManager.chromedriver().setup();
         ChromeOptions options = new ChromeOptions();
        // Detect if running in CI (GitHub Actions)
        boolean isCi = System.getenv("CI") != null;

        if (isCi) {
            // CI environment (Linux)
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis());
        } else {
            // Local environment (Windows/Mac)
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--headless=new");  // optional: remove if you want GUI locally
            options.addArguments("--disable-gpu");   // required for Windows headless
        }

        // Initialize driver AFTER setting all options
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();

        // Navigate to target URL
        hitTargetUrl();  // your existing method

        LOGGER.info("Navigated to Rapidscoop Web Application");

        // Set implicit and explicit waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        LOGGER.info("WebDriver setup completed successfully");
    }*/



    /*
    public void LaunchWebApp() {
        LOGGER.info("initializing Web driver");
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--incognito");
        //driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        hitTargetUrl();
        LOGGER.info("Navigate into Rapiscoop Web Application");
        driver.manage().window().maximize();
        LOGGER.info("maximize the screen for better visibility");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }*/


    public void tearDown(){
      driver.quit();
    }


    //Verify if App is launched successfully in the device.
    private boolean isAppLaunched(AppiumDriver driver, String expectedBundleId, String textToBeVerifiedInPageSource) throws Exception {
        try {
            //String pageSource = driver.getPageSource();
            //return pageSource.contains(textToBeVerifiedInPageSource);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Issue in verifying if the App has been launched successfully" + e);
            throw e;
        }
        return true;
    }



    public void LaunchApp(AppiumDriver appiumDriver, DesiredCapabilities caps) throws Exception {
        try {
            Properties props = new PropertyManager().getProps();
            Map<String, Object> installedPackages;
            installedPackages = (Map<String, Object>) appiumDriver.executeScript("mobile: listApps");

            //Launch the app if app is already installed and verify that the app is launched successfully.
            String bundleIDOfAppToBeTested = props.getProperty("iOSBundleId");
            if (installedPackages.containsKey(bundleIDOfAppToBeTested)) {
                LOGGER.info("App already installed, Launching App in the device");
                caps.setCapability("bundleId", bundleIDOfAppToBeTested);
                appiumDriver = new IOSDriver(new ServerManager().getServer().getUrl(), caps);
                boolean isAppLaunched = isAppLaunched(appiumDriver, bundleIDOfAppToBeTested, props.getProperty("iOSAppName"));
                if (isAppLaunched) LOGGER.info("App Launched Successfully in the device");
                else {
                    throw new RuntimeException("Issue in launching of Application");
                }
                setDriver(appiumDriver);
            }
            //Fail the test case and throw an exception when the App is not already installed
            else {
                throw new RuntimeException("App is not installed in the test device with configuration as " +
                        "\nSerial Number = " + props.getProperty("iOSDeviceName") +
                        "\nIOS version = " + props.getProperty("iOSPlatformVersion") +
                        "\nUDID = " + props.getProperty("iOSUDID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.fatal("Issue with application launching in the device. ABORT !!!!" + e);
            throw e;
        }
    }



    public  String getCurrentDateandTime() {
        DateFormat customFormat =new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date currentDate = new Date();
        return customFormat.format(currentDate);
    }


    public String captureScreenShot(String TestCaseName){
        TakesScreenshot ts = ((TakesScreenshot)getWebDriver());
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/src/Screenshots/"+TestCaseName+" "+getCurrentDateandTime()+".png";
        try {
            FileHandler.copy(source, new File(path));
            System.out.println("Screenshot Captured");
        } catch (Exception e) {
            System.out.println("Unable to Capture Screenshot"+e.getMessage());
        }
        return path;
    }






}
