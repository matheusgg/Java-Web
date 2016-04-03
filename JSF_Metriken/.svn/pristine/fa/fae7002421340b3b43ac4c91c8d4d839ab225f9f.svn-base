package br.com.metriken.model.dao.base;

import java.io.Serializable;

/**
 * Interface que contém as assinaturas dos métodos comuns em todas as classes
 * DAO. As classes concretas devem informar o tipo de entidade que será
 * manipulada.
 * 
 * @author Matheus
 * 
 * @param <T>
 *            A Entidade que será manipulada.
 */
public interface GenericDAO<T> extends Serializable {

	public void persist(T object);

	public void remove(T object);

	public void update(T object);

}
