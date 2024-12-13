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


    @Given("I login into iEnterprise Web Application")
    public void I_login_into_iEnterprise_Web_Application() throws InterruptedException {
        LaunchWebApp();
        loginPage = new LoginWebPage(driver);
        homePage = new HomeWebPage(driver);
        smartSupportPage = new SmartSupportPage(driver);
        loginPage.performLogin();
        homePage.clickOnSmartSupportMenu();
        smartSupportPage.verifySmartSupportMenu();
    }

    @Then("I close my web browser")
    public void i_close_my_web_browser() throws InterruptedException {
        System.out.println("Close the browser for better performance");
        teardown();
    }



}
