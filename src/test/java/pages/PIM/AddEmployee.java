package pages.PIM;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddEmployee {
    int length;
    int i;
    @FindBy(tagName = "a")
    List<WebElement> sideBarPim;
    @FindBy(tagName = "button")
    List<WebElement> btnAdd;
    @FindBy(css = "[type=submit]")
    WebElement btnSave;
    @FindBy(name = "firstName")
    WebElement txtFirstName;
    @FindBy(name = "lastName")
    WebElement txtLastName;
    @FindBy(xpath = "//a[contains(text(),'Add Employee')]")
    WebElement navBarAddEmployee;
    @FindBy(tagName = "input")
    public List<WebElement> txtId;
    @FindBy(css = ".oxd-switch-input.oxd-switch-input--active.--label-right")
    WebElement btnToggle;
    @FindBy(tagName = "input")
    List<WebElement> txtUserCreds;
    @FindBy(css = ".orangehrm-main-title")
    public List<WebElement> headerTitle;
    //    @FindBy(css = ".oxd-input-field-error-message")
//    public WebElement lblValidationError;
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement lblErrorMsg;
    @FindBy(tagName = "h6")
    List<WebElement> lblAddValidation;
    @FindBy(xpath = "//div[contains(text(),'Abir')]")
    public WebElement tableValidation;

    public AddEmployee(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String mandatoryFieldsShouldBlankWithoutLoginDetails() throws InterruptedException {
        sideBarPim.get(2).click();
        Thread.sleep(3000);
        btnAdd.get(6).click();
        Thread.sleep(3000);
        StringBuilder cleanDate = new StringBuilder(String.valueOf(txtId));
        length = cleanDate.length();
        for (i = 0; i <= length; i++) {
            txtId.get(5).sendKeys(Keys.BACK_SPACE);
        }
        btnSave.click();
        Thread.sleep(3000);
        return lblErrorMsg.getText();
    }

    public String mandatoryFieldsFillUpWithoutLoginDetails(String firstName, String lastName) throws InterruptedException {
        Thread.sleep(3000);
        txtFirstName.sendKeys(firstName);
        Thread.sleep(3000);
        txtLastName.sendKeys(lastName);
        btnSave.click();
        Thread.sleep(5000);
        return lblAddValidation.get(2).getText();
    }

    public String alreadyExistsId(String firstName, String lastName, String id) throws InterruptedException {
        navBarAddEmployee.click();
        Thread.sleep(3000);
        txtFirstName.sendKeys(firstName);
        Thread.sleep(3000);
        txtLastName.sendKeys(lastName);
        Thread.sleep(3000);
        StringBuilder cleanDate = new StringBuilder(String.valueOf(txtId));
        length = cleanDate.length();
        for (i = 0; i <= length; i++) {
            txtId.get(5).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        txtId.get(5).sendKeys(id);
        btnSave.click();
        Thread.sleep(2000);
        return lblErrorMsg.getText();
    }

    public String passwordLengthValidation(String id, String username, String password, String cPass) throws InterruptedException {
        Thread.sleep(3000);
        StringBuilder cleanDate = new StringBuilder(String.valueOf(txtId));
        length = cleanDate.length();
        for (i = 0; i <= length; i++) {
            txtId.get(5).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        txtId.get(5).sendKeys(id);
        btnToggle.click();
        txtUserCreds.get(7).sendKeys(username);
        txtUserCreds.get(10).sendKeys(password);
        txtUserCreds.get(11).sendKeys(cPass);
        return lblErrorMsg.getText(); //Should have at least 7 characters
    }

    public String passwordMismatchWithLogin(String firstName, String lastName, String id, String username, String password, String cPass)
            throws InterruptedException {
        navBarAddEmployee.click();
        Thread.sleep(3000);
        txtFirstName.sendKeys(firstName);
        Thread.sleep(3000);
        txtLastName.sendKeys(lastName);
        Thread.sleep(3000);
        StringBuilder cleanDate = new StringBuilder(String.valueOf(txtId));
        length = cleanDate.length();
        for (i = 0; i <= length; i++) {
            txtId.get(5).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        txtId.get(5).sendKeys(id);
        btnToggle.click();
        txtUserCreds.get(7).sendKeys(username);
        txtUserCreds.get(10).sendKeys(password);
        txtUserCreds.get(11).sendKeys(cPass);
        return lblErrorMsg.getText(); //Passwords do not match
    }

    public String createEmployee(String firstName, String lastName, String id, String username, String password, String cPass) throws InterruptedException {
        navBarAddEmployee.click();
        Thread.sleep(3000);
        txtFirstName.sendKeys(firstName);
        Thread.sleep(3000);
        txtLastName.sendKeys(lastName);
        Thread.sleep(3000);
        StringBuilder cleanDate = new StringBuilder(String.valueOf(txtId));
        length = cleanDate.length();
        for (i = 0; i <= length; i++) {
            txtId.get(5).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        txtId.get(5).sendKeys(id);
        btnToggle.click();
        txtUserCreds.get(7).sendKeys(username);
        txtUserCreds.get(10).sendKeys(password);
        txtUserCreds.get(11).sendKeys(cPass);
        btnSave.click();
        Thread.sleep(5000);
        return lblAddValidation.get(2).getText();
    }
//    public String checkIfUserExists(String username){
//        btnAddEmployee.get(2).click();
//        btnToggle.click();
//        txtUserCreds.get(5).sendKeys(username);
//        txtUserCreds.get(5).clear();
//        return lblValidationError.getText();
//    }
}
