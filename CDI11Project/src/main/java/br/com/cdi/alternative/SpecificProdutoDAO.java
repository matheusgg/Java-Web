package br.com.cdi.alternative;

import javax.annotation.Priority;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.interceptor.Interceptor;

/**
 * A anotacao Alternative indica que esta implementaçao é alternativa, e se for
 * registrada (através do beans.xml ou definicao de prioridade) ela deve ser
 * utilizada. <br />
 * A anotação Priority define a prioridade deste Alternative, desta forma, com o
 * CDI 1.1, o registro deste Alternative é feito automaticamente sem a
 * necessidade de declaracao no arquivo beans.xml. Quanto maior o valor de
 * prioridade, mais importante será a implementacão que o container utilizará.
 * Além disso, essa nova anotacao traz um novo beneficio. Antes, as classes
 * marcadas com Alternative eram enxergadas apenas dentro de seu proprio jar,
 * com a nova anotacao Priority, esta classe é enxergada por todos os jars.
 * Deste modo, o container escaneará todos os jars que contenham beans.xml
 * procurando classes anotadas com Priority.
 * 
 * @author Matheus
 *
 */
@RequestScoped
@Alternative
@Priority(Interceptor.Priority.PLATFORM_AFTER)
public class SpecificProdutoDAO implements GenericProdutoDAO {

	@Override
	public String save() {
		return "SpecificProdutoDAO.save()";
	}

}
