package com.qa.APIMetods;


import com.qa.config.DriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class BaseAPIPage extends DriverManager {

    private static final Logger LOGGER = LogManager.getLogger(BaseAPIPage.class);

    public RequestSpecification spec;
    public Response response;

    public void startAPI(){
        spec = RestAssured.given();
    }

    public String USERNAME = props.getProperty("user");
    public String PASSWORD = props.getProperty("pwd");



    public String TARGET_URL = props.getProperty("API_env");
    public String devUrl = props.getProperty("dev_url");
    public String testUrl = props.getProperty("test_url");

    public String newblack_API1 = props.getProperty("newblack_device");


    public void hitTargetUrl(String url,String endpoint){
        startAPI();
        if(TARGET_URL.equalsIgnoreCase("dev")){
            spec.baseUri(url);
            spec.basePath(endpoint);
        }else if(TARGET_URL.equalsIgnoreCase("test")){
            spec.baseUri(url);
            spec.basePath(endpoint);
        }else{
            System.out.println("Please choose the target url");
        }
    }

    public void HandleAuthentication(String uname,String pwd){
        response=spec.auth().basic(uname,pwd).get();
        response.then().log().all();
    }


    public void verifyStatusCodePass(){
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
