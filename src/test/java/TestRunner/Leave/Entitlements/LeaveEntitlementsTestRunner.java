package TestRunner.Leave.Entitlements;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Leave.Entitlements.LeaveEntitlementsPage;
import pages.Login.LoginPage;

import java.io.IOException;

public class LeaveEntitlementsTestRunner extends Setup {
    LoginPage loginPage;
    LeaveEntitlementsPage leaveEntitlementsPage;
    Utils utils;
    String empName;
    String invalidEmpName;

    public void basicInfo() {
        empName = "Abir";
        invalidEmpName = "Messi";
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
        leaveEntitlementsPage = new LeaveEntitlementsPage(driver);
        String isGotErrorMsg = leaveEntitlementsPage.mandatoryAllFieldShouldBlank();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed" +
                "then an error message will be thrown to the user 'Required'");
    }

    @Test(priority = 1, description = "Attempt to add leave entitlement with an invalid employee name")
    public void leaveEntitlementsWithInvalidName() throws InterruptedException {
        Thread.sleep(2000);
        leaveEntitlementsPage = new LeaveEntitlementsPage(driver);
        basicInfo();
        String isGotErrorMsg = leaveEntitlementsPage.leaveEntitlementsWithInvalidName(invalidEmpName);
        Assert.assertTrue(isGotErrorMsg.contains("Invalid"));
        Allure.description("Verify that the system prevents adding a leave entitlement when the employee name is invalid +" +
                " or ensure that an appropriate error message is displayed ");
    }

    @Test(priority = 2, description = "Leave Entitlements for an employee with valid data")
    public void leaveEntitlementsWithValidData() throws InterruptedException {
        Thread.sleep(2000);
        leaveEntitlementsPage = new LeaveEntitlementsPage(driver);
        basicInfo();
        String isGotMsg = leaveEntitlementsPage.leaveEntitlementsWithValidData(empName);
        Assert.assertTrue(isGotMsg.contains("Record Found"));
        Allure.description("Verify that the system successfully adds leave entitlement when all fields are filled with valid data");
    }
}
