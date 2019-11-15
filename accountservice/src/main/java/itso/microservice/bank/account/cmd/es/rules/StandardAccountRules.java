package itso.microservice.bank.account.cmd.es.rules;

import java.math.BigDecimal;

import itso.microservice.bank.account.cmd.es.aggregates.AccountAggregate;

public class StandardAccountRules implements AccountRules {
	/*
	 * public boolean eligibleForDelete( AccountAggregate account) { if
	 * (!account.isActiveTransfer() &&
	 * account.getBalance().compareTo(BigDecimal.ZERO) == 0) { return true; } return
	 * false; }
	 */
    public boolean eligibleForDebit(AccountAggregate account, double debitAmount) {
    	
    	BigDecimal debitAmount_ = BigDecimal.valueOf(debitAmount);
    	BigDecimal difference = BigDecimal.valueOf(account.getBalance()).subtract(debitAmount_);
    	
       // BigDecimal difference = account.getBalance().subtract(BigDecimal.valueOf(debitAmount));
        if (account.getStatus().equals("ACTIVATED") && difference.compareTo(BigDecimal.ZERO) >= 0) {
            return true;   
            //account.
        }
        return false;
    }

    public boolean eligibleForCredit(AccountAggregate account, double creditAmount) {
    	//BigDecimal debitAmount_ = BigDecimal.valueOf(creditAmount);
    	
        BigDecimal sum = BigDecimal.valueOf(account.getBalance()).add(BigDecimal.valueOf(creditAmount));
        if (sum.compareTo(BigDecimal.valueOf(Double.MAX_VALUE)) <= 0) {
            return true;
        }
        return false;
    }
}
