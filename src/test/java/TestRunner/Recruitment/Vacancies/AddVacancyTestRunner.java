package TestRunner.Recruitment.Vacancies;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Login.LoginPage;
import pages.Recruitment.Vacancies.AddVacancyPage;

import java.io.IOException;

public class AddVacancyTestRunner extends Setup {
    AddVacancyPage addVacancyPage;
    LoginPage loginPage;
    Utils utils;
    String name;
    String hiringManager;
    String invalidHiringManager;
    String description;
    String numberOfPosition;
    String invalidNumberOfPosition;
    String existName;
    String newName;

    public void basicInfo() {
        existName = "Lead SQA Engineer";
        newName = "UI/UX designer";
        name = "SQA Engineer";
        hiringManager = "Abir";
        invalidHiringManager = "124abcd";
        numberOfPosition = "5";
        invalidNumberOfPosition = "asbsf";
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

    @Test(priority = 0, description = "One Mandatory Fields is empty")
    public void oneMandatoryFieldsEmpty() throws InterruptedException {
        Thread.sleep(2000);
        addVacancyPage = new AddVacancyPage(driver);
        basicInfo();
        String isGotErrorMsg = addVacancyPage.oneMandatoryFieldsEmpty(name);
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed atleast for example:Vacancy Name is not given," +
                "then an error message will be thrown to the user 'Required'");
    }

    @Test(priority = 1, description = "Mandatory Fields fill up or not")
    public void mandatoryFieldsShouldFillUp() throws InterruptedException {
        Thread.sleep(2000);
        addVacancyPage = new AddVacancyPage(driver);
        basicInfo();
        String isGotText = addVacancyPage.mandatoryFieldsShouldFillUp(name, hiringManager);
        Assert.assertTrue(isGotText.contains("Edit Vacancy"));
        Allure.description("All the mandatory fields in the form must be filled up for Create Vacancy");
    }

    @Test(priority = 2, description = "Cannot Create Vacancy with already registered vacancy name")
    public void alreadyExistsVacancyName() throws InterruptedException {
        Thread.sleep(2000);
        addVacancyPage = new AddVacancyPage(driver);
        basicInfo();
        String isGotErrorMsg = addVacancyPage.alreadyExistsVacancyName(existName, hiringManager);
        Assert.assertTrue(isGotErrorMsg.contains("Already exists"));
        Allure.description("Already been registered vacancy name cannot be used for creating Vacancy and " +
                "error message will be given.");
    }

    @Test(priority = 3, description = "Hiring Manager with invalid data")
    public void incorrectHiringManager() throws InterruptedException {
        Thread.sleep(2000);
        addVacancyPage = new AddVacancyPage(driver);
        basicInfo();
        String isGotErrorMsg = addVacancyPage.incorrectHiringManager(newName, description, invalidHiringManager, numberOfPosition);
        Assert.assertTrue(isGotErrorMsg.contains("Invalid"));
        Allure.description("Error message will be shown if user inputs invalid data of hiring manager field");
    }

    @Test(priority = 4, description = "Number of position with invalid data")
    public void incorrectNumberOfPosition() throws InterruptedException {
        Thread.sleep(2000);
        addVacancyPage = new AddVacancyPage(driver);
        basicInfo();
        String isGotErrorMsg = addVacancyPage.incorrectNumberOfPosition(newName, description, hiringManager, invalidNumberOfPosition);
        Assert.assertTrue(isGotErrorMsg.contains("Should be a numeric value"));
        Allure.description("Error message will be shown if user inputs invalid data og number of position field");
    }

    @Test(priority = 5, description = "Successful create vacancy")
    public void allFieldFillUpAddVacancy() throws InterruptedException {
        Thread.sleep(2000);
        addVacancyPage = new AddVacancyPage(driver);
        basicInfo();
        String isGotText = addVacancyPage.allFieldFillUpAddVacancy(newName, description, hiringManager, numberOfPosition);
        Assert.assertTrue(isGotText.contains("Edit Vacancy"));
        Allure.description("All fields will be filled on successful vacancy creation");
    }
}
