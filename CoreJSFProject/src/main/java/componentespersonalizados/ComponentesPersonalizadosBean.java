package componentespersonalizados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ComponentesPersonalizadosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7844766982093917184L;

	private List<String> lista;
	private Integer fontSize;
	private String text;

	@PostConstruct
	public void init() {
		this.lista = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			this.lista.add("Item " + (i + 1));
		}

		this.fontSize = 12;
		this.text = "JavaServer Faces 2.2";
	}
	
	public void metodoAjax(AjaxBehaviorEvent event){
		this.getClass();
	}

	public void change(ValueChangeEvent event) {
		event.getClass();
	}

	public String action() {
		return "";
	}

	/**
	 * @return the lista
	 */
	public List<String> getLista() {
		return lista;
	}

	/**
	 * @param lista
	 *            the lista to set
	 */
	public void setLista(List<String> lista) {
		this.lista = lista;
	}

	/**
	 * @return the fontSize
	 */
	public Integer getFontSize() {
		return fontSize;
	}

	/**
	 * @param fontSize
	 *            the fontSize to set
	 */
	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

}
