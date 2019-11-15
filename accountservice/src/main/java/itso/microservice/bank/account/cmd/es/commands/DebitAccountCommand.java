package itso.microservice.bank.account.cmd.es.commands;

public class DebitAccountCommand extends BaseCommand<String> {

	private double debitAmount;
	private boolean transfer= false;
	
	public DebitAccountCommand(String id, double debitAmount) {
		super(id);
		this.debitAmount = debitAmount;
		

	}

	

	public double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public boolean isTransfer() {
		return transfer;
	}

	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}

	
}
