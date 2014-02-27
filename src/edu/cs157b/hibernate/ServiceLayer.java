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
	
	public static Doctor getDoctor(String name) {
		Doctor doctor = dao.getDoctor(name);
		return doctor;
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
		String info = "Doctors Information:\n\n";
		Doctor doctor = dao.getDoctor(name);
		try {
			info += "Name: " + doctor.getName() + "\n";
			info += "Specialty: ";
			if(doctor.getSpecialty() != null) {
				info += doctor.getSpecialty().getName() + "\n";
			}
		}
		catch(NullPointerException e) {
			info = "Doctor not found";
		}
		return info;
	}
	
	public static String doctorsBySpecialty(String specialty_name) {
		String result = specialty_name + " doctors:\n";
		Specialty specialty = dao.getSpecialty(specialty_name);
		if(specialty != null) {
	 		List<Doctor> doctors = specialty.getDoctors();
			for(Doctor doctor:doctors) {
				result += doctor.getName() + "\n";
			}
		}
		else {
			result = "Specialty not found";
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
	
	public static String listSpecialties() {
		String result = "List of Specialties:\n\n";
		ArrayList<Specialty> specialties = dao.getListofSpecialties();
		for(Specialty specialty:specialties) {
			result += specialty.getName() + "\n";
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
	
	public static String specialtyByName(String name) {
		String info = "";
		Specialty specialty = dao.getSpecialty(name);
		try {
			info = "Specialty name: " + specialty.getName() + "\n";
		}
		catch(NullPointerException e) {
			info = "Specialty not found";
		}
		return info;
	}
	
	public static String updateDoctor(Doctor doctor, String new_name, String specialty_name) {
		String result = "";
		boolean valid = true;
		
		if(doctor != null) {
			if(specialty_name != null) {
				Specialty specialty = dao.getSpecialty(specialty_name);
				if(specialty == null) {
					valid = false;
					result = "Specialty not found\n";
				}
			}
			
			if(valid) {
				result = doctor.getName() + "'s new information:\n";
				if(new_name != null) {
					dao.updateDoctor(doctor, new_name, null);
				}
				if(specialty_name != null) {
					Specialty specialty = dao.getSpecialty(specialty_name);
					dao.updateDoctor(doctor, new_name, specialty);
				}
				if(new_name != null) {
					result += doctorInfoByName(new_name);
				}
				else {
					result += doctorInfoByName(doctor.getName());
				}
			}
		}
		else {
			result = "Doctor not found";
		}
		
		return result;
	}
	
	public static String listDoctorsBySpecialty(String specialty_name) {
		String result = specialty_name + " doctors:\n";
		Specialty specialty = dao.getSpecialty(specialty_name);
		if(specialty != null) {
	 		List<Doctor> doctors = specialty.getDoctors();
			for(Doctor doctor:doctors) {
				result += doctor.getName() + "\n";
			}
		}
		else {
			result = "Specialty not found";
		}
		return result;
	}
	
	public static Specialty getSpecialty(String name) {
		Specialty specialty = dao.getSpecialty(name);
		return specialty;
	}
	
	public static String updateSpecialty(Specialty specialty, String new_name) {
		String result = "";
		if(specialty != null) {
			dao.updateSpecialty(specialty, new_name);
			
			result = "Specialty's new information:\n\n";
			result += specialtyByName(new_name);
		}
		else {
			result = "Specialty not found";
		}
		return result;
	}
	
	public static String createPatient(String name) {
		String result = "Patient " + name + " Created";
		try {
			dao.createPatient(name);
		}
		catch(ConstraintViolationException e) {
			result = "Patient " + name + " is already in the database";
		}
		return result;
	}
	
	public static Patient getPatient(String name) {
		Patient patient = dao.getPatient(name);
		return patient;
	}
	
	public static String listPatients() {
		String result = "List of Patient:\n\n";
		ArrayList<Patient> patients = dao.getListOfPatients();
		for(Patient patient:patients) {
			result += patient.getName() + "\n";
		}
		return result;
	}
	
	public static String patientByName(String name) {
		String info = "";
		Patient patient = dao.getPatient(name);
		try {
			info = "Patient name: " + patient.getName() + "\n";
		}
		catch(NullPointerException e) {
			info = "Patient not found";
		}
		return info;
	}
	
	public static String updatePatient(Patient patient, String new_name) {
		String result = "";
		if(patient != null) {
			dao.updatePatient(patient, new_name);
			
			result = "Patients new information:\n\n";
			result += patientByName(new_name);
		}
		else {
			result = "Patient not found";
		}
		return result;
	}
	
	public static String deleteDoctor(String name) {
		String result = "Doctor deleted";
		
		try{
			dao.deleteDoctor(name);
		}
		catch(NullPointerException e) {
			result = "Doctor not found";
		}
		
		return result;
	}
	
	public static String deleteSpecialty(String name) {
		String result = "Specialty deleted";
		
		try{
			dao.deleteSpecialty(name);
		}
		catch(NullPointerException e) {
			result = "Specialty not found";
		}
		
		return result;
	}
	
	public static String deletePatient(String name) {
		String result = "Patient deleted";
		
		try{
			dao.deletePatient(name);
		}
		catch(NullPointerException e) {
			result = "Patient not found";
		}
		
		return result;
	}
	
	public static void closeConnection() {
		dao.shutdownConnection();
	}
	
	public static String associationTester() {
		String result = "";

		Doctor doctor1 = dao.createDoctor("Sidney1");
		Doctor doctor2 = dao.createDoctor("Bob");
		Doctor doc = dao.createDoctor("Lil herbo");
		
		Specialty specialty1 = dao.createSpecialty("Pediatrics");
		Specialty specialty2 = dao.createSpecialty("Surgery");
		dao.assignDoctorSpecialty(doctor1, specialty1);
		dao.assignDoctorSpecialty(doctor2, specialty2);
		dao.assignDoctorSpecialty(doc, specialty2);
		Specialty specialty = dao.getSpecialty("Pediatrics");
		List<Doctor> doctors = specialty.getDoctors();
		for(Doctor doctor:doctors) {
			result += doctor.getName() + "\n";
		}
		
		Doctor doctor3 = dao.getDoctor("Bob");
		Specialty specialty3 = dao.getSpecialty("Surgery");
		dao.removeDoctorSpecialtyAssociation(doctor3, specialty3);

		Doctor doctor4 = dao.getDoctor("Bob");
		Specialty specialty4 = dao.getSpecialty("Surgery");
		
		result += "\nTesting removal:\n";
		if(doctor4.getSpecialty() != null) {
			result += "Bobs specialty: " + doctor4.getSpecialty().getName();
		}
		result += "\nSurgery Doctors:\n";
		for(Doctor doctor:specialty4.getDoctors()) {
			result += doctor.getName() + "\n";
		}
		
		return result;
	}
	
//	public static String appointmentTester() {
//		String result = "";
//		
//		Patient patient1 = dao.createPatient("Patient Sid");
//		Patient patient2 = dao.createPatient("Patient Bob");
//		Patient patient3 = dao.createPatient("Patient John");
//		Patient patient4 = dao.createPatient("Patient Jim");
//		
//		Doctor doctor1 = dao.createDoctor("Doctor Herb");
//		Doctor doctor2 = dao.createDoctor("Doctor Bibby");
//		
//		AppointmentRequest appointment1 = dao.createAppointment(patient1, doctor1);
//		AppointmentRequest appointment2 = dao.createAppointment(patient2, doctor1);
//		AppointmentRequest appointment3 = dao.createAppointment(patient3, doctor2);
//		AppointmentRequest appointment4 = dao.createAppointment(patient4, doctor2);
//		
//		Patient patienta = dao.getPatient("Patient Sid");
//		Patient patientb = dao.getPatient("Patient Bob");
//		Patient patientc = dao.getPatient("Patient John");
//		Patient patientd = dao.getPatient("Patient Jim");
//		
//		Doctor doctora = dao.getDoctor("Doctor Herb");
//		Doctor doctorb = dao.getDoctor("Doctor Bibby");
//		
//		result = "Doctor Herb's Patients:\n";
//		List<Patient> doctora_patients = doctora.getPatients();
//		for(Patient patient:doctora_patients) {
//			result += patient.getName() + "\n";
//		}
//		
//		return result;
//	}

}
