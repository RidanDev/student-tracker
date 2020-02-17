package com.example.demo;

import com.example.demo.entities.Instructor;
import com.example.demo.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreateDemo {

	public static void main(String[] args) {
		SpringApplication.run(CreateDemo.class, args);

		// create session factory
		SessionFactory factory = new Configuration()
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();

		try {
			Instructor instructor = new Instructor("Mario", "Rossi", "mario@luv2code.com");
			InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com", "Piano");

			instructor.setInstructorDetail(instructorDetail);
			session.beginTransaction();

			//The CascadeType.ALL will save both the Instructor and the InstructorDetail
			System.out.println("Saving instructor: " + instructor);
			session.save(instructor);

			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}
}
