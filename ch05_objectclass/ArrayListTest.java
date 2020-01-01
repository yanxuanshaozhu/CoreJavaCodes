package pers.yanxuanshaozhu.corejavach05.ch05_objectclass;

import java.util.ArrayList;

public class ArrayListTest {
	public static void main(String[] args) {
		ArrayList<Employee> staff = new ArrayList<Employee>();
		staff.add(new Employee("Jing Tang", 20000, 2018, 12, 24));
		staff.add(new Employee("Jing Wang", 19000, 2016, 1, 10));
		staff.add(new Employee("Ruan Zhou", 25000, 2018, 12, 1));

		for (Employee e : staff) {
			e.raiseSalary(5);
		}

		for (Employee e : staff) {
			System.out.println(e);
		}
	}
}
