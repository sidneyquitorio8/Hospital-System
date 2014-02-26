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
		session.beginTransaction();
		Query query = session.getNamedQuery("Doctor.getAll");
		ArrayList<Doctor> doctors = (ArrayList<Doctor>) query.list();
		session.close();
		return doctors;
	}

	@Override
	public Doctor getDoctor(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("Doctor.findByName");
		query.setString("name", name);
		Doctor doctor = (Doctor) query.uniqueResult();
		session.close();
		return doctor;
	}
	
	@Override
	public ArrayList<Doctor> doctorsBySpecialty(Specialty specialty) {
		return (ArrayList<Doctor>) specialty.getDoctors();
	}

	@Override
	public Specialty getSpecialty(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("Specialty.findByName");
		query.setString("name", name);
		Specialty specialty = (Specialty) query.uniqueResult();
		session.close();
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
		session.beginTransaction();
		Query query = session.getNamedQuery("Specialty.getAll");
		ArrayList<Specialty> specialties = (ArrayList<Specialty>) query.list();
		session.close();
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


}
