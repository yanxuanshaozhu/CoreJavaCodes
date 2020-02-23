package pers.yanxuanshaozhu.corejavach19.ch19_query;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class QueryTest {
	private static final String allQuery = "select books.price ,books.title from books";

	private static final String authorPublisherQuery = "select books.price ,books.titles "
			+ "from books,booksauthors,authors,publishers "
			+ "where authors.author_id = booksauthors.author_id and booksauthors.ISBN = books.ISBN and books.publisher_id = publishers.publisher_id and authors.name = ?";

	private static final String authorQuery = "select books.price ,books.titles " + "from books,booksauthors,authors "
			+ "where authors.author_id = booksauthors.author_id and booksauthors.ISBN = books.ISBN and authors.name = ?";

	private static final String publisherQuery = "select books.price ,books.titles " + "from books,publishers "
			+ "where books.publisher_id = publishers.publisher_id and authors.name = ?";

	private static final String priceUpdate = "update books set price = price + ? "
			+ "where books.publisher_id = (select publisher_id from publishers where name = ?)";

	private static Scanner in;
	private static ArrayList<String> authors = new ArrayList<String>();
	private static ArrayList<String> publishers = new ArrayList<String>();

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		try (Connection connection = getConnection()) {
			in = new Scanner(System.in);
			authors.add("Any");
			publishers.add("Any");
			try (Statement statement = connection.createStatement()) {
				String query = "select name from authors";
				try (ResultSet resultSet = statement.executeQuery(query)) {
					while (resultSet.next()) {
						authors.add(resultSet.getString(1));
					}
				}

				query = "select name from publishers";
				try (ResultSet resultSet = statement.executeQuery(query)) {
					while (resultSet.next()) {
						authors.add(resultSet.getString(1));
					}
				}
			}
			boolean done = false;
			while (!done) {
				System.out.print("Q)uery C)hange prices E)xit: ");
				String input = in.next().toUpperCase();
				if (input.equals("Q")) {
					executeQuery(connection);
				} else if (input.equals("C")) {
					changePrices(getConnection());
				} else {
					done = true;
				}
			}
		} catch (SQLException e) {
			for (Throwable throwable : e) {
				System.out.println(throwable.getMessage());
			}
		}
	}

	public static void executeQuery(Connection connection) throws SQLException {
		String author = select("Authors:", authors);
		String publisher = select("Publishers:", publishers);
		PreparedStatement statement;
		if (!author.equals("Any") && !publisher.equals("Any")) {
			statement = connection.prepareStatement(authorPublisherQuery);
			statement.setString(1, author);
			statement.setString(2, publisher);
		}

		else if (!author.equals("Any") && publisher.equals("Any")) {
			statement = connection.prepareStatement(authorQuery);
			statement.setString(1, author);
		} else if (author.equals("Any") && !publisher.equals("Any")) {
			statement = connection.prepareStatement(publisherQuery);
			statement.setString(1, publisher);
		} else {
			statement = connection.prepareStatement(allQuery);
		}
		try (ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + "," + resultSet.getString(2));
			}
		}
	}

	public static void changePrices(Connection connection) throws SQLException {
		String publisher = select("Publishers:", publishers.subList(1, publishers.size()));
		System.out.print("Change prices by:");
		double priceChange = in.nextDouble();
		PreparedStatement statement = connection.prepareStatement(priceUpdate);
		statement.setDouble(1, priceChange);
		statement.setString(2, publisher);
		int r = statement.executeUpdate();
		System.out.println(r + " records updated");

	}

	public static String select(String prompt, List<String> options) {
		while (true) {
			System.out.println(prompt);
			for (int i = 0; i < options.size(); i++) {
				System.out.printf("%2d) %s%n", i + 1, options.get(i));
			}
			int sel = in.nextInt();
			if (sel > 0 && sel < options.size()) {
				return options.get(sel - 1);
			}
		}
	}

	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		Properties properties = new Properties();
		try (InputStream in = Files
				.newInputStream(Paths.get("./src/pers/yanxuanshaozhu/corejavach19/ch19_query/database.properties"))) {
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
