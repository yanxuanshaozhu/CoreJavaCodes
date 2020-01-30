package pers.yanxuanshaozhu.corejavach07.ch07_stacktrace;

import java.util.Scanner;

public class StackTraceTest {

	public static int factorial(int n) {
		System.out.println("factorial(" + n + "):");
		Throwable throwable = new Throwable();
		StackTraceElement[] frames = throwable.getStackTrace();
		for (StackTraceElement stackTraceElement : frames) {
			System.out.println(stackTraceElement);
		}
		int r;
		if (n <= 1) {
			r = 1;
		} else {
			r = n * factorial(n - 1);
		}
		System.out.println("return " + r);
		return r;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter n: ");
		int n = in.nextInt();
		factorial(n);
		in.close();
	}

}
