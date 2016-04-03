package reverse;

public class Reverse {

	public static void main(String[] args) {
		String reverse = "";
		String nome = "Isto é um teste";
		int length = nome.length();

		for (int i = length; i > 0; i--) {
			reverse += nome.substring(i - 1, i);
		}

		System.out.println(nome);
		System.out.println(reverse);
	}
}
