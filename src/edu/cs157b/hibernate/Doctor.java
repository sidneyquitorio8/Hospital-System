package edu.cs157b.hibernate;

import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name="DOCTOR_INFO")
@NamedQueries (
	{
		@NamedQuery(name = "Doctor.getAll", query = "from Doctor"),
		@NamedQuery(name = "Doctor.findByName", query = "from Doctor where name = :name")
	}
)
public class Doctor implements Person {

	private int id;
	private String name;
	private Specialty specialty; 

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne (fetch = FetchType.EAGER, cascade= CascadeType.PERSIST) 
	@JoinColumn(name="specialty_id") 
	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
}
