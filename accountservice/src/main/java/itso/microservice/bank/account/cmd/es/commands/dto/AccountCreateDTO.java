package itso.microservice.bank.account.cmd.es.commands.dto;

public class AccountCreateDTO {

    private double balance;

    private String customer_ssn;

    public String getCustomer_ssn() {
		return customer_ssn;
	}

	public void setCustomer_ssn(String customer_ssn) {
		this.customer_ssn = customer_ssn;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	

    
}
