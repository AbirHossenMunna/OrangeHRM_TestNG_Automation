package pages.Login;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    int i;
    int length;
    @FindBy(name = "username")
    WebElement txtUserName;
    @FindBy(name = "password")
    WebElement txtPassword;
    @FindBy(css = "[type=submit]")
    WebElement btnLogin;
    @FindBy(tagName = "span")
    WebElement inLineErrorMsg;
    @FindBy(xpath = "//header/div[1]/div[1]/span[1]/h6[1]")
    public WebElement lblDashboard;
    @FindBy(tagName = "p")
    WebElement errorMsg;
    @FindBy(className = "oxd-userdropdown-img")
    WebElement imgProfile;
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    WebElement linkLogout;
    @FindBy(tagName = "p")
    List<WebElement> linkForgetPassword;
    @FindBy(css = "[type=submit]")
    WebElement btnResetPassword;
    @FindBy(name = "username")
    WebElement txtResetName;
    @FindBy(tagName = "h6")
    WebElement lblResetPasswordSuccessfullyMsg;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
    // Test Step
    public String doLoginWithBothBlank(String username, String password) throws InterruptedException {
        Thread.sleep(3000);
        txtUserName.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
        return inLineErrorMsg.getText();
    }

    public String doLoginWithBlankUserNameAndValidPassword(String username, String password) throws InterruptedException {
        txtUserName.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
        return inLineErrorMsg.getText();
    }

    public String doLoginWithValidUserNameAndBlankPassword(String username, String password) throws InterruptedException {
        txtUserName.sendKeys(username);
        StringBuilder loop = new StringBuilder(String.valueOf(txtPassword));
        length = loop.length();
        for (i = 0; i <= length; i++) {
            txtPassword.sendKeys(Keys.BACK_SPACE);
        }
        txtPassword.sendKeys(password);
        btnLogin.click();
        return inLineErrorMsg.getText();
    }

    public String doLoginWithInvalidUserNameAndValidPassword(String username, String password) throws InterruptedException {
        StringBuilder userName = new StringBuilder(String.valueOf(txtUserName));
        length = userName.length();
        for (i = 0; i <= length; i++) {
            txtUserName.sendKeys(Keys.BACK_SPACE);
        }
        txtUserName.sendKeys(username);
        StringBuilder pass = new StringBuilder(String.valueOf(txtPassword));
        length = pass.length();
        for (i = 0; i <= length; i++) {
            txtPassword.sendKeys(Keys.BACK_SPACE);
        }
        txtPassword.sendKeys(password);
        btnLogin.click();
        return errorMsg.getText();
    }

    public String doLoginWithValidUserNameAndInvalidPassword(String username, String password) throws InterruptedException {
        StringBuilder userName = new StringBuilder(String.valueOf(txtUserName));
        length = userName.length();
        for (i = 0; i <= length; i++) {
            txtUserName.sendKeys(Keys.BACK_SPACE);
        }
        txtUserName.sendKeys(username);
        StringBuilder pass = new StringBuilder(String.valueOf(txtPassword));
        length = pass.length();
        for (i = 0; i <= length; i++) {
            txtPassword.sendKeys(Keys.BACK_SPACE);
        }
        txtPassword.sendKeys(password);
        btnLogin.click();
        return errorMsg.getText();
    }

    public String doLoginWithValidCredential(String username, String password) {
        StringBuilder userName = new StringBuilder(String.valueOf(txtUserName));
        length = userName.length();
        for (i = 0; i <= length; i++) {
            txtUserName.sendKeys(Keys.BACK_SPACE);
        }
        txtUserName.sendKeys(username);
        StringBuilder pass = new StringBuilder(String.valueOf(txtPassword));
        length = pass.length();
        for (i = 0; i <= length; i++) {
            txtPassword.sendKeys(Keys.BACK_SPACE);
        }
        txtPassword.sendKeys(password);
        btnLogin.click();
        return lblDashboard.getText();
    }

    public boolean signOutPerformed() throws InterruptedException {
        imgProfile.click();
        Thread.sleep(1500);
        linkLogout.click();
        return btnLogin.isDisplayed();
    }

    public boolean forgotPasswordLinkVisibleOrNot() throws InterruptedException {
        Thread.sleep(1000);
        return linkForgetPassword.get(2).isDisplayed();
    }

    public boolean resetPasswordButtonVisibleOrNot() throws InterruptedException {
        linkForgetPassword.get(2).click();
        Thread.sleep(1000);
        return btnResetPassword.isDisplayed();
    }

    public String resetPassword(String username) throws InterruptedException {
        txtResetName.sendKeys(username);
        Thread.sleep(1000);
        btnResetPassword.click();
        return lblResetPasswordSuccessfullyMsg.getText();
    }
}
