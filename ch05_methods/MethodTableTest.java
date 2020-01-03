package pers.yanxuanshaozhu.corejavach05.ch05_methods;

import java.lang.reflect.Method;

public class MethodTableTest {

	public static double square(double x) {
		return x * x;
	}

	public static void printTable(double from, double to, int n, Method f) {
		System.out.println(f);
		double dx = (to - from) / (n - 1);
		for (double x = from; x <= to; x += dx) {
			try {
				double y = (Double) f.invoke(null, x);
				System.out.printf("%10.4f | %10.4f%n", x, y);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args)  throws Exception{
	
			Method square = MethodTableTest.class.getMethod("square", double.class);
			
			//报错：NoSuchMethodException
			
			//Method sqrt = MethodTableTest.class.getMethod("sqrt", double.class);

			printTable(1, 10, 10, square);
			System.out.println();
			//printTable(1, 10, 10, sqrt);
		
	}
}
