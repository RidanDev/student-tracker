package com.example.demo;

import com.example.demo.entities.Instructor;
import com.example.demo.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeleteDemo {
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

            Instructor instructor = session.get(Instructor.class, 3);

            System.out.println("Found instructor: " + instructor);

            if (instructor != null) {
                System.out.println("Deleting: " + instructor);

                //Will also delete associated "details" object because of CascadeType.ALL
                session.delete(instructor);
            }

            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
