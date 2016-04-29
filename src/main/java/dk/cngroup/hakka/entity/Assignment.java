package dk.cngroup.hakka.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.time.LocalDate;

@NodeEntity
@Data
public class Assignment {

    @GraphId
    private Long id;

    private Person person;
    private Project project;
    private Technology technology;

    private LocalDate from;
    private LocalDate to;

}
