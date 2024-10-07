package pages.Time.Customers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddCustomerPage {
    int length;
    @FindBy(tagName = "a")
    List<WebElement> sideBarTime; //4
    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> topBarProjectInfo; //3
    @FindBy(css = "[role=menuitem]")
    List<WebElement> menuItem; //0
    @FindBy(css = "[type=button]")
    List<WebElement> btnAdd; //3
    @FindBy(tagName = "input")
    List<WebElement> txtName;
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement lblErrorMsg;
    @FindBy(tagName = "textarea")
    WebElement txtDescription;
    @FindBy(css = "[type=submit]")
    WebElement btnSave;
    @FindBy(xpath = "//div[contains(text(),'Abir Corporation Ltd')]")
    public WebElement tableValidation;

    public AddCustomerPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Test Steps
    public String mandatoryFieldsShouldBlank() throws InterruptedException {
        sideBarTime.get(4).click();
        topBarProjectInfo.get(3).click();
        menuItem.get(0).click();
        Thread.sleep(2000);
        btnAdd.get(3).click();
        Thread.sleep(3000);
        btnSave.click();
        return lblErrorMsg.getText();
    }

    public String alreadyExistsName(String name) throws InterruptedException {
        Thread.sleep(1000);
        txtName.get(1).sendKeys(name);
        Thread.sleep(2000);
        btnSave.click();
        return lblErrorMsg.getText();
    }

    public void allFieldsFillUp(String name, String description) throws InterruptedException {
        String clearName = txtName != null ? txtName.toString() : ""; //Remove Data in any field
        length = clearName.length();
        for (int i = 0; i <= length; i++) {
            txtName.get(1).sendKeys(Keys.BACK_SPACE);
        }
        txtName.get(1).sendKeys(name);
        txtDescription.sendKeys(description);
        btnSave.click();
    }
}
