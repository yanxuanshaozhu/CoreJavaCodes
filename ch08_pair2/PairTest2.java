package pers.yanxuanshaozhu.corejavach08.ch08_pair2;

import java.time.LocalDate;

public class PairTest2 {
	public static void main(String[] args) {
		LocalDate[] birthdays = { LocalDate.of(1906, 12, 9), // G. Hopper
				LocalDate.of(1815, 12, 10), // A. Lovelace
				LocalDate.of(1903, 12, 3), // J. von Neumann
				LocalDate.of(1910, 6, 22) // K. Zuse
		};
		Pair<LocalDate> mm = ArrayAlg.<LocalDate>minmax(birthdays);
		System.out.println("min = " + mm.getFirst());
		System.out.println("max = " + mm.getSecond());
	}
}

class ArrayAlg {
	public static <T extends Comparable> Pair<T> minmax(T[] a) {
		if (a == null || a.length == 0) {
			return null;
		}

		T min = a[0];
		T max = a[0];
		for (int i = 0; i < a.length; i++) {
			if (min.compareTo(a[i]) > 0) {
				min = a[i];
			}
			if (max.compareTo(a[i]) < 0) {
				max = a[i];
			}
		}
		return new Pair<T>(min, max);
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

	public T getSecond() {
		return second;
	}

	public void setFirst(T value) {
		first = value;
	}

	public void setSecond(T value) {
		second = value;
	}
}