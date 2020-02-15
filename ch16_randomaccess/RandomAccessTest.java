package pers.yanxuanshaozhu.corejavach16.ch16_randomaccess;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

public class RandomAccessTest {
	public static void main(String[] args) throws IOException {
		Employee[] staff = new Employee[3];

		staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
		staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

		try (DataOutputStream out = new DataOutputStream(
				new FileOutputStream("./src/pers/yanxuanshaozhu/corejavach16/ch16_randomaccess/employee.dat"))) {

			for (Employee e : staff) {
				writeData(out, e);
			}
		}

		try (RandomAccessFile in = new RandomAccessFile(
				"./src/pers/yanxuanshaozhu/corejavach16/ch16_randomaccess/employee.dat", "r")) {

			int n = (int) (in.length() / Employee.RECORD_SIZE);
			Employee[] newStaff = new Employee[n];

			for (int i = n - 1; i >= 0; i--) {
				newStaff[i] = new Employee();
				in.seek(i * Employee.RECORD_SIZE);
				newStaff[i] = readData(in);
			}

			for (Employee e : newStaff) {
				System.out.println(e);
			}
		}
	}

	public static void writeData(DataOutput out, Employee e) throws IOException {
		DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
		out.writeDouble(e.getSalary());

		LocalDate hireDate = e.getHireDate();
		out.writeInt(hireDate.getYear());
		out.writeInt(hireDate.getMonthValue());
		out.writeInt(hireDate.getDayOfMonth());
	}

	public static Employee readData(DataInput in) throws IOException {
		String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
		double salary = in.readDouble();
		int year = in.readInt();
		int month = in.readInt();
		int day = in.readInt();
		return new Employee(name, salary, year, month - 1, day);
	}
}
