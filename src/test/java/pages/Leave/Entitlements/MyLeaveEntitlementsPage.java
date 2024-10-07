package pages.Leave.Entitlements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyLeaveEntitlementsPage {
    int length;
    @FindBy(tagName = "a")
    List<WebElement> sideBarTime; //3
    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> topBar; //2
    @FindBy(css = "[role=menuitem]")
    List<WebElement> menuItem; //2
    @FindBy(className = "oxd-select-text-input")
    List<WebElement> dropdownLeave; //0,1
    @FindBy(tagName = "button")
    List<WebElement> btnSearch; //4
    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/span[1]")
    WebElement lblValidationResult; //Record Found
    @FindBy(tagName = "button")
    List<WebElement> btnEditIcon; //7
    @FindBy(tagName = "input")
    List<WebElement> inputFields; //2
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement inLineErrorMsg;
    @FindBy(tagName = "button") //4
    List<WebElement> btnSave;

    public MyLeaveEntitlementsPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public String SearchEntitlement () throws InterruptedException{
        sideBarTime.get(3).click();
        topBar.get(2).click();
        menuItem.get(2).click();
        dropdownLeave.get(0).click();
        Thread.sleep(2000);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            Thread.sleep(3000);
            dropdownLeave.get(0).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        dropdownLeave.get(0).sendKeys(Keys.ENTER);
        btnSearch.get(4).click();
        return lblValidationResult.getText();
    }
    public String editMandatoryAllFieldShouldBlank () throws InterruptedException{
        btnEditIcon.get(7).click();
        Thread.sleep(3000);
        StringBuilder cleanEnt = new StringBuilder(String.valueOf(inputFields));
        length = cleanEnt.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(2).sendKeys(Keys.BACK_SPACE);
        }
        btnSearch.get(4).click();
        Thread.sleep(2000);
        return inLineErrorMsg.getText();
    }
    public String editLeaveEntitlementWithInvalidEntitlement(String entitlement) throws InterruptedException {
        inputFields.get(2).sendKeys(entitlement);
        Thread.sleep(2000);
        btnSearch.get(4).click();
        return inLineErrorMsg.getText();
    }
    public String editLeaveEntitlementWithValidEntitlement(String entitlement) throws InterruptedException{
        StringBuilder cleanEnt = new StringBuilder(String.valueOf(inputFields));
        length = cleanEnt.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(2).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        inputFields.get(2).sendKeys(entitlement);
        btnSave.get(4).click();
        return lblValidationResult.getText();
    }
}
