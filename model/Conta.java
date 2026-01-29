package bancoAvancado.model;

public class Conta {

	private int id;
	private String titular;
	private double saldo;
	
	public Conta(int id, String titular, double saldo) {
		this.id = id;
		this.titular = titular;
		this.saldo = saldo;
	}
	
	public Conta(String titular, double saldo) {
		this.titular = titular;
		this.saldo = saldo;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitular() {
		return titular;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void depositar(double valor) {
		if(valor <= 0) throw new IllegalArgumentException(" Valor inválido!");
		saldo += valor;
	}
	
	public void sacar(double valor) {
		if(valor <= 0) throw new IllegalArgumentException(" Valor inválido!");
		if(valor > saldo) throw new IllegalArgumentException(" Saldo insuficiente!");
		saldo -= valor;
	}
	
}
