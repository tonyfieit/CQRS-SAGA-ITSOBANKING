package itso.microservice.bank.account.query.es.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccountQueryEntity {

	@Id
	private String id;
	private double balance;
	private String customer_ssn;
	private String status;

	public AccountQueryEntity() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomer_ssn() {
		return customer_ssn;
	}

	public void setCustomer_ssn(String customer_ssn) {
		this.customer_ssn = customer_ssn;
	}

	@Override
	public String toString() {
		return "AccountQueryEntity [id=" + id + ", balance=" + balance + ", customer_ssn=" + customer_ssn + ", status="
				+ status + "]";
	}

}
