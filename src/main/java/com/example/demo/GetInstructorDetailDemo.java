package com.example.demo;

import com.example.demo.entities.Instructor;
import com.example.demo.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GetInstructorDetailDemo {
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
            session.beginTransaction();

            InstructorDetail instructorDetail = session.get(InstructorDetail.class, 2);

            System.out.println("the associated instructor: " + instructorDetail.getInstructor());

            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
