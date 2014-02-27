package edu.cs157b.hibernate;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
 
@Embeddable
public class AppointmentRequestId implements java.io.Serializable {
 
	private Patient patient;
    private Doctor doctor;
 
	@ManyToOne
	public Patient getPatient() {
		return patient;
	}
 
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
 
	@ManyToOne
	public Doctor getDoctor() {
		return doctor;
	}
 
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
 
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        AppointmentRequestId that = (AppointmentRequestId) o;
 
        if (patient != null ? !patient.equals(that.patient) : that.patient != null) return false;
        if (doctor != null ? !doctor.equals(that.doctor) : that.doctor != null)
            return false;
 
        return true;
    }
 
    public int hashCode() {
        int result;
        result = (patient != null ? patient.hashCode() : 0);
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        return result;
    }
 
}
