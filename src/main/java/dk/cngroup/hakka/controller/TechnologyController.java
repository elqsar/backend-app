package dk.cngroup.hakka.controller;

import dk.cngroup.hakka.controller.responses.HakkaResponse;
import dk.cngroup.hakka.controller.routes.Routes;
import dk.cngroup.hakka.entity.Technology;
import dk.cngroup.hakka.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Routes.TECHNOLOGIES, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TechnologyController {

    private final TechnologyRepository technologyRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list() {
        Iterable<Technology> technologies = technologyRepository.findAll();
        return ResponseEntity.ok(HakkaResponse.of("technologies", technologies));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Technology create(@RequestBody Technology technology) {
        return technologyRepository.save(technology);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Technology findOne(@PathVariable("id") Long id) {
        return technologyRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        technologyRepository.delete(id);
    }
}
