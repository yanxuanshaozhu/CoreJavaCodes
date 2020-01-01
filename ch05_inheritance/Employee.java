package pers.yanxuanshaozhu.corejavach05.ch05_inheritance;

import java.time.LocalDate;

public class Employee {

	private String name;
	private double salary;
	private LocalDate hireDate;

	Employee() {
		super();
	}

	public Employee(String name, double salary, int year, int month, int day) {
		super();
		this.name = name;
		this.salary = salary;
		hireDate = LocalDate.of(year, month, day);
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", hireDate=" + hireDate + "]";
	}
	
}
