package dk.cngroup.hakka.controller.responses;

import com.google.common.collect.Maps;

import java.util.Map;

public class HakkaResponse {
    public static <T> Map<String, T> of(String name, T content) {
        Map<String, T> result = Maps.newHashMap();
        result.put(name, content);
        return result;
    }
}
