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
import br.cinema.model.Endereco;
import br.cinema.model.Filme;
import br.cinema.model.Login;

public class EnderecoPersist implements EnderecoDAO {

	private Connection conn = null;

	public EnderecoPersist() {
		try {
			this.conn = FabricaConexao.getConnection();
		} catch (CinemaDAOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int save(Endereco endereco) {
		String sql = "INSERT INTO tab_endereco VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, endereco.getLogradouro());
			stmt.setString(2, endereco.getNumero());
			stmt.setString(3, endereco.getBairro());
			stmt.setString(4, endereco.getCep());
			stmt.setString(5, endereco.getCidade());
			stmt.setString(6, endereco.getEstado());
			stmt.setString(7, endereco.getComplemento());

			stmt.execute();
			
			rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                endereco.setId(rs.getInt(1));
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
		return endereco.getId();
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM tab_endereco WHERE id = ?";

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
	public List<Endereco> getAll() {
		List<Endereco> ativos = new ArrayList<>();
		String sql = "SELECT * FROM tab_endereco";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ativos.add(new Endereco(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
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
	public int update(Endereco endereco) {
		String sql = "UPDATE tab_endereco SET logradouro = ?, numero = ?, bairro = ?, cep = ?, cidade = ?, estado = ?, complemento = ? WHERE id = ?";

		PreparedStatement stmt = null;
		try {
			stmt = this.conn.prepareStatement(sql);
			
			stmt.setString(1, endereco.getLogradouro());
			stmt.setString(2, endereco.getNumero());
			stmt.setString(3, endereco.getBairro());
			stmt.setString(4, endereco.getCep());
			stmt.setString(5, endereco.getCidade());
			stmt.setString(6, endereco.getEstado());
			stmt.setString(7, endereco.getComplemento());
			stmt.setInt(8, endereco.getId());

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
		return endereco.getId();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
