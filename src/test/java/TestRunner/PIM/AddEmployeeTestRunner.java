package TestRunner.PIM;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.PIM.AddEmployee;
import pages.Login.LoginPage;

import java.io.IOException;


public class AddEmployeeTestRunner extends Setup {
    Utils utils;
    LoginPage loginPage;
    AddEmployee addEmployee;
    String firstName;
    String lastName;
    String existsId;
    String id;
    String invalidPassword;
    String invalidCPass;
    String username;
    String password;
    String validCPass;

    public void basicInfo() {
        firstName = "Abir Hossen";
        lastName = "Munna";
        existsId = "12345";
        id = "12345";
        invalidPassword = "123456";
        invalidCPass = "12345";
        username = "Abir001";
        password = "a12345678";
        validCPass = "a12345678";
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
    public void mandatoryFieldsShouldBlankWithoutLoginDetails() throws InterruptedException {
        Thread.sleep(2000);
        addEmployee = new AddEmployee(driver);
        String isGotErrorMsg = addEmployee.mandatoryFieldsShouldBlankWithoutLoginDetails();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed atleast for example:Vacancy Name is not given," +
                "then an error message will be thrown to the user 'Required'");
    }

    @Test(priority = 1, description = "Mandatory Fields fill up or not")
    public void mandatoryFieldsFillUpWithoutLoginDetails() throws InterruptedException {
        Thread.sleep(2000);
        addEmployee = new AddEmployee(driver);
        basicInfo();
        String isGotText = addEmployee.mandatoryFieldsFillUpWithoutLoginDetails(firstName, lastName);
        Thread.sleep(3000);
        Assert.assertTrue(isGotText.contains("Personal Details"));
        Allure.description("All the mandatory fields in the form must be filled up for Create Vacancy");
    }

    @Test(priority = 2, description = "Cannot Create Vacancy with already registered vacancy name")
    public void alreadyExistsId() throws InterruptedException {
        Thread.sleep(2000);
        addEmployee = new AddEmployee(driver);
        basicInfo();
        String isGotErrorMsg = addEmployee.alreadyExistsId(firstName, lastName, existsId);
        Assert.assertTrue(isGotErrorMsg.contains("Employee Id already exists"));
        Allure.description("Already been registered vacancy name cannot be used for creating Vacancy and " +
                "error message will be given.");
    }

    @Test(priority = 3, description = "Password Length Validation")
    public void passwordLengthValidation() throws InterruptedException {
        Thread.sleep(2000);
        addEmployee = new AddEmployee(driver);
        basicInfo();
        String isGotErrorMsg = addEmployee.passwordLengthValidation(id, username, invalidPassword, invalidCPass);
        Assert.assertTrue(isGotErrorMsg.contains("Should have at least 7 characters"));
        Allure.description("Verify that the system enforces password length requirements by rejecting passwords " +
                "shorter than the minimum or longer than the maximum allowed length.");
    }

    @Test(priority = 4, description = "Password Mismatch During Employee Creation")
    public void passwordMismatch() throws InterruptedException {
        Thread.sleep(2000);
        addEmployee = new AddEmployee(driver);
        basicInfo();
        String isGotErrorMsg = addEmployee.passwordMismatchWithLogin(firstName, lastName, id, username, password, invalidCPass);
        Assert.assertTrue(isGotErrorMsg.contains("Passwords do not match"));
        Allure.description("Verify that the system detects and prevents submission when the 'Password' and 'Confirm Password' fields contain mismatched values.");
    }

    @Test(priority = 5, description = "Create new employee")
    public void createEmployee() throws InterruptedException, IOException, ParseException {
        Thread.sleep(2000);
        addEmployee = new AddEmployee(driver);
        basicInfo();
        String isGotText = addEmployee.createEmployee(firstName, lastName, id, username, password, validCPass);
        Assert.assertTrue(isGotText.contains("Personal Details"));
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/pim/viewEmployeeList");
        Assert.assertTrue(addEmployee.tableValidation.isDisplayed());
        Thread.sleep(3000);
        Allure.description("Verify the system's ability to successfully create a new employee with valid data," +
                " ensuring that the employee appears in the employee list and no errors occur during the process.");
    }
//    @Test(priority = 2,enabled = false,description = "Check if user exists")
//    public void checkIfUserExists() throws IOException, ParseException {
//        addEmployee =new AddEmployee(driver);
//
//        List data= Utils.readJSONArray("./src/test/resources/users.json");
//        JSONObject userObj= (JSONObject) data.get(data.size()-1);
//        String exsistingUserName= (String) userObj.get("userName");
//        String validationMessageActual= addEmployee.checkIfUserExists(exsistingUserName);
//        String validationMessageExpected="Username already exists";
//
//        Assert.assertTrue(validationMessageActual.contains(validationMessageExpected));
//    }
}
