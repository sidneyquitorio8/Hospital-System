package edu.cs157b.hibernate;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
 
@Entity
@Table(name = "APPOINTMENT_REQUEST")
@AssociationOverrides({
		@AssociationOverride(name = "pk.patient", 
			joinColumns = @JoinColumn(name = "PATIENT_ID")),
		@AssociationOverride(name = "pk.doctor", 
			joinColumns = @JoinColumn(name = "DOCTOR_ID")) })
public class AppointmentRequest implements java.io.Serializable {
 
	private AppointmentRequestId pk = new AppointmentRequestId();
	private Date appointmentDate;
	private boolean fulfilled;
 
	public AppointmentRequest() {
	}
 
	@EmbeddedId
	public AppointmentRequestId getPk() {
		return pk;
	}
 
	public void setPk(AppointmentRequestId pk) {
		this.pk = pk;
	}
 
	@Transient
	public Patient getPatient() {
		return getPk().getPatient();
	}
 
	public void setPatient(Patient patient) {
		getPk().setPatient(patient);
	}
 
	@Transient
	public Doctor getDoctor() {
		return getPk().getDoctor();
	}
 
	public void setDoctor(Doctor doctor) {
		getPk().setDoctor(doctor);
	}
 
	@Temporal(TemporalType.DATE)
	@Column(name = "APPOINTMENT_DATE", nullable = false, length = 10)
	public Date getAppointmentDate() {
		return this.appointmentDate;
	}
 
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
 
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
 
		AppointmentRequest that = (AppointmentRequest) o;
 
		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;
 
		return true;
	}
 
	public boolean isFulfilled() {
		return fulfilled;
	}

	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}