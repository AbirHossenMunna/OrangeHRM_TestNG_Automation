package pages.Leave.Apply;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ApplyLeavePage {
    int length;
    @FindBy(tagName = "a")
    List<WebElement> sideBarTime; //3
    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> topBar; //0
    @FindBy(className = "oxd-select-text-input")
    WebElement dropdownLeaveType;
    @FindBy(tagName = "input")
    List<WebElement> inputDate; //1,2
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement inLineErrorMsg;
    @FindBy(tagName = "button")
    List<WebElement> btnApply; //3
    @FindBy(tagName = "textarea")
    WebElement txtComment;
    //    @FindBy(tagName = "p")
    @FindBy(css = ".oxd-text.oxd-text--p.orangehrm-leave-balance-text")
    WebElement lblLeaveBalance;
    @FindBy(xpath = "//div[contains(text(),'2024-02-11 to 2024-05-11')]")
    public WebElement tableValidation;
    WebDriver driver;

    public ApplyLeavePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Test Steps
    public String mandatoryAllFieldShouldBlank() throws InterruptedException {
        sideBarTime.get(3).click();
        topBar.get(0).click();
        Thread.sleep(3000);
        btnApply.get(3).click();
        return inLineErrorMsg.getText();
    }

    public String doLeaveApplyWithDateFieldAreBlank() throws InterruptedException {
        dropdownLeaveType.click();
        Thread.sleep(3000);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            Thread.sleep(3000);
            dropdownLeaveType.sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdownLeaveType.sendKeys(Keys.ENTER);
        btnApply.get(3).click();
        return inLineErrorMsg.getText();
    }

    public String doLeaveApplyWithInvalidDateRange() throws InterruptedException {
        inputDate.get(1).sendKeys("2024-05-10");
        Thread.sleep(2000);
        StringBuilder cleanToDate = new StringBuilder(String.valueOf(inputDate));
        length = cleanToDate.length();
        for (int i = 0; i <= length; i++) {
            inputDate.get(2).sendKeys(Keys.BACK_SPACE);
        }
        inputDate.get(2).sendKeys("2024-01-10");
        Thread.sleep(2000);
        btnApply.get(3).click();
        return inLineErrorMsg.getText();
    }

    public String doLeaveApplyWithInvalidDateFormat() throws InterruptedException {
        StringBuilder cleanFromDate = new StringBuilder(String.valueOf(inputDate));
        length = cleanFromDate.length();
        for (int i = 0; i <= length; i++) {
            inputDate.get(1).sendKeys(Keys.BACK_SPACE);
        }
        inputDate.get(1).sendKeys("05-10-2024");
        Thread.sleep(2000);
        StringBuilder cleanToDate = new StringBuilder(String.valueOf(inputDate));
        length = cleanToDate.length();
        for (int i = 0; i <= length; i++) {
            inputDate.get(2).sendKeys(Keys.BACK_SPACE);
        }
        inputDate.get(2).sendKeys("10-10-2024");
        btnApply.get(3).click();
        return inLineErrorMsg.getText();
    }

    public void doLeaveApplyWithValidData(String comment) throws InterruptedException {
        StringBuilder cleanFromDate = new StringBuilder(String.valueOf(inputDate));
        length = cleanFromDate.length();
        for (int i = 0; i <= length; i++) {
            inputDate.get(1).sendKeys(Keys.BACK_SPACE);
        }
        inputDate.get(1).sendKeys("2024-2-11");
        Thread.sleep(2000);
        StringBuilder cleanToDate = new StringBuilder(String.valueOf(inputDate));
        length = cleanToDate.length();
        for (int i = 0; i <= length; i++) {
            inputDate.get(2).sendKeys(Keys.BACK_SPACE);
        }
        inputDate.get(2).sendKeys("2024-5-11");
        Thread.sleep(3000);
        txtComment.sendKeys(comment);
        Thread.sleep(3000);
        btnApply.get(3).click();
    }
}
