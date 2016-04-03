package security.model;

import org.springframework.security.core.GrantedAuthority;

public class Permissao implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1142707401955279587L;

	private String nome;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		return this.nome;
	}

}
