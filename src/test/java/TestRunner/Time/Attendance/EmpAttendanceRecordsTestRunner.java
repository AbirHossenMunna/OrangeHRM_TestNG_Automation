package TestRunner.Time.Attendance;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Login.LoginPage;
import pages.Time.Attendance.EmployeeAttendanceRecordsPage;

import java.io.IOException;

public class EmpAttendanceRecordsTestRunner extends Setup {
    LoginPage loginPage;
    EmployeeAttendanceRecordsPage employeeAttendanceRecordsPage;
    Utils utils;
    String employeeName;
    String invalidName;

    public void basicInfo() {
        employeeName = "dvdvv vsv dsvsdv";
        invalidName = "asda";
    }

    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        utils = new Utils();
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        utils.getUserCreds(5);
        loginPage.doLoginWithValidCredential(utils.username(), utils.getPassword());
    }

    @Test(priority = 0, description = "Mandatory Fields fill up or not")
    public void mandatoryFieldsFillUp() throws InterruptedException {
        Thread.sleep(2000);
        employeeAttendanceRecordsPage = new EmployeeAttendanceRecordsPage(driver);
        basicInfo();
        String isGotResult = employeeAttendanceRecordsPage.mandatoryFieldsFillUp(employeeName);
        Assert.assertTrue(isGotResult.contains("Records Found"));
        Thread.sleep(3000);
        Assert.assertTrue(employeeAttendanceRecordsPage.tableValidationByDuration.isDisplayed());
        Allure.description("All the mandatory fields in the form must be filled up for Edit Employee");
    }

    @Test(priority = 1, description = "Mandatory Fields is empty")
    public void mandatoryFieldsShouldBlank() throws InterruptedException {
        Thread.sleep(2000);
        employeeAttendanceRecordsPage = new EmployeeAttendanceRecordsPage(driver);
        String isGotErrorMsg = employeeAttendanceRecordsPage.mandatoryFieldsShouldBlank();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed" +
                "then an error message will be thrown to the user 'Required'");
    }

    @Test(priority = 2, description = "Invalid Employee Name Input")
    public void invalidEmployeeName() throws InterruptedException {
        Thread.sleep(2000);
        employeeAttendanceRecordsPage = new EmployeeAttendanceRecordsPage(driver);
        basicInfo();
        String isGotErrorMsg = employeeAttendanceRecordsPage.invalidEmployeeName(invalidName);
        Assert.assertTrue(isGotErrorMsg.contains("Invalid"));
        Allure.description("Verify that the system handles incorrectly formatted names during employee view," +
                "displaying an appropriate error message.");
    }

    @Test(priority = 3, description = "Invalid Employee Date Entry")
    public void invalidEmployeeDate() throws InterruptedException {
        Thread.sleep(2000);
        employeeAttendanceRecordsPage = new EmployeeAttendanceRecordsPage(driver);
        basicInfo();
        String isGotErrorMsg = employeeAttendanceRecordsPage.invalidDate(employeeName);
        Assert.assertTrue(isGotErrorMsg.contains("Should be a valid date in yyyy-dd-mm format"));
        Allure.description("Verify that the system handles incorrectly formatted dates during employee view," +
                "displaying an appropriate error message.");
    }
}
