package TestRunner.Recruitment.Candidates;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Login.LoginPage;
import pages.Recruitment.Candidates.CandidatesSearchPage;

import java.io.IOException;

public class CandidatesSearchTestRunner extends Setup {
    CandidatesSearchPage candidatesSearchPage;
    LoginPage loginPage;
    Utils utils;
    String name = "Taher";
    int i;
    int length;

    public void clearText() {
        StringBuilder extEmail = new StringBuilder(String.valueOf(candidatesSearchPage.txtCandidatesName));
        length = extEmail.length();
        for (i = 0; i <= length; i++) {
            candidatesSearchPage.txtCandidatesName.get(1).sendKeys(Keys.BACK_SPACE);
        }
    }

    @BeforeTest
    public void doLogin() throws InterruptedException, IOException, ParseException {
        utils = new Utils();
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        utils.getUserCreds(5);
        loginPage.doLoginWithValidCredential(utils.username(), utils.getPassword());
    }

    @Test(priority = 0, description = "Search button is visible or not")
    public void searchButtonVisibleOrNot() throws InterruptedException {
        Thread.sleep(3000);
        candidatesSearchPage = new CandidatesSearchPage(driver);
        boolean searchButtonVisibility = candidatesSearchPage.searchButtonVisibleOrNot();
        Assert.assertTrue(searchButtonVisibility);
        Allure.description("Checking whether search button is visible or not");
    }

    @Test(priority = 1, description = "Search with invalid data")
    public void invalidSearchName() throws InterruptedException {
        candidatesSearchPage = new CandidatesSearchPage(driver);
        String response = candidatesSearchPage.incorrectSearchName("skjdfhsdkjfh");
        Assert.assertTrue(response.contains("Invalid"));
        Allure.description("'Invalid' will be shown if user inputs invalid keyword");
    }

    @Test(priority = 2, description = "Search with numeric data")
    public void numericSearchName() throws InterruptedException {
        candidatesSearchPage = new CandidatesSearchPage(driver);
        clearText();
        candidatesSearchPage.btnReset.click();
        String response = candidatesSearchPage.incorrectSearchName("12345");
        Assert.assertTrue(response.contains("Invalid"));
        Allure.description("If user inputs numeric keywords into the search field, Invalid message will show.");
    }

    @Test(priority = 3, description = "Search with caseInsensitive data")
    public void caseInsensitiveSearchName() throws InterruptedException {
        candidatesSearchPage = new CandidatesSearchPage(driver);
        clearText();
        candidatesSearchPage.btnReset.click();
        String response = candidatesSearchPage.incorrectSearchName("ABiR");
        Assert.assertTrue(response.contains("Invalid"));
        Allure.description("If user inputs numeric keywords into the search field, Invalid message will show.");
    }

    @Test(priority = 4, description = "User can search name")
    public void searchProduct() throws InterruptedException {
        candidatesSearchPage = new CandidatesSearchPage(driver);
        Thread.sleep(2000);
        String res = candidatesSearchPage.correctSearchName(name);
        Thread.sleep(3000);
        Assert.assertTrue(res.contains("Record Found"));
        Allure.description("User can search name successfully by giving valid input");
    }

    @Test(priority = 5, description = "User can search product by pressing the Enter Button")
    public void searchProductByEnterButton() throws InterruptedException {
        candidatesSearchPage = new CandidatesSearchPage(driver);
        Thread.sleep(2000);
        String res = candidatesSearchPage.searchNameByEnterButton(name);
        Thread.sleep(2000);
        Assert.assertTrue(res.contains("Record Found"));
        Allure.description("Verifying that after giving valid data to the search field,user can get data by entering Enter key.");
    }

    @Test(priority = 6, description = "Search status with select dropdown")
    public void doSearchWithSelectDropdown() throws InterruptedException {
        Thread.sleep(3000);
        candidatesSearchPage = new CandidatesSearchPage(driver);
        String isGotText = candidatesSearchPage.doSearchWithSelectDropdown();
        Thread.sleep(3000);
        Assert.assertTrue(isGotText.contains("Records Found"));
        Allure.description("Checking whether search button is visible or not");
    }
}
