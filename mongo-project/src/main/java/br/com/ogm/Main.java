package br.com.ogm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.model.Cliente;
import br.com.model.Produto;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mongo-project");
		EntityManager em = emf.createEntityManager();

		// Main.testPerformance(em);
		Main.testSearch(em);

		// Cliente cliente = new Cliente();
		// cliente.setNome("Matheus");
		//
		// Produto produto = new Produto();
		// produto.setNome("Teclado");
		// produto.setPreco(new BigDecimal("120.00"));
		// produto.setCliente(cliente);
		//
		// cliente.setProdutos(Arrays.asList(produto));

		// EntityTransaction tx = em.getTransaction();
		// tx.begin();

		/*
		 * Insert
		 */
		// em.persist(cliente);

		/*
		 * Count
		 */
		// Cliente cli = em.find(Cliente.class, 1l);
		// System.out.println(cli);
		// Long count = em.createQuery("select count(c) from Cliente c",
		// Long.class).getSingleResult();
		// System.out.println(count);

		// tx.commit();
		emf.close();
		System.out.println("OK");
	}

	public static void testPerformance(EntityManager em) {
		List<Cliente> clientes = new ArrayList<>();
		Cliente cliente = null;
		Produto produto = null;

		for (int i = 0; i < 100; i++) {
			cliente = new Cliente("Cliente " + (i + 1));
			cliente.setProdutos(new ArrayList<>());

			for (int j = 0; j < 100; j++) {
				produto = new Produto("Produto " + (j + 1), new BigDecimal(100 * (j + 1)), cliente);
				cliente.getProdutos().add(produto);
			}

			clientes.add(cliente);
		}

		System.out.println("Dados carregados, salvando...");

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		for (Cliente cli : clientes) {
			em.persist(cli);
		}

		tx.commit();
	}

	public static void testSearch(EntityManager em) {
		List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
		System.out.println(clientes.size());
	}
}
