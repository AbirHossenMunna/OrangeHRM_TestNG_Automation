package TestRunner.Recruitment.Vacancies;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Login.LoginPage;
import pages.Recruitment.Vacancies.EditVacancyPage;

import java.io.IOException;

public class EditVacancyTestRunner extends Setup {
    LoginPage loginPage;
    EditVacancyPage editVacancyPage;
    Utils utils;
    String existName;
    String invalidHiringManager;
    String invalidNumberOfPosition;
    String name;
    String hiringManager;
    String description;
    String numberOfPosition;

    public void basicInfo() {
        existName = "Senior QA Lead";
        invalidHiringManager = "124abcd";
        invalidNumberOfPosition = "asbsf";
        name = "Software Engineer";
        hiringManager = "Abir";
        numberOfPosition = "2";
        description = "SQA Engineer ensures that the software delivered to users is functional, reliable, and meets the intended requirements";
    }

    @BeforeTest
    public void doLogin() throws InterruptedException, IOException, ParseException {
        utils = new Utils();
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        utils.getUserCreds(5);
        loginPage.doLoginWithValidCredential(utils.username(), utils.getPassword());
    }

    @Test(priority = 0, description = "Update name with Blank Required Fields")
    public void blankEditName() throws InterruptedException {
        Thread.sleep(2000);
        editVacancyPage = new EditVacancyPage(driver);
        editVacancyPage.blankEditName();
        String isGotErrorMsg = editVacancyPage.blankEditName();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("The system displays an error message 'Required' for vacancy name blank field.");
    }

    @Test(priority = 1, description = "Cannot Edit Vacancy with already registered vacancy name")
    public void existEditName() throws InterruptedException {
        Thread.sleep(2000);
        editVacancyPage = new EditVacancyPage(driver);
        basicInfo();
        String isGotErrorMsg = editVacancyPage.existEditName(existName);
        Assert.assertTrue(isGotErrorMsg.contains("Already exists"));
        Allure.description("Already been registered vacancy name cannot be used for editing Vacancy and " +
                "error message will be given.");
    }

    @Test(priority = 2, description = "Select Job title")
    public void selectBlankTitle() throws InterruptedException {
        Thread.sleep(2000);
        editVacancyPage = new EditVacancyPage(driver);
        String isGotErrorMsg = editVacancyPage.selectBlankTitle();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("The system displays an error message 'Required' for job title field.");
    }

    @Test(priority = 3, description = "Update hiring manager name with Invalid Data")
    public void editInvalidManagerName() throws InterruptedException {
        Thread.sleep(2000);
        editVacancyPage = new EditVacancyPage(driver);
        basicInfo();
        String isGotErrorMsg = editVacancyPage.editInvalidManagerName(invalidHiringManager);
        Assert.assertTrue(isGotErrorMsg.contains("Invalid"));
        Allure.description("The system displays an error message 'Invalid' for hiring manager name blank field.");
    }

    @Test(priority = 4, description = "Update hiring manager name with Blank")
    public void blankManagerName() throws InterruptedException {
        Thread.sleep(2000);
        editVacancyPage = new EditVacancyPage(driver);
        String isGotErrorMsg = editVacancyPage.blankManagerName();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("The system displays an error message 'Required' for vacancy name blank field.");
    }

    @Test(priority = 5, description = "Update number of position with Invalid Data")
    public void editInvalidNumberOfPosition() throws InterruptedException {
        Thread.sleep(2000);
        editVacancyPage = new EditVacancyPage(driver);
        basicInfo();
        String isGotErrorMsg = editVacancyPage.editInvalidNumberOfPosition(invalidNumberOfPosition);
        Assert.assertTrue(isGotErrorMsg.contains("Should be a numeric value"));
        Allure.description("The system displays an error message 'Should be a numeric value' for number of position blank field.");
    }

    @Test(priority = 6, description = "Successful edit vacancy")
    public void editVacancy() throws InterruptedException {
        Thread.sleep(2000);
        editVacancyPage = new EditVacancyPage(driver);
        basicInfo();
        String isGotMsg = editVacancyPage.editVacancy(name, description, hiringManager, numberOfPosition);
        Assert.assertTrue(isGotMsg.contains("Edit Vacancy"));
        Allure.description("All fields will be filled on successful vacancy edit");
    }
}
