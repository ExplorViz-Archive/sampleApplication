package sampleApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Logger;

/**
 * JDBC Example Application Class for testing the new DatabaseAspects
 * 
 * @author Christian Zirkelbach (czi@informatik.uni-kiel.de)
 *
 */
public class JDBCExample {

	private static final Logger LOG = Logger.getLogger(JDBCExample.class.getName());
	private static final String DB_BASE_URL = "jdbc:sqlite:";

	// Database credentials
	private static final String USER = "christian";
	private static final String PASS = "test";

	public static Connection connect(String databaseName) {
		Connection connection = null;
		String DB_URL = DB_BASE_URL + databaseName;

		try {
			if (DB_URL.contains("sqlite")) {
				connection = DriverManager.getConnection(DB_URL);
			} else {
				connection = DriverManager.getConnection(DB_URL, USER, PASS);
			}

			// get driver information
			// DatabaseMetaData meta = connection.getMetaData();
			// LOG.info("JDBC driver: " + meta.getDriverName());

		} catch (SQLException e) {
			LOG.warning(e.getMessage());
		}

		return connection;
	}

	public static void disconnect(final Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			LOG.warning(e.getMessage());
		}
	}

	/**
	 * Generator for Statements with different querytypes
	 * 
	 * @param databaseName
	 * @param query
	 */
	public static void executeStatementHandler(final String databaseName, final Querytype qType, final String query,
			final String preparedValue) {

		final Connection conn = connect(databaseName);

		try {
			if (conn != null) {
				switch (qType) {
				case statementExecute:
					final Statement stmtE;
					stmtE = conn.createStatement();
					stmtE.execute(query);
					stmtE.close();
					break;
				case statementExecuteQuery:
					final Statement stmtEQ;
					stmtEQ = conn.createStatement();
					stmtEQ.executeQuery(query);
					stmtEQ.close();
					break;
				case preparedStatementExecute:
					final PreparedStatement pStmtE = conn.prepareStatement(query);
					pStmtE.setString(1, preparedValue);
					pStmtE.execute();
					pStmtE.close();
					break;
				case preparedStatementExecuteQuery:
					final PreparedStatement pStmtEQ = conn.prepareStatement(query);
					pStmtEQ.setString(1, preparedValue);
					pStmtEQ.executeQuery();
					pStmtEQ.close();
					break;
				default:
					break;
				}

				disconnect(conn);

			} else {
				LOG.warning("Failed to connect!");
			}

			conn.close();

		} catch (SQLException e) {
			LOG.warning(e.getMessage());
		}
	}

	public static void main(final String[] argv) {
		runQueries();
	}

	/**
	 * Executes SQL queries for testing purposes
	 */
	private static void runQueries() {
		String databaseName = "test.db";

		LOG.info("Start generating SQL queries...");

		// create queries based on the number of maximum iterations
		int maxIterations = 100;
		for (int i = 0; i < maxIterations; i++) {
			executeStatementHandler(databaseName, Querytype.statementExecute, "CREATE TABLE IF NOT EXISTS `order` "
					+ "(oid integer PRIMARY KEY, name text NOT NULL, email text NOT NULL, odate text NOT NULL, itemid integer NOT NULL);",
					null);

			executeStatementHandler(databaseName, Querytype.statementExecute, "SELECT * FROM `order`;", null);
			executeStatementHandler(databaseName, Querytype.statementExecute,
					"INSERT INTO `order` (oid, name, email, odate, itemid) " + "VALUES('" + getRandomNumber()
							+ "', 'Tom B. Erichsen', 'erichsen@uni-kiel.de', '2017-11-16', '1');",
					null);
			executeStatementHandler(databaseName, Querytype.statementExecute,
					"INSERT INTO `order` (oid, name, email, odate, itemid) " + "VALUES('" + getRandomNumber()
							+ "', 'Tom B. Erichsen', 'erichsen@uni-kiel.de', '2017-11-16', '2');",
					null);
			executeStatementHandler(databaseName, Querytype.statementExecute,
					"INSERT INTO `order` (oid, name, email, odate, itemid) " + "VALUES('" + getRandomNumber()
							+ "', 'Carol K. Durham', 'durham@uni-kiel.de', '2017-10-08', '1');",
					null);
			executeStatementHandler(databaseName, Querytype.statementExecute,
					"INSERT INTO `order` (oid, name, email, odate, itemid) " + "VALUES('" + getRandomNumber()
							+ "', 'Carol K. Durham', 'durham@uni-kiel.de', '2017-10-08', '2');",
					null);
			executeStatementHandler(databaseName, Querytype.statementExecute,
					"SELECT * FROM `order` WHERE name = 'Carol K. Durham';", null);
		}

		LOG.info("Finished generating SQL queries...");
	}

	/**
	 * Returns a random number between low and high
	 * 
	 * @return
	 */
	private static int getRandomNumber() {
		Random r = new Random();
		int Low = 10;
		int High = 100000;
		return r.nextInt(High - Low) + Low;
	}

	public enum Querytype {
		statementExecute, statementExecuteQuery, preparedStatementExecute, preparedStatementExecuteQuery
	}

}
