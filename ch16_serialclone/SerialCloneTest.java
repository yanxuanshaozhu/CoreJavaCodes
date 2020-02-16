package pers.yanxuanshaozhu.corejavach16.ch16_serialclone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public class SerialCloneTest {
	public static void main(String[] args) throws CloneNotSupportedException {
		Employee harry = new Employee("Harry Hacker", 35000, 1989, 10, 1);
		Employee harry2 = (Employee) harry.clone();

		harry.raiseSalary(10);

		System.out.println(harry);
		System.out.println(harry2);
	}
}

class SerialClonable implements Cloneable, Serializable {
	public Object clone() throws CloneNotSupportedException {
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			try (ObjectOutputStream out = new ObjectOutputStream(bout)) {
				out.writeObject(this);
			}

			try (InputStream bin = new ByteArrayInputStream(bout.toByteArray())) {
				ObjectInputStream in = new ObjectInputStream(bin);
				return in.readObject();
			}
		} catch (IOException | ClassNotFoundException e) {
			CloneNotSupportedException e2 = new CloneNotSupportedException();
			e2.initCause(e);
			throw e2;
		}
	}
}

class Employee extends SerialClonable {
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

	public double raiseSalary(double per) {
		double raise = salary * per / 100;
		salary += raise;
		return salary;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [name=" + name + ", salary=" + salary + ", hireDate=" + hireDate + "]";
	}
}
