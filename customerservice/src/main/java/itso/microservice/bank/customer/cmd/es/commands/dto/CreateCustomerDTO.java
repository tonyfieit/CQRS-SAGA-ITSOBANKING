package itso.microservice.bank.customer.cmd.es.commands.dto;

public class CreateCustomerDTO {

	

private String customer_ssn;
private String title;
private String first_name;
private String last_name;

	
	  public String getCustomer_ssn() { return customer_ssn; }
	  public void setCustomer_ssn(String customer_ssn) { this.customer_ssn =customer_ssn; }
	 
	
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	@Override
	public String toString() {
		return "CreateCustomerDTO [customer_ssn=" + customer_ssn + ", title=" + title + ", first_name=" + first_name + ", last_name="
				+ last_name + "]";
	}

}
