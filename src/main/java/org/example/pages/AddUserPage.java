package org.example.pages;

import org.example.Locators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Model for Add User Page
 */
public class AddUserPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By pageTitle = Locators.AddUserPage.PAGE_TITLE;
    private final By backButton = Locators.AddUserPage.BACK_BUTTON;
    private final By groupDropdown = Locators.AddUserPage.GROUP_DROPDOWN;
    private final By estateDropdown = Locators.AddUserPage.ESTATE_DROPDOWN;
    private final By firstNameInput = Locators.AddUserPage.FIRST_NAME_INPUT;
    private final By lastNameInput = Locators.AddUserPage.LAST_NAME_INPUT;
    private final By userNameInput = Locators.AddUserPage.USER_NAME_INPUT;
    private final By passwordInput = Locators.AddUserPage.PASSWORD_INPUT;
    private final By confirmPasswordInput = Locators.AddUserPage.CONFIRM_PASSWORD_INPUT;
    private final By roleDropdown = Locators.AddUserPage.ROLE_DROPDOWN;
    private final By activeToggle = Locators.AddUserPage.ACTIVE_TOGGLE;
    private final By lockedToggle = Locators.AddUserPage.LOCKED_TOGGLE;
    private final By saveButton = Locators.AddUserPage.SAVE_BUTTON;

    // Constructor
    public AddUserPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Verify if Add User page is displayed
     * @return true if on Add User page
     */
    public boolean isAddUserPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            return driver.findElement(pageTitle).isDisplayed();
        } catch (Exception e) {
            System.out.println("Add User page not displayed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Wait for page to load completely
     */
    public void waitForPageLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton));
            Thread.sleep(1000); // Allow UI to settle
        } catch (Exception e) {
            System.out.println("Page load wait completed with exception: " + e.getMessage());
        }
    }

    /**
     * Click Back button
     */
    public void clickBack() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(backButton));

            try {
                button.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            }

            System.out.println("✓ Clicked Back button");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("✗ Failed to click Back button: " + e.getMessage());
        }
    }

    /**
     * Select group from dropdown
     * @param groupName Name of the group to select
     */
    public void selectGroup(String groupName) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(groupDropdown));

            try {
                dropdown.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
            }

            Thread.sleep(500);

            By optionLocator = By.xpath("//li[contains(@class,'MuiMenuItem-root') and text()='" + groupName + "']");
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
            option.click();

            System.out.println("✓ Selected group: " + groupName);
            Thread.sleep(300);
        } catch (Exception e) {
            System.out.println("✗ Failed to select group: " + e.getMessage());
        }
    }

    /**
     * Select estate from dropdown
     * @param estateName Name of the estate to select
     */
    public void selectEstate(String estateName) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(estateDropdown));

            try {
                dropdown.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
            }

            Thread.sleep(500);

            By optionLocator = By.xpath("//li[contains(@class,'MuiMenuItem-root') and text()='" + estateName + "']");
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
            option.click();

            System.out.println("✓ Selected estate: " + estateName);
            Thread.sleep(300);
        } catch (Exception e) {
            System.out.println("✗ Failed to select estate: " + e.getMessage());
        }
    }

    /**
     * Enter first name
     * @param firstName First name to enter
     */
    public void enterFirstName(String firstName) {
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
            input.clear();
            input.sendKeys(firstName);

            System.out.println("✓ Entered first name: " + firstName);
        } catch (Exception e) {
            System.out.println("✗ Failed to enter first name: " + e.getMessage());
        }
    }

    /**
     * Enter last name
     * @param lastName Last name to enter
     */
    public void enterLastName(String lastName) {
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
            input.clear();
            input.sendKeys(lastName);

            System.out.println("✓ Entered last name: " + lastName);
        } catch (Exception e) {
            System.out.println("✗ Failed to enter last name: " + e.getMessage());
        }
    }

    /**
     * Enter username
     * @param userName Username to enter
     */
    public void enterUserName(String userName) {
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameInput));
            input.clear();
            input.sendKeys(userName);

            System.out.println("✓ Entered username: " + userName);
        } catch (Exception e) {
            System.out.println("✗ Failed to enter username: " + e.getMessage());
        }
    }

    /**
     * Enter password
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
            input.clear();
            input.sendKeys(password);

            System.out.println("✓ Entered password");
        } catch (Exception e) {
            System.out.println("✗ Failed to enter password: " + e.getMessage());
        }
    }

    /**
     * Enter confirm password
     * @param confirmPassword Confirm password to enter
     */
    public void enterConfirmPassword(String confirmPassword) {
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordInput));
            input.clear();
            input.sendKeys(confirmPassword);

            System.out.println("✓ Entered confirm password");
        } catch (Exception e) {
            System.out.println("✗ Failed to enter confirm password: " + e.getMessage());
        }
    }

    /**
     * Select role from dropdown
     * @param roleName Name of the role to select
     */
    public void selectRole(String roleName) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(roleDropdown));

            try {
                dropdown.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
            }

            Thread.sleep(500);

            By optionLocator = By.xpath("//li[contains(@class,'MuiMenuItem-root') and text()='" + roleName + "']");
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
            option.click();

            System.out.println("✓ Selected role: " + roleName);
            Thread.sleep(300);
        } catch (Exception e) {
            System.out.println("✗ Failed to select role: " + e.getMessage());
        }
    }

    /**
     * Toggle Active switch
     * @param enable true to enable, false to disable
     */
    public void setActiveToggle(boolean enable) {
        try {
            WebElement toggle = driver.findElement(activeToggle);
            boolean isChecked = toggle.isSelected();

            if ((enable && !isChecked) || (!enable && isChecked)) {
                toggle.click();
                Thread.sleep(300);
                System.out.println("✓ Active toggle set to: " + enable);
            } else {
                System.out.println("✓ Active toggle already at: " + enable);
            }
        } catch (Exception e) {
            System.out.println("✗ Failed to set active toggle: " + e.getMessage());
        }
    }

    /**
     * Toggle Locked switch
     * @param enable true to enable, false to disable
     */
    public void setLockedToggle(boolean enable) {
        try {
            WebElement toggle = driver.findElement(lockedToggle);
            boolean isChecked = toggle.isSelected();

            if ((enable && !isChecked) || (!enable && isChecked)) {
                toggle.click();
                Thread.sleep(300);
                System.out.println("✓ Locked toggle set to: " + enable);
            } else {
                System.out.println("✓ Locked toggle already at: " + enable);
            }
        } catch (Exception e) {
            System.out.println("✗ Failed to set locked toggle: " + e.getMessage());
        }
    }

    /**
     * Click Save button
     */
    public void clickSave() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(saveButton));

            // Scroll to button
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
            Thread.sleep(300);

            try {
                button.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            }

            System.out.println("✓ Clicked Save button");
            Thread.sleep(1500); // Wait for save operation
        } catch (Exception e) {
            System.out.println("✗ Failed to click Save button: " + e.getMessage());
        }
    }

    /**
     * Fill complete user form
     * @param group Group name
     * @param estate Estate name
     * @param firstName First name
     * @param lastName Last name
     * @param userName Username
     * @param password Password
     * @param confirmPassword Confirm password
     * @param role Role name
     * @param isActive Active status
     * @param isLocked Locked status
     */
    public void fillUserForm(String group, String estate, String firstName, String lastName,
                             String userName, String password, String confirmPassword,
                             String role, boolean isActive, boolean isLocked) {
        selectGroup(group);
        selectEstate(estate);
        enterFirstName(firstName);
        enterLastName(lastName);
        enterUserName(userName);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        selectRole(role);
        setActiveToggle(isActive);
        setLockedToggle(isLocked);
    }

    /**
     * Check if success message is displayed
     * @return true if success message is visible
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.AddUserPage.SUCCESS_MESSAGE));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if error message is displayed
     * @return true if error message is visible
     */
    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(Locators.AddUserPage.ERROR_MESSAGE).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get error message text
     * @return Error message text
     */
    public String getErrorMessage() {
        try {
            return driver.findElement(Locators.AddUserPage.ERROR_MESSAGE).getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Check if required field error is displayed
     * @return true if required field error is visible
     */
    public boolean isRequiredFieldErrorDisplayed() {
        try {
            return driver.findElement(Locators.AddUserPage.REQUIRED_FIELD_ERROR).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get current URL
     * @return Current page URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Check if Save button is enabled
     * @return true if button is enabled
     */
    public boolean isSaveButtonEnabled() {
        try {
            return driver.findElement(saveButton).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clear all form fields
     */
    public void clearAllFields() {
        try {
            driver.findElement(firstNameInput).clear();
            driver.findElement(lastNameInput).clear();
            driver.findElement(userNameInput).clear();
            driver.findElement(passwordInput).clear();
            driver.findElement(confirmPasswordInput).clear();

            System.out.println("✓ Cleared all form fields");
        } catch (Exception e) {
            System.out.println("✗ Failed to clear all fields: " + e.getMessage());
        }
    }

    /**
     * Toggle password visibility
     */
    public void togglePasswordVisibility() {
        try {
            WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(Locators.AddUserPage.PASSWORD_VISIBILITY_TOGGLE));
            toggle.click();
            Thread.sleep(300);

            System.out.println("✓ Toggled password visibility");
        } catch (Exception e) {
            System.out.println("✗ Failed to toggle password visibility: " + e.getMessage());
        }
    }

    /**
     * Toggle confirm password visibility
     */
    public void toggleConfirmPasswordVisibility() {
        try {
            WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(Locators.AddUserPage.CONFIRM_PASSWORD_VISIBILITY_TOGGLE));
            toggle.click();
            Thread.sleep(300);

            System.out.println("✓ Toggled confirm password visibility");
        } catch (Exception e) {
            System.out.println("✗ Failed to toggle confirm password visibility: " + e.getMessage());
        }
    }
}