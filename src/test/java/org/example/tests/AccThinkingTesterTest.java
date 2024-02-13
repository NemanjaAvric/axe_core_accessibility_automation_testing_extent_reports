package org.example.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.thinkingtester.ThinkingTesterAddContactPage;
import org.example.pages.thinkingtester.ThinkingTesterContactListPage;
import org.example.pages.thinkingtester.ThinkingTesterSignUpPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utility.FakeData;

import java.io.IOException;

public class AccThinkingTesterTest extends AbstractTest {

    private final static String THINKING_TESTER_CONTACT_LIST_PAGE_URL = "https://thinking-tester-contact-list.herokuapp.com/contactList";

    private final static String THINKING_TESTER_SIGNUP_PAGE_URL = "https://thinking-tester-contact-list.herokuapp.com/addUser";

    private final static String THINKING_TESTER_ADD_CONTACT_PAGE_URL = "https://thinking-tester-contact-list.herokuapp.com/addContact";

    private final static String THINKING_TESTER_LOGIN_PAGE_URL = "https://thinking-tester-contact-list.herokuapp.com/";
    private ExtentTest extentTest;

    private ExtentSparkReporter spark;

    private ThinkingTesterSignUpPage thinkingTesterSignUpPage;

    private ThinkingTesterContactListPage thinkingTesterContactListPage;

    private ThinkingTesterAddContactPage thinkingTesterAddContactPage;


    @BeforeTest(dependsOnMethods = "setAxeBuilderAndExtentReports")
    public void setUpSpark() throws IOException {
        String reportPath = "target/reports/AccThinkingTesterTest.html";
        spark = createSpark(reportPath);
        extent.attachReporter(spark);
    }


    @Test
    public void checkAccessibilityOfTheThinkingTesterSignUpPage() {
        String page = "Thinking Tester Sing up page";
        extentTest = startExtentTest(page);
        goTo(THINKING_TESTER_SIGNUP_PAGE_URL);
        analyzeCreateLabelAndLogViolations(extentTest, page, THINKING_TESTER_SIGNUP_PAGE_URL);
    }

    @Test(dependsOnMethods = "checkAccessibilityOfTheThinkingTesterSignUpPage")
    public void checkAccessibilityOfTheThinkingTesterContactListPage() {
        String page = "Thinking Tester Contact List page";
        extentTest = startExtentTest(page);
        thinkingTesterSignUpPage = new ThinkingTesterSignUpPage(driver);
        thinkingTesterSignUpPage.signUp(FakeData.getFakeFirstName(), FakeData.getFakeLastName(), FakeData.getFakeEmail(), FakeData.getFakePassword());
        analyzeCreateLabelAndLogViolations(extentTest, page, THINKING_TESTER_CONTACT_LIST_PAGE_URL);
    }

    @Test(dependsOnMethods = "checkAccessibilityOfTheThinkingTesterContactListPage")
    public void checkAccessibilityOfTheThinkingTesterAddContactPage() {
        String page = "Thinking Tester Add Contact page";
        extentTest = startExtentTest(page);
        thinkingTesterContactListPage = new ThinkingTesterContactListPage(driver);
        thinkingTesterContactListPage.clickOnAddANewContactButton();
        analyzeCreateLabelAndLogViolations(extentTest, page, THINKING_TESTER_ADD_CONTACT_PAGE_URL);
    }

    @Test(dependsOnMethods = "checkAccessibilityOfTheThinkingTesterAddContactPage")
    public void checkAccessibilityOfTheThinkingTesterContactListPageAfterAddingAContact() {
        String page = "Thinking Tester Contact List page";
        extentTest = startExtentTest(page + " after adding a contact");
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
        analyzeCreateLabelAndLogViolations(extentTest, page, THINKING_TESTER_CONTACT_LIST_PAGE_URL);
        thinkingTesterAddContactPage.logOut();
    }

    @Test(dependsOnMethods = "checkAccessibilityOfTheThinkingTesterContactListPageAfterAddingAContact")
    public void checkAccessibilityOfTheThinkingTesterLogInPage() {
        String page = "Thinking Tester Log In page";
        extentTest = startExtentTest(page);
        analyzeCreateLabelAndLogViolations(extentTest, page, THINKING_TESTER_LOGIN_PAGE_URL);
    }
}
