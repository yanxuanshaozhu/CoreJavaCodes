package pers.yanxuanshaozhu.corejavach05.ch05_objectclass;

public class EqualsTest {
	public static void main(String[] args) {
		Employee employee1 = new Employee("Jing Tang", 20000, 2018, 12, 24);
		Employee employee2 = employee1;
		Employee employee3 = new Employee("Jing Tang", 20000, 2018, 12, 24);
		Employee employee4 = new Employee("Jing Wang", 20000, 2017, 1, 10);

		System.out.println("employee1 == employ2 " + (employee1 == employee2));
		System.out.println("employee1 == employ3 " + (employee1 == employee3));
		System.out.println("employee1.equals(employee3) " + (employee1.equals(employee3)));
		System.out.println("employee1.equals(employee4) " + (employee1.equals(employee4)));
		System.out.println(employee1.toString());

		Manager manager1 = new Manager("Ruan Zhou", 30000, 2015, 12, 1);
		Manager manager2 = new Manager("Ruan Zhou", 30000, 2015, 12, 1);
		manager2.setBonus(5000);

		System.out.println(manager2.toString());
		System.out.println("manager1.equals(manager2) " + manager1.equals(manager2));
		System.out.println("employee1.hashCode " + employee1.hashCode());
		System.out.println("employee3.hashCode " + employee3.hashCode());
		System.out.println("manager1.hashCode " + manager1.hashCode());
		System.out.println("manager2.hashCode " + manager2.hashCode());
		
		
		System.out.println("********************");
		Employee employee5 = new Employee("Jing Tang", 20000, 2018, 12, 24);
		Manager manager3 = new Manager("Jing Tang", 20000, 2018, 12, 24);
		manager3.setBonus(5000);
		System.out.println(employee5.equals(manager3));

	}
}
