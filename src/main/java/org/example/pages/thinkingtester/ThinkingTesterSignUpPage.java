package org.example.pages.thinkingtester;

import org.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ThinkingTesterSignUpPage extends AbstractPage {
    @FindBy(id = "firstName")
    private WebElement firstNameInputField;
    @FindBy(id = "lastName")
    private WebElement lastNameInputField;

    @FindBy(id = "email")
    private WebElement emailInputField;

    @FindBy(id = "password")
    private WebElement passwordInputField;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public ThinkingTesterSignUpPage(WebDriver driver) {
        super(driver);
    }


    public void signUp(String firstName, String lastName, String email, String password){
        firstNameInputField.sendKeys(firstName);
        lastNameInputField.sendKeys(lastName);
        emailInputField.sendKeys(email);
        passwordInputField.sendKeys(password);
        submitButton.click();
    }

}
