package pers.yanxuanshaozhu.corejavach14.ch14_bounce;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bounce {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new BounceFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

class BounceFrame extends JFrame {
	private BallComponent comp;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;

	public BounceFrame() {
		setTitle("Bounce");
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonJPanel = new JPanel();
		addButton(buttonJPanel, "Start", event -> addBall());
		addButton(buttonJPanel, "Close", event -> System.exit(0));
		add(buttonJPanel, BorderLayout.SOUTH);
		pack();
	}

	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton();
		c.add(button);
		button.addActionListener(listener);
	}

	public void addBall() {
		try {
			Ball ball = new Ball();
			comp.add(ball);
			for (int i = 1; i <= STEPS; i++) {
				ball.move(comp.getBounds());
				comp.paint(comp.getGraphics());
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
		}
	}
}
