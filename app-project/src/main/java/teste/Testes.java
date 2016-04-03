package teste;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Base64.Encoder;

public class Testes {

	public static void main(String[] args) throws Exception {
		try (FileInputStream is = new FileInputStream(new File("C:\\Users\\mggoes\\Downloads\\ipem-logo.png"))) {
			byte[] bytes = new byte[is.available()];
			is.read(bytes);

			Encoder encoder = Base64.getEncoder();
			String encoded = encoder.encodeToString(bytes);

			System.out.println(encoded);
		}
	}
}
