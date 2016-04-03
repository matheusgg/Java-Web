package financeiro.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import financeiro.categoria.Categoria;
import financeiro.categoria.CategoriaRN;

/**
 * Conversor personalizado para converter o texto de um item selecionado do
 * SelectOneMenu em um objeto do tipo Categoria.
 * 
 * @author Matheus
 * 
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

	/*
	 * Este m�todo � utilizado sempre que uma informa��o que estiver na tela
	 * precisar ser convertida para um objeto que ser� atribu�do a alguma
	 * propriedade do bean. Por este motivo, o par�metro de entrada � uma
	 * String, e o retorno � um objeto.
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && value.trim().length() > 0) {
			Integer codigo = Integer.valueOf(value);
			try {
				return new CategoriaRN().carregar(codigo);
			} catch (Exception ex) {
				throw new ConverterException(
						"N�o foi poss�vel encontrar a categoria de c�digo "
								+ value + ". " + ex.getMessage());
			}
		}
		return null;
	}

	/*
	 * Este m�todo � o inverso de getAsObject. Sempre que uma informa��o que
	 * estiver no bean precisar ser convertida para texto para exibi��o na
	 * p�gina, este m�todo ser� chamado. Por este motivo ele recebe um objeto
	 * como par�metro, e retorna a String que ser� exibida na tela.
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Categoria categoria = (Categoria) value;
			return categoria.getCodigo().toString();
		}
		return "";
	}

}
