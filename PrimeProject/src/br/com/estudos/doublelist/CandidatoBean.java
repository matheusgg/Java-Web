package br.com.estudos.doublelist;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.SelectEvent;

public class CandidatoBean {
	private List<CompetenciaBean> listCompetenciasQueNaoPossui;
	private List<CompetenciaBean> listCompetenciasQuePossui;
	private DataModelCandidatoCompetencia modelCompetenciasQueNaoPossui;
	private DataModelCandidatoCompetencia modelCompetenciasQuePossui;
	private CompetenciaBean competenciaSelecionada;
	private CompetenciaBean competenciaTemporaria;

	public CandidatoBean() {
		this.inicalizaAtributos();
		this.populaCompetencias();
	}

	public void selecionaRegistroTabela1(SelectEvent event) {
		this.competenciaTemporaria = modelCompetenciasQueNaoPossui
				.getRowData(this.competenciaSelecionada.getNome());
	}
	
	public void selecionaRegistroTabela2(SelectEvent event) {
		this.competenciaTemporaria = modelCompetenciasQuePossui
				.getRowData(this.competenciaSelecionada.getNome());
	}

	public String inserirCompetencia() {
		if (!this.listCompetenciasQuePossui
				.contains(this.competenciaTemporaria)) {
			this.listCompetenciasQueNaoPossui.remove(this.competenciaTemporaria);
			this.listCompetenciasQuePossui.add(this.competenciaTemporaria);
		}
		return "";
	}

	public String removerCompetencia() {
		if (!this.listCompetenciasQueNaoPossui
				.contains(this.competenciaTemporaria)) {
			this.listCompetenciasQuePossui.remove(this.competenciaTemporaria);
			this.listCompetenciasQueNaoPossui.add(this.competenciaTemporaria);
		}
		return "";
	}

	public void inicalizaAtributos() {
		listCompetenciasQueNaoPossui = new ArrayList<CompetenciaBean>();
		listCompetenciasQuePossui = new ArrayList<CompetenciaBean>();
		modelCompetenciasQueNaoPossui = new DataModelCandidatoCompetencia(
				this.listCompetenciasQueNaoPossui);
		modelCompetenciasQuePossui = new DataModelCandidatoCompetencia(
				this.listCompetenciasQuePossui);
	}

	public void populaCompetencias() {
		for (int i = 0; i < 3; i++) {
			listCompetenciasQueNaoPossui.add(new CompetenciaBean("Competencia "
					+ i));
		}
	}

	public List<CompetenciaBean> getListCompetenciasQueNaoPossui() {
		return listCompetenciasQueNaoPossui;
	}

	public void setListCompetenciasQueNaoPossui(
			List<CompetenciaBean> listCompetenciasQueNaoPossui) {
		this.listCompetenciasQueNaoPossui = listCompetenciasQueNaoPossui;
	}

	public List<CompetenciaBean> getListCompetenciasQuePossui() {
		return listCompetenciasQuePossui;
	}

	public void setListCompetenciasQuePossui(
			List<CompetenciaBean> listCompetenciasQuePossui) {
		this.listCompetenciasQuePossui = listCompetenciasQuePossui;
	}

	public DataModelCandidatoCompetencia getModelCompetenciasQueNaoPossui() {
		return modelCompetenciasQueNaoPossui;
	}

	public void setModelCompetenciasQueNaoPossui(
			DataModelCandidatoCompetencia modelCompetenciasQueNaoPossui) {
		this.modelCompetenciasQueNaoPossui = modelCompetenciasQueNaoPossui;
	}

	public DataModelCandidatoCompetencia getModelCompetenciasQuePossui() {
		return modelCompetenciasQuePossui;
	}

	public void setModelCompetenciasQuePossui(
			DataModelCandidatoCompetencia modelCompetenciasQuePossui) {
		this.modelCompetenciasQuePossui = modelCompetenciasQuePossui;
	}

	public CompetenciaBean getCompetenciaSelecionada() {
		return competenciaSelecionada;
	}

	public void setCompetenciaSelecionada(CompetenciaBean competenciaSelecionada) {
		this.competenciaSelecionada = competenciaSelecionada;
	}

	public CompetenciaBean getCompetenciaTemporaria() {
		return competenciaTemporaria;
	}

	public void setCompetenciaTemporaria(CompetenciaBean competenciaTemporaria) {
		this.competenciaTemporaria = competenciaTemporaria;
	}

}
