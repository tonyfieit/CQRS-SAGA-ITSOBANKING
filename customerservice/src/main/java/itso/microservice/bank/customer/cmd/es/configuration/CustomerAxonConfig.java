package itso.microservice.bank.customer.cmd.es.configuration;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import itso.microservice.bank.customer.cmd.es.aggregates.CustomerAggregate;



@Configuration
public class CustomerAxonConfig {

	@Bean
	EventSourcingRepository<CustomerAggregate> customerAggregateEventSourcingRepository(EventStore eventStore) {
		EventSourcingRepository<CustomerAggregate> repository = EventSourcingRepository.builder(CustomerAggregate.class)
				.eventStore(eventStore).build();
		
	
		return repository;
	}
}
