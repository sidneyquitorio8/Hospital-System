package edu.cs157b.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

public class ConcreteMedicalDAO implements MedicalDAO {
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public Doctor createDoctor(String name) throws ConstraintViolationException {
		Session session = sessionFactory.openSession();
	    Doctor doctor = new Doctor();
		try{
			session.beginTransaction();
		    doctor.setName(name);
		    session.save(doctor);
		    session.getTransaction().commit();
		}
		catch(ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw e;
		}
		    session.close();
		return doctor;
	}
	
	public void shutdownConnection() {
		sessionFactory.close();
	}

	@Override
	public ArrayList<Doctor> getListOfDoctors() {
		Session session = sessionFactory.openSession();
		ArrayList<Doctor> doctors = new ArrayList<Doctor>();
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("Doctor.getAll");
			doctors = (ArrayList<Doctor>) query.list();
		}
		finally {
			session.close();
		}
		return doctors;
	}

	@Override
	public Doctor getDoctor(String name) {
		Session session = sessionFactory.openSession();
		Doctor doctor = new Doctor();
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("Doctor.findByName");
			query.setString("name", name);
			doctor = (Doctor) query.uniqueResult();
		}
		finally {
			session.close();
		}
		return doctor;
	}
	
	@Override
	public ArrayList<Doctor> doctorsBySpecialty(Specialty specialty) {
		return (ArrayList<Doctor>) specialty.getDoctors();
	}

	@Override
	public Specialty getSpecialty(String name) {
		Session session = sessionFactory.openSession();
		Specialty specialty = new Specialty();
		try{
		session.beginTransaction();
		Query query = session.getNamedQuery("Specialty.findByName");
		query.setString("name", name);
		specialty = (Specialty) query.uniqueResult();
		}
		finally {
			session.close();
		}
		return specialty;		
	}

	@Override
	public Specialty createSpecialty(String name) throws ConstraintViolationException {
		Session session = sessionFactory.openSession();
		Specialty specialty = new Specialty();
		try {
			session.beginTransaction();
			specialty.setName(name);
			session.save(specialty);
			session.getTransaction().commit();
		}
		catch(ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw e;
		}
		finally {
			session.close();			
		}
		return specialty;
	}

	@Override
	public ArrayList<Specialty> getListofSpecialties() {
		Session session = sessionFactory.openSession();
		ArrayList<Specialty> specialties = new ArrayList<Specialty>();
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("Specialty.getAll");
			specialties = (ArrayList<Specialty>) query.list();
		}
		finally {
			session.close();
		}
		return specialties;
	}

	@Override
	public void assignDoctorSpecialty(Doctor doctor, Specialty specialty) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			
			doctor.setSpecialty(specialty);
	
			List<Doctor> doctors = specialty.getDoctors();
			doctors.add(doctor);
			
			session.saveOrUpdate(doctor);
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
		
	}

	@Override
	public void updateDoctor(Doctor doctor, String name, Specialty specialty) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			
			if(name != null) {
				doctor.setName(name);				
			}
			if(specialty != null) {
				Specialty old_specialty = doctor.getSpecialty();
				if(old_specialty != null) {
					old_specialty.getDoctors().remove(doctor);
				}
				doctor.setSpecialty(specialty);
				List<Doctor> doctors = specialty.getDoctors();
				doctors.add(doctor);
			}
			session.saveOrUpdate(doctor);
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}		
	}

	@Override
	public void removeDoctorSpecialtyAssociation(Doctor doctor, Specialty specialty) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			
			doctor.setSpecialty(null);
	
			List<Doctor> doctors = specialty.getDoctors();
			doctors.remove(doctor);
			
			session.saveOrUpdate(doctor);
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
		
	}

	@Override
	public void updateSpecialty(Specialty specialty, String new_name) {
		// TODO Auto-generated method stub
		if(specialty != null) {
			Session session = sessionFactory.openSession();
			try {
				session.beginTransaction();
				
				specialty.setName(new_name);
				
				session.saveOrUpdate(specialty);
				session.getTransaction().commit();
			}
			finally {
				session.close();
			}			
		}
	}

	@Override
	public Patient createPatient(String name) {
		Session session = sessionFactory.openSession();
	    Patient patient = new Patient();
		try{
			session.beginTransaction();
		    patient.setName(name);
		    session.save(patient);
		    session.getTransaction().commit();
		}
		catch(ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw e;
		}
		    session.close();
		return patient;
	}

	@Override
	public ArrayList<Patient> getListOfPatients() {
		Session session = sessionFactory.openSession();
		ArrayList<Patient> patients = new ArrayList<Patient>();
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("Patient.getAll");
			patients = (ArrayList<Patient>) query.list();
		}
		finally {
			session.close();
		}
		return patients;
	}

	@Override
	public Patient getPatient(String name) {
		Session session = sessionFactory.openSession();
		Patient patient = new Patient();
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("Patient.findByName");
			query.setString("name", name);
			patient = (Patient) query.uniqueResult();
		}
		finally {
			session.close();
		}
		return patient;
	}

	@Override
	public void updatePatient(Patient patient, String new_name) {
		if(patient != null) {
			Session session = sessionFactory.openSession();
			try {
				session.beginTransaction();
				
				patient.setName(new_name);
				
				session.saveOrUpdate(patient);
				session.getTransaction().commit();
			}
			finally {
				session.close();
			}			
		}
		
	}

	@Override
	public void deleteDoctor(String doctor_name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Doctor doctor = new Doctor();
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("Doctor.findByName");
			query.setString("name", doctor_name);
			doctor = (Doctor) query.uniqueResult();
			if(doctor == null) {
				throw new NullPointerException();
			}
			session.delete(doctor);
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	}

	@Override
	public void deleteSpecialty(String specialty_name) {
		Session session = sessionFactory.openSession();
		Specialty specialty = new Specialty();
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("Specialty.findByName");
			query.setString("name", specialty_name);
			specialty = (Specialty) query.uniqueResult();
			if(specialty == null) {
				throw new NullPointerException();
			}
			List<Doctor> doctors = specialty.getDoctors();
			for(Doctor doctor:doctors) {
				doctor.setSpecialty(null);
			}
			session.delete(specialty);
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	}

	@Override
	public void deletePatient(String patient_name) {
		Session session = sessionFactory.openSession();
		Patient patient = new Patient();
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("Patient.findByName");
			query.setString("name", patient_name);
			patient = (Patient) query.uniqueResult();
			if(patient == null) {
				throw new NullPointerException();
			}
			session.delete(patient);
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	}

//	@Override
//	public AppointmentRequest createAppointment(Patient patient, Doctor doctor) {
//		Session session = sessionFactory.openSession();
//		AppointmentRequest appointment = new AppointmentRequest();
//		try {
//			session.beginTransaction();
//			if(patient == null) {
//				throw new NullPointerException();
//			}
//			if(doctor == null) {
//				throw new NullPointerException();
//			}
//
//			appointment.setPatient(patient);
//			appointment.setDoctor(doctor);
//			appointment.setFulfilled(true);
//		 
//		    patient.getAppointmentRequests().add(appointment);
//			
//			session.getTransaction().commit();
//		}
//		finally {
//			session.close();
//		}
//		return appointment;		
//		
//	}
	


}
