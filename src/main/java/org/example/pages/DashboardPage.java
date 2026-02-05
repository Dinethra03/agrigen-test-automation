package org.example.pages;


import org.example.Locators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Model for AgriGEN Dashboard Page
 */
public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
    private By agrigenLogo = Locators.DashboardPage.AGRIGEN_LOGO;
    private By userName = Locators.DashboardPage.USER_NAME;
    private By generalMenu = Locators.DashboardPage.GENERAL_MENU;
    private By operationEntity = Locators.DashboardPage.OPERATION_ENTITY;
    private By estateManagement = Locators.DashboardPage.ESTATE_MANAGEMENT;
    private By userManagement = Locators.DashboardPage.USER_MANAGEMENT;

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Check if AgriGEN logo is displayed
     * @return true if logo is visible
     */
    public boolean isLogoDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(agrigenLogo)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the displayed username
     * @return Username text
     */
    public String getUserName() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(userName)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Check if General menu is displayed
     * @return true if menu item exists
     */
    public boolean isGeneralMenuDisplayed() {
        try {
            return driver.findElement(generalMenu).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click on General menu
     */
    public void clickGeneralMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(generalMenu)).click();
    }

    /**
     * Click on Operation Entity Management
     */
    public void clickOperationEntity() {
        wait.until(ExpectedConditions.elementToBeClickable(operationEntity)).click();
    }

    /**
     * Click on Estate Management
     */
    public void clickEstateManagement() {
        wait.until(ExpectedConditions.elementToBeClickable(estateManagement)).click();
    }

    /**
     * Click on User Management
     */
    public void clickUserManagement() {
        wait.until(ExpectedConditions.elementToBeClickable(userManagement)).click();
    }

    /**
     * Verify user is on dashboard page
     * @return true if on dashboard
     */
    public boolean isOnDashboard() {
        return driver.getCurrentUrl().contains("/app/dashboard");
    }

    /**
     * Get current page URL
     * @return Current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get page title
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}