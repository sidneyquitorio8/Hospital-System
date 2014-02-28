package edu.cs157b.hibernate;

import java.util.ArrayList;
import java.util.Calendar;

public interface MedicalDAO {

	public Doctor createDoctor(String name);
	
	public ArrayList<Doctor> getListOfDoctors();
	
	public Doctor getDoctor(String name);
	
	public ArrayList<Doctor> doctorsBySpecialty(Specialty specialty);
	
	public Specialty getSpecialty(String name);
	
	public Specialty createSpecialty(String name);
	
	public ArrayList<Specialty> getListofSpecialties();
	
	public void assignDoctorSpecialty(Doctor doctor, Specialty specialty);
	
	public void updateDoctor(Doctor doctor, String name, Specialty specialty);
	
	public void removeDoctorSpecialtyAssociation(Doctor doctor, Specialty specialty);
	
	public void updateSpecialty(Specialty specialty, String new_name);
	
	public Patient createPatient(String name);
	
	public ArrayList<Patient> getListOfPatients();
	
	public Patient getPatient(String name);

	public void updatePatient(Patient patient, String name);
	
	public void deleteDoctor(String doctor);
	
	public void deleteSpecialty(String specialty);
	
	public void deletePatient(String patient);
	
	public AppointmentRequest createAppointmentRequest(Patient patient, Doctor doctor, Calendar time);
	
	public AppointmentRequest getAppointmentById(int id);
	
	public void deleteAppointment(int id);
	
}
