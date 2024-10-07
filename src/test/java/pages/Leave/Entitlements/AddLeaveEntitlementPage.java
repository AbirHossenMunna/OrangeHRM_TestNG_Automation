package pages.Leave.Entitlements;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddLeaveEntitlementPage {
    int length;
    @FindBy(tagName = "a")
    List<WebElement> sideBarTime; //3
    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> topBar; //2
    @FindBy(css = "[role=menuitem]")
    List<WebElement> menuItem; //0
    @FindBy(tagName = "input")
    List<WebElement> inputFields; //3,4
    @FindBy(className = "oxd-select-text-input")
    List<WebElement> dropdownLeave; //0,1
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement inLineErrorMsg;
    @FindBy(tagName = "button")
    List<WebElement> btnLeaves; //3,4
    @FindBy(tagName = "button")
    List<WebElement> btnConfirm; //7
    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/span[1]")
    WebElement lblValidationResult; //Record Found

    public AddLeaveEntitlementPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Test Cases
    public String mandatoryAllFieldShouldBlank() throws InterruptedException {
        sideBarTime.get(3).click();
        topBar.get(2).click();
        menuItem.get(0).click();
        btnLeaves.get(4).click();
        return inLineErrorMsg.getText();
    }
    public String allMandatoryFieldFillButOneFieldLeft(String empName) throws InterruptedException{
        dropdownLeave.get(0).click();
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            Thread.sleep(3000);
            dropdownLeave.get(0).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        dropdownLeave.get(0).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        inputFields.get(3).sendKeys(empName);
        Thread.sleep(2000);
        inputFields.get(3).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        inputFields.get(3).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        btnLeaves.get(4).click();
        return inLineErrorMsg.getText();
    }
    public String addLeaveEntitlementWithInvalidName(String entitlement,String empName) throws InterruptedException {
        inputFields.get(4).sendKeys(entitlement);
        Thread.sleep(3000);
        StringBuilder cleanEmpName = new StringBuilder(String.valueOf(inputFields));
        length = cleanEmpName.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(3).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(3000);
        inputFields.get(3).sendKeys(empName);
        btnLeaves.get(4).click();
        return inLineErrorMsg.getText();
    }
    public String addLeaveEntitlementWithInvalidData(String empName,String entitlement) throws InterruptedException {
        StringBuilder cleanEmpName = new StringBuilder(String.valueOf(inputFields));
        length = cleanEmpName.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(3).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(3000);
        inputFields.get(3).sendKeys(empName);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            Thread.sleep(3000);
            inputFields.get(3).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        inputFields.get(3).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        inputFields.get(4).sendKeys(entitlement);
        btnLeaves.get(4).click();
        return inLineErrorMsg.getText();
    }
    public String AddLeaveEntitlementWithValidData(String entitlement) throws InterruptedException {
        StringBuilder cleanEnt = new StringBuilder(String.valueOf(inputFields));
        length = cleanEnt.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(4).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        inputFields.get(4).sendKeys(entitlement);
        btnLeaves.get(4).click();
        Thread.sleep(2000);
        btnConfirm.get(7).click();
        Thread.sleep(3000);
        return lblValidationResult.getText();
    }
}

