package datetimeformatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateTimeFormatterExample {

	public static void main(String[] args) {
		DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendLiteral("Ano atual: ").appendValue(ChronoField.YEAR).appendLiteral("\nMês atual: ")
				.appendText(ChronoField.MONTH_OF_YEAR).appendLiteral("\nDia atual: ").appendValue(ChronoField.DAY_OF_MONTH).toFormatter();

		LocalDateTime now = LocalDateTime.now();
		System.out.println(now.format(formatter));
	}
}
