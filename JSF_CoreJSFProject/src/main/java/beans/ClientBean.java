package beans;

import java.io.Serializable;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class ClientBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2295422945986092939L;
	private Object bean;

	/**
	 * Recupera o bean informado com o escopo personalizado. O JSF pergunta para
	 * todos os seus EL Resolvers (inclusive o customizado) para verificar se
	 * algum conhece o bean solicitado.
	 */
	public void searchBean() {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		ELResolver elResolver = FacesContext.getCurrentInstance().getApplication().getELResolver();
		this.bean = elResolver.getValue(context, null, "customBean");
	}

	/**
	 * @return the bean
	 */
	public Object getBean() {
		return bean;
	}

	/**
	 * @param bean
	 *            the bean to set
	 */
	public void setBean(Object bean) {
		this.bean = bean;
	}

}
