package componentespersonalizados.spinner;

import javax.el.MethodExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.convert.IntegerConverter;
import javax.faces.event.FacesListener;

/**
 * Esta classe serve de suporte para o componente Spinner. Ela executa as
 * tarefas de codificacao e decodificacao da tag, pois nao possui um
 * renderizador externo.
 * 
 * @author Matheus
 * 
 */
@FacesComponent("ui.spinner")
public class UISpinner extends UIInput {

	/**
	 * O JSF possui um handler de tag responsavel pela avaliacao dos atributos
	 * de um componente. Primeiro o handler verifica se e um caso especial, ou
	 * seja, se o atributo e um value, valueChangeListener, action,
	 * actionListener, converter ou validator, caso nao seja nenhuma destas
	 * opcoes, o handler verifica se o componente possui metodos getter e
	 * setters para os atributos especificados. Caso positivo, invoca estes
	 * metodos passando os valores convertidos. Se o componente nao possuir os
	 * metodos getters e setters, o handler de tag insere os atributos
	 * informados no mapa de atributos do componente.
	 **/
	private String size;
	private Integer max;
	private Integer min;
	private MethodExpression actionMethod;

	/**
	 * Definindo o conversor e o renderizador externo do componente
	 */
	public UISpinner() {
		super.setConverter(new IntegerConverter());
		super.setRendererType("ui.spinner");
	}

	@Override
	public void addFacesListener(FacesListener listener) {
		super.addFacesListener(listener);
	}

	/**
	 * Recuperando o atributo salvo atraves do objeto stateHelper auxiliar.
	 */
	public String getSize() {
		String size = (String) super.getStateHelper().get("size");
		if (size == null) {
			return this.size;
		}
		return size;
	}

	/**
	 * O JSF 2 possui um mecanismo aprimorado para salvemento parcial de estados
	 * dos componentes. Este mecanismo e implementado atravez do StateHelper que
	 * nada mais e do que um objeto auxiliar que possui um mapa de estados e
	 * contem os atributos que serao salvos do componente quando o metodo de
	 * salvamento for definido como client no parametro
	 * javax.faces.STATE_SAVING_METHOD no arquivo web.xml
	 */
	public void setSize(String size) {
		super.getStateHelper().put("size", size);
		this.size = size;
	}

	/**
	 * @return the max
	 */
	public Integer getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(Integer max) {
		this.max = max;
	}

	/**
	 * @return the min
	 */
	public Integer getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(Integer min) {
		this.min = min;
	}

	/**
	 * @return the actionMethod
	 */
	public MethodExpression getActionMethod() {
		return actionMethod;
	}

	/**
	 * @param actionMethod
	 *            the actionMethod to set
	 */
	public void setActionMethod(MethodExpression actionMethod) {
		this.actionMethod = actionMethod;
	}

}
