package br.com.eclipselink.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import br.com.eclipselink.business.AppBean;
import br.com.eclipselink.model.PessoaFisica;

@Named
@ViewScoped
public class AppMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2980170633142902762L;

	@Inject
	private AppBean appBean;

	@Getter
	private List<PessoaFisica> pessoas;

	@PostConstruct
	public void testMapping() {
		this.pessoas = this.appBean.pesquisaPessoasFisicas();
	}

}
