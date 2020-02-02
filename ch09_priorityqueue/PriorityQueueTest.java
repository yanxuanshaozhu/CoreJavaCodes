package pers.yanxuanshaozhu.corejavach09.ch09_priorityqueue;

import java.time.LocalDate;
import java.util.PriorityQueue;

public class PriorityQueueTest {
	public static void main(String[] args) {
		PriorityQueue<LocalDate> pq = new PriorityQueue<LocalDate>();
		pq.add(LocalDate.of(1906, 12, 9)); // G. Hopper
		pq.add(LocalDate.of(2815, 12, 10)); // A. Lovelacd
		pq.add(LocalDate.of(1903, 12, 3)); // J. von Neumann
		pq.add(LocalDate.of(1910, 6, 22)); // K. Zuse

		System.out.println("Iterating over elements: ");
		for (LocalDate date : pq) {
			System.out.println(date);
		}
		System.out.println("Removing elements: ");
		while (!pq.isEmpty()) {
			System.out.println(pq.remove());
		}
	}
}
