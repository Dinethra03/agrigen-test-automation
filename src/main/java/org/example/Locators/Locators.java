package org.example.Locators;

import org.openqa.selenium.By;

/**
 * Centralized repository for all locators in AgriGEN application
 * This makes maintenance easier - update locators in one place
 */
public class Locators {

    // ==================== LOGIN PAGE LOCATORS ====================

    public static class LoginPage {
        // Input fields - Using placeholder text as fallback
        public static final By USERNAME_FIELD = By.xpath("//input[@placeholder='Username']");
        public static final By PASSWORD_FIELD = By.xpath("//input[@type='password']");

        // Alternative locators using label text
        public static final By USERNAME_BY_LABEL = By.xpath("//label[text()='Username']/following-sibling::input");
        public static final By PASSWORD_BY_LABEL = By.xpath("//label[text()='Password']/following-sibling::input");

        // Login button
        public static final By LOGIN_BUTTON = By.xpath("//button[.//span[normalize-space()='Sign in now']]");

        // Alternative button locator
        public static final By LOGIN_BUTTON_ALT = By.xpath("//button[contains(normalize-space(),'SIGN IN')]");

        // Error messages
        public static final By ERROR_MESSAGE = By.xpath("//div[contains(@class,'MuiAlert-message') and contains(.,'Invalid username or password')]");
        public static final By ERROR_CONTAINER = By.xpath("//*[contains(@style,'background') and contains(text(),'Error')]");
        public static final By ANY_ERROR = By.xpath("//*[contains(text(),'Error') or contains(text(),'error')]");
    }

    // ==================== LOADER PAGE LOCATORS ====================

    public static class LoaderPage {
        // Loader animation/image
        public static final By LOADER_IMAGE = By.xpath("//img[contains(@src,'loader') or contains(@alt,'loading')]");
        public static final By LOADER_CONTAINER = By.xpath("//*[contains(@class,'loader')]");
    }

    // ==================== DASHBOARD PAGE LOCATORS ====================

    public static class DashboardPage {
        // Logo
        public static final By AGRIGEN_LOGO = By.xpath("//img[contains(@src,'AgriGEN.png')]");

        // User profile
        public static final By USER_NAME = By.xpath("//h6[contains(@class,'MuiTypography-h6') and contains(@class,'MuiTypography-colorPrimary') and text()='Dinethra R']");
        public static final By USER_SHORT_NAME = By.xpath("//*[contains(text(),'Dine')]");
        public static final By USER_AVATAR = By.xpath("//img[@alt='user-avatar'] | //*[contains(@class,'avatar')]");

        // Left sidebar menu items
        public static final By GENERAL_MENU = By.xpath("//*[contains(text(),'General')]");
        public static final By OPERATION_ENTITY = By.xpath("//*[contains(text(),'Operation Entity Management')]");
        public static final By ESTATE_MANAGEMENT = By.xpath("//*[contains(text(),'Estate Management')]");
        public static final By USER_MANAGEMENT = By.xpath("//*[contains(text(),'User Management')]");
        public static final By CUSTOMER_MANAGEMENT = By.xpath("//*[contains(text(),'Customer Management')]");
        public static final By EMPLOYEE_MANAGEMENT = By.xpath("//*[contains(text(),'Employee Management')]");
        public static final By MASTER_MENU = By.xpath("//*[contains(text(),'Master')]");
        public static final By STATUTORY_MENU = By.xpath("//*[contains(text(),'Statutory')]");
        public static final By LEAVE_MANAGEMENT = By.xpath("//*[contains(text(),'Leave Management')]");

        // Top navigation icons
        public static final By NOTIFICATION_ICON = By.xpath("//*[contains(@class,'notification') or @title='Notifications']");
        public static final By SETTINGS_ICON = By.xpath("//*[contains(@class,'settings') or @title='Settings']");
    }

    // ==================== USER MANAGEMENT PAGE LOCATORS ====================

    public static class UserManagementPage {
        // Page title
        public static final By PAGE_TITLE = By.xpath("//h4[text()='User'] | //*[contains(@class,'MuiTypography') and text()='User']");

        // User menu in sidebar
        public static final By USER_MENU = By.xpath("//span[text()='User']");
        public static final By USER_MANAGEMENT_MENU = By.xpath("//span[text()='User Management']");

        // Filters section
        public static final By GROUP_DROPDOWN = By.xpath("//label[contains(text(),'Group')]/following-sibling::div//input | //div[contains(@class,'MuiSelect-select') and text()='Tea Test']");
        public static final By ESTATE_DROPDOWN = By.xpath("//label[contains(text(),'Estate')]/following-sibling::div//input | //div[contains(@class,'MuiSelect-select') and text()='TestA Factory']");

        // Add User button
        public static final By ADD_USER_BUTTON = By.xpath("//button[contains(@class,'MuiButton') and .//span[contains(text(),'+')]] | //button[@title='Add User']");

