package itso.microservice.bank.customer.cmdquery.es.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import itso.microservice.bank.customer.cmd.es.commands.dto.CreateCustomerDTO;
import itso.microservice.bank.customer.cmd.es.commands.dto.UpdateCustomerProfileDTO;
import itso.microservice.bank.customer.cmd.es.services.CustomerCommandServices;

/**
 *
 * ITSO Customer Command Controller
 */
@RestController
@RequestMapping(value = "/api/v1/customers")
@Api(value = "Customer Commands", description = "Customer Command Events Endpoint", tags = "Customer Commands")
public class CustomerCommandController {

	private final CustomerCommandServices customerCommandServices;

	public CustomerCommandController(CustomerCommandServices customerCommandServices) {
		this.customerCommandServices = customerCommandServices;
	}

	@PostMapping
	public CompletableFuture<String> createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
		System.out.println("@@@@11<@PostMapping> inside CustomerCommandController createCustomer" + createCustomerDTO );
		
		
		return customerCommandServices.createCustomer(createCustomerDTO);
	}

	@PutMapping(value = "/updateCustomer/{customer_ssn}")
	public CompletableFuture<String> updateCustomer(@PathVariable(value = "customer_ssn") String customer_ssn, @RequestBody UpdateCustomerProfileDTO updateCustomerProfileDTO){
		
		return customerCommandServices.updateCustomer(customer_ssn, updateCustomerProfileDTO);
	}

}
