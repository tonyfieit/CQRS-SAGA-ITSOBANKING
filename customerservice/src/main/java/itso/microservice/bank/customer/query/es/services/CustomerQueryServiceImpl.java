package itso.microservice.bank.customer.query.es.services;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import itso.microservice.bank.customer.query.es.entities.CustomerQueryEntity;
import itso.microservice.bank.customer.query.es.entities.repositories.CustomerRepository;



@Service
public class CustomerQueryServiceImpl implements CustomerQueryService {
	
	 private final EventStore eventStore;

	    private final CustomerRepository customerRepository;

	    public CustomerQueryServiceImpl(EventStore eventStore, CustomerRepository customerRepository) {
	        this.eventStore = eventStore;
	        this.customerRepository = customerRepository;
	    }
	    
	@Override
	public List<Object> listEventsForCustomer(String customer_ssn ) {
		// TODO Auto-generated method stub
		return eventStore.readEvents(customer_ssn).asStream().map( s -> s.getPayload()).collect(Collectors.toList());
	}

	@Override
	public CustomerQueryEntity getCustomer(String customer_ssn) {
		// TODO Auto-generated method stub
		return customerRepository.findById(customer_ssn).get();
	}

}
