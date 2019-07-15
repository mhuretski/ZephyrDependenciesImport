package element;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import util.Driver;
import util.Waiter;

import static properties.Constants.*;

public class Login {

    private Driver driver;

    public Login(Driver driver) {
        this.driver = driver;
    }

    public void login() {
        driver.get(DOMAIN);
        WebElement loginField = Waiter.waitAndGetElement(By.id("username"));
        driver.setValue(loginField, USERNAME);
        loginField.sendKeys(Keys.ENTER);
        WebElement passwordField = Waiter.waitAndGetElement(By.id("password"));
        driver.setValue(passwordField, PASSWORD);
        passwordField.sendKeys(Keys.ENTER);
    }

}
