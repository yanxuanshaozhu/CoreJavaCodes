package pers.yanxuanshaozhu.corejavach06.ch06_timer;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;

class TimePrinter implements ActionListener {

	/* java.awt.event包的ActionListener接口
	 * public interface ActionListener extends EventListener {
	 * 		public void ActionPerformed( ActionEvent event)
	 * }
	 * 這個接口的繼承類的對象通過使用addActionListener方法注冊了一個組件。當事件發生的時候，對象的ActionPerformed方法啓動。
	 * java.awt。event包的ActionEvent類
	 * public class java.awt.event.ActionEvent extends AWTEvent
	 * public abstract class AWTEvent extends EventObject
	 * public class EventObject implements java.io.Serializable
	 * ActionEvent代表了組件定義的行爲的發生
	 */
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("At the tone, the time is " + new Date());
		// java.awt包的Toolkit抽象類
		//public abstract java.awt.Toolkit extends Object
		//getDefaultToolkit獲得默認工具箱
		//beep發出一聲響，和系統環境設置與硬件性能有關
		Toolkit.getDefaultToolkit().beep();

	}

}

public class TimerTest {
	public static void main(String[] args) {

		ActionListener listener = new TimePrinter();
		//Timer(int delay, ActionListener actionListener) 第一個參數是時間間隔，以毫秒表示；第二個參數是監聽器
		//使用方法，首先建立一個Timer對象，然後給Timer對象傳一個ActionListener對象，再調用Timer對象的start方法
		//java.swing包的Timer類
		Timer t = new Timer(3000, listener);
		t.start();
		JOptionPane.showMessageDialog(null, "Quit program");
		System.exit(0);
	}
}
