package br.cinema.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import br.cinema.jpa.CinemaDAOException;
import br.cinema.jpa.FabricaConexao;
import br.cinema.model.Sessao;

public class SessaoPersist implements SessaoDAO {

	private Connection conn = null;

	public SessaoPersist() {
		try {
			this.conn = FabricaConexao.getConnection();
		} catch (CinemaDAOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int save(Sessao sessao) {
		String sql = "INSERT INTO tab_sessao VALUES (DEFAULT, ?, ?, ?, ?, DEFAULT)";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setDate(1, Date.valueOf(sessao.getData()));
			stmt.setTime(2, Time.valueOf(sessao.getHora()));
			stmt.setDouble(3, sessao.getValor());
			stmt.setString(4, sessao.getTipo());

			stmt.execute();
			rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                sessao.setId(rs.getInt(1));
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
		return sessao.getId();
	}

	@Override
	public boolean delete(int id) {
		String sql = "UPDATE tab_sessao SET ativo = 0 WHERE id = ?";

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
	public List<Sessao> getAll() {
		List<Sessao> ativos = new ArrayList<>();
		String sql = "SELECT * FROM tab_sessao WHERE ativo = 1";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ativos.add(new Sessao(rs.getInt(0), rs.getDate(1).toLocalDate(), rs.getTime(2).toLocalTime(), rs.getDouble(3), rs.getString(4)));
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
	public int update(Sessao sessao) {
		String sql = "UPDATE tab_assento SET data = ?, hora = ?, valor = ?, tipo = ? WHERE id = ?";

		PreparedStatement stmt = null;
		try {
			stmt = this.conn.prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(sessao.getData()));
			stmt.setTime(2, Time.valueOf(sessao.getHora()));
			stmt.setDouble(3, sessao.getValor());
			stmt.setString(4, sessao.getTipo());
			stmt.setInt(5, sessao.getId());

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
		return sessao.getId();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
