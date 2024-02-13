package utility;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.deque.html.axecore.args.AxeRunOnlyOptions;
import com.deque.html.axecore.args.AxeRunOptions;
import com.deque.html.axecore.results.CheckedNode;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

import static org.testng.util.Strings.escapeHtml;

public class AxeMethods {
    public static AxeBuilder createAxeBuilder() {
        AxeRunOnlyOptions runOnlyOptions = new AxeRunOnlyOptions();
        runOnlyOptions.setType("tag");
        runOnlyOptions.setValues(Arrays.asList("wcag2a", "wcag2aa"));
        AxeRunOptions options = new AxeRunOptions();
        options.setRunOnly(runOnlyOptions);
        return new AxeBuilder().withOptions(options);
    }


    public static void logViolations(List<Rule> violations, ExtentTest extentTest) {
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

    public static void analyzeCreateLabelAndLogViolations(AxeBuilder axeBuilder, WebDriver driver, ExtentTest extentTest, String page, String URL) {
        Results axeResults = axeBuilder.analyze(driver);
        ExtentReportsMethods.createResultLabel(extentTest, page, URL);
        AxeMethods.logViolations(axeResults.getViolations(), extentTest);
    }
}
