package br.com.cdi.alternative;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ProdutoDAO implements GenericProdutoDAO {

	@Override
	public String save() {
		return "ProdutoDAO.save()";
	}

}
