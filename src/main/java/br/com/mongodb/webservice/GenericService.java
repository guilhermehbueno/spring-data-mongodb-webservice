package br.com.mongodb.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.mongodb.model.Person;

@WebService
public class GenericService {

	@Autowired
	MongoOperations mongoOperations;
	
	
	/**
	 * Servico que representa a busca em uma collection generica já realizando a conversao para um Person
	 * 	db.generic.insert({"name" : "generic name", "age"  : "24"})
	 */
	@WebMethod(operationName="genericSearch")
	public Person getAccountFromGenericColletion(){
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		return mongoOperations.findOne(new Query(), Person.class, "generic");
	}

}
