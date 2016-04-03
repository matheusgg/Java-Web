package br.com.cdi.model.dao;

import br.com.cdi.model.Usuario;

public class UsuarioDAOImpl2 implements UsuarioDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7354437316575764717L;

	@Override
	public void salva(Usuario usuario) {
		System.out.println("Usuário salvo! IMPL 2");

	}

}