        // Search box
        public static final By SEARCH_BOX = By.xpath("//input[@placeholder='Search'] | //input[@type='search']");
        public static final By SEARCH_ICON = By.xpath("//*[contains(@class,'search')]");
        public static final By CLEAR_SEARCH = By.xpath("//button[@aria-label='Clear'] | //*[contains(@class,'clear')]");

        // Table headers
        public static final By USERNAME_HEADER = By.xpath("//th[text()='Username'] | //*[contains(@class,'MuiTableCell-head') and text()='Username']");
        public static final By ROLE_NAME_HEADER = By.xpath("//th[text()='Role Name'] | //*[contains(@class,'MuiTableCell-head') and text()='Role Name']");
        public static final By FIRST_NAME_HEADER = By.xpath("//th[text()='First Name'] | //*[contains(@class,'MuiTableCell-head') and text()='First Name']");
        public static final By LAST_NAME_HEADER = By.xpath("//th[text()='Last Name'] | //*[contains(@class,'MuiTableCell-head') and text()='Last Name']");
        public static final By STATUS_HEADER = By.xpath("//th[text()='Status'] | //*[contains(@class,'MuiTableCell-head') and text()='Status']");
        public static final By ACTIONS_HEADER = By.xpath("//th[text()='Actions'] | //*[contains(@class,'MuiTableCell-head') and text()='Actions']");

        // Table rows
        public static final By TABLE_ROWS = By.xpath("//tbody/tr");
        public static final By FIRST_ROW = By.xpath("//tbody/tr[1]");

        // Table data for specific user (dinethra)
        public static final By USERNAME_CELL = By.xpath("//td[text()='dinethra']");
        public static final By ROLE_NAME_CELL = By.xpath("//td[text()='Dine']");
        public static final By FIRST_NAME_CELL = By.xpath("//td[text()='Dinethra']");
        public static final By LAST_NAME_CELL = By.xpath("//td[text()='R']");
        public static final By STATUS_ACTIVE = By.xpath("//td[text()='Active']");

        // Action buttons in table
        public static final By EDIT_BUTTON = By.xpath("//button[@title='Edit'] | //*[contains(@class,'edit')]");
        public static final By DELETE_BUTTON = By.xpath("//button[@title='Delete'] | //*[contains(@class,'delete')]");

        // Pagination
        public static final By ROWS_PER_PAGE = By.xpath("//div[contains(text(),'rows')] | //*[contains(@class,'MuiTablePagination-select')]");
        public static final By PAGINATION_INFO = By.xpath("//p[contains(text(),'1-1 of 1')]");
        public static final By FIRST_PAGE_BUTTON = By.xpath("//button[@aria-label='Go to first page']");
        public static final By PREVIOUS_PAGE_BUTTON = By.xpath("//button[@aria-label='Go to previous page']");
        public static final By NEXT_PAGE_BUTTON = By.xpath("//button[@aria-label='Go to next page']");
        public static final By LAST_PAGE_BUTTON = By.xpath("//button[@aria-label='Go to last page']");
    }

    // ==================== ADD USER PAGE LOCATORS ====================

    public static class AddUserPage {
        // Page title
        public static final By PAGE_TITLE = By.xpath("//h4[text()='Add User'] | //*[contains(@class,'MuiTypography') and text()='Add User']");

        // Back button
        public static final By BACK_BUTTON = By.xpath("//button[contains(@class,'MuiButton') and .//span[contains(text(),'←')]] | //button[@title='Back']");

        // Form fields - Group
        public static final By GROUP_LABEL = By.xpath("//label[text()='Group *']");
        public static final By GROUP_DROPDOWN = By.xpath("//label[contains(text(),'Group')]/following-sibling::div//div[contains(@class,'MuiSelect-select')]");
        public static final By GROUP_INPUT = By.xpath("//input[@name='group'] | //div[@role='button' and .//div[contains(text(),'Tea Test')]]");

        // Form fields - Estate
        public static final By ESTATE_LABEL = By.xpath("//label[text()='Estate *']");
        public static final By ESTATE_DROPDOWN = By.xpath("//label[contains(text(),'Estate')]/following-sibling::div//div[contains(@class,'MuiSelect-select')]");
        public static final By ESTATE_INPUT = By.xpath("//input[@name='estate'] | //div[@role='button' and .//div[contains(text(),'TestA Factory')]]");

        // Form fields - First Name
        public static final By FIRST_NAME_LABEL = By.xpath("//label[text()='First Name *']");
        public static final By FIRST_NAME_INPUT = By.xpath("//label[contains(text(),'First Name')]/following-sibling::div//input | //input[@name='firstName']");

        // Form fields - Last Name
        public static final By LAST_NAME_LABEL = By.xpath("//label[text()='Last Name *']");
        public static final By LAST_NAME_INPUT = By.xpath("//label[contains(text(),'Last Name')]/following-sibling::div//input | //input[@name='lastName']");

        // Form fields - User Name
        public static final By USER_NAME_LABEL = By.xpath("//label[text()='User Name *']");
        public static final By USER_NAME_INPUT = By.xpath("//label[contains(text(),'User Name')]/following-sibling::div//input | //input[@name='userName']");

