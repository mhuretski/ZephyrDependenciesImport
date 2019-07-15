import element.*;
import util.Driver;
import util.DriverOptions;
import util.Waiter;
import util.Writer;

import static properties.Constants.SHOULD_CREATE_ZEPHYR_FOLDER;

public class Main {

    public static void main(String[] args) {
        Driver driver = null;
        String result = "SUCCESS";
        try {
            DriverOptions options = new DriverOptions();
            driver = new Driver(options.get());
            driver.init();
            Login l = new Login(driver);
            NavigationMenu nm = new NavigationMenu(driver);
            JiraSearch js = new JiraSearch(driver);
            ZephyrApp za = new ZephyrApp(driver);
            TestSummary ts = new TestSummary(za, driver);
            CycleSummary cs = new CycleSummary(za, driver);
            JiraApp ja = new JiraApp(driver);
            Waiter.setDriver(driver);

            l.login();
            jiraCreateFilter(nm, js);
            zephyrCreateCyclesAndAddTests(nm, za, ts, cs);
            jiraSetStoryDependencies(ja);
            jiraDeleteLabelsFromTests(nm, ja);
            jiraDeleteFilter(nm, ja);
        } catch (Exception e) {
            result = Writer.getErrorMessage(e);
            e.printStackTrace();
        } finally {
            Writer.write(result);
            if (driver != null)
                driver.close();
        }
    }

    private static void jiraCreateFilter(NavigationMenu nm, JiraSearch js) {
        nm.clickIssuesAndFiltersBtn();
        nm.clickSearchIssuesBtn();
        js.fillSearchField();
        js.saveSearchFilter();
        nm.backToMainMenuBtn();
    }

    private static void zephyrCreateCyclesAndAddTests(NavigationMenu nm, ZephyrApp za, TestSummary ts, CycleSummary cs) {
        nm.clickTestsBtn();
        nm.clickTestSummaryBtn();
        za.switchToZephyr();
        ts.openCycleSummary();
        if (SHOULD_CREATE_ZEPHYR_FOLDER) cs.createCycleFolder();
        cs.addTestsToCycleFolder();
        za.switchFromZephyr();
    }

    private static void jiraSetStoryDependencies(JiraApp ja) {
        ja.getStory();
        ja.addTestsToStory();
    }

    private static void jiraDeleteLabelsFromTests(NavigationMenu nm, JiraApp ja) {
        nm.clickIssuesAndFiltersBtn();
        nm.viewAllFiltersBtn();
        nm.chooseFilteredLink();
        ja.openMenuLink();
        ja.bulkAllLink();
        ja.chooseBulkItAll();
        ja.nextBulkItAllBtn();
        ja.editIssues();
        ja.nextChooseOperationBtn();
        ja.removeTempLabel();
    }

    private static void jiraDeleteFilter(NavigationMenu nm, JiraApp ja) {
        nm.clickIssuesAndFiltersBtn();
        nm.viewAllFiltersBtn();
        ja.findFilter();
        ja.deleteFilter();
    }

}
