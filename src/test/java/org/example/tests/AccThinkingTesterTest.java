package org.example.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.thinkingtester.ThinkingTesterAddContactPage;
import org.example.pages.thinkingtester.ThinkingTesterContactListPage;
import org.example.pages.thinkingtester.ThinkingTesterSignUpPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utility.*;

import java.io.IOException;

public class AccThinkingTesterTest extends AbstractTest {

    private ExtentTest extentTest;

    private ExtentSparkReporter spark;

    private ThinkingTesterSignUpPage thinkingTesterSignUpPage;

    private ThinkingTesterContactListPage thinkingTesterContactListPage;

    private ThinkingTesterAddContactPage thinkingTesterAddContactPage;


    @BeforeTest(dependsOnMethods = "setAxeBuilderAndExtentReports")
    public void setUpSpark() throws IOException {
        String reportPath = "target/reports/AccThinkingTesterTest.html";
        spark = ExtentReportsMethods.createSpark(reportPath, CONF);
        extent.attachReporter(spark);
    }


    @Test
    public void checkAccessibilityOfTheThinkingTesterSignUpPage() {
        String page = "Thinking Tester Sing up page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        goTo(URL.THINKING_TESTER_SIGNUP_PAGE_URL);
        Assert.assertEquals(driver.getCurrentUrl(), URL.THINKING_TESTER_SIGNUP_PAGE_URL);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, URL.THINKING_TESTER_SIGNUP_PAGE_URL);
    }

    @Test(dependsOnMethods = "checkAccessibilityOfTheThinkingTesterSignUpPage")
    public void checkAccessibilityOfTheThinkingTesterContactListPage() {
        String page = "Thinking Tester Contact List page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        thinkingTesterSignUpPage = new ThinkingTesterSignUpPage(driver);
        thinkingTesterSignUpPage.signUp(FakeData.getFakeFirstName(), FakeData.getFakeLastName(), FakeData.getFakeEmail(), FakeData.getFakePassword());
        Wait.waitForPageToLoad(driver, URL.THINKING_TESTER_CONTACT_LIST_PAGE_URL);
        Assert.assertEquals(driver.getCurrentUrl(), URL.THINKING_TESTER_CONTACT_LIST_PAGE_URL);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, URL.THINKING_TESTER_CONTACT_LIST_PAGE_URL);
    }

    @Test(dependsOnMethods = "checkAccessibilityOfTheThinkingTesterContactListPage")
    public void checkAccessibilityOfTheThinkingTesterAddContactPage() {
        String page = "Thinking Tester Add Contact page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        thinkingTesterContactListPage = new ThinkingTesterContactListPage(driver);
        thinkingTesterContactListPage.clickOnAddANewContactButton();
        Wait.waitForPageToLoad(driver, URL.THINKING_TESTER_ADD_CONTACT_PAGE_URL);
        Assert.assertEquals(driver.getCurrentUrl(), URL.THINKING_TESTER_ADD_CONTACT_PAGE_URL);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, URL.THINKING_TESTER_ADD_CONTACT_PAGE_URL);
    }

    @Test(dependsOnMethods = "checkAccessibilityOfTheThinkingTesterAddContactPage")
    public void checkAccessibilityOfTheThinkingTesterContactListPageAfterAddingAContact() {
        String page = "Thinking Tester Contact List page";
        extentTest = ExtentReportsMethods.startExtentTest(page + " after adding a contact", extent);
        thinkingTesterAddContactPage = new ThinkingTesterAddContactPage(driver);
        thinkingTesterAddContactPage.addContact(FakeData.getFakeFirstName(),
                FakeData.getFakeLastName(),
                FakeData.getFakeDateOfBirth(),
                FakeData.getFakeEmail(),
                FakeData.getFakePhoneNumber(),
                FakeData.getFakeStreetAddress(),
                FakeData.getFakeStreetAddress(),
                FakeData.getFakeCity(),
                FakeData.getFakeState(),
                FakeData.getFakeZipPostalCode(),
                FakeData.getFakeCountry());
        Wait.waitForPageToLoad(driver, URL.THINKING_TESTER_CONTACT_LIST_PAGE_URL);
        Assert.assertEquals(driver.getCurrentUrl(), URL.THINKING_TESTER_CONTACT_LIST_PAGE_URL);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, URL.THINKING_TESTER_CONTACT_LIST_PAGE_URL);
        thinkingTesterAddContactPage.logOut();
    }

    @Test(dependsOnMethods = "checkAccessibilityOfTheThinkingTesterContactListPageAfterAddingAContact")
    public void checkAccessibilityOfTheThinkingTesterLogInPage() {
        String page = "Thinking Tester Log In page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        Wait.waitForPageToLoad(driver, URL.THINKING_TESTER_LOGIN_PAGE_URL);
        Assert.assertEquals(driver.getCurrentUrl(), URL.THINKING_TESTER_LOGIN_PAGE_URL);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, URL.THINKING_TESTER_LOGIN_PAGE_URL);
    }
}
