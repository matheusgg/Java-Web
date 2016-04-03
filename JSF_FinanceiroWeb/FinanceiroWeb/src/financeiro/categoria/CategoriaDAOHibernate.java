package financeiro.categoria;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import financeiro.usuario.Usuario;

public class CategoriaDAOHibernate implements CategoriaDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public Categoria salvar(Categoria categoria) {
		/*
		 * O m�todo merge faz a mesma fun��o de saveOrUpdate, por�m ele �
		 * utilizando quando existe mais de uma inst�ncia de um mesmo objeto na
		 * mem�ria. Neste caso, o Hibernate se encarregar� de fazer a fus�o
		 * dessas inst�ncias e salvar as informa��es no banco de dados.
		 */
		Categoria merged = (Categoria) this.session.merge(categoria);

		/*
		 * Este m�todo for�a a sincriniza��o dos objetos que est�o na mem�ria
		 * com o banco de dados. O m�todo flush() sempre � utilizado depois de
		 * merge, pois somente quando flush() for chamado, � que o Hibernate
		 * dar� o UPDATE do objeto no banco caso j� exista.
		 */
		this.session.flush();

		/*
		 * O m�todo clear() remove da mem�ria todas as inst�ncias carregadas de
		 * um objeto.
		 */
		this.session.clear();
		return merged;
	}

	@Override
	public void excluir(Categoria categoria) {
		categoria = (Categoria) this.carregar(categoria.getCodigo());
		this.session.delete(categoria);
		this.session.flush();
		this.session.clear();
	}

	@Override
	public Categoria carregar(Integer categoria) {
		return (Categoria) this.session.get(Categoria.class, categoria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> listar(Usuario usuario) {
		String hql = "select c from Categoria c where c.pai is null and c.usuario = :usuario";
		Query query = this.session.createQuery(hql);
		query.setInteger("usuario", usuario.getCodigo());
		List<Categoria> lista = query.list();
		return lista;
	}

}
