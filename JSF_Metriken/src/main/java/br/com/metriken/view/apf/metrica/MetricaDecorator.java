package br.com.metriken.view.apf.metrica;


public interface MetricaDecorator {

	void adicionaLinhaAbaixo(int index);

	void removeLinhaSelecionada(int index);

	void adicionaLinhas();
	
	void carregaLista();

	void gerarCRUD();
	
}
