package email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Autenticador extends Authenticator {
	private String usuario;
	private String senha;
	
	public Autenticador(String usuario, String senha){
		this.usuario = usuario;
		this.senha = senha;
	}

	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(usuario, senha);
	}
}
