package element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.Driver;

import java.util.List;

import static properties.Constants.*;
import static util.Waiter.*;

public class CycleSummary {

    private Driver driver;
    private ZephyrApp za;

    public CycleSummary(ZephyrApp za, Driver driver) {
        this.driver = driver;
        this.za = za;
    }

    public void exportAllCycles() {
        za.switchToZephyr();
        za.waitLoader();
        By cyclesSelector = By.cssSelector("div[class='context-wrapper']");
        waitPresence(cyclesSelector);
        List<WebElement> cycles = getCycles();
        ExportPopup ep = new ExportPopup(za, driver);
        cycles.forEach(cycle -> {
            za.waitLoader();
            openCycleMenu(cycle);
            openExportCyclePopup();
            ep.exportXML();
        });
    }

    private List<WebElement> getCycles() {
        return driver.findElements(By.cssSelector("div[class='context-wrapper']"));
    }

    private void openCycleMenu(WebElement cycle) {
        driver.click(cycle);
    }

    private void openExportCyclePopup() {
        waitAndClick(By.xpath("//span[contains(text(),'Export Cycle')]"));
    }

    public void createCycleFolder() {
        za.waitLoader();
        za.switchToZephyr();
        za.waitLoader();
        WebElement folderMenu = waitAndGetElement(By.xpath("//span[@title='" + CYCLE_NAME + "']/ancestor::div[contains(@class,'node-wrapper')]//div[@class='context-wrapper']"));
        folderMenu.click();
        By addFolderBtn = By.xpath("//span[contains(text(),'Add Folder')]");
        waitAndClick(addFolderBtn);
        za.waitLoader();
        WebElement setNameElem = waitAndGetElement(By.id("name"));
        setNameElem.sendKeys(ZEPHYR_FOLDER_NAME);
        By addBtn = By.xpath("//div[@class='modal-footer']/button[contains(text(),'Add')]");
        waitAndClick(addBtn);
    }

    private List<WebElement> getRequiredCycleOptions() {
        return driver.findElements(By.xpath("//span[@title='" + CYCLE_NAME + "']/ancestor::div[contains(@class,'node-wrapper')]//div[@class='context-wrapper']"));
    }

    public void addTestsToCycleFolder() {
        za.waitLoader();
        By requiredCycle = By.xpath("//span[@title='" + CYCLE_NAME + "']/ancestor::div[contains(@class,'node-wrapper')]//div[@class='navigation-img next']");
        waitAndClick(requiredCycle);
        za.waitLoader();
        WebElement requiredCycleOptions = waitAndGetElement(By.xpath("//div[@class='navigation-wrapper']//div[text()[contains(.,'" + ZEPHYR_FOLDER_NAME + "')]]/ancestor::div[contains(@class,'node-wrapper')]//div[@class='context-wrapper']"));
        requiredCycleOptions.click();
        By addTestsBtn = By.xpath("//span[contains(text(),'Add Tests')]");
        waitAndClick(addTestsBtn);
        za.waitLoader();
        By viaSearchFilter = By.xpath("//li[contains(text(),'Via Search Filter')]");
        waitAndClick(viaSearchFilter);
        WebElement searchFilterField = waitAndGetElement(By.xpath("//label[@for='test']/ancestor::div[@class='ak-field-group']//div[@class='ak-field-flex-grow']//div/input"));
        searchFilterField.sendKeys(JIRA_FILTER);
        za.waitLoader();
        WebElement chooseFilter = waitAndGetElement(By.cssSelector("div[data-role='droplistContent']"));
        chooseFilter.click();
        za.waitLoader();
        By addBtn = By.xpath("//div[@class='modal-footer']/button[contains(text(),'Add')]");
        waitAndClick(addBtn);
        waitExportToFinish();
        waitLoaderBar();
        closeExportPopup();
    }

    private void waitExportToFinish() {
        List<WebElement> progressBar = getDownloadStatus();
        while (progressBar.size() == 0) progressBar = getDownloadStatus();
    }

    private void waitLoaderBar() {
        By loaderSelector = By.cssSelector(".slim-loader-bar");
        waitAbsence(loaderSelector);
    }

    private List<WebElement> getDownloadStatus() {
        return driver.findElements(By.cssSelector("div[class='progress-bar'] span[style*='100']"));
    }

    private void closeExportPopup() {
        waitAndClick(By.cssSelector("div[class='modal-footer'] button"));
    }

}
