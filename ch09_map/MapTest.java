package pers.yanxuanshaozhu.corejavach09.ch09_map;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	public static void main(String[] args) {
		Map<String, Employee> staff = new HashMap<String, Employee>();
		staff.put("144-25-5464", new Employee("Amy Lee"));
		staff.put("567-24-2546", new Employee("Harry Hacker"));
		staff.put("157-62-7935", new Employee("Gary Cooper"));
		staff.put("456-62-5527", new Employee("Francesca Cruz"));

		System.out.println(staff);

		staff.remove("567-24-2546");

		staff.put("456-62-5527", new Employee("Francesca Miller"));

		System.out.println(staff.get("157-62-7935"));

		staff.forEach((k, v) -> System.out.println("key = " + k + " value = " + v));
	}
}

class Employee {

	private String name;
	private double salary;

	public Employee() {
		super();
	}

	public Employee(String name) {
		this.name = name;
		salary = 0;
	}

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double raiseSalary(double x) {
		return salary * (1 + x / 100.0);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[" + name + "]";
	}

}