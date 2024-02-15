package org.example.pages.thinkingtester;

import org.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ThinkingTesterContactListPage extends AbstractPage {

    @FindBy(id = "add-contact")
    private WebElement addANewContactButton;

    public ThinkingTesterContactListPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnAddANewContactButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addANewContactButton));
        addANewContactButton.click();
    }

}
