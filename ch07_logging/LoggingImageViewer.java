package pers.yanxuanshaozhu.corejavach07.ch07_logging;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LoggingImageViewer {
	public static void main(String[] args) {
		if ((System.getProperty("java.util.logging.config.class") == null)
				&& (System.getProperty("java.util.logging.config.file") == null)) {
			try {
				Logger.getLogger("com.horstmann.corejava").setLevel(Level.ALL);
				final int LOG_ROTATION_COUNT = 10;
				Handler handler = new FileHandler("%/LoggingViewer.loge", 0, LOG_ROTATION_COUNT);
				Logger.getLogger("com.horstmann.corejava").addHandler(handler);
			} catch (IOException e) {
				Logger.getLogger("com.horstmann.corejava").log(Level.SEVERE, "Can't create log file handler", e);
			}
		}
	}
}

class ImageViewerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;

	private JLabel label;
	private static Logger logger = Logger.getLogger("com.horstmann.corejava");

	public ImageViewerFrame() {
		logger.entering("ImageViewerFrame", "<init>");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("File");
		menuBar.add(menu);

		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new FileOpenListener());

		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logger.fine("Exiting");
				System.exit(0);
			}
		});

		label = new JLabel();
		add(label);
		logger.exiting("ImageViewerFrame", "<init>");

	}

	private class FileOpenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed", event);

			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));

			chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
				public boolean accept(File f) {
					return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
				}

				public String getDescription() {
					return "GIF Images";
				}
			});

			int r = chooser.showOpenDialog(ImageViewerFrame.this);

			if (r == JFileChooser.APPROVE_OPTION) {
				String name = chooser.getSelectedFile().getPath();
				logger.log(Level.FINE, "Reading file {0}", name);
				label.setIcon(new ImageIcon(name));
			} else {
				logger.fine("File open dialog canceled.");
			}
			logger.exiting("ImageViewerFrame.FileOpenListener", "actionPerformed");
		}

	}

}

class WindowHandler extends StreamHandler {
	private JFrame frame;

	public WindowHandler() {
		frame = new JFrame();
		final JTextArea output = new JTextArea();
		output.setEditable(false);
		frame.setSize(200, 200);
		frame.add(new JScrollPane(output));
		frame.setFocusableWindowState(false);
		frame.setVisible(true);
		setOutputStream(new OutputStream() {

			@Override
			public void write(int b) throws IOException {

			}

			public void write(byte[] b, int off, int len) {
				output.append(new String(b, off, len));
			}
		});

	}

	public void publish(LogRecord record) {
		if (!frame.isVisible()) {
			return;
		}
		super.publish(record);
		flush();
	}
}