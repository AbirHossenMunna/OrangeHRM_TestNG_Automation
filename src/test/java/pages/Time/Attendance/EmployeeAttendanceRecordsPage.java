package pages.Time.Attendance;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeeAttendanceRecordsPage {
    int length;
    @FindBy(tagName = "a")
    List<WebElement> sideBarTime; //4
    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> topBarAttendance; //1
    @FindBy(css = "[role=menuitem]")
    List<WebElement> menuItem;
    @FindBy(tagName = "input")
    List<WebElement> txtInputField;
    @FindBy(css = ".oxd-button.oxd-button--medium.oxd-button--secondary")
    WebElement btnView;
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement lblErrorMsg;
    //    @FindBy(css = ".oxd-text.oxd-text--span")
    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/span[1]")
    WebElement lblValidationMsg;
    @FindBy(xpath = "//div[contains(text(),'0.75')]")
    public WebElement tableValidationByDuration;

    public EmployeeAttendanceRecordsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Test Steps
    public String mandatoryFieldsFillUp(String name) throws InterruptedException {
        sideBarTime.get(4).click();
        topBarAttendance.get(1).click();
        Thread.sleep(3000);
        menuItem.get(2).click();

        txtInputField.get(1).sendKeys(name);
        Thread.sleep(3000);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            Thread.sleep(3000);
            txtInputField.get(1).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        txtInputField.get(1).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        btnView.click();
        Thread.sleep(5000);
        return lblValidationMsg.getText();
    }

    public String mandatoryFieldsShouldBlank() throws InterruptedException {
        //Date
        Thread.sleep(3000);
        String clearDate = txtInputField != null ? txtInputField.toString() : ""; //Remove Data in any field
        length = clearDate.length();
        for (int i = 0; i <= length; i++) {
            txtInputField.get(2).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(3000);
        btnView.click();
        return lblErrorMsg.getText();
    }

    public String invalidEmployeeName(String name) throws InterruptedException {
        Thread.sleep(3000);
        //EmployeeName
        StringBuilder clearName = new StringBuilder(String.valueOf(txtInputField));
        length = clearName.length();
        for (int i = 0; i <= length; i++) {
            txtInputField.get(1).sendKeys(Keys.BACK_SPACE);
        }
        txtInputField.get(1).sendKeys(name);
        Thread.sleep(3000);
        txtInputField.get(2).sendKeys("2024-01-10");
        Thread.sleep(3000);
        btnView.click();
        Thread.sleep(3000);
        return lblErrorMsg.getText();
    }

    public String invalidDate(String name) throws InterruptedException {
        //EmployeeName
        String clearName = txtInputField != null ? txtInputField.toString() : ""; //Remove Date from the Date field
        length = clearName.length();
        for (int i = 0; i <= length; i++) {
            txtInputField.get(1).sendKeys(Keys.BACK_SPACE);
        }
        txtInputField.get(1).sendKeys(name);
        Thread.sleep(3000);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            txtInputField.get(1).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        txtInputField.get(1).sendKeys(Keys.ENTER);

        //Date
        Thread.sleep(3000);
        String clearDate = txtInputField != null ? txtInputField.toString() : ""; //Remove Date from the Date field
        length = clearDate.length();
        for (int i = 0; i <= length; i++) {
            txtInputField.get(2).sendKeys(Keys.BACK_SPACE);
        }
        txtInputField.get(2).sendKeys("01-12-1985");
        btnView.click();
        return lblErrorMsg.getText();
    }
}
