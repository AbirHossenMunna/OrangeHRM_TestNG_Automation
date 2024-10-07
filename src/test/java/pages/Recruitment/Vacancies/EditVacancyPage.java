package pages.Recruitment.Vacancies;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EditVacancyPage {
    int i;
    int length;
    @FindBy(tagName = "li")
    List<WebElement> sideBarRecruitment;
    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> linkVacancies;
    @FindBy(css = ".oxd-icon.bi-pencil-fill")
    List<WebElement> iconEdit;
    @FindBy(tagName = "input")
    List<WebElement> txtName;
    @FindBy(className = "oxd-select-text-input")
    WebElement dropdownJobTitle;
    @FindBy(xpath = "//div[text()='-- Select --']")
    WebElement dropdownDefaultValue;
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
    @FindBy(tagName = "label")
    List<WebElement> lblHiringManager;

    public EditVacancyPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Test Steps
    public String blankEditName() throws InterruptedException {
        sideBarRecruitment.get(4).click();
        linkVacancies.get(1).click();
        iconEdit.get(6).click();
        Thread.sleep(1000);
        StringBuilder cleanName = new StringBuilder(String.valueOf(txtName));
        length = cleanName.length();
        for (i = 0; i <= length; i++) {
            txtName.get(1).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(1000);
        btnSave.click();
        return lblErrorMsg.getText();
    }

    public String existEditName(String existName) throws InterruptedException {
        btnCancel.get(3).click();
        iconEdit.get(6).click();
        Thread.sleep(2000);
        StringBuilder cleanName = new StringBuilder(String.valueOf(txtName));
        length = cleanName.length();
        for (i = 0; i <= length; i++) {
            txtName.get(1).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        txtName.get(1).sendKeys(existName);
        btnSave.click();
        return lblErrorMsg.getText();
    }

    //Job Title
    public String selectBlankTitle() throws InterruptedException {
        btnCancel.get(3).click();
        iconEdit.get(6).click();
        dropdownJobTitle.click();
        Thread.sleep(3000);
        dropdownDefaultValue.click();
        Thread.sleep(1000);
        btnSave.click();
        return lblErrorMsg.getText();
    }

    //Hiring Manager
    public String editInvalidManagerName(String invalidHiringManager) {
        btnCancel.get(3).click();
        iconEdit.get(6).click();
        txtHiringManager.get(2).sendKeys(invalidHiringManager);
        lblHiringManager.get(3).click();
        btnSave.click();
        return lblErrorMsg.getText();
    }

    public String blankManagerName() throws InterruptedException {
        btnCancel.get(3).click();
        iconEdit.get(6).click();
        StringBuilder cleanManagerName = new StringBuilder(String.valueOf(txtHiringManager));
        length = cleanManagerName.length();
        for (i = 0; i <= length; i++) {
            txtHiringManager.get(2).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(1000);
        btnSave.click();
        return lblErrorMsg.getText();
    }

    //Number of Position
    public String editInvalidNumberOfPosition(String numberOfPosition) {
        btnCancel.get(3).click();
        iconEdit.get(6).click();
        StringBuilder cleanNumber = new StringBuilder(String.valueOf(txtNumberOfPosition));
        length = cleanNumber.length();
        for (i = 0; i <= length; i++) {
            txtNumberOfPosition.get(3).sendKeys(Keys.BACK_SPACE);
        }
        txtNumberOfPosition.get(3).sendKeys(numberOfPosition);
        btnSave.click();
        return lblErrorMsg.getText();
    }

    //Edit vacancy to apply valid data
    public String editVacancy(String name, String description, String hiringManager, String numberOfPosition) throws InterruptedException {
        btnCancel.get(3).click();
        iconEdit.get(6).click();

        //Vacancy Name
        StringBuilder cleanName = new StringBuilder(String.valueOf(txtName));
        length = cleanName.length();
        for (i = 0; i <= length; i++) {
            txtName.get(1).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        txtName.get(1).sendKeys(name);

        //Description
        txtDescription.sendKeys(description);

        //Manager name
        StringBuilder cleanManagerName = new StringBuilder(String.valueOf(txtHiringManager));
        length = cleanManagerName.length();
        for (i = 0; i <= length; i++) {
            txtHiringManager.get(2).sendKeys(Keys.BACK_SPACE);
        }
        txtHiringManager.get(2).sendKeys(hiringManager);
        Thread.sleep(2000);

        //HiringManager
        txtHiringManager.get(2).sendKeys(Keys.ARROW_DOWN);
        txtHiringManager.get(2).sendKeys(Keys.ENTER);

        //Number of position
        StringBuilder cleanNumber = new StringBuilder(String.valueOf(txtNumberOfPosition));
        length = cleanNumber.length();
        for (i = 0; i <= length; i++) {
            txtNumberOfPosition.get(3).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(1000);
        txtNumberOfPosition.get(3).sendKeys(numberOfPosition);
        btnSave.click();
        return lblAddValidation.get(1).getText();
    }
}
