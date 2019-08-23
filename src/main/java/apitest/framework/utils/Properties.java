package apitest.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Properties {

    private Properties() {
    }

    private static java.util.Properties props = new java.util.Properties();

    public static final String ACCESS_KEY;
    public static final double SUCCESS_THRESHOLD;
    private static final String PROP_FILE = "src/main/resources/config.properties";

    static {
        try (InputStream is = new FileInputStream(new File(PROP_FILE)) {
        }) {
            props.load(is);
            ACCESS_KEY = props.getProperty("accessKey");
            SUCCESS_THRESHOLD = Double.parseDouble(props.getProperty("successThreshold"));
        } catch (IOException e) {
            // LOGGING may be here
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
