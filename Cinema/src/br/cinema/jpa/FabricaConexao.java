package br.cinema.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FabricaConexao {

	public static Connection getConnection() throws CinemaDAOException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String username = "root";
			String password = "my591 @ND&R 53RV3R";
			return DriverManager.getConnection("jdbc:mysql://localhost/cinema", username, password);
		} catch (Exception ex) {
			throw new CinemaDAOException(ex.getMessage());
		}
	}
	
	public static void closeConnection(Connection conn, PreparedStatement stmt) throws CinemaDAOException {
		close(conn, stmt, null);
	}
	
	public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) throws CinemaDAOException {
		close(conn, stmt, rs);
	}

	private static void close(Connection conn, PreparedStatement stmt, ResultSet rs) throws CinemaDAOException {
		try {
			if (conn != null) {
				conn.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception ex) {
			throw new CinemaDAOException(ex.getMessage());
		}
	}
}
