package pages.Time.Reports;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ViewEmployeeReportPage {
    int length;
    @FindBy(tagName = "a")
    List<WebElement> sideBarTime; //4
    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> topBarProjectInfo; //2
    @FindBy(css = "[role=menuitem]")
    List<WebElement> menuItem; //1
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement lblErrorMsg;
    @FindBy(tagName = "button")
    List<WebElement> btnView; //4
    @FindBy(tagName = "input")
    List<WebElement> txtInputField; //1,2,3,4
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-text--count")
    WebElement lblValidation;

    public ViewEmployeeReportPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    //Test Steps
    public String mandatoryFieldsShouldBlank() throws InterruptedException{
        sideBarTime.get(4).click();
        topBarProjectInfo.get(2).click();
        menuItem.get(1).click();
        Thread.sleep(2000);
        btnView.get(4).click();
        return lblErrorMsg.getText();
    }
    public String employeeReportByInvalidEmployeeName(String name) throws InterruptedException {
        Thread.sleep(2000);
        txtInputField.get(1).sendKeys(name);
        btnView.get(4).click();
        Thread.sleep(2000);
        return lblErrorMsg.getText();
    }
    public String employeeReportByInvalidProjectName(String customerName,String projectName) throws InterruptedException {
        String clearEmployeeName = txtInputField != null ? txtInputField.toString() : ""; //Remove Data in any field
        length = clearEmployeeName.length();
        for (int i = 0; i <= length; i++) {
            txtInputField.get(1).sendKeys(Keys.BACK_SPACE);
        }
        txtInputField.get(1).sendKeys(customerName);
        Thread.sleep(2000);
        for (int i = 0; i < 2; i++) {  // Adjust this based on how many options you want to scroll through
            txtInputField.get(1).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        txtInputField.get(1).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        txtInputField.get(2).sendKeys(projectName);
        btnView.get(4).click();
        Thread.sleep(2000);
        return lblErrorMsg.getText();
    }
    public String employeeReportByInvalidDateRange(String name) throws InterruptedException {
        String clearProjectName = txtInputField != null ? txtInputField.toString() : ""; //Remove Data in any field
        length = clearProjectName.length();
        for (int i = 0; i <= length; i++) {
            txtInputField.get(2).sendKeys(Keys.BACK_SPACE);
        }
        txtInputField.get(2).sendKeys(name);
        Thread.sleep(2000);
        for (int i = 0; i < 2; i++) {  // Adjust this based on how many options you want to scroll through
            txtInputField.get(2).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        txtInputField.get(2).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        txtInputField.get(3).sendKeys("2024-15-10");
        Thread.sleep(2000);
        txtInputField.get(4).sendKeys("2024-04-10");
        Thread.sleep(2000);
        btnView.get(4).click();
        return lblErrorMsg.getText();
    }
    public String viewEmployeeReportByMandatoryField() throws InterruptedException {
        String clearStartDate = txtInputField != null ? txtInputField.toString() : ""; //Remove Data in any field
        length = clearStartDate.length();
        for (int i = 0; i <= length; i++) {
            txtInputField.get(4).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        String clearEndDate = txtInputField != null ? txtInputField.toString() : ""; //Remove Data in any field
        length = clearEndDate.length();
        for (int i = 0; i <= length; i++) {
            txtInputField.get(3).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(2000);
        btnView.get(4).click();
        return lblValidation.getText();
    }
}
