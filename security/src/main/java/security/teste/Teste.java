package security.teste;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

public class Teste {

	/**
	 * Salt usado para criptografia (** Obs: Não deve ser alterada)
	 */
	private static final byte[] saltCriptografia = { 105, 80, 101, 109, 83, 97, 108, 116, 83, 72, 65, 95, 50, 54, 48, 57 };

	public static void main(String[] args) {
		System.out.println(Teste.encode("teste"));
	}

	/**
	 * Faz a criptografia da string passada usando Algoritimo SHA-256
	 * 
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("SHA-256");
		return encoder.encodePassword(str, new String(saltCriptografia));
	}
}
