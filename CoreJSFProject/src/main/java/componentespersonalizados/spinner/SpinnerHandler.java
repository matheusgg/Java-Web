package componentespersonalizados.spinner;

import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.MetaRule;
import javax.faces.view.facelets.MetaRuleset;

import com.sun.faces.facelets.tag.MethodRule;

/**
 * Como o handler de tag padrao do JSF nao sabe como identificar expressoes de
 * metodos que nao se encaixam nas regras especiais, e preciso criar um handler
 * que saiba tratar essas expressoes desejadas.
 * 
 * @author Matheus
 * 
 */
public class SpinnerHandler extends ComponentHandler {

	public SpinnerHandler(ComponentConfig config) {
		super(config);
	}

	/**
	 * Adiciona uma nova regra para tratamento de metodo no set de regras do
	 * componente. Aqui tambem e definida a assinatura e o tipo de retorno do
	 * metodo.
	 */
	@Override
	@SuppressWarnings("rawtypes")
	protected MetaRuleset createMetaRuleset(Class type) {
		MetaRule actionMethod = new MethodRule("actionMethod", String.class, new Class[] { Void.class });
		return super.createMetaRuleset(type).addRule(actionMethod);
	}

}
