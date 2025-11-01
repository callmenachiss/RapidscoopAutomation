package com.qa.webpageLocators;


import com.qa.config.DriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class BaseWebPage extends  DriverManager{

    private static final Logger LOGGER = LogManager.getLogger(BaseWebPage.class);

    public  void teardown() {
            if (driver != null) {
                try {
                    driver.quit();
                    LOGGER.info("Browser closed successfully.");
                } catch (Exception e) {
                    LOGGER.warn("Ignoring WebDriver shutdown exception: " + e.getMessage());
                }
            }
    }

    public String getUrlofCurrentPage(){
        String url=driver.getCurrentUrl();
        return url;
    }

    public void handleWindow(int position){
        Set<String> allwindows = driver.getWindowHandles();
        List<String> targetWindow = new ArrayList<String>(allwindows);
        driver.switchTo().window(targetWindow.get(position));
    }

    public void Close(){
        driver.close();
    }

    public void pageRefresh(){
        driver.navigate().refresh();
    }

        public WebElement waitforelement(WebElement ele) {
            WebElement element=wait.until(ExpectedConditions.elementToBeClickable(ele));
            return element;
        }

    public WebElement waitforelement(WebElement ele,long time) {
        WebElement element=wait.until(ExpectedConditions.elementToBeClickable(ele));
        return element;
    }

        public void click(WebElement ele){
            WebElement element = waitforelement(ele);
            element.click();
        }

    public void click(WebElement ele,long time){
        WebElement element = waitforelement(ele,time);
        element.click();
    }

        public void type(WebElement ele,String data) {
            WebElement element = waitforelement(ele);
            element.sendKeys(data);
        }


    public void js_click(WebElement ele){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", ele);
    }


    public void type(WebElement ele,String data,long time) {
        WebElement element = waitforelement(ele,time);
        element.sendKeys(data);
    }

    public void switchFrame(WebElement element) {
        waitforelement(element);
        driver.switchTo().frame(element);
    }

    public void switchParentFrame() {
        driver.switchTo().defaultContent();
    }


       public String getText(WebElement ele) {
        WebElement element = waitforelement(ele);
        String text=element.getText();
        //System.out.println(text);
        return text;
       }

        public void selectValue(WebElement ele, String value) {
            WebElement element = waitforelement(ele);
            new Select(element).selectByValue(value);
        }

        public void selectText(WebElement ele, String text) {
            WebElement element = waitforelement(ele);
            new Select(element).selectByVisibleText(text);
        }

        public void selectIndex(WebElement ele, int position) {
            WebElement element = waitforelement(ele);
            new Select(element).selectByIndex(position);
        }


        public void pressEnter() throws InterruptedException {
         Thread.sleep(2000);
         Actions act = new Actions(driver);
         act.sendKeys(Keys.ENTER).build().perform();
        }

        public void forcedSleep() throws InterruptedException {
        Thread.sleep(6000);
        }

        public void minSleep() throws InterruptedException {
        Thread.sleep(2000);
        }

        public void pressTab() throws InterruptedException {
        Thread.sleep(2000);
        Actions act = new Actions(driver);
        act.sendKeys(Keys.TAB).build().perform();
        }

        public int generateRandomNumber(){
            Random random = new Random();
            int x = random.nextInt(9);
            return x;
        }

        public String generateRandomString(){
          String random = RandomStringUtils.randomAlphanumeric(5);
          System.out.println(random);
          return random;
        }

        /*
        public String getTimerHHMM(){
            // Get current time in IST
            ZonedDateTime istNow = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
            ZonedDateTime futureTime = istNow.plusMinutes(10);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = futureTime.format(formatter);
            System.out.println("Current IST Time + 10 mins: " + formattedTime);
            return formattedTime;
        }*/


    public String getTimerHHMM() {
        LocalDate today = LocalDate.now();
        System.out.println("Today's Date: " + today);
        LOGGER.info("Today's Date: " + today);
        // Detect if running in CI (commonly set in pipelines like GitHub Actions, Jenkins, etc.)
        boolean isCi = System.getenv("CI") != null || System.getenv("GITHUB_ACTIONS") != null;

        // Choose timezone based on environment
        //ZoneId zone = isCi ? ZoneId.of("UTC") : ZoneId.systemDefault();
        ZoneId zone = ZoneId.systemDefault();

        // Get current time in appropriate zone
        ZonedDateTime now = ZonedDateTime.now(zone);
        ZonedDateTime futureTime = now.plusMinutes(10);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = futureTime.format(formatter);

        System.out.println("Environment: " + (isCi ? "CI (UTC)" : "Local (" + zone + ")"));
        System.out.println("Current Time + 10 mins: " + formattedTime);

        LOGGER.info("Current Time + 10 mins: " + formattedTime);

        return formattedTime;
    }


    public boolean isDisplayed(WebElement element) {
        try {
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            if (e instanceof StaleElementReferenceException) {
                throw e;
            }
            LOGGER.error("WebElement is not displayed. " + e.getLocalizedMessage());
            return false;
        }
    }

        public  String getCurrentTime() {
        DateFormat customFormat =new SimpleDateFormat("ddMMyyyyHHmmss");
        Date currentDate = new Date();
        return customFormat.format(currentDate);
        }

        public static int genarateFiveNumbers()
        {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        }


    }
