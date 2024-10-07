package pages.Leave.AssignLeave;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AssignLeavePage {
    int length;
    @FindBy(tagName = "a")
    List<WebElement> sideBarTime; //3
    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> topBar; //6
    @FindBy(tagName = "button")
    List<WebElement> btnAssign; //3
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement inLineErrorMsg;
    @FindBy(tagName = "input")
    List<WebElement> inputFields; //1,2,3
    @FindBy(className = "oxd-select-text-input")
    WebElement dropdownLeave;

    public AssignLeavePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Test cases
    public String mandatoryAllFieldShouldBlank() throws InterruptedException {
        sideBarTime.get(3).click();
        topBar.get(6).click();
        Thread.sleep(2000);
        btnAssign.get(3).click();
        return inLineErrorMsg.getText();
    }

    public String doLeaveApplyWithInvalidEmpName(String empName) throws InterruptedException {
        inputFields.get(1).sendKeys(empName);
        Thread.sleep(2000);
        dropdownLeave.click();
        Thread.sleep(2000);
        for (int i = 0; i < 2; i++) {  // Adjust this based on how many options you want to scroll through
            Thread.sleep(3000);
            dropdownLeave.sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdownLeave.sendKeys(Keys.ENTER);
        inputFields.get(2).sendKeys("2024-05-12");
        Thread.sleep(2000);
        StringBuilder cleanToDate = new StringBuilder(String.valueOf(inputFields));
        length = cleanToDate.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(3).sendKeys(Keys.BACK_SPACE);
        }
        inputFields.get(3).sendKeys("2024-09-12");
        Thread.sleep(2000);
        btnAssign.get(3).click();
        return inLineErrorMsg.getText();
    }

    public String doLeaveApplyWithDateFieldAreBlank(String empName) throws InterruptedException {
        //Employee Name
        StringBuilder cleanEmpName = new StringBuilder(String.valueOf(inputFields));
        length = cleanEmpName.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(1).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        inputFields.get(1).sendKeys(Keys.BACK_SPACE);
        inputFields.get(1).sendKeys(empName);
        Thread.sleep(2000);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            Thread.sleep(3000);
            inputFields.get(1).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        inputFields.get(1).sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        //Leave Type
        dropdownLeave.click();
        Thread.sleep(3000);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            Thread.sleep(3000);
            dropdownLeave.sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdownLeave.sendKeys(Keys.ENTER);

        // From Date
        StringBuilder cleanFromDate = new StringBuilder(String.valueOf(inputFields));
        length = cleanFromDate.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(2).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);

        // To Date
        StringBuilder cleanToDate = new StringBuilder(String.valueOf(inputFields));
        length = cleanToDate.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(3).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        btnAssign.get(3).click();
        return inLineErrorMsg.getText();
    }

    public String doLeaveApplyWithInvalidDateRange() throws InterruptedException {
        inputFields.get(2).sendKeys("2024-05-12");
        Thread.sleep(2000);
        StringBuilder cleanToDate = new StringBuilder(String.valueOf(inputFields));
        length = cleanToDate.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(3).sendKeys(Keys.BACK_SPACE);
        }
        inputFields.get(3).sendKeys("2024-01-12");
        btnAssign.get(3).click();
        return inLineErrorMsg.getText();
    }

    public String doLeaveApplyWithInvalidDateFormat() throws InterruptedException {
        StringBuilder cleanFromDate = new StringBuilder(String.valueOf(inputFields));
        length = cleanFromDate.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(2).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        inputFields.get(2).sendKeys("35-12-2024");

        //To Date
        StringBuilder cleanToDate = new StringBuilder(String.valueOf(inputFields));
        length = cleanToDate.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(3).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        inputFields.get(3).sendKeys("40-12-2024");
        btnAssign.get(3).click();
        return inLineErrorMsg.getText();
    }

    public void doAssignLeave() throws InterruptedException {
        StringBuilder cleanFromDate = new StringBuilder(String.valueOf(inputFields));
        length = cleanFromDate.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(2).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        inputFields.get(2).sendKeys("2024-05-12");
        StringBuilder cleanToDate = new StringBuilder(String.valueOf(inputFields));
        length = cleanToDate.length();
        for (int i = 0; i <= length; i++) {
            inputFields.get(3).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        inputFields.get(2).sendKeys("2024-09-12");
        btnAssign.get(3).click();
    }
}
