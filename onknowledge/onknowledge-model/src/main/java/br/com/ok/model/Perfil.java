package br.com.ok.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.security.core.GrantedAuthority;

import br.com.ok.model.enums.PerfilUsuario;

/**
 * The persistent class for the tb_perfil database table.
 * 
 */
@Entity
@Table(name = "tb_perfil")
@NamedQueries({ @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
		@NamedQuery(name = "Perfil.findByDescricao", query = "select p from Perfil p where p.descricao = :descricao") })
@Data
@EqualsAndHashCode(of = "id")
public class Perfil implements GrantedAuthority, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4295734419492740451L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil")
	private Integer id;

	/** The descricao. */
	@Column(name = "ds_perfil")
	@Enumerated(EnumType.STRING)
	private PerfilUsuario descricao;

	/** The ativo. */
	@Column(name = "fl_ativo")
	private Boolean ativo;

	@Transient
	@Override
	public String getAuthority() {
		return this.descricao.name();
	}

}