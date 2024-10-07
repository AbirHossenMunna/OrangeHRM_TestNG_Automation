package TestRunner.Time.Reports;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Login.LoginPage;
import pages.Time.Reports.ViewEmployeeReportPage;

import java.io.IOException;

public class ViewEmployeeReportTestRunner extends Setup {
    LoginPage loginPage;
    ViewEmployeeReportPage viewEmployeeReportPage;
    Utils utils;
    String employeeName;
    String projectName;
    String invalidProjectName;
    String invalidEmployeeName;

    public void basicInfo() {
        employeeName= "Abir";
        invalidEmployeeName = "Vin";
        projectName = "General HR Tasks";
        invalidProjectName = "Abir";
    }
    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        utils = new Utils();
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        utils.getUserCreds(5);
        loginPage.doLoginWithValidCredential(utils.username(), utils.getPassword());
    }
    @Test(priority = 0, description = "Mandatory Fields is empty")
    public void mandatoryFieldsShouldBlank() throws InterruptedException {
        Thread.sleep(2000);
        viewEmployeeReportPage = new ViewEmployeeReportPage(driver);
        String isGotErrorMsg = viewEmployeeReportPage.mandatoryFieldsShouldBlank();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed" +
                "then an error message will be thrown to the user 'Required'");
    }
    @Test(priority = 1, description = "View Project by Invalid Employee Name")
    public void employeeReportByInvalidEmployeeName() throws InterruptedException {
        Thread.sleep(2000);
        viewEmployeeReportPage = new ViewEmployeeReportPage(driver);
        basicInfo();
        String isGotErrorMsg = viewEmployeeReportPage.employeeReportByInvalidEmployeeName(invalidEmployeeName);
        Assert.assertTrue(isGotErrorMsg.contains("Invalid"));
        Allure.description("Verify that the system prevents project view when an invalid Employee is provided.");
    }
    @Test(priority = 2, description = "View Project by Invalid Project Name")
    public void employeeReportByInvalidProjectName() throws InterruptedException {
        Thread.sleep(2000);
        viewEmployeeReportPage = new ViewEmployeeReportPage(driver);
        basicInfo();
        String isGotErrorMsg = viewEmployeeReportPage.employeeReportByInvalidProjectName(employeeName,invalidProjectName);
        Assert.assertTrue(isGotErrorMsg.contains("Invalid"));
        Allure.description("Verify that the system prevents project creation when an invalid customer is provided.");
    }
    @Test(priority = 3, description = "View Project by Invalid Date Range")
    public void employeeReportByInvalidDateRange() throws InterruptedException {
        Thread.sleep(2000);
        viewEmployeeReportPage = new ViewEmployeeReportPage(driver);
        basicInfo();
        String isGotErrorMsg = viewEmployeeReportPage.employeeReportByInvalidDateRange(projectName);
        Assert.assertTrue(isGotErrorMsg.contains("From date should be before to date") ||
                isGotErrorMsg.contains("To date should be after From date"));
        Allure.description("Verify that the system prevents project view when an invalid Date range is provided.");
    }
    @Test(priority = 4, description = "Mandatory Fields fill up or not")
    public void viewEmployeeReportByMandatoryField() throws InterruptedException {
        Thread.sleep(2000);
        viewEmployeeReportPage = new ViewEmployeeReportPage(driver);
        basicInfo();
        String isGotText = viewEmployeeReportPage.viewEmployeeReportByMandatoryField();
        Assert.assertTrue(isGotText.contains("Records Found"));
        Allure.description("All the mandatory fields in the form must be filled up for Create Vacancy");
    }
}
