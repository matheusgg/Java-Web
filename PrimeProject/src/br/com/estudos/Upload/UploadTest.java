package br.com.estudos.Upload;

public class UploadTest {
	private String caminhoCurriculo;
	private String caminhoCurriculoTemporario;

	public void verificaAlteracaoCurriculo() {
		if (this.caminhoCurriculo.equals("")) {
			this.deletaCurriculoSalvo();
			this.caminhoCurriculoTemporario = "";
		} else if (this.caminhoCurriculo != this.caminhoCurriculoTemporario) {
			this.deletaCurriculoSalvo();
			this.preparaCaminhoCurriculo();
			this.salvaCurriculoAnexado();
			this.caminhoCurriculoTemporario = this.caminhoCurriculo;
		}
	}

	public void deletaCurriculoSalvo() {
		
	}

	public void salvaCurriculoAnexado() {

	}

	public void preparaCaminhoCurriculo() {

	}

	public String getCaminhoCurriculo() {
		return caminhoCurriculo;
	}

	public void setCaminhoCurriculo(String caminhoCurriculo) {
		this.caminhoCurriculo = caminhoCurriculo;
	}

	public String getCaminhoCurriculoTemporario() {
		return caminhoCurriculoTemporario;
	}

	public void setCaminhoCurriculoTemporario(String caminhoCurriculoTemporario) {
		this.caminhoCurriculoTemporario = caminhoCurriculoTemporario;
	}

}
