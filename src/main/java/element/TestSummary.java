package element;

import org.openqa.selenium.By;
import util.Driver;
import util.Waiter;

public class TestSummary {

    private Driver driver;
    private ZephyrApp za;

    public TestSummary(ZephyrApp za, Driver driver) {
        this.driver = driver;
        this.za = za;
    }

    public void openCycleSummary() {
        za.waitLoader();
        Waiter.waitAndClick(By.cssSelector("a[href*='test-cycles-tab']"));
    }

}