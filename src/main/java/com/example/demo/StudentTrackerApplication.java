package com.example.demo;

import com.example.demo.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentTrackerApplication.class, args);

		// create session factory
		SessionFactory factory = new Configuration()
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();

		try {
			//create a student object
			System.out.println("Creating new student object");
			Student tempStudent = new Student("Gianluca", "Villalba", "gianlucanadir@gmail.com");

			//start a transaction
			session.beginTransaction();

			//save the student object
			System.out.println("Saving the student...");
			System.out.println(tempStudent);
			session.save(tempStudent);

			//commit transaction
			session.getTransaction().commit();

			//find out the student's id
			System.out.println("Saved student. Generated id: " + tempStudent.getId());

			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + tempStudent.getId());

			Student myStudent = session.get(Student.class, tempStudent.getId());

			System.out.println("Get complete: " + myStudent);

			//commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}
}
