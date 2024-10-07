package TestRunner.Leave.AssignLeave;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Leave.AssignLeave.AssignLeavePage;
import pages.Login.LoginPage;

import java.io.IOException;

public class AssignLeaveTestRunner extends Setup {
    LoginPage loginPage;
    AssignLeavePage assignLeavePage;
    Utils utils;
    String invalidEmpName;
    String empName;

    public void basicInfo() {
        invalidEmpName = "Messi";
        empName = "Abir";
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
    public void mandatoryAllFieldShouldBlank() throws InterruptedException {
        Thread.sleep(2000);
        assignLeavePage = new AssignLeavePage(driver);
        String isGotErrorMsg = assignLeavePage.mandatoryAllFieldShouldBlank();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed" +
                "then an error message will be thrown to the user 'Required'");
    }

    @Test(priority = 1, description = "")
    public void doLeaveApplyWithInvalidEmpName() throws InterruptedException {
        Thread.sleep(2000);
        assignLeavePage = new AssignLeavePage(driver);
        basicInfo();
        String isGotErrorMsg = assignLeavePage.doLeaveApplyWithInvalidEmpName(invalidEmpName);
        Assert.assertTrue(isGotErrorMsg.contains("Invalid"));
        Allure.description("");

    }

    @Test(priority = 2, description = "Apply for Leave with Blank Date Fields")
    public void doLeaveApplyWithDateFieldAreBlank() throws InterruptedException {
        Thread.sleep(2000);
        assignLeavePage = new AssignLeavePage(driver);
        basicInfo();
        String isGotErrorMsg = assignLeavePage.doLeaveApplyWithDateFieldAreBlank(empName);
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("Verifies that the leave application cannot be submitted when the date fields " +
                "(\"From Date\" or \"To Date\") are left blank, ensuring proper input validation for the required fields.");
    }

    @Test(priority = 3, description = "Apply for Leave with Invalid Date Range")
    public void doLeaveApplyWithInvalidDateRange() throws InterruptedException {
        Thread.sleep(2000);
        assignLeavePage = new AssignLeavePage(driver);
        String isGotErrorMsg = assignLeavePage.doLeaveApplyWithInvalidDateRange();
        Assert.assertTrue(isGotErrorMsg.contains("To date should be after From date"));
        Allure.description("Verifies that the system enforces date range validation by preventing submission when " +
                "the 'From Date' is later than the 'To Date'.");
    }

    @Test(priority = 4, description = "Assign for Leave with Invalid Date Format")
    public void doLeaveApplyWithInvalidDateFormat() throws InterruptedException {
        Thread.sleep(2000);
        assignLeavePage = new AssignLeavePage(driver);
        String isGotErrorMsg = assignLeavePage.doLeaveApplyWithInvalidDateFormat();
        Assert.assertTrue(isGotErrorMsg.contains("Should be a valid date in dd-mm-yyyy format"));
        Allure.description("Validates that the system enforces proper date format validation by preventing submission " +
                "when an invalid date format is entered in the leave application form.");
    }

    @Test(priority = 5, description = "Assign for Leave with Valid Data")
    public void doAssignLeave() throws InterruptedException {
        Thread.sleep(2000);
        assignLeavePage = new AssignLeavePage(driver);
        assignLeavePage.doAssignLeave();
        Allure.description("Verifies that the system successfully Leave assign when all required fields," +
                " including valid date range and leave type, are correctly filled.");
    }
}
