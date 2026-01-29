package bancoAvancado.observer;

import java.util.logging.Logger;

public class LoggerObserver implements BancoObserver{

	private final Logger logger = Logger.getLogger("BancoLogger");

	@Override
	public void notificar(BancoEvent event) {

		logger.info("LOG => " + event.getMensagem());
		
	}
}
