package edu.cs157b.hibernate;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

public class PresentationLayer {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String code = "";
		while(!code.equalsIgnoreCase("q")) {
			System.out.println("Sign in as: [A]dmin, [S]taff, or [Q]uit");
			code = keyboard.next();
			if(code.equalsIgnoreCase("a")) {
				adminHandler(keyboard);
			}
			if(code.equalsIgnoreCase("s")) {
				staffHandler(keyboard);
			}
		}
		System.out.println("Bye Bye");
		ServiceLayer.closeConnection();
	}
	
	public static void adminHandler(Scanner keyboard) {
		String command = "";
		while(!command.equalsIgnoreCase("q")) {
			System.out.println("Manage [D]octors, Manage [S]pecialties, Manage [P]atients, [Q]uit to menu");
			command = keyboard.next();
			if(command.equalsIgnoreCase("d")) {
				crudDoctor(keyboard);
			}
			else if(command.equalsIgnoreCase("s")) {
				crudSpecialty(keyboard);
			}
			else if(command.equalsIgnoreCase("p")) {
				crudPatient(keyboard);
			}
		}
	}
	
	public static void crudDoctor(Scanner keyboard) {
		String command = "";
		while(!command.equalsIgnoreCase("q")) {
			System.out.println("[L]ist Doctors, [C]reate Doctor, [R]etrieve Doctor Information, [U]pdate Doctor, [D]elete Doctor, [Q]uit to admin");
			command = keyboard.next();
			if(command.equalsIgnoreCase("c")) {
				System.out.println("Enter Doctors Name:");
				keyboard.nextLine();
				String name = keyboard.nextLine();
				System.out.println(ServiceLayer.createDoctor(name));
			}
			else if(command.equalsIgnoreCase("l")) {
				System.out.println(ServiceLayer.listDoctors());
			}
			else if(command.equalsIgnoreCase("r")) {
				System.out.println("Enter the doctors name:");
				keyboard.nextLine();
				String name = keyboard.nextLine();
				System.out.println(ServiceLayer.doctorInfoByName(name));
			}
			else if(command.equalsIgnoreCase("u")) {
				System.out.println("Enter the doctors name:");
				keyboard.nextLine();
				String doctor_name = keyboard.nextLine();
				Doctor doctor = ServiceLayer.getDoctor(doctor_name);
				if(doctor != null) {
					System.out.println(ServiceLayer.doctorInfoByName(doctor_name));
					while(!command.equalsIgnoreCase("b")) {
						System.out.println("Update [N]ame, Update [S]pecialty, [B]ack to doctor menu");
						command = keyboard.next();
						if(command.equalsIgnoreCase("n")) {
							doctor = ServiceLayer.getDoctor(doctor_name);
							System.out.println("Enter Doctors new name:");
							keyboard.nextLine();
							String new_name = keyboard.nextLine();
							doctor_name = new_name;
							System.out.println(ServiceLayer.updateDoctor(doctor, new_name, null));
						}
						else if(command.equalsIgnoreCase("s")) {
							doctor = ServiceLayer.getDoctor(doctor_name);
							System.out.println("Enter Doctors new specialty:");
							keyboard.nextLine();
							String specialty_name = keyboard.nextLine();
							System.out.println(ServiceLayer.updateDoctor(doctor, null, specialty_name));							
						}
					}
				}
				else {
					System.out.println("Doctor not found");
				}
				
			}
			else if(command.equalsIgnoreCase("d")) {
				System.out.println("Enter the doctors name:");
				keyboard.nextLine();
				String name = keyboard.nextLine();
				System.out.println(ServiceLayer.deleteDoctor(name));
			}
		}
	}
	
	public static void crudSpecialty(Scanner keyboard) {
		String command = "";
		while(!command.equalsIgnoreCase("q")) {
			System.out.println("[L]ist Specialty, [S]how doctors by specialty, [C]reate Specialty, [R]etrieve Specialty, [U]pdate Specialty, [D]elete Specialty, [Q]uit to admin");
			command = keyboard.next();
			if(command.equalsIgnoreCase("c")) {
				System.out.println("Enter Specialty Name:");
				keyboard.nextLine();
				String name = keyboard.nextLine();
				System.out.println(ServiceLayer.createSpecialty(name));
			}
			else if(command.equalsIgnoreCase("l")) {
				System.out.println(ServiceLayer.listSpecialties());
			}
			else if(command.equalsIgnoreCase("r")) {
				System.out.println("Enter the specialtys name:");
				keyboard.nextLine();
				String name = keyboard.nextLine();
				System.out.println(ServiceLayer.specialtyByName(name));
			}
			else if(command.equalsIgnoreCase("u")) {
				System.out.println("Enter the specialties name:");
				keyboard.nextLine();
				String specialty_name = keyboard.nextLine();
				Specialty specialty = ServiceLayer.getSpecialty(specialty_name);
				if(specialty != null) {
					System.out.println("Enter the specialties new name:");
					String new_name = keyboard.nextLine();
					System.out.println(ServiceLayer.updateSpecialty(specialty, new_name));
				}
				else {
					System.out.println("Specialty not found");
				}
			}
			else if(command.equalsIgnoreCase("d")) {
				System.out.println("Enter the specialtys name:");
				keyboard.nextLine();
				String name = keyboard.nextLine();
				System.out.println(ServiceLayer.deleteSpecialty(name));
			}
			else if(command.equalsIgnoreCase("s")) {
				System.out.println("Enter Specialty Name:");
				keyboard.nextLine();
				String name = keyboard.nextLine();
				Specialty specialty = ServiceLayer.getSpecialty(name);
				if(specialty != null) {
					System.out.println(ServiceLayer.listDoctorsBySpecialty(name));
				}
				else {
					System.out.println("Specialty not found");
				}
			}
		}
	}
	
	public static void crudPatient(Scanner keyboard) {
		String command = "";
		while(!command.equalsIgnoreCase("q")) {
			System.out.println("[L]ist Patients, [C]reate Patient, [R]etrieve Patient, [U]pdate Patient, [D]elete Patient, [Q]uit to admin");
			command = keyboard.next();
			if(command.equalsIgnoreCase("c")) {
				System.out.println("Enter Patients Name:");
				keyboard.nextLine();
				String name = keyboard.nextLine();
				System.out.println(ServiceLayer.createPatient(name));
			}
			else if(command.equalsIgnoreCase("l")) {
				System.out.println(ServiceLayer.listPatients());
			}
			else if(command.equalsIgnoreCase("r")) {
				System.out.println("Enter the patients name:");
				keyboard.nextLine();
				String name = keyboard.nextLine();
				System.out.println(ServiceLayer.patientByName(name));
			}
			else if(command.equalsIgnoreCase("u")) {
				System.out.println("Enter the patients name:");
				keyboard.nextLine();
				String patient_name = keyboard.nextLine();
				Patient patient = ServiceLayer.getPatient(patient_name);
				if(patient != null) {
					System.out.println("Enter the patients new name:");
					String new_name = keyboard.nextLine();
					System.out.println(ServiceLayer.updatePatient(patient, new_name));
				}
				else {
					System.out.println("Patient not found");
				}
			}
			else if(command.equalsIgnoreCase("d")) {
				System.out.println("Enter the patients name:");
				keyboard.nextLine();
				String name = keyboard.nextLine();
				System.out.println(ServiceLayer.deletePatient(name));
			}
		}
	}
	
	public static void staffHandler(Scanner keyboard) {
		String command = "";
		while(!command.equalsIgnoreCase("q")) {
			System.out.println("[A]ppointment");
			command = keyboard.next();
			if(command.equalsIgnoreCase("a")) {
//				System.out.println(ServiceLayer.appointmentTester());
			}
		}
	}

}
