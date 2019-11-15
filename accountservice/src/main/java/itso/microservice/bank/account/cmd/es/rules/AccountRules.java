package itso.microservice.bank.account.cmd.es.rules;

import itso.microservice.bank.account.cmd.es.aggregates.AccountAggregate;

public interface AccountRules {
	// boolean eligibleForDelete(AccountAggregate account);

	boolean eligibleForDebit(AccountAggregate account, double debitAmount);

	boolean eligibleForCredit(AccountAggregate account, double creditAmount);

}
