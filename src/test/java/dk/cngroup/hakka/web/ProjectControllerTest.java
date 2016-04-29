package dk.cngroup.hakka.web;

import com.google.common.collect.Lists;
import dk.cngroup.hakka.controller.ProjectController;
import dk.cngroup.hakka.controller.routes.Routes;
import dk.cngroup.hakka.entity.Project;
import dk.cngroup.hakka.repository.ProjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringApplicationConfiguration(classes = {MockServletContext.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ProjectControllerTest {

    private MockMvc mockMvc;

    private ProjectRepository repository;

    private ProjectController controller;

    @Before
    public void setUp() {
        repository = mock(ProjectRepository.class);
        controller = new ProjectController(repository);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void getProjectsShouldReturnStatusOk() throws Exception {
        mockMvc.perform(get(Routes.PROJECTS))
                .andExpect(status().isOk());
    }

    @Test
    public void getProjectsShouldReturnJson() throws Exception {
        List<Project> projects = createProjects();

        when(repository.findAll()).thenReturn(projects);

        mockMvc.perform(get(Routes.PROJECTS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projects[0].name").value("Test Project"))
                .andExpect(jsonPath("$.projects[0].id").value(1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(repository).findAll();
    }

    private List<Project> createProjects() {
        return Lists.newArrayList(createProject(1L, "Test Project"));
    }

    private Project createProject(Long id, String name) {
        Project project = new Project();
        project.setId(id);
        project.setName(name);

        return project;
    }


}
