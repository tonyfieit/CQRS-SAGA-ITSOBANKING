package itso.microservice.bank.account.cmd.es.events;

public class AccountDebitedEvent extends BaseEvent<String> {

  

	private double debitAmount;

   public AccountDebitedEvent(String id, double debitAmount) {
        super(id);
        this.debitAmount = debitAmount;
       
    }
   
   public double getDebitAmount() {
 		return debitAmount;
 	}

 	public void setDebitAmount(double debitAmount) {
 		this.debitAmount = debitAmount;
 	}
}
