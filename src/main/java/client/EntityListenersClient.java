package client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Message;

public class EntityListenersClient {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-world");		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Message msg = new Message("Hello @EntityListeners");
		em.persist(msg);
		
		em.getTransaction().commit();
		em.close();
	}
}