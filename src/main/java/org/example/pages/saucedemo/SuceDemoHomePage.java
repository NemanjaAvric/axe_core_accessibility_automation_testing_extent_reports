package org.example.pages.saucedemo;

import org.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SuceDemoHomePage extends AbstractPage {

    @FindBy(id = "shopping_cart_container")
    private WebElement shoppingCartIcon;
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackPackToCartButton;
    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement addBikeLightToCartButton;

    public SuceDemoHomePage(WebDriver driver) {
        super(driver);
    }

    public void addAndGoToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addBackPackToCartButton));
        addBackPackToCartButton.click();
        addBikeLightToCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(shoppingCartIcon));
        shoppingCartIcon.click();
    }

}
