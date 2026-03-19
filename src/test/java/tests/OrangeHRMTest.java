package tests;

import pages.*;
import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrangeHRMTest extends BaseTest {

    // ─────────────────────────────────────────────
    // TC_001: Valid admin login
    // ─────────────────────────────────────────────
    @Test(priority = 1, description = "Verify admin login with valid credentials")
    public void validAdminLogin() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.loginAs(ADMIN_USER, ADMIN_PASS);
        boolean isDashboard = dashboard.isDashboardDisplayed();
        System.out.println("Dashboard displayed: " + isDashboard);
        Assert.assertTrue(isDashboard, "Login failed - Dashboard not displayed");
    }

    // ─────────────────────────────────────────────
    // TC_002: Invalid login - wrong password
    // ─────────────────────────────────────────────
    @Test(priority = 2, description = "Verify error message on invalid credentials")
    public void invalidLoginWrongPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(ADMIN_USER, "wrongpassword123");
        boolean errorDisplayed = loginPage.isErrorMessageDisplayed();
        System.out.println("Error message displayed: " + errorDisplayed);
        Assert.assertTrue(errorDisplayed, "Error message not shown for invalid credentials");
    }

    // ─────────────────────────────────────────────
    // TC_003: Invalid login - wrong username
    // ─────────────────────────────────────────────
    @Test(priority = 3, description = "Verify error message on wrong username")
    public void invalidLoginWrongUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("wronguser", ADMIN_PASS);
        boolean errorDisplayed = loginPage.isErrorMessageDisplayed();
        System.out.println("Error message displayed: " + errorDisplayed);
        Assert.assertTrue(errorDisplayed, "Error message not shown for wrong username");
    }

    // ─────────────────────────────────────────────
    // TC_004: Dashboard URL verification
    // ─────────────────────────────────────────────
    @Test(priority = 4, description = "Verify URL changes to dashboard after login")
    public void verifyDashboardUrl() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.loginAs(ADMIN_USER, ADMIN_PASS);
        dashboard.isDashboardDisplayed();
        String url = dashboard.getCurrentUrl();
        System.out.println("Dashboard URL: " + url);
        Assert.assertTrue(url.contains("dashboard"),
                "URL does not contain 'dashboard' after login");
    }

    // ─────────────────────────────────────────────
    // TC_005: Navigate to Add Employee page
    // ─────────────────────────────────────────────
    @Test(priority = 5, description = "Verify Add Employee page loads from PIM menu")
    public void navigateToAddEmployee() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.loginAs(ADMIN_USER, ADMIN_PASS);
        dashboard.isDashboardDisplayed();

        AddEmployeePage addEmpPage = dashboard.navigateToAddEmployee();
        boolean isLoaded = addEmpPage.isAddEmployeePageLoaded();
        System.out.println("Add Employee page loaded: " + isLoaded);
        Assert.assertTrue(isLoaded, "Add Employee page did not load");
    }

    // ─────────────────────────────────────────────
    // TC_006: Add a new employee
    // ─────────────────────────────────────────────
    @Test(priority = 6, description = "Add a new employee with valid details")
    public void addNewEmployee() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.loginAs(ADMIN_USER, ADMIN_PASS);
        dashboard.isDashboardDisplayed();

        AddEmployeePage addEmpPage = dashboard.navigateToAddEmployee();
        addEmpPage.addEmployee("Deepika", "Tester", "EMP" + System.currentTimeMillis() % 10000);

        boolean saved = addEmpPage.isSuccessToastDisplayed();
        System.out.println("Employee added successfully: " + saved);
        Assert.assertTrue(saved, "Employee was not saved successfully");
    }

    // ─────────────────────────────────────────────
    // TC_007: Add employee with first name only
    // ─────────────────────────────────────────────
    @Test(priority = 7, description = "Add employee with only first name and last name")
    public void addEmployeeMinimalDetails() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.loginAs(ADMIN_USER, ADMIN_PASS);
        dashboard.isDashboardDisplayed();

        AddEmployeePage addEmpPage = dashboard.navigateToAddEmployee();
        addEmpPage.addEmployee("AutoTest", "User", "AT" + System.currentTimeMillis() % 9999);

        boolean saved = addEmpPage.isSuccessToastDisplayed();
        System.out.println("Employee with minimal details saved: " + saved);
        Assert.assertTrue(saved, "Employee with minimal details was not saved");
    }

    // ─────────────────────────────────────────────
    // TC_008: Navigate to Employee List
    // ─────────────────────────────────────────────
    @Test(priority = 8, description = "Verify Employee List page loads from PIM menu")
    public void navigateToEmployeeList() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.loginAs(ADMIN_USER, ADMIN_PASS);
        dashboard.isDashboardDisplayed();

        EmployeeListPage listPage = dashboard.navigateToEmployeeList();
        boolean isLoaded = listPage.isEmployeeListPageLoaded();
        System.out.println("Employee List page loaded: " + isLoaded);
        Assert.assertTrue(isLoaded, "Employee List page did not load");
    }

    // ─────────────────────────────────────────────
    // TC_009: Search employee - results appear
    // ─────────────────────────────────────────────
    @Test(priority = 9, description = "Search for an employee and verify results are displayed")
    public void searchEmployeeByName() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.loginAs(ADMIN_USER, ADMIN_PASS);
        dashboard.isDashboardDisplayed();

        EmployeeListPage listPage = dashboard.navigateToEmployeeList();
        listPage.searchByEmployeeName("Admin");

        boolean resultsFound = listPage.areResultsDisplayed();
        System.out.println("Search results found: " + resultsFound);
        Assert.assertTrue(resultsFound, "No results found when searching for 'Admin'");
    }

    // ─────────────────────────────────────────────
    // TC_010: Search with invalid name - no records
    // ─────────────────────────────────────────────
    @Test(priority = 10, description = "Search with non-existent name - verify No Records Found")
    public void searchNonExistentEmployee() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.loginAs(ADMIN_USER, ADMIN_PASS);
        dashboard.isDashboardDisplayed();

        EmployeeListPage listPage = dashboard.navigateToEmployeeList();
        listPage.searchByEmployeeId("ZZZZ99999");

        boolean noRecords = listPage.isNoRecordsDisplayed();
        System.out.println("No Records Found displayed: " + noRecords);
        Assert.assertTrue(noRecords,
                "'No Records Found' not displayed for invalid employee ID");
    }

    // ─────────────────────────────────────────────
    // TC_011: Verify employee list shows records
    // ─────────────────────────────────────────────
    @Test(priority = 11, description = "Verify employee list loads with existing records")
    public void verifyEmployeeListHasRecords() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.loginAs(ADMIN_USER, ADMIN_PASS);
        dashboard.isDashboardDisplayed();

        EmployeeListPage listPage = dashboard.navigateToEmployeeList();

        // Click search with no filters to get all employees
        boolean hasRecords = listPage.areResultsDisplayed();
        int count = listPage.getResultCount();
        System.out.println("Total employee records visible: " + count);
        Assert.assertTrue(hasRecords, "Employee list is empty - expected existing records");
    }
}
