package com.factorial.tests;

import com.aventstack.extentreports.Status;
import com.factorial.pages.FactorialPage;
import commonLibs.implementation.CommonDriver;
import commonLibs.utils.ConfigUtils;
import commonLibs.utils.ReportUtils;
import commonLibs.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.util.Properties;

public class BaseTest {
    CommonDriver cmnDriver;
    String url;
    WebDriver driver;

    FactorialPage factorialPage;

    String currentWorkingDirectory;
    String configFileName;
    Properties configProperty;

    String reportFileName;
    ReportUtils reportUtils;

    ScreenshotUtils screenshot;

    @BeforeSuite
    public void preSetup() throws Exception {
        currentWorkingDirectory = System.getProperty("user.dir");

        configFileName = currentWorkingDirectory + "/config/config.properties";
        reportFileName = currentWorkingDirectory + "/reports/factorialTestReport.html";

        configProperty = ConfigUtils.readProperties(configFileName);

        reportUtils = new ReportUtils(reportFileName);
    }

    @BeforeClass
    public void setup() throws Exception {

        url = configProperty.getProperty("baseUrl");
        String browserType = configProperty.getProperty("browserType");
        cmnDriver = new CommonDriver(browserType);

        driver = cmnDriver.getDriver();
        factorialPage = new FactorialPage(driver);

        screenshot = new ScreenshotUtils(driver);
        cmnDriver.navigateToUrl(url);
    }

    @AfterMethod
    public void postTestAction(ITestResult result) throws Exception {

        String testCaseName = result.getMethod().getMethodName();

        long executeTime = System.currentTimeMillis();
        String screenshotFileName = testCaseName + executeTime + ".jpeg";

        if(result.getStatus() == ITestResult.FAILURE) {

            reportUtils.addTestLog(Status.FAIL,"One or more steps failed");
            screenshot.captureAndSaveScreenshot(currentWorkingDirectory +
                    File.separator +
                    "screenshots" +
                    File.separator + screenshotFileName);
            reportUtils.attachScreenshotToReport( ".."+File.separator + "screenshots" + File.separator + screenshotFileName);
        }
        if(!driver.getCurrentUrl().equals(url)){
            cmnDriver.navigateToUrl(url);
        }
    }

    @AfterClass
    public void closeDown() {
        cmnDriver.closeAll();
    }

    @AfterSuite
    public void postCloseDown() {
        reportUtils.flushReport();
    }
}
