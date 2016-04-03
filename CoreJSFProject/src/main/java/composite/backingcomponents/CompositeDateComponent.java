package composite.backingcomponents;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;

/**
 * Um backing component que auxilia um composite component deve implementar a
 * interface NamingContainer e pertencer a familia "javax.faces.NamingContainer"
 * 
 * @author Matheus
 * 
 */
@FacesComponent("compositeDateComponent")
public class CompositeDateComponent extends UIInput implements NamingContainer {

	/**
	 * Este metodo e chamado pelo JSF a cada requisicao para manipulacao da tag
	 * e escrita da mesma na pagina.
	 */
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		Date data = (Date) this.getValue();

		if (data == null) {
			data = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);

		HtmlSelectOneMenu dia = (HtmlSelectOneMenu) this.findComponent("dia");
		HtmlSelectOneMenu mes = (HtmlSelectOneMenu) this.findComponent("mes");
		HtmlSelectOneMenu ano = (HtmlSelectOneMenu) this.findComponent("ano");

		dia.setValue(cal.get(Calendar.DAY_OF_MONTH));
		mes.setValue(cal.get(Calendar.MONTH) + 1);
		ano.setValue(cal.get(Calendar.YEAR));

		super.encodeBegin(context);
	}

	/**
	 * Metodo executado quando uma nova requisicao e enviada ao servidor
	 * 
	 * @param context
	 */
	@Override
	public void decode(FacesContext context) {
		this.setValue(this.getUserSubmmitedValue());
	}

	private Date getUserSubmmitedValue() {
		HtmlSelectOneMenu dia = (HtmlSelectOneMenu) this.findComponent("dia");
		HtmlSelectOneMenu mes = (HtmlSelectOneMenu) this.findComponent("mes");
		HtmlSelectOneMenu ano = (HtmlSelectOneMenu) this.findComponent("ano");

		if (dia.getSubmittedValue() == null) {
			return null;
		} else {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dia.getSubmittedValue().toString()));
			cal.set(Calendar.MONTH, Integer.valueOf(mes.getSubmittedValue().toString()) - 1);
			cal.set(Calendar.YEAR, Integer.valueOf(ano.getSubmittedValue().toString()));
			return cal.getTime();
		}
	}

	@Override
	public String getFamily() {
		return "javax.faces.NamingContainer";
	}

}
