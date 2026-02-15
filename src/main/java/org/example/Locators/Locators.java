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
        public static final By LOGIN_BUTTON =By.xpath("//button[.//span[normalize-space()='Sign in now']]");

        // Alternative button locator
        public static final By LOGIN_BUTTON_ALT = By.xpath("//button[contains(normalize-space(),'SIGN IN')]");

        // Error messages
        public static final By ERROR_MESSAGE =By.xpath("//div[contains(@class,'MuiAlert-message') and contains(.,'Invalid username or password')]");
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
        public static final By USER_NAME =By.xpath("//h6[contains(@class,'MuiTypography-h6') and contains(@class,'MuiTypography-colorPrimary') and text()='Dinethra R']");
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

    // ==================== COMMON LOCATORS ====================

    public static class Common {
        // Loading indicators
        public static final By LOADING_SPINNER = By.xpath("//div[contains(@class,'loading') or contains(@class,'spinner')]");

        // Success messages
        public static final By SUCCESS_MESSAGE = By.xpath("//*[contains(@class,'success') or contains(text(),'Success')]");

        // Error popups
        public static final By ERROR_POPUP = By.xpath("//*[contains(@class,'error') or contains(@class,'alert-danger')]");

        // Common buttons
        public static final By SUBMIT_BUTTON = By.xpath("//button[@type='submit']");
        public static final By CANCEL_BUTTON = By.xpath("//button[contains(text(),'Cancel')]");
        public static final By CLOSE_BUTTON = By.xpath("//button[contains(text(),'Close')]");
        public static final By OK_BUTTON = By.xpath("//button[contains(text(),'OK') or contains(text(),'Ok')]");
    }
}