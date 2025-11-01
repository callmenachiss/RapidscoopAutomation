package com.qa.stepdef;

import com.qa.webpageLocators.BaseWebPage;
import com.qa.webpageLocators.HomeWebPage;
import com.qa.webpageLocators.LoginWebPage;
import com.qa.webpageLocators.SmartSupportPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class LoginpageStepDef extends BaseWebPage {
    private LoginWebPage loginPage;
    private HomeWebPage homePage;
    private SmartSupportPage smartSupportPage;


    @Given("User login into Rapidscoop Web Application")
    public void User_login_into_Rapidscoop_Web_Application() throws InterruptedException {
        LaunchWebApp();
        loginPage = new LoginWebPage(driver);
        //homePage = new HomeWebPage(driver);
        //smartSupportPage = new SmartSupportPage(driver);
        loginPage.performLogin();
        //homePage.clickOnSmartSupportMenu();
        //smartSupportPage.verifySmartSupportMenu();
    }


    @Given("Create a instant post for twitter")
    public void Create_a_instant_post_for_twitter() throws InterruptedException {
        loginPage = new LoginWebPage(driver);
        minSleep();
        loginPage.createInstantPostTwitter();
        forcedSleep();
    }

    @Given("Create a instant post for linkedin")
    public void Create_a_instant_post_for_linkedin() throws InterruptedException {
        loginPage = new LoginWebPage(driver);
        minSleep();
        loginPage.createInstantPostLinkedin();
        forcedSleep();
    }

    @Given("Create a schedule post for twitter")
    public void Create_a_schedule_post_for_twitter() throws InterruptedException {
        loginPage = new LoginWebPage(driver);
        minSleep();
        loginPage.createSchedulePostTwitter();
        forcedSleep();
    }

    @Given("Create a schedule post for linkedin")
    public void Create_a_schedule_post_for_linkedin() throws InterruptedException {
        loginPage = new LoginWebPage(driver);
        minSleep();
        loginPage.createSchedulePostLinkedin();
        forcedSleep();
    }

    @Given("Create instant post in twitter and linkedin")
    public void Create_instant_post_in_twitter_and_linkedin() throws InterruptedException {
        loginPage = new LoginWebPage(driver);
        minSleep();
        loginPage.createInstantPostLinkedinandTwitter();
        forcedSleep();
    }

    @Given("Create schedule post in twitter and linkedin")
    public void Create_schedule_post_in_twitter_and_linkedin() throws InterruptedException {
        loginPage = new LoginWebPage(driver);
        minSleep();
        loginPage.createSchedulePostLinkedinandTwitter();
        forcedSleep();
    }

    @Then("Create Magic post")
    public void Create_Magic_post() throws InterruptedException {
        loginPage = new LoginWebPage(driver);
        minSleep();
        loginPage.createMagicPost();
        forcedSleep();
    }

    @Then("I close my web browser")
    public void i_close_my_web_browser() throws InterruptedException {
        System.out.println("Close the browser for better performance");
        teardown();
    }



}
