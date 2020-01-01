package pers.yanxuanshaozhu.corejavach05.ch05_inheritance;

public class Manager extends Employee {
	private double bonus;

	Manager() {
		super();
	}

	public Manager(String name, double salary, int year, int month, int day) {
		super(name, salary, year, month, day);
		bonus = 0;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	

	public double getSalary() {
		double baseSalary = super.getSalary();
		return baseSalary + bonus;
	}

	@Override
	public String toString() {
		return "Manager [name=" + getName() + ", salary=" + getSalary() + ", hireDate=" + getHireDate() +", bonus=" + bonus + "]";
	}
}
