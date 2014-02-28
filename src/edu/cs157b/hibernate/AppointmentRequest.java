package edu.cs157b.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import javax.persistence.*;

import org.hibernate.annotations.Type;

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
	private Calendar time;
	private final SimpleDateFormat timestampFormat = new SimpleDateFormat("MM/dd/yyyy h a");
	
	
	public Calendar getTime() {
		return time;
	}
	
	@Transient
	public String getFormattedTime() {	
		String result = timestampFormat.format(time.getTime());
		return result;
	}
	
	public void setTime(Calendar time) {
		this.time = time;
	}
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
