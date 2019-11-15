package itso.microservice.bank.account.cmd.es.commands;

public class CreditAccountCommand extends BaseCommand<String> {

	private double creditAmount;
	private boolean transfer = false;;
	
	public CreditAccountCommand(String id, double creditAmount) {
		super(id);
		this.creditAmount = creditAmount;
		
	}



	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public boolean isTransfer() {
		return transfer;
	}

	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}

	
}
