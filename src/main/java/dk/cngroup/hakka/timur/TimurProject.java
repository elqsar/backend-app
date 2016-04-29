package dk.cngroup.hakka.timur;

import lombok.Data;

import java.util.List;

@Data
public class TimurProject {
    private Long id;
    private String name;
    private List<TimurPhase> phases;
}
