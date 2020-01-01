package pers.yanxuanshaozhu.corejavach05.ch05_abstractclass;

import java.time.LocalDate;

public class Employee extends Person {
	
	private double salary;
	private LocalDate hireDate;

	Employee() {
		super();
	}

	public Employee(String name, double salary, int year, int month, int day) {
		super(name);
		this.salary = salary;
		hireDate = LocalDate.of(year, month, day);
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
		hireDate = LocalDate.of(year, month, day);
	}

	@Override
	public String toString() {
		return "Employee [name=" + getName() + ", salary=" + salary + ", hireDate=" + hireDate + "]";
	}

	@Override
	public String getDescription() {
		return toString();
	}

}
