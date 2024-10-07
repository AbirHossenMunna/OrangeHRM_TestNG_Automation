package pages.Time.Projects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchProjectsPage {
    int length;
    @FindBy(tagName = "a")
    List<WebElement> sideBarTime; //4
    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> topBarProjectInfo; //3
    @FindBy(css = "[role=menuitem]")
    List<WebElement> menuItem; //1
    @FindBy(tagName = "input")
    List<WebElement> txtInputField; //1,2,3
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement lblErrorMsg;
    @FindBy(tagName = "button")
    List<WebElement> btnSearch; //5
    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/span[1]")
    WebElement lblValidation;

    public SearchProjectsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public String searchWithInvalidData(String customerName,String projectName, String admin) throws InterruptedException {
        sideBarTime.get(4).click();
        topBarProjectInfo.get(3).click();
        menuItem.get(1).click();
        Thread.sleep(2000);
        txtInputField.get(1).sendKeys(customerName);
        Thread.sleep(2000);
        txtInputField.get(2).sendKeys(projectName);
        Thread.sleep(2000);
        txtInputField.get(3).sendKeys(admin);
        btnSearch.get(5).click();
        Thread.sleep(2000);
        return lblErrorMsg.getText();
    }
    public String searchWithValidData(String customerName,String projectName, String admin) throws InterruptedException {
        Thread.sleep(2000);
        String clearCustomerName = txtInputField != null ? txtInputField.toString() : ""; //Remove Data in any field
        length = clearCustomerName.length();
        for (int i = 0; i <= length; i++) {
            txtInputField.get(1).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(1000);
        txtInputField.get(1).sendKeys(customerName);; //Customer Name
        Thread.sleep(3000);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            txtInputField.get(1).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        txtInputField.get(1).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        String clearProjectName = txtInputField != null ? txtInputField.toString() : ""; //Remove Data in any field
        length = clearProjectName.length();
        for (int i = 0; i <= length; i++) {
            txtInputField.get(2).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(1000);
        txtInputField.get(2).sendKeys(projectName);; //Project Name
        Thread.sleep(3000);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            txtInputField.get(2).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        txtInputField.get(2).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        String clearAdminName = txtInputField != null ? txtInputField.toString() : ""; //Remove Data in any field
        length = clearAdminName.length();
        for (int i = 0; i <= length; i++) {
            txtInputField.get(3).sendKeys(Keys.BACK_SPACE);
        }
        Thread.sleep(1000);
        txtInputField.get(3).sendKeys(admin);; //Admin Name
        Thread.sleep(3000);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            txtInputField.get(3).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        txtInputField.get(3).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        btnSearch.get(5).click();
        Thread.sleep(2000);
        return lblValidation.getText();
    }
}
