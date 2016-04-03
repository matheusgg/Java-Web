package br.com.cdi.model.dao;

import java.io.Serializable;

import br.com.cdi.model.Usuario;

public interface UsuarioDAO extends Serializable {

	void salva(Usuario usuario);

}
