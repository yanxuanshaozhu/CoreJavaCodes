package pers.yanxuanshaozhu.corejavach16.ch16_randomaccess;

import java.time.LocalDate;

public class Employee {
	public static final int NAME_SIZE = 40;
	public static final int RECORD_SIZE = 100;

	private String name;
	private double salary;
	private LocalDate hireDate;

	public Employee() {

	}

	public Employee(String name, double salary, int year, int month, int day) {
		this.name = name;
		this.salary = salary;
		this.hireDate = LocalDate.of(year, month, day);
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

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(int year, int month, int day) {
		this.hireDate = LocalDate.of(year, month, day);
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", hireDate=" + hireDate + "]";
	}

}
