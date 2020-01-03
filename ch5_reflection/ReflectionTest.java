package pers.yanxuanshaozhu.corejavach05.ch5_reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {

	public static void printConstructors(Class cl) {
		Constructor[] constructors = cl.getDeclaredConstructors();

		for (Constructor c : constructors) {
			String name = c.getName();
			String modifiers = Modifier.toString(c.getModifiers());
			if (modifiers.length() > 0) {
				System.out.print(modifiers + " ");
			}
			System.out.print(name + " (");
			Class[] paramTypes = c.getParameterTypes();
			for (int i = 0; i < paramTypes.length; i++) {
				if (i > 0) {
					System.out.print(", ");
				}
				// getName()返回全限定類名 但是 getSimpleName()返回非全限定類名
				System.out.print(paramTypes[i].getSimpleName());

			}
			System.out.print(")");
			System.out.println();

		}
	}

	public static void printFields(Class cl) {
		Field[] fields = cl.getDeclaredFields();
		for (Field field : fields) {
			String modifiers = Modifier.toString(field.getModifiers());
			Class type = field.getType();
			String name = field.getName();
			if (modifiers.length() > 0) {

				System.out.print(modifiers + " ");
			}
			System.out.print(type + " ");
			System.out.print(name);
			System.out.println();
		}
	}

	public static void printMethods(Class cl) {
		Method[] methods = cl.getDeclaredMethods();
		for (Method method : methods) {
			String modifiers = Modifier.toString(method.getModifiers());
			Class returnType = method.getReturnType();
			String name = method.getName();
			if (modifiers.length() > 0) {
				System.out.print(modifiers + " ");
			}
			System.out.print(returnType.getSimpleName() + " ");
			System.out.print(name + " (");
			Class[] paramTypes = method.getParameterTypes();
			for (int i = 0; i < paramTypes.length; i++) {
				if (i > 0) {
					System.out.print(", ");
				}
				// getName()返回全限定類名 但是 getSimpleName()返回非全限定類名
				System.out.print(paramTypes[i].getSimpleName());

			}
			System.out.print(")");
			System.out.println();
		}
	}

	public static void main(String[] args) {

		String name;
		if (args.length > 0) {
			name = args[0];
		} else {
			Scanner in = new Scanner(System.in);
			System.out.println("Enter class name(e.g. java.util.Date)");
			name = in.next();
		}

		try {
			Class cl = Class.forName(name);
			Class supercl = cl.getSuperclass();
			String modifiers = Modifier.toString(cl.getModifiers());
			if (modifiers.length() > 0) {
				System.out.print(modifiers + " ");
			}
			System.out.print(name + " ");
			if (supercl != null && supercl != Object.class) {
				System.out.print("extends " + supercl.getName());
			}
			System.out.println();
			System.out.println();
			printConstructors(cl);
			System.out.println();
			printFields(cl);
			System.out.println();
			printMethods(cl);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 這個System.exit(int n) 對於非0的int表示非正常結束JVM,等價於Runtime.getRunTime.exit(int n)
		System.exit(0);
		;
	}
}