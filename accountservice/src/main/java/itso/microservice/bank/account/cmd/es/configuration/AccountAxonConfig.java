package itso.microservice.bank.account.cmd.es.configuration;



import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import itso.microservice.bank.account.cmd.es.aggregates.AccountAggregate;

@Configuration
public class AccountAxonConfig {

    @Bean
    EventSourcingRepository<AccountAggregate> accountAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<AccountAggregate> repository = EventSourcingRepository.builder(AccountAggregate.class).eventStore(eventStore).build();
        return repository;
    }
}
