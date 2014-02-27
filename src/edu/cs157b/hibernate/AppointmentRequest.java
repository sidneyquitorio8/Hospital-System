package edu.cs157b.hibernate;

import java.util.ArrayList;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="APPOINTMENT_REQUEST")
@NamedQueries (
	{
		@NamedQuery(name = "AppointmentRequest.getAll", query = "from AppointmentRequest"),
		@NamedQuery(name = "AppointmentRequest.findByDoctorId", query = "from AppointmentRequest where doctor_id = :doctor_id"),
		@NamedQuery(name = "AppointmentRequest.findByPatientId", query = "from AppointmentRequest where patient_id = :patient_id")
	}
)
public class AppointmentRequest {
	 
	private int id;
	
	private Doctor doctor;
	private Patient patient;
	private boolean fulfilled = false;
	 
	public boolean isFulfilled() {
		return fulfilled;
	}
	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne (fetch = FetchType.EAGER, cascade= CascadeType.PERSIST) 
	@JoinColumn(name="doctor_id") 
	public Doctor getDoctor() {
		return doctor;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	@ManyToOne (fetch = FetchType.EAGER, cascade= CascadeType.PERSIST) 
	@JoinColumn(name="patient_id") 
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}	 

}
