package dk.cngroup.hakka.controller;

import dk.cngroup.hakka.controller.responses.HakkaResponse;
import dk.cngroup.hakka.controller.routes.Routes;
import dk.cngroup.hakka.entity.Project;
import dk.cngroup.hakka.timur.ProjectImport;
import dk.cngroup.hakka.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Routes.PROJECTS, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProjectImport projectImport;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list() {
        Iterable<Project> projects = projectRepository.findAll();
        return ResponseEntity.ok(HakkaResponse.of("projects", projects));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Project create(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Project findOne(@PathVariable("id") Long id) {
        return projectRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        projectRepository.delete(id);
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProjectImport.ImportResponse importProjects(@RequestBody ProjectImport.ImportRequest request) {
        return projectImport.doImportProject(request);
    }

}
