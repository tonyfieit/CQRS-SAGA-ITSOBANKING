package itso.microservice.bank.account.cmd.es.commands.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionDTO {
    @NotNull
    @NotBlank
    private String id;
    @NotNull
    @NotBlank
    private String accountId;
    @NotNull
    @NotBlank
    private String customer_ssn;
    @Min(value = 0)
    private double amount;
    private String destinationAccountId;

    public TransactionDTO(
        @JsonProperty("id") @NotNull @NotBlank String id,
        @JsonProperty("accountId") @NotNull @NotBlank String accountId,
        @JsonProperty("customerId") @NotNull @NotBlank String customer_ssn,
        @JsonProperty("amount") double amount,
        @JsonProperty("destinationAccountId") String destinationAccountId) {
        this.id = id;
        this.accountId = accountId;
        this.customer_ssn = customer_ssn;
        this.amount = amount;
        this.destinationAccountId = destinationAccountId;
    }
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCustomer_ssn() {
		return customer_ssn;
	}

	public void setCustomer_ssn(String customer_ssn) {
		this.customer_ssn = customer_ssn;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDestinationAccountId() {
		return destinationAccountId;
	}

	public void setDestinationAccountId(String destinationAccountId) {
		this.destinationAccountId = destinationAccountId;
	}
}