package itso.microservice.bank.account.cmd.es.events;

import itso.microservice.bank.account.cmd.es.aggregates.Status;

public class AccountHeldEvent extends BaseEvent<String> {

    private Status status;

    public AccountHeldEvent(String id, Status status) {
        super(id);
        this.setStatus(status);
    }

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
