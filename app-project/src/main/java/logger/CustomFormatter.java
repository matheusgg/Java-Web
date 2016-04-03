package logger;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		StringBuilder message = new StringBuilder();
		message.append("%tT ");
		message.append("%s ");
		message.append("[").append("%s").append("] ");
		message.append("(").append("%s").append("): ");
		message.append("%s");

		return String.format(message.toString(), new Date(), record.getLevel().getName(), record.getSourceClassName(), record.getSourceMethodName(),
				record.getMessage());
	}

}
