package com.qa.ApiStepDef;

import com.qa.APIMetods.NewBlackPage;
import com.qa.webpageLocators.BaseWebPage;
import com.qa.webpageLocators.HomeWebPage;
import com.qa.webpageLocators.LoginWebPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class NewBlackStepDef{

    NewBlackPage newblack;

    @Given("verify Newblack device details based on store selection")
    public void verify_Newblack_device_details_based_on_store_selection() {
        newblack = new NewBlackPage();
        newblack.getDetailsofNewBlackDeviceDetails();
    }

}
