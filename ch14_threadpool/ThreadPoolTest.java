package pers.yanxuanshaozhu.corejavach14.ch14_threadpool;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.org.apache.regexp.internal.recompile;

public class ThreadPoolTest {
	public static void main(String[] args) throws Exception {
		try (Scanner in = new Scanner(System.in)) {
			System.out.print("Enter the directory(e.g. /usr/local/jdk5.0/src):");
			String directory = in.nextLine();
			System.out.print("Enter keyword(e.g. volatile):");
			String keyword = in.nextLine();

			ExecutorService pool = Executors.newCachedThreadPool();

			MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
			Future<Integer> result = pool.submit(counter);

			try {
				System.out.println(result.get() + " matching files");
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
			}
			pool.shutdown();

			int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
			System.out.println("largest pool size = " + largestPoolSize);
		}
	}
}

class MatchCounter implements Callable<Integer> {
	private File directory;
	private String keyword;
	private ExecutorService pool;
	private int count;

	public MatchCounter(File directory, String keyword, ExecutorService pool) {
		this.directory = directory;
		this.keyword = keyword;
		this.pool = pool;
	}

	public Integer call() {
		count = 0;
		try {
			File[] files = directory.listFiles();
			List<Future<Integer>> rusults = new ArrayList<Future<Integer>>();
			for (File file : files) {
				if (file.isDirectory()) {
					MatchCounter counter = new MatchCounter(file, keyword, pool);
					Future<Integer> result = pool.submit(counter);
					rusults.add(result);
				} else {
					if (search(file)) {
						count++;
					}
				}
			}
			for (Future<Integer> result : rusults) {
				try {
					count += result.get();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}

		} catch (InterruptedException e) {
		}
		return count;
	}

	public boolean search(File file) {
		try {
			try (Scanner in = new Scanner(file, "UTF-8")) {
				boolean found = false;
				while (!found && in.hasNextLine()) {
					String line = in.nextLine();
					if (line.contains(keyword)) {
						found = true;
					}
				}
				return found;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
