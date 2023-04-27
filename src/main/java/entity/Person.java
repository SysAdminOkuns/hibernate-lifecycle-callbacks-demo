package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

@Entity
public class Person {
	
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
	
	private String name;	
	
	//for Hibernate 5.x Users	
	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;
	
	//for Hibernate 4.3.x Users
	/*
    @Temporal(TemporalType.DATE)
    @Column(name="date_of_birth")
    private Calendar dateOfBirth;
	*/
	
	@Transient
	private Integer age;	
	
	//for Hibernate 5.x Users	
	@Column(name="last_update")
	private LocalDateTime lastUpdate;	
	
	//for Hibernate 4.3.x Users
	/*
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update")
	private Calendar lastUpdate;
	*/
	
	public Person() {}	
	public Person(String name, LocalDate dateOfBirth) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	//for Hibernate 5.x Users	
	@PostLoad
	public void calculateAge() {
		this.age = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
		System.out.println("@PostLoad called to calculate Person.age = " + age);   
	}	
	
	//for Hibernate 5.x Users	
	@PrePersist
	@PreUpdate
	public void setLastUpdate() {
		this.lastUpdate = LocalDateTime.now();
		System.out.println("Person#setLastUpdate() called to assign Person.lastUpdate = " + lastUpdate);   
	}
	
	//for Hibernate 4.3.x Users (calculating "age" using "LocalDate" and "Years" from joda-time-2.9.2.jar, not Java 8)
	/*
    @PostLoad
    public void calculateAge() {     
       LocalDate dob = new LocalDate(dateOfBirth.get(Calendar.YEAR), dateOfBirth.get(Calendar.MONTH), dateOfBirth.get(Calendar.DATE));
       LocalDate now = new LocalDate();
       this.age = Years.yearsBetween(dob, now).getYears();
       System.out.println("@PostLoad called to calculate Person.age = " + age);   
    }
    */
	
	//for Hibernate 4.3.x Users
	/*
	@PrePersist
	@PreUpdate
	public void setLastUpdate() {
		this.lastUpdate = new GregorianCalendar();
		System.out.println("Person#setLastUpdate() called to assign Person.lastUpdate = " + lastUpdate);   
	}
	*/
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", age=" + age + "]";
	}	
	
}



