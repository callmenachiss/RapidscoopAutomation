package com.qa.mobileStepdef;

import com.qa.mobilepageLocators.BasePage;
import com.qa.mobilepageLocators.LoginPage;
import com.qa.mobilepageLocators.StoreHealthPage;
import io.cucumber.java.en.Given;

public class LoginPageStepDef extends BasePage {

    private final LoginPage loginPage = new LoginPage();
    private final StoreHealthPage storeHealthPage = new StoreHealthPage();

    @Given("I login into Sitemanager App")
    public void I_login_into_Sitemanager_App() {
        loginPage.loginIntoSitemanagerApp();
        storeHealthPage.verifyStoreHealthPageAvailability();
    }
}
