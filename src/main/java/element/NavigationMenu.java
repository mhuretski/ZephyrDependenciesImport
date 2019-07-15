package element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import util.Driver;
import util.Waiter;

import static properties.Constants.JIRA_FILTER;

public class NavigationMenu {

    private Driver driver;

    public NavigationMenu(Driver driver) {
        this.driver = driver;
    }

    public void clickTestsBtn() {
        Waiter.waitAndClick(By.xpath("//div[contains(text(),'Tests')]"));
    }

    public void clickTestSummaryBtn() {
        Waiter.waitAndClick(By.xpath("//a//div[contains(text(),'Test Summary')]"));
    }

    public void clickIssuesAndFiltersBtn() {
        Waiter.waitAndClick(By.xpath("//a//div[contains(text(),'Issues and filters')]"));
    }

    public void clickSearchIssuesBtn() {
        Waiter.waitAndClick(By.xpath("//a//div[contains(text(),'Search issues')]"));
    }

    public void backToMainMenuBtn() {
        ((JavascriptExecutor) driver).executeScript("return document.getElementsByClassName('aui-blanket')[0].remove();");
        Waiter.waitAndClick(By.cssSelector("button[data-test-id='NavigationItem']"));
    }

    public void viewAllFiltersBtn() {
        Waiter.waitAndClick(By.xpath("//a//div[contains(text(),'View all filters')]"));
    }

    public void chooseFilteredLink() {
        Waiter.waitAndClick(By.xpath("//a//div[contains(text(),'" + JIRA_FILTER + "')]"));
    }

}