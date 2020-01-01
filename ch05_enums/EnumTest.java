package pers.yanxuanshaozhu.corejavach05.ch05_enums;

enum Size {
	SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

	private String abbreviation;

	private Size() {

	}

	private Size(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getArrbeviation() {
		return abbreviation;
	}
}

public class EnumTest {
	public static void main(String[] args) {
		for (Size size : Size.values()) {
			System.out.println(size.ordinal() + " => " + size + " => " + size.getArrbeviation());
		}

		System.out.println();
		// enum的compareTo返回的是this.ordinal()-other.ordinal()
		System.out.println(Size.SMALL.compareTo(Size.EXTRA_LARGE));
	}
}
