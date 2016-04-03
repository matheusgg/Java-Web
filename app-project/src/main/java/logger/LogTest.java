package logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LogTest {

	public static void main(String[] args) {
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new CustomFormatter());

		LogManager.getLogManager();
		Logger logger = Logger.getLogger(LogTest.class.getSimpleName());
		logger.addHandler(handler);
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.FINE);

		logger.info("Mensagem de Teste");

	}

}
