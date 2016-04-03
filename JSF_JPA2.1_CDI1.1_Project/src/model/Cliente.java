package model;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners({ClienteListener.class})

@NamedQueries({ @NamedQuery(name = "Cliente.search", query = "select c from Cliente c"),
// Chamando funcoes com JPA 2.1
		@NamedQuery(name = "Cliente.searchFunction", query = "select c from Cliente c where FUNCTION('DAY', c.nascimento) = :param") })

// Chamar Stored Procedures com JPA 2.1
@NamedStoredProcedureQuery(name = "insertAndSearch", procedureName = "insertAndSearch", parameters = {
		// Parametros de entrada
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "nome", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "nascimento", type = Date.class), }, resultClasses = Cliente.class)

@SqlResultSetMappings({
	/*
	 * Criacao de objetos de acordo com o mapeamento do construtor de resultados.
	 * Com este novo recurso, e possivel escrever queries nativas em SQL e criar
	 * construtores de resultados para converter o resultado da query SQL em objetos
	 * mapeados. Utilizando o ConstructorResult, os objetos serao criados pela invocacao
	 * do construtor que recebe os parametros (@ColumnResult) informados.
	 */
	@SqlResultSetMapping(name = "ClienteResult", classes = { 
			@ConstructorResult(targetClass = Cliente.class, columns = {
				@ColumnResult(name = "id", type = int.class), 
				@ColumnResult(name = "nome", type = String.class), 
			}) 
	}),
	
	@SqlResultSetMapping(name = "ClienteFieldResult", entities={
			@EntityResult(entityClass=Cliente.class, fields={
				@FieldResult(name="id", column="ID"),
				@FieldResult(name="nome", column="NOME")
			})
	})
})
public class Cliente {

	@Id
	@GeneratedValue
	private int id;

	private String nome;

	@Temporal(TemporalType.DATE)
	private Date nascimento;

	public Cliente(int id, String nome){
		this.id = id;
		this.nome = nome;
	}
	
	public Cliente(){
		
	}
	
	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
