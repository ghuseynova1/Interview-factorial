package com.factorial.pages;

import commonLibs.implementation.ElementControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;
    public ElementControl elementControl;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        elementControl = new ElementControl(driver);
    }

    public void waitForCondition(ExpectedCondition<Boolean> isTrue){

        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(isTrue);
    }

    public String getPageBody() {
        return driver.findElement(By.tagName("body")).getText();
    }

}
