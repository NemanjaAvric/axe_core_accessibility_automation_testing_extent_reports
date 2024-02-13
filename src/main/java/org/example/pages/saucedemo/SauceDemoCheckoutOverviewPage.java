package org.example.pages.saucedemo;

import org.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SauceDemoCheckoutOverviewPage extends AbstractPage {
    @FindBy(id = "finish")
    private WebElement finishButton;

    public SauceDemoCheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void clickFinishButton() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finishButton.click();
    }
}
