package com.mahesh.Tables;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class App {
	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		Session session = factory.openSession();
		int userId = 2;

		// parameter binding

		String hql = "from Employee where id>" + userId;
		// yala sql injection hacker hack karun chukichi value insert karu shakato
		// lot of damage he can do.

		org.hibernate.query.Query<Employee> query = session.createQuery(hql);

		List<Employee> lstemployees = query.list();

		for (Employee employee : lstemployees) {
			System.out.println("Employee List:\n");
			System.out.println(
					"ID: " + employee.getId() + " Name: " + employee.getName() + " Address: " + employee.getAddress());
		}
		// ----------------------------------------------------------------------------------------------------------------
		// ---------------------- Protection from hacker use Parameter_Binding_Use
		// ----------------------------------------
		// ----------------------------------------------------------------------------------------------------------------

		String userId2 = "2";
		String hql2 = "from Employee WHERE id>:userId2";

		Query<Employee> query2 = session.createQuery(hql2);
		// first location la hi value ghal.
		query2.setInteger("userId2", userId);
		List<Employee> employees = query2.list();
		System.out.println(employees);

		String userId3 = "1";
		String add = "Satara";
		String n1 = "Mahesh Dilip Potdar";
		// hibernate madhe direct id>3 dyayacha nasata. ani id="id;
		// asa pan dyayacha nasata.
		// i1 location
		// a1 location.
		// parameter binding manaje :a1(location la konatahi variable dya.
		// setString("a1","add"));
		org.hibernate.Query<Employee> query3 = session
				.createQuery("FROM Employee WHERE id=:i1 and address=:a1 and name=:n1");
		query3.setString("i1", userId3);
		System.out.println("userId3 " + userId3);
		query3.setString("a1", add);
		query3.setString("n1", n1);
		List<Employee> employees2 = query3.list();
		System.out.println("11: " + employees2);

		String iddd = "2";
		String addd = "USA";

		String q4 = "FROM Employee WHERE id>:i1 and address=:a1";
		org.hibernate.Query<Employee> query4 = session.createQuery(q4);
		query4.setString("i1", iddd);
		query4.setString("a1", addd);
		
		Employee employee=query4.uniqueResult();
		System.out.println("Unique result: \n"+employee);
	}
}
