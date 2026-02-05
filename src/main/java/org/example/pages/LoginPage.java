package org.example.pages;



import org.example.Locators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Model for AgriGEN Login Page
 * Uses centralized locators from Locators class
 */
public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators - imported from centralized Locators class
    private final By usernameField = Locators.LoginPage.USERNAME_FIELD;
    private final By passwordField = Locators.LoginPage.PASSWORD_FIELD;
    private final By loginButton = Locators.LoginPage.LOGIN_BUTTON;
    private final By errorMessage = Locators.LoginPage.ERROR_MESSAGE;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Lower the default explicit wait to a pragmatic 10s (keeps responsiveness)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Enter username in the username field
     * @param username Username to enter
     */
    public void enterUsername(String username) {
        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(usernameField));

        // Try clicking to focus and clearing any existing value
        try { usernameElement.click(); } catch (Exception ignored) {}
        try { usernameElement.clear(); } catch (Exception ignored) {}

        // Primary attempt: sendKeys
        try {
            usernameElement.sendKeys(username);
        } catch (Exception e) {
            // Fallback 1: Actions
            try {
                new Actions(driver).moveToElement(usernameElement).click().sendKeys(username).perform();
            } catch (Exception ignored) {
                // Fallback 2: JavaScript set value
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));", usernameElement, username);
                } catch (Exception finalEx) {
                    throw new RuntimeException("Failed to enter username", finalEx);
                }
            }
        }

        // Verify the value was entered (short verification)
        try {
            wait.until(d -> usernameElement.getAttribute("value") != null && usernameElement.getAttribute("value").contains(username));
        } catch (Exception ignored) {
            // ignore - some inputs may not reflect immediately
        }
    }

    /**
     * Enter password in the password field
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(passwordField));

        try { passwordElement.click(); Thread.sleep(200); } catch (Exception ignored) {}
        try { passwordElement.clear(); } catch (Exception ignored) {}

        // Primary: sendKeys
        try {
            passwordElement.sendKeys(password);
        } catch (Exception e) {
            // Fallback: Actions
            try {
                new Actions(driver).moveToElement(passwordElement).click().sendKeys(password).perform();
            } catch (Exception ignored) {
                // Last resort: JS
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));", passwordElement, password);
                } catch (Exception finalEx) {
                    throw new RuntimeException("Failed to enter password", finalEx);
                }
            }
        }

        // Verify the value was entered (short verification)
        try {
            wait.until(d -> passwordElement.getAttribute("value") != null && !passwordElement.getAttribute("value").isEmpty());
        } catch (Exception ignored) {
            // ignore
        }
    }

    /**
     * Click the login button
     */
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    /**
     * Get error message text
     * @return Error message displayed on page
     */
    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the error message text shown on the login page
     * @return error message text or empty string if not present
     */
    public String getErrorMessage() {
        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            String text = el.getText();
            return text == null ? "" : text.trim();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Clear all input fields
     */
    @SuppressWarnings("unused")
     public void clearAllFields() {
         try { wait.until(ExpectedConditions.elementToBeClickable(usernameField)).clear(); } catch (Exception ignored) {}
         try { wait.until(ExpectedConditions.elementToBeClickable(passwordField)).clear(); } catch (Exception ignored) {}
     }

     /**
      * Get the current page URL
      * @return Current URL
      */
    @SuppressWarnings("unused")
     public String getCurrentUrl() {
         return driver.getCurrentUrl();
     }

     /**
      * Check if still on login page
      * @return true if on login page (URL contains /signin)
      */
     public boolean isOnLoginPage() {
         return driver.getCurrentUrl().contains("/signin");
     }

     /**
      * Check if navigated to dashboard
      * @return true if on dashboard page
      */
     public boolean isOnDashboard() {
         return driver.getCurrentUrl().contains("/app/dashboard");
     }

    /**
     * Wait for loader page to appear and disappear
     * @param timeoutSeconds Maximum time to wait
     */
    public void waitForLoaderToDisappear(int timeoutSeconds) {
        try {
            // Short probe for loader presence (avoid waiting the full timeout if loader never appears)
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            try {
                shortWait.until(ExpectedConditions.or(
                        ExpectedConditions.urlContains("/loader"),
                        ExpectedConditions.visibilityOfElementLocated(Locators.LoaderPage.LOADER_CONTAINER)
                ));

                // If loader detected, wait until it disappears (cap the wait)
                WebDriverWait loaderDisappearWait = new WebDriverWait(driver, Duration.ofSeconds(Math.min(timeoutSeconds, 8)));
                loaderDisappearWait.until(ExpectedConditions.invisibilityOfElementLocated(Locators.LoaderPage.LOADER_CONTAINER));
            } catch (Exception ignored) {
                // loader not detected quickly - continue
            }

            // Finally, ensure we've reached the dashboard (cap wait to avoid long blocking)
            WebDriverWait dashboardWait = new WebDriverWait(driver, Duration.ofSeconds(Math.min(timeoutSeconds, 10)));
            dashboardWait.until(ExpectedConditions.urlContains("/app/dashboard"));
        } catch (Exception e) {
            System.out.println("Loader transition was very quick or not detected");
        }
    }

    /**
     * Complete login process with username and password
     * @param username Username
     * @param password Password
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    /**
     * Complete login and wait for dashboard
     * @param username Username
     * @param password Password
     * @return true if successfully reached dashboard
     */
    public boolean loginAndWaitForDashboard(String username, String password) {
        // call login and wait for loader transition
        login(username, password);
        waitForLoaderToDisappear(15);
        return isOnDashboard();
    }
 }
