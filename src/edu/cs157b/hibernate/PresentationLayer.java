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
			System.out.println("Sign in as: [A]dmin or [Q]uit");
			code = keyboard.next();
			if(code.equalsIgnoreCase("a")) {
				adminHandler(keyboard);
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
				System.out.println("Not implemented yet");
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
				
			}
			else if(command.equalsIgnoreCase("d")) {
				
			}
		}
	}
	
	public static void crudSpecialty(Scanner keyboard) {
		String command = "";
		while(!command.equalsIgnoreCase("q")) {
			System.out.println("[L]ist Specialty, [C]reate Specialty, [R]etrieve Specialty, [U]pdate Specialty, [D]elete Specialty, [Q]uit to admin");
			command = keyboard.next();
			if(command.equalsIgnoreCase("c")) {
				System.out.println("Enter Specialty Name:");
				keyboard.nextLine();
				String name = keyboard.nextLine();
				System.out.println(ServiceLayer.createSpecialty(name));
			}
			else if(command.equalsIgnoreCase("l")) {
//				System.out.println(ServiceLayer.listDoctors());
			}
			else if(command.equalsIgnoreCase("r")) {
//				System.out.println("Enter the doctors name:");
//				keyboard.nextLine();
//				String name = keyboard.nextLine();
//				System.out.println(ServiceLayer.doctorInfoByName(name));
			}
			else if(command.equalsIgnoreCase("u")) {
				System.out.println(ServiceLayer.tester());
			}
			else if(command.equalsIgnoreCase("d")) {
				
			}
		}
	}

}
