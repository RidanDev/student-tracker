package com.example.demo;

import com.example.demo.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

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
			session.beginTransaction();

			List<Student> theStudents = session.createQuery("from Student").list();
			displayStudents(theStudents);

			Student student = session.get(Student.class, 10);

			session.delete(student);

			theStudents = session.createQuery("from Student").list();
			displayStudents(theStudents);

			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}
}
