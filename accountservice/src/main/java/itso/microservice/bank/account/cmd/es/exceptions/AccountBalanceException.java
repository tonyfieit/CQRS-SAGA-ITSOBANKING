package itso.microservice.bank.account.cmd.es.exceptions;

public class AccountBalanceException extends Exception {
	public AccountBalanceException(String message) {
		super(message);
	}
}