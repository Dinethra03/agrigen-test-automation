package org.example.pages;

import org.example.Locators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Page Object Model for User Management Page
 */
public class UserManagementPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By pageTitle = Locators.UserManagementPage.PAGE_TITLE;
    private final By groupDropdown = Locators.UserManagementPage.GROUP_DROPDOWN;
    private final By estateDropdown = Locators.UserManagementPage.ESTATE_DROPDOWN;
    private final By addUserButton = Locators.UserManagementPage.ADD_USER_BUTTON;
    private final By searchBox = Locators.UserManagementPage.SEARCH_BOX;
    private final By tableRows = Locators.UserManagementPage.TABLE_ROWS;
    private final By editButton = Locators.UserManagementPage.EDIT_BUTTON;
    private final By deleteButton = Locators.UserManagementPage.DELETE_BUTTON;

    // Constructor
    public UserManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Verify if User Management page is displayed
     * @return true if on User Management page
     */
    public boolean isUserManagementPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            return driver.getCurrentUrl().contains("/user") ||
                    driver.findElement(pageTitle).isDisplayed();
        } catch (Exception e) {
            System.out.println("User Management page not displayed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Wait for page to load completely
     */
    public void waitForPageLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            wait.until(ExpectedConditions.visibilityOfElementLocated(addUserButton));
            Thread.sleep(1000); // Allow UI to settle
        } catch (Exception e) {
            System.out.println("Page load wait completed with exception: " + e.getMessage());
        }
    }

    /**
     * Select group from dropdown
     * @param groupName Name of the group to select
     */
    public void selectGroup(String groupName) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(groupDropdown));
            dropdown.click();
            Thread.sleep(500);

            By optionLocator = By.xpath("//li[contains(@class,'MuiMenuItem-root') and text()='" + groupName + "']");
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
            option.click();

            System.out.println("✓ Selected group: " + groupName);
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
            dropdown.click();
            Thread.sleep(500);

            By optionLocator = By.xpath("//li[contains(@class,'MuiMenuItem-root') and text()='" + estateName + "']");
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
            option.click();

            System.out.println("✓ Selected estate: " + estateName);
        } catch (Exception e) {
            System.out.println("✗ Failed to select estate: " + e.getMessage());
        }
    }

    /**
     * Click Add User button
     */
    public void clickAddUser() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addUserButton));

            // Try regular click first
            try {
                button.click();
            } catch (Exception e) {
                // Fallback to JavaScript click
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            }

            System.out.println("✓ Clicked Add User button");
            Thread.sleep(1000); // Wait for page transition
        } catch (Exception e) {
            System.out.println("✗ Failed to click Add User button: " + e.getMessage());
        }
    }

    /**
     * Search for a user
     * @param searchText Text to search
     */
    public void searchUser(String searchText) {
        try {
            WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
            search.clear();
            search.sendKeys(searchText);
            Thread.sleep(1000); // Wait for search results

            System.out.println("✓ Searched for: " + searchText);
        } catch (Exception e) {
            System.out.println("✗ Failed to search: " + e.getMessage());
        }
    }

    /**
     * Clear search box
     */
    public void clearSearch() {
        try {
            WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
            search.clear();
            Thread.sleep(500);

            System.out.println("✓ Cleared search");
        } catch (Exception e) {
            System.out.println("✗ Failed to clear search: " + e.getMessage());
        }
    }

    /**
     * Get count of users in table
     * @return Number of user rows
     */
    public int getUserCount() {
        try {
            List<WebElement> rows = driver.findElements(tableRows);
            return rows.size();
        } catch (Exception e) {
            System.out.println("✗ Failed to get user count: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Check if user exists in table by username
     * @param username Username to check
     * @return true if user exists
     */
    public boolean isUserPresent(String username) {
        try {
            By userLocator = By.xpath("//td[text()='" + username + "']");
            return driver.findElement(userLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get user details from table by row index
     * @param rowIndex Index of the row (0-based)
     * @return List containing [username, roleName, firstName, lastName, status]
     */
    public List<String> getUserDetails(int rowIndex) {
        List<String> userDetails = new ArrayList<>();
        try {
            List<WebElement> rows = driver.findElements(tableRows);
            if (rowIndex < rows.size()) {
                WebElement row = rows.get(rowIndex);
                List<WebElement> cells = row.findElements(By.tagName("td"));

                for (WebElement cell : cells) {
                    userDetails.add(cell.getText());
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Failed to get user details: " + e.getMessage());
        }
        return userDetails;
    }

    /**
     * Click Edit button for specific user
     * @param username Username of the user to edit
     */
    public void clickEditUser(String username) {
        try {
            By editButtonLocator = By.xpath("//td[text()='" + username + "']/following-sibling::td//button[@title='Edit' or contains(@class,'edit')]");
            WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(editButtonLocator));

            try {
                editBtn.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editBtn);
            }

            System.out.println("✓ Clicked Edit for user: " + username);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("✗ Failed to click Edit: " + e.getMessage());
        }
    }

    /**
     * Click Delete button for specific user
     * @param username Username of the user to delete
     */
    public void clickDeleteUser(String username) {
        try {
            By deleteButtonLocator = By.xpath("//td[text()='" + username + "']/following-sibling::td//button[@title='Delete' or contains(@class,'delete')]");
            WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(deleteButtonLocator));

            try {
                deleteBtn.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
            }

            System.out.println("✓ Clicked Delete for user: " + username);
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("✗ Failed to click Delete: " + e.getMessage());
        }
    }

    /**
     * Get status of a user
     * @param username Username to check
     * @return Status text (Active/Inactive)
     */
    public String getUserStatus(String username) {
        try {
            By statusLocator = By.xpath("//td[text()='" + username + "']/following-sibling::td[contains(text(),'Active') or contains(text(),'Inactive')]");
            return driver.findElement(statusLocator).getText();
        } catch (Exception e) {
            System.out.println("✗ Failed to get user status: " + e.getMessage());
            return "";
        }
    }

    /**
     * Verify table headers are displayed
     * @return true if all headers are visible
     */
    public boolean areTableHeadersDisplayed() {
        try {
            boolean usernameHeader = driver.findElement(Locators.UserManagementPage.USERNAME_HEADER).isDisplayed();
            boolean roleHeader = driver.findElement(Locators.UserManagementPage.ROLE_NAME_HEADER).isDisplayed();
            boolean firstNameHeader = driver.findElement(Locators.UserManagementPage.FIRST_NAME_HEADER).isDisplayed();
            boolean lastNameHeader = driver.findElement(Locators.UserManagementPage.LAST_NAME_HEADER).isDisplayed();
            boolean statusHeader = driver.findElement(Locators.UserManagementPage.STATUS_HEADER).isDisplayed();
            boolean actionsHeader = driver.findElement(Locators.UserManagementPage.ACTIONS_HEADER).isDisplayed();

            return usernameHeader && roleHeader && firstNameHeader && lastNameHeader && statusHeader && actionsHeader;
        } catch (Exception e) {
            System.out.println("✗ Table headers not displayed: " + e.getMessage());
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
     * Check if Add User button is displayed
     * @return true if button is visible
     */
    public boolean isAddUserButtonDisplayed() {
        try {
            return driver.findElement(addUserButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click pagination next button
     */
    public void clickNextPage() {
        try {
            WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.UserManagementPage.NEXT_PAGE_BUTTON));
            nextBtn.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked next page");
        } catch (Exception e) {
            System.out.println("✗ Failed to click next page: " + e.getMessage());
        }
    }

    /**
     * Click pagination previous button
     */
    public void clickPreviousPage() {
        try {
            WebElement prevBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.UserManagementPage.PREVIOUS_PAGE_BUTTON));
            prevBtn.click();
            Thread.sleep(1000);
            System.out.println("✓ Clicked previous page");
        } catch (Exception e) {
            System.out.println("✗ Failed to click previous page: " + e.getMessage());
        }
    }
}