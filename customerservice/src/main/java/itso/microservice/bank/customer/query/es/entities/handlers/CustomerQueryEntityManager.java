package itso.microservice.bank.customer.query.es.entities.handlers;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import itso.microservice.bank.customer.cmd.es.aggregates.CustomerAggregate;
import itso.microservice.bank.customer.cmd.es.events.BaseEvent;
import itso.microservice.bank.customer.query.es.entities.CustomerQueryEntity;
import itso.microservice.bank.customer.query.es.entities.repositories.CustomerRepository;



@Component
public class CustomerQueryEntityManager {

    @Autowired
    private CustomerRepository customerRepository;

    //@Autowired(required =false)
      @Autowired
      @Qualifier("customerAggregateEventSourcingRepository")
   
    private EventSourcingRepository<CustomerAggregate> customerAggregateEventSourcingRepository;

    @EventSourcingHandler
    void on(BaseEvent event){
    	System.out.println("@@@@11<@EventSourcingHandler> inside CustomerQueryEntityManager on(BaseEvent event)" + event );
		 persistCustomer(buildQueryCustomer(getCustomerFromEvent(event))); 
    }


    private CustomerAggregate getCustomerFromEvent(BaseEvent event){
    	
    	System.out.println("@@@@11<@Override> inside CustomerQueryEntityManager getCustomerFromEvent(...)" + event.customer_ssn.toString() );
    	CustomerAggregate custaggre = customerAggregateEventSourcingRepository.load(event.customer_ssn.toString()).getWrappedAggregate().getAggregateRoot();
    	
    	System.out.println("@@@@11<@Override> inside CustomerQueryEntityManager getCustomerFromEvent(...)" + custaggre);
        return custaggre;
    }

    private CustomerQueryEntity findExistingOrCreateQueryCustomer(String customer_ssn){
        return customerRepository.findById(customer_ssn).isPresent() ? customerRepository.findById(customer_ssn).get() : new CustomerQueryEntity();
    }

    private CustomerQueryEntity buildQueryCustomer(CustomerAggregate customerAggregate){
    	
    	System.out.println("@@@@<@Override> inside CustomerQueryEntityManager buildQueryCustomer(...)" + customerAggregate );
        CustomerQueryEntity customerQueryEntity = findExistingOrCreateQueryCustomer(customerAggregate.getCustomer_ssn());

		
		  customerQueryEntity.setCustomer_ssn(customerAggregate.getCustomer_ssn());;
		  customerQueryEntity.setTitle(customerAggregate.getTitle());
		  customerQueryEntity.setFirst_name(customerAggregate.getFirst_name());
		  customerQueryEntity.setLast_name(customerAggregate.getLast_name());
		  customerQueryEntity.setCreatedDate(customerAggregate.getRecordCreationDate());
		  
			System.out.println("@@@@<@Override> inside CustomerQueryEntityManager buildQueryCustomer(...) return object" + customerQueryEntity );

        return customerQueryEntity;
    }

    private void persistCustomer(CustomerQueryEntity customerQueryEntity){
    	System.out.println("@@@@<@Override> inside CustomerQueryEntityManager persistCustomer(...)" + customerQueryEntity );
    	
        customerRepository.save(customerQueryEntity);
        
        System.out.println("@@@@<@Override> inside CustomerQueryEntityManager persistCustomer(...) after save object" + customerQueryEntity.getCustomer_ssn() );
    }

}