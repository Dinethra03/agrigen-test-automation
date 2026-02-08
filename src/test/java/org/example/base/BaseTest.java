package org.example.base;


import org.example.testdata.LoginTestData;
import org.example.utils.ScreenshotUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.time.Duration;

/**
 * Base test class for AgriGEN automation
 * All test classes should extend this class
 * Provides setup, teardown, and automatic screenshot capture on failure
 */
public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    // Use the JDK logger to avoid external logging dependency issues in the IDE/build
    private static final Logger logger = Logger.getLogger(BaseTest.class.getName());

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        System.out.println("========================================");
        System.out.println("Setting up test with browser: " + browser);
        System.out.println("========================================");

        // Initialize driver based on browser parameter
        driver = initializeDriver(browser);

        // Maximize the browser window to ensure visibility
        driver.manage().window().maximize();

        // Bring the browser window to the foreground
        driver.switchTo().window(driver.getWindowHandle());

        // Configure timeouts
        // Reduce implicit wait to keep tests responsive
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        // Use a pragmatic explicit wait default
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to login page
        driver.get(LoginTestData.LOGIN_PAGE_URL);
        System.out.println("✓ Navigated to: " + LoginTestData.LOGIN_PAGE_URL);
    }

    /**
     * Initialize WebDriver based on browser type
     * @param browser Browser name (chrome, firefox, edge)
     * @return Configured WebDriver instance
     */
    private WebDriver initializeDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--start-maximized");
                // Uncomment for headless mode
                // chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                // Uncomment for headless mode
                // firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                edgeOptions.addArguments("--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                System.out.println("⚠ Unknown browser: " + browser + ", defaulting to Chrome");
                WebDriverManager.chromedriver().setup();
                ChromeOptions defaultOptions = new ChromeOptions();
                defaultOptions.addArguments("--remote-allow-origins=*");
                defaultOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(defaultOptions);
                break;
        }

        return driver;
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        System.out.println("========================================");

        // Capture screenshot if test failed
        if (result.getStatus() == ITestResult.FAILURE) {
            String testName = result.getName();
            System.out.println("✗ Test FAILED: " + testName);
            System.out.println("Error: " + result.getThrowable().getMessage());
            ScreenshotUtil.captureScreenshot(driver, testName + "_FAILED", "FailedTests");
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("✓ Test PASSED: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            System.out.println("⊘ Test SKIPPED: " + result.getName());
        }

        System.out.println("========================================\n");

        // Close browser
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Helper method to capture screenshot manually during test execution
     * @param screenshotName Name for the screenshot
     */
    protected void takeScreenshot(String screenshotName) {
        ScreenshotUtil.captureScreenshot(driver, screenshotName, "TestProgress");
    }

    /**
     * Helper method to wait for a specified duration
     * @param milliseconds Time to wait in milliseconds
     */
    protected void waitFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            // Log using java.util.logging
            logger.log(Level.SEVERE, "Thread interrupted during wait: ", e);
        }
    }
}