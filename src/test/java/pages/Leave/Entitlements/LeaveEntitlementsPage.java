package pages.Leave.Entitlements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LeaveEntitlementsPage {
    int length;
    @FindBy(tagName = "a")
    List<WebElement> sideBarTime; //3
    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> topBar; //2
    @FindBy(css = "[role=menuitem]")
    List<WebElement> menuItem; //1
    @FindBy(tagName = "input")
    List<WebElement> inputFields; //1
    @FindBy(className = "oxd-select-text-input")
    List<WebElement> dropdownLeave; //0,1
    @FindBy(tagName = "button")
    List<WebElement> btnSearch; //4
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement inLineErrorMsg;
    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/span[1]")
    WebElement lblValidationResult; //Record Found

    public LeaveEntitlementsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String mandatoryAllFieldShouldBlank() throws InterruptedException {
        sideBarTime.get(3).click();
        topBar.get(2).click();
        menuItem.get(1).click();
        Thread.sleep(3000);
        btnSearch.get(4).click();
        return inLineErrorMsg.getText();
    }

    public String leaveEntitlementsWithInvalidName(String empName) throws InterruptedException {
        Thread.sleep(2000);
        inputFields.get(1).sendKeys(empName);
        Thread.sleep(3000);
        dropdownLeave.get(0).click();
        for (int i = 0; i < 2; i++) {  // Adjust this based on how many options you want to scroll through
            dropdownLeave.get(0).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        dropdownLeave.get(0).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        btnSearch.get(4).click();
        return inLineErrorMsg.getText();
    }

    public String leaveEntitlementsWithValidData(String empName) throws InterruptedException {
        Thread.sleep(2000);
        StringBuilder cleanEmpName = new StringBuilder(String.valueOf(inputFields));
        length = cleanEmpName.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(1).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        inputFields.get(1).sendKeys(empName);
        inputFields.get(1).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        inputFields.get(1).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        btnSearch.get(4).click();
        return lblValidationResult.getText();
    }
}
