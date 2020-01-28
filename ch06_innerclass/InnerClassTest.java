package pers.yanxuanshaozhu.corejava06.ch06_innerclass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class InnerClassTest {
	public static void main(String[] args) {
		TalkingClock clock = new TalkingClock(1000, true);
		clock.start();

		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}
}

class TalkingClock {
	private int interval;
	private boolean beep;

	public TalkingClock(int interval, boolean beep) {
		this.interval = interval;
		this.beep = beep;
	}

	public void start() {
		ActionListener listener = this.new TimePrinter();
		Timer timer = new Timer(interval, listener);
		timer.start();
	}

	public class TimePrinter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("At the tone, the time is " + new Date());
			if (TalkingClock.this.beep) {
				Toolkit.getDefaultToolkit().beep();
			}
		}

	}
}
