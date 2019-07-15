package element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.Driver;
import util.Waiter;

import java.util.List;

public class ZephyrApp {

    private Driver driver;

    public ZephyrApp(Driver driver) {
        this.driver = driver;
    }

    public void switchToZephyr() {
        By zephyrFrameSelector = By.cssSelector("iframe[id*='zephyr']");
        List<WebElement> frame = driver.findElements(zephyrFrameSelector);
        while (frame.size() == 0) {
            frame = driver.findElements(zephyrFrameSelector);
            System.out.println("no zephyr frame");
        }
        driver.switchTo().frame(frame.get(0));
//        driver.switchTo().frame(0);
    }

    public void waitLoader() {
        boolean loaderIsPresent = checkLoaderPresence();
        while (loaderIsPresent) loaderIsPresent = checkLoaderPresence();
    }

    private boolean checkLoaderPresence() {
        return driver.findElements(By.cssSelector("div.loader-bar")).size() > 0;
    }

    public void switchFromZephyr() {
        driver.switchTo().defaultContent();
    }

}
