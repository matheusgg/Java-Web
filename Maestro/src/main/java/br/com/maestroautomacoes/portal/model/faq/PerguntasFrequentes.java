package br.com.maestroautomacoes.portal.model.faq;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_faq database table.
 * 
 */
@Entity
@Table(name="tb_faq")
public class PerguntasFrequentes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_faq", unique=true, nullable=false)
	private int idFaq;

	@Column(length=500)
	private String descricao;

	@Column(length=255)
	private String titulo;

    public PerguntasFrequentes() {
    }

	public int getIdFaq() {
		return this.idFaq;
	}

	public void setIdFaq(int idFaq) {
		this.idFaq = idFaq;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}