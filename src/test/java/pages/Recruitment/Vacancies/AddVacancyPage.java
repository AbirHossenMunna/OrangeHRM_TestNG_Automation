package pages.Recruitment.Vacancies;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddVacancyPage {
    @FindBy(tagName = "li")
    List<WebElement> sideBarRecruitment;
    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> linkVacancies;
    @FindBy(tagName = "button")
    List<WebElement> btnAdd;
    @FindBy(tagName = "input")
    List<WebElement> txtName;
    @FindBy(className = "oxd-select-text-input")
    WebElement dropdownJobTitle;
    @FindBy(tagName = "textarea")
    WebElement txtDescription;
    @FindBy(tagName = "input")
    List<WebElement> txtHiringManager;
    @FindBy(tagName = "input")
    List<WebElement> txtNumberOfPosition;
    @FindBy(css = "[type=submit]")
    WebElement btnSave;
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement lblErrorMsg;
    @FindBy(tagName = "button")
    List<WebElement> btnCancel;
    @FindBy(tagName = "h6")
    List<WebElement> lblAddValidation;

    public AddVacancyPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Test Steps
    public String oneMandatoryFieldsEmpty(String name) throws InterruptedException {
        sideBarRecruitment.get(4).click();
        linkVacancies.get(1).click();
        btnAdd.get(6).click();
        Thread.sleep(2000);
        txtName.get(1).sendKeys(name);
        dropdownJobTitle.click();
        Thread.sleep(3000);
        for (int i = 0; i < 18; i++) {  // Adjust this based on how many options you want to scroll through
            dropdownJobTitle.sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdownJobTitle.sendKeys(Keys.ENTER);
        btnSave.click();
        return lblErrorMsg.getText();
    }

    public String mandatoryFieldsShouldFillUp(String name, String hiringManager) throws InterruptedException {
        btnCancel.get(3).click();
        btnAdd.get(6).click();
        Thread.sleep(2000);
        txtName.get(1).sendKeys(name);
        dropdownJobTitle.click();
        Thread.sleep(3000);
        for (int i = 0; i < 14; i++) {  // Adjust this based on how many options you want to scroll through
            dropdownJobTitle.sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdownJobTitle.sendKeys(Keys.ENTER);
        txtHiringManager.get(2).sendKeys(hiringManager);
        Thread.sleep(3000);
        txtHiringManager.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        txtHiringManager.get(2).sendKeys(Keys.ENTER);
        btnSave.click();
        Thread.sleep(5000);
        return lblAddValidation.get(1).getText();
    }

    public String alreadyExistsVacancyName(String existName, String hiringManager) throws InterruptedException {
        btnCancel.get(3).click();
        btnAdd.get(6).click();
        Thread.sleep(2000);
        txtName.get(1).sendKeys(existName);
        dropdownJobTitle.click();
        Thread.sleep(3000);
        for (int i = 0; i < 10; i++) {  // Adjust this based on how many options you want to scroll through
            dropdownJobTitle.sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdownJobTitle.sendKeys(Keys.ENTER);
        txtHiringManager.get(2).sendKeys(hiringManager);
        Thread.sleep(3000);
        txtHiringManager.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        txtHiringManager.get(2).sendKeys(Keys.ENTER);
        btnSave.click();
        return lblErrorMsg.getText();
    }

    public String incorrectHiringManager(String name, String description, String invalidHiringManager, String numberOfPosition) throws InterruptedException {
        btnCancel.get(3).click();
        btnAdd.get(6).click();
        Thread.sleep(2000);
        txtName.get(1).sendKeys(name);
        dropdownJobTitle.click();
        Thread.sleep(3000);
        for (int i = 0; i < 10; i++) {  // Adjust this based on how many options you want to scroll through
            dropdownJobTitle.sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdownJobTitle.sendKeys(Keys.ENTER);
        txtDescription.sendKeys(description);
        txtHiringManager.get(2).sendKeys(invalidHiringManager);
        Thread.sleep(3000);
        txtHiringManager.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        txtHiringManager.get(2).sendKeys(Keys.ENTER);
        txtNumberOfPosition.get(3).sendKeys(numberOfPosition);
        btnSave.click();
        return lblErrorMsg.getText();
    }

    public String incorrectNumberOfPosition(String name, String description, String hiringManager, String invalidNumberOfPosition) throws InterruptedException {
        btnCancel.get(3).click();
        btnAdd.get(6).click();
        Thread.sleep(2000);
        txtName.get(1).sendKeys(name);
        dropdownJobTitle.click();
        Thread.sleep(3000);
        for (int i = 0; i < 10; i++) {  // Adjust this based on how many options you want to scroll through
            dropdownJobTitle.sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdownJobTitle.sendKeys(Keys.ENTER);
        txtDescription.sendKeys(description);
        txtHiringManager.get(2).sendKeys(hiringManager);
        Thread.sleep(3000);
        txtHiringManager.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        txtHiringManager.get(2).sendKeys(Keys.ENTER);
        txtNumberOfPosition.get(3).sendKeys(invalidNumberOfPosition);
        btnSave.click();
        return lblErrorMsg.getText();
    }

    public String allFieldFillUpAddVacancy(String name, String description, String hiringManager, String numberOfPosition) throws InterruptedException {
        btnCancel.get(3).click();
        btnAdd.get(6).click();
        Thread.sleep(2000);
        txtName.get(1).sendKeys(name);
        dropdownJobTitle.click();
        Thread.sleep(3000);
        for (int i = 0; i < 10; i++) {  // Adjust this based on how many options you want to scroll through
            dropdownJobTitle.sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdownJobTitle.sendKeys(Keys.ENTER);
        txtDescription.sendKeys(description);
        txtHiringManager.get(2).sendKeys(hiringManager);
        Thread.sleep(3000);
        txtHiringManager.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        txtHiringManager.get(2).sendKeys(Keys.ENTER);
        txtNumberOfPosition.get(3).sendKeys(numberOfPosition);
        btnSave.click();
        Thread.sleep(5000);
        return lblAddValidation.get(1).getText();
    }
}
