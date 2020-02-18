package pers.yanxuanshaozhu.corejavach18.ch18_socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {
	public static void main(String[] args) throws IOException {
		try (Socket socket = new Socket("time-a.nist.gov", 13);
				Scanner in = new Scanner(socket.getInputStream(), "UTF-8")) {
			while (in.hasNextLine()) {
				String line = in.nextLine();
				System.out.println(line);
			}
		}

		try (Socket socket = new Socket("time-a.nist.gov", 13);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			while (bufferedReader.readLine() != null) {
				String line = bufferedReader.readLine();
				System.out.println(line);
			}
		}
	}
}
