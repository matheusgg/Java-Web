package br.com.cdi.model.dao;

import br.com.cdi.model.Usuario;

public class UsuarioDAOImpl3 implements UsuarioDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void salva(Usuario usuario) {
		System.out.println("Usuário salvo! IMPL 3");

	}

}
