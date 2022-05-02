package com.factorial.pages;

import commonLibs.implementation.ElementControl;
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

    public void calculateFactorial (String input){
     //   String lastResult = elementControl.getElementText(resultField);
        String lastResult = resultField.getText();
       // elementControl.clear(inputField);
        inputField.clear();
        //elementControl.setText(inputField,input);
        inputField.sendKeys(input);
      //  elementControl.clickElement(calculateButton);
        calculateButton.click();
        waitForCondition(driver -> !resultField.getText().equals(lastResult));  //need optimate
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
