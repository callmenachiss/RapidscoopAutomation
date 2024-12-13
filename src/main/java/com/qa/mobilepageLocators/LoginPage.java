package com.qa.mobilepageLocators;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(LoginPage.class);


    @iOSXCUITFindBy(accessibility = "Sign in")
    public WebElement SigninLabelText;
    @iOSXCUITFindBy(accessibility = "Continue")
    public WebElement ContinueLabelText;
    @iOSXCUITFindBy(accessibility = "Use another account")
    public WebElement UseanotheraccountLabelText;
    @iOSXCUITFindBy(accessibility = "Enter your email, phone, or Skype.")
    public WebElement EmailLabelText;
    @iOSXCUITFindBy(accessibility = "Next")
    public WebElement NextButton;
    @iOSXCUITFindBy(accessibility = "Enter the password for nachiyappan.karuppiah@streamlinedigital.com")
    public WebElement PasswordLabelText;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == \"Sign in\"`][2]")
    public WebElement SignInButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Skip\"]")
    public WebElement SkipButton;


    public void loginIntoSitemanagerApp() {
        click(SigninLabelText);
        //try{click(SigninLabelText,5);}catch(Exception e){}
        LOGGER.info("User clicked on signin button");
        driver.switchTo().alert().accept();
        //try{click(ContinueLabelText,2);}catch(Exception e){driver.switchTo().alert().accept();}
        click(UseanotheraccountLabelText);
        sendKeys(EmailLabelText,username);
        LOGGER.info("User entered the username");
        click(NextButton);
        sendKeys(PasswordLabelText,password);
        LOGGER.info("User entered the password");
        click(SignInButton);
        try{click(SkipButton,5);}catch(Exception e){System.out.println("User Already handled welcome tour message");}
        LOGGER.info("Performed Login Successfully");
    }




}
