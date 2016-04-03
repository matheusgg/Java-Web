package customscope;

import java.beans.FeatureDescriptor;
import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.PropertyNotFoundException;

/**
 * Esta e a implementacao personalizada de um el resolver para resolver o escopo
 * personalizado que foi criado.
 * 
 * @author Matheus
 * 
 */
public class CustomElResolver extends ELResolver {

	/**
	 * Este metodo e responsavel por devolver um mapa que armazenara os beans
	 * que possuirem o escopo personalizado. Este metodo pode devolver tambem um
	 * managed bean que possui escopo personalizado que esta armazenada dentro
	 * do mapa do escopo customizado. Caso este metodo retorne um mapa, o JSF se
	 * encarregara de inserir o managed bean dentro dele. Se for solicitado um
	 * bean que nao possui um escopo personalizado, este metodo retornara null.
	 */
	@Override
	public Object getValue(ELContext context, Object base, Object property) {
		if (property == null) {
			throw new PropertyNotFoundException("A Propriedade não pode ser nula!");
		}

		Object object = null;

		if (base == null) {

			/*
			 * Cria o mapa do escopo personalizado
			 */
			CustomScopeMap map = CustomScopeMap.getInstance();

			/*
			 * Verifica se a solicitacao e pelo mapa do escopo personalizado,
			 * caso positivo, informa ao JSF que o objeto foi encontrado e
			 * devolve o mapa.
			 */
			if (property.equals(CustomScopeMap.SCOPE_NAME)) {

				/*
				 * Informa ao JSF que o objeto foi encontrado, deste modo, a
				 * implementacao nao continuara a pesquisa nos outros el
				 * resolvers.
				 */
				context.setPropertyResolved(true);
				object = map;

			} else {
				/*
				 * Retorna o MB que possui o escopo personalizado que esta
				 * dentro do mapa de escopo personalizado.
				 */
				object = map.get(property);

				if (object != null) {
					context.setPropertyResolved(true);
				}
			}
		}

		return object;
	}

	@Override
	public Class<?> getCommonPropertyType(ELContext context, Object base) {
		return null;
	}

	@Override
	public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
		return null;
	}

	@Override
	public Class<?> getType(ELContext context, Object base, Object property) {
		return null;
	}

	@Override
	public boolean isReadOnly(ELContext context, Object base, Object property) {
		return false;
	}

	@Override
	public void setValue(ELContext context, Object base, Object property, Object value) {

	}

}
