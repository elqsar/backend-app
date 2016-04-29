package dk.cngroup.hakka.timur;

import lombok.Data;

import java.util.List;

@Data
public class TimurPerson {
    private long id;
    private String name;
    private List<TimurAssignment> assignments;
}
