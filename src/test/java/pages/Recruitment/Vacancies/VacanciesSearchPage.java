package pages.Recruitment.Vacancies;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class VacanciesSearchPage {
    @FindBy(tagName = "li")
    List<WebElement> sideBarRecruitment;
    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> linkVacancies;
    @FindBy(className = "oxd-select-text-input")
    List<WebElement> dropdownValue;
    @FindBy(css = "[type=submit]")
    WebElement btnSearch;
    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/span[1]")
    WebElement lblResult;
//    @FindBy(css = "[type=reset]")
    @FindBy(tagName = "button")
    public List<WebElement> btnReset;

    public VacanciesSearchPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public boolean JobTitleFieldVisibleOrNot() throws InterruptedException {
        sideBarRecruitment.get(4).click();
        linkVacancies.get(1).click();
        Thread.sleep(2000);
        return dropdownValue.get(0).isDisplayed();
    }
    //Test Steps
    public String selectJobTitleWithNoResult() throws InterruptedException {
        Thread.sleep(1000);
        dropdownValue.get(0).click();
        dropdownValue.get(0).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        dropdownValue.get(0).sendKeys(Keys.ARROW_DOWN);
        dropdownValue.get(0).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        btnSearch.click();
        return lblResult.getText();
    }
    public String doSearchWithJobTitle() throws InterruptedException {
        btnReset.get(4).click();
        Thread.sleep(1000);
        dropdownValue.get(0).click();
        Thread.sleep(1000);
        dropdownValue.get(0).sendKeys(Keys.ARROW_DOWN);
        dropdownValue.get(0).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        btnSearch.click();
        return lblResult.getText();
    }
    public boolean vacancyFieldVisibleOrNot(){
        btnReset.get(4).click();
        return dropdownValue.get(1).isDisplayed();
    }
    public String doSearchWithVacancy() throws InterruptedException {
        Thread.sleep(1000);
        dropdownValue.get(1).click();
        Thread.sleep(1000);
        dropdownValue.get(1).sendKeys(Keys.ARROW_DOWN);
        dropdownValue.get(1).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        btnSearch.click();
        return lblResult.getText();
    }
}
