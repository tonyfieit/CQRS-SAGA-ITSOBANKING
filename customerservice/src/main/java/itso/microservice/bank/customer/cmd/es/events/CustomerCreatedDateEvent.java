package itso.microservice.bank.customer.cmd.es.events;

import itso.microservice.bank.customer.cmd.es.aggregates.RecordCreationDate;

public class CustomerCreatedDateEvent extends BaseEvent<String> {
	
	 public final  RecordCreationDate recordCreationDate;
	
	public CustomerCreatedDateEvent(String customer_ssn, RecordCreationDate recordCreationDate) {
		super(customer_ssn);
		this.recordCreationDate =recordCreationDate;
	}


}
