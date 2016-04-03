package br.com.cdi.extension;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

import br.com.cdi.alternative.GenericProdutoDAO;

/**
 * O CDI disponibiliza uma API para extender suas funcionalidades. Desta forma,
 * quando o container identifica um ponto de injecao observado pelo metodo
 * informado nesta classe, este método é invocado e um objeto do tipo
 * ProcessAnnotatedType é passado como argumento. Este objeto possui várias
 * informacoes sobre o ponto de injecao e o tipo anotado. Esta classe de
 * extensao deve implementar a interface Extension e um arquivo
 * javax.enterprise.inject.spi.Extension, contendo o nome da classe de Extensao,
 * deve ser criado dentro da pasta META-INF/services. Deste modo o container
 * conseguira identificar esta extensao e utiliza-la.
 * 
 * @author Matheus
 *
 */
public class AppExtension implements Extension {

	public void processAnnotatedType(@Observes ProcessAnnotatedType<GenericProdutoDAO> annotatedType) {
		this.getClass();
	}

}
