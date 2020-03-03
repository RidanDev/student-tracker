package com.example.demo;

import com.example.demo.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreateCourseAndStudentDemo {

	public static void main(String[] args) {
		SpringApplication.run(CreateCourseAndStudentDemo.class, args);

		// create session factory
		SessionFactory factory = new Configuration()
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// create a course
			Course course = new Course("Tetris - Let's the challenge begin");

			// save the course
			System.out.println("\nSaving the course ...");
			session.save(course);
			System.out.println("Saved the course: " + course);

			// create the students
			Student student1 = new Student("John", "Doe", "john@luv2code.com");
			Student student2 = new Student("Mary", "Public", "mary@luv2code.com");

			// add students to the course
			course.addStudent(student1);
			course.addStudent(student2);

			// save the students
			System.out.println("\nSaving students ...");
			session.save(student1);
			session.save(student2);
			System.out.println("Saved students: " + course.getStudents());

			// commit transaction
			session.getTransaction().commit();
		} finally {
			//add clean up code
			session.close();

			factory.close();
		}
	}
}
