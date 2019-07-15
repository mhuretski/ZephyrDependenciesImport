package element;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import util.Driver;

import java.util.List;

import static properties.Constants.JIRA_FILTER;
import static util.Waiter.*;

public class JiraSearch {

    private Driver driver;

    public JiraSearch(Driver driver) {
        this.driver = driver;
    }

    public void fillSearchField() {
        List<WebElement> advancedButton = driver.findElements(By.cssSelector("a[class~='switcher-item'].active"));
        List<WebElement> searchField = driver.findElements(By.id("advanced-search"));
        if (searchField.size() == 0) {
            while (true) {
                if (advancedButton.size() > 0) {
                    advancedButton.get(0).click();
                    searchField = driver.findElements(By.id("advanced-search"));
                    if (searchField.size() > 0) {
                        break;
                    }
                } else {
                    searchField = driver.findElements(By.id("advanced-search"));
                    if (searchField.size() > 0) {
                        break;
                    }
                    advancedButton = driver.findElements(By.cssSelector("a[class~='switcher-item'].active"));
                }
            }
        }

        WebElement searchFieldFound = searchField.get(0);
        searchFieldFound.clear();
        searchFieldFound.sendKeys(JIRA_FILTER);
        searchFieldFound.sendKeys(Keys.ENTER);

//        if (driver.findElements(By.cssSelector("div.suggestions.dropdown-ready")).size() > 0)
//            searchField.sendKeys(Keys.ENTER);
//        waitAbsence(By.cssSelector("div.suggestions.dropdown-ready"));
    }

    public void saveSearchFilter() {
        By saveAs = By.cssSelector(".save-as-new-filter");
        waitAndClick(saveAs);
        By filterField = By.id("filterName");
        driver.setValue(filterField, JIRA_FILTER);
        By submit = By.cssSelector("input[type='submit']");
        waitAndClick(submit);
        By savedTitle = By.cssSelector(".search-title[title='" + JIRA_FILTER + "']");
        waitPresence(savedTitle);
    }

}
