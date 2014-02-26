package edu.cs157b.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

public class ServiceLayer {
	
	public static ConcreteMedicalDAO dao = new ConcreteMedicalDAO();
	
	public static String createDoctor(String name) {
		String result = "Doctor " + name + " Created";
		try {
			dao.createDoctor(name);
		}
		catch(ConstraintViolationException e) {
			result = "Doctor " + name + " is already in the database";
		}
		return result;
	}
	
	public static String listDoctors() {
		String result = "List of Doctors:\n\n";
		ArrayList<Doctor> doctors = dao.getListOfDoctors();
		for(Doctor doctor:doctors) {
			result += doctor.getName() + "\n";
		}
		return result;
	}
	
	public static String doctorInfoByName(String name) {
		String info = "";
		Doctor doctor = dao.getDoctor(name);
		try {
			info = "Name: " + doctor.getName() + "\n";
			info += "Specialty " + doctor.getSpecialty().getName() + "\n";
		}
		catch(NullPointerException e) {
			info = "Doctor not found";
		}
		return info;
	}
	
	public static String doctorsBySpecialty(String name) {
		Specialty specialty = dao.getSpecialty(name);
		String result = "List of Doctors that specialize in: " + specialty.getName() + "\n\n";
		ArrayList<Doctor> doctors = dao.doctorsBySpecialty(specialty);
		for(Doctor doctor:doctors) {
			result = doctor.getName() + "\n";
		}
		
		return result;
	}
	
	public static String createSpecialty(String name) {
		String result = "Specialty " + name + " was created";
		
		try {
			dao.createSpecialty(name);		
		}
		catch(ConstraintViolationException e) {
			result = "The specialty already exist";
		}
		
		return result;
	}
	
	public static String assignSpecialty(String doctor_name, String specialty_name) {
		String result = "Doctor " + doctor_name + " now specializes in " + specialty_name;

		Doctor doctor = dao.getDoctor(doctor_name);
		Specialty specialty = dao.getSpecialty(specialty_name);
		if(doctor == null) {
			result = "Doctor not found";
		}
		else if(specialty == null) {
			result = "Specialty not found";
		}
		else {
			dao.assignDoctorSpecialty(doctor, specialty);
		}
		return result;
	}
	
	public static void closeConnection() {
		dao.shutdownConnection();
	}
	
	public static String tester() {
		String result = "";

		Doctor doctor1 = dao.createDoctor("Sidney1");
		Doctor doctor2 = dao.createDoctor("Bob");
		
		Specialty specialty1 = dao.createSpecialty("Pediatrics");
		dao.assignDoctorSpecialty(doctor1, specialty1);
		Specialty specialty = dao.getSpecialty("Pediatrics");
		List<Doctor> doctors = specialty.getDoctors();
		for(Doctor doctor:doctors) {
			result += doctor.getName() + "\n";
		}
		
		return result;
	}

}
