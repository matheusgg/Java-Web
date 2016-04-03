package email;

import email.externo.EmailExterno;

public class Principal {
	public static void main(String[] args) {
		/*EmailInterno email = new EmailInterno();
		email.setDe("adm@localhost");
		email.setPara("testuser@localhost");
		email.setAssunto("Teste");
		email.setAnexo("C:\\Users\\Matheus\\Documents\\Trabalhos\\Apostilas\\Web\\Links.txt");
		email.setMensagem("Mensagem de Teste");		
		email.enviaEmail();*/
		
		EmailExterno email2 = new EmailExterno();
		email2.setDe("matheus.ggoes@yahoo.com.br");
		email2.setPara("matheus.ggoes@yahoo.com.br");
		email2.setAssunto("Teste");
		email2.setMensagem("Mensagem de Teste");		
		email2.enviaEmail();
	}

}
