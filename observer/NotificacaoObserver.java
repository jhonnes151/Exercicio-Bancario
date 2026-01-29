package bancoAvancado.observer;

public class NotificacaoObserver implements BancoObserver{

	@Override
	public void notificar(BancoEvent event) {
		System.out.println("Notificação enviada: " + event.getMensagem());
	}

	
	
}
