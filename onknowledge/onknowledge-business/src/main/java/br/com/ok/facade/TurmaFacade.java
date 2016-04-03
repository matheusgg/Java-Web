package br.com.ok.facade;

import java.util.List;

import javax.inject.Inject;

import br.com.ok.business.CodigoSegurancaBean;
import br.com.ok.business.TurmaBean;
import br.com.ok.facade.base.OKGenericFacade;
import br.com.ok.model.CodigoSeguranca;
import br.com.ok.model.Turma;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.pagination.OKPaginatedList;

/**
 * The Class TurmaFacade.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public class TurmaFacade extends OKGenericFacade {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1936607769322063303L;

	/** The turma bean. */
	@Inject
	private TurmaBean turmaBean;

	@Inject
	private CodigoSegurancaBean codigoSegurancaBean;

	/**
	 * Lista codigos turmas.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the list
	 */
	public List<String> listaCodigosTurmas(String codigo) {
		return this.turmaBean.listaCodigosTurmas(codigo);
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
	public OKPaginatedList<Turma> pesquisaTurmasPorArgumentos(Turma args, int firstResult, int maxResult) {
		return this.turmaBean.pesquisaTurmasPorArgumentos(args, firstResult, maxResult);
	}

	/**
	 * Pesquisa turma por id.
	 *
	 * @param id
	 *            the id
	 * @return the turma
	 */
	public Turma pesquisaTurmaPorId(Integer id) {
		return this.turmaBean.pesquisaTurmaPorId(id);
	}

	/**
	 * Salva turma.
	 *
	 * @param turma
	 *            the turma
	 */
	public void salvaTurma(Turma turma) {
		this.turmaBean.validaDatasTurma(turma);

		if (turma.isGeraCodigosSeguranca() && turma.getQuantidadeCodigosSeguranca() != null && turma.getQuantidadeCodigosSeguranca() > OKConstants.VALOR_ZERO) {
			CodigoSeguranca codigoSeguranca = null;
			for (int i = 0; i < turma.getQuantidadeCodigosSeguranca(); i++) {
				codigoSeguranca = this.codigoSegurancaBean.geraCodigoSeguranca();
				codigoSeguranca.setTurma(turma);
				turma.getCodigosSeguranca().add(codigoSeguranca);
			}
		}

		this.turmaBean.salvaTurma(turma);
	}

}
