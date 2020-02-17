package com.example.demo;

import com.example.demo.entities.Course;
import com.example.demo.entities.Instructor;
import com.example.demo.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreateCoursesDemo {

	public static void main(String[] args) {
		SpringApplication.run(CreateCoursesDemo.class, args);

		// create session factory
		SessionFactory factory = new Configuration()
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			Instructor instructor = session.get(Instructor.class, 1);
			Course course1 = new Course("Air Guitar - The Ultimate Guide");
			Course course2 = new Course("the Pinball Masterclass");

			instructor.add(course1);
			instructor.add(course2);

			session.save(course1);
			session.save(course2);

			session.getTransaction().commit();
		} finally {
			//add clean up code
			session.close();

			factory.close();
		}
	}
}
