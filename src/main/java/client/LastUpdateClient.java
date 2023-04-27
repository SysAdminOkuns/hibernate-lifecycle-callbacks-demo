package client;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Person;

import static java.lang.Thread.sleep;

public class LastUpdateClient {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(
				"hello-world");
		EntityManager em1 = emf.createEntityManager();
		em1.getTransaction().begin();
		
		//for Hibernate 4.3.x Users
		/*
		Person person = new Person("Harry Bates", new GregorianCalendar(1983, 8, 8));
		em1.persist(person);
		*/
		
		//for Hibernate 5.x Users
		Person person = new Person(
				"Harry Bates", LocalDate.of(1983, 8, 8));
		em1.persist(person);
		
		em1.getTransaction().commit();
		em1.close();

//		sleep(10000);
		
		EntityManager em2 = emf.createEntityManager();
		em2.getTransaction().begin();
		
		Person loadedPerson = em2.find(Person.class, 1L);
		loadedPerson.setName("Harry Jr Bates");
		
		em2.getTransaction().commit();
		em2.close();
		emf.close();
	}
}