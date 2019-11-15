package itso.microservice.bank.account.query.es.services;



import java.util.List;

import itso.microservice.bank.account.query.es.entities.AccountQueryEntity;

public interface AccountQueryService {
    public List<Object> listEventsForAccount(String accountNumber);
    public AccountQueryEntity getAccount(String accountNumber);
}
