package dk.cngroup.hakka.repository;

import dk.cngroup.hakka.entity.Project;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends GraphRepository<Project> {

    Project findOneByTimurId(long id);
}
