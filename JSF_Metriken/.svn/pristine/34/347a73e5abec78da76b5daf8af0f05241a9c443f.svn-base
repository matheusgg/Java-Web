package br.com.metriken.model.nopersistence.apf;

import java.io.Serializable;


public class Projeto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1571223214854251718L;
	
	private long   codigo;
	private String Descricao;
	
	private TipoContagem tipoContagem;	
	private Responsavel  responsavel;	
	private Consideracao consideracao;
	private Contagem     contagem;
	private TipoProjeto  tipoProjeto;
	
	
	public Projeto(){
		this.tipoContagem = new TipoContagem();
		this.responsavel  = new Responsavel();
		this.consideracao = new Consideracao();
		this.contagem     = new Contagem();
		this.tipoProjeto  = new TipoProjeto();
	}
	
	
	
	public Contagem getContagem() {
		return contagem;
	}


	public void setContagem(Contagem contagem) {
		this.contagem = contagem;
	}
		
	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return Descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((Descricao == null) ? 0 : Descricao.hashCode());
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (Descricao == null) {
			if (other.Descricao != null)
				return false;
		} else if (!Descricao.equals(other.Descricao))
			return false;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	/**
	 * @return the tipoContagem
	 */
	public TipoContagem getTipoContagem() {
		return tipoContagem;
	}
	/**
	 * @param tipoContagem the tipoContagem to set
	 */
	public void setTipoContagem(TipoContagem tipoContagem) {
		this.tipoContagem = tipoContagem;
	}
	/**
	 * @return the responsavel
	 */
	public Responsavel getResponsavel() {
		return responsavel;
	}
	/**
	 * @param responsavel the responsavel to set
	 */
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
	/**
	 * @return the consideracao
	 */
	public Consideracao getConsideracao() {
		return consideracao;
	}
	/**
	 * @param consideracao the consideracao to set
	 */
	public void setConsideracao(Consideracao consideracao) {
		this.consideracao = consideracao;
	}



	/**
	 * @return the tipoProjeto
	 */
	public TipoProjeto getTipoProjeto() {
		return tipoProjeto;
	}



	/**
	 * @param tipoProjeto the tipoProjeto to set
	 */
	public void setTipoProjeto(TipoProjeto tipoProjeto) {
		this.tipoProjeto = tipoProjeto;
	}

}
