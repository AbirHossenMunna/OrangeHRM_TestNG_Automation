package TestRunner.Recruitment.Candidates;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Login.LoginPage;
import pages.Recruitment.Candidates.AddCandidatePage;

import java.io.IOException;

public class AddCandidatesTestRunner extends Setup {
    LoginPage loginPage;
    AddCandidatePage addCandidatePage;
    Utils utils;
    String firstName;
    String lastName;
    String email;
    String contactNumber;
    String notes;
    String invalidContactNumber;
    String invalidEmail;
    String blankEmail;
    String newFirstName;
    String newLastName;
    String newEmail;

    public void basicInfo() {
        firstName = "Md.Abir Hossen";
        lastName = "Munna";
        newFirstName = "Leo";
        newLastName = "Messi";
        email = "abir@gmail.com";
        newEmail = "ab@gmail.com";
        contactNumber = "017895452547";
        notes = "As an SQA (Software Quality Assurance) Engineer, role is crucial in ensuring the quality of software products";
        invalidContactNumber = "abadafa";
        invalidEmail = "ab.com";
        blankEmail = "";
    }

    @BeforeTest
    public void doLogin() throws InterruptedException, IOException, ParseException {
        utils = new Utils();
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        utils.getUserCreds(5);
        loginPage.doLoginWithValidCredential(utils.username(), utils.getPassword());
    }

    @Test(priority = 0, description = "One Mandatory Fields is empty")
    public void oneMandatoryFieldsEmpty() throws InterruptedException {
        Thread.sleep(2000);
        addCandidatePage = new AddCandidatePage(driver);
        basicInfo();
        String isGotErrorMsg = addCandidatePage.oneMandatoryFieldsEmpty(firstName, lastName, blankEmail);
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed atleast for example:Vacancy Name is not given," +
                "then an error message will be thrown to the user 'Required'");
    }

    @Test(priority = 1, description = "Mandatory Fields fill up or not")
    public void mandatoryFieldsShouldFillUp() throws InterruptedException {
        Thread.sleep(2000);
        addCandidatePage = new AddCandidatePage(driver);
        basicInfo();
        String isGotText = addCandidatePage.mandatoryFieldsShouldFillUp(firstName, lastName, email);
        Assert.assertTrue(isGotText.contains("Candidate Profile"));
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/recruitment/viewCandidates");
        Assert.assertTrue(addCandidatePage.tableValidation.isDisplayed());
        Allure.description("All the mandatory fields in the form must be filled up for Create Candidates");
    }

    @Test(priority = 2, description = "Email with invalid data")
    public void addWithInvalidEmail() throws InterruptedException {
        Thread.sleep(2000);
        addCandidatePage = new AddCandidatePage(driver);
        basicInfo();
        String isGotErrorMsg = addCandidatePage.addWithInvalidEmail(newFirstName, newLastName, invalidEmail);
        Assert.assertTrue(isGotErrorMsg.contains("Expected format: admin@example.com"));
        Allure.description("Error message will be shown if user inputs invalid data of email field");
    }

    @Test(priority = 3, description = "Contact Number with invalid data")
    public void addWithInvalidPhoneNUmber() throws InterruptedException {
        Thread.sleep(2000);
        addCandidatePage = new AddCandidatePage(driver);
        basicInfo();
        String isGotErrorMsg = addCandidatePage.addWithInvalidPhoneNUmber(newFirstName, newLastName, email, invalidContactNumber);
        Assert.assertTrue(isGotErrorMsg.contains("Allows numbers and only + - / ( )"));
        Allure.description("Error message will be shown if user inputs invalid data of contact number field");
    }

    @Test(priority = 4, description = "Date of application with invalid format")
    public void invalidFormatDateOfApplication() throws InterruptedException {
        Thread.sleep(2000);
        addCandidatePage = new AddCandidatePage(driver);
        basicInfo();
        String isGotErrorMsg = addCandidatePage.invalidFormatDateOfApplication(newFirstName, newLastName, email, contactNumber);
        Assert.assertTrue(isGotErrorMsg.contains("Should be a valid date in yyyy-dd-mm format"));
        Allure.description("Error message will be shown if user inputs invalid data of date of application field");
    }

    @Test(priority = 5, description = "Successful create candidates")
    public void allFieldFillUpAddCandidates() throws InterruptedException {
        Thread.sleep(2000);
        addCandidatePage = new AddCandidatePage(driver);
        basicInfo();
        String isGotText = addCandidatePage.allFieldFillUpAddCandidates(newFirstName, newLastName, email, contactNumber, notes);
        Assert.assertTrue(isGotText.contains("Candidate Profile"));
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/recruitment/viewCandidates");
        Assert.assertTrue(addCandidatePage.tableValidation.isDisplayed());
        Allure.description("All the mandatory fields in the form must be filled up for Create Candidates");
    }
//     @Test(priority = 2)
//    public void listEmp(){
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
//
//        WebElement table = driver.findElement(By.className("oxd-table-body"));
//        List<WebElement> allRows = table.findElements(By.cssSelector("[role=row]"));
//        for (WebElement row: allRows) {
//            List<WebElement> cells =row.findElements(By.cssSelector("[role=row]"));
//            System.out.println(cells.get(4).getText());
//            Assert.assertTrue(cells.get(4).getText().contains("Full-Time Contract"));
//        }
//    }
}
