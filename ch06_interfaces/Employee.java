package pers.yanxuanshaozhu.corejavach06.ch06_interfaces;

public class Employee implements Comparable<Employee> {

	private String name;
	private double salary;

	public Employee() {
		super();
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
	public int compareTo(Employee o) {
		return Double.compare(this.salary, o.salary);
	}

	@Override 
	public String toString() {
		return this.getClass().getSimpleName() + "[name=" + name + ", salary=" + salary + "]";
	}

}
