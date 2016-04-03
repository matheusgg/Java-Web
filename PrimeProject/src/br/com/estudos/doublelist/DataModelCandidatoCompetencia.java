package br.com.estudos.doublelist;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class DataModelCandidatoCompetencia extends
		ListDataModel<CompetenciaBean> implements
		SelectableDataModel<CompetenciaBean> {

	public DataModelCandidatoCompetencia() {

	}

	public DataModelCandidatoCompetencia(List<CompetenciaBean> data) {
		super(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CompetenciaBean getRowData(String rowKey) {
		List<CompetenciaBean> competencias = (List<CompetenciaBean>) getWrappedData();
		for (CompetenciaBean competencia : competencias) {
			if (competencia.getNome().equals(rowKey)) {
				return competencia;
			}
		}

		return null;
	}

	@Override
	public Object getRowKey(CompetenciaBean competencia) {
		return competencia;
	}

}
