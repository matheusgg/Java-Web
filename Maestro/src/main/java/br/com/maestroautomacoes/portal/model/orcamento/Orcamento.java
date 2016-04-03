package br.com.maestroautomacoes.portal.model.orcamento;

import java.io.Serializable;
import javax.persistence.*;

import br.com.maestroautomacoes.portal.enums.orcamento.StatusOrcamento;
import br.com.maestroautomacoes.portal.model.usuario.Usuario;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the tb_orcamento database table.
 * 
 */
@Entity
@Table(name = "tb_orcamento")
public class Orcamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_orcamento", unique = true, nullable = false)
	private Integer idOrcamento;

	@Column(name = "codigo_orcamento", nullable = false, length = 255)
	private String codigoOrcamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_entrada_orcamento", nullable = false)
	private Date dataEntradaOrcamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_ultima_atualizacao_orcamento")
	private Date dataUltimaAtualizacaoOrcamento;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private StatusOrcamento status;

	// bi-directional many-to-one association to ItensDoOrcamento
	@OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL)
	private List<ItensDoOrcamento> itensDoOrcamento;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	public Orcamento() {
	}

	public int getValorOrcamento() {
		return 1000;
	}

	public Integer getIdOrcamento() {
		return this.idOrcamento;
	}

	public void setIdOrcamento(Integer idOrcamento) {
		this.idOrcamento = idOrcamento;
	}

	public String getCodigoOrcamento() {
		return this.codigoOrcamento;
	}

	public void setCodigoOrcamento(String codigoOrcamento) {
		this.codigoOrcamento = codigoOrcamento;
	}

	public Date getDataEntradaOrcamento() {
		return this.dataEntradaOrcamento;
	}

	public void setDataEntradaOrcamento(Date dataEntradaOrcamento) {
		this.dataEntradaOrcamento = dataEntradaOrcamento;
	}

	public Date getDataUltimaAtualizacaoOrcamento() {
		return this.dataUltimaAtualizacaoOrcamento;
	}

	public void setDataUltimaAtualizacaoOrcamento(Date dataUltimaAtualizacaoOrcamento) {
		this.dataUltimaAtualizacaoOrcamento = dataUltimaAtualizacaoOrcamento;
	}

	public StatusOrcamento getStatus() {
		return this.status;
	}

	public void setStatus(StatusOrcamento status) {
		this.status = status;
	}

	public List<ItensDoOrcamento> getItensDoOrcamento() {
		return this.itensDoOrcamento;
	}

	public void setItensDoOrcamento(List<ItensDoOrcamento> itensDoOrcamento) {
		this.itensDoOrcamento = itensDoOrcamento;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}