package itso.microservice.bank.customer.cmd.es.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {

	@TargetAggregateIdentifier
	public final T customer_ssn;

	public BaseCommand(T customer_ssn) {
		this.customer_ssn = customer_ssn;
	}
}
