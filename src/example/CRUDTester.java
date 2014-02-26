package example;
//package edu.cs157b.hibernate;
//
//import java.io.Serializable;
//import java.util.List;
//
//
//
//
//
//
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//public class CRUDTester 
//{   private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//
//	public static void main (String [] args)
//	{
//	  
//      create();
//      retrieve(1);
//      update();
//      delete();
//      
//      sessionFactory.close();
//	}
//
//	public static void create()
//	{
//		  Session session = sessionFactory.openSession();
//	      session.beginTransaction();
////	      Doctor d = new Doctor();
////	      d.setName("Bob");
////	      session.save(d);
//	      Student s1 = new Student();
//		  s1.setName("Jane Smith");
//		  s1.setMajor("Computer Science");
//		  s1.setGpa(3.8);
//		  s1.setStudentId(10);
//		  session.save(s1);
//	      
//	      Student s2 = new Student();
//		  s2.setName("Tom Taylor");
//		  s2.setMajor("Computer Science");
//		  s2.setGpa(4.0);
//		  session.save(s2);
//		  
//         
//	      Student s3 = new Student();
//		  s3.setName("Galvin Clark");
//		  s3.setMajor("Biology");
//		  s3.setGpa(3.1);
//	      session.save(s3);
//	     
//	      Student s4 = new Student();
//		  s4.setName("Muriel Marsh");
//		  s4.setMajor("Biology");
//		  s4.setGpa(3.2);
//	      session.save(s4);
//	      
//	      Student s5= new Student();
//		  s5.setName("Muriel Marsh");
//		  s5.setMajor("Biology"); 
//		  s5.setGpa(2.0);
//	      session.save(s4);
//	      
//	      
//	      session.getTransaction().commit();
//	      session.close();
//	}
//    public static void retrieve(int idValue)
//    {   
//    
//    	Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        
//        List<Student> allStudents;
//        Query query = session.createQuery("from Student");
//        allStudents = query.list(); // contains Student instances
//        for (Student s: allStudents) System.out.println(s);
//        
//        String hql = "SELECT name from Student";
//        query = session.createQuery(hql);
//        List<String> allNames = query.list();
//        for (String n: allNames) System.out.println(n);
//        
//        String queryString = "from Student where id = :id";
//        query = session.createQuery(queryString);
//        query.setInteger("id", idValue);
//        Student firstStudent = (Student) query.uniqueResult();
//        System.out.println(firstStudent);
//        
//        hql = "from Student as s order by s.studentId ASC";
//        query = session.createQuery(hql);
//        List<Student> sortedStudents = query.list();
//        System.out.println(sortedStudents);
//        
//        hql = "SELECT major from Student as s group by s.major having avg(gpa) > 3.5"; 
//        query = session.createQuery(hql);
//        List<String> majorsWithHighAvg = query.list();
//        System.out.println(majorsWithHighAvg);
//        
//        Student thirdStudent = (Student)session.get(Student.class, new Integer(3));
//        System.out.println(thirdStudent.getName());
//        
//        Student secondStudent = (Student)session.load(Student.class, new Integer(2));
//        System.out.println(secondStudent.getName());
//       
//        
//       
//        query = session.getNamedQuery("Student.findByName");
//        query.setString("name", "Tom Taylor");
//        List<Student> studentsByName = query.list();
//        System.out.println(studentsByName);
//        
//        session.getTransaction().commit();
//        System.out.println(secondStudent.getName());
//        session.close();
//    }
//    public static void update()
//    { 
//    	List<Student> toBeUpdated;
//    	Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        
//        Student x = new Student();
//        
//        Query queryResult = session.createQuery("from Student");
//        toBeUpdated = queryResult.list(); // contains Student instances
//        
//        for (Student u: toBeUpdated) 
//        { u.setGpa(u.getGpa()+0.1);
//          session.update(u);
//        }
//        
//        String hql = "update Student set gpa = 3.0 where gpa < 2.5";
//        Query query = session.createQuery(hql);
//        int rowCount = query.executeUpdate();
//        		                       
//      
//        
//        session.getTransaction().commit();
//        session.close();
//    	
//    }
//    
//    public static void delete()
//    { 
//    	List<Student> allStudents;
//    	Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        
//        Query queryResult = session.createQuery("from Student");
//        allStudents = queryResult.list(); // contains Student instances
//        
//        for (Student u: allStudents) 
//        { 
//        	if (u.getGpa() < 2.5) session.delete(u);
//        }
//        
//        String hql = "delete Student where gpa < 2.5" ; ;
//        Query query = session.createQuery(hql);
//        int rowCount = query.executeUpdate();
//        		
//        session.getTransaction().commit();
//        session.close();
//    	
//    }   
//}
