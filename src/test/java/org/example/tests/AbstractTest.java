package org.example.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.deque.html.axecore.args.AxeRunOnlyOptions;
import com.deque.html.axecore.args.AxeRunOptions;
import com.deque.html.axecore.results.CheckedNode;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.testng.util.Strings.escapeHtml;


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
        AxeRunOnlyOptions runOnlyOptions = new AxeRunOnlyOptions();
        runOnlyOptions.setType("tag");
        runOnlyOptions.setValues(Arrays.asList("wcag2a", "wcag2aa"));
        AxeRunOptions options = new AxeRunOptions();
        options.setRunOnly(runOnlyOptions);
        axeBuilder = new AxeBuilder().withOptions(options);
        extent = new ExtentReports();
        CONF = new File("src/test/resources/report-config/spark-config.json");
    }


    public void logViolations(List<Rule> violations, ExtentTest extentTest) {
        if (!violations.isEmpty()) {
            extentTest.log(Status.FAIL, "Number of Violations: " + violations.size());
            for (int i = 0; i < violations.size(); i++) {
                Rule violation = violations.get(i);
                extentTest.log(Status.FAIL, "Violation " + (i + 1) + ": " + violation.getHelp());
                extentTest.log(Status.INFO, "Impact: " + violation.getImpact());
                extentTest.log(Status.INFO, "Tag: " + violation.getTags());
                extentTest.log(Status.INFO, "Description: " + violation.getDescription());
                List<CheckedNode> nodes = violation.getNodes();
                if (!nodes.isEmpty()) {
                    extentTest.log(Status.INFO, "  - Affected Nodes:");
                    for (int j = 0; j < nodes.size(); j++) {
                        CheckedNode node = nodes.get(j);
                        extentTest.log(Status.INFO, "     - Node " + (j + 1) + ":");
                        String formattedHtml = escapeHtml(node.getHtml());
                        extentTest.log(Status.INFO, "       - HTML: " + formattedHtml);
                    }
                }
                extentTest.log(Status.INFO, "For more information and recommendations, refer to the following link: " + violation.getHelpUrl());
            }
        } else {
            extentTest.log(Status.PASS, "No violations found.");
        }
    }

    public ExtentTest startExtentTest(String page) {
        String author = "Nemanja Avric";
        String system = "DESKTOP-UG7LVM5";
        String category = "ACCESSIBILITY";
        return extent.createTest("Check Accessibility of the " + page).assignAuthor(author).assignDevice(system).assignCategory(category);
    }


    public void createResultLabel(ExtentTest extentTest, String pageName, String pageURL) {
        extentTest.info(MarkupHelper.createLabel("Axe Results for " + pageName + ": <a href=\"" + pageURL + "\">" + pageURL + "</a>  browser: " + System.getProperty("browser"), ExtentColor.AMBER));
    }

    public void analyzeCreateLabelAndLogViolations(ExtentTest extentTest, String page, String URL) {
        Results axeResults = axeBuilder.analyze(driver);
        createResultLabel(extentTest, page, URL);
        logViolations(axeResults.getViolations(), extentTest);
    }


    public ExtentSparkReporter createSpark(String pathToGenerateReport) throws IOException {
        ExtentSparkReporter spark = new ExtentSparkReporter(pathToGenerateReport);
        spark.loadJSONConfig(CONF);
        return spark;
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
