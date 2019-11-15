package itso.microservice.bank.account.cmd.es.events;

import itso.microservice.bank.account.cmd.es.aggregates.Status;

public class AccountActivatedEvent extends BaseEvent<String> {

   private Status status;

    public Status getStatus() {
	return status;
}

public void setStatus(Status status) {
	this.status = status;
}

	public AccountActivatedEvent(String id, Status status) {
        super(id);
        this.status = status;
       
    }
	
}
