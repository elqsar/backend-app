package dk.cngroup.hakka.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Person")
@Data
public class Person {

    @GraphId
    private Long id;

    private Long timurId;

    @Index(unique = true)
    private String name;

    @Relationship(type = "HAS_A", direction = Relationship.INCOMING)
    private Set<Assignment> assignments = new HashSet<>();

    @Relationship(type = "USES", direction = Relationship.OUTGOING)
    private Set<Technology> technologies = new HashSet<>();

    @Relationship(type = "LIKES", direction = Relationship.OUTGOING)
    private Set<Technology> likes = new HashSet<>();

    @Relationship(type = "IS_CERTIFIED", direction = Relationship.OUTGOING)
    private Set<Technology> certified = new HashSet<>();
}
