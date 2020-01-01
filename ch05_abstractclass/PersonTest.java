package pers.yanxuanshaozhu.corejavach05.ch05_abstractclass;

public class PersonTest {
	public static void main(String[] args) {

		Person[] persons = new Person[2];

		persons[0] = new Employee("Jing Tang", 25000, 2018, 12, 24);
		persons[1] = new Student("Ruan Zhou", "Physics");

		for (Person e : persons) {
			System.out.println(e.getDescription());
		}

	}
}
