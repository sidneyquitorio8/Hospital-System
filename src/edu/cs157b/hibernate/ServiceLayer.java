package edu.cs157b.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
			info = "Patient medical record: " + patient.getMedical_record() + "\n";
		}
		catch(NullPointerException e) {
			info = "Patient not found";
		}
		return info;
	}
	
	public static String updatePatientName(Patient patient, String new_name) {
		String result = "";
		if(patient != null) {
			dao.updatePatient(patient, new_name, null);
			
			result = "Patients new information:\n\n";
			result += patientByName(new_name);
		}
		else {
			result = "Patient not found";
		}
		return result;
	}
	
	public static String updatePatientRecord(Patient patient, String medical_record) {
		String result = "";
		if(patient != null) {
			dao.updatePatient(patient, null, medical_record);
			
			result = "Patients new information:\n\n";
			result += patientByName(patient.getName());
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
	
	public static String createAppointmentRequest(String doctor_name, String patient_name, Calendar time) {
		String result = "";
		
		Patient patient = dao.getPatient(patient_name);
		Doctor doctor = dao.getDoctor(doctor_name);
		
		if(patient == null) {
			result += "Patient not found\n";
			if(doctor == null) {
				result += "Doctor not found";
			}
		}
		else if(doctor == null) {
			result += "Doctor not found\n";
		}
		else {
			dao.createAppointmentRequest(patient, doctor, time);
			result = "Appointment Created";
		}
		
		return result;
	}
	
	public static String getAppointmentsByDoctor(String doctor_name) {
		String result = "";
		
		Doctor doctor = dao.getDoctor(doctor_name);
		
		if(doctor != null) {
			List<AppointmentRequest> doctors_appointments = doctor.getAppointmentRequests();
			
			if(doctors_appointments != null) {
				result = "Doctor " + doctor.getName() + "'s Appointments:\n";
				result += "Appointment Id | Doctor | Patient | Time | Status\n\n";
				for(AppointmentRequest appointment:doctors_appointments) {
					result += appointment.getId() + " | " + appointment.getDoctor().getName() + " | ";
					result += appointment.getPatient().getName() + " | " + appointment.getFormattedTime() +" | ";
					if(appointment.isFulfilled()) {
						result += "SCHEDULED\n";
					}
					else {
						result += "NOT SCHEDULED\n";
					}
				}
				result += "\n";
			}
			else {
				result = "Doctor has no appointments";
			}
		}
		else {
			result = "Doctor not found";
		}
		
		return result;
	}
	
	public static String getAppointmentsByPatient(String patient_name, String type) {
		String result = "";
		
		Patient patient = dao.getPatient(patient_name);
		
		if(patient != null) {
			List<AppointmentRequest> patients_appointments = null;
			if(type == "unscheduled") {
				patients_appointments = patient.getUnscheduledAppointmentRequests();
			}
			else if(type == "scheduled") {
				patients_appointments = patient.getScheduledAppointmentsRequests();
			}
			else {
				patients_appointments = patient.getAppointmentRequests();
			}
			
			if(patients_appointments != null) {
				result = "Patient " + patient.getName() + "'s Appointments:\n";
				result += "Appointment ID | Doctor | Patient | Time | Status\n\n";
				for(AppointmentRequest appointment:patients_appointments) {
					result += appointment.getId() + " | " + appointment.getDoctor().getName() + " | ";
					result += appointment.getPatient().getName() + " | " + appointment.getFormattedTime() +" | ";
					if(appointment.isFulfilled()) {
						result += "SCHEDULED\n";
					}
					else {
						result += "NOT SCHEDULED\n";
					}
				}
				result += "\n";
			}
			else {
				result = "Patient has no appointments";
			}
		}
		else {
			result = "Patient not found";
		}
		
		return result;
	}
	
	public static String cancelAppointmentRequestByPatient(String patient_name, int id) {
		String result = "";
		AppointmentRequest appointment = dao.getAppointmentById(id);
		
		if(appointment != null) {
			if(appointment.getPatient().getName().equalsIgnoreCase(patient_name)) {
				if(!appointment.isFulfilled()) {
					dao.deleteAppointment(id);
					result = "Appointment " + id + " deleted\n";
				}
				else {
					result = "Appointment Already Scheduled Please Submit Cancellation Request";
				}
			}
			else {
				result = "Not your appointment";
			}
		}
		else {
			result = "Appointment not found";
		}
		
		return result;
	}
	
	public static String unFulfilledAppointmentRequest() {
		String result = "";
		List<AppointmentRequest> appointments = dao.getUnFulfilledAppointments();
		result = "All Unfulfilled Appointment Requests\n";
		result += "Appointment Id | Doctor | Patient | Time | Status\n\n";
		for(AppointmentRequest appointment:appointments) {
			result += appointment.getId() + " | " + appointment.getDoctor().getName() + " | ";
			result += appointment.getPatient().getName() + " | " + appointment.getFormattedTime() +" | ";
			if(appointment.isFulfilled()) {
				result += "SCHEDULED\n";
			}
			else {
				result += "NOT SCHEDULED\n";
			}
		}
		result += "\n";
		
		return result;		
	}
	
	public static String fulfilledAppointmentRequest() {
		String result = "";
		List<AppointmentRequest> appointments = dao.getFulfilledAppointments();
		result = "All Fulfilled Appointment Requests\n";
		result += "Appointment Id | Doctor | Patient | Time | Status\n\n";
		for(AppointmentRequest appointment:appointments) {
			result += appointment.getId() + " | " + appointment.getDoctor().getName() + " | ";
			result += appointment.getPatient().getName() + " | " + appointment.getFormattedTime() +" | ";
			if(appointment.isFulfilled()) {
				result += "SCHEDULED\n";
			}
			else {
				result += "NOT SCHEDULED\n";
			}
		}
		result += "\n";
		
		return result;		
	}
	
	public static String staffScheduleAppointment(int id) {
		String result = "";
		
		result = dao.staffScheduleAppointment(id);	
		
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
	
	public static String viewAppointmentCancelRequest() {
		String result = "";
		List<AppointmentRequest> appointments = dao.getCancelRequest();
		result = "All Cancellation Requests\n";
		result += "Appointment Id | Doctor | Patient | Time | Status\n\n";
		for(AppointmentRequest appointment:appointments) {
			result += appointment.getId() + " | " + appointment.getDoctor().getName() + " | ";
			result += appointment.getPatient().getName() + " | " + appointment.getFormattedTime() +" | ";
			if(appointment.isFulfilled()) {
				result += "SCHEDULED\n";
			}
			else {
				result += "NOT SCHEDULED\n";
			}
		}
		result += "\n";
		
		return result;	
	}
	
	public static String requestToCancelScheduledAppointment(String patient_name, int id) {
		String result = "";
		AppointmentRequest appointment = dao.getAppointmentById(id);
		
		if(appointment != null) {
			if(appointment.getPatient().getName().equalsIgnoreCase(patient_name)) {
				if(appointment.isFulfilled()) {
					dao.requestToCancelScheduledAppointment(id);
					result = "Request to cancel appointment " + id + " was sent\n";
				}
				else {
					result = "Appointment has not been scheduled";
				}
			}
			else {
				result = "Not your appointment";
			}
		}
		else {
			result = "Appointment not found";
		}
			
		return result;
	}
	
	public static String cancelScheduledAppointment(int id) {
		String result = "";
		
		AppointmentRequest appointment = dao.getAppointmentById(id);
		if(appointment != null) {
			if(appointment.isCancel_requested()) {
				if(appointment.isFulfilled()) {
					dao.deleteAppointment(id);
					result = "Appointment " + id + " was deleted";
				}
				else {
					result = "Appointment was not even scheduled";
				}
			}
			else {
				result = "Appointment was not requested to be cancelled";
			}
		}
		else {
			result = "Appointment not found";
		}
		
		return result;
	}
	
	public static String appointmentTester() {
		String result = "";
		Patient patient1 = dao.createPatient("Patient Sid");
		Patient patient2 = dao.createPatient("Patient Bob");
		Patient patient3 = dao.createPatient("Patient John");
		Patient patient4 = dao.createPatient("Patient Jim");
		
		Doctor doctor1 = dao.createDoctor("Doctor Herb");
		Doctor doctor2 = dao.createDoctor("Doctor Bibby");
		Doctor doctor3 = dao.createDoctor("Doctor Jay");
		Doctor doctor4 = dao.createDoctor("Doctor Fazo");
		
		Specialty specialty1 = dao.createSpecialty("Surgery");
		Specialty specialty2 = dao.createSpecialty("Pediatrics");
		
		dao.assignDoctorSpecialty(doctor1, specialty1);
		dao.assignDoctorSpecialty(doctor2, specialty1);
		dao.assignDoctorSpecialty(doctor3, specialty2);
		dao.assignDoctorSpecialty(doctor4, specialty2);
		
		Calendar birthday = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		try {
			birthday.setTime(sdf.parse("10/21/2014 10:00 PM"));
		} catch (ParseException e) {
			result = "ERORORORORORROR";
		}
		
		Calendar old_date = new GregorianCalendar();
		try {
			old_date.setTime(sdf.parse("10/21/1990 10:00 PM"));
		} catch (ParseException e) {
			result = "ERORORORORORROR";
		}
		
		AppointmentRequest appointment1 = dao.createAppointmentRequest(patient1, doctor1,birthday);
		AppointmentRequest appointment2 = dao.createAppointmentRequest(patient2, doctor1,birthday);
		AppointmentRequest appointment3 = dao.createAppointmentRequest(patient3, doctor2,birthday);
		AppointmentRequest appointment4 = dao.createAppointmentRequest(patient4, doctor2,birthday);
		
		AppointmentRequest appointment5 = dao.createAppointmentRequest(patient1, doctor2,old_date);
		
		Patient patienta = dao.getPatient("Patient Sid");
		Patient patientb = dao.getPatient("Patient Bob");
		Patient patientc = dao.getPatient("Patient John");
		Patient patientd = dao.getPatient("Patient Jim");
		
		Doctor doctora = dao.getDoctor("Doctor Herb");
		Doctor doctorb = dao.getDoctor("Doctor Bibby");
		
		result += "Doctor Herb's Patients:\n";
		List<Patient> doctora_patients = doctora.getPatients();
		for(Patient patient:doctora_patients) {
			Calendar c = patient.getAppointmentRequests().get(0).getTime();
			result += patient.getName() + " " + sdf.format(c.getTime()) + "\n";
		}
		
		result += "\nPatient Sid's Doctors:\n";
		List<Doctor> patienta_doctors = patienta.getDoctors();
		for(Doctor doctor:patienta_doctors) {
			result += doctor.getName() + "\n";
		}
		
		return result;
	}

}
