package com.factorial.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForCondition(ExpectedCondition<Boolean> isTrue){

        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(isTrue);
    }

    public String getPageBody() {
        return driver.findElement(By.tagName("body")).getText();
    }

}
