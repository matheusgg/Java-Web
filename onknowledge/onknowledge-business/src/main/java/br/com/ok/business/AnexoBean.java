package br.com.ok.business;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;

import br.com.ok.model.Anexo;
import br.com.ok.model.dao.AnexoDAO;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.files.OKFileHandler;

/**
 * The Class AnexoBean.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Stateless
public class AnexoBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1465448726774255740L;

	/** The anexo dao. */
	@Inject
	private AnexoDAO anexoDAO;

	/**
	 * Salva.
	 *
	 * @param anexo
	 *            the anexo
	 */
	public void salva(Anexo anexo) {
		this.anexoDAO.save(anexo);
	}

	/**
	 * Removes the.
	 *
	 * @param anexo
	 *            the anexo
	 */
	public void remove(Anexo anexo) {
		this.anexoDAO.remove(anexo);
	}

	/**
	 * Atualiza.
	 *
	 * @param anexo
	 *            the anexo
	 * @return the anexo
	 */
	public Anexo atualiza(Anexo anexo) {
		return this.anexoDAO.update(anexo);
	}

	/**
	 * Salva arquivos anexos em disco.
	 *
	 * @param anexos
	 *            the anexos
	 */
	public void salvaArquivosAnexosEmDisco(List<Anexo> anexos) {
		anexos.stream().filter(anexo -> anexo.getFileData() != null).forEach(anexo -> {
			StringBuilder path = new StringBuilder(OKFileHandler.getDefaultFilePath()).append(File.separator);
			path.append(RandomStringUtils.randomAlphabetic(OKConstants.VALOR_DEZ));
			path.append(OKConstants.PONTO_FINAL).append(anexo.getExtensaoArquivo());
			OKFileHandler.saveFile(anexo.getFileData(), path.toString());
			anexo.setCaminhoArquivo(path.toString());
			this.salva(anexo);
		});
	}

}
