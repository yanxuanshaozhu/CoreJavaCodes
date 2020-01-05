package pers.yanxuanshaozhu.corejavach06.ch06_clone;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Employee implements Cloneable {
	private String name;
	private double salary;
	private Date hireDate;

	public Employee() {
		super();
	}

	Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
		hireDate = new Date();
	}

	public void raiseSalary(double percent) {
		salary = salary * (100 + percent) / 100;
	}

	public void setHireDate(int year, int month, int day) {
		// GregorianCalender構造器拿到一個calendar，這個month是從0開始的，因此要減1
		// GregorianCalendar的getTime是 public final Date getTime
		// Date的getTime是 public long getTime
		// Date的setTime 是 public void setTime(long time)
		Date newHireDate = new GregorianCalendar(year, month - 1, day).getTime();
		hireDate.setTime(newHireDate.getTime());
	}

	public Employee clone() throws CloneNotSupportedException {
		Employee cloned = (Employee) super.clone();
		cloned.hireDate = (Date) this.hireDate.clone();
		return cloned;
	}

	@Override
	public String toString() {
		DateFormat formatter = DateFormat.getDateTimeInstance();
		return getClass().getSimpleName() + "[name=" + name + ", salary=" + salary + ", hireDate="
				+ formatter.format(hireDate) + "]";
	}

}
