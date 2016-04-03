package br.com.estudos.tabela;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class DataModel<C extends Object> extends ListDataModel<C> implements
		SelectableDataModel<C> {

	public DataModel(List<C> data) {
		super(data);
	}

	public DataModel() {

	}

	@Override
	public C getRowData(String rowkey) {
		@SuppressWarnings("unchecked")
		List<C> listaDeObjetos = (List<C>) getWrappedData();

		for (C objeto : listaDeObjetos) {
			if (objeto.toString().equals(rowkey))
				return objeto;
		}

		return null;
	}

	@Override
	public Object getRowKey(C objeto) {
		return objeto;
	}

}
