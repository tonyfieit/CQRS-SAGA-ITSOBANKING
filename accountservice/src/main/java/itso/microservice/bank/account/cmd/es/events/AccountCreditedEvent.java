package itso.microservice.bank.account.cmd.es.events;

public class AccountCreditedEvent extends BaseEvent<String> {

    private double creditAmount;

    

	public AccountCreditedEvent(String id, double creditAmount) {
        super(id);
        this.creditAmount = creditAmount;
       
    }
	 public double getCreditAmount() {
			return creditAmount;
		}

		public void setCreditAmount(double creditAmount) {
			this.creditAmount = creditAmount;
		}
}
