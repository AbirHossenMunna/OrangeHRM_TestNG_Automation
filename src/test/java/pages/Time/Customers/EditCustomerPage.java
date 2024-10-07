package pages.Time.Customers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EditCustomerPage {
    int length;
    @FindBy(tagName = "a")
    List<WebElement> sideBarTime; //4
    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> topBarProjectInfo; //3
    @FindBy(css = "[role=menuitem]")
    List<WebElement> menuItem; //0
    @FindBy(tagName = "button")
    List<WebElement> btnEdit; //17
    @FindBy(tagName = "input")
    List<WebElement> txtName; //1
    @FindBy(tagName = "textarea")
    WebElement txtDescription;
    @FindBy(css = "[type=submit]")
    WebElement btnSave;
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement lblErrorMsg;
    @FindBy(xpath = "//div[contains(text(),'Abir Corporation Limited')]")
    public WebElement tableValidation;

    public EditCustomerPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public String mandatoryFieldsShouldBlank() throws InterruptedException{
        sideBarTime.get(4).click();
        topBarProjectInfo.get(3).click();
        menuItem.get(0).click();
        Thread.sleep(2000);
        btnEdit.get(5).click();
        String clearName = txtName != null ? txtName.toString() : ""; //Remove Data in any field
        length = clearName.length();
        for (int i = 0; i <= length; i++) {
            txtName.get(1).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(3000);
        btnSave.click();
        return lblErrorMsg.getText();
    }
    public String alreadyExistsName(String name) throws InterruptedException{
        Thread.sleep(1000);
        txtName.get(1).sendKeys(name);
        Thread.sleep(2000);
        btnSave.click();
        return lblErrorMsg.getText();
    }
    public void editAllFields(String name, String description) throws InterruptedException{
        String clearName = txtName != null ? txtName.toString() : ""; //Remove Data in any field
        length = clearName.length();
        for (int i = 0; i <= length; i++) {
            txtName.get(1).sendKeys(Keys.BACK_SPACE);
        }
        txtName.get(1).sendKeys(name);
        String clearDescription = txtDescription != null ? txtDescription.toString() : ""; //Remove Data in any field
        length = clearDescription.length();
        for (int i = 0; i <= length; i++) {
            txtDescription.sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(3000);
        txtDescription.sendKeys(description);
        btnSave.click();
    }
}
