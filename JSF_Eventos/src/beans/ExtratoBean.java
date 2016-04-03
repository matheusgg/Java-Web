package beans;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean
@SessionScoped
public class ExtratoBean {
	private Date dataInicial;
	private Date dataFinal;

	public void validaDatas(ComponentSystemEvent event) {
		UIComponent source = event.getComponent();
		UIInput dataInicialInput = (UIInput) source.findComponent("dataInicial");
		UIInput dataFinalInput = (UIInput) source.findComponent("dataFinal");

		if (dataInicialInput.isValid() && dataFinalInput.isValid()) {
			Date dataInicial = (Date) dataInicialInput.getLocalValue();
			Date dataFinal = (Date) dataFinalInput.getLocalValue();

			if (dataFinal.before(dataInicial)) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"A data final deve ser maior do que a data inicial!", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				FacesContext.getCurrentInstance().renderResponse();
			}
		}

	}

	public void geraExtrato() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Extrato gerado com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * @return the dataInicial
	 */
	public Date getDataInicial() {
		return dataInicial;
	}

	/**
	 * @param dataInicial
	 *            the dataInicial to set
	 */
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	/**
	 * @return the dataFinal
	 */
	public Date getDataFinal() {
		return dataFinal;
	}

	/**
	 * @param dataFinal
	 *            the dataFinal to set
	 */
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

}
