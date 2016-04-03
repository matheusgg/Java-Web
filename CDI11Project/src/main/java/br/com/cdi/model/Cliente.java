package br.com.cdi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table
@Entity
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -789721154982545205L;

	@Id
	@GeneratedValue
	private Long id;
	private String nome;

	@OneToMany(targetEntity = Produto.class, mappedBy = "cliente")
	private List<Produto> produtos;

}
