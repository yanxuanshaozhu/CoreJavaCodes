package pers.yanxuanshaozhu.corejavach06.ch06_interfaces;

public class Manager extends Employee {
	private double bonus;

	Manager() {
		super();
	}

	public Manager(String name, double salary, double bonus) {
		super(name, salary);
		this.bonus = bonus;
	}

	public double getSalary() {
		double baseSalary = super.getSalary();
		return baseSalary + bonus;
	}
	
	@Override 
	public int compareTo(Employee o) {
		if (this.getClass() != o.getClass()) {
			return Double.compare(this.getSalary(), o.getSalary());
		}
		else {
			return Double.compare(this.getSalary(),((Manager)o).getSalary());
		}
	}
	@Override
	public String toString() {
		return super.toString() + "[bonus=" + bonus + "]";
	}

}