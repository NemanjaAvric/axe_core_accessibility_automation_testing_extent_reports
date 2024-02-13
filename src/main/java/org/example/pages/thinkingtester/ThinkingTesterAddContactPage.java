package org.example.pages.thinkingtester;

import org.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ThinkingTesterAddContactPage extends AbstractPage {

    @FindBy(id = "firstName")
    private WebElement firstNameInputField;
    @FindBy(id = "lastName")
    private WebElement lastNameInputField;
    @FindBy(id = "birthdate")
    private WebElement dateOfBirthInputField;

    @FindBy(id = "email")
    private WebElement emailInputField;

    @FindBy(id = "phone")
    private WebElement phoneInputField;

    @FindBy(id = "street1")
    private WebElement streetAdress1InputField;

    @FindBy(id = "street2")
    private WebElement streetAdress2InputField;
    @FindBy(id = "city")
    private WebElement cityInputField;
    @FindBy(id = "stateProvince")
    private WebElement stateOrProvinceInputField;
    @FindBy(id = "postalCode")
    private WebElement postalCodeInputField;
    @FindBy(id = "country")
    private WebElement countryInputField;
    @FindBy(id = "submit")
    private WebElement submitButton;
    @FindBy(id = "logout")
    private WebElement logoutButton;

    public ThinkingTesterAddContactPage(WebDriver driver) {
        super(driver);
    }

    public void addContact(String firstName, String lastName, String dateOfBirth, String email, String phone, String streetAdress1, String streetAdress2, String city, String stateOrProvince, String postalCode, String country) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameInputField));
        firstNameInputField.sendKeys(firstName);
        lastNameInputField.sendKeys(lastName);
        dateOfBirthInputField.sendKeys(dateOfBirth);
        emailInputField.sendKeys(email);
        phoneInputField.sendKeys(phone);
        streetAdress1InputField.sendKeys(streetAdress1);
        streetAdress2InputField.sendKeys(streetAdress2);
        cityInputField.sendKeys(city);
        stateOrProvinceInputField.sendKeys(stateOrProvince);
        postalCodeInputField.sendKeys(postalCode);
        countryInputField.sendKeys(country);
        submitButton.click();
    }

    public void logOut() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

}
