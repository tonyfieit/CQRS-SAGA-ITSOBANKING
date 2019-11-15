package itso.microservice.bank.customer.cmd.es.aggregates;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordCreationDate {

	public String getCreatedDate() {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
	return timeStamp;
	}
}
