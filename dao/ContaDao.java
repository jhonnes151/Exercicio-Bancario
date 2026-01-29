package bancoAvancado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bancoAvancado.model.Conta;
import bancoAvancado.util.ConnectionFactory;

public class ContaDao {

	public List<Conta> listar() throws Exception {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM conta");
		ResultSet rs = ps.executeQuery();
		
		List<Conta> contas = new ArrayList<>();
		
		while(rs.next()) {
			contas.add(new Conta(
					rs.getInt("id"),
					rs.getString("titular"),
					rs.getDouble("Saldo")
					));
		}
		
		rs.close();
		ps.close();
		conn.close();
		
		return contas;
	}
	
	public Conta buscarPorId(int id) throws Exception {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps =
				conn.prepareStatement("SELECT * FROM conta WHERE id=?");
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			Conta c = new Conta(
					rs.getInt("id"),
					rs.getString("titular"),
					rs.getDouble("saldo")
					);
			rs.close();
			ps.close();
			conn.close();
			return c;
		}
		
		rs.close();
		ps.close();
		conn.close();
		return null;
	}
	
	public void atualizarSaldo(Connection conn, Conta conta) throws Exception {
		PreparedStatement ps =
				conn.prepareStatement("UPDATE conta SET saldo=? WHERE id=?");
		ps.setDouble(1, conta.getSaldo());
		ps.setInt(2, conta.getId());
		ps.executeUpdate();
		ps.close();
	}
}
