package org.example.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.saucedemo.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utility.AxeMethods;
import utility.ExtentReportsMethods;
import utility.FakeData;
import utility.URL;

import java.io.IOException;

public class AccSauceDemoTest extends AbstractTest {

    private SauceDemoLoginPage sauceDemoLoginPage;

    private SuceDemoHomePage sauceDemoHomePage;

    private SauceDemoCheckoutYourInfomationPage sauceDemoCheckoutYourInfomationPage;

    private SauceDemoYourCartPage sauceDemoYourCartPage;

    private SauceDemoCheckoutOverviewPage sauceDemoCheckoutOverviewPage;

    private ExtentTest extentTest;

    private ExtentSparkReporter spark;


    @BeforeTest(dependsOnMethods = "setAxeBuilderAndExtentReports")
    public void setUpSpark() throws IOException {
        String reportPath = "target/reports/AccSauceDemoTest.html";
        spark = ExtentReportsMethods.createSpark(reportPath, CONF);
        extent.attachReporter(spark);
    }


    @Test
    public void checkLoginPage() {
        String page = "Sauce Demo Login Page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        goTo(URL.SAUCE_DEMO_LOGIN_PAGE_URL);
        Assert.assertEquals(driver.getCurrentUrl(), URL.SAUCE_DEMO_LOGIN_PAGE_URL);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, URL.SAUCE_DEMO_LOGIN_PAGE_URL);
    }

    @Test(dependsOnMethods = "checkLoginPage")
    public void checkHomePage() {
        String page = "Sauce Demo Home Page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        sauceDemoLoginPage = new SauceDemoLoginPage(driver);
        sauceDemoLoginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), URL.SAUCE_DEMO_HOME_PAGE_URL);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, URL.SAUCE_DEMO_HOME_PAGE_URL);
    }

    @Test(dependsOnMethods = "checkHomePage")
    public void checkYourCartPage() {
        String page = "Sauce Demo Your Cart Page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        sauceDemoHomePage = new SuceDemoHomePage(driver);
        sauceDemoHomePage.addAndGoToCart();
        Assert.assertEquals(driver.getCurrentUrl(), URL.SAUCE_DEMO_YOUR_CART_PAGE_URL);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, URL.SAUCE_DEMO_YOUR_CART_PAGE_URL);
    }

    @Test(dependsOnMethods = "checkYourCartPage")
    public void checkYourInformationPage() {
        String page = "Sauce Demo Your Information Page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        sauceDemoYourCartPage = new SauceDemoYourCartPage(driver);
        sauceDemoYourCartPage.clickCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), URL.SAUCE_DEMO_CHECKOUT_YOUR_INFORMATION_PAGE);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, URL.SAUCE_DEMO_CHECKOUT_YOUR_INFORMATION_PAGE);
    }

    @Test(dependsOnMethods = "checkYourInformationPage")
    public void checkCheckoutOverviewPage() {
        String page = "Sauce Demo Checkout Overview Page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        sauceDemoCheckoutYourInfomationPage = new SauceDemoCheckoutYourInfomationPage(driver);
        sauceDemoCheckoutYourInfomationPage.fillCheckOutInfomation(FakeData.getFakeFirstName(), FakeData.getFakeLastName(), FakeData.getFakeZipPostalCode());
        Assert.assertEquals(driver.getCurrentUrl(), URL.SAUCE_DEMO_CHECKOUT_OVERVIEW_PAGE);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, URL.SAUCE_DEMO_CHECKOUT_OVERVIEW_PAGE);
    }


    @Test(dependsOnMethods = "checkCheckoutOverviewPage")
    public void checkCheckoutCompletePage() {
        String page = "Sauce Demo Checkout Complete Page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        sauceDemoCheckoutOverviewPage = new SauceDemoCheckoutOverviewPage(driver);
        sauceDemoCheckoutOverviewPage.clickFinishButton();
        Assert.assertEquals(driver.getCurrentUrl(), URL.SAUCE_DEMO_CHECKOUT_COMPLETE_PAGE);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, URL.SAUCE_DEMO_CHECKOUT_COMPLETE_PAGE);
    }

}
