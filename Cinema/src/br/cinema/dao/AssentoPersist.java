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
import br.cinema.model.Assento;
import br.cinema.model.Login;

public class AssentoPersist implements AssentoDAO {

	private Connection conn = null;

	public AssentoPersist() {
		try {
			this.conn = FabricaConexao.getConnection();
		} catch (CinemaDAOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int save(Assento assento) {
		String sql = "INSERT INTO tab_assento VALUES (DEFAULT, ?, ?)";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, assento.getTipo());
			stmt.setString(2, assento.getPoltrona());

			stmt.execute();
			rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                assento.setId(rs.getInt(1));
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
		return assento.getId();
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM tab_assento WHERE id = ?";

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
	public List<Assento> getAll() {
		List<Assento> ativos = new ArrayList<>();
		String sql = "SELECT * FROM tab_assento";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ativos.add(new Assento(rs.getInt(0), rs.getString(1), rs.getString(2)));
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
	public int update(Assento assento) {
		String sql = "UPDATE tab_assento SET tipo = ?, poltrona = ? WHERE id = ?";

		PreparedStatement stmt = null;
		try {
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, assento.getTipo());
			stmt.setString(2, assento.getPoltrona());
			stmt.setInt(3, assento.getId());

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
		return assento.getId();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
