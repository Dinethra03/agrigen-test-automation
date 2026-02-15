package org.example.testdata;

/**
 * Centralized Test Data for User Management Tests
 */
public class UserManagementTestData {

    // ==================== BASE URL ====================
    public static final String BASE_URL = "http://agri-qa.southeastasia.cloudapp.azure.com:5090";
    public static final String USER_MANAGEMENT_URL = "/app/user-management/user";
    public static final String ADD_USER_URL = "/app/user-management/user/add";

    // ==================== VALID TEST DATA ====================
    public static final String VALID_GROUP = "Tea Test";
    public static final String VALID_ESTATE = "TestA Factory";

    // User details for adding new user
    public static final String NEW_FIRST_NAME = "Test";
    public static final String NEW_LAST_NAME = "User";
    public static final String NEW_USERNAME = "testuser" + System.currentTimeMillis(); // Unique username
    public static final String NEW_PASSWORD = "Test@123";
    public static final String NEW_CONFIRM_PASSWORD = "Test@123";
    public static final String NEW_ROLE = "Admin"; // Change based on available roles
    public static final boolean NEW_USER_ACTIVE = true;
    public static final boolean NEW_USER_LOCKED = false;

    // Existing user details (from screenshot)
    public static final String EXISTING_USERNAME = "dinethra";
    public static final String EXISTING_ROLE_NAME = "Dine";
    public static final String EXISTING_FIRST_NAME = "Dinethra";
    public static final String EXISTING_LAST_NAME = "R";
    public static final String EXISTING_STATUS = "Active";

    // ==================== INVALID TEST DATA ====================
    public static final String INVALID_USERNAME = "invalid_user_12345";
    public static final String INVALID_PASSWORD = "weak";
    public static final String MISMATCHED_PASSWORD = "Different@123";

    // Special characters for testing
    public static final String SPECIAL_CHAR_USERNAME = "user@#$%";
    public static final String SPECIAL_CHAR_FIRSTNAME = "Test<>User";
    public static final String SQL_INJECTION_USERNAME = "' OR '1'='1";
    public static final String XSS_FIRSTNAME = "<script>alert('XSS')</script>";

    // ==================== EMPTY/NULL DATA ====================
    public static final String EMPTY_STRING = "";
    public static final String WHITESPACE_STRING = "   ";

    // ==================== EXPECTED MESSAGES ====================
    public static final String SUCCESS_MESSAGE_USER_ADDED = "User added successfully";
    public static final String SUCCESS_MESSAGE_USER_UPDATED = "User updated successfully";
    public static final String SUCCESS_MESSAGE_USER_DELETED = "User deleted successfully";

    public static final String ERROR_MESSAGE_REQUIRED_FIELD = "This field is required";
    public static final String ERROR_MESSAGE_PASSWORD_MISMATCH = "Passwords do not match";
    public static final String ERROR_MESSAGE_INVALID_USERNAME = "Invalid username format";
    public static final String ERROR_MESSAGE_USERNAME_EXISTS = "Username already exists";
    public static final String ERROR_MESSAGE_WEAK_PASSWORD = "Password must be stronger";

    // ==================== DROPDOWN OPTIONS ====================
    public static final String[] AVAILABLE_GROUPS = {"Tea Test", "Group 2", "Group 3"};
    public static final String[] AVAILABLE_ESTATES = {"TestA Factory", "Estate 2", "Estate 3"};
    public static final String[] AVAILABLE_ROLES = {"Admin", "Manager", "User", "Viewer"};
    public static final String DEFAULT_ROLE_OPTION = "--Select Role--";

    // ==================== TABLE HEADERS ====================
    public static final String HEADER_USERNAME = "Username";
    public static final String HEADER_ROLE_NAME = "Role Name";
    public static final String HEADER_FIRST_NAME = "First Name";
    public static final String HEADER_LAST_NAME = "Last Name";
    public static final String HEADER_STATUS = "Status";
    public static final String HEADER_ACTIONS = "Actions";

    // ==================== TEST MESSAGES ====================
    public static final String TEST_PASS_USER_LIST_DISPLAYED = "✓ Test PASSED: User list displayed successfully";
    public static final String TEST_PASS_USER_ADDED = "✓ Test PASSED: User added successfully";
    public static final String TEST_PASS_USER_EDITED = "✓ Test PASSED: User edited successfully";
    public static final String TEST_PASS_USER_DELETED = "✓ Test PASSED: User deleted successfully";
    public static final String TEST_PASS_SEARCH_WORKS = "✓ Test PASSED: Search functionality works correctly";
    public static final String TEST_PASS_VALIDATION_WORKS = "✓ Test PASSED: Form validation works correctly";
    public static final String TEST_PASS_NAVIGATION = "✓ Test PASSED: Navigation successful";

