package org.example.pages.saucedemo;

import org.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SauceDemoLoginPage extends AbstractPage {


    @FindBy(id = "user-name")
    protected WebElement userIdInputField;
    @FindBy(id = "password")
    protected WebElement passwordInputField;
    @FindBy(id = "login-button")
    protected WebElement loginButton;


    public SauceDemoLoginPage(WebDriver driver) {
        super(driver);
    }


    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(userIdInputField));
        userIdInputField.sendKeys(username);
        passwordInputField.sendKeys(password);
        loginButton.click();
    }

}
