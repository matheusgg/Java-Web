package br.com.ok.business;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.model.Turma;
import br.com.ok.model.dao.TurmaDAO;
import br.com.ok.util.pagination.OKPaginatedList;

/**
 * The Class TurmaBean.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Stateless
public class TurmaBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8818045680169328384L;

	/** The turma dao. */
	@Inject
	private TurmaDAO turmaDAO;

	/**
	 * Pesquisa por codigo com data atual.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the turma
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Turma pesquisaPorCodigoComDataAtual(String codigo) {
		return this.turmaDAO.findByCodigoAndDataAtual(codigo, new Date());
	}

	/**
	 * Lista codigos turmas.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<String> listaCodigosTurmas(String codigo) {
		return this.turmaDAO.listByCodigo(codigo);
	}

	/**
	 * Pesquisa turmas por argumentos.
	 *
	 * @param args
	 *            the args
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max result
	 * @return the OK paginated list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public OKPaginatedList<Turma> pesquisaTurmasPorArgumentos(Turma args, int firstResult, int maxResult) {
		return this.turmaDAO.findTurmasByArgs(args, firstResult, maxResult);
	}

	/**
	 * Pesquisa turma por id.
	 *
	 * @param id
	 *            the id
	 * @return the turma
	 */
	public Turma pesquisaTurmaPorId(Integer id) {
		Turma turma = this.turmaDAO.findById(id);
		this.turmaDAO.initProxy(turma.getCodigosSeguranca());
		turma.setDataInicioOriginal(turma.getDataInicio());
		return turma;
	}

	/**
	 * Salva turma.
	 *
	 * @param turma
	 *            the turma
	 * @return the turma
	 */
	public Turma salvaTurma(Turma turma) {
		if (turma.getId() == null) {
			this.turmaDAO.save(turma);
		} else {
			turma = this.turmaDAO.update(turma);
		}
		return turma;
	}

	/**
	 * Valida datas turma.
	 *
	 * @param turma
	 *            the turma
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void validaDatasTurma(Turma turma) throws OKBusinessException {
		Date dataInicio = turma.getDataInicio();
		LocalDate dataInformada = LocalDateTime.ofInstant(dataInicio.toInstant(), ZoneId.systemDefault()).toLocalDate();

		if (!dataInicio.equals(turma.getDataInicioOriginal()) && dataInformada.isBefore(LocalDate.now())) {
			throw new OKBusinessException("{msg.validacao.data.inicial.invalida}", ":mainForm:dataInicio");
		}

		if (!turma.getDataEncerramento().after(turma.getDataInicio())) {
			throw new OKBusinessException("{msg.validacao.data.encerramento.invalida}", ":mainForm:dataTermino");
		}
	}

	/**
	 * Count by id and coordenador curso.
	 *
	 * @param idTurma
	 *            the id turma
	 * @param idCoordenador
	 *            the id coordenador
	 * @return the long
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long countByIdAndCoordenadorCurso(Integer idTurma, Integer idCoordenador) {
		return this.turmaDAO.countByIdAndCoordenadorCurso(idTurma, idCoordenador);
	}
}
