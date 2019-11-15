package itso.microservice.bank.account.query.es.entities.handlers;


import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import itso.microservice.bank.account.cmd.es.aggregates.AccountAggregate;
import itso.microservice.bank.account.cmd.es.events.BaseEvent;
import itso.microservice.bank.account.query.es.entities.AccountQueryEntity;
import itso.microservice.bank.account.query.es.entities.repositories.AccountRepository;

@Component
public class AccountQueryEntityManager {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    @Qualifier("accountAggregateEventSourcingRepository")
    private EventSourcingRepository<AccountAggregate> accountAggregateEventSourcingRepository;

    @EventSourcingHandler
    void on(BaseEvent event){
    	System.out.println("@@@@11<@EventSourcingHandler> inside AccountQueryEntityManager on(BaseEvent event)" + event );
        persistAccount(buildQueryAccount(getAccountFromEvent(event)));
    }


    private AccountAggregate getAccountFromEvent(BaseEvent event){
    	System.out.println("@@@@11<@Override> inside AccountQueryEntityManager getAccountFromEvent(...)" + event.id.toString() );
        return accountAggregateEventSourcingRepository.load(event.id.toString()).getWrappedAggregate().getAggregateRoot();
    }

    private AccountQueryEntity findExistingOrCreateQueryAccount(String id){
        return accountRepository.findById(id).isPresent() ? accountRepository.findById(id).get() : new AccountQueryEntity();
    }

    private AccountQueryEntity buildQueryAccount(AccountAggregate accountAggregate){
    	System.out.println("@@@@11<@Override> inside AccountQueryEntityManager buildQueryAccount(...)" + accountAggregate );
        AccountQueryEntity accountQueryEntity = findExistingOrCreateQueryAccount(accountAggregate.getId());

        accountQueryEntity.setId(accountAggregate.getId());
        accountQueryEntity.setBalance(accountAggregate.getBalance());
        accountQueryEntity.setCustomer_ssn(accountAggregate.getCustomer_ssn());;
        accountQueryEntity.setStatus(accountAggregate.getStatus());
           
        System.out.println("@@@@11<@Override> inside AccountQueryEntityManager buildQueryAccount(...) return object" + accountQueryEntity );
        return accountQueryEntity;
    }

    private void persistAccount(AccountQueryEntity accountQueryEntity){
    	System.out.println("@@@@<@Override> inside AccountQueryEntityManager persistAccount(...)" + accountQueryEntity );
        accountRepository.save(accountQueryEntity);
        
        System.out.println("@@@@<@Override> inside AccountQueryEntityManager persistAccount(...) after save object" + accountQueryEntity.getId() );
    }
}
