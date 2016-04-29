package dk.cngroup.hakka;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import lombok.SneakyThrows;

public class JsonTools {

    @SneakyThrows
    public static String loadResource(Class<?> contextClass, String path) {
        return Resources.toString(Resources.getResource(contextClass, path), Charsets.UTF_8);
    }

}
