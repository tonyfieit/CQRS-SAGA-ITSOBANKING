package itso.microservice.bank.account.cmd.es.aggregates;


import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import itso.microservice.bank.account.cmd.es.commands.CreateAccountCommand;
import itso.microservice.bank.account.cmd.es.commands.CreditAccountCommand;
import itso.microservice.bank.account.cmd.es.commands.DebitAccountCommand;
import itso.microservice.bank.account.cmd.es.events.AccountActivatedEvent;
import itso.microservice.bank.account.cmd.es.events.AccountCreatedEvent;
import itso.microservice.bank.account.cmd.es.events.AccountCreditedEvent;
import itso.microservice.bank.account.cmd.es.events.AccountDebitedEvent;
import itso.microservice.bank.account.cmd.es.events.AccountHeldEvent;
import itso.microservice.bank.account.cmd.es.exceptions.AccountNotEligibleForDebitException;
import itso.microservice.bank.account.cmd.es.rules.AccountRules;
import itso.microservice.bank.account.cmd.es.rules.StandardAccountRules;

@Aggregate
public class AccountAggregate {

	Logger logger = LoggerFactory.getLogger(AccountAggregate.class);

	@AggregateIdentifier
	private String id;
	private double balance;
	private String customer_ssn;
	private String status;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getCustomer_ssn() {
		return customer_ssn;
	}

	public void setCustomer_ssn(String customer_ssn) {
		this.customer_ssn = customer_ssn;
	}

	private AccountRules accountRules = new StandardAccountRules();

	public AccountAggregate() {
	}

	/*
	 * @CommandHandler CreateAccountCommand This command is used to make a deposit
	 * to a single account
	 */
	@CommandHandler
	public AccountAggregate(CreateAccountCommand createAccountCommand) {

		AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.id, createAccountCommand.getBalance(),
				createAccountCommand.getCustomer_ssn()));
	}

	/*
	 * @EventSourcingHandler AccountCreatedEvent
	 */
	@EventSourcingHandler
	protected void on(AccountCreatedEvent accountCreatedEvent) {
		this.id = accountCreatedEvent.id;
		this.balance = accountCreatedEvent.getBalance();
		this.customer_ssn = accountCreatedEvent.getCustomer_ssn();
		this.status = String.valueOf(Status.CREATED);

		AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
	}

	@EventSourcingHandler
	protected void on(AccountActivatedEvent accountActivatedEvent) {
		this.status = String.valueOf(accountActivatedEvent.getStatus());
	}

	/*
	 * @CommandHandler DebitAccountCommand This is for the transfer from one account
	 * to another
	 */

	@CommandHandler
	protected void on(CreditAccountCommand creditAccountCommand) {

		logger.info("Handling Credit Command for " + creditAccountCommand.id);
		if (!this.accountRules.eligibleForCredit(this, creditAccountCommand.getCreditAmount())) {

			if (creditAccountCommand.isTransfer()) {

				logger.info("@@@@@@@22 inside Handling Credit Command for creditAccountCommand.isTransfer() (step1)" + creditAccountCommand.isTransfer());
				/*
				 * AggregateLifecycle.apply(new TransferFailedEvent(creditAccountCommand.id,
				 * creditAccountCommand.id,
				 * String.format("Account %s not eligible for deposit.")));
				 */
			} else {
				logger.info("@@@@@@@22 inside Handling Credit Command for creditAccountCommand.isTransfer() (step2)" + creditAccountCommand.isTransfer());
				/*
				 * AggregateLifecycle.apply(new TransactionFailedEvent(creditAccountCommand.id,
				 * creditAccountCommand.id,
				 * String.format("Account %s balance not eligible for deposit.")));
				 */
			}

		}
		if (!creditAccountCommand.isTransfer()) {

			logger.info("@@@@@@@22 inside Handling Credit Command for creditAccountCommand.isTransfer() (step3)" + creditAccountCommand.isTransfer());
			AggregateLifecycle.apply(
					new AccountCreditedEvent(creditAccountCommand.id, creditAccountCommand.getCreditAmount() ));

		} else {
			
			logger.info("@@@@@@@22 inside Handling Credit Command for creditAccountCommand.isTransfer() (step4)" + creditAccountCommand.isTransfer());

			AggregateLifecycle.apply(
					new AccountCreditedEvent(creditAccountCommand.id, creditAccountCommand.getCreditAmount()));
		}

	}

	@EventSourcingHandler
	protected void on(AccountCreditedEvent moneyCreditedEvent) {

		if (this.balance < 0 & (this.balance + moneyCreditedEvent.getCreditAmount()) >= 0) {
			AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
		}

		this.balance += moneyCreditedEvent.getCreditAmount();
	}

	/*
	 * @CommandHandler DebitAccountCommand This is for the transfer from one account
	 * to another
	 */

	@CommandHandler
	protected void on(DebitAccountCommand debitAccountCommand) throws Exception {
		logger.info("Handling Debit Command for " + debitAccountCommand.id);
		if (!this.accountRules.eligibleForDebit(this, debitAccountCommand.getDebitAmount())) {
			if (debitAccountCommand.isTransfer()) {

				logger.info("@@@@@@@12 inside Handling Debit Command for debitAccountCommand.isTransfer() (step1)" + debitAccountCommand.isTransfer());
				/*
				 * AggregateLifecycle.apply(new TransferFailedEvent(debitAccountCommand.id,
				 * debitAccountCommand.id,
				 * String.format("Account %s balance not eligible for withdraw.")));
				 */

			} else {
				logger.info("@@@@@@@13 inside Handling Debit Command for debitAccountCommand.isTransfer() (step2)" + debitAccountCommand.isTransfer());
				/*
				 * AggregateLifecycle.apply(new TransactionFailedEvent(debitAccountCommand.id,
				 * debitAccountCommand.id,
				 * String.format("Account %s balance not eligible for withdraw.")));
				 */
			}

			throw new AccountNotEligibleForDebitException(id, balance);
		}

		if (debitAccountCommand.isTransfer()) {

			logger.info("@@@@@@@14 inside Handling Debit Command for debitAccountCommand.isTransfer() (step3)" + debitAccountCommand.isTransfer());
			AggregateLifecycle
					.apply(new AccountDebitedEvent(debitAccountCommand.id, debitAccountCommand.getDebitAmount()));

		} else {
			
			logger.info("@@@@@@@15 inside Handling Debit Command for debitAccountCommand.isTransfer() (step4)" + debitAccountCommand.isTransfer());

			AggregateLifecycle
					.apply(new AccountDebitedEvent(debitAccountCommand.id, debitAccountCommand.getDebitAmount()));

		}

	}

	@EventSourcingHandler
	protected void on(AccountDebitedEvent accountDebitedEvent) {

		if (this.balance >= 0 & (this.balance - accountDebitedEvent.getDebitAmount()) < 0) {
			AggregateLifecycle.apply(new AccountHeldEvent(this.id, Status.HOLD));
		}

		this.balance -= accountDebitedEvent.getDebitAmount();

	}

	
	   
	public void setAccountRules(AccountRules accountRules) {
		this.accountRules = accountRules;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
