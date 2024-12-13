package com.qa.mobilepageLocators;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class StoreHealthPage extends BasePage{
    private static final Logger LOGGER = LogManager.getLogger(StoreHealthPage.class);

    @iOSXCUITFindBy(accessibility = "Store Health")
    public WebElement StoreHealthLabelText;

    public void verifyStoreHealthPageAvailability(){
        isDisplayed(StoreHealthLabelText);
        LOGGER.info("Store Health Page is visible to the user");
        Assert.assertEquals(getText(StoreHealthLabelText),"Store Health");
    }
}
