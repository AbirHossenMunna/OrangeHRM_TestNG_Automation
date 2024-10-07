# OrangeHRM_TestNG_Automation

### This is a complete project where a OrangeHRM site is automated by writing test suites using selenium-webdriver and TestNg as testing framework.
The following key modules are automated:

# Login
# Recruitment
     * Candidates
        * Add Candidate
        * Search Candidates
     * Vacancies
        * Search Vacancies
        * Add Vacancices
        * Create Vacancies
# PIM
     * Add Employee
     * Edit Employee
     * Search Employee
# Time
     * Attendance
        * Employee Attendance Records
     * Customers
        * Add Customers
        * Edit Customers
     * Projects
        * Search Project
     * Reports
        * View Employee Records
        * View Project Records
# Leave
     * Apply
        * Apply Leave
     * Assign Leave
        * Assign Leave 
     * Entitlements
        * Add Entitlements
        * Leave Entitlements
        * My Leave Eentitlements               

For failed test cases it will take a screenshot aswell at the point of failure.

### Technology:

* Tool: Selenium Webdriver
* IDE: Intellij
* Build tool: Gradle
* Language: Java
* Test_Runner: TestNG

### Prerequisite:
* Need to install jdk 11, gradle and allure
* Configure Environment variable for jdk 11, gradle and allure
* Clone this project and unzip it
* Open the project folder
* Double click on "build.gradle" and open it through IntellIJ IDEA
* Let the project build successfully
* Click on "Terminal" and run the automation scripts

### Run the Automation Script by the following command:

```bash
  gradle clean test 
```
* Selenium will open the browser and start automating.
* After automation to view allure report , give the following commands:

```bash
allure generate allure-results --clean -o allure-report
allure serve allure-results
```
#### N.B: I can't Report Generate because OrangeHRM is a open Source platfrom and Data all time changes thats why i can't generate report.
