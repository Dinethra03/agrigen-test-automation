package tests;


import org.example.base.BaseTest;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;
import org.example.testdata.LoginTestData;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Comprehensive Login Page Test Suite for AgriGEN Application
 * Tests cover positive, negative, and security scenarios
 * All test data is managed centrally in LoginTestData.java
 */
public class LoginPageTest extends BaseTest {

    // ==================== POSITIVE SCENARIO ====================

    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        System.out.println("\n▶ Test 1: Valid Login Test");
        System.out.println("Credentials: " + LoginTestData.VALID_USERNAME);

        loginPage.enterUsername(LoginTestData.VALID_USERNAME);
        loginPage.enterPassword(LoginTestData.VALID_PASSWORD);
        takeScreenshot(LoginTestData.SCREENSHOT_VALID_LOGIN_BEFORE);

        loginPage.clickLogin();
        System.out.println("→ Login button clicked, waiting for loader and dashboard...");

        // Wait for loader and then dashboard
        loginPage.waitForLoaderToDisappear(15);

        // Verify navigation to dashboard
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(LoginTestData.DASHBOARD_URL),
                LoginTestData.ASSERT_DASHBOARD_URL + currentUrl);

        takeScreenshot(LoginTestData.SCREENSHOT_VALID_LOGIN_AFTER);

        // Additional verification - check dashboard elements
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isLogoDisplayed(), "AgriGEN logo should be visible");

        System.out.println(LoginTestData.VALID_LOGIN_SUCCESS_MSG);
    }

    // ==================== NEGATIVE SCENARIOS ====================

    @Test(priority = 2, description = "Verify login fails with invalid username")
    public void testInvalidUsername() {
        LoginPage loginPage = new LoginPage(driver);

        System.out.println("\n▶ Test 2: Invalid Username Test");
        System.out.println("Using invalid username: " + LoginTestData.INVALID_USERNAME);

        loginPage.enterUsername(LoginTestData.INVALID_USERNAME);
        loginPage.enterPassword(LoginTestData.VALID_PASSWORD);
        takeScreenshot(LoginTestData.SCREENSHOT_INVALID_USERNAME_BEFORE);

        loginPage.clickLogin();

        // Wait for error message to appear
        waitFor(LoginTestData.MEDIUM_WAIT);

        // Verify error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                LoginTestData.ASSERT_ERROR_MESSAGE);

        String errorMsg = loginPage.getErrorMessage();
        System.out.println("→ Error message displayed: " + errorMsg);
        Assert.assertTrue(errorMsg.contains("Invalid username or password"),
                "Expected error message about invalid credentials");

        // Verify still on login page
        Assert.assertTrue(loginPage.isOnLoginPage(),
                LoginTestData.ASSERT_LOGIN_PAGE);

        takeScreenshot(LoginTestData.SCREENSHOT_INVALID_USERNAME_AFTER);
        System.out.println(LoginTestData.INVALID_USERNAME_MSG);
    }

    @Test(priority = 3, description = "Verify login fails with invalid password")
    public void testInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);

        System.out.println("\n▶ Test 3: Invalid Password Test");

        loginPage.enterUsername(LoginTestData.VALID_USERNAME);
        loginPage.enterPassword(LoginTestData.INVALID_PASSWORD);
        takeScreenshot(LoginTestData.SCREENSHOT_INVALID_PASSWORD_BEFORE);

        loginPage.clickLogin();
        waitFor(LoginTestData.MEDIUM_WAIT);

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                LoginTestData.ASSERT_ERROR_MESSAGE);
        Assert.assertTrue(loginPage.isOnLoginPage(),
                LoginTestData.ASSERT_LOGIN_PAGE);

        takeScreenshot(LoginTestData.SCREENSHOT_INVALID_PASSWORD_AFTER);
        System.out.println(LoginTestData.INVALID_PASSWORD_MSG);
    }

    @Test(priority = 4, description = "Verify login fails with both fields empty")
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        System.out.println("\n▶ Test 4: Empty Credentials Test");

        takeScreenshot(LoginTestData.SCREENSHOT_EMPTY_FIELDS_BEFORE);
        loginPage.clickLogin();

        waitFor(LoginTestData.SHORT_WAIT);

        // Should remain on login page
        Assert.assertTrue(loginPage.isOnLoginPage(),
                LoginTestData.ASSERT_LOGIN_PAGE);

        takeScreenshot(LoginTestData.SCREENSHOT_EMPTY_FIELDS_AFTER);
        System.out.println(LoginTestData.EMPTY_FIELDS_MSG);
    }

    @Test(priority = 5, description = "Verify login fails with empty username")
    public void testEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver);

        System.out.println("\n▶ Test 5: Empty Username Test");

        loginPage.enterUsername(LoginTestData.EMPTY_STRING);
        loginPage.enterPassword(LoginTestData.VALID_PASSWORD);
        takeScreenshot(LoginTestData.SCREENSHOT_EMPTY_USERNAME_BEFORE);

        loginPage.clickLogin();
        waitFor(LoginTestData.SHORT_WAIT);

        Assert.assertTrue(loginPage.isOnLoginPage(),
                LoginTestData.ASSERT_LOGIN_PAGE);

        takeScreenshot(LoginTestData.SCREENSHOT_EMPTY_USERNAME_AFTER);
        System.out.println(LoginTestData.EMPTY_FIELDS_MSG);
    }

    @Test(priority = 6, description = "Verify login fails with empty password")
    public void testEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);

        System.out.println("\n▶ Test 6: Empty Password Test");

        loginPage.enterUsername(LoginTestData.VALID_USERNAME);
        loginPage.enterPassword(LoginTestData.EMPTY_STRING);
        takeScreenshot(LoginTestData.SCREENSHOT_EMPTY_PASSWORD_BEFORE);

        loginPage.clickLogin();
        waitFor(LoginTestData.SHORT_WAIT);

        Assert.assertTrue(loginPage.isOnLoginPage(),
                LoginTestData.ASSERT_LOGIN_PAGE);

        takeScreenshot(LoginTestData.SCREENSHOT_EMPTY_PASSWORD_AFTER);
        System.out.println(LoginTestData.EMPTY_FIELDS_MSG);
    }

    // ==================== SECURITY SCENARIOS ====================

    @Test(priority = 7, description = "Verify SQL injection prevention")
    public void testSQLInjection() {
        LoginPage loginPage = new LoginPage(driver);

        System.out.println("\n▶ Test 7: SQL Injection Prevention Test");
        System.out.println("Attempting SQL injection: " + LoginTestData.SQL_INJECTION_USERNAME);

        loginPage.enterUsername(LoginTestData.SQL_INJECTION_USERNAME);
        loginPage.enterPassword(LoginTestData.SQL_INJECTION_PASSWORD);
        takeScreenshot(LoginTestData.SCREENSHOT_SQL_INJECTION_BEFORE);

        loginPage.clickLogin();
        waitFor(LoginTestData.MEDIUM_WAIT);

        // Should not reach dashboard
        Assert.assertTrue(loginPage.isOnLoginPage(),
                LoginTestData.ASSERT_SQL_INJECTION_BLOCKED);

        takeScreenshot(LoginTestData.SCREENSHOT_SQL_INJECTION_AFTER);
        System.out.println(LoginTestData.SQL_INJECTION_MSG);
    }

    @Test(priority = 8, description = "Verify XSS attack prevention")
    public void testXSSAttack() {
        LoginPage loginPage = new LoginPage(driver);

        System.out.println("\n▶ Test 8: XSS Attack Prevention Test");
        System.out.println("Attempting XSS: " + LoginTestData.XSS_USERNAME);

        loginPage.enterUsername(LoginTestData.XSS_USERNAME);
        loginPage.enterPassword(LoginTestData.XSS_PASSWORD);
        takeScreenshot(LoginTestData.SCREENSHOT_XSS_ATTACK_BEFORE);

        loginPage.clickLogin();
        waitFor(LoginTestData.MEDIUM_WAIT);

        Assert.assertTrue(loginPage.isOnLoginPage(),
                LoginTestData.ASSERT_XSS_BLOCKED);

        takeScreenshot(LoginTestData.SCREENSHOT_XSS_ATTACK_AFTER);
        System.out.println(LoginTestData.XSS_ATTACK_MSG);
    }

    @Test(priority = 9, description = "Verify special characters handling")
    public void testSpecialCharacters() {
        LoginPage loginPage = new LoginPage(driver);

        System.out.println("\n▶ Test 9: Special Characters Test");
        System.out.println("Using special characters: " + LoginTestData.SPECIAL_CHARS_USERNAME);

        loginPage.enterUsername(LoginTestData.SPECIAL_CHARS_USERNAME);
        loginPage.enterPassword(LoginTestData.SPECIAL_CHARS_PASSWORD);
        takeScreenshot(LoginTestData.SCREENSHOT_SPECIAL_CHARS_BEFORE);

        loginPage.clickLogin();
        waitFor(LoginTestData.MEDIUM_WAIT);

        Assert.assertTrue(loginPage.isOnLoginPage(),
                LoginTestData.ASSERT_LOGIN_PAGE);

        takeScreenshot(LoginTestData.SCREENSHOT_SPECIAL_CHARS_AFTER);
        System.out.println(LoginTestData.SPECIAL_CHARS_MSG);
    }
}