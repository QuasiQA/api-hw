package apitest.framework.utils;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    private FileUtils() {
    }

    public static <T> T getJsonFromFile(String file, Class<T> clazz) {
        Gson gson = new Gson();
        try (FileReader fr = new FileReader(file)) {
            return gson.fromJson(fr, clazz);
        } catch (IOException e) {
            // LOGGING may be here
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
