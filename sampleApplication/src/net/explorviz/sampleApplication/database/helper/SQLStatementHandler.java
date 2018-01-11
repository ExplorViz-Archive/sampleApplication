package net.explorviz.sampleApplication.database.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * Handler for creating SQL statements based on the type of query
 * @author Christian Zirkelbach (czi@informatik.uni-kiel.de)
 *
 */
public class SQLStatementHandler {

	static Logger LOG = Logger.getLogger("global");
	
	public enum Querytype {
		statementExecute, statementExecuteQuery, preparedStatementExecute, preparedStatementExecuteQuery
	}

	/**
	 * Generator for Statements with different querytypes
	 * 
	 * @param databaseName
	 * @param query
	 */
	public static void executeStatementHandler(final String databaseName, final SQLStatementHandler.Querytype qType,
			final String query, final String preparedValue) {

		final Connection conn = SQLConnectionHandler.connect(databaseName);

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

				SQLConnectionHandler.disconnect(conn);

			} else {
				LOG.warning("Failed to connect!");
			}

			conn.close();

		} catch (SQLException e) {
			LOG.warning(e.getMessage());
		}
	}

}
