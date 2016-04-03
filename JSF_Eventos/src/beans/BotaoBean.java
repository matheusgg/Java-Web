package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class BotaoBean {

	public void sorteiaBotao(ActionEvent event) {
		UIComponent form = event.getComponent().getParent();

		UICommand botao1 = (UICommand) form.findComponent("botao1");
		UICommand botao2 = (UICommand) form.findComponent("botao2");
		UICommand botao3 = (UICommand) form.findComponent("botao3");

		botao1.getAttributes().put("disabled", true);
		botao2.getAttributes().put("disabled", true);
		botao3.getAttributes().put("disabled", true);

		double num = Math.random();
		if (num < 1.0 / 3.0) {
			botao1.getAttributes().put("disabled", false);
		} else if (num < 2.0 / 3.0) {
			botao2.getAttributes().put("disabled", false);
		} else {
			botao3.getAttributes().put("disabled", false);
		}
	}

}
