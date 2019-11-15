package itso.microservice.bank.customer.cmd.es.events;

public class BaseEvent<T> {

    public final T customer_ssn;

    public BaseEvent(T customer_ssn) {
        this.customer_ssn = customer_ssn;
    }
}
