
public class MeuBean {
	private String user = "";
	private String pass = "";
	private String valorDoDia;
	private String mensagem = "";
	
	public String getValorDoDia() {
		return valorDoDia;
	}
	public void setValorDoDia(String valorDoDia) {
		this.valorDoDia = valorDoDia;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String verificaCliente(){
		this.mensagem = "";
		this.valorDoDia = "";
		if(this.user.equalsIgnoreCase("matheus") && this.pass.equalsIgnoreCase("12345")){
			this.valorDoDia = String.format("R$ %.2f", (Math.random()*10));			
		}else{
			this.mensagem = "Usuário ou senha inválida!";
		}
		System.out.println(mensagem);
		System.out.println(valorDoDia);
		return null;		
	}
}
