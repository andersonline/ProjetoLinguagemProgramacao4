package br.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.cinema.jpa.CinemaDAOException;
import br.cinema.jpa.FabricaConexao;
import br.cinema.model.Login;

public class LoginPersist implements LoginDAO {

	private Connection conn = null;

	public LoginPersist() {
		try {
			this.conn = FabricaConexao.getConnection();
		} catch (CinemaDAOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int save(Login login) {
		String sql = "INSERT INTO tab_login VALUES (DEFAULT, ?, ?, ?, DEFAULT)";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, login.getEmail());
			stmt.setString(2, login.getSenha());
			stmt.setString(3, login.getTipo());

			stmt.execute();
			rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                login.setId(rs.getInt(1));
            }
            stmt.clearParameters();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				FabricaConexao.closeConnection(this.conn, stmt, rs);
			} catch (CinemaDAOException e) {
				e.printStackTrace();
			}
		}
		return login.getId();
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM tab_login WHERE id = ?";

		PreparedStatement stmt = null;
		try {
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, id);

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				FabricaConexao.closeConnection(this.conn, stmt);
			} catch (CinemaDAOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<Login> getAll() {
		List<Login> ativos = new ArrayList<>();
		String sql = "SELECT * FROM tab_login";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ativos.add(new Login(rs.getInt(0), rs.getString(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				FabricaConexao.closeConnection(this.conn, stmt, rs);
			} catch (CinemaDAOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int update(Login login) {
		String sql = "UPDATE tab_login SET email = ?, senha = ?, tipo = ? WHERE id = ?";

		PreparedStatement stmt = null;
		try {
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, login.getEmail());
			stmt.setString(2, login.getSenha());
			stmt.setString(3, login.getTipo());
			stmt.setInt(4, login.getId());

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				FabricaConexao.closeConnection(this.conn, stmt);
			} catch (CinemaDAOException e) {
				e.printStackTrace();
			}
		}
		return login.getId();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
