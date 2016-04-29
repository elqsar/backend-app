package dk.cngroup.hakka.timur;

import static dk.cngroup.hakka.timur.ProjectImport.*;

import dk.cngroup.hakka.entity.Person;
import dk.cngroup.hakka.entity.Project;
import dk.cngroup.hakka.repository.ProjectRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ImportProjectTest {

    LinkedList<TimurPerson> timurPersons = new LinkedList<>();

    @Mock
    private Timur timur;

    @InjectMocks
    private ProjectImport projectImport;

    @Mock
    private ProjectRepository projectRepository;

    @Before
    public void setup() {
        TimurPerson person = new TimurPerson();
        person.setId(1l);
        timurPersons.add(person);
        when(timur.getAssignedPersons(1l, 1l, 1l)).thenReturn(timurPersons);
    }

    @Test
    public void shouldCheckActivityImport() {
        ImportRequest request = ImportRequest.builder().timurId(1l).build();
        ImportRequestPhase phase = ImportRequestPhase.builder().timurId(1l).build();
        ImportRequestActivity activity = ImportRequestActivity.builder().timurId(1l).build();

        List<TimurPerson> timurPersons = projectImport.doImportActivity(request, phase, activity, true);

        assertNotNull(timurPersons);
        assertFalse(timurPersons.isEmpty());
        assertEquals(1l, timurPersons.get(0).getId());
    }

    @Test
    @Ignore
    public void shouldStoreProject() {
        when(projectRepository.getByTimurId(1l)).thenReturn(null);
        Project project1 = new Project();
        project1.setId(1l);
        when(projectRepository.save(project1)).thenReturn(project1);

        Project project = projectImport.storeProject(1l, "Project X");

        assertNotNull(project);
        assertEquals(new Long(1), project.getTimurId());
    }

    @Test
    @Ignore
    public void shouldNoStoreExistingProject() {
        Project project1 = new Project();
        project1.setId(1l);
        when(projectRepository.getByTimurId(1l)).thenReturn(project1);

        Project project = projectImport.storeProject(1l, "Project X");

        assertNotNull(project);
        assertEquals(new Long(1), project.getTimurId());
    }

}
