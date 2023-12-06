package com.pro;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();

		UserData u = new UserData();
		u.setId(1);
		u.setName("Faisal");
		u.setEmail("faisal@gmail.com");
		u.setPassword("faisal");
		s.save(u);

		tx.commit();
		s.close();
		sf.close();
	}
}
