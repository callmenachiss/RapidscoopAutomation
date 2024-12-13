package com.qa.mobilepageLocators;

import com.qa.config.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static com.qa.utils.Constants.WaitTime.TWENTY_FIVE;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

public class BasePage extends DriverManager{
    private static final Logger LOGGER = LogManager.getLogger(BasePage.class);
    public final AppiumDriver driver;

    public BasePage(){
        driver = new DriverManager().getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void waitForVisibility(WebElement e) {
        waitForVisibility(e, TWENTY_FIVE);
    }

    public void waitForVisibility(WebElement e, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void clear(WebElement e) {
        clear(e, TWENTY_FIVE);
    }

    public void clear(WebElement e, long waitTime) {
        waitForVisibility(e, waitTime);
        e.clear();
    }

    public void click(WebElement e) {
        click(e, TWENTY_FIVE);
    }

    public void click(WebElement e, long waitTime) {
        waitForVisibility(e, waitTime);
        e.click();
    }

    public void type(WebElement e, String value){
        waitForVisibility(e, TWENTY_FIVE);
        e.sendKeys(value);
    }



    public void sendKeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.click();
        e.sendKeys(txt);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getText(WebElement e) {
        return getText(e, TWENTY_FIVE);
    }

    public String getText(WebElement e, long waitTime) {
        waitForVisibility(e, waitTime);
        return getAttribute(e, "label");
    }

    public Boolean elementIsDisplayed(WebElement e) {
        waitForVisibility(e, TWENTY_FIVE);
        return e.isDisplayed();
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

    public void findElementByAccessibilityIdAndClick(String accessibilityId) {
        int count = 0;
        boolean keepFinding = true;
        while (keepFinding && count < 10) {
            try {
                driver.findElement(AppiumBy.accessibilityId(accessibilityId)).click();
                keepFinding = false;
            } catch (Exception e) {
                count++;
                iOSSwipeToElement(accessibilityId);
            }
        }
    }

    public void clickusingId(String accessibilityId){
        driver.findElement(AppiumBy.accessibilityId(accessibilityId)).click();
    }

    public void findElementByAccessibilityId(String accessibilityId) {
        int count = 0;
        boolean keepFinding = true;
        while (keepFinding && count < 10) {
            try {
                driver.findElement(AppiumBy.accessibilityId(accessibilityId));
                keepFinding = false;
            } catch (Exception e) {
                count++;
                iOSSwipeToElement(accessibilityId);
            }
        }
    }

    public void findElementWithPredicateAndClick(String predicate) {
        int count = 0;
        boolean keepFinding = true;
        while (keepFinding && count < 10) {
            try {
                driver.findElement(AppiumBy.iOSNsPredicateString(predicate)).click();
                keepFinding = false;
            } catch (Exception e) {
                count++;
                iOSSwipeToElement(predicate);
            }
        }
    }

    public void findElementWithXpathAndClick(String xpath) {
        int count = 0;
        boolean keepFinding = true;
        while (keepFinding && count < 10) {
            try {
                driver.findElement(AppiumBy.xpath(xpath)).click();
                keepFinding = false;
            } catch (Exception e) {
                count++;
                iOSSwipeToElement(xpath);
            }
        }
    }


    public WebElement findElementwithXpath(String xpath){
        return driver.findElement(AppiumBy.xpath(xpath));
    }

    public WebElement findElementWithPredicate(String predicate) {
        return driver.findElement(AppiumBy.iOSNsPredicateString(predicate));
    }

    public void findElementByClassChainAndClick(String classChain) {
        int count = 0;
        boolean keepFinding = true;
        while (keepFinding && count < 10) {
            try {
                driver.findElement(AppiumBy.iOSClassChain(classChain)).click();
                keepFinding = false;
            } catch (Exception e) {
                count++;
                iOSSwipeToElement(classChain);
            }
        }
    }

    public void useKeyboard(String data){
        Actions act = new Actions(driver);
        act.sendKeys(data).build().perform();
    }

    public static int genarateFiveNumbers()
    {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }


    public  String getCurrentTime() {
        DateFormat customFormat =new SimpleDateFormat("ddMMyyyyHHmmss");
        Date currentDate = new Date();
        return customFormat.format(currentDate);
    }


    /*
    durationTime - how many seconds to press on an element
     */
    public void longPress(WebElement e, Duration durationTime) {
        Map<String, Object> params = new HashMap<>();
        params.put("element", ((RemoteWebElement) e).getId());
        params.put("duration", durationTime);
        driver.executeScript("mobile:touchAndHold", params);
    }

    public void iOSSwipeToElement(String elementLabel) {
        JavascriptExecutor js = driver;
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", "up");
        scrollObject.put("predicateString", "label == '" + elementLabel + "'");
        js.executeScript("mobile: swipe", scrollObject);
    }

    public void iOSDownSwipeToElement(String elementLabel) {
        JavascriptExecutor js = driver;
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", "down");
        scrollObject.put("predicateString", "label == '" + elementLabel + "'");
        js.executeScript("mobile: swipe", scrollObject);
    }




    public long generateRandomNumber() {
        Random random = new Random();
        long rand = random.nextInt(10000000);
        return rand;
    }

    private int getYear(WebElement element) {
        String yearString = element.getAttribute("label");
        if (isNotEmpty(yearString)) {
            return Integer.parseInt(yearString);
        }
        return 0;
    }

    public void datePicker(int date, String month, int year) {
        List<WebElement> datePickerValues = driver.findElements(AppiumBy.xpath("//XCUIElementTypePickerWheel"));
     /*   for(int i=0;i< datePickerValues.size();i++){
            LOGGER.info("Current Date in the page is "+ datePickerValues.get(i).getText());
        }*/
        datePickerValues.get(0).sendKeys(String.valueOf(date));
        datePickerValues.get(0).sendKeys(Keys.TAB);

        datePickerValues.get(1).sendKeys(month);
        datePickerValues.get(1).sendKeys(Keys.TAB);

        datePickerValues.get(2).sendKeys(String.valueOf(year));
    }

    private WebElement findElementByClassChain(String classChain) {
        return driver.findElement(AppiumBy.iOSClassChain(classChain));
    }

    public void tapOnElementUsingCoOrdinates(int x, int y) {
        JavascriptExecutor js = driver;
        Map args = new HashMap<>();
        args.put("x", x);
        args.put("y", y);
        js.executeScript("mobile: tap", args);
    }

    public void doubleTapOnElementUsingCoOrdinates(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map args = new HashMap<>();
        args.put("x", x);
        args.put("y", y);
        js.executeScript("mobile: doubleTap", args);
    }

    public void sendKeysUsingJS(WebElement element, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + value + "'", element);
        // js.executeScript("$('#" + "idValue" + "').val('" + "text" + "');");
    }

}
