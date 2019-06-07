package br.cinema.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.cinema.jpa.CinemaDAOException;
import br.cinema.jpa.FabricaConexao;
import br.cinema.model.Endereco;
import br.cinema.model.Login;
import br.cinema.model.Pessoa;

public class PessoaPersist implements PessoaDAO {

	private Connection conn = null;

	public PessoaPersist() {
		try {
			this.conn = FabricaConexao.getConnection();
		} catch (CinemaDAOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int save(Pessoa pessoa, Endereco endereco, Login login) {
		if (login != null) {
			LoginPersist loginPersist = new LoginPersist();
			login.setId(loginPersist.save(login));
		}
		EnderecoPersist enderecoPersist = new EnderecoPersist();
		endereco.setId(enderecoPersist.save(endereco));
		
		String sql = "INSERT INTO tab_pessoa VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Integer id = 0;
		try {
			stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getCpf());
			stmt.setString(3, pessoa.getEmail());
			stmt.setString(4, pessoa.getFone());
			stmt.setDate(5, Date.valueOf(pessoa.getDataNascimento()));
			stmt.setInt(6, endereco.getId());
			stmt.setInt(7, login.getId());

			stmt.execute();
			
			rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
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
		return id;
	}

	@Override
	public boolean delete(int id) {
		String sql = "UPDATE tab_pessoa SET ativo = 0 WHERE id = ?";

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
	public List<Pessoa> getAll() {
		List<Pessoa> ativos = new ArrayList<>();
		String sql = "SELECT * FROM tab_pessoa WHERE ativo = 1";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Endereco endereco = null;
				Login login = null;
				sql = "SELECT * FROM tab_endereco WHERE id = ?";
				stmt = this.conn.prepareStatement(sql);
				stmt.setInt(1, rs.getInt("id_endereco"));
				try (ResultSet rs2 = stmt.executeQuery()) {
					endereco = new Endereco(rs2.getInt(1), rs2.getString(2), 
							rs2.getString(3), rs2.getString(4), 
							rs2.getString(5), rs2.getString(6), 
							rs2.getString(7), rs2.getString(8));
				}
				if (rs.getInt("id_login") != 0) {
					sql = "SELECT * FROM tab_login WHERE id = ?";
					stmt = this.conn.prepareStatement(sql);
					stmt.setInt(1, rs.getInt("id_login"));
					try (ResultSet rs3 = stmt.executeQuery()) {
						login = new Login(rs3.getInt(1), rs3.getString(2), rs3.getString(3), rs3.getString(4));
					}
				}
				ativos.add(new Pessoa(rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getDate(6).toLocalDate(), endereco, login));
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
		return ativos;
	}

	@Override
	public int update(Pessoa pessoa, Endereco endereco, Login login) {
		if (login != null) {
			LoginPersist loginPersist = new LoginPersist();
			if (login.getSenha() == null) {
				if (login.getId() != 0) {
					loginPersist.delete(login.getId());
				}
			} else {
				loginPersist.update(login);
			}
		}
		EnderecoPersist enderecoPersist = new EnderecoPersist();
		enderecoPersist.update(endereco);
		
		String sql = "UPDATE tab_pessoa SET nome = ?, cpf = ?, email = ?, fone = ?, data_nascimento = ? WHERE id = ?";

		PreparedStatement stmt = null;
		try {
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getCpf());
			stmt.setString(3, pessoa.getEmail());
			stmt.setString(4, pessoa.getFone());
			stmt.setDate(5, Date.valueOf(pessoa.getDataNascimento()));
			stmt.setInt(6, pessoa.getId());

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
		return pessoa.getId();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
