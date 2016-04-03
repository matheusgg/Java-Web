package br.com.estudos.tabela;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class Modelo extends ListDataModel<Carros> implements
		SelectableDataModel<Carros> {

	public Modelo(List<Carros> data) {
		super(data);
	}

	/*
	 * Quando um registro � selecionado na tabela, este � o primeiro m�todo
	 * executado. Este m�todo compara a chave do registro selecionado com os
	 * itens existentes na tabela. Caso exista, retorna o objeto da posi��o
	 * daquela chave.
	 */
	@Override
	public Carros getRowData(String data) {
		@SuppressWarnings("unchecked")
		List<Carros> cars = (List<Carros>) getWrappedData();

		for (Carros car : cars) {
			if (car.getNome().equals(data))
				return car;
		}

		return null;
	}

	/*
	 * Logo ap�s a execu��o do m�todo acima, est� m�todo � executado. Ele recebe
	 * o objeto retornado do m�todo anterior e retorna um objeto, no caso, uma
	 * String.
	 */
	@Override
	public Object getRowKey(Carros car) {
		return car.getNome();
	}

}
