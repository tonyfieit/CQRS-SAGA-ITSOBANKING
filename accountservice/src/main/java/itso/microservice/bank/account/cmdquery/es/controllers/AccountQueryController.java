package itso.microservice.bank.account.cmdquery.es.controllers;


import io.swagger.annotations.Api;
import itso.microservice.bank.account.query.es.entities.AccountQueryEntity;
import itso.microservice.bank.account.query.es.services.AccountQueryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/accounts")
@Api(value = "Account Queries", description = "Account Query Events Endpoint", tags = "Account Queries")
public class AccountQueryController {

    private final AccountQueryService accountQueryService;

    public AccountQueryController(AccountQueryService accountQueryService) {
        this.accountQueryService = accountQueryService;
    }

    @GetMapping("/{accountNumber}")
    public AccountQueryEntity getAccount(@PathVariable(value = "accountNumber") String accountNumber){
        return accountQueryService.getAccount(accountNumber);
    }

    @GetMapping("/events/{accountNumber}")
    public List<Object> listEventsForAccount(@PathVariable(value = "accountNumber") String accountNumber){
        return accountQueryService.listEventsForAccount(accountNumber);
    }
    
}
