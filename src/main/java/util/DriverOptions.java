package util;

import org.openqa.selenium.chrome.ChromeOptions;

public class DriverOptions {
    public ChromeOptions get() {
//        Map<String, Object> chromePrefs = new HashMap<>();
//        chromePrefs.put("profile.default_content_settings.popups", 0);
//        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "/temp/");
//        chromePrefs.put("safebrowsing.enabled", false);
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("prefs", chromePrefs);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        return options;
    }
}
