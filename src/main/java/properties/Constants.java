package properties;

public class Constants {
    public static final String PATH_TO_PROPERTIES = "properties.properties";
    public static final String DOMAIN = "https://" + Properties.get().getProperty("jira") + ".atlassian.net";
    public static final String USERNAME = Properties.get().getProperty("username");
    public static final String PASSWORD = Properties.get().getProperty("password");
    public static final String STORY_ID = Properties.get().getProperty("story_id");
    public static final String CYCLE_NAME = Properties.get().getProperty("cycle_name");
    public static final boolean SHOULD_CREATE_ZEPHYR_FOLDER = Properties.get().getProperty("create_folder").equals("true");
    public static final String ZEPHYR_FOLDER_NAME = (Properties.get().getProperty("folder_name").equals("true"))
            ? Properties.get().getProperty("folder_name")
            : ("Dev " + STORY_ID);
    public static final String LABEL = Properties.get().getProperty("label");
    public static final String JIRA_FILTER = "type = Test and Labels = import_" + STORY_ID;
    public static final String JIRA_FILTER_FOR_STORY = "type = Test and Labels = " + LABEL + " and Issue not in linkedIssues(" + STORY_ID + ")";
    public static final String LINK_TO_JIRA = DOMAIN + "/browse/";
    public static final String PASS_TO_CHROME_DRIVER = "chromedriver.exe";

}
