package edu.cs157b.hibernate;

import java.util.ArrayList;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="SPECIALTY_INFO")
@NamedQueries (
	{
		@NamedQuery(name = "Specialty.getAll", query = "from Specialty"),
		@NamedQuery(name = "Specialty.findByName", query = "from Specialty where name = :name")
	}
)
public class Specialty {
	private List<Doctor> doctors = new ArrayList<Doctor>();
	 
	private int id;
	private String name;
	 
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

	@OneToMany(mappedBy="specialty", targetEntity = Doctor.class, 
			 fetch=FetchType.EAGER, cascade= CascadeType.PERSIST) 
	public List<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}	 

}
