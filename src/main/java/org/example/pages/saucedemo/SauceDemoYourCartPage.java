package org.example.pages.saucedemo;

import org.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SauceDemoYourCartPage extends AbstractPage {
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public SauceDemoYourCartPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
    }
}