    // ==================== ASSERTION MESSAGES ====================
    public static final String ASSERT_USER_PAGE_DISPLAYED = "User Management page should be displayed";
    public static final String ASSERT_ADD_USER_PAGE_DISPLAYED = "Add User page should be displayed";
    public static final String ASSERT_TABLE_HEADERS_VISIBLE = "All table headers should be visible";
    public static final String ASSERT_USER_EXISTS = "User should exist in the table";
    public static final String ASSERT_USER_NOT_EXISTS = "User should not exist in the table";
    public static final String ASSERT_SUCCESS_MESSAGE = "Success message should be displayed";
    public static final String ASSERT_ERROR_MESSAGE = "Error message should be displayed";
    public static final String ASSERT_REQUIRED_FIELD_ERROR = "Required field error should be displayed";
    public static final String ASSERT_PASSWORD_MISMATCH = "Password mismatch error should be displayed";
    public static final String ASSERT_SAVE_BUTTON_ENABLED = "Save button should be enabled";
    public static final String ASSERT_SAVE_BUTTON_DISABLED = "Save button should be disabled";

    // ==================== SCREENSHOT NAMES ====================
    public static final String SCREENSHOT_USER_LIST = "01_UserManagement_UserList";
    public static final String SCREENSHOT_ADD_USER_PAGE = "02_AddUser_FormEmpty";
    public static final String SCREENSHOT_ADD_USER_FILLED = "03_AddUser_FormFilled";
    public static final String SCREENSHOT_ADD_USER_SUCCESS = "04_AddUser_Success";
    public static final String SCREENSHOT_EDIT_USER = "05_EditUser_Form";
    public static final String SCREENSHOT_DELETE_USER = "06_DeleteUser_Confirmation";
    public static final String SCREENSHOT_SEARCH_USER = "07_SearchUser_Results";
    public static final String SCREENSHOT_VALIDATION_ERROR = "08_Validation_Error";
    public static final String SCREENSHOT_REQUIRED_FIELDS = "09_RequiredFields_Error";
    public static final String SCREENSHOT_PASSWORD_MISMATCH = "10_PasswordMismatch_Error";

    // ==================== WAIT TIMES (in milliseconds) ====================
    public static final int SHORT_WAIT = 500;
    public static final int MEDIUM_WAIT = 1000;
    public static final int LONG_WAIT = 2000;
    public static final int SAVE_OPERATION_WAIT = 3000;
    public static final int PAGE_LOAD_WAIT = 2000;

    // ==================== PAGINATION ====================
    public static final int DEFAULT_ROWS_PER_PAGE = 5;
    public static final int[] ROWS_PER_PAGE_OPTIONS = {5, 10, 25, 50};

    // ==================== FILTER/SEARCH DATA ====================
    public static final String SEARCH_EXISTING_USER = "dinethra";
    public static final String SEARCH_NON_EXISTING = "nonexistentuser123";
    public static final String SEARCH_PARTIAL_NAME = "dine";

    // ==================== PASSWORD REQUIREMENTS ====================
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 20;
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,}$";

    // ==================== USERNAME REQUIREMENTS ====================
    public static final int MIN_USERNAME_LENGTH = 3;
    public static final int MAX_USERNAME_LENGTH = 50;
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9_]+$";

    // ==================== STATUS VALUES ====================
    public static final String STATUS_ACTIVE = "Active";
    public static final String STATUS_INACTIVE = "Inactive";

    // ==================== EDIT USER TEST DATA ====================
    public static final String EDITED_FIRST_NAME = "EditedTest";
    public static final String EDITED_LAST_NAME = "EditedUser";
    public static final String EDITED_ROLE = "Manager";

    // ==================== BULK TEST DATA ====================
    // For testing multiple user creation
    public static final String[][] BULK_USERS = {
            {"User1", "Test1", "user1test", "Pass@123", "Admin", "true", "false"},
            {"User2", "Test2", "user2test", "Pass@123", "Manager", "true", "false"},
            {"User3", "Test3", "user3test", "Pass@123", "User", "true", "false"}
    };
}