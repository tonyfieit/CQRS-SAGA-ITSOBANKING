package itso.microservice.bank.account.cmd.es.commands;

public class CreateAccountCommand extends  BaseCommand<String>{

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

	private double balance;

    private String customer_ssn;

    public CreateAccountCommand(String id, double balance, String customer_ssn) {
        super(id);
        this.balance = balance;
        this.customer_ssn = customer_ssn;
    }
}
