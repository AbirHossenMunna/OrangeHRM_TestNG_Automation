package TestRunner.Leave.Apply;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Leave.Apply.ApplyLeavePage;
import pages.Login.LoginPage;

import java.io.IOException;

public class ApplyLeaveTestRunner extends Setup {
    LoginPage loginPage;
    ApplyLeavePage applyLeavePage;
    Utils utils;
    String comment;

    public void basicInfo() {
        comment = "I need to take leave due to illness";
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
        applyLeavePage = new ApplyLeavePage(driver);
        String isGotErrorMsg = applyLeavePage.mandatoryAllFieldShouldBlank();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed" +
                "then an error message will be thrown to the user 'Required'");
    }

    @Test(priority = 1, description = "Apply for Leave with Blank Date Fields")
    public void doLeaveApplyWithDateFieldAreBlank() throws InterruptedException {
        Thread.sleep(2000);
        applyLeavePage = new ApplyLeavePage(driver);
        String isGotErrorMsg = applyLeavePage.doLeaveApplyWithDateFieldAreBlank();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("Verifies that the leave application cannot be submitted when the date fields " +
                "(\"From Date\" or \"To Date\") are left blank, ensuring proper input validation for the required fields.");
    }

    @Test(priority = 2, description = "Apply for Leave with Invalid Date Range")
    public void doLeaveApplyWithInvalidDateRange() throws InterruptedException {
        Thread.sleep(2000);
        applyLeavePage = new ApplyLeavePage(driver);
        String isGotErrorMsg = applyLeavePage.doLeaveApplyWithInvalidDateRange();
        Assert.assertTrue(isGotErrorMsg.contains("To date should be after From date"));
        Allure.description("Verifies that the system enforces date range validation by preventing submission when " +
                "the 'From Date' is later than the 'To Date'.");
    }

    @Test(priority = 3, description = "Apply for Leave with Invalid Date Format")
    public void doLeaveApplyWithInvalidDateFormat() throws InterruptedException {
        Thread.sleep(2000);
        applyLeavePage = new ApplyLeavePage(driver);
        String isGotErrorMsg = applyLeavePage.doLeaveApplyWithInvalidDateFormat();
        Assert.assertTrue(isGotErrorMsg.contains("Should be a valid date in yyyy-dd-mm format"));
        Allure.description("Validates that the system enforces proper date format validation by preventing submission " +
                "when an invalid date format is entered in the leave application form.");
    }

    @Test(priority = 4, description = "Apply for Leave with Valid Data")
    public void doLeaveApplyWithValidData() throws InterruptedException {
        Thread.sleep(2000);
        applyLeavePage = new ApplyLeavePage(driver);
        basicInfo();
        applyLeavePage.doLeaveApplyWithValidData(comment);
        Thread.sleep(3000);
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/leave/viewLeaveList");
        Assert.assertTrue(applyLeavePage.tableValidation.isDisplayed());
        Allure.description("Verifies that the system successfully processes a leave application when all required fields," +
                " including valid date range and leave type, are correctly filled.");
    }
}
