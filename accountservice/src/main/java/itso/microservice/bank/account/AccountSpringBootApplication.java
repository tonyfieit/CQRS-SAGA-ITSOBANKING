package itso.microservice.bank.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import itso.microservice.bank.account.cmd.es.clients.CustomerDetails;
import itso.microservice.bank.account.cmd.es.clients.CustomerDetailsClient;

@SpringBootApplication
public class AccountSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountSpringBootApplication.class, args);
		
		CustomerDetails customer= new CustomerDetailsClient().getCustomer("123-45-6789");
		System.out.println(" <============ customerDetails.toString() =============>");
	}

}