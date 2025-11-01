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
import org.testng.Assert;

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


    @FindBy(xpath = "//span[text()='RapidScoop.co']")
    private WebElement RapiscoopTxt;
    @FindBy(name = "email")
    private WebElement emailfield;
    @FindBy(name = "password")
    private WebElement passwordfield;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement SignInButton;


    @FindBy(xpath = "//span[text()='Create']")
    private WebElement CreateButton;
    @FindBy(xpath = "//label[normalize-space(text())='Post Content']")
    private WebElement PostTxtlbl;
    @FindBy(xpath = "//textarea[@data-testid='post-content']")
    private WebElement ContentTxtbox;
    @FindBy(xpath = "//button[normalize-space(text())='X']")
    private WebElement twitterButton;
    @FindBy(xpath = "//button[normalize-space(text())='LinkedIn']")
    private WebElement linkedinButton;
    @FindBy(xpath = "//div[normalize-space(text())='Publish Now']")
    private WebElement PublishButton;
    @FindBy(xpath = "//button[normalize-space(text())='Schedule Post']")
    private WebElement ScheduleButton;
    //button[normalize-space(text())='Today']
    @FindBy(xpath = "//button[normalize-space(text())='Today']")
    private WebElement TodayButton;
    @FindBy(xpath = "//input[@type='time']")
    private WebElement Timertxtbox;
    @FindBy(xpath = "//button[normalize-space(text())='7:00 PM']")
    private WebElement ScheduleTime;
    @FindBy(xpath = "(//button[normalize-space(text())='Schedule Post'])[2]")
    private WebElement SchedulePostButton;
    @FindBy(xpath = "//span[contains(text(),'Your post has')]")
    private WebElement ToastMessage;


    public void performLogin(){
        waitforelement(RapiscoopTxt,10);
        type(emailfield, username);
        type(passwordfield, password);
        click(SignInButton);
    }

    public void createInstantPostTwitter(){
        click(CreateButton,20);
        waitforelement(PostTxtlbl,20);
        type(ContentTxtbox,"instant twitter post");
        click(twitterButton);
        waitforelement(PublishButton,10);
        click(PublishButton);
    }

    public void createInstantPostLinkedin(){
        click(CreateButton,20);
        waitforelement(PostTxtlbl,20);
        type(ContentTxtbox,"instant linkedin post");
        waitforelement(linkedinButton,10);
        click(linkedinButton);
        click(PublishButton);
    }

    public void createSchedulePostLinkedin(){
        String time=getTimerHHMM();
        click(CreateButton,20);
        waitforelement(PostTxtlbl,20);
        type(ContentTxtbox,"scheduled linkedin post");
        waitforelement(linkedinButton,10);
        click(linkedinButton);
        click(ScheduleButton);
        waitforelement(TodayButton,10);
        click(TodayButton);
        type(Timertxtbox,time);
        //click(ScheduleTime);
        waitforelement(SchedulePostButton);
        click(SchedulePostButton);
    }


    public void createSchedulePostTwitter() throws InterruptedException {
        String time=getTimerHHMM();
        click(CreateButton,20);
        waitforelement(PostTxtlbl,20);
        type(ContentTxtbox,"scheduled twitter post");
        waitforelement(twitterButton,10);
        click(twitterButton);
        click(ScheduleButton);
        waitforelement(TodayButton,10);
        click(TodayButton);
        type(Timertxtbox,time);
        //click(ScheduleTime);
        waitforelement(SchedulePostButton);
        //captureScreenShot("twitterpost");
        click(SchedulePostButton);
        waitforelement(ToastMessage,10);
        Assert.assertEquals(ToastMessage.getText(),"Your post has been scheduled successfully!");
        LOGGER.info(ToastMessage.getText());
    }

    public void createSchedulePostLinkedinandTwitter(){
        String time=getTimerHHMM();
        click(CreateButton,20);
        waitforelement(PostTxtlbl,20);
        type(ContentTxtbox,"scheduled Linkedin and twitter post");
        waitforelement(twitterButton,10);
        click(twitterButton);
        click(linkedinButton);
        click(ScheduleButton);
        waitforelement(TodayButton,10);
        click(TodayButton);
        type(Timertxtbox,time);
        //click(ScheduleTime);
        waitforelement(SchedulePostButton);
        click(SchedulePostButton);
    }

    public void createInstantPostLinkedinandTwitter(){
        click(CreateButton,20);
        waitforelement(PostTxtlbl,20);
        type(ContentTxtbox,"instant Linkedin and twitter post");
        waitforelement(twitterButton,10);
        click(twitterButton);
        click(linkedinButton);
        waitforelement(PublishButton);
        click(PublishButton);
    }









}
