package tr.com.swe599;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonHelper {

    private static final Gson gson;

    static {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    public static String toJsonString(Object dto) {
        return gson.toJson(dto);
    }

    public static Object fromJsonString(String jsonStr, Class<?> dtoClazz) {
        return gson.fromJson(jsonStr, dtoClazz);
    }
}
