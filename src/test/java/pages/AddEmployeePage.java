package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class AddEmployeePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement firstNameField;

    @FindBy(xpath = "//input[@placeholder='Middle Name']")
    WebElement middleNameField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lastNameField;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    WebElement employeeIdField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveButton;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast-content')]")
    WebElement successToast;

    @FindBy(xpath = "//h6[contains(text(),'Personal Details')]")
    WebElement personalDetailsHeader;

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public boolean isAddEmployeePageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(firstNameField));
            return firstNameField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterFirstName(String name) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.clear();
        firstNameField.sendKeys(name);
    }

    public void enterLastName(String name) {
        lastNameField.clear();
        lastNameField.sendKeys(name);
    }

    public void enterMiddleName(String name) {
        middleNameField.clear();
        middleNameField.sendKeys(name);
    }

    public void clearAndEnterEmployeeId(String empId) {
        wait.until(ExpectedConditions.visibilityOf(employeeIdField));
        employeeIdField.clear();
        employeeIdField.sendKeys(empId);
    }

    public void clickSave() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", saveButton);
    }

    public boolean isSuccessToastDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successToast));
            return successToast.isDisplayed();
        } catch (Exception e) {
            // Fallback: check if navigated to personal details page
            try {
                wait.until(ExpectedConditions.visibilityOf(personalDetailsHeader));
                return personalDetailsHeader.isDisplayed();
            } catch (Exception ex) {
                return driver.getCurrentUrl().contains("viewPersonalDetails");
            }
        }
    }

    public void addEmployee(String firstName, String lastName, String empId) {
        enterFirstName(firstName);
        enterLastName(lastName);
        clearAndEnterEmployeeId(empId);
        clickSave();
    }
}
