package pers.yanxuanshaozhu.corejavach06.ch06_staticinnerclass;

public class StaticInnerClassTest {
	public static void main(String[] args) {
		double[] d = new double[20];
		for (int i = 0; i < d.length; i++) {
			d[i] = 100 * Math.random();
		}
		for (double e : d) {
			System.out.printf("%2.2f", e);
			System.out.print('\t');
		}
		System.out.println();

		ArrayAlg.Pair pair = ArrayAlg.minmax(d);
		System.out.printf("min =  %2.2f", pair.getFirst());
		System.out.println();
		System.out.printf("max = %2.2f", pair.getSecond());
	}
}

class ArrayAlg {
	public static class Pair {

		private double first;
		private double second;

		public Pair(double f, double s) {
			first = f;
			second = s;
		}

		public double getFirst() {
			return first;
		}

		public double getSecond() {
			return second;
		}
	}

	public static Pair minmax(double[] values) {
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;

		for (double d : values) {
			if (min > d) {
				min = d;
			}
			if (max < d) {
				max = d;
			}
		}
		return new Pair(min, max);
	}
}