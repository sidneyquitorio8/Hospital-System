package edu.cs157b.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="PATIENT_INFO")
@NamedQueries (
	{
		@NamedQuery(name = "Patient.getAll", query = "from Patient"),
		@NamedQuery(name = "Patient.findByName", query = "from Patient where name = :name")
	}
)
public class Patient implements Person {

	private int id;
	private String name;
	private String medical_record;
	private List<AppointmentRequest> appointmentRequests = new ArrayList<AppointmentRequest>();

	public String getMedical_record() {
		return medical_record;
	}

	public void setMedical_record(String medical_record) {
		this.medical_record = medical_record;
	}

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
	
	@OneToMany(mappedBy="patient", targetEntity = AppointmentRequest.class, 
			 fetch=FetchType.EAGER, orphanRemoval=true, cascade= CascadeType.PERSIST) 
	public List<AppointmentRequest> getAppointmentRequests() {
		return this.appointmentRequests;
	}
 
	public void setAppointmentRequests(List<AppointmentRequest> appointmentRequests) {
		this.appointmentRequests = appointmentRequests;
	}
	
	@Transient
	public List<Doctor> getDoctors() {
		List<Doctor> doctors = new ArrayList<Doctor>();
		
		for(AppointmentRequest appointment:appointmentRequests) {
			doctors.add(appointment.getDoctor());
		}
		return doctors;
	}
}
