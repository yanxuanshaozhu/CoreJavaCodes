package pers.yanxuanshaozhu.corejavach19.ch19_test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TestDb {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		try {
			TestDb.runTest();
		} catch (SQLException e) {
			for (Throwable throwable : e) {
				throwable.printStackTrace();
			}
		}
	}

	public static void runTest() throws ClassNotFoundException, SQLException, IOException {
		try (Connection connection = TestDb.getConnection(); Statement statement = connection.createStatement()) {
			statement.executeUpdate("create table if not exists greetings(message char(20))");
			statement.executeUpdate("insert into greetings values('Hello World1!')");
			statement.executeUpdate("insert into greetings values('Hello World2!')");
			try (ResultSet resultSet = statement.executeQuery("select * from greetings")) {
				while (resultSet.next()) {
					System.out.println(resultSet.getString(1));
				}
			}
			statement.executeUpdate("drop table greetings");
		}
	}

	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		Properties properties = new Properties();
		try (InputStream in = Files
				.newInputStream(Paths.get("./src/pers/yanxuanshaozhu/corejavach19/ch19_test/database.properties"))) {
			properties.load(in);
		}
		Class.forName(properties.getProperty("jdbcdriver"));
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}
