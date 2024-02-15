package org.example.pages.saucedemo;

import org.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SauceDemoCheckoutYourInfomationPage extends AbstractPage {


    @FindBy(id = "first-name")
    private WebElement firstNameInputField;
    @FindBy(id = "last-name")
    private WebElement lastNameInputField;
    @FindBy(id = "postal-code")
    private WebElement zipPostalCodeInputField;
    @FindBy(id = "continue")
    private WebElement continueButton;

    public SauceDemoCheckoutYourInfomationPage(WebDriver driver) {
        super(driver);
    }



    public void fillCheckOutYourInfomationForm(String firstName, String lastName, String zipPostalCode) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameInputField));
        firstNameInputField.sendKeys(firstName);
        lastNameInputField.sendKeys(lastName);
        zipPostalCodeInputField.sendKeys(zipPostalCode);
        continueButton.click();
    }
}
