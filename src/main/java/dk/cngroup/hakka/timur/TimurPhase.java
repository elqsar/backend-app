package dk.cngroup.hakka.timur;

import lombok.Data;

import java.util.List;

@Data
public class TimurPhase {
    private Long id;
    private String name;
    private List<TimurActivity> activities;
}
