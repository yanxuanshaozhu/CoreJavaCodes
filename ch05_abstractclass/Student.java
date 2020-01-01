package pers.yanxuanshaozhu.corejavach05.ch05_abstractclass;

public class Student extends Person {

	private String major;

	public Student() {
		super();
	}

	public Student(String name, String major) {
		super(name);
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "Student [name=" + getName() + ", major=" + major + "]";
	}

	@Override
	public String getDescription() {
		return toString();
	}
	
	

}
