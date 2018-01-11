package net.explorviz.sampleApplication.database.helper;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.explorviz.sampleApplication.database.JDBCExample;

/**
 * Handles SQL connections and basic tasks
 * 
 * @author Christian Zirkelbach (czi@informatik.uni-kiel.de)
 *
 */
public class SQLConnectionHandler {

	/**
	 * Established connection to a database
	 * 
	 * @param databaseName
	 * @return
	 */
	public static Connection connect(String databaseName) {
		Connection connection = null;
		String DB_URL = JDBCExample.DB_BASE_URL + databaseName;

		try {
			connection = DriverManager.getConnection(DB_URL);
		} catch (SQLException e) {
			JDBCExample.LOG.warning(e.getMessage());
		}
		return connection;
	}

	/**
	 * Terminates a connection to a database
	 * 
	 * @param conn
	 */
	public static void disconnect(final Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			JDBCExample.LOG.warning(e.getMessage());
		}
	}

	/**
	 * Creates a new database
	 */
	public static void createDatabase(final String databaseName) {
		String url = JDBCExample.DB_BASE_URL + databaseName;

		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				JDBCExample.LOG.info("The driver name is " + meta.getDriverName());
				JDBCExample.LOG.info("A new database " + databaseName + " has been created.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
