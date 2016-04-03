package converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AppConverterTestMB implements Serializable {

	private static final long serialVersionUID = 4821976405146888349L;

	private List<Usuario> usuarios;
	private Usuario usuarioSelecionado;

	@PostConstruct
	public void init() {
		this.usuarios = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			this.usuarios.add(new Usuario(i + 1, "Usuario " + (i + 1)));
		}
	}

	/**
	 * @return the usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios
	 *            the usuarios to set
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * @return the usuarioSelecionado
	 */
	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	/**
	 * @param usuarioSelecionado
	 *            the usuarioSelecionado to set
	 */
	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}
