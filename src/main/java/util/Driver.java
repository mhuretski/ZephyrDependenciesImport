package util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import static properties.Constants.PASS_TO_CHROME_DRIVER;

public class Driver extends ChromeDriver {

    private static final String CLICK = "arguments[0].click();";
    private static final String HIGHLIGHT = "arguments[0].style.border='3px dotted blue'";
    private static final String SET_VALUE_START = "arguments[0].value='";
    private static final String SET_VALUE_END = "';";

    static {
        System.setProperty("webdriver.chrome.driver", PASS_TO_CHROME_DRIVER);
    }

    private JavascriptExecutor driver;

    public Driver() {
        super();
        this.driver = (JavascriptExecutor) this;
    }

    public Driver(ChromeOptions options) {
        super(ChromeDriverService.createDefaultService(), options);
        this.driver = (JavascriptExecutor) this;
    }

    public Driver(DesiredCapabilities capabilities) {
        super(capabilities);
        this.driver = (JavascriptExecutor) this;
    }

    public JavascriptExecutor getJsDriver() {
        return driver;
    }

    public void click(WebElement element) {
        driver.executeScript(CLICK, element);
    }

    public void click(By by) {
        WebElement element = findElement(by);
        driver.executeScript(CLICK, element);
    }

    public void init() {
        this.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        this.manage().window().maximize();
    }

    public void highLightElement(By by) {
        WebElement elem = findElement(by);
        driver.executeScript(HIGHLIGHT, elem);
    }

    public void setValue(By by, String value) {
        WebElement elem = findElement(by);
        driver.executeScript(SET_VALUE_START + value + SET_VALUE_END, elem);
    }

    public void setValue(WebElement elem, String value) {
        driver.executeScript(SET_VALUE_START + value + SET_VALUE_END, elem);
    }

}
