package app.logging;

import app.model.Cliente;
import app.model.dao.ClienteDAO;

public class Principal {
	public static void main(String[] args) {
		Cliente cliente = new ClienteDAO().busca("Matheus");
		System.out.println("==============================================");		
		System.out.println(cliente.getEndereco());
	}

}
