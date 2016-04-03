package br.com.maestroautomacoes.portal.model.dao.usuario;

import br.com.maestroautomacoes.portal.model.usuario.Usuario;

public interface UsuarioDao {
	void cadastar(Usuario usuario);

	void alterar(Usuario usuario);

	void deletar(Usuario usuario);

	Usuario recuperaUsuario(int codigoUsuario);

	Usuario recuperaUsuario(String email, String senha);

	Usuario recuperaUsuario(String email);
}
