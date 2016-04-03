package jersey;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import resources.ProdutoResource;

/**
 * Existem várias formas de se configurar o Jersey. Um possibilidade bem simples
 * de configurar o Jersey em um servlet container 3.0 é criar uma classe que
 * extende ResourceConfig, registrar os pacotes que possuem os recursos e que
 * serao monitorados. Em seguida, basta anotar essa classe com ApplicationPath
 * indicando o caminho do mapeamento dos recursos. Na primeira vez que um
 * recurso for solicitado, uma instancia dessa classe sera criada e os pacotes
 * registrados. Outra opcao mais padronizada é estender a classe Application e
 * informar as classes que disponibilizarao os metodos para manipulacao dos
 * recursos.
 * 
 * @author Matheus
 * 
 */
@ApplicationPath("rest")
public class JerseyConfig extends Application { // extends ResourceConfig {

	// public JerseyConfig() {
	// super.packages("resource");
	// }

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> mc = new HashSet<>();
		mc.add(ProdutoResource.class);
		return mc;
	}

}
