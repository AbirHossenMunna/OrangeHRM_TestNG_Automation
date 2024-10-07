package pages.Recruitment.Candidates;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CandidatesSearchPage {
    @FindBy(tagName = "li")
    List<WebElement> sideBarRecruitment;
    //    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[5]/a[1]/span[1]")
//    WebElement sideBarRecruitment;
    @FindBy(css = "[type=submit]")
    WebElement btnSearch;
    @FindBy(className = "oxd-select-text-input")
    List<WebElement> dropdownStatus;
    @FindBy(css = "[type=reset]")
    public WebElement btnReset;
    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/span[1]")
    WebElement lblResult;
    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/div[1]/span[1]")
    public WebElement lblErrorMsg;
    @FindBy(tagName = "input")
    public List<WebElement> txtCandidatesName;

    public CandidatesSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Test Steps
    public boolean searchButtonVisibleOrNot() throws InterruptedException {
        Thread.sleep(3000);
        sideBarRecruitment.get(4).click();
//        sideBarRecruitment.click();
        Thread.sleep(3000);
        return btnSearch.isDisplayed();
    }

    public String incorrectSearchName(String name) throws InterruptedException {
        txtCandidatesName.get(1).sendKeys(name);
        btnSearch.click();
        return lblErrorMsg.getText();
    }

    public String correctSearchName(String name) throws InterruptedException {
        btnReset.click();
        Thread.sleep(1000);
        txtCandidatesName.get(1).sendKeys(name);
        Thread.sleep(2000);
        txtCandidatesName.get(1).sendKeys(Keys.ARROW_DOWN);
        txtCandidatesName.get(1).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        txtCandidatesName.get(1).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        btnSearch.click();
        return lblResult.getText();
    }

    public String searchNameByEnterButton(String name) throws InterruptedException {
        btnReset.click();
        Thread.sleep(1000);
        txtCandidatesName.get(1).sendKeys(name);
        txtCandidatesName.get(1).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        txtCandidatesName.get(1).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);
        btnSearch.sendKeys(Keys.ENTER);
        return lblResult.getText();
    }

    public String doSearchWithSelectDropdown() throws InterruptedException {
        btnReset.click();
        Thread.sleep(3000);
        dropdownStatus.get(3).click();
        Thread.sleep(2000);
        dropdownStatus.get(3).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        dropdownStatus.get(3).sendKeys(Keys.ENTER);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0, 900);");
        btnSearch.click();
        return lblResult.getText();
    }
}
