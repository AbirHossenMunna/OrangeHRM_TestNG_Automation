package TestRunner.Recruitment.Vacancies;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Login.LoginPage;
import pages.Recruitment.Vacancies.VacanciesSearchPage;

import java.io.IOException;

public class SearchVacanciesTestRunner extends Setup {
    LoginPage loginPage;
    VacanciesSearchPage vacanciesSearchPage;
    Utils utils;

    @BeforeTest
    public void doLogin() throws InterruptedException, IOException, ParseException {
        utils = new Utils();
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        utils.getUserCreds(5);
        loginPage.doLoginWithValidCredential(utils.username(), utils.getPassword());
    }

    @Test(priority = 0, description = "Job title field is visible or not")
    public void JobTitleFieldVisibleOrNot() throws InterruptedException {
        vacanciesSearchPage = new VacanciesSearchPage(driver);
        boolean jobTitleVisibility = vacanciesSearchPage.JobTitleFieldVisibleOrNot();
        Assert.assertTrue(jobTitleVisibility);
        Allure.description("Checking whether Job title field is visible or not");
    }

    @Test(priority = 1, description = "Search job title")
    public void selectJobTitleWithNoResult() throws InterruptedException {
        Thread.sleep(3000);
        vacanciesSearchPage = new VacanciesSearchPage(driver);
        String isGotText = vacanciesSearchPage.selectJobTitleWithNoResult();
        Assert.assertTrue(isGotText.contains("No Records Found"));
        Allure.description("Search job title but not found any data");
    }

    @Test(priority = 2, description = "Search job title")
    public void doSearchWithJobTitle() throws InterruptedException {
        Thread.sleep(3000);
        vacanciesSearchPage = new VacanciesSearchPage(driver);
        String isGotText = vacanciesSearchPage.doSearchWithJobTitle();
        Assert.assertTrue(isGotText.contains("Records Found"));
        Allure.description("Search job title and showing result for those searching");
    }

    @Test(priority = 3, description = "Vacancy field is visible or not")
    public void vacancyFieldVisibleOrNot() {
        vacanciesSearchPage = new VacanciesSearchPage(driver);
        boolean vacancyVisibility = vacanciesSearchPage.vacancyFieldVisibleOrNot();
        Assert.assertTrue(vacancyVisibility);
        Allure.description("Checking whether Vacancy field is visible or not");
    }

    @Test(priority = 4, description = "Search Vacancy")
    public void doSearchWithVacancy() throws InterruptedException {
        vacanciesSearchPage = new VacanciesSearchPage(driver);
        String isGotText = vacanciesSearchPage.doSearchWithVacancy();
        Assert.assertTrue(isGotText.contains("Record Found"));
        Allure.description("Search Vacancy and showing result for those searching");
    }
}
