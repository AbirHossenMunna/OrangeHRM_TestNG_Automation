package TestRunner.Leave.Entitlements;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Leave.Entitlements.MyLeaveEntitlementsPage;
import pages.Login.LoginPage;

import java.io.IOException;

public class MyLeaveEntitlementsTestRunner extends Setup {
    LoginPage loginPage;
    MyLeaveEntitlementsPage myLeaveEntitlementsPage;
    Utils utils;
    String entitlement;
    String invalidEntitlement;
    public void basicInfo() {
        entitlement = "16";
        invalidEntitlement ="-2548";
    }
    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        utils = new Utils();
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        utils.getUserCreds(5);
        loginPage.doLoginWithValidCredential(utils.username(), utils.getPassword());
    }
    @Test(priority = 0, description = "Search for an employee's leave entitlement")
    public void SearchEntitlement() throws InterruptedException {
        Thread.sleep(2000);
        myLeaveEntitlementsPage = new MyLeaveEntitlementsPage(driver);
        String isGotMsg = myLeaveEntitlementsPage.SearchEntitlement();
        Assert.assertTrue(isGotMsg.contains("Record Found"));
        Allure.description("Verify that the system correctly searches and displays results for a valid Entitlements," +
                "showing relevant Entitlements details without any errors.");
    }
    @Test(priority = 1, description = "Mandatory Fields is empty")
    public void editMandatoryAllFieldShouldBlank() throws InterruptedException {
        Thread.sleep(2000);
        myLeaveEntitlementsPage = new MyLeaveEntitlementsPage(driver);
        String isGotErrorMsg = myLeaveEntitlementsPage.editMandatoryAllFieldShouldBlank();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed" +
                "then an error message will be thrown to the user 'Required'");
    }
    @Test(priority = 2, description = "Attempt to edit leave entitlement with an invalid entitlement")
    public void editLeaveEntitlementWithInvalidEntitlement() throws InterruptedException {
        Thread.sleep(2000);
        myLeaveEntitlementsPage = new MyLeaveEntitlementsPage(driver);
        basicInfo();
        String isGotErrorMsg = myLeaveEntitlementsPage.editLeaveEntitlementWithInvalidEntitlement(invalidEntitlement);
        Assert.assertTrue(isGotErrorMsg.contains("Should be a number with upto 2 decimal places"));
        Allure.description("Verify that the system prevents editing a leave entitlement when the employee name is invalid" +
                " or ensure that an appropriate error message is displayed ");
    }
    @Test(priority = 4, description = "Edit leave entitlement for an employee with valid data")
    public void editLeaveEntitlementWithValidEntitlement() throws InterruptedException {
        Thread.sleep(2000);
        myLeaveEntitlementsPage = new MyLeaveEntitlementsPage(driver);
        basicInfo();
        String isGotMsg = myLeaveEntitlementsPage.editLeaveEntitlementWithValidEntitlement(entitlement);
        Assert.assertTrue(isGotMsg.contains("Record Found"));
        Allure.description("Verify that the system successfully edit leave entitlement when all fields are filled with" +
                "valid data and successfully message");
    }
}
