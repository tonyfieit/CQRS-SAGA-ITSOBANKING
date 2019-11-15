package itso.microservice.bank.customer.cmdquery.es.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import itso.microservice.bank.customer.query.es.entities.CustomerQueryEntity;
import itso.microservice.bank.customer.query.es.services.CustomerQueryService;


@RestController
@RequestMapping(value = "/api/v1/customers")
@Api(value = "Customer Queries", description = "Customer Query Events Endpoint", tags = "Customer Queries")
public class CustomerQueryController {

    private final CustomerQueryService customerQueryService;

    public CustomerQueryController(CustomerQueryService customerQueryService) {
        this.customerQueryService = customerQueryService;
    }

    @GetMapping("/{customer_ssn}")
    public CustomerQueryEntity getCustomer(@PathVariable(value = "customer_ssn") String customer_ssn){
        return customerQueryService.getCustomer(customer_ssn);
    }

    @GetMapping("/events/{customer_ssn}")
    public List<Object> listEventsForCustomer(@PathVariable(value = "customer_ssn") String customer_ssn){
        return customerQueryService.listEventsForCustomer(customer_ssn);
    }
    
}
