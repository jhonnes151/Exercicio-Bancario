package bancoAvancado.observer;

public class AuditoriaObserver implements BancoObserver {

	@Override
	public void notificar(BancoEvent event) {
		System.out.println("Auditoria registrou: " + event.getMensagem());
		
	}
	
}
