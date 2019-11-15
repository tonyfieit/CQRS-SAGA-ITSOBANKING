package itso.microservice.bank.customer.cmd.es.commands;

public class UpdateCustomerProfileCommand extends BaseCommand<String> {
	
	
	public final String title;
	public final String first_name;
	public final String last_name;
	
	public UpdateCustomerProfileCommand(String customer_ssn , String title, String first_name, String last_name) {
        super(customer_ssn);
        this.title = title;
        this.first_name = first_name;
        this.last_name = last_name;
    }

}
