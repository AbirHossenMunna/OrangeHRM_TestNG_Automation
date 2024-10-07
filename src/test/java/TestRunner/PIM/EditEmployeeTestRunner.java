package TestRunner.PIM;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Login.LoginPage;
import pages.PIM.EditEmployee;

import java.io.IOException;

public class EditEmployeeTestRunner extends Setup {
    LoginPage loginPage;
    EditEmployee editEmployee;
    Utils utils;
    String firstName;
    String lastName;
    String id;
    String editId;
    String editFirstName;
    String editLastName;

    public void basicInfo() {
        firstName = "Abir Hossen";
        lastName = "Munna";
        id = "123568";
        editId = "1781";
        editFirstName = "Leo";
        editLastName = "Messi";
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
        editEmployee = new EditEmployee(driver);
        String isGotErrorMsg = editEmployee.mandatoryFieldsShouldBlank();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed" +
                "then an error message will be thrown to the user 'Required'");
    }

    @Test(priority = 1, description = "Mandatory Fields fill up or not")
    public void mandatoryFieldsFillUp() throws InterruptedException {
        Thread.sleep(2000);
        editEmployee = new EditEmployee(driver);
        basicInfo();
        editEmployee.mandatoryFieldsFillUp(firstName, lastName);
        Thread.sleep(3000);
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/pim/viewEmployeeList");
        Assert.assertTrue(editEmployee.tableValidationByName.isDisplayed());
        Allure.description("All the mandatory fields in the form must be filled up for Edit Employee");
    }

    @Test(priority = 2, description = "Attachment is empty")
    public void attachmentFieldShouldBlank() throws InterruptedException {
        Thread.sleep(2000);
        editEmployee = new EditEmployee(driver);
        String isGotErrorMsg = editEmployee.attachmentFieldShouldBlank();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("Attachment fields in the form must be filled up,if it is missed" +
                "then an error message will be thrown to the user 'Required'");
    }

    @Test(priority = 3, description = "Edit with employee id")
    public void editWithId() throws InterruptedException {
        Thread.sleep(2000);
        editEmployee = new EditEmployee(driver);
        basicInfo();
        editEmployee.editWithId(id);
        Thread.sleep(3000);
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/pim/viewEmployeeList");
        Assert.assertTrue(editEmployee.tableValidationById.isDisplayed());
        Allure.description("Verify that the system allows editing of employee details using the Employee ID, " +
                "ensuring the correct employee is selected and changes are saved successfully.");
    }

    @Test(priority = 4, description = "Editing Personal Details and Uploading Attachments")
    public void editPersonalDetails() throws InterruptedException {
        Thread.sleep(2000);
        editEmployee = new EditEmployee(driver);
        basicInfo();
        editEmployee.editPersonalDetails(editFirstName, editLastName, editId);
        Thread.sleep(3000);
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/pim/viewEmployeeList");
        Assert.assertTrue(editEmployee.editTableValidationByName.isDisplayed());
        Assert.assertTrue(editEmployee.editTableValidationById.isDisplayed());
        Allure.description("Verify that the system allows editing of personal details and uploading of attachments, " +
                "ensuring both the details and the attachment are saved successfully without errors.");
    }
}
