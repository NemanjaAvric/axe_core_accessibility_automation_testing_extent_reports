package org.example.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.saucedemo.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utility.FakeData;

import java.io.IOException;

public class AccSauceDemoTest extends AbstractTest {

    private SauceDemoLoginPage sauceDemoLoginPage;

    private SuceDemoHomePage sauceDemoHomePage;

    private SauceDemoCheckoutYourInfomationPage sauceDemoCheckoutYourInfomationPage;

    private SauceDemoYourCartPage sauceDemoYourCartPage;

    private SauceDemoCheckoutOverviewPage sauceDemoCheckoutOverviewPage;

    private final static String SAUCE_DEMO_LOGIN_PAGE_URL = "https://www.saucedemo.com/";

    private final static String SAUCE_DEMO_HOME_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    private final static String SAUCE_DEMO_YOUR_CART_PAGE_URL = "https://www.saucedemo.com/cart.html";

    private final static String SAUCE_DEMO_CHECKOUT_YOUR_INFORMATION_PAGE = "https://www.saucedemo.com/checkout-step-one.html";

    private final static String SAUCE_DEMO_CHECKOUT_OVERVIEW_PAGE = "https://www.saucedemo.com/checkout-step-two.html";

    private final static String SAUCE_DEMO_CHECKOUT_COMPLETE_PAGE = "https://www.saucedemo.com/checkout-complete.html";

    private ExtentTest extentTest;

    private ExtentSparkReporter spark;


    @BeforeTest(dependsOnMethods = "setAxeBuilderAndExtentReports")
    public void setUpSpark() throws IOException {
        String reportPath = "target/reports/AccSauceDemoTest.html";
        spark = createSpark(reportPath);
        extent.attachReporter(spark);
    }


    @Test
    public void checkLoginPage() {
        String page = "Sauce Demo Login Page";
        extentTest = startExtentTest(page);
        goTo(SAUCE_DEMO_LOGIN_PAGE_URL);
        analyzeCreateLabelAndLogViolations(extentTest, page, SAUCE_DEMO_LOGIN_PAGE_URL);
    }

    @Test(dependsOnMethods = "checkLoginPage")
    public void checkHomePage() {
        String page = "Sauce Demo Home Page";
        extentTest = startExtentTest(page);
        sauceDemoLoginPage = new SauceDemoLoginPage(driver);
        sauceDemoLoginPage.login("standard_user", "secret_sauce");
        analyzeCreateLabelAndLogViolations(extentTest, page, SAUCE_DEMO_HOME_PAGE_URL);
    }

    @Test(dependsOnMethods = "checkHomePage")
    public void checkYourCartPage() {
        String page = "Sauce Demo Your Cart Page";
        extentTest = startExtentTest(page);
        sauceDemoHomePage = new SuceDemoHomePage(driver);
        sauceDemoHomePage.addAndGoToCart();
        analyzeCreateLabelAndLogViolations(extentTest, page, SAUCE_DEMO_YOUR_CART_PAGE_URL);
    }

    @Test(dependsOnMethods = "checkYourCartPage")
    public void checkYourInformationPage() {
        String page = "Sauce Demo Your Information Page";
        extentTest = startExtentTest(page);
        sauceDemoYourCartPage = new SauceDemoYourCartPage(driver);
        sauceDemoYourCartPage.clickCheckoutButton();
        analyzeCreateLabelAndLogViolations(extentTest, page, SAUCE_DEMO_CHECKOUT_YOUR_INFORMATION_PAGE);
    }

    @Test(dependsOnMethods = "checkYourInformationPage")
    public void checkCheckoutOverwievPage() {
        String page = "Sauce Demo Checkout Overview Page";
        extentTest = startExtentTest(page);
        sauceDemoCheckoutYourInfomationPage = new SauceDemoCheckoutYourInfomationPage(driver);
        sauceDemoCheckoutYourInfomationPage.fillCheckOutInfomation(FakeData.getFakeFirstName(), FakeData.getFakeLastName(), FakeData.getFakeZipPostalCode());
        analyzeCreateLabelAndLogViolations(extentTest, page, SAUCE_DEMO_CHECKOUT_OVERVIEW_PAGE);
    }


  /*  @Test(dependsOnMethods = "checkYourInformationPage")
    public void checkCheckoutCompletePage() {
        String page = "Sauce Demo Checkout Complete Page";
        extentTest = startExtentTest(page);
        sauceDemoCheckoutOverviewPage = new SauceDemoCheckoutOverviewPage(driver);
        sauceDemoCheckoutOverviewPage.clickFinishButton();
        analyzeCreateLabelAndLogViolations(extentTest, page, SAUCE_DEMO_CHECKOUT_COMPLETE_PAGE);
    }*/

}
