package dk.cngroup.hakka.controller.responses;

import lombok.Data;

import java.util.Map;

@Data
public class HakkaResponse<T> {
    private final Map<String, T> response;
}
