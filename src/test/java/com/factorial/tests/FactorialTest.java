package com.factorial.tests;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class FactorialTest extends BaseTest {

    private static final String INVALID_INPUT_ERR_MESS = "Please enter an integer";
    private static final String NEGATIVE_INPUT_ERR_MESS = "Please enter a number great than 0";
    private static final String STYLE_OF_ERR_MESS_FIELD = "color: rgb(255, 0, 0);";

    @Test
    public void verifyTitleOfPage() {
        reportUtils.createATestCase("Verify page title with expected one");
        reportUtils.addTestLog(Status.INFO, "Performing of getting page title");
        String expectedTitle = "Factorial";
        reportUtils.addTestLog(Status.INFO, String.format("Expected Title: %s", expectedTitle));
        String actualTitle = cmnDriver.getTitleOfThePage();
        reportUtils.addTestLog(Status.INFO, String.format("Actual Title: %s", actualTitle));
        reportUtils.addTestLog(Status.INFO, "Comparing expected and actual title");
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @DataProvider
    public static Object[][] validFactorialValues() {
        return new Object[][]{
                {"0", "1"},
                {"5", "120"},
                {"31", "8.222838654177922e+33"},
                {"667", "Infinity"}
        };
    }

    @Test(dataProvider = "validFactorialValues")
    public void verifyFactorialResultsWithValidValues(String input, String result) {
        reportUtils.createATestCase(String.format("Verify factorial result of  %s is equal to %s ", input, result));
        reportUtils.addTestLog(Status.INFO, "Performing of calculation");

        factorialPage.calculateFactorial(input);

        reportUtils.addTestLog(Status.INFO, "Getting the actual result");
        String actualResult = factorialPage.getResultField().getText();
        String expectedResult = String.format("The factorial of %s is: %s", input, result);
        reportUtils.addTestLog(Status.INFO, "Comparing actual and expected results");
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void verifyFactorialResultsWithInvalidValue() {
        String input = "31ew";
        reportUtils.createATestCase(String.format("Verify factorial result(invalid input error message) of invalid input: %s", input));
        reportUtils.addTestLog(Status.INFO, "Performing of calculation");
        factorialPage.calculateFactorial(input);

        reportUtils.addTestLog(Status.INFO, "Getting the actual result");
        String actualResult = factorialPage.getResultField().getText();

        reportUtils.addTestLog(Status.INFO, "Comparing actual and expected results");
        Assert.assertEquals(actualResult, INVALID_INPUT_ERR_MESS);

        reportUtils.addTestLog(Status.INFO, "Comparing actual and expected styles of result field");
        String actualStyle = factorialPage.getResultField().getAttribute("style");
        Assert.assertEquals(actualStyle, STYLE_OF_ERR_MESS_FIELD);
    }

    @Test
    public void verifyFactorialWithNegativeNumber() {
        String input = "-5";
        reportUtils.createATestCase(String.format("Verify factorial result of negative number: %s", input));
        reportUtils.addTestLog(Status.INFO, "Performing of calculation");
        try {
            factorialPage.calculateFactorial(input);
        } catch (TimeoutException ex) {
            reportUtils.addTestLog(Status.ERROR, "There is no any (negative input error message)result for negative numbers");
            String actualResult = factorialPage.getResultField().getText();
            reportUtils.addTestLog(Status.INFO, "Comparing actual and expected results");
            Assert.assertEquals(actualResult, NEGATIVE_INPUT_ERR_MESS);
        }

    }

    @Test
    public void verifyFactorialWithNumberGreatThan989() {
        String input = "990";
        reportUtils.createATestCase(String.format("Verify factorial result of number great than 989: %s", input));
        reportUtils.addTestLog(Status.INFO, "Performing of calculation");
        try {
            factorialPage.calculateFactorial(input);
        } catch (TimeoutException ex) {
            reportUtils.addTestLog(Status.ERROR,
                    String.format("There is no any (may be Infinity) result for this number %s", input));
            String actualResult = factorialPage.getResultField().getText();
            reportUtils.addTestLog(Status.INFO,"Comparing actual and expected results");
            Assert.assertEquals(actualResult, String.format("The factorial of %s is: Infinity", input));
        }
    }

    @Test
    public void verifyRedirectionOfTermsAndConditionsLink() {
        reportUtils.createATestCase("Verify Terms and Conditions link redirected to the appropriate page");

        reportUtils.addTestLog(Status.INFO, "Click the link");
        String actualText = factorialPage.clickTermsAndConditionsLink().getPageBody();
        String expectedText = "This is the terms and conditions document. We are not yet ready with it. Stay tuned!";

        reportUtils.addTestLog(Status.INFO, "Comparing page body with expected text");
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void verifyRedirectionOfPrivacyLink() {
        reportUtils.createATestCase("Verify Privacy link redirected to the appropriate page");

        reportUtils.addTestLog(Status.INFO, "Click the link");
        String actualText = factorialPage.clickPrivacyLink().getPageBody();
        String expectedText = "This is the privacy document. We are not yet ready with it. Stay tuned!";

        reportUtils.addTestLog(Status.INFO, "Comparing page body with expected text");
        Assert.assertEquals(actualText, expectedText);
    }


}
