package br.com.cdi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table
@Entity
public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1103739028419713413L;

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private double preco;

	@ManyToOne(targetEntity = Cliente.class)
	private Cliente cliente;

}
