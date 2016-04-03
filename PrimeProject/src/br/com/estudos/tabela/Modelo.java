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
	 * Quando um registro é selecionado na tabela, este é o primeiro método
	 * executado. Este método compara a chave do registro selecionado com os
	 * itens existentes na tabela. Caso exista, retorna o objeto da posição
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
	 * Logo após a execução do método acima, esté método é executado. Ele recebe
	 * o objeto retornado do método anterior e retorna um objeto, no caso, uma
	 * String.
	 */
	@Override
	public Object getRowKey(Carros car) {
		return car.getNome();
	}

}
