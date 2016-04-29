package dk.cngroup.hakka.repository;

import dk.cngroup.hakka.entity.Person;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends GraphRepository<Person> {

    Person findOneByTimurId(long timurId);
}
