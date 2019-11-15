package itso.microservice.bank.customer.query.es.entities;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class CustomerQueryEntity {

	@Id
	private String customer_ssn;
	private String title;
	private String first_name;
	private String last_name;
	private String CreatedDate;

	//recordCreationDate =new RecordCreationDate().getTodayDate();
	public CustomerQueryEntity() {
	    }
	
	
	
		
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
	
	public String getCustomer_ssn() {
		return customer_ssn;
	}

	public void setCustomer_ssn(String customer_ssn) {
		this.customer_ssn = customer_ssn;
	}

	/*
	 * @Override public String toString() { return "CustomerQueryEntity [ssn=" + id
	 * + ", title=" + title + ", first_name=" + first_name + ", last_name=" +
	 * last_name + "]"; }
	 */
	
	@Override
    public String toString() {
        return "CustomerQueryEntity{" +
                "customer_ssn='" + customer_ssn + '\'' +
                ", title=" + title +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }




	public String getCreatedDate() {
		return CreatedDate;
	}




	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}

}
