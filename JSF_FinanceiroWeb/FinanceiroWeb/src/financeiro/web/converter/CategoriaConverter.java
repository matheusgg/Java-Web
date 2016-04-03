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
	 * Este método é utilizado sempre que uma informação que estiver na tela
	 * precisar ser convertida para um objeto que será atribuído a alguma
	 * propriedade do bean. Por este motivo, o parâmetro de entrada é uma
	 * String, e o retorno é um objeto.
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
						"Não foi possível encontrar a categoria de código "
								+ value + ". " + ex.getMessage());
			}
		}
		return null;
	}

	/*
	 * Este método é o inverso de getAsObject. Sempre que uma informação que
	 * estiver no bean precisar ser convertida para texto para exibição na
	 * página, este método será chamado. Por este motivo ele recebe um objeto
	 * como parâmetro, e retorna a String que será exibida na tela.
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
