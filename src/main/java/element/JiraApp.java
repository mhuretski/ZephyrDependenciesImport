package element;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import util.Driver;

import java.util.List;

import static properties.Constants.*;
import static util.Waiter.*;

public class JiraApp {

    private Driver driver;

    public JiraApp(Driver driver) {
        this.driver = driver;
    }

    public void getStory() {
        driver.get(LINK_TO_JIRA + STORY_ID);
        waitForLoad();
    }

    public void addTestsToStory() {
        boolean hasMore = true;
        while (hasMore) {
            By menu = By.cssSelector("a#opsbar-operations_more");
            waitAndClick(menu);
            By linkElem = By.cssSelector("a#link-issue");
            waitAndClick(linkElem);

            By searchForAnIssue = By.id("remote-jira-issue-search");
            waitAndClick(searchForAnIssue);

            By advancedSearchLink = By.id("advanced-search-toggle");
            waitAndClick(advancedSearchLink);

            By searchField = By.cssSelector("textarea[id='jql-search-text']");
            driver.setValue(searchField, JIRA_FILTER_FOR_STORY);

            waitAndClick(By.id("advanced-search-panel-button"));
            waitPresence(By.cssSelector("#remote-jira-advanced-search-form #link-search-loading.hidden"));

            List<WebElement> selectAll = driver.findElements(By.id("linkjiraissue-select-all"));
            if (selectAll.size() > 0) {
                selectAll.get(0).click();
                By add = By.id("linkjiraissue-add-selected");
                waitAndClick(add);
                By linkToStory = By.cssSelector("input[name='Link']");
                waitAndClick(linkToStory);
                waitAbsence(By.cssSelector(".icon.throbber.loading"));
            } else if (driver.findElements(By.cssSelector("div[class='aui-message info']")).size() > 0) {
                hasMore = false;
                By close = By.id("remote-jira-link-cancel");
                waitAndClick(close);
                By cancel = By.cssSelector("#link-jira-issue a.cancel");
                waitAndClick(cancel);
            }
        }
    }

    private void waitStoryPageLoad() {
        waitAbsence(By.cssSelector("div[class='aui-blanket'][aria-hidden='false']"));
    }

    public void openMenuLink() {
        waitAndClick(By.cssSelector("ul.operations .header-tools"));
    }

    public void bulkAllLink() {
        waitAndClick(By.id("bulkedit_all"));
    }

    public void chooseBulkItAll() {
        waitAndClick(By.cssSelector("input#bulkedit-select-all"));
    }

    public void nextBulkItAllBtn() {
        waitAndClick(By.id("next-bottom"));
    }

    public void editIssues() {
        waitAndClick(By.cssSelector("input[id='bulk.edit.operation.name_id']"));
    }

    public void nextChooseOperationBtn() {
        waitAndClick(By.id("next"));
    }

    public void removeTempLabel() {
        Select typeDropdown = new Select(driver.findElement(By.id("changelabels")));
        typeDropdown.selectByValue("remove");
        WebElement inputFieldForRemoval = waitAndGetElement(By.id("labels-textarea"));
        inputFieldForRemoval.click();
        inputFieldForRemoval.sendKeys(LABEL);
        inputFieldForRemoval.sendKeys(Keys.ENTER);
        WebElement sendEmailDisable = driver.findElement(By.cssSelector("input#sendBulkNotificationCB"));
        sendEmailDisable.click();

        nextChooseOperationBtn();
        By confirmDeletion = By.cssSelector("input#confirm");
        waitAndClick(confirmDeletion);
        By confirmedDeletion = By.xpath("//span[contains(text(),'Bulk operation is 100% complete.')]");
        waitPresence(confirmedDeletion);
    }

    public void findFilter() {
        WebElement input = waitAndGetElement(By.cssSelector("input[name='search']"));
        driver.setValue(input, JIRA_FILTER);
        input.sendKeys(Keys.ENTER);
        By filter = By.xpath("//a[contains(text(),'" + JIRA_FILTER + "')]/ancestor::tbody//span[@aria-label='More']");
        waitAndClick(filter);
    }

    public void deleteFilter() {
        By chooseDelete = By.xpath("//div[@role='menu']//span[contains(text(),'Delete')]");
        waitAndClick(chooseDelete);
        By confirmDeletion = By.xpath("//button//span[contains(text(),'Delete')]");
        waitAndClick(confirmDeletion);
    }

}
