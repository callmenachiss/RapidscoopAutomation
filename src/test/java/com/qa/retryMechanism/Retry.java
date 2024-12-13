package com.qa.retryMechanism;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int maxTry = 1;
    int count = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(!iTestResult.isSuccess()){
            if(count < maxTry) {
                count++;
                return true;
            }
        }
        return false;
    }
    public boolean isRetryAvailable() {
        return count < maxTry;
    }
}


