package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static properties.Constants.PATH_TO_PROPERTIES;

public class Properties {

    private static java.util.Properties properties;

    private static void init() {
        try (InputStream input = new FileInputStream(PATH_TO_PROPERTIES)) {
            properties = new java.util.Properties();
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static java.util.Properties get() {
        if (properties == null) init();
        return properties;
    }

}