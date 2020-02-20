package pers.yanxuanshaozhu.corejavach18.ch18_interruptible;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InterruptibleSocketTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new InterruptibleSocketFrame();
			frame.setTitle("InterruptibleSocketTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

class InterruptibleSocketFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private Scanner in;
	private JButton interruptibleButton;
	private JButton blockingButton;
	private JButton cancelButton;
	private JTextArea messages;
	private TestServer server;
	private Thread connecThread;

	public InterruptibleSocketFrame() {
		JPanel northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);

		final int TEXT_ROWS = 20;
		final int TEXT_COLUMNS = 60;
		messages = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
		add(new JScrollPane(messages));

		interruptibleButton = new JButton("Interruptible");
		blockingButton = new JButton("Blocking");

		northPanel.add(interruptibleButton);
		northPanel.add(blockingButton);

		interruptibleButton.addActionListener(event -> {
			interruptibleButton.setEnabled(false);
			blockingButton.setEnabled(false);
			cancelButton.setEnabled(true);
			connecThread = new Thread(() -> {
				try {
					connectInterruptibly();
				} catch (IOException e) {
					messages.append("\nInterruptibleSocketTest.connectInterruptibly: " + e);
				}
			});
			connecThread.start();
		});

		blockingButton.addActionListener(event -> {
			interruptibleButton.setEnabled(false);
			blockingButton.setEnabled(false);
			cancelButton.setEnabled(true);
			connecThread = new Thread(() -> {
				try {
					connectBlocking();
				} catch (IOException e) {
					messages.append("\nInterruptibleSocketTest.connectBlocking: " + e);
				}
			});
		});

		cancelButton = new JButton("Cancel");
		cancelButton.setEnabled(false);
		northPanel.add(cancelButton);
		cancelButton.addActionListener(event -> {
			connecThread.interrupt();
			cancelButton.setEnabled(false);
		});
		server = new TestServer();
		new Thread(server).start();
		pack();

	}

	public void connectInterruptibly() throws IOException {
		messages.append("Interruptible:\n");
		try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8189))) {
			in = new Scanner(channel, "UTF-8");
			while (!Thread.currentThread().isInterrupted()) {
				messages.append("Reading ");
				if (in.hasNextLine()) {
					String line = in.nextLine();
					messages.append(line);
					messages.append("\n");
				}
			}
		} finally {
			EventQueue.invokeLater(() -> {
				messages.append("Channel closed\n");
				interruptibleButton.setEnabled(true);
				blockingButton.setEnabled(true);
			});
		}
	}

	public void connectBlocking() throws IOException {
		messages.append("Blocking:\n");
		try (Socket socket = new Socket("localhost", 8189)) {
			in = new Scanner(socket.getInputStream(), "UTF-8");
			while (!Thread.currentThread().isInterrupted()) {
				messages.append("Reading ");
				if (in.hasNextLine()) {
					String line = in.nextLine();
					messages.append(line);
					messages.append("\n");
				}
			}
		} finally {
			EventQueue.invokeLater(() -> {
				messages.append("Socket closed\n");
				interruptibleButton.setEnabled(true);
				blockingButton.setEnabled(true);
			});
		}
	}

	class TestServer implements Runnable {
		public void run() {
			try (ServerSocket socket = new ServerSocket(8189)) {
				while (true) {
					Socket incoming = socket.accept();
					Runnable runnable = new TestServerHandler(incoming);
					Thread thread = new Thread(runnable);
					thread.start();
				}
			} catch (IOException e) {
				messages.append("\nTestServer.run: + e");
			}
		}

	}

	class TestServerHandler implements Runnable {
		private Socket incoming;
		private int counter;

		public TestServerHandler(Socket socket) {
			incoming = socket;
		}

		public void run() {
			try {
				try {
					OutputStream outputStream = incoming.getOutputStream();
					PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"),
							true/* autoFlust */);
					while (counter < 100) {
						counter++;
						if (counter <= 10) {
							out.println(counter);
						}
						Thread.sleep(100);
					}
				} finally {
					incoming.close();
					messages.append("Closing server\n");
				}
			} catch (Exception e) {
				messages.append("\nTestServerHandler.run: " + e);
			}
		}
	}
}
