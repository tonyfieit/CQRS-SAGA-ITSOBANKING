package itso.microservice.bank.customer.cmd.es.services;


import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import itso.microservice.bank.customer.cmd.es.commands.CreateCustomerCommand;
import itso.microservice.bank.customer.cmd.es.commands.UpdateCustomerProfileCommand;
import itso.microservice.bank.customer.cmd.es.commands.dto.CreateCustomerDTO;
import itso.microservice.bank.customer.cmd.es.commands.dto.UpdateCustomerProfileDTO;



@Service
public class CustomerCommandServicesImpl implements CustomerCommandServices {

	private final CommandGateway commandGateway;

	public CustomerCommandServicesImpl(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@Override
	public CompletableFuture<String> createCustomer(CreateCustomerDTO createCustomerDTO) {
		// TODO Auto-generated method stub
		System.out.println("@@@@11<@Override> inside CustomerCommandServicesImpl createCustomer" + createCustomerDTO);
		CreateCustomerCommand customerCommand = new CreateCustomerCommand(createCustomerDTO.getCustomer_ssn(),
				 createCustomerDTO.getTitle(), createCustomerDTO.getFirst_name(),
				createCustomerDTO.getLast_name());

		System.out.println("@@@@11<@Override> inside CustomerCommandServicesImpl commandGateway" + commandGateway);

		System.out.println(
				"@@@@11<@Override> inside CustomerCommandServicesImpl createCustomer" + createCustomerDTO.toString());

		return commandGateway.send(customerCommand);
	}

	@Override
	public CompletableFuture<String> updateCustomer(String customer_ssn, UpdateCustomerProfileDTO updateCustomerProfileDTO) {
		// TODO Auto-generated method stub
		UpdateCustomerProfileCommand customerUpdateCommand = new UpdateCustomerProfileCommand(customer_ssn, updateCustomerProfileDTO.getTitle(), updateCustomerProfileDTO.getFirst_name(), updateCustomerProfileDTO.getLast_name());
		
		return commandGateway.send(customerUpdateCommand);
	}

}
