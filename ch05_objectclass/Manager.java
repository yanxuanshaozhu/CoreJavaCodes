package pers.yanxuanshaozhu.corejavach05.ch05_objectclass;

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
	public boolean equals(Object obj) {
		/*
		 * 如果父類的equals方法為false ，説明父類的fields裏面就有不同的，那麽顯然子類equals直接為false
		 * 如果父類的equals方法為true，那麽繼續比較子類獨有的field
		 * */
		if(!super.equals(obj)) {                                        
			return false;             
		}
		Manager other = (Manager) obj;
		return this.bonus == other.bonus;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + 17 * new Double(bonus).hashCode();
	}

	@Override
	public String toString() {
		return super.toString() + "[bonus=" + bonus + "]";
	}
}