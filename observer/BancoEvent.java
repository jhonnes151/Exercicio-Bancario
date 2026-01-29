package bancoAvancado.observer;

public class BancoEvent {

	private final String mensagem;
	
	public BancoEvent(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
}
