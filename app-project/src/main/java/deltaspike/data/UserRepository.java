package deltaspike.data;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import model.User;
import model.User_;

import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Modifying;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

@Repository(forEntity = User.class)
@RequestScoped
public abstract class UserRepository extends AbstractEntityRepository<User, Integer>implements CriteriaSupport<User>, Serializable {

	private static final long serialVersionUID = -7448376501976583719L;

	/**
	 * Constru��o da query por conven��es de nomeclatura de m�todo.
	 * 
	 * @param id
	 * @return
	 */
	public abstract User findById(Integer id);

	public abstract User findByNomeLike(String nome);

	/**
	 * JQPL
	 * 
	 * @param nome
	 * @return
	 */
	@Query(value = "select count(u) from User u where u.nome like ?")
	public abstract Long countByNomeLike(String nome);

	/**
	 * NamedQuery
	 * 
	 * @see org.apache.deltaspike.data.api.EntityRepository#findAll()
	 */
	@Query(named = "User.findAll")
	public abstract List<User> findAllNamedQuery();

	/**
	 * NamedQuery com named param
	 * 
	 * @param id
	 * @return
	 */
	@Query(named = "User.findById")
	public abstract User findByIdNamedQuery(@QueryParam("id") Integer id);

	/**
	 * Com @Modifying ser� feito um executeUpdate() ao inv�s de um
	 * getResultList() ou getSingleResult(). O m�todo deve ser void ou retornar
	 * um int.
	 * 
	 * @param login
	 * @param id
	 * @return
	 */
	@Modifying
	@Query(named = "User.updateLoginById")
	public abstract int updateLoginNamedQuery(@QueryParam("login") String login, @QueryParam("id") Integer id);

	/**
	 * Utiliza a nomeclatura de m�todos para realizar cria��o da query de
	 * consulta. Caso nenhum resultado seja encontrado, o valor null ser�
	 * retornado ao inv�s de uma NoResultException ser lan�ada.
	 * 
	 * @param nome
	 * @return
	 */
	public abstract User findOptionalByNomeLike(String nome);

	/**
	 * O atributo singleResult = SingleResultType.OPTIONAL especifica que caso
	 * nenhum resultado seja encontrado, o valor null ser� retornado ao inv�s de
	 * uma NoResultException ser lan�ada.
	 * 
	 * @param nome
	 * @return
	 */
	@Query(value = "select u from User u where u.login like :nome", singleResult = SingleResultType.OPTIONAL)
	public abstract User findOptionalByNomeLikeJPQL(@QueryParam("nome") String nome);

	/**
	 * Utilizando as express�es de m�todos, findAny significa que caso nenhum
	 * resultado seja encontrado, null ser� retornado. Por�m se mais do que um
	 * resultado for encontrado, apenas o primeiro ser� retornado. Pela
	 * configura��o por anota��o, basta especificar o atributo singleResult =
	 * SingleResultType.ANY.
	 * 
	 * @param nome
	 * @return
	 */
	// @Query(value = "select u from User u where u.login like :nome",
	// singleResult = SingleResultType.ANY)
	public abstract User findAnyByNomeLike(String nome);

	public User findByIdCriteria(Integer id) {
		return this.criteria().eq(User_.id, 1).getSingleResult();
	}

}
