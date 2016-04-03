package ordenacaoversao;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class OrdenacaoVersao {

	public static void main(String[] args) {
		List<String> versions = Arrays.asList("1.1.4", "1.0", "1.1", "1", "2.1", "1.1", "2", "2.3", "2.1.1", "1.2", "2.2", "2.1", "1.1.3");
		versions.sort(new VersionComparator());
		versions.forEach(System.out::println);
	}
}

class VersionComparator implements Comparator<String> {

	private static final String DEFAULT_SEPARATOR_REGEX = "\\.";
	private static final int ZERO = 0;
	private static final int ONE = 1;

	@Override
	public int compare(String o1, String o2) {
		if (o1 == null) {
			return -ONE;
		} else if (o2 == null) {
			return ONE;
		}

		String[] arrayA = o1.split(DEFAULT_SEPARATOR_REGEX);
		String[] arrayB = o2.split(DEFAULT_SEPARATOR_REGEX);

		int arrayALength = arrayA.length;
		int arrayBLength = arrayB.length;

		int maxSize = (arrayALength < arrayBLength) ? arrayALength : arrayBLength;

		int valueA = ZERO;
		int valueB = ZERO;
		int comparationValue = ZERO;

		for (int i = ZERO; i < maxSize; i++) {
			valueA = this.extractIntValue(arrayA[i]);
			valueB = this.extractIntValue(arrayB[i]);

			comparationValue = Integer.compare(valueA, valueB);

			if (comparationValue != ZERO) {
				return comparationValue;
			}
		}

		if (comparationValue == ZERO && arrayALength < arrayBLength) {
			return -ONE;
		}

		return ZERO;
	}

	private int extractIntValue(String valueText) {
		try {
			return Integer.valueOf(valueText);
		} catch (NumberFormatException e) {
			return ZERO;
		}
	}

}