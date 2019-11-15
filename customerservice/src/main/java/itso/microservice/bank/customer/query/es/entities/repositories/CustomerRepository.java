package itso.microservice.bank.customer.query.es.entities.repositories;

import org.springframework.data.repository.CrudRepository;

import itso.microservice.bank.customer.query.es.entities.CustomerQueryEntity;



public interface CustomerRepository extends CrudRepository<CustomerQueryEntity, String> {

}
