package TestRunner.PIM;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Login.LoginPage;
import pages.PIM.SearchEmployee;

import java.io.IOException;

public class SearchEmpTestRunner extends Setup {
    SearchEmployee searchEmployee;
    Utils utils;
    LoginPage loginPage;
    String invalidName;
    String validName;
    String invalidId;
    String validId;

    public void basicInfo() {
        invalidName = "Aftab";
        validName = "Abir";
        invalidId = "1009";
        validId = "2024";
    }

    @BeforeTest
    public void doLogin() throws InterruptedException, IOException, ParseException {
        utils = new Utils();
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        utils.getUserCreds(5);
        loginPage.doLoginWithValidCredential(utils.username(), utils.getPassword());
    }

    @Test(priority = 0, description = "Search for an employee by entering an invalid name")
    public void searchEmployeeByInvalidName() throws InterruptedException {
        searchEmployee = new SearchEmployee(driver);
        basicInfo();
        String isGotTxt = searchEmployee.searchEmployeeByInvalidName(invalidName);
        Assert.assertTrue(isGotTxt.contains("No Records Found"));
        Allure.description("Verify that the system handles searches for invalid employee names gracefully by returning" +
                " 'No Records Found' and without causing any errors or crashes.");
    }

    @Test(priority = 1, description = "Searching for an Employee by Valid Name")
    public void searchEmployeeByValidName() throws InterruptedException {
        searchEmployee = new SearchEmployee(driver);
        basicInfo();
        String isGotTxt = searchEmployee.searchEmployeeByValidName(validName);
        Assert.assertTrue(isGotTxt.contains("Record Found"));
        Allure.description("Verify that the system correctly searches and displays results for a valid employee name," +
                "showing relevant employee details without any errors.");
    }

    @Test(priority = 2, description = "Searching for an Employee by Invalid ID")
    public void searchEmployeeByInvalidId() throws InterruptedException {
        searchEmployee = new SearchEmployee(driver);
        basicInfo();
        String isGotTxt = searchEmployee.searchEmployeeByInvalidId(invalidId);
        Assert.assertTrue(isGotTxt.contains("No Records Found"));
        Allure.description("Verify that the system handles searches for invalid employee Id gracefully by returning" +
                " 'No Records Found' and without causing any errors or crashes.");
    }

    @Test(priority = 3, description = "Searching for an Employee by Valid Name")
    public void searchEmployeeByValidId() throws InterruptedException {
        searchEmployee = new SearchEmployee(driver);
        basicInfo();
        String isGotTxt = searchEmployee.searchEmployeeByValidId(validId);
        Assert.assertTrue(isGotTxt.contains("Record Found"));
        Allure.description("Verify that the system correctly searches and displays results for a valid employee id," +
                "showing relevant employee details without any errors.");
    }

    @Test(priority = 4, description = "Handling Searches with No Results")
    public void searchWithNoResult() throws InterruptedException {
        searchEmployee = new SearchEmployee(driver);
        basicInfo();
        String isGotTxt = searchEmployee.searchWithNoResult(invalidName, validId);
        Assert.assertTrue(isGotTxt.contains("No Records Found"));
        Allure.description("Verify the system's response when a search yields no results, ensuring that a" +
                " 'No Records Found' message is displayed and the user interface remains stable.");
    }

    @Test(priority = 5, description = "Searching for an Employee")
    public void searchEmployee() throws InterruptedException {
        searchEmployee = new SearchEmployee(driver);
        basicInfo();
        String isGotTxt = searchEmployee.searchEmployee(validName, validId);
        Assert.assertTrue(isGotTxt.contains("Record Found"));
        Allure.description("Verify that the system correctly searches and displays results for a valid employee," +
                "showing relevant employee details without any errors.");
    }
}
