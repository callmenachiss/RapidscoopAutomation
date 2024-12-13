package com.qa.APIMetods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class NewBlackPage extends BaseAPIPage{

    private static final Logger LOGGER = LogManager.getLogger(NewBlackPage.class);


    public void getDetailsofNewBlackDeviceDetails(){
        hitTargetUrl(devUrl,newblack_API1);
        LOGGER.info("User is launched the API endpoint");
        HandleAuthentication(USERNAME,PASSWORD);
        LOGGER.info("User is entered the authentication");
        verifyStatusCodePass();
        LOGGER.info("User is verified the status code");
    }
}
