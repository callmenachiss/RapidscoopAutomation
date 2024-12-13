package com.qa.utils;

public interface Constants {

    interface Config {
        String CONFIG_PROPERTIES = "config.properties";
        String IMAGE_PNG = "image/png";
        String BASE_WEB_URL="webUrl";
        String IOS_PLATFORM_NAME = "iOSPlatformName";
        String ROUTINGKEY = "ROUTINGKEY";
        String IOS_DEVICE_NAME = "iOSDeviceName";
    }

    interface WaitTime {
        long TWENTY_FIVE = 17;
    }



    interface File {
        String TEST_RESOURCES_PATH = "src/test/resources/jsonFiles/";
    }

    interface AssertStaticText {
        String DISCOUNT_ADDED = "Obsolete Line Discount";
        String CUSTOMER_DETAILS = "Consumer Details";
    }

    interface ToastMessage {
        String PRODUCT_ADDED = "Product Added";
        String PRICE_CORRECTION_TOAST_MESSAGE = "Price modified";

        String DISCOUNT_ADDED_TOAST = "Discount Added.";

        String BEAUTY_PRO = "BEAUTY PRO 30 MIN AE";

    }

    interface SwipeElementLabelText {
        String MODIFY_PRICE = "Modify Price";
        String ORDER_OPTIONS = "Order Options";
        String PAYMENT_METHODS = "PAYMENT METHODS";
        String LOYALTY_PROGRAM_SUBSCRIPTION = "KIKO Me Loyalty Program";
        String LOYALTY_PROGRAM = "Loyalty Program";
        String CREATE_RETURN = "Create Return";

        String TEST_REASON = "Test reason";
    }

    interface OrderOptions {
        String MUA = "MUA";
        String PPLUS = "P+";

    }

}
