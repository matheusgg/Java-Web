package br.com.ok.model.dao;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.ok.model.Aluno;
import br.com.ok.model.Anexo;
import br.com.ok.model.Professor;
import br.com.ok.model.Usuario;
import br.com.ok.model.dao.base.OKGenericDAO;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.pagination.OKPaginatedList;

/**
 * The Class UsuarioDAO.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Component
public class UsuarioDAO extends OKGenericDAO<Usuario> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5802138997508612660L;

	/**
	 * Find usuario by login and password.
	 *
	 * @param login
	 *            the login
	 * @param password
	 *            the password
	 * @return the usuario
	 */
	public Usuario findByLoginAndPassword(String login, byte[] password) {
		TypedQuery<Usuario> query = super.getEntityManager().createNamedQuery("Usuario.findByLoginAndPassword", Usuario.class);
		query.setParameter("login", login);
		query.setParameter("senha", password);
		return query.getSingleResult();
	}

	/**
	 * Count by email.
	 *
	 * @param email
	 *            the email
	 * @return the long
	 */
	public Long countByEmail(String email) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("Usuario.countByEmail", Long.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}

	/**
	 * Count by email excluding user id.
	 *
	 * @param email
	 *            the email
	 * @param userId
	 *            the user id
	 * @return the long
	 */
	public Long countByEmailExcludingUserId(String email, Integer userId) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("Usuario.countByEmailExcludingUserId", Long.class);
		query.setParameter("email", email);
		query.setParameter("userId", userId);
		return query.getSingleResult();
	}

	/**
	 * Count usuario by login.
	 *
	 * @param login
	 *            the login
	 * @return the long
	 */
	public Long countByLogin(String login) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("Usuario.countByLogin", Long.class);
		query.setParameter("login", login);
		return query.getSingleResult();
	}

	/**
	 * Count by login excluding user id.
	 *
	 * @param login
	 *            the login
	 * @param userId
	 *            the user id
	 * @return the long
	 */
	public Long countByLoginExcludingUserId(String login, Integer userId) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("Usuario.countByLoginExcludingUserId", Long.class);
		query.setParameter("login", login);
		query.setParameter("userId", userId);
		return query.getSingleResult();
	}

	/**
	 * Find alunos by arguments.
	 *
	 * @param args
	 *            the args
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max result
	 * @return the OK paginated list
	 */
	public OKPaginatedList<Usuario> findAlunosByArguments(Aluno args, int firstResult, int maxResult) {
		String select = "new Aluno(obj.id, obj.nome, obj.sobrenome, obj.apelido, obj.login, obj.email, obj.dataCadastro, c.nome, t.codigo, obj.perfil)";
		StringBuilder queryBuilder = new StringBuilder(OKConstants.SELECT_STRING).append(select).append(OKConstants.WHITE_SPACE);
		queryBuilder.append("from Aluno obj inner join obj.turma t inner join t.curso c").append(OKConstants.WHITE_SPACE);

		Optional<String> disciplinaOptional = Optional.ofNullable(args.getDisciplinas().get(0).getCodigo());
		queryBuilder.append(disciplinaOptional.isPresent() ? "inner join obj.disciplinas d" : OKConstants.STRING_VAZIA).append(OKConstants.WHERE);

		Map<String, Object> params = this.prepareCommonSearchArguments(args, queryBuilder);

		Optional.ofNullable(StringUtils.trimToNull(args.getTurma().getCodigo())).ifPresent(turma -> {
			queryBuilder.append(OKConstants.AND).append("t.codigo = :turma");
			params.put("turma", turma);
		});

		Optional.ofNullable(StringUtils.trimToNull(args.getTurma().getCurso().getNome())).ifPresent(curso -> {
			queryBuilder.append(OKConstants.AND).append("c.nome = :curso");
			params.put("curso", curso);
		});

		disciplinaOptional.ifPresent(disciplina -> {
			queryBuilder.append(OKConstants.AND).append("d.codigo = :disciplina");
			params.put("disciplina", disciplina);
		});

		String queryString = queryBuilder.append(OKConstants.ORDER_BY).append("obj.nome").toString();
		String countQueryString = queryString.replace(select, OKConstants.COUNT_STRING);

		TypedQuery<Usuario> query = super.getEntityManager().createQuery(queryString, Usuario.class);
		TypedQuery<Long> countQuery = super.getEntityManager().createQuery(countQueryString, Long.class);

		this.prepareCommonSearchParams(params, query, countQuery, firstResult, maxResult);
		return new OKPaginatedList<>(query.getResultList(), countQuery.getSingleResult());
	}

	/**
	 * Find professores by arguments.
	 *
	 * @param args
	 *            the args
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max result
	 * @return the OK paginated list
	 */
	public OKPaginatedList<Usuario> findProfessoresByArguments(Professor args, int firstResult, int maxResult) {
		String select = "new Professor(obj.id, obj.nome, obj.sobrenome, obj.apelido, obj.login, obj.email, obj.dataCadastro, obj.perfil)";
		StringBuilder queryBuilder = new StringBuilder(OKConstants.SELECT_STRING).append(select);
		queryBuilder.append(OKConstants.WHITE_SPACE).append("from Professor obj inner join obj.perfil p").append(OKConstants.WHITE_SPACE);

		Optional<String> disciplinaOptional = Optional.ofNullable(StringUtils.trimToNull(args.getDisciplinas().get(0).getCodigo()));
		queryBuilder.append(disciplinaOptional.isPresent() ? "inner join obj.disciplinas d" : OKConstants.STRING_VAZIA).append(OKConstants.WHERE);

		Map<String, Object> params = this.prepareCommonSearchArguments(args, queryBuilder);

		disciplinaOptional.ifPresent(disciplina -> {
			queryBuilder.append(OKConstants.AND).append("d.codigo = :disciplina");
			params.put("disciplina", disciplina);
		});

		Optional.ofNullable(args.getPerfil().getDescricao()).ifPresent(perfil -> {
			queryBuilder.append(OKConstants.AND).append("p.descricao = :perfil");
			params.put("perfil", perfil);
		});

		String queryString = queryBuilder.append(OKConstants.ORDER_BY).append("obj.nome").toString();
		String countQueryString = queryString.replace(select, OKConstants.COUNT_STRING);

		TypedQuery<Usuario> query = super.getEntityManager().createQuery(queryString, Usuario.class);
		TypedQuery<Long> countQuery = super.getEntityManager().createQuery(countQueryString, Long.class);

		this.prepareCommonSearchParams(params, query, countQuery, firstResult, maxResult);
		return new OKPaginatedList<>(query.getResultList(), countQuery.getSingleResult());
	}

	/**
	 * Find admins by arguments.
	 *
	 * @param args
	 *            the args
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max result
	 * @return the OK paginated list
	 */
	public OKPaginatedList<Usuario> findAdminsByArguments(Usuario args, int firstResult, int maxResult) {
		String select = "new Usuario(obj.id, obj.nome, obj.sobrenome, obj.apelido, obj.login, obj.email, obj.dataCadastro, obj.perfil)";
		StringBuilder queryBuilder = new StringBuilder(OKConstants.SELECT_STRING).append(OKConstants.WHITE_SPACE).append(select);
		queryBuilder.append(OKConstants.WHITE_SPACE).append("from Usuario obj inner join obj.perfil p").append(OKConstants.WHERE);

		Map<String, Object> params = this.prepareCommonSearchArguments(args, queryBuilder);
		queryBuilder.append(OKConstants.AND).append("p.descricao = :perfil");
		params.put("perfil", args.getPerfil().getDescricao());

		String queryString = queryBuilder.append(OKConstants.ORDER_BY).append("obj.nome").toString();
		String countQueryString = queryString.replace(select, OKConstants.COUNT_STRING);

		TypedQuery<Usuario> query = super.getEntityManager().createQuery(queryString, Usuario.class);
		TypedQuery<Long> countQuery = super.getEntityManager().createQuery(countQueryString, Long.class);

		this.prepareCommonSearchParams(params, query, countQuery, firstResult, maxResult);
		return new OKPaginatedList<>(query.getResultList(), countQuery.getSingleResult());
	}

	/**
	 * Prepare common search arguments.
	 *
	 * @param args
	 *            the args
	 * @param queryBuilder
	 *            the query builder
	 * @return the map
	 */
	private Map<String, Object> prepareCommonSearchArguments(Usuario args, StringBuilder queryBuilder) {
		Map<String, Object> params = new HashMap<>();

		Optional.ofNullable(StringUtils.trimToNull(args.getNome())).ifPresent(nome -> {
			queryBuilder.append("obj.nome like :nome").append(OKConstants.AND);
			params.put("nome", OKConstants.PERCENT + nome + OKConstants.PERCENT);
		});

		Optional.ofNullable(StringUtils.trimToNull(args.getSobrenome())).ifPresent(sobrenome -> {
			queryBuilder.append("obj.sobrenome like :sobrenome").append(OKConstants.AND);
			params.put("sobrenome", OKConstants.PERCENT + sobrenome + OKConstants.PERCENT);
		});

		Optional.ofNullable(StringUtils.trimToNull(args.getLogin())).ifPresent(login -> {
			queryBuilder.append("obj.login like :login").append(OKConstants.AND);
			params.put("login", OKConstants.PERCENT + login + OKConstants.PERCENT);
		});

		Optional.ofNullable(StringUtils.trimToNull(args.getEmail())).ifPresent(email -> {
			queryBuilder.append("obj.email = :email").append(OKConstants.AND);
			params.put("email", email);
		});

		queryBuilder.append("obj.dataCadastro between :dataCadastroIncial and :dataCadastroFinal").append(OKConstants.WHITE_SPACE);
		params.put("dataCadastroIncial", Optional.ofNullable(args.getDataCadastro()).orElse(Date.from(OKConstants.MINIMUM_ZONED_DATE_TIME.toInstant())));
		params.put("dataCadastroFinal", Optional.ofNullable(args.getDataCadastroFinal()).orElse(Date.from(ZonedDateTime.now().toInstant())));

		return params;
	}

	/**
	 * Prepare common search params.
	 *
	 * @param params
	 *            the params
	 * @param query
	 *            the query
	 * @param countQuery
	 *            the count query
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max result
	 */
	private void prepareCommonSearchParams(Map<String, Object> params, TypedQuery<Usuario> query, TypedQuery<Long> countQuery, int firstResult, int maxResult) {
		for (Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
			countQuery.setParameter(entry.getKey(), entry.getValue());
		}
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
	}

	/**
	 * Find professores by disciplina id.
	 *
	 * @param id
	 *            the id
	 * @return the list
	 */
	public List<Professor> findProfessoresByDisciplinaId(Integer id) {
		TypedQuery<Professor> query = super.getEntityManager().createNamedQuery("Professor.findProfessorByDisciplinaId", Professor.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	/**
	 * Find nomes professores.
	 *
	 * @param nome
	 *            the nome
	 * @return the list
	 */
	public List<String> findNomesProfessores(String nome) {
		TypedQuery<String> query = super.getEntityManager().createNamedQuery("Professor.findNomesProfessores", String.class);
		query.setParameter("nome", nome + OKConstants.PERCENT);
		return query.getResultList();
	}

	/**
	 * Find by email.
	 *
	 * @param email
	 *            the email
	 * @return the usuario
	 */
	public Usuario findByEmail(String email) {
		TypedQuery<Usuario> query = super.getEntityManager().createNamedQuery("Usuario.findByEmail", Usuario.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}

	/**
	 * Find by login.
	 *
	 * @param login
	 *            the login
	 * @return the usuario
	 */
	public Usuario findByLogin(String login) {
		TypedQuery<Usuario> query = super.getEntityManager().createNamedQuery("Usuario.findByLogin", Usuario.class);
		query.setParameter("login", login);
		return query.getSingleResult();
	}

	/**
	 * Find all active logins.
	 *
	 * @return the list
	 */
	public List<String> findAllActiveLogins() {
		TypedQuery<String> query = super.getEntityManager().createNamedQuery("Usuario.findAllLogins", String.class);
		return query.getResultList();
	}

	/**
	 * Find foto perfil by login usuario.
	 *
	 * @param login
	 *            the login
	 * @return the anexo
	 */
	public Anexo findFotoPerfilByLoginUsuario(String login) {
		TypedQuery<Anexo> query = super.getEntityManager().createNamedQuery("Usuario.findFotoPerfilByLogin", Anexo.class);
		query.setParameter("login", login);
		return query.getSingleResult();
	}

}
