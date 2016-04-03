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
		 * O método merge faz a mesma função de saveOrUpdate, porém ele é
		 * utilizando quando existe mais de uma instância de um mesmo objeto na
		 * memória. Neste caso, o Hibernate se encarregará de fazer a fusão
		 * dessas instâncias e salvar as informações no banco de dados.
		 */
		Categoria merged = (Categoria) this.session.merge(categoria);

		/*
		 * Este método força a sincrinização dos objetos que estão na memória
		 * com o banco de dados. O método flush() sempre é utilizado depois de
		 * merge, pois somente quando flush() for chamado, é que o Hibernate
		 * dará o UPDATE do objeto no banco caso já exista.
		 */
		this.session.flush();

		/*
		 * O método clear() remove da memória todas as instâncias carregadas de
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
