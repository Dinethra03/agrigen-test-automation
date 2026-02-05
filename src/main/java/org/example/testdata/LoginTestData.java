package org.example.testdata;


/**
 * Centralized Test Data for AgriGEN Login Tests
 * Contains all test data used in LoginPageTest scenarios
 */
public class LoginTestData {

    // ==================== VALID CREDENTIALS ====================
    // TODO: Replace with your actual valid credentials
    public static final String VALID_USERNAME = "dinethra";
    public static final String VALID_PASSWORD = "12345";

    // ==================== INVALID CREDENTIALS ====================
    public static final String INVALID_USERNAME = "InvalidUser123";
    public static final String INVALID_PASSWORD = "WrongPassword999";

    // ==================== EMPTY CREDENTIALS ====================
    public static final String EMPTY_STRING = "";

    // ==================== SECURITY TEST DATA ====================
    public static final String SQL_INJECTION_USERNAME = "' OR '1'='1";
    public static final String SQL_INJECTION_PASSWORD = "' OR '1'='1";

    public static final String XSS_USERNAME = "<script>alert('XSS')</script>";
    public static final String XSS_PASSWORD = "<script>alert('XSS')</script>";

    public static final String SPECIAL_CHARS_USERNAME = "user@#$%^&*";
    public static final String SPECIAL_CHARS_PASSWORD = "pass!@#$%^&*()";

    // ==================== EXPECTED URLs ====================
    public static final String BASE_URL = "http://agri-qa.southeastasia.cloudapp.azure.com:5090";
    public static final String LOGIN_PAGE_URL = BASE_URL + "/signin";
    public static final String LOADER_URL = "/loader";
    public static final String DASHBOARD_URL = "/app/dashboard";

    // ==================== EXPECTED ERROR MESSAGES ====================
    public static final String EXPECTED_ERROR_MESSAGE = "Error: Invalid username or password !";

    // ==================== TEST MESSAGES ====================
    public static final String VALID_LOGIN_SUCCESS_MSG = "✓ Test PASSED: Valid login successful - Navigated to dashboard";
    public static final String INVALID_USERNAME_MSG = "✓ Test PASSED: Invalid username correctly rejected";
    public static final String INVALID_PASSWORD_MSG = "✓ Test PASSED: Invalid password correctly rejected";
    public static final String EMPTY_FIELDS_MSG = "✓ Test PASSED: Empty credentials correctly rejected";
    public static final String SQL_INJECTION_MSG = "✓ Test PASSED: SQL injection properly blocked";
    public static final String XSS_ATTACK_MSG = "✓ Test PASSED: XSS attack properly blocked";
    public static final String SPECIAL_CHARS_MSG = "✓ Test PASSED: Special characters handled correctly";

    // ==================== ASSERTION MESSAGES ====================
    public static final String ASSERT_DASHBOARD_URL = "Expected dashboard URL but got: ";
    public static final String ASSERT_LOGIN_PAGE = "Should remain on login page";
    public static final String ASSERT_ERROR_MESSAGE = "Expected error message not displayed";
    public static final String ASSERT_NO_DASHBOARD_ACCESS = "Should not reach dashboard";
    public static final String ASSERT_SQL_INJECTION_BLOCKED = "System should prevent SQL injection attempts";
    public static final String ASSERT_XSS_BLOCKED = "System should prevent XSS attacks";

    // ==================== SCREENSHOT NAMES ====================
    public static final String SCREENSHOT_VALID_LOGIN_BEFORE = "01_ValidLogin_BeforeSubmit";
    public static final String SCREENSHOT_VALID_LOGIN_LOADER = "01_ValidLogin_LoaderPage";
    public static final String SCREENSHOT_VALID_LOGIN_AFTER = "01_ValidLogin_Dashboard";

    public static final String SCREENSHOT_INVALID_USERNAME_BEFORE = "02_InvalidUsername_BeforeSubmit";
    public static final String SCREENSHOT_INVALID_USERNAME_AFTER = "02_InvalidUsername_Error";

    public static final String SCREENSHOT_INVALID_PASSWORD_BEFORE = "03_InvalidPassword_BeforeSubmit";
    public static final String SCREENSHOT_INVALID_PASSWORD_AFTER = "03_InvalidPassword_Error";

    public static final String SCREENSHOT_EMPTY_FIELDS_BEFORE = "04_EmptyFields_BeforeSubmit";
    public static final String SCREENSHOT_EMPTY_FIELDS_AFTER = "04_EmptyFields_Error";

    public static final String SCREENSHOT_EMPTY_USERNAME_BEFORE = "05_EmptyUsername_BeforeSubmit";
    public static final String SCREENSHOT_EMPTY_USERNAME_AFTER = "05_EmptyUsername_Error";

    public static final String SCREENSHOT_EMPTY_PASSWORD_BEFORE = "06_EmptyPassword_BeforeSubmit";
    public static final String SCREENSHOT_EMPTY_PASSWORD_AFTER = "06_EmptyPassword_Error";

    public static final String SCREENSHOT_SQL_INJECTION_BEFORE = "07_SQLInjection_BeforeSubmit";
    public static final String SCREENSHOT_SQL_INJECTION_AFTER = "07_SQLInjection_Blocked";

    public static final String SCREENSHOT_XSS_ATTACK_BEFORE = "08_XSSAttack_BeforeSubmit";
    public static final String SCREENSHOT_XSS_ATTACK_AFTER = "08_XSSAttack_Blocked";

    public static final String SCREENSHOT_SPECIAL_CHARS_BEFORE = "09_SpecialChars_BeforeSubmit";
    public static final String SCREENSHOT_SPECIAL_CHARS_AFTER = "09_SpecialChars_Error";

    // ==================== WAIT TIMES (in milliseconds) ====================
    // Reduced waits to speed up tests; keep them reasonable for typical UI responses
    public static final int SHORT_WAIT = 500;    // 0.5 seconds
    public static final int MEDIUM_WAIT = 1000;   // 1 second
    public static final int LONG_WAIT = 2000;     // 2 seconds
    public static final int LOADER_WAIT = 5000;  // 5 seconds for loader transition

    // ==================== EXPECTED USER INFO ====================
    public static final String EXPECTED_USER_NAME = "Dinethra R";
    public static final String EXPECTED_SHORT_NAME = "Dine";
}
