package pages.PIM;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EditEmployee {
    int length;
    int i;
    @FindBy(tagName = "a")
    List<WebElement> sideBarPim;
    @FindBy(css = ".oxd-icon.bi-pencil-fill")
    List<WebElement> editIcon;
    @FindBy(name = "firstName")
    WebElement txtFirstName;
    @FindBy(name = "lastName")
    WebElement txtLastName;
    @FindBy(tagName = "input")
    public List<WebElement> txtId;
    @FindBy(className = "oxd-select-text-input")
    List<WebElement> dropdowns;
    @FindBy(tagName = "input")
    List<WebElement> dob;
    @FindBy(tagName = "span")
    List<WebElement> radioGender;
    @FindBy(css = "[type=submit]")
    List<WebElement> btnSave;
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement lblErrorMsg;
    @FindBy(xpath = "//div[contains(text(),'Abir Hossen')]")
    public WebElement tableValidationByName;
    @FindBy(xpath = "//div[contains(text(),'123568')]")
    public WebElement tableValidationById;
    @FindBy(xpath = "//div[contains(text(),'Leo')]")
    public WebElement editTableValidationByName;
    @FindBy(xpath = "//div[contains(text(),'1781')]")
    public WebElement editTableValidationById;
    @FindBy(tagName = "button")
    List<WebElement> btnAdd;
    @FindBy(css = "[type=button]")
    List<WebElement> btnCancel;


    public EditEmployee(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Test Steps
    public String mandatoryFieldsShouldBlank() throws InterruptedException {
        sideBarPim.get(2).click();
        editIcon.get(1).click();
        Thread.sleep(3000);
        StringBuilder clearFirstName = new StringBuilder(String.valueOf(txtFirstName));
        length = clearFirstName.length();
        for (i = 0; i <= length; i++) {
            txtFirstName.sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(3000);
        StringBuilder clearLastName = new StringBuilder(String.valueOf(txtLastName));
        length = clearLastName.length();
        for (i = 0; i <= length; i++) {
            txtLastName.sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(3000);
        btnSave.get(0).click();
        Thread.sleep(2000);
        return lblErrorMsg.getText();
    }

    public void mandatoryFieldsFillUp(String firstname, String lastname) throws InterruptedException {
        Thread.sleep(3000);
        txtFirstName.sendKeys(firstname);
        Thread.sleep(3000);
        txtLastName.sendKeys(lastname);
        btnSave.get(0).click();
    }

    public String attachmentFieldShouldBlank() throws InterruptedException {
        Thread.sleep(3000);
        editIcon.get(1).click();
        Thread.sleep(3000);
        btnAdd.get(5).click();
        Thread.sleep(3000);
        btnSave.get(2).click();
        return lblErrorMsg.getText();
    }

    public void editWithId(String id) throws InterruptedException {
        btnCancel.get(1).click();
        Thread.sleep(4000);
        StringBuilder clearId = new StringBuilder(String.valueOf(txtId));
        length = clearId.length();
        for (i = 0; i <= length; i++) {
            txtId.get(4).sendKeys(Keys.BACK_SPACE);
        }
        txtId.get(4).sendKeys(id);
        btnSave.get(0).click();
    }

    public void editPersonalDetails(String firstname, String lastname, String id) throws InterruptedException {
        sideBarPim.get(2).click();
        Thread.sleep(3000);
        editIcon.get(1).click();
        StringBuilder clearFirstName = new StringBuilder(String.valueOf(txtFirstName));
        length = clearFirstName.length();
        for (i = 0; i <= length; i++) {
            txtFirstName.sendKeys(Keys.BACK_SPACE);
        }
        txtFirstName.sendKeys(firstname);
        StringBuilder clearLastName = new StringBuilder(String.valueOf(txtLastName));
        length = clearLastName.length();
        for (i = 0; i <= length; i++) {
            txtLastName.sendKeys(Keys.BACK_SPACE);
        }
        txtLastName.sendKeys(lastname);
        StringBuilder clearId = new StringBuilder(String.valueOf(txtId));
        length = clearId.length();
        for (i = 0; i <= length; i++) {
            txtId.get(4).sendKeys(Keys.BACK_SPACE);
        }
        txtId.get(4).sendKeys(id);

        //Nationality
        dropdowns.get(0).click();
        Thread.sleep(3000);
        for (int i = 0; i < 15; i++) {  // Adjust this based on how many options you want to scroll through
            dropdowns.get(0).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdowns.get(0).sendKeys(Keys.ENTER);

        //Marital Status
        dropdowns.get(1).click();
        Thread.sleep(3000);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            dropdowns.get(1).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdowns.get(1).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        //Gender
        radioGender.get(15).click();
        Thread.sleep(3000);

        //Save
        btnSave.get(0).click();
    }
}
