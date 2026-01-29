package bancoAvancado.util;

import java.util.logging.*;

public class LogConfig {
	
	public static Logger getLogger() {
		Logger logger = Logger.getLogger("BancoLogger");
		logger.setUseParentHandlers(false);
		
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.ALL);
		logger.addHandler(handler);
		
		logger.setLevel(Level.ALL);
		return logger;
	}
	
}
