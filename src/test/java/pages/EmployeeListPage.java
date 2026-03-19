package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class EmployeeListPage {

    WebDriver driver;
    WebDriverWait wait;

    // Search by name field
    @FindBy(xpath = "//label[text()='Employee Name']/following::input[1]")
    WebElement employeeNameSearch;

    // Employee ID search field
    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    WebElement employeeIdSearch;

    // Search button
    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;

    // Result rows
    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@role='row']")
    List<WebElement> resultRows;

    // No records found
    @FindBy(xpath = "//span[text()='No Records Found']")
    WebElement noRecordsLabel;

    // Delete buttons in results
    @FindBy(xpath = "//div[@class='oxd-table-body']//button[contains(@class,'oxd-icon-button') and .//i[contains(@class,'bi-trash')]]")
    List<WebElement> deleteButtons;

    // Delete confirmation button
    @FindBy(xpath = "//button[contains(@class,'oxd-button--label-danger') and text()=' Yes, Delete ']")
    WebElement confirmDeleteButton;

    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public boolean isEmployeeListPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchButton));
            return searchButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void searchByEmployeeName(String name) {
        wait.until(ExpectedConditions.visibilityOf(employeeNameSearch));
        employeeNameSearch.clear();
        employeeNameSearch.sendKeys(name);

        // Wait for dropdown suggestion and press Enter
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        employeeNameSearch.sendKeys(Keys.ENTER);
        searchButton.click();
    }

    public void searchByEmployeeId(String empId) {
        wait.until(ExpectedConditions.visibilityOf(employeeIdSearch));
        employeeIdSearch.clear();
        employeeIdSearch.sendKeys(empId);
        searchButton.click();
    }

    public int getResultCount() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(resultRows));
            return resultRows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean areResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(resultRows));
            return resultRows.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoRecordsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(noRecordsLabel));
            return noRecordsLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickDeleteFirstEmployee() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(deleteButtons));
            deleteButtons.get(0).click();
        } catch (Exception e) {
            System.out.println("Delete button not found: " + e.getMessage());
        }
    }

    public void confirmDelete() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
            confirmDeleteButton.click();
        } catch (Exception e) {
            System.out.println("Confirm delete button not found: " + e.getMessage());
        }
    }
}
