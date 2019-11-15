package itso.microservice.bank.account.cmdquery.es.controllers;

import io.swagger.annotations.Api;
import itso.microservice.bank.account.cmd.es.clients.CustomerDetails;
import itso.microservice.bank.account.cmd.es.clients.CustomerDetailsClient;
import itso.microservice.bank.account.cmd.es.commands.dto.AccountCreateDTO;
import itso.microservice.bank.account.cmd.es.commands.dto.AccountCreditDTO;
import itso.microservice.bank.account.cmd.es.commands.dto.AccountDebitDTO;
import itso.microservice.bank.account.cmd.es.commands.dto.TransactionDTO;
import itso.microservice.bank.account.cmd.es.exceptions.CustomerNotFoundException;
import itso.microservice.bank.account.cmd.es.services.AccountCommandService;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/v1/accounts")
@Api(value = "Account Commands", description = "Account Commands Related Endpoints", tags = "Account Commands")
public class AccountCommandController {

	private final AccountCommandService accountCommandService;

	public AccountCommandController(AccountCommandService accountCommandService) {
		this.accountCommandService = accountCommandService;
	}

	@PostMapping
	public CompletableFuture<String> createAccount(@RequestBody AccountCreateDTO account)
			throws CustomerNotFoundException {

		CustomerDetails customer = new CustomerDetailsClient().getCustomer(account.getCustomer_ssn());

		if (customer.getCustomer_ssn() != null) {

			account.setCustomer_ssn(customer.getCustomer_ssn());

			CompletableFuture<String> accountnumber = accountCommandService.createAccount(account);

			return accountnumber;
		}
		throw new CustomerNotFoundException(account.getCustomer_ssn(), "Person not found during creation of account.");

	}


	@PutMapping(value = "/credits/{accountNumber}")
	public CompletableFuture<String> creditMoneyToAccount(@PathVariable(value = "accountNumber") String accountNumber,
			@RequestBody AccountCreditDTO accountCreditDTO) {
		return accountCommandService.creditMoneyToAccount(accountNumber, accountCreditDTO);
	}

	@PutMapping(value = "/debits/{accountNumber}")
	public CompletableFuture<String> debitMoneyFromAccount(@PathVariable(value = "accountNumber") String accountNumber,
			@RequestBody AccountDebitDTO accountDebitDTO) {
		return accountCommandService.debitMoneyFromAccount(accountNumber, accountDebitDTO);
	}
	
	/*
	 * @PostMapping(value = "/transfer") public CompletableFuture<String>
	 * createAccount(@RequestBody TransactionDTO transaction) { return
	 * accountCommandService.transfer(transaction);
	 * 
	 * }
	 */
	
}
