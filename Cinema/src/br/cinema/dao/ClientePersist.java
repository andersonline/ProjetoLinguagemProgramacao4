//package br.cinema.dao;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import br.cinema.jpa.CinemaDAOException;
//import br.cinema.jpa.FabricaConexao;
//import br.cinema.model.Cliente;
//
//public class ClientePersist implements ClienteDAO {
//
//	private Connection conn = null;
//
//	public ClientePersist() {
//		try {
//			this.conn = FabricaConexao.getConnection();
//		} catch (CinemaDAOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public int save(Cliente cliente) {
//		PessoaPersist pessoaPersist = new PessoaPersist();
//		cliente.setId(pessoaPersist.save(cliente, cliente.getEndereco(), cliente.getLogin()));
//		String sql = "INSERT INTO tab_cliente VALUES (DEFAULT, ?, ?, ?, ?)";
//
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		try {
//			stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			stmt.setString(1, cliente.getTipoCliente());
//			stmt.setBoolean(2, cliente.isEstudante());
//			stmt.setDate(3, Date.valueOf(cliente.getValidade()));
//			stmt.setInt(4, cliente.getId());
//
//			stmt.execute();
//			rs = stmt.getGeneratedKeys();
//            if (rs.next()) {
//                cliente.setId(rs.getInt(1));
//            }
//            stmt.clearParameters();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				FabricaConexao.closeConnection(this.conn, stmt, rs);
//			} catch (CinemaDAOException e) {
//				e.printStackTrace();
//			}
//		}
//		return cliente.getId();
//	}
//
//	@Override
//	public boolean delete(int id) {
//		String sql = "DELETE FROM tab_cliente WHERE id = ?";
//
//		PreparedStatement stmt = null;
//		try {
//			stmt = this.conn.prepareStatement(sql);
//			stmt.setInt(1, id);
//
//			stmt.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				FabricaConexao.closeConnection(this.conn, stmt);
//			} catch (CinemaDAOException e) {
//				e.printStackTrace();
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public List<Cliente> getAll() {
//		List<Cliente> ativos = new ArrayList<>();
//		String sql = "SELECT * FROM tab_cliente";
//
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		try {
//			stmt = this.conn.prepareStatement(sql);
//			rs = stmt.executeQuery();
//			while (rs.next()) {
////				new Cliente(id, nome, cpf, email, fone, dataNascimento, endereco, tipoCliente, estudante, validade)
////				ativos.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDate(6).toLocalDate(), rs.getInt(7), rs.getInt(8), ));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				FabricaConexao.closeConnection(this.conn, stmt, rs);
//			} catch (CinemaDAOException e) {
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public int update(Cliente cliente) {
//		PessoaPersist pessoaPersist = new PessoaPersist();
//		pessoaPersist.update(cliente, cliente.getEndereco(), cliente.getLogin());
//		String sql = "UPDATE tab_cliente SET tipo = ?, estudante = ?, validade = ? WHERE id = ?";
//
//		PreparedStatement stmt = null;
//		try {
//			stmt = this.conn.prepareStatement(sql);
//			stmt.setString(1, cliente.getTipoCliente());
//			stmt.setBoolean(2, cliente.isEstudante());
//			stmt.setDate(3, Date.valueOf(cliente.getValidade()));
//			stmt.setInt(4, cliente.getId());
//
//			stmt.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				FabricaConexao.closeConnection(this.conn, stmt);
//			} catch (CinemaDAOException e) {
//				e.printStackTrace();
//			}
//		}
//		return cliente.getId();
//	}
//
//	public Connection getConn() {
//		return conn;
//	}
//
//	public void setConn(Connection conn) {
//		this.conn = conn;
//	}
//}
