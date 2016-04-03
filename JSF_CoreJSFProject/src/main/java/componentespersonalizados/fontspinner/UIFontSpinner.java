package componentespersonalizados.fontspinner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.component.UICommand;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;

/**
 * Para promover suporte a tag f:ajax, e necessario implementar a interface
 * ClientBehaviorHolder. Em ultima instancia, nosso componente herda de
 * UIComponentBase, este por sua vez, ja possui implementacao padrao para todos
 * os metodos de ClientBehaviorHolder, apesar de nao implementar esta interface.
 * 
 * @author Matheus
 * 
 */
@FacesComponent("ui.fontSpinner")
public class UIFontSpinner extends UICommand implements ClientBehaviorHolder {

	private Integer fontSize;
	private String update;

	// Eventos ajax suportados
	private List<String> eventNames;

	public UIFontSpinner() {
		super.setRendererType("ui.fontSpinner");
		this.eventNames = Arrays.asList("click");
	}

	/**
	 * Informamos ao JSF qual e o evento ajax padrao.
	 */
	@Override
	public String getDefaultEventName() {
		return "click";
	}

	/**
	 * Informamos aos JSF quais eventos ajax o componente suporta.
	 */
	@Override
	public Collection<String> getEventNames() {
		return this.eventNames;
	}

	/**
	 * @return the fontSize
	 */
	public Integer getFontSize() {
		this.fontSize = (Integer) this.getValueExpression("fontSize").getValue(FacesContext.getCurrentInstance().getELContext());
		return fontSize;
	}

	/**
	 * @param fontSize
	 *            the fontSize to set
	 */
	public void setFontSize(Integer fontSize) {
		this.getValueExpression("fontSize").setValue(FacesContext.getCurrentInstance().getELContext(), fontSize);
		this.fontSize = fontSize;
	}

	/**
	 * @return the update
	 */
	public String getUpdate() {
		return update;
	}

	/**
	 * @param update
	 *            the update to set
	 */
	public void setUpdate(String update) {
		this.update = update;
	}

}
