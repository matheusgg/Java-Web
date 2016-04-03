package model;

import javax.persistence.PrePersist;

public class ClienteListener {

	@PrePersist
	public void prePersist(Cliente cliente) {
		System.out.println("Inserindo o cliente... " + cliente.getNome());
	}

}
