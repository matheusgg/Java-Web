package br.prime.model;

public class Usuario {
	private String nome;
	private String email;
	private int tipoComment;
	private String recebeAnuncio;
	private String mensagem;
	private String user;
	private String password;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTipoComment() {
		return tipoComment;
	}
	public void setTipoComment(int tipoComment) {
		this.tipoComment = tipoComment;
	}
	public String getRecebeAnuncio() {
		return recebeAnuncio;
	}
	public void setRecebeAnuncio(String recebeAnuncio) {
		this.recebeAnuncio = recebeAnuncio;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
