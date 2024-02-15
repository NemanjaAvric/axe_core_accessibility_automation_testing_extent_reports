package org.example.tests.saucedemo;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.pages.saucedemo.*;
import org.example.tests.AbstractTest;
import org.example.tests.saucedemo.model.AccSauceDemoTestData;
import org.example.utility.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class AccSauceDemoTest extends AbstractTest {

    private SauceDemoLoginPage sauceDemoLoginPage;

    private SuceDemoHomePage sauceDemoHomePage;

    private SauceDemoCheckoutYourInfomationPage sauceDemoCheckoutYourInfomationPage;

    private SauceDemoYourCartPage sauceDemoYourCartPage;

    private SauceDemoCheckoutOverviewPage sauceDemoCheckoutOverviewPage;

    private ExtentTest extentTest;

    private ExtentSparkReporter spark;

    private AccSauceDemoTestData accSauceDemoTestData;


    @BeforeTest(dependsOnMethods = "setAxeBuilderAndExtentReports")
    @Parameters({"testDataPath"})
    public void setParameters(String testDataPath) {
        this.accSauceDemoTestData = JsonUtil.getTestData(testDataPath, AccSauceDemoTestData.class);
    }


    @BeforeTest(dependsOnMethods = "setParameters")
    public void setUpSpark() throws IOException {
        String reportPath = switch (accSauceDemoTestData.username()) {
            case "standard_user" -> "target/reports/AccSauceDemoTest/StandardUser.html";
            case "performance_glitch_user" -> "target/reports/AccSauceDemoTest/PerformanceGlitchUser.html";
            case "visual_user" -> "target/reports/AccSauceDemoTest/VisualUser.html";
            case "error_user" -> "target/reports/AccSauceDemoTest/ErrorUser.html";
            case "problem_user" -> "target/reports/AccSauceDemoTest/ProblemUser.html";
            default -> "target/reports/AccSauceDemoTest/LockedOutUser.html";
        };
        spark = ExtentReportsMethods.createSpark(reportPath, CONF);
        extent.attachReporter(spark);
    }


    @Test
    public void checkLoginPage() {
        String page = "Sauce Demo Login Page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        goTo(URL.SAUCE_DEMO_LOGIN_PAGE_URL);
        Assertions.assertEquals(extentTest, driver.getCurrentUrl(), URL.SAUCE_DEMO_LOGIN_PAGE_URL);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, driver.getCurrentUrl());
    }

    @Test(dependsOnMethods = "checkLoginPage")
    public void checkHomePage() {
        String page = "Sauce Demo Home Page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        sauceDemoLoginPage = new SauceDemoLoginPage(driver);
        sauceDemoLoginPage.login(accSauceDemoTestData.username(), accSauceDemoTestData.password());
        Assertions.assertEquals(extentTest, driver.getCurrentUrl(), URL.SAUCE_DEMO_HOME_PAGE_URL);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, driver.getCurrentUrl());
    }

    @Test(dependsOnMethods = "checkHomePage")
    public void checkYourCartPage() {
        String page = "Sauce Demo Your Cart Page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        sauceDemoHomePage = new SuceDemoHomePage(driver);
        sauceDemoHomePage.addAndGoToCart();
        Assertions.assertEquals(extentTest, driver.getCurrentUrl(), URL.SAUCE_DEMO_YOUR_CART_PAGE_URL);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, driver.getCurrentUrl());
    }

    @Test(dependsOnMethods = "checkYourCartPage")
    public void checkYourInformationPage() {
        String page = "Sauce Demo Your Information Page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        sauceDemoYourCartPage = new SauceDemoYourCartPage(driver);
        sauceDemoYourCartPage.clickCheckoutButton();
        Assertions.assertEquals(extentTest, driver.getCurrentUrl(), URL.SAUCE_DEMO_CHECKOUT_YOUR_INFORMATION_PAGE);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, driver.getCurrentUrl());
    }

    @Test(dependsOnMethods = "checkYourInformationPage")
    public void checkCheckoutOverviewPage() {
        String page = "Sauce Demo Checkout Overview Page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        sauceDemoCheckoutYourInfomationPage = new SauceDemoCheckoutYourInfomationPage(driver);
        sauceDemoCheckoutYourInfomationPage.fillCheckOutYourInfomationForm(FakeData.getFakeFirstName(), FakeData.getFakeLastName(), FakeData.getFakeZipPostalCode());
        Assertions.assertEquals(extentTest, driver.getCurrentUrl(), URL.SAUCE_DEMO_CHECKOUT_OVERVIEW_PAGE);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, driver.getCurrentUrl());
    }


    @Test(dependsOnMethods = "checkCheckoutOverviewPage")
    public void checkCheckoutCompletePage() {
        String page = "Sauce Demo Checkout Complete Page";
        extentTest = ExtentReportsMethods.startExtentTest(page, extent);
        sauceDemoCheckoutOverviewPage = new SauceDemoCheckoutOverviewPage(driver);
        sauceDemoCheckoutOverviewPage.clickFinishButton();
        Assertions.assertEquals(extentTest, driver.getCurrentUrl(), URL.SAUCE_DEMO_CHECKOUT_COMPLETE_PAGE);
        AxeMethods.analyzeCreateLabelAndLogViolations(axeBuilder, driver, extentTest, page, driver.getCurrentUrl());
    }
}
