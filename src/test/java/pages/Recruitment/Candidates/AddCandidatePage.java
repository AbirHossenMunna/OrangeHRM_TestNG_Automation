package pages.Recruitment.Candidates;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddCandidatePage {
    int length;
    int i;
    @FindBy(tagName = "li")
    List<WebElement> sideBarRecruitment;
    @FindBy(tagName = "button")
    List<WebElement> btnAdd;
    @FindBy(name = "firstName")
    WebElement txtFirstName;
    @FindBy(name = "lastName")
    WebElement txtLastName;
    @FindBy(className = "oxd-select-text-input")
    WebElement dropDownVacancy;
    @FindBy(tagName = "input")
    List<WebElement> txtEmail;
    //    @FindBy(css = ".oxd-input.oxd-input--active")
    @FindBy(tagName = "input")
    List<WebElement> txtContactNumber;
    @FindBy(tagName = "input")
    List<WebElement> calenderDateOfApplication;
    @FindBy(tagName = "textarea")
    WebElement txtNotes;
    @FindBy(tagName = "i")
    List<WebElement> checkbox;
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement lblErrorMsg;
    @FindBy(css = ".oxd-text.oxd-text--h6.orangehrm-main-title")
    List<WebElement> lblAddValidation;
    @FindBy(css = "[type=submit]")
    WebElement btnSave;
    @FindBy(tagName = "button")
    List<WebElement> btnCancel;
    @FindBy(xpath = "//div[contains(text(),'Md.Abir Hossen  Munna')]")
    public WebElement tableValidation;

    public AddCandidatePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Test Steps
    public String oneMandatoryFieldsEmpty(String firstName, String lastName, String blankEmail) throws InterruptedException {
        sideBarRecruitment.get(4).click();
        btnAdd.get(6).click();
        Thread.sleep(3000);
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        txtEmail.get(4).sendKeys(blankEmail);
        btnSave.click();
        return lblErrorMsg.getText();
    }

    public String mandatoryFieldsShouldFillUp(String firstName, String lastName, String email) throws InterruptedException {
        btnCancel.get(3).click();
        btnAdd.get(6).click();
        Thread.sleep(5000);
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        Thread.sleep(2000);
        txtEmail.get(4).sendKeys(email);
        Thread.sleep(2000);
        btnSave.click();
        Thread.sleep(5000);
        return lblAddValidation.get(1).getText();
    }

    public String addWithInvalidEmail(String firstName, String lastName, String invalidEmail) throws InterruptedException {
        btnAdd.get(6).click();
        Thread.sleep(3000);
        txtFirstName.sendKeys(firstName);
        Thread.sleep(1000);
        txtLastName.sendKeys(lastName);
        Thread.sleep(3000);
        txtEmail.get(4).sendKeys(invalidEmail);
        Thread.sleep(2000);
        btnSave.click();
        return lblErrorMsg.getText();
    }

    public String addWithInvalidPhoneNUmber(String firstName, String lastName, String email, String invalidContactNumber) throws InterruptedException {
        btnCancel.get(3).click();
        btnAdd.get(6).click();
        Thread.sleep(2000);
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        Thread.sleep(2000);
        txtEmail.get(4).sendKeys(email);
        Thread.sleep(2000);
        txtContactNumber.get(5).sendKeys(invalidContactNumber);
        Thread.sleep(2000);
        btnSave.click();
        return lblErrorMsg.getText();
    }

    public String invalidFormatDateOfApplication(String firstName, String lastName, String email, String contactNumber) throws InterruptedException {
        btnCancel.get(3).click();
        btnAdd.get(6).click();
        Thread.sleep(2000);
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        Thread.sleep(2000);
        txtEmail.get(4).sendKeys(email);
        Thread.sleep(2000);
        txtContactNumber.get(5).sendKeys(contactNumber);
        Thread.sleep(2000);
        Thread.sleep(2000);
        StringBuilder cleanDate = new StringBuilder(String.valueOf(calenderDateOfApplication));
        length = cleanDate.length();
        for (i = 0; i <= length; i++) {
            calenderDateOfApplication.get(8).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        calenderDateOfApplication.get(8).sendKeys("10-12-2024");
        Thread.sleep(2000);
        return lblErrorMsg.getText();
    }

    public String allFieldFillUpAddCandidates(String firstName, String lastName, String email, String contactNumber, String notes) throws InterruptedException {
        btnCancel.get(3).click();
        btnAdd.get(6).click();
        Thread.sleep(3000);
        txtFirstName.sendKeys(firstName);
        Thread.sleep(1000);
        txtLastName.sendKeys(lastName);
        Thread.sleep(2000);
        dropDownVacancy.click();
        for (int i = 0; i < 5; i++) {  // Adjust this based on how many options you want to scroll through
            dropDownVacancy.sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropDownVacancy.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        txtEmail.get(4).sendKeys(email);
        Thread.sleep(2000);
        txtContactNumber.get(5).sendKeys(contactNumber);
        Thread.sleep(2000);
        txtNotes.sendKeys(notes);
        Thread.sleep(2000);
        checkbox.get(8).click();
        Thread.sleep(2000);
        btnSave.click();
        Thread.sleep(5000);
        return lblAddValidation.get(1).getText();
    }
}
