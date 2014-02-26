package example;
//package edu.cs157b.hibernate;
//import javax.persistence.*;
//
//
//@Entity
//@Table(name="STUDENT_INFO")
//@NamedQuery(name = "Student.findByName", query = "from Student where name = :name")
//public class Student {
//	
//	private int studentId;
//	private String name;
//	private String major; 
//	private double gpa; 
//	private String eMail;
//	
//
//	private Address address;
//	
//	@Id
//	@GeneratedValue
//	public int getStudentId() {
//		return studentId;
//	}
//	public void setStudentId(int studentId) {
//		this.studentId = studentId;
//	}
//	
//	@Column(name="Student_Name")
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	public double getGpa() {
//		return gpa;
//	}
//	public void setGpa(double gpa) {
//		this.gpa = gpa;
//	}
//	
//	public String getMajor() {
//		return major;
//	}
//	public void setMajor(String major) {
//		this.major = major;
//	}
//	public Address getAddress() {
//		return address;
//	}
//	public void setAddress(Address address) {
//		this.address = address;
//	}
//	public String geteMail() {
//		return eMail;
//	}
//	public void seteMail(String eMail) {
//		this.eMail = eMail;
//	}
//	
//	public String toString()
//	{   return studentId+ " "+ name + " " + major + " " + gpa + " " + address + " " + eMail; }
//   
//}