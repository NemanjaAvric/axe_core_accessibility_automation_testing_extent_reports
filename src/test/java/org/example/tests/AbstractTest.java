package org.example.tests;

import com.aventstack.extentreports.ExtentReports;
import com.deque.html.axecore.selenium.AxeBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utility.AxeMethods;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public abstract class AbstractTest {

    protected WebDriver driver;
    protected AxeBuilder axeBuilder;
    protected ExtentReports extent;
    protected File CONF;


    @BeforeTest
    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        if (System.getProperty("browser").equalsIgnoreCase("chrome")) {
            this.driver = new ChromeDriver();
        } else {
            this.driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeTest(dependsOnMethods = "setDriver")
    public void setAxeBuilderAndExtentReports() throws IOException {
        axeBuilder = AxeMethods.createAxeBuilder();
        extent = new ExtentReports();
        CONF = new File("src/test/resources/report-config/spark-config.json");
    }


    public void goTo(String url) {
        driver.get(url);
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }

    @AfterSuite
    public void flushExtentReports() {
        extent.flush();
    }

}
