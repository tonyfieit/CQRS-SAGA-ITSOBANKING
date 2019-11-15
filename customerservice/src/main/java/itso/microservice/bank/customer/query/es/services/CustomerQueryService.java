package itso.microservice.bank.customer.query.es.services;

import java.util.List;

import itso.microservice.bank.customer.query.es.entities.CustomerQueryEntity;



public interface CustomerQueryService {
	 public List<Object> listEventsForCustomer(String customer_ssn);
	    public CustomerQueryEntity getCustomer(String customer_ssn);
}
