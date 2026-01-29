package bancoAvancado.observer;

public interface BancoObserver {
	void notificar(BancoEvent event);
}
