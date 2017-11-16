package sampleApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC Example Application Class
 * 
 * @author Christian Zirkelbach
 *
 */
public class JDBCExample {

	private static final boolean VERBOSE = true;

	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/sampleApplication";

	// Database credentials
	private static final String USER = "christian";
	private static final String PASS = "test";

	public static Connection connect() {
		Connection connection = null;

		try {
			Class.forName(JDBC_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
			e.printStackTrace();
			return connection;
		}

		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return connection;

		}
		return connection;
	}

	public static void disconnect(final Connection conn) {
		try {
			conn.close();
			// System.out.println("Connection closed!");
		} catch (SQLException e) {
			System.out.println("Can't close connection");
			e.printStackTrace();
		}
	}

	/**
	 * Generator for Statements queryTypes 0 (execute), 1 (executeQuery)
	 * 
	 * @param query
	 * @param queryType
	 * @throws SQLException
	 */
	public static void executeStatementGenerator(final String query, final int queryType) throws SQLException {

		final Connection conn = connect();

		if (conn != null) {

			final Statement stmt = conn.createStatement();

			if (queryType == 1) {

				final ResultSet rs = stmt.executeQuery(query);

				if (VERBOSE) {
					// Resultset
					while (rs.next()) {
						final String name = rs.getString("n");
						final String date = rs.getString("d");
						final String itemno = rs.getString("i");
						System.out.print(name + "	|	");
						System.out.print(date + "	|	");
						System.out.println(itemno);
					}

					rs.close();

				}
			} else if (queryType == 0) {
				stmt.execute(query);
			}

			else {
				System.out.println("Invalid queryType!");
			}

			stmt.close();
			disconnect(conn);

		} else {
			System.out.println("Failed to connect!");
		}

		conn.close();
	}

	/**
	 * Generator for PreparedStatements queryTypes 0 (execute), 1 (executeQuery)
	 * 
	 * @param query
	 * @param value
	 * @param queryType
	 * @throws SQLException
	 */
	public static void executePreparedStatementGenerator(final String query, final String value, final int queryType)
			throws SQLException {

		final Connection conn = connect();

		if (conn != null) {

			final PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, value);

			if (queryType == 1) {

				final ResultSet rs = preparedStatement.executeQuery();

				if (VERBOSE) {
					// System.out.println("Results:");
					while (rs.next()) {
						final String name = rs.getString("n");
						final String date = rs.getString("d");
						final String itemno = rs.getString("i");
						System.out.print(name + "	|	");
						System.out.print(date + "	|	");
						System.out.println(itemno);
					}

				}
			} else if (queryType == 0) {
				preparedStatement.execute();
			}

			else {
				System.out.println("Invalid queryType!");
			}

			disconnect(conn);
		}

		else {
			System.out.println("Failed to make connection!");
		}

		conn.close();
	}

	public static void main(final String[] argv) throws SQLException {

		/*
		 * Testing queries for the instrumentation
		 */
		initDatabase();

		// testQueries();

	}

	private static void testQueries() throws SQLException {
		/*
		 * Statement
		 */
		executeStatementGenerator(
				"SELECT * FROM purchases p WHERE EXISTS (SELECT * FROM purchases p2 WHERE p.d = p2.d - Interval '1 Month' AND p.n = p2.n AND p.i = p2.i)",
				0);
		executeStatementGenerator(
				"SELECT * FROM purchases p WHERE EXISTS (SELECT * FROM purchases p2 WHERE p.d = p2.d - Interval '1 Month' AND p.n = p2.n AND p.i = p2.i)",
				1);

		/*
		 * PreparedStatement
		 */
		executePreparedStatementGenerator("select * from purchases where name=?", "Carol K. Durham", 0);
		executePreparedStatementGenerator("select * from purchases where name=?", "Carol K. Durham", 1);

	}

	private static void initDatabase() throws SQLException {
		executeStatementGenerator("CREATE TABLE pruchases (\n" + " id integer PRIMARY KEY,\n" + " name text NOT NULL,\n"
				+ " email text NOT NULL UNIQUE,\n" + " purchasedate date NOT NULL,\n" + " itemid integer NOT NULL,\n"
				+ ");", 0);

		executeStatementGenerator("INSERT INTO purchases (id, name, email, purchasedate, itemid)\n"
				+ "VALUES ('1', 'Tom B. Erichsen', 'erichsen@uni-kiel.de', '2017-11-16', '1' );", 0);

		executeStatementGenerator("INSERT INTO purchases (id, name, email, date, purchasedate, itemid)\n"
				+ "VALUES ('2', 'Carol K. Durham', 'durham@uni-kiel.de', '2017-10-08', '1');", 0);
	}
}
