package TestRunner.Time.Projects;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Login.LoginPage;
import pages.Time.Projects.SearchProjectsPage;

import java.io.IOException;

public class SearchProjectsTestRunner extends Setup {
    LoginPage loginPage;
    SearchProjectsPage searchProjectsPage;
    Utils utils;
    String projectName;
    String customerName;
    String invalidCustomerName;
    String invalidProjectName;
    String admin;
    String invalidAdmin;

    public void basicInfo() {
        customerName = "Internal";
        invalidCustomerName = "Abir";
        projectName = "Recruitment";
        invalidProjectName = "IHB";
        admin= "Abir";
        invalidAdmin ="Vin";
    }
    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        utils = new Utils();
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        utils.getUserCreds(5);
        loginPage.doLoginWithValidCredential(utils.username(), utils.getPassword());
    }
    @Test(priority = 0, description = "Search Project by Invalid Data")
    public void searchWithInvalidData() throws InterruptedException {
        Thread.sleep(2000);
        searchProjectsPage = new SearchProjectsPage(driver);
        basicInfo();
        String isGotErrorMsg = searchProjectsPage.searchWithInvalidData(invalidCustomerName,invalidProjectName,invalidAdmin);
        Assert.assertTrue(isGotErrorMsg.contains("Invalid"));
        Allure.description("Verify that the system prevents project search when an invalid Data is provided.");
    }
    @Test(priority = 1, description = "Search Project by Valid Data")
    public void searchWithValidData() throws InterruptedException {
        Thread.sleep(2000);
        searchProjectsPage = new SearchProjectsPage(driver);
        basicInfo();
        String isGotMsg = searchProjectsPage.searchWithValidData(customerName,projectName,admin);
        Assert.assertTrue(isGotMsg.contains("Record Found"));
        Allure.description("Verify that the system prevents project search when an valid Data is provided.");
    }
}
