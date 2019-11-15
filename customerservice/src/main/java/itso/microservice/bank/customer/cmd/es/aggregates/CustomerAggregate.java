package itso.microservice.bank.customer.cmd.es.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import itso.microservice.bank.customer.cmd.es.commands.CreateCustomerCommand;
import itso.microservice.bank.customer.cmd.es.commands.UpdateCustomerProfileCommand;
import itso.microservice.bank.customer.cmd.es.events.CustomerCreatedDateEvent;
import itso.microservice.bank.customer.cmd.es.events.CustomerCreatedEvent;
import itso.microservice.bank.customer.cmd.es.events.CustomerUpdatedEvent;


@Aggregate
public class CustomerAggregate {
	@AggregateIdentifier
	private String customer_ssn;
	private String title;
	private String first_name;
	private String last_name;
	private String recordCreationDate;

	

	public CustomerAggregate() {
	}

	@CommandHandler
	public CustomerAggregate(CreateCustomerCommand createCustomerCommand) {
		System.out.println("@@@@222<@CommandHandle> inside CustomerAggregate createCustomerCommand" + createCustomerCommand);
		
		Assert.notNull(createCustomerCommand.customer_ssn, " Customer SSN should not be null or empty");
		Assert.notNull(createCustomerCommand.title, " Title should not be null or empty");
		Assert.notNull(createCustomerCommand.first_name, " First Name should not be null or empty");
		Assert.notNull(createCustomerCommand.last_name, " Last Name should not be null or empty");

		
		AggregateLifecycle.apply(new CustomerCreatedEvent(createCustomerCommand.customer_ssn, createCustomerCommand.title,
				createCustomerCommand.first_name, createCustomerCommand.last_name));
		System.out.println(
				"@@@@222<@CommandHandler> inside CustomerAggregate createCustomerCommand --> successfully ran!");
	}

	@EventSourcingHandler
	protected void on(CustomerCreatedEvent CustomerCreatedEvent) {

	System.out.println("@@@@333 <<@EventSourcingHandler>> inside CustomerAggregate CustomerCreatedEvent" + CustomerCreatedEvent);
	
		this.customer_ssn = CustomerCreatedEvent.customer_ssn;
		this.title = CustomerCreatedEvent.title;
		this.first_name = CustomerCreatedEvent.first_name;
		this.last_name = CustomerCreatedEvent.last_name;
		this.recordCreationDate =new RecordCreationDate().getCreatedDate();
				
	    AggregateLifecycle.apply(new CustomerCreatedDateEvent(this.customer_ssn,new RecordCreationDate()));
		
			System.out.println(
				"@@@@333<<@EventSourcingHandler>> inside CustomerAggregate CustomerCreatedEvent --> successfully ran!");
	}

	@CommandHandler
	public void handle(UpdateCustomerProfileCommand updateCustomerCommand) {
		
		Assert.notNull(updateCustomerCommand.customer_ssn, " Customer SSN should not be null or empty");
		Assert.notNull(updateCustomerCommand.title, " Title should not be null or empty");
		Assert.notNull(updateCustomerCommand.first_name, " First Name should not be null or empty");
		Assert.notNull(updateCustomerCommand.last_name, " Last Name should not be null or empty");

		AggregateLifecycle.apply(new CustomerUpdatedEvent(updateCustomerCommand.customer_ssn,  updateCustomerCommand.title,
				updateCustomerCommand.first_name, updateCustomerCommand.last_name));

	}

	@EventSourcingHandler
	public void on(CustomerUpdatedEvent CustomerUpdatedEvent) {
		
		
		this.title = CustomerUpdatedEvent.title;
		this.first_name = CustomerUpdatedEvent.first_name;
		this.last_name = CustomerUpdatedEvent.last_name;
		this.recordCreationDate =new RecordCreationDate().getCreatedDate();
		
		// AggregateLifecycle.apply(CustomerUpdatedEvent);
	}

	public String getCustomer_ssn() {
		return customer_ssn;
	}

	public void setCustomer_ssn(String customer_ssn) {
		this.customer_ssn = customer_ssn;
	}
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public String getRecordCreationDate() {
		return recordCreationDate;
	}

	public void setRecordCreationDate(String recordCreationDate) {
		this.recordCreationDate = recordCreationDate;
	}

	/*
	 * private void applyEvent(CustomerCreatedDateEvent event) {
	 * AggregateLifecycle.apply(event); }
	 */
}
