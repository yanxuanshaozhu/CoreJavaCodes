package pers.yanxuanshaozhu.corejavach14.ch14_synch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	private final double[] accounts;
	private Lock banLock;
	private Condition sufficientFunds;

	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		Arrays.fill(accounts, initialBalance);
		banLock = new ReentrantLock();
		sufficientFunds = banLock.newCondition();
	}

	public void transfer(int from, int to, double amount) throws InterruptedException {
		banLock.lock();
		try {
			while (accounts[from] < amount) {
				sufficientFunds.await();
			}
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf("%10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf("Total Balance: %10.2f%n", getTotalBalance());
			sufficientFunds.signalAll();
		} finally

		{
			banLock.unlock();
		}
	}

	public double getTotalBalance() {
		banLock.lock();
		try {

			double sum = 0;
			for (double d : accounts) {
				sum += d;
			}
			return sum;
		} finally {
			banLock.unlock();
		}
	}

	public int size() {
		return accounts.length;
	}
}
