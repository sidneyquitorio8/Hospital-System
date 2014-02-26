package edu.cs157b.hibernate;

import java.util.ArrayList;

public interface MedicalDAO {

	public Doctor createDoctor(String name);
	
	public ArrayList<Doctor> getListOfDoctors();
	
	public Doctor getDoctor(String name);
	
	public ArrayList<Doctor> doctorsBySpecialty(Specialty specialty);
	
	public Specialty getSpecialty(String name);
	
	public Specialty createSpecialty(String name);
	
	public ArrayList<Specialty> getListofSpecialties();
	
	public void assignDoctorSpecialty(Doctor doctor, Specialty specialty);
	
	
}
