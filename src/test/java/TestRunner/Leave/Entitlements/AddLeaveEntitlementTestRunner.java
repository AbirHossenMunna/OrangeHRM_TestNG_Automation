package TestRunner.Leave.Entitlements;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Leave.Entitlements.AddLeaveEntitlementPage;
import pages.Login.LoginPage;

import java.io.IOException;

public class AddLeaveEntitlementTestRunner extends Setup {
    LoginPage loginPage;
    AddLeaveEntitlementPage addLeaveEntitlementPage;
    Utils utils;
    String empName;
    String entitlement;
    String invalidEmpName;
    String invalidEntitlement;

    public void basicInfo() {
        empName = "Abir";
        invalidEmpName = "Messi";
        entitlement = "2";
        invalidEntitlement = "-2342";
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
        addLeaveEntitlementPage = new AddLeaveEntitlementPage(driver);
        String isGotErrorMsg = addLeaveEntitlementPage.mandatoryAllFieldShouldBlank();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed" +
                "then an error message will be thrown to the user 'Required'");
    }

    @Test(priority = 1, description = "Validate form submission with one mandatory field left empty")
    public void allMandatoryFieldFillButOneFieldLeft() throws InterruptedException {
        Thread.sleep(2000);
        addLeaveEntitlementPage = new AddLeaveEntitlementPage(driver);
        basicInfo();
        String isGotErrorMsg = addLeaveEntitlementPage.allMandatoryFieldFillButOneFieldLeft(empName);
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("Verify that the system blocks form submission when one mandatory field is left unfilled, " +
                "while all other fields are filled correctly. Ensure that an appropriate error message is displayed");
    }

    @Test(priority = 2, description = "Attempt to add leave entitlement with an invalid employee name")
    public void addLeaveEntitlementWithInvalidName() throws InterruptedException {
        Thread.sleep(2000);
        addLeaveEntitlementPage = new AddLeaveEntitlementPage(driver);
        basicInfo();
        String isGotErrorMsg = addLeaveEntitlementPage.addLeaveEntitlementWithInvalidName(entitlement, invalidEmpName);
        Assert.assertTrue(isGotErrorMsg.contains("Invalid"));
        Allure.description("Verify that the system prevents adding a leave entitlement when the employee name is invalid" +
                " or ensure that an appropriate error message is displayed ");
    }

    @Test(priority = 3, description = "Attempt to add leave entitlement with an invalid entitlement data")
    public void addLeaveEntitlementWithInvalidData() throws InterruptedException {
        Thread.sleep(2000);
        addLeaveEntitlementPage = new AddLeaveEntitlementPage(driver);
        basicInfo();
        String isGotErrorMsg = addLeaveEntitlementPage.addLeaveEntitlementWithInvalidData(empName, invalidEntitlement);
        Assert.assertTrue(isGotErrorMsg.contains("Should be a number with upto 2 decimal places"));
        Allure.description("Verify that the system prevents adding a leave entitlement when the employee name is invalid" +
                " or ensure that a error message is displayed and the user is informed");
    }

    @Test(priority = 4, description = "Add leave entitlement for an employee with valid data")
    public void AddLeaveEntitlementWithValidData() throws InterruptedException {
        Thread.sleep(2000);
        addLeaveEntitlementPage = new AddLeaveEntitlementPage(driver);
        basicInfo();
        String isGotMsg = addLeaveEntitlementPage.AddLeaveEntitlementWithValidData(entitlement);
        Assert.assertTrue(isGotMsg.contains("Record Found"));
        Allure.description("Verify that the system successfully adds leave entitlement when all fields are filled with" +
                "valid data and successfully message");
    }
}
