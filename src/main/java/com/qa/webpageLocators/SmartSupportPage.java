package com.qa.webpageLocators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SmartSupportPage extends BaseWebPage {

    private static final Logger LOGGER = LogManager.getLogger(HomeWebPage.class);

    public SmartSupportPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//h1[normalize-space(text())='Smart Support']")
    public WebElement SmartSupportlabel;

    public void verifySmartSupportMenu() throws InterruptedException {
        Assert.assertEquals(getText(SmartSupportlabel), "Smart Support");
        LOGGER.info("User is able to see the Smart support page");
    }
}
