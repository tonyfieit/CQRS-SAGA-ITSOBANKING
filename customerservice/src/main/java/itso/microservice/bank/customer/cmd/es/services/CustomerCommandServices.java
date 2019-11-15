package itso.microservice.bank.customer.cmd.es.services;

import java.util.concurrent.CompletableFuture;

import itso.microservice.bank.customer.cmd.es.commands.dto.CreateCustomerDTO;
import itso.microservice.bank.customer.cmd.es.commands.dto.UpdateCustomerProfileDTO;


public interface CustomerCommandServices {

	public  CompletableFuture<String> createCustomer(CreateCustomerDTO createCustomerDTO);

	public CompletableFuture<String> updateCustomer(String customer_ssn, UpdateCustomerProfileDTO updateCustomerProfileDTO);

}
