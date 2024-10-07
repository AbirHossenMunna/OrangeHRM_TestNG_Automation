package pages.PIM;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchEmployee {
    @FindBy(className = "oxd-icon")
    List<WebElement> sideBarPim;
    @FindBy(tagName = "input")
    List<WebElement> txtEmpName;
    @FindBy(tagName = "input")
    List<WebElement> txtId;
    @FindBy(className = "oxd-select-text-input")
    List<WebElement> dropdowns;
    @FindBy(className = "oxd-button")
    List<WebElement> btnSearch;
    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/span[1]")
    WebElement lblResult;
    @FindBy(tagName = "button")
    public List<WebElement> btnReset;

    public SearchEmployee(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String searchEmployeeByInvalidName(String name) throws InterruptedException {
        sideBarPim.get(4).click();
        Thread.sleep(3000);
        txtEmpName.get(1).sendKeys(name);
        btnSearch.get(1).click();
        Thread.sleep(3000);
        return lblResult.getText();
    }

    public String searchEmployeeByValidName(String name) throws InterruptedException {
        Thread.sleep(1000);
        btnReset.get(4).click();
        Thread.sleep(3000);
        txtEmpName.get(1).sendKeys(name);
        btnSearch.get(1).click();
        Thread.sleep(3000);
        return lblResult.getText();
    }

    public String searchEmployeeByInvalidId(String id) throws InterruptedException {
        Thread.sleep(3000);
        btnReset.get(4).click();
        Thread.sleep(2000);
        txtId.get(2).sendKeys(id);
        btnSearch.get(1).click();
        Thread.sleep(3000);
        return lblResult.getText();
    }

    public String searchEmployeeByValidId(String id) throws InterruptedException {
        Thread.sleep(3000);
        btnReset.get(4).click();
        Thread.sleep(3000);
        txtId.get(2).sendKeys(id);
        btnSearch.get(1).click();
        Thread.sleep(3000);
        return lblResult.getText();
    }

    public String searchWithNoResult(String name, String id) throws InterruptedException {
        Thread.sleep(3000);
        btnReset.get(4).click();
        Thread.sleep(3000);
        txtEmpName.get(1).sendKeys(name);
        txtId.get(2).sendKeys(id);

        //EmployeeStatus
        dropdowns.get(0).click();
        Thread.sleep(3000);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            dropdowns.get(0).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdowns.get(0).sendKeys(Keys.ENTER);

        //JobTitle
        dropdowns.get(2).click();
        Thread.sleep(3000);
        for (int i = 0; i < 13; i++) {  // Adjust this based on how many options you want to scroll through
            dropdowns.get(2).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdowns.get(2).sendKeys(Keys.ENTER);

        //SubUnit
        dropdowns.get(3).click();
        Thread.sleep(3000);
        for (int i = 0; i < 13; i++) {  // Adjust this based on how many options you want to scroll through
            dropdowns.get(3).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdowns.get(3).sendKeys(Keys.ENTER);
        btnSearch.get(1).click();
        Thread.sleep(3000);
        return lblResult.getText();
    }

    public String searchEmployee(String name, String id) throws InterruptedException {
        Thread.sleep(3000);
        btnReset.get(4).click();
        Thread.sleep(3000);
        txtEmpName.get(1).sendKeys(name);
        txtId.get(2).sendKeys(id);

        //EmployeeStatus
        dropdowns.get(0).click();
        Thread.sleep(3000);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            dropdowns.get(0).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdowns.get(0).sendKeys(Keys.ENTER);

        //JobTitle
        dropdowns.get(2).click();
        Thread.sleep(3000);
        for (int i = 0; i < 36; i++) {  // Adjust this based on how many options you want to scroll through
            dropdowns.get(2).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        dropdowns.get(2).sendKeys(Keys.ENTER);

        //SubUnit
        dropdowns.get(3).click();
        Thread.sleep(3000);
        for (int i = 0; i < 1; i++) { // Adjust this based on how many options you want to scroll through
            for (int y = 0; y < 3; y++) { // Adjust this value based on how many arrow downs you need
                dropdowns.get(3).sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
            }
        }
        Thread.sleep(3000);
        dropdowns.get(3).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        btnSearch.get(1).click();
        Thread.sleep(3000);
        return lblResult.getText();
    }

}
