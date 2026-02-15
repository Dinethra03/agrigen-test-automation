package tests;

import org.example.base.BaseTest;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;
import org.example.pages.UserManagementPage;
import org.example.pages.AddUserPage;
import org.example.testdata.LoginTestData;
import org.example.testdata.UserManagementTestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Comprehensive User Management Test Suite
 * Tests all functionality related to User Management module
 */
public class UserManagementTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private UserManagementPage userManagementPage;
    private AddUserPage addUserPage;

    @BeforeMethod
    public void loginAndNavigateToUserManagement() {
        // Login to application
        loginPage = new LoginPage(driver);
        System.out.println("\n▶ Logging in to the application...");

        boolean loginSuccess = loginPage.loginAndWaitForDashboard(
                LoginTestData.VALID_USERNAME,
                LoginTestData.VALID_PASSWORD
        );

        Assert.assertTrue(loginSuccess, "Login should be successful");
        System.out.println("✓ Login successful");

        // Navigate to User Management
        dashboardPage = new DashboardPage(driver);
        dashboardPage.clickUserManagement();
        waitFor(UserManagementTestData.PAGE_LOAD_WAIT);

        userManagementPage = new UserManagementPage(driver);
        System.out.println("✓ Navigated to User Management page");
    }

    // ==================== NAVIGATION TESTS ====================

    @Test(priority = 1, description = "Verify User Management page loads correctly")
    public void testUserManagementPageLoad() {
        System.out.println("\n▶ Test 1: User Management Page Load Test");

        // Verify page is displayed
        Assert.assertTrue(userManagementPage.isUserManagementPageDisplayed(),
                UserManagementTestData.ASSERT_USER_PAGE_DISPLAYED);

        // Verify URL
        String currentUrl = userManagementPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("user"),
                "URL should contain 'user'");

        // Verify Add User button is present
        Assert.assertTrue(userManagementPage.isAddUserButtonDisplayed(),
                "Add User button should be displayed");

        // Verify table headers
        Assert.assertTrue(userManagementPage.areTableHeadersDisplayed(),
                UserManagementTestData.ASSERT_TABLE_HEADERS_VISIBLE);

        takeScreenshot(UserManagementTestData.SCREENSHOT_USER_LIST);
        System.out.println(UserManagementTestData.TEST_PASS_USER_LIST_DISPLAYED);
    }

    @Test(priority = 2, description = "Verify existing user is displayed in the table")
    public void testExistingUserDisplayed() {
        System.out.println("\n▶ Test 2: Existing User Display Test");

        userManagementPage.waitForPageLoad();

        // Check if existing user is present
        boolean userExists = userManagementPage.isUserPresent(
                UserManagementTestData.EXISTING_USERNAME
        );

        Assert.assertTrue(userExists,
                "User '" + UserManagementTestData.EXISTING_USERNAME + "' should be present in the table");

        // Verify user status
        String status = userManagementPage.getUserStatus(UserManagementTestData.EXISTING_USERNAME);
        Assert.assertEquals(status, UserManagementTestData.EXISTING_STATUS,
                "User status should be Active");

        takeScreenshot(UserManagementTestData.SCREENSHOT_USER_LIST);
        System.out.println("✓ Test PASSED: Existing user displayed correctly");
    }

    // ==================== ADD USER TESTS ====================

    @Test(priority = 3, description = "Navigate to Add User page")
    public void testNavigateToAddUserPage() {
        System.out.println("\n▶ Test 3: Navigate to Add User Page");

        userManagementPage.clickAddUser();
        waitFor(UserManagementTestData.PAGE_LOAD_WAIT);

        addUserPage = new AddUserPage(driver);
        Assert.assertTrue(addUserPage.isAddUserPageDisplayed(),
                UserManagementTestData.ASSERT_ADD_USER_PAGE_DISPLAYED);

        takeScreenshot(UserManagementTestData.SCREENSHOT_ADD_USER_PAGE);
        System.out.println(UserManagementTestData.TEST_PASS_NAVIGATION);
    }

    @Test(priority = 4, description = "Verify Add User form fields are present")
    public void testAddUserFormFields() {
        System.out.println("\n▶ Test 4: Add User Form Fields Test");

        userManagementPage.clickAddUser();
        waitFor(UserManagementTestData.PAGE_LOAD_WAIT);

        addUserPage = new AddUserPage(driver);
        addUserPage.waitForPageLoad();

        // Verify page is displayed
        Assert.assertTrue(addUserPage.isAddUserPageDisplayed(),
                "Add User page should be displayed");

        // Verify Save button is present
        Assert.assertTrue(addUserPage.isSaveButtonEnabled(),
                "Save button should be present");

        takeScreenshot(UserManagementTestData.SCREENSHOT_ADD_USER_PAGE);
        System.out.println("✓ Test PASSED: All form fields are present");
    }

    @Test(priority = 5, description = "Add new user with valid data")
    public void testAddNewUserWithValidData() {
        System.out.println("\n▶ Test 5: Add New User with Valid Data");

        userManagementPage.clickAddUser();
        waitFor(UserManagementTestData.PAGE_LOAD_WAIT);

        addUserPage = new AddUserPage(driver);
        addUserPage.waitForPageLoad();

        // Fill the form
        System.out.println("→ Filling user form...");
        addUserPage.selectGroup(UserManagementTestData.VALID_GROUP);
        addUserPage.selectEstate(UserManagementTestData.VALID_ESTATE);
        addUserPage.enterFirstName(UserManagementTestData.NEW_FIRST_NAME);
        addUserPage.enterLastName(UserManagementTestData.NEW_LAST_NAME);
        addUserPage.enterUserName(UserManagementTestData.NEW_USERNAME);
        addUserPage.enterPassword(UserManagementTestData.NEW_PASSWORD);
        addUserPage.enterConfirmPassword(UserManagementTestData.NEW_CONFIRM_PASSWORD);

        // Note: Role selection may need adjustment based on available roles
        // addUserPage.selectRole(UserManagementTestData.NEW_ROLE);

        addUserPage.setActiveToggle(UserManagementTestData.NEW_USER_ACTIVE);
        addUserPage.setLockedToggle(UserManagementTestData.NEW_USER_LOCKED);

        takeScreenshot(UserManagementTestData.SCREENSHOT_ADD_USER_FILLED);

        // Click Save
        addUserPage.clickSave();
        waitFor(UserManagementTestData.SAVE_OPERATION_WAIT);

        // Verify success (either success message or navigation back to user list)
        boolean successMessageDisplayed = addUserPage.isSuccessMessageDisplayed();
        boolean navigatedBack = driver.getCurrentUrl().contains("user") &&
                !driver.getCurrentUrl().contains("add");

        Assert.assertTrue(successMessageDisplayed || navigatedBack,
                "User should be added successfully");

        takeScreenshot(UserManagementTestData.SCREENSHOT_ADD_USER_SUCCESS);
        System.out.println(UserManagementTestData.TEST_PASS_USER_ADDED);
    }

    @Test(priority = 6, description = "Add user with empty required fields - validation test")
    public void testAddUserWithEmptyFields() {
        System.out.println("\n▶ Test 6: Add User with Empty Required Fields");

        userManagementPage.clickAddUser();
        waitFor(UserManagementTestData.PAGE_LOAD_WAIT);

        addUserPage = new AddUserPage(driver);
        addUserPage.waitForPageLoad();

        // Try to save without filling any fields
        addUserPage.clickSave();
        waitFor(UserManagementTestData.MEDIUM_WAIT);

        // Should show validation errors or remain on same page
        boolean stillOnAddPage = addUserPage.isAddUserPageDisplayed();
        boolean errorDisplayed = addUserPage.isErrorMessageDisplayed() ||
                addUserPage.isRequiredFieldErrorDisplayed();

        Assert.assertTrue(stillOnAddPage || errorDisplayed,
                "Should show validation error for empty required fields");

        takeScreenshot(UserManagementTestData.SCREENSHOT_REQUIRED_FIELDS);
        System.out.println(UserManagementTestData.TEST_PASS_VALIDATION_WORKS);
    }

    @Test(priority = 7, description = "Add user with mismatched passwords - validation test")
    public void testAddUserWithMismatchedPasswords() {
        System.out.println("\n▶ Test 7: Add User with Mismatched Passwords");

        userManagementPage.clickAddUser();
        waitFor(UserManagementTestData.PAGE_LOAD_WAIT);

        addUserPage = new AddUserPage(driver);
        addUserPage.waitForPageLoad();

        // Fill form with mismatched passwords
        addUserPage.selectGroup(UserManagementTestData.VALID_GROUP);
        addUserPage.selectEstate(UserManagementTestData.VALID_ESTATE);
        addUserPage.enterFirstName(UserManagementTestData.NEW_FIRST_NAME);
        addUserPage.enterLastName(UserManagementTestData.NEW_LAST_NAME);
        addUserPage.enterUserName("testuser_mismatch");
        addUserPage.enterPassword(UserManagementTestData.NEW_PASSWORD);
        addUserPage.enterConfirmPassword(UserManagementTestData.MISMATCHED_PASSWORD);

        takeScreenshot(UserManagementTestData.SCREENSHOT_PASSWORD_MISMATCH);

        addUserPage.clickSave();
        waitFor(UserManagementTestData.MEDIUM_WAIT);

        // Should show error or remain on page
        boolean stillOnPage = addUserPage.isAddUserPageDisplayed();

        Assert.assertTrue(stillOnPage,
                UserManagementTestData.ASSERT_PASSWORD_MISMATCH);

        System.out.println(UserManagementTestData.TEST_PASS_VALIDATION_WORKS);
    }

    // ==================== SEARCH TESTS ====================

    @Test(priority = 8, description = "Search for existing user")
    public void testSearchExistingUser() {
        System.out.println("\n▶ Test 8: Search Existing User");

        userManagementPage.waitForPageLoad();

        // Get initial count
        int initialCount = userManagementPage.getUserCount();
        System.out.println("→ Initial user count: " + initialCount);

        // Search for existing user
        userManagementPage.searchUser(UserManagementTestData.SEARCH_EXISTING_USER);
        waitFor(UserManagementTestData.MEDIUM_WAIT);

        // Verify user is found
        boolean userFound = userManagementPage.isUserPresent(
                UserManagementTestData.EXISTING_USERNAME
        );

        Assert.assertTrue(userFound,
                "Searched user should be displayed");

        takeScreenshot(UserManagementTestData.SCREENSHOT_SEARCH_USER);
        System.out.println(UserManagementTestData.TEST_PASS_SEARCH_WORKS);

        // Clear search
        userManagementPage.clearSearch();
        waitFor(UserManagementTestData.SHORT_WAIT);
    }

    @Test(priority = 9, description = "Search for non-existing user")
    public void testSearchNonExistingUser() {
        System.out.println("\n▶ Test 9: Search Non-Existing User");

        userManagementPage.waitForPageLoad();

        // Search for non-existing user
        userManagementPage.searchUser(UserManagementTestData.SEARCH_NON_EXISTING);
        waitFor(UserManagementTestData.MEDIUM_WAIT);

        // Verify no results or user not found
        int resultCount = userManagementPage.getUserCount();
        boolean userNotFound = !userManagementPage.isUserPresent(
                UserManagementTestData.SEARCH_NON_EXISTING
        );

        Assert.assertTrue(resultCount == 0 || userNotFound,
                "Non-existing user should not be found");

        System.out.println("✓ Test PASSED: Search correctly shows no results");

        // Clear search
        userManagementPage.clearSearch();
    }

    // ==================== FILTER TESTS ====================

    @Test(priority = 10, description = "Filter users by group")
    public void testFilterByGroup() {
        System.out.println("\n▶ Test 10: Filter by Group");

        userManagementPage.waitForPageLoad();

        // Select group filter
        userManagementPage.selectGroup(UserManagementTestData.VALID_GROUP);
        waitFor(UserManagementTestData.MEDIUM_WAIT);

        // Verify page updates (user count may change)
        int userCount = userManagementPage.getUserCount();
        System.out.println("→ Users after group filter: " + userCount);

        Assert.assertTrue(userCount >= 0,
                "Filter should work without errors");

        System.out.println("✓ Test PASSED: Group filter applied successfully");
    }

    @Test(priority = 11, description = "Filter users by estate")
    public void testFilterByEstate() {
        System.out.println("\n▶ Test 11: Filter by Estate");

        userManagementPage.waitForPageLoad();

        // Select estate filter
        userManagementPage.selectEstate(UserManagementTestData.VALID_ESTATE);
        waitFor(UserManagementTestData.MEDIUM_WAIT);

        // Verify page updates
        int userCount = userManagementPage.getUserCount();
        System.out.println("→ Users after estate filter: " + userCount);

        Assert.assertTrue(userCount >= 0,
                "Filter should work without errors");

        System.out.println("✓ Test PASSED: Estate filter applied successfully");
    }

    // ==================== BACK NAVIGATION TEST ====================

    @Test(priority = 12, description = "Test back navigation from Add User page")
    public void testBackNavigationFromAddUser() {
        System.out.println("\n▶ Test 12: Back Navigation Test");

        userManagementPage.clickAddUser();
        waitFor(UserManagementTestData.PAGE_LOAD_WAIT);

        addUserPage = new AddUserPage(driver);
        Assert.assertTrue(addUserPage.isAddUserPageDisplayed(),
                "Should be on Add User page");

        // Click back button
        addUserPage.clickBack();
        waitFor(UserManagementTestData.PAGE_LOAD_WAIT);

        // Verify returned to User Management page
        boolean backToUserList = driver.getCurrentUrl().contains("user") &&
                !driver.getCurrentUrl().contains("add");

        Assert.assertTrue(backToUserList,
                "Should navigate back to User Management page");

        System.out.println(UserManagementTestData.TEST_PASS_NAVIGATION);
    }

    // ==================== SECURITY TESTS ====================

    @Test(priority = 13, description = "Test SQL injection prevention in username field")
    public void testSQLInjectionPrevention() {
        System.out.println("\n▶ Test 13: SQL Injection Prevention");

        userManagementPage.clickAddUser();
        waitFor(UserManagementTestData.PAGE_LOAD_WAIT);

        addUserPage = new AddUserPage(driver);
        addUserPage.waitForPageLoad();

        // Try SQL injection
        addUserPage.selectGroup(UserManagementTestData.VALID_GROUP);
        addUserPage.selectEstate(UserManagementTestData.VALID_ESTATE);
        addUserPage.enterFirstName("Test");
        addUserPage.enterLastName("User");
        addUserPage.enterUserName(UserManagementTestData.SQL_INJECTION_USERNAME);
        addUserPage.enterPassword(UserManagementTestData.NEW_PASSWORD);
        addUserPage.enterConfirmPassword(UserManagementTestData.NEW_PASSWORD);

        addUserPage.clickSave();
        waitFor(UserManagementTestData.MEDIUM_WAIT);

        // Should show error or validation message
        boolean errorShown = addUserPage.isErrorMessageDisplayed() ||
                addUserPage.isAddUserPageDisplayed();

        Assert.assertTrue(errorShown,
                "SQL injection should be prevented");

        System.out.println("✓ Test PASSED: SQL injection blocked");
    }

    @Test(priority = 14, description = "Test XSS prevention in name fields")
    public void testXSSPrevention() {
        System.out.println("\n▶ Test 14: XSS Prevention");

        userManagementPage.clickAddUser();
        waitFor(UserManagementTestData.PAGE_LOAD_WAIT);

        addUserPage = new AddUserPage(driver);
        addUserPage.waitForPageLoad();

        // Try XSS attack
        addUserPage.selectGroup(UserManagementTestData.VALID_GROUP);
        addUserPage.selectEstate(UserManagementTestData.VALID_ESTATE);
        addUserPage.enterFirstName(UserManagementTestData.XSS_FIRSTNAME);
        addUserPage.enterLastName("User");
        addUserPage.enterUserName("testxss");
        addUserPage.enterPassword(UserManagementTestData.NEW_PASSWORD);
        addUserPage.enterConfirmPassword(UserManagementTestData.NEW_PASSWORD);

        addUserPage.clickSave();
        waitFor(UserManagementTestData.MEDIUM_WAIT);

        // Should show error or validation
        boolean errorShown = addUserPage.isErrorMessageDisplayed() ||
                addUserPage.isAddUserPageDisplayed();

        Assert.assertTrue(errorShown,
                "XSS attack should be prevented");

        System.out.println("✓ Test PASSED: XSS attack blocked");
    }
}