import java.util.ArrayList;

import javax.faces.model.*;

public class Cliente {
	private ArrayList<Client> clientes;
	private DataModel<Client> cliente;

	public Cliente() {
		clientes = new ArrayList<Client>();
		Client c1 = new Client();
		c1.nome = "Matheus";
		c1.idade = 19;

		Client c2 = new Client();
		c2.nome = "Nelson";
		c2.idade = 53;
		
		Client c3 = new Client();
		c3.nome = "Rose";
		c3.idade = 44;

		clientes.add(c1);
		clientes.add(c2);
		clientes.add(c3);
		clientes.add(c1);
		clientes.add(c2);
		clientes.add(c3);
		clientes.add(c1);
		clientes.add(c2);
		clientes.add(c3);
		clientes.add(c1);
		clientes.add(c2);
		clientes.add(c3);
		cliente = new ListDataModel<Client>(clientes);
	}

	public void setClientes(ArrayList<Client> clientes) {
		this.clientes = clientes;
	}

	public DataModel<Client> getCliente() {		
		return cliente;

	}

	public void setCliente(DataModel<Client> cliente) {
		this.cliente = cliente;
	}
}
