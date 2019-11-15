package itso.microservice.bank.account.cmd.es.events;

public class AccountCreatedEvent extends BaseEvent<String>{

    private double balance;
    private String customer_ssn;

    public AccountCreatedEvent(String id, double balance, String customer_ssn) {
        super(id);
        this.balance = balance;
        this.customer_ssn = customer_ssn;
    }
    
    public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getCustomer_ssn() {
		return customer_ssn;
	}
}
