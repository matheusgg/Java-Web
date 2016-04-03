package br.com.estudos.tabela;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.SelectEvent;

public class TabelaBean {  
    private List<Carros> carsSmall;        
    private Carros selectedCar;    
    private Modelo mediumCarsModel; 
    private String palavra;
  
    public TabelaBean() {  
        carsSmall = new ArrayList<Carros>();  
        carsSmall.add(new Carros("Carro 1", "15000"));
        carsSmall.add(new Carros("Carro 2", "15000"));
  
        mediumCarsModel = new Modelo(carsSmall);  
    }

	public List<Carros> getCarsSmall() {
		return carsSmall;
	}

	public void setCarsSmall(List<Carros> carsSmall) {
		this.carsSmall = carsSmall;
	}

	public Carros getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(Carros selectedCar) {
		this.selectedCar = selectedCar;
	}

	public Modelo getMediumCarsModel() {
		return mediumCarsModel;
	}

	public void setMediumCarsModel(Modelo mediumCarsModel) {
		this.mediumCarsModel = mediumCarsModel;
	}  
	
	public void onRowSelect(SelectEvent event) {
		System.out.println(this.selectedCar.getNome());
    }

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}
}
