package dk.cngroup.hakka.timur;

import dk.cngroup.hakka.entity.Assignment;
import dk.cngroup.hakka.entity.Person;
import dk.cngroup.hakka.entity.Project;
import dk.cngroup.hakka.repository.AssignmentRepository;
import dk.cngroup.hakka.repository.PersonRepository;
import dk.cngroup.hakka.repository.ProjectRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectImport {

    final Timur timur;
    final ProjectRepository projectRepository;
    final AssignmentRepository assignmentRepository;
    final PersonRepository personRepository;

    @Autowired
    public ProjectImport(Timur timur, ProjectRepository projectRepository, AssignmentRepository assignmentRepository, PersonRepository personRepository) {
        this.timur = timur;
        this.projectRepository = projectRepository;
        this.assignmentRepository = assignmentRepository;
        this.personRepository = personRepository;
    }


    @Data
    @Builder
    public static class ImportResponse {
        // list of imported projects and persons with intervals
    }

    @Data
    @Builder
    public static class ImportRequest {
        long timurId;
        String name;
        boolean checked;
        Integer minIntervalGap;
        List<ImportRequestPhase> phases;
    }

    @Data
    @Builder
    static class ImportRequestPhase {
        long timurId;
        String name;
        boolean checked;
        List<ImportRequestActivity> activities;
    }

    @Data
    @Builder
    static class ImportRequestActivity {
        long timurId;
        String name;
        boolean checked;
    }

    public ImportResponse doImportProject(ImportRequest request) {
        // for all checked phases do import them
        // for all not checked phases do import them and gather intervals
        // if checked
        //   ask timur
        //   merge it with phase intervals
        //   do import
        // return list of imported projects and persons with interval
        return new ImportResponse();
    }

    void doImportPhase(ImportRequest request, ImportRequestPhase phase, boolean checkOnly, boolean parentChecked) {
        // for all checked activities do import them
        // if parent checked or checked:
        //   for all not checked activities find intervals
        //   ask timur
        //   merge it with phase intervals
        // if checked do import
        // return
        //   - list of imported projects and persons with interval
        //   - (if parent checked) list of remaining projects and persons with interval
    }

    List<TimurPerson> doImportActivity(ImportRequest request, ImportRequestPhase phase, ImportRequestActivity activity, boolean checkOnly) {
        // ask timur
        List<TimurPerson> persons = timur.getAssignedPersons(request.getTimurId(), phase.getTimurId(), activity.getTimurId());
        // import project and persons to db
        if (!checkOnly) {
            storeProjectAndAssignments(activity.getTimurId(), activity.getName(), persons);
        }
        // return list persons with interval
        return persons;
    }

    void storeProjectAndAssignments(long timurId, String name, List<TimurPerson> timurPersons) {
        Project project = storeProject(timurId, name);
        for(TimurPerson timurPerson: timurPersons) {
            Person person = storePerson(timurPerson);
        }
    }

    Project storeProject(long timurId, String name) {
        return null;
    }

    Person storePerson(TimurPerson timurPerson) {
        return null;
    }

    void storeProjectAndAssignments(Project project, Person person, List<TimurAssignment> timurAssignments) {
    }

}
