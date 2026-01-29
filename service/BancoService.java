package bancoAvancado.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import bancoAvancado.dao.ContaDao;
import bancoAvancado.model.Conta;
import bancoAvancado.observer.AuditoriaObserver;
import bancoAvancado.observer.BancoEvent;
import bancoAvancado.observer.BancoObserver;
import bancoAvancado.observer.LoggerObserver;
import bancoAvancado.observer.NotificacaoObserver;
import bancoAvancado.util.ConnectionFactory;

public class BancoService {

	private final ContaDao dao = new ContaDao();
	private final List<BancoObserver> observers = new ArrayList<>();
	
	public BancoService() {
		observers.add(new LoggerObserver());
		observers.add(new NotificacaoObserver());
		observers.add(new AuditoriaObserver());
	}
	
	private void dispararEvento(String mensagem) {
		BancoEvent event = new BancoEvent(mensagem);
		for (BancoObserver o : observers) {
			o.notificar(event);
		}
	}
	
	//---- TRANFERÊNCIA----
	public void transferir(int origemId, int destinoId, double valor) throws Exception {
		
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			conn.setAutoCommit(false);
			
			Conta origem = dao.buscarPorId(origemId);
			Conta destino = dao.buscarPorId(destinoId);
			
			if(origem == null|| destino == null)
				throw new IllegalArgumentException("Conta não encontrada");
			
			origem.sacar(valor);
			destino.depositar(valor);
			
			dao.atualizarSaldo(conn, origem);
			dao.atualizarSaldo(conn, destino);
			
			conn.commit();
			
			dispararEvento(
					"Transferência de R$" + valor +
					" da conta " + origemId +
					" para conta " + destinoId
					);
		} catch(Exception e) {
			conn.rollback();
			dispararEvento(" ERRO: " + e.getMessage());
			throw e;
		} finally {
			conn.close();
		}
	}
	
	//---- DEPÓSITO ----
	public void depositar(int contaId, double valor) throws Exception {
		
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			conn.setAutoCommit(false);
			
			Conta conta = dao.buscarPorId(contaId);
			if(conta == null) 
				throw new IllegalArgumentException("Conta não encontrada");

			conta.depositar(valor);
			dao.atualizarSaldo(conn, conta);
			
			conn.commit();
			
			dispararEvento("Depósito de R$" + valor + " na conta " + contaId);
			
		} catch(Exception e) {
			conn.rollback();
			dispararEvento("Erro no depósito: " + e.getMessage());
			throw e;
		} finally {
			conn.close();
		}
	}
	
	// ---- SAQUE ----
	public void sacar(int contaId, double valor) throws Exception {
		
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			conn.setAutoCommit(false);
			
			Conta conta = dao.buscarPorId(contaId);
			if(conta == null)
				throw new IllegalArgumentException("Conta não encontrada");
			
			conta.sacar(valor);
			
			dao.atualizarSaldo(conn, conta);
			
			conn.commit();
			
			dispararEvento("Saque de R$" + valor + " na conta " + contaId);
			
		} catch(Exception e) {
			conn.rollback();
			dispararEvento("ERRO no saque: " + e.getMessage());
			throw e;
		} finally {
			conn.close();
		}
	}
	
	// ---- CONSULTAR SALDO ----
	public double consultarSaldo(int contaId) throws Exception {
		Conta conta = dao.buscarPorId(contaId);
		
		if(conta == null)
			throw new IllegalArgumentException("Conta não encontrada");
		
		dispararEvento("Consulta de saldo da conta" + contaId);
		return conta.getSaldo();
	}
	
}
