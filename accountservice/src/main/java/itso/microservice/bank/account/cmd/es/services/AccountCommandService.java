package itso.microservice.bank.account.cmd.es.services;



import java.util.concurrent.CompletableFuture;

import itso.microservice.bank.account.cmd.es.commands.dto.AccountCreateDTO;
import itso.microservice.bank.account.cmd.es.commands.dto.AccountCreditDTO;
import itso.microservice.bank.account.cmd.es.commands.dto.AccountDebitDTO;
import itso.microservice.bank.account.cmd.es.commands.dto.TransactionDTO;

public interface AccountCommandService {

    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, AccountCreditDTO accountCreditDTO);
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, AccountDebitDTO accountDebitDTO);
    //public CompletableFuture<String> transfer(TransactionDTO transaction);
}
