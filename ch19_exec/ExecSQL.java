package pers.yanxuanshaozhu.corejavach19.ch19_exec;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class ExecSQL {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		try (Scanner in = args.length == 0 ? new Scanner(System.in) : new Scanner(Paths.get(args[0]), "UTF-8")) {
			try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
				while (true) {
					if (args.length == 0) {
						System.out.println("Enter command or EXIT to exit");
					}
					if (!in.hasNextLine()) {
						return;
					}
					String line = in.nextLine().trim();
					if (line.equalsIgnoreCase("EXIT")) {
						return;
					}
					if (line.endsWith(";")) {
						line = line.substring(0, line.length() - 1);
					}
					try {
						boolean isResult = statement.execute(line);
						if (isResult) {
							try (ResultSet resultSet = statement.getResultSet()) {
								showResultSet(resultSet);
							}
						} else {
							int updateCount = statement.getUpdateCount();
							System.out.println(updateCount + " rows updated");
						}
					} catch (SQLException e) {
						for (Throwable throwable : e) {
							throwable.printStackTrace();
						}
					}
				}
			}
		} catch (SQLException e) {
			for (Throwable throwable : e) {
				throwable.printStackTrace();
			}
		}
	}

	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		Properties properties = new Properties();
		try (InputStream in = Files
				.newInputStream(Paths.get("./src/pers/yanxuanshaozhu/corejavach19/ch19_exec/database.properties"))) {
			properties.load(in);
		}

		Class.forName(properties.getProperty("jdbcdriver"));
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}

	public static void showResultSet(ResultSet resultSet) throws SQLException {
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int i = 0; i <= columnCount; i++) {
			if (i > 1) {
				System.out.print(", ");
			}
			System.out.print(metaData.getColumnLabel(i));
		}
		System.out.println();

		while (resultSet.next()) {
			for (int i = 0; i <= columnCount; i++) {
				if (i > 1) {
					System.out.print(", ");
				}
				System.out.print(resultSet.getString(i));
			}
			System.out.println();
		}
	}
}
