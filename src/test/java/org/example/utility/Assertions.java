package org.example.utility;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.Assert;

public class Assertions {

    public static void assertEquals(ExtentTest extentTest, String actual, String expected) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (AssertionError e) {
            String errorMessage = "Assertion failed: " + e.getMessage();
            extentTest.fail(MarkupHelper.createLabel(errorMessage, ExtentColor.RED));
        }
    }
}
