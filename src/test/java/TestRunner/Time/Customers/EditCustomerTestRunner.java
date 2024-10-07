package TestRunner.Time.Customers;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Login.LoginPage;
import pages.Time.Customers.EditCustomerPage;

import java.io.IOException;

public class EditCustomerTestRunner extends Setup {
    LoginPage loginPage;
    EditCustomerPage editCustomerPage;
    Utils utils;

    String name;
    String existsName;
    String description;

    public void basicInfo() {
        name = "Abir Corporation Limited";
        existsName= "ACME Ltd";
        description ="Specifies that the test case checks whether the system prevents the creation of a customer with" +
                " a name that already exists and displays an appropriate error message.";
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
        editCustomerPage = new EditCustomerPage(driver);
        String isGotErrorMsg = editCustomerPage.mandatoryFieldsShouldBlank();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed" +
                "then an error message will be thrown to the user 'Required'");
    }
    @Test(priority = 1, description = "Prevent adding customer with existing name")
    public void alreadyExistsName() throws InterruptedException {
        Thread.sleep(2000);
        editCustomerPage = new EditCustomerPage(driver);
        basicInfo();
        String isGotErrorMsg = editCustomerPage.alreadyExistsName(existsName);
        Assert.assertTrue(isGotErrorMsg.contains("Already exists"));
        Allure.description("Verify that the system prevents form submission and displays an error message when " +
                "a customer name that already exists in the database is entered.");
    }
    @Test(priority = 2,description="Mandatory Fields fill up or not")
    public void editAllFields() throws InterruptedException{
        Thread.sleep(2000);
        editCustomerPage = new EditCustomerPage(driver);
        basicInfo();
        editCustomerPage.editAllFields(name,description);
        Assert.assertTrue(editCustomerPage.tableValidation.isDisplayed());
        Allure.description("All the mandatory fields in the form must be filled up for Add Customer");
    }
}
