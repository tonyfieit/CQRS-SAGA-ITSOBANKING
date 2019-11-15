package itso.microservice.bank.account.cmd.es.services;


import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import itso.microservice.bank.account.cmd.es.commands.CreateAccountCommand;
import itso.microservice.bank.account.cmd.es.commands.CreditAccountCommand;
import itso.microservice.bank.account.cmd.es.commands.DebitAccountCommand;
import itso.microservice.bank.account.cmd.es.commands.dto.AccountCreateDTO;
import itso.microservice.bank.account.cmd.es.commands.dto.AccountCreditDTO;
import itso.microservice.bank.account.cmd.es.commands.dto.AccountDebitDTO;
import itso.microservice.bank.account.cmd.es.commands.dto.TransactionDTO;

import java.util.UUID;
//import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

    private final CommandGateway commandGateway;

    public AccountCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO) {
    	
    	String acct_Id = generateAccountID(accountCreateDTO.getCustomer_ssn());
    	//UUID.randomUUID().toString()
    	
        return commandGateway.send(new CreateAccountCommand(acct_Id, accountCreateDTO.getBalance(), accountCreateDTO.getCustomer_ssn()));
    }

    @Override
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, AccountCreditDTO accountCreditDTO) {
        return commandGateway.send(new CreditAccountCommand(accountNumber, accountCreditDTO.getCreditAmount()));
    }

    @Override
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, AccountDebitDTO accountDebitDTO) {
        return commandGateway.send(new DebitAccountCommand(accountNumber, accountDebitDTO.getDebitAmount()));
    }
    
   
    
    private String generateAccountID(String customer_ssn) {
    	
    	System.out.println("customer_ssn " +customer_ssn);
    	int acctNumber = (new java.util.Random()).nextInt(899999) + 100000;
    	System.out.println("acctNumber " +acctNumber);
    	String accountid = "47" + customer_ssn.substring(0, 1) + "-" + acctNumber;
    	System.out.println("accountid " +accountid);
    	
    	return accountid;
    }
}
