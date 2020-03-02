package com.example.demo;

import com.example.demo.entities.Course;
import com.example.demo.entities.Instructor;
import com.example.demo.entities.InstructorDetail;
import com.example.demo.entities.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		SpringApplication.run(CreateCourseAndReviewsDemo.class, args);

		// create session factory
		SessionFactory factory = new Configuration()
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			Course course = new Course("Pacman - How to score one million points");

			course.addReview(new Review("Great course...loved it!"));
			course.addReview(new Review("Cool course...well done!"));
			course.addReview(new Review("What a dumb course, you are an idiot!"));

			System.out.println(course);
			System.out.println(course.getReviews());

			session.save(course);

			session.getTransaction().commit();
		} finally {
			//add clean up code
			session.close();

			factory.close();
		}
	}
}
