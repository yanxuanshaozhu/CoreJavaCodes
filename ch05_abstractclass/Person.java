package pers.yanxuanshaozhu.corejavach05.ch05_abstractclass;

public abstract class Person {

	private String name;

	public abstract String getDescription();

	public Person() {
		super();
	}

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
