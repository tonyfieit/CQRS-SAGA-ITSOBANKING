package itso.microservice.bank.account.cmd.es.exceptions;

import java.util.UUID;

public class AccountActiveException extends Throwable {
    private String id;

    public AccountActiveException(String id) {
        this.id = id;
    }

    public String getMessage() {
        return String.format("Account %s inactive and not eligible for transaction", id);
    }
}