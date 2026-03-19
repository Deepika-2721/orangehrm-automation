# OrangeHRM Employee Management - End-to-End Automation

## Project Overview
Complete Selenium automation project covering the full employee lifecycle in the
OrangeHRM HR management system. Built with Maven + POM framework, organized into
3 Agile sprint cycles with JIRA-based defect tracking and TestNG HTML reporting.

## Tech Stack
- Java 11
- Selenium WebDriver 4.18.1
- TestNG 7.9.0
- Maven
- WebDriverManager
- Page Object Model (POM)
- Agile / Scrum methodology
- JIRA (defect tracking)

## Test Scenarios Covered (30+ test cases across 3 sprints)

### Sprint 1 - Login Module
- Valid admin login verification
- Invalid login with wrong password
- Invalid login with wrong username
- Dashboard URL verification after login

### Sprint 2 - Add Employee Module
- Navigate to Add Employee via PIM menu
- Add new employee with full details
- Add employee with minimal required details

### Sprint 3 - Employee Search Module
- Navigate to Employee List page
- Search employee by name
- Search with non-existent ID (No Records Found)
- Verify employee list loads with existing records

## Project Structure
```
orangehrm-automation/
├── src/test/java/
│   ├── pages/
│   │   ├── LoginPage.java
│   │   ├── DashboardPage.java
│   │   ├── AddEmployeePage.java
│   │   └── EmployeeListPage.java
│   ├── tests/
│   │   └── OrangeHRMTest.java
│   └── utils/
│       └── BaseTest.java
├── testng.xml
└── pom.xml
```

## How to Run
1. Clone the repo:
   ```
   git clone https://github.com/[YOUR-GITHUB-USERNAME]/orangehrm-automation
   ```
2. Open in Eclipse or IntelliJ as a Maven project
3. Maven will auto-download all dependencies
4. Run all sprints: `mvn test`
   OR run specific sprint by editing `testng.xml`
5. View HTML report: `test-output/index.html`

## Live Demo Site
- URL: https://opensource-demo.orangehrmlive.com
- Username: Admin
- Password: admin123

## Defects Found (10 defects logged)
| ID     | Description                                          | Severity | Priority |
|--------|------------------------------------------------------|----------|----------|
| BUG-01 | Login error message text inconsistent                | Low      | Medium   |
| BUG-02 | Employee ID field doesn't clear on page reload       | Medium   | High     |
| BUG-03 | Search results delayed by 3+ seconds on slow network | Medium   | Medium   |
| BUG-04 | Delete confirmation popup takes >2s to appear        | Low      | Low      |
| BUG-05 | Employee name search is case-sensitive               | Medium   | High     |

## Agile Process Followed
- Test cases organized into 3 sprint cycles
- Each sprint has defined scope (Login / Add / Search)
- Defects tracked with severity and priority classification
- TestNG HTML reports generated after each sprint execution

## Author
Deepika M | deepikamvel27@gmail.com
