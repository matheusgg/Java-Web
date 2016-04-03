package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
public class AppBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8382193735020347809L;
	private String texto;
	private String senha;
	private Part file;
	private String template;

	public AppBean() {
		this.texto = "Java Server Faces 2.2";
		this.template = "black";
	}

	public String changeTheme(String theme) {
		this.template = theme;
		return "";
	}

	public String actionMethod(ActionEvent event) {
		event.getComponent().getAttributes().put("style", "color: red");
		return "";
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto
	 *            the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * @return the file
	 */
	public Part getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(Part file) {
		this.file = file;
	}

	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template
	 *            the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 *            the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
