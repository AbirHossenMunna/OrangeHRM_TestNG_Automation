package TestRunner.Time.Customers;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Login.LoginPage;
import pages.Time.Customers.AddCustomerPage;

import java.io.IOException;

public class AddCustomerTestRunner extends Setup {
    LoginPage loginPage;
    AddCustomerPage addCustomerPage;
    Utils utils;
    String name;
    String existsName;
    String description;

    public void basicInfo() {
        name = "Abir Corporation Ltd";
        existsName = "Aayushi";
        description = "Specifies that the test case checks whether the system prevents the creation of a customer with" +
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
        addCustomerPage = new AddCustomerPage(driver);
        String isGotErrorMsg = addCustomerPage.mandatoryFieldsShouldBlank();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed" +
                "then an error message will be thrown to the user 'Required'");
    }

    @Test(priority = 1, description = "Prevent adding customer with existing name")
    public void alreadyExistsName() throws InterruptedException {
        Thread.sleep(2000);
        addCustomerPage = new AddCustomerPage(driver);
        basicInfo();
        String isGotErrorMsg = addCustomerPage.alreadyExistsName(existsName);
        Assert.assertTrue(isGotErrorMsg.contains("Already exists"));
        Allure.description("Verify that the system prevents form submission and displays an error message when " +
                "a customer name that already exists in the database is entered.");
    }

    @Test(priority = 2, description = "All fields Fill with valid data")
    public void allFieldsFillUp() throws InterruptedException {
        Thread.sleep(2000);
        addCustomerPage = new AddCustomerPage(driver);
        basicInfo();
        addCustomerPage.allFieldsFillUp(name, description);
        Assert.assertTrue(addCustomerPage.tableValidation.isDisplayed());
        Allure.description("This test verifies that all fields are filled with valid data and the form submission is successful.");
    }
}
