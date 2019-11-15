package itso.microservice.bank.account.query.es.entities.repositories;


import org.springframework.data.repository.CrudRepository;

import itso.microservice.bank.account.query.es.entities.AccountQueryEntity;

public interface AccountRepository extends CrudRepository<AccountQueryEntity, String> {
}