        // Form fields - Password
        public static final By PASSWORD_LABEL = By.xpath("//label[text()='Password *']");
        public static final By PASSWORD_INPUT = By.xpath("//label[contains(text(),'Password')]/following-sibling::div//input[@type='password'] | //input[@name='password']");
        public static final By PASSWORD_VISIBILITY_TOGGLE = By.xpath("//button[@aria-label='toggle password visibility'] | //button[contains(@class,'password-toggle')]");

        // Form fields - Confirm Password
        public static final By CONFIRM_PASSWORD_LABEL = By.xpath("//label[text()='Confirm Password *']");
        public static final By CONFIRM_PASSWORD_INPUT = By.xpath("//label[contains(text(),'Confirm Password')]/following-sibling::div//input[@type='password'] | //input[@name='confirmPassword']");
        public static final By CONFIRM_PASSWORD_VISIBILITY_TOGGLE = By.xpath("//button[@aria-label='toggle confirm password visibility']");

        // Form fields - Role
        public static final By ROLE_LABEL = By.xpath("//label[text()='Role *']");
        public static final By ROLE_DROPDOWN = By.xpath("//label[contains(text(),'Role')]/following-sibling::div//div[contains(@class,'MuiSelect-select')]");
        public static final By ROLE_INPUT = By.xpath("//input[@name='role'] | //div[@role='button' and contains(text(),'--Select Role--')]");

        // Toggle switches
        public static final By ACTIVE_TOGGLE = By.xpath("//span[contains(@class,'MuiSwitch-root')]//input[@type='checkbox' and following-sibling::span[contains(text(),'Active')]] | //input[@name='active']/..");
        public static final By ACTIVE_LABEL = By.xpath("//span[text()='Active']");

        public static final By LOCKED_TOGGLE = By.xpath("//span[contains(@class,'MuiSwitch-root')]//input[@type='checkbox' and following-sibling::span[contains(text(),'Locked')]] | //input[@name='locked']/..");
        public static final By LOCKED_LABEL = By.xpath("//span[text()='Locked']");

        // Save button
        public static final By SAVE_BUTTON = By.xpath("//button[contains(@class,'MuiButton') and .//span[text()='SAVE']] | //button[@type='submit']");

        // Validation messages
        public static final By REQUIRED_FIELD_ERROR = By.xpath("//*[contains(@class,'error') and contains(text(),'required')]");
        public static final By PASSWORD_MISMATCH_ERROR = By.xpath("//*[contains(@class,'error') and contains(text(),'password')]");

        // Success/Error notifications
        public static final By SUCCESS_MESSAGE = By.xpath("//*[contains(@class,'success') or contains(@class,'MuiAlert-success')]");
        public static final By ERROR_MESSAGE = By.xpath("//*[contains(@class,'error') or contains(@class,'MuiAlert-error')]");
    }

    // ==================== COMMON LOCATORS ====================

    public static class Common {
        // Loading indicators
        public static final By LOADING_SPINNER = By.xpath("//div[contains(@class,'loading') or contains(@class,'spinner')]");
        public static final By CIRCULAR_PROGRESS = By.xpath("//*[contains(@class,'MuiCircularProgress')]");

        // Success messages
        public static final By SUCCESS_MESSAGE = By.xpath("//*[contains(@class,'success') or contains(text(),'Success')]");
        public static final By SUCCESS_SNACKBAR = By.xpath("//*[contains(@class,'MuiSnackbar') and contains(@class,'success')]");

        // Error popups
        public static final By ERROR_POPUP = By.xpath("//*[contains(@class,'error') or contains(@class,'alert-danger')]");
        public static final By ERROR_SNACKBAR = By.xpath("//*[contains(@class,'MuiSnackbar') and contains(@class,'error')]");

        // Common buttons
        public static final By SUBMIT_BUTTON = By.xpath("//button[@type='submit']");
        public static final By CANCEL_BUTTON = By.xpath("//button[contains(text(),'Cancel') or contains(text(),'CANCEL')]");
        public static final By CLOSE_BUTTON = By.xpath("//button[contains(text(),'Close') or contains(text(),'CLOSE')]");
        public static final By OK_BUTTON = By.xpath("//button[contains(text(),'OK') or contains(text(),'Ok')]");
        public static final By YES_BUTTON = By.xpath("//button[contains(text(),'Yes') or contains(text(),'YES')]");
        public static final By NO_BUTTON = By.xpath("//button[contains(text(),'No') or contains(text(),'NO')]");

        // Confirmation dialogs
        public static final By CONFIRM_DIALOG = By.xpath("//div[contains(@class,'MuiDialog')]");
        public static final By DIALOG_TITLE = By.xpath("//h2[contains(@class,'MuiDialogTitle')]");
        public static final By DIALOG_CONTENT = By.xpath("//div[contains(@class,'MuiDialogContent')]");

        // Dropdown options
        public static final By DROPDOWN_OPTION = By.xpath("//li[contains(@class,'MuiMenuItem-root')]");
    }
}