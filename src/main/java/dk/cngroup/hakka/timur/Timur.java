package dk.cngroup.hakka.timur;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(serviceId = "timur", url = "${timur.url}")
public interface Timur {

    @RequestMapping(method = GET, value = "projects")
    List<TimurProject> getProjects();

    @RequestMapping(method = GET, value = "projects/{project}/phases/{phase}/activities/{activity}/persons")
    List<TimurPerson> getAssignedPersons(
            @PathVariable("project") Long projectId,
            @PathVariable("phase") Long phaseId,
            @PathVariable("activity") Long activityId);

}
