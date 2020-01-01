package pers.yanxuanshaozhu.corejavach05.ch05_inheritance;

public class ManagerTest {
	public static void main(String[] args) {
		Manager boss = new Manager("Jing Tang", 20000, 2018, 12, 24);
		boss.setBonus(5000);

		Employee[] staff = new Employee[3];

		staff[0] = boss;

		// staff[0].setBonus(5000) 錯誤，編譯類型是Employee,運行類型才是boss。
		// ((Manager) staff[0]).setBonus(5000) 也是可以的
		
		staff[1] = new Employee("Ruan Zheng", 17000, 2018, 10, 1);
		staff[2] = new Employee("Jing Wang", 16000, 2017, 1, 11);

		for (Employee e : staff) {
			System.out.println(e);
		}
	}
}
