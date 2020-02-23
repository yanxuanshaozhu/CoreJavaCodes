package pers.yanxuanshaozhu.corejavach19.ch19_view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class ViewDB {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frme;
			try {
				frme = new ViewDBFrame();
				frme.setTitle("ViewDB");
				frme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frme.setVisible(true);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		});
	}

}

class ViewDBFrame extends JFrame {
	private JButton previousButton;
	private JButton nextButton;
	private JButton deleteButton;
	private JButton saveButton;
	private DataPanel dataPanel;
	private Component scrollPane;
	private JComboBox<String> tableNames;
	private Properties props;
	private CachedRowSet crs;
	private Connection connection;

	public ViewDBFrame() throws ClassNotFoundException, IOException {
		tableNames = new JComboBox<String>();

		try {
			readDatabaseProperties();
			connection = getConnection();
			DatabaseMetaData meta = connection.getMetaData();
			try (ResultSet mrs = meta.getTables(null, null, null, new String[] { "TABLE" })) {
				while (mrs.next()) {
					tableNames.addItem(mrs.getString(3));
				}
			}
		} catch (SQLException e) {
			for (Throwable throwable : e) {
				throwable.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		tableNames.addActionListener(event -> showTable((String) tableNames.getSelectedItem(), connection));
		add(tableNames, BorderLayout.NORTH);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					for (Throwable throwable : e) {
						throwable.printStackTrace();
					}
				}
			}
		});

		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);

		previousButton = new JButton("Previous");
		previousButton.addActionListener(event -> showPreviousRow());
		buttonPanel.add(previousButton);

		nextButton = new JButton("Next");
		nextButton.addActionListener(event -> showNextRow());
		buttonPanel.add(nextButton);

		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(event -> deleteRow());
		buttonPanel.add(deleteButton);

		saveButton = new JButton("Save");
		saveButton.addActionListener(event -> saveChanges());
		buttonPanel.add(saveButton);
		if (tableNames.getItemCount() > 0) {
			showTable(tableNames.getItemAt(0), connection);
		}

	}

	public void showTable(String tableName, Connection connection) {
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from " + tableName)) {
			RowSetFactory factory = RowSetProvider.newFactory();
			crs = factory.createCachedRowSet();
			crs.setTableName(tableName);
			crs.populate(resultSet);

			if (scrollPane != null) {
				remove(scrollPane);
			}
			dataPanel = new DataPanel(crs);
			scrollPane = new JScrollPane(dataPanel);
			add(scrollPane, BorderLayout.CENTER);
			pack();
			showNextRow();

		} catch (SQLException e) {
			for (Throwable throwable : e) {
				throwable.printStackTrace();
			}
		}
	}

	public void showPreviousRow() {
		try {
			if (crs == null || crs.isFirst()) {
				return;
			}
			crs.previous();
			dataPanel.showRow(crs);
		} catch (SQLException e) {
			for (Throwable throwable : e) {
				throwable.printStackTrace();
			}
		}
	}

	public void showNextRow() {
		try {
			if (crs == null || crs.isLast()) {
				return;
			}
			crs.next();
			dataPanel.showRow(crs);
		} catch (SQLException e) {
			for (Throwable throwable : e) {
				throwable.printStackTrace();
			}
		}
	}

	public void deleteRow() {
		if (crs == null) {
			return;
		}
		new SwingWorker<Void, Void>() {
			public Void doInBackground() throws SQLException {
				crs.deleteRow();
				crs.acceptChanges(connection);
				if (crs.isAfterLast()) {
					if (!crs.last()) {
						crs = null;
					}
				}
				return null;
			}

			public void done() {
				dataPanel.showRow(crs);
			}
		}.execute();
	}

	public void saveChanges() {
		if (crs == null) {
			return;
		}
		new SwingWorker<Void, Void>() {
			public Void doInBackground() throws SQLException {
				dataPanel.setRow(crs);
				crs.acceptChanges(connection);
				return null;
			}
		}.execute();

	}

	private void readDatabaseProperties() throws IOException, ClassNotFoundException {
		props = new Properties();
		try (InputStream in = Files
				.newInputStream(Paths.get("./src/pers/yanxuanshaozhu/corejavach19/ch19_view/database.properties"))) {
			props.load(in);
		}
		Class.forName(props.getProperty("jdbcdriver"));
	}

	private Connection getConnection() throws SQLException {
		String url = props.getProperty("url");
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}

class DataPanel extends JPanel {

	private List<JTextField> fields;

	public DataPanel(RowSet rs) throws SQLException {
		fields = new ArrayList<JTextField>();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 1;
		gbc.gridheight = 1;

		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			gbc.gridy = i - 1;

			String columnName = rsmd.getColumnLabel(i);
			gbc.gridx = 0;
			gbc.anchor = GridBagConstraints.EAST;
			add(new JLabel(columnName), gbc);

			int columnWidth = rsmd.getColumnDisplaySize(i);
			JTextField tb = new JTextField(columnWidth);
			if (!rsmd.getColumnClassName(i).equals("java.lang.String")) {
				tb.setEditable(false);
			}

			fields.add(tb);

			gbc.gridx = 1;
			gbc.anchor = GridBagConstraints.WEST;
			add(tb, gbc);
		}
	}

	public void showRow(ResultSet rs) {
		try {
			if (rs == null) {
				return;
			}
			for (int i = 0; i <= fields.size(); i++) {
				String field = rs == null ? "" : rs.getString(i);
				JTextField tb = fields.get(i - 1);
				tb.setText(field);
			}
		} catch (SQLException e) {
			for (Throwable throwable : e) {
				throwable.printStackTrace();
			}
		}
	}

	public void setRow(RowSet rs) throws SQLException {
		for (int i = 1; i <= fields.size(); i++) {
			String field = rs.getString(i);
			JTextField tb = fields.get(i - 1);
			if (!field.equals(tb.getText())) {
				rs.updateString(i, tb.getText());
			}
			rs.updateRow();
		}
	}
}