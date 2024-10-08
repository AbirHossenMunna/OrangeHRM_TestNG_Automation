package TestRunner.Login;

import Base.Setup;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login.LoginPage;

import java.io.IOException;

public class LoginTestRunner extends Setup {
    Utils utils;
    LoginPage loginPage;

    @Test(priority = 0)
    public void doLoginWithBothBlank() throws IOException, ParseException, InterruptedException {
        utils = new Utils();
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com");
        Thread.sleep(3000);
        utils.getUserCreds(0);
        String isGotText = loginPage.doLoginWithBothBlank(utils.username(), utils.getPassword());
        Assert.assertTrue(isGotText.contains("Required"));
    }

    @Test(priority = 1, description = "User tries to login with blank username but blank password")
    public void doLoginWithBlankUserNameAndValidPassword() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        utils = new Utils();
        utils.getUserCreds(1);
        String ValidationMessage = loginPage.doLoginWithBlankUserNameAndValidPassword(utils.username(), utils.getPassword());
        Assert.assertTrue(ValidationMessage.contains("Required"));
        Allure.description("User tries to login with invalid username and valid password" +
                "User will not be allowed to login and 'Required' will be prompted");
        Thread.sleep(3000);
        loginPage.clearCreds();
    }

    @Test(priority = 2, description = "User tries to login with correct username but blank password")
    public void doLoginWithValidUserNameAndBlankPassword() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        utils = new Utils();
        utils.getUserCreds(2);
        String ValidationMessage = loginPage.doLoginWithValidUserNameAndBlankPassword(utils.username(), utils.getPassword());
        Assert.assertTrue(ValidationMessage.contains("Required"));
        Allure.description("User tries to login with valid username and blank password" +
                "User will not be allowed to login and 'Required' will be prompted");
        Thread.sleep(3000);
        loginPage.clearCreds();
    }
    @Test(priority = 3, description = "User tries to login with incorrect username but correct password")
    public void doLoginWithInvalidUserNameAndValidPassword() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        utils = new Utils();
        utils.getUserCreds(3);
        String isGotText = loginPage.doLoginWithInvalidUserNameAndValidPassword(utils.username(), utils.getPassword());
        Assert.assertTrue(isGotText.contains("Invalid credentials"));
        Allure.description("User tries to login with Invalid username and correct password" +
                "User will not be allowed to login and 'Invalid credentials' will be prompted");
        Thread.sleep(3000);
        loginPage.clearCreds();
    }
    @Test(priority = 4, description = "User tries to login with correct username but incorrect password")
    public void doLoginWithValidUserNameAndInvalidPassword() throws IOException, ParseException, InterruptedException {
        Thread.sleep(3000);
        loginPage = new LoginPage(driver);
        utils = new Utils();
        utils.getUserCreds(4);
        String isGotText = loginPage.doLoginWithValidUserNameAndInvalidPassword(utils.username(), utils.getPassword());
        Assert.assertTrue(isGotText.contains("Invalid credentials"));
        Allure.description("User tries to login with valid username and Incorrect password" +
                "User will not be allowed to login and 'Invalid credentials' will be prompted");
    }

    @Test(priority = 6, description = "User gives valid credentials and login is successful")
    public void doLoginWithValidCredential() throws IOException, ParseException, InterruptedException {
        Thread.sleep(3000);
        loginPage = new LoginPage(driver);
        utils = new Utils();
        utils.getUserCreds(5);
        String isGotText = (loginPage.doLoginWithValidCredential(utils.username(), utils.getPassword()));
        Assert.assertTrue(isGotText.contains("Dashboard"));
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "dashboard";
        Assert.assertTrue(urlActual.contains(urlExpected));
        Allure.description("After giving valid credentials of the user, user will be able to successfully login " +
                "and after login logout button will be displayed");
    }

    @Test(priority = 7, description = "Logout button will be clicked,Login button should be displayed")
    public void signOutPerformed() throws InterruptedException {
        loginPage = new LoginPage(driver);
        boolean isGotText = loginPage.signOutPerformed();
        Assert.assertTrue(isGotText);
        Allure.description("If the user signs out,Login button should be displayed");
    }

    @Test(priority = 8, description = "Forgot Password link visible or not")
    public void forgotPasswordLinkVisibleOrNot() throws InterruptedException {
        Thread.sleep(3000);
        loginPage = new LoginPage(driver);
        boolean forgotPasswordVisibility = loginPage.forgotPasswordLinkVisibleOrNot();
        Assert.assertTrue(forgotPasswordVisibility);
        Allure.description("Checking whether forgot password link is visible or not");
    }

    @Test(priority = 9, description = "Reset Password button visible or not")
    public void resetPasswordButtonVisibleOrNot() throws InterruptedException {
        Thread.sleep(3000);
        loginPage = new LoginPage(driver);
        boolean retrievePasswordVisibility = loginPage.resetPasswordButtonVisibleOrNot();
        Assert.assertTrue(retrievePasswordVisibility);
        Allure.description("Checking whether reset password button is visible or not");
    }

    @Test(priority = 10, description = "Reset password by giving registered username")
    public void resetPassword() throws InterruptedException {
        Thread.sleep(3000);
        loginPage = new LoginPage(driver);
        String isGotButton = loginPage.resetPassword("Abir");
        Assert.assertTrue(isGotButton.contains("Reset Password link sent successfully"));
        Allure.description("The password is reset password by giving registered username and after retrieving password user " +
                "will click back to successfully login again.");

    }
}
