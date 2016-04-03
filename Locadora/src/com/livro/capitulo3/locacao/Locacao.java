package com.livro.capitulo3.locacao;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.livro.capitulo3.cliente.Cliente;
import com.livro.capitulo3.media.Media;

@Entity
@Table(name = "locacao")
public class Locacao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "cod_locacao")
	private int locacao;

	@ManyToOne
	@JoinColumn(name = "cod_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "cod_midia")
	private Media media;

	/*
	 * Updatable indica que este campo não pode ser alterado
	 */
	@Column(name = "data_emprestimo", updatable = false)
	private Date dataEmprestimo;

	@Column(name = "hora_emprestimo", updatable = false)
	private Time horaEmprestimo;

	@Column(name = "data_devolucao")
	private Date dataDevolucao;

	@Column(name = "obs")
	private String observacao;

	public int getLocacao() {
		return locacao;
	}

	public void setLocacao(int locacao) {		
		this.locacao = locacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Time getHoraEmprestimo() {
		return horaEmprestimo;
	}

	public void setHoraEmprestimo(Time horaEmprestimo) {
		this.horaEmprestimo = horaEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
