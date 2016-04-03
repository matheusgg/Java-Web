package br.com;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.model.Cliente;
import br.com.model.Produto;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;
import com.uaihebert.util.CriteriaUtil;

public class App {
	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory("pu").createEntityManager();

		// Join
		EasyCriteria<Produto> easy = EasyCriteriaFactory.createQueryCriteria(em, Produto.class);
		List<Produto> p = easy.innerJoin("cliente").andJoinStringLike("cliente", "nome", "Ma%").getResultList();
		System.out.println(p.size());
		System.out.println(CriteriaUtil.count(easy));

		// // Empty Collection
		EasyCriteria<Cliente> easyc = EasyCriteriaFactory.createQueryCriteria(em, Cliente.class);
		List<Cliente> c = easyc.andCollectionIsEmpty("produtos").getResultList();

		System.out.println(c.size());
		System.out.println(CriteriaUtil.count(easyc));
	}

}