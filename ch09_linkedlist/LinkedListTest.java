package pers.yanxuanshaozhu.corejavach09.ch09_linkedlist;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListTest {
	public static void main(String[] args) {
		List<String> a = new LinkedList<String>();
		a.add("Amy");
		a.add("Carl");
		a.add("Erica");
		
		List<String> b = new LinkedList<String>();
		b.add("Bob");
		b.add("Doug");
		b.add("Frances");
		b.add("Gloria");
		
		System.out.println("List a: " + a);
		System.out.println("List b: " + b);
		
		ListIterator<String> itera = a.listIterator();
		ListIterator<String> iterb = b.listIterator();
		
		while (iterb.hasNext()) {
			if (itera.hasNext()) {
				itera.next();
			}
			itera.add(iterb.next());
		}
		System.out.println("List a after adding list b: " + a);
		
		iterb = b.listIterator();
		while (iterb.hasNext()) {
			iterb.next();
			if (iterb.hasNext()) {
				iterb.next();
				iterb.remove();
			}
		}
		System.out.println("List b after removing every second element: " + b);
		a.removeAll(b);
		System.out.println("List a after removing all elements in list b " + a);
 	}
}
