package br.com.maestroautomacoes.portal.logger;

import org.apache.logging.log4j.Logger;

public abstract class LogManager {
	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger("loggerFile");

	/**
	 * Monta a mensagem do rastro da pilha para o log.
	 * 
	 * @param exception
	 * @return
	 */
	public static String makeStackTraceLog(Exception exception) {
		StringBuilder stringBuilder = new StringBuilder();
		for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
			stringBuilder.append(stackTraceElement + "\r\n");
		}
		return stringBuilder.toString();
	}

	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}

}
