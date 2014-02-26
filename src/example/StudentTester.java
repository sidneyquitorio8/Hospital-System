package example;
//package edu.cs157b.hibernate;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//
//public class StudentTester {
//    private static SessionFactory sessionFactory; 
//    
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//       Student student = new Student();
//       student.setName("Lee");
//       
//       Student anotherStudent = new Student();
//       anotherStudent.setName("Smith");
//       
//      // sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//       sessionFactory = HibernateUtil.getSessionFactory();
//       Session session = sessionFactory.openSession();
//       session.beginTransaction();
//       
//       session.save(student);
//       student.setGpa(4.0);
//    
//       session.save(anotherStudent);
//       session.evict(anotherStudent); // detached during the transaction
//       anotherStudent.setGpa(4.5); // Smith's GPA will not be changed. 
//      
//       session.getTransaction().commit();
//       student.setGpa(2.0); // Lee's GPA will be still 4.0
//       session.close();
//       sessionFactory.close();
//	}
//
//}