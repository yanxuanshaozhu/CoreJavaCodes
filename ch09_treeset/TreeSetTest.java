package pers.yanxuanshaozhu.corejavach09.ch09_treeset;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest {
	public static void main(String[] args) {
		SortedSet<Item> parts = new TreeSet<Item>();
		parts.add(new Item("Toaster", 1234));
		parts.add(new Item("Widget", 4562));
		parts.add(new Item("Modem", 9912));
		System.out.println(parts);

		// TreeSet implements NavigableSet
		NavigableSet<Item> sortByDescription = new TreeSet<Item>(Comparator.comparing(Item::getDescription));
		sortByDescription.addAll(parts);
		System.out.println(sortByDescription);

	}
}

class Item implements Comparable<Item> {
	private String description;
	private int partNumber;

	public Item(String description, int partNumber) {
		this.description = description;
		this.partNumber = partNumber;
	}

	public String getDescription() {
		return description;
	}

	public int getPartNumber() {
		return partNumber;
	}

	public String toString() {
		return "[description= " + description + ", partNumber= " + partNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + partNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (partNumber != other.partNumber)
			return false;
		return true;
	}

	@Override
	public int compareTo(Item o) {
		int diff = Integer.compare(partNumber, o.partNumber);
		return diff != 0 ? diff : description.compareTo(o.description);
	}

}
