package com.factorial.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class FactorialPage extends BasePage{

    public FactorialPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @CacheLookup
    @FindBy(id = "number")
    private WebElement inputField;

    @CacheLookup
    @FindBy(id="getFactorial")
    private WebElement calculateButton;

    @CacheLookup
    @FindBy(id="resultDiv")
    private WebElement resultField;

    @CacheLookup
    @FindBy(linkText = "Terms and Conditions")
    private WebElement termsAndConditionsLink;

    @CacheLookup
    @FindBy(linkText = "Privacy")
    private WebElement privacyLink;

    public WebElement getInputField() {
        return inputField;
    }

    public void calculateFactorial (String input){
        String lastResult = resultField.getText();
        inputField.clear();
        inputField.sendKeys(input);
        calculateButton.click();
        waitForCondition(driver -> !resultField.getText().equals(lastResult));
    }

    public WebElement getResultField() {
        return resultField;
    }

    public TermsAndConditionsPage clickTermsAndConditionsLink() {
        termsAndConditionsLink.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return new TermsAndConditionsPage(driver);
    }

    public PrivacyPage clickPrivacyLink() {
        privacyLink.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return new PrivacyPage(driver);
    }
}
