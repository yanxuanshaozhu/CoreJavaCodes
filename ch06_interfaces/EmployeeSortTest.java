package pers.yanxuanshaozhu.corejavach06.ch06_interfaces;

import java.util.Arrays;

public class EmployeeSortTest {
	public static void main(String[] args) {
		Employee[] staff = new Employee[4];
		staff[0] = new Employee("Harry Hacker", 35000);
		staff[1] = new Employee("Carl Cracker", 75000);
		staff[2] = new Manager("Tony Tester", 32000,3000);
		staff[3] = new Manager("Jack Ma", 33000,1000);
		
		Arrays.sort(staff);
		for (Employee e : staff) {
			System.out.println(e); 
		}
	}
}
 