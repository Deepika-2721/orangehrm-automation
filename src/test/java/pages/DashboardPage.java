package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//h6[contains(@class,'oxd-text') and contains(text(),'Dashboard')]")
    WebElement dashboardTitle;

    @FindBy(xpath = "//span[text()='PIM']")
    WebElement pimMenu;

    @FindBy(xpath = "//a[text()='Employee List']")
    WebElement employeeListLink;

    @FindBy(xpath = "//a[text()='Add Employee']")
    WebElement addEmployeeLink;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(dashboardTitle));
            return dashboardTitle.isDisplayed();
        } catch (Exception e) {
            // Try URL check as fallback
            return driver.getCurrentUrl().contains("dashboard");
        }
    }

    public String getDashboardTitle() {
        try {
            wait.until(ExpectedConditions.visibilityOf(dashboardTitle));
            return dashboardTitle.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public AddEmployeePage navigateToAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu));
        pimMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeLink));
        addEmployeeLink.click();
        return new AddEmployeePage(driver);
    }

    public EmployeeListPage navigateToEmployeeList() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu));
        pimMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(employeeListLink));
        employeeListLink.click();
        return new EmployeeListPage(driver);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
