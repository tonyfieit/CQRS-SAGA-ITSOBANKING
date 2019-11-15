package itso.microservice.bank.account.cmd.es.exceptions;

public class CustomerNotFoundException extends Exception {
    private String personId;

    public CustomerNotFoundException(String personId, String msg) {
        super(msg);
        this.personId = personId;
    }

    public String getPersonId() {
        return personId;
    }
}