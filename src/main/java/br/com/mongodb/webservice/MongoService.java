package br.com.mongodb.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.mongodb.Mongo;

import br.com.mongodb.model.Account;
import br.com.mongodb.model.Person;

@WebService(serviceName="MongoService")
public class MongoService extends HttpServlet{
	
	private static final long serialVersionUID = -8308487562730612088L;
	
	
	@Autowired
	MongoOperations mongoOperations;
	

	@WebMethod(operationName="create")
	public String create(){
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		if (mongoOperations.collectionExists(Person.class)) {
			mongoOperations.dropCollection(Person.class);
		}

		mongoOperations.createCollection(Person.class);

		Person p = new Person("John", 39);
		Account a = new Account("1234-59873-893-1", Account.Type.SAVINGS, 123.45D);
		p.getAccounts().add(a);

		mongoOperations.insert(p);

		List<Person> results = mongoOperations.findAll(Person.class);
		System.out.println("Results: " + results);
		return "Criado com sucesso...";
	}

}
