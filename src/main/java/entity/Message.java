package entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import listener.LastUpdateListener;
import listener.NotificationListener;

@Entity
@EntityListeners({LastUpdateListener.class, NotificationListener.class})
public class Message {
	
	//for Hibernate 4.3.x Users
	/*
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	*/
	
	// for Hibernate 5.x Users
	// if you're using Hibernate 5.x, use GenerationType.IDENTITY id generator strategy explicitly
	// for more information on "GenerationType" have a look at https://www.udemy.com/hibernate-and-jpa-fundamentals/learn/v4/questions/937412
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	private String text;	
	
	//for Hibernate 5.x Users	
	@Column(name="last_update")
	private LocalDateTime lastUpdate;	
	
	//for Hibernate 4.3.x Users
	/*
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update")
	private Calendar lastUpdate;
	*/
	
	public Message() {}	
	public Message(String text) {
		this.text = text;
	}
	
	//for Hibernate 5.x Users	
	/*
	@PrePersist
	@PreUpdate
	public void setLastUpdate() {
		this.lastUpdate = LocalDateTime.now();
		System.out.println("Message#setLastUpdate() called. lastUpdate = " + lastUpdate);   
	}
	*/
	//for Hibernate 4.3.x Users
	/*
	@PrePersist
	@PreUpdate
	public void setLastUpdate() {
		this.lastUpdate = new GregorianCalendar();
		System.out.println("Message#setLastUpdate() called. lastUpdate = " + lastUpdate);   
	}
	*/
	
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	

	@Override
	public String toString() {
		return "Message [id=" + id + ", text=" + text + ", lastUpdate=" + lastUpdate + "]";
	}


}