package itso.microservice.bank.account.cmd.es.clients;

import org.springframework.web.client.RestTemplate;

public class CustomerDetailsClient {

	 //private final RestTemplate restTemplate;
 //   @Value("${preferences.api.url:http://preference:8081}")
	//    private String remoteURL;
	    
	  
	    public CustomerDetails getCustomer(String ssn) {
	    	
	    	RestTemplate template = new RestTemplate();
	    	
	    	CustomerDetails customerDetails = template.getForObject("http://localhost:8000/api/v1/customers/"+ssn+"", CustomerDetails.class);
	    	
	    	System.out.println(" <============ customerDetails.toString() =============>");
	    	System.out.println( customerDetails.toString());
	    	return customerDetails;
	    	
	    }
}
