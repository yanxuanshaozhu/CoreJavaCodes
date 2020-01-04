package pers.yanxuanshaozhu.corejavach06.ch06_timer;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;

class TimePrinter implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("At the tone, the time is " + new Date());
		Toolkit.getDefaultToolkit().beep();

	}

}

public class TimerTest {
	public static void main(String[] args) {

		ActionListener listener = new TimePrinter();
		Timer t = new Timer(3000, listener);
		t.start();
		JOptionPane.showMessageDialog(null, "Quit program");
		System.exit(0);
	}
}
