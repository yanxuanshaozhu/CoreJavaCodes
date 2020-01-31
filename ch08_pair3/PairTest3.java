package pers.yanxuanshaozhu.corejavach08.ch08_pair3;

import java.time.LocalDate;

public class PairTest3 {
	public static void main(String[] args) {
		Manager ceo = new Manager("Gus Greedy", 800000, 2003, 12, 15);
		Manager cfo = new Manager("Sid Sneaky", 600000, 2003, 12, 15);
		Pair<Manager> buddis = new Pair<Manager>(ceo, cfo);
		printBuddis(buddis);

		ceo.setBonus(1000000);
		cfo.setBonus(500000);

		Manager[] managers = { ceo, cfo };

		Pair<Employee> result = new Pair<>();
		minmaxBonus(managers, result);
		System.out.println("first: " + result.getFirst().getName() + ", second: " + result.getSecond().getName());
		maxminBonus(managers, result);
		System.out.println("first: " + result.getFirst().getName() + ", second: " + result.getSecond().getName());
	}

	public static void printBuddis(Pair<? extends Employee> p) {
		Employee first = p.getFirst();
		Employee second = p.getSecond();
		System.out.println(first.getName() + " and " + second.getName() + " are buddis.");
	}

	public static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {
		if (a.length == 0) {
			return;
		}
		Manager min = a[0];
		Manager max = a[0];
		for (int i = 0; i < a.length; i++) {
			if (min.getBonus() > a[i].getBonus()) {
				min = a[i];
			}
			if (max.getBonus() < a[i].getBonus()) {
				max = a[i];
			}
		}
		result.setFirst(min);
		result.setSecond(max);
	}

	public static void maxminBonus(Manager[] a, Pair<? super Manager> result) {
		minmaxBonus(a, result);
		PairAlg.swap(result);
	}
}

class PairAlg {
	public static boolean hasNulls(Pair<?> p) {
		return p.getFirst() == null || p.getSecond() == null;
	}

	public static void swap(Pair<?> p) {
		swapHelper(p);
	}

	public static <T> void swapHelper(Pair<T> p) {
		T temp = p.getFirst();
		p.setFirst(p.getSecond());
		p.setSecond(temp);
	}
}

class Employee {
	private String name;
	private double salary;
	private LocalDate hireDate;

	public Employee(String name, double salary, int year, int month, int day) {
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
}

class Manager extends Employee {
	private double bonus;

	public Manager(String name, double salary, int year, int month, int day) {
		super(name, salary, year, month, day);
		bonus = 0;
	}

	public double getSalary() {
		double baseSalary = super.getSalary();
		return baseSalary + bonus;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double value) {
		bonus = value;
	}

}

class Pair<T> {
	private T first;
	private T second;

	public Pair() {
		first = null;
		second = null;
	}

	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public T getSecond() {
		return second;
	}

	public void setSecond(T second) {
		this.second = second;
	}

}
