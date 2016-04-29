package dk.cngroup.hakka.timur;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.val;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static dk.cngroup.hakka.JsonTools.loadResource;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TimurIntegrationTest.TestApplication.class)
@DirtiesContext
public class TimurIntegrationTest {

    @Autowired
    private Timur client;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8100);

    @Configuration
    @EnableAutoConfiguration
    @EnableFeignClients
    @PropertySource("classpath:dk/cngroup/hakka/timur/timur.properties")
    protected static class TestApplication {
    }

    @Test
    public void shouldGetAllProjects() {
        stubFor(get(urlEqualTo("/projects"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(loadResource(getClass(), "projects.json"))));

        val projects = client.getProjects();

        assertEquals(1, projects.size());
    }

    @Test
    public void shouldGetAssignedPersons() {
        stubFor(get(urlEqualTo("/projects/1/phases/2/activities/3/persons?minIntervalGap=14"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(loadResource(getClass(), "assigned_persons.json"))));

        val assignedPersons = client.getAssignedPersons(1L, 2L, 3L);

        assertEquals(2, assignedPersons.size());
        assertEquals(assignedPersons.get(0).getName(), "Robert Carlos");
    }

}