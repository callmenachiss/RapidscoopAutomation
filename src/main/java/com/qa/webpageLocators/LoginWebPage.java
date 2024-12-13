package com.qa.webpageLocators;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.N;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginWebPage extends BaseWebPage{
    private static final Logger LOGGER = LogManager.getLogger(LoginWebPage.class);
    public LoginWebPage(RemoteWebDriver driver) {
       PageFactory.initElements(driver,this);
    }


    @FindBy(name = "loginfmt")
    private WebElement emailtxtfield;
    @FindBy(name = "passwd")
    private WebElement passwordtxtfield;
    @FindBy(xpath = "//button[@type='button']")
    private WebElement AzureButton;
    @FindBy(id = "idSIButton9")
    private WebElement NextButton;
    @FindBy(xpath = "//div[normalize-space(text())='Stay signed in?']")
    private WebElement signInTxt;






    public void performLogin(){
        click(AzureButton);
        type(emailtxtfield, username);
        click(NextButton);
        type(passwordtxtfield, password);
        click(NextButton);
        waitforelement(signInTxt);
        click(NextButton);
    }









}
