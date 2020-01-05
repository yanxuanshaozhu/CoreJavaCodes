package pers.yanxuanshaozhu.corejavach06.ch06_clone;

public class CloneTest {
	public static void main(String[] args) {
		try {
			Employee original = new Employee("John Q.Public", 50000);
			original.setHireDate(2000, 12, 1);
			Employee copy = original.clone();
			copy.raiseSalary(10);
			copy.setHireDate(2001, 1, 2);
			System.out.println("Original " + original);
			System.out.println("Copy " + copy);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
