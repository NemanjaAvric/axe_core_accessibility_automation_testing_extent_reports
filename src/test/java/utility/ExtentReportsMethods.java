package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

public class ExtentReportsMethods {
    public static ExtentTest startExtentTest(String page, ExtentReports extent) {
        String author = "Nemanja Avric";
        String system = "DESKTOP-UG7LVM5";
        String category = "ACCESSIBILITY";
        return extent.createTest("Check Accessibility of the " + page).assignAuthor(author).assignDevice(system).assignCategory(category);
    }


    public static void createResultLabel(ExtentTest extentTest, String pageName, String pageURL) {
        extentTest.info(MarkupHelper.createLabel("Axe Results for " + pageName + ": <a href=\"" + pageURL + "\">" + pageURL + "</a>  browser: " + System.getProperty("browser"), ExtentColor.AMBER));
    }

    public static ExtentSparkReporter createSpark(String pathToGenerateReport, File CONF) throws IOException {
        ExtentSparkReporter spark = new ExtentSparkReporter(pathToGenerateReport);
        spark.loadJSONConfig(CONF);
        return spark;
    }
}
