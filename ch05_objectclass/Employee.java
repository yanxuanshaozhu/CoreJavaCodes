package pers.yanxuanshaozhu.corejavach05.ch05_objectclass;

import java.time.LocalDate;
import java.util.Objects;

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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Employee other = (Employee) obj;
		return Objects.equals(name, other.name) && this.salary == other.salary
				&& Objects.equals(hireDate, other.hireDate);
	}

	public int hashCode() {
		return Objects.hash(name, salary, hireDate);
	}

	@Override
	public String toString() {// getClass返回的是運行時類/字節碼文件的class文件的類名，這個比較無語，是帶著全限定類名的
		return getClass().getSimpleName() + "[name=" + name + ", salary=" + salary + ", hireDate=" + hireDate + "]";
	}

}