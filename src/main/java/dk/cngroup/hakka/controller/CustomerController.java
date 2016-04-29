package dk.cngroup.hakka.controller;

import dk.cngroup.hakka.controller.responses.HakkaResponse;
import dk.cngroup.hakka.controller.routes.Routes;
import dk.cngroup.hakka.entity.Customer;
import dk.cngroup.hakka.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Routes.CUSTOMERS, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final CustomerRepository customerRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list() {
        Iterable<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(HakkaResponse.of("customers", customers));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer create(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Customer findOne(@PathVariable("id") Long id) {
        return customerRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        customerRepository.delete(id);
    }
}
