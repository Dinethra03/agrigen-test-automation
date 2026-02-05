package org.example.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class to capture and save screenshots during test execution
 */
public class ScreenshotUtil {

    /**
     * Captures screenshot and saves it with a timestamp and test name
     * @param driver WebDriver instance
     * @param testName Name of the test case
     * @return Path to the saved screenshot
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            // Create screenshots directory if it doesn't exist
            String screenshotDir = System.getProperty("user.dir") + File.separator + "test-screenshots" + File.separator;
            File directory = new File(screenshotDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generate timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

            // Generate filename
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = screenshotDir + fileName;

            // Take screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(filePath);

            // Copy to destination
            FileUtils.copyFile(sourceFile, destinationFile);

            System.out.println("✓ Screenshot saved: " + filePath);
            return filePath;

        } catch (Exception e) {
            System.out.println("✗ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Captures screenshot with custom subfolder organization
     * @param driver WebDriver instance
     * @param testName Name of the test case
     * @param subFolder Subfolder name (e.g., "LoginTests", "DashboardTests")
     * @return Path to the saved screenshot
     */
    public static String captureScreenshot(WebDriver driver, String testName, String subFolder) {
        try {
            // Create screenshots directory with subfolder
            String screenshotDir = System.getProperty("user.dir") + File.separator +
                    "test-screenshots" + File.separator +
                    subFolder + File.separator;
            File directory = new File(screenshotDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generate timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

            // Generate filename
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = screenshotDir + fileName;

            // Take screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(filePath);

            // Copy to destination
            FileUtils.copyFile(sourceFile, destinationFile);

            System.out.println("✓ Screenshot saved: " + filePath);
            return filePath;

        } catch (Exception e) {
            System.out.println("✗ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Captures screenshot without timestamp (for comparison purposes)
     * @param driver WebDriver instance
     * @param fileName Exact filename to use
     * @param subFolder Subfolder name
     * @return Path to the saved screenshot
     */
    public static String captureScreenshotNoTimestamp(WebDriver driver, String fileName, String subFolder) {
        try {
            String screenshotDir = System.getProperty("user.dir") + File.separator +
                    "test-screenshots" + File.separator +
                    subFolder + File.separator;
            File directory = new File(screenshotDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filePath = screenshotDir + fileName + ".png";

            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(filePath);

            FileUtils.copyFile(sourceFile, destinationFile);

            System.out.println("✓ Screenshot saved: " + filePath);
            return filePath;

        } catch (Exception e) {
            System.out.println("✗ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}