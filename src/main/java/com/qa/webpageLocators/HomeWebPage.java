package com.qa.webpageLocators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomeWebPage extends BaseWebPage {

    private static final Logger LOGGER = LogManager.getLogger(HomeWebPage.class);

    public HomeWebPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }



    @FindBy(xpath = "//p[normalize-space(text())='Smart Support']")
    public WebElement SmartSupportTab;





    public void clickOnSmartSupportMenu() throws InterruptedException {
        js_click(SmartSupportTab);
        LOGGER.info("User is navigate into Smart support page");
    }


    

}
