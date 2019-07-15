package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {

    private static final int TIMEOUT = 15;
    private static Driver driver;
    private static WebDriverWait wait;

    public static WebElement waitAndGetElement(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        return driver.findElement(by);
    }

    public static void waitAndClick(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        WebElement elem = driver.findElement(by);
        driver.click(elem);
    }

    public static void waitToBeClickable(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitPresence(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitAbsence(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static boolean waitReturnAbsence(By by) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void setDriver(Driver driver) {
        Waiter.driver = driver;
        Waiter.wait = new WebDriverWait(driver, TIMEOUT);
    }

    public static void waitForLoad() {
        new WebDriverWait(driver, 10).until((ExpectedCondition<Boolean>) wd ->
                driver.executeScript("return document.readyState").equals("complete"));
    }

}
