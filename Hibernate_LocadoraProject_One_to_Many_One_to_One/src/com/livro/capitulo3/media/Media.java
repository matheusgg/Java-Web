package com.livro.capitulo3.media;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.livro.capitulo3.filme.Filme;

@Entity
@Table(name = "midia")
public class Media implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "cod_midia")
	private int media;

	@ManyToOne
	@JoinColumn(name = "cod_filme")
	private Filme filme;

	@Column(name = "inutilizada")
	private String inutilizado;

	public int getMedia() {
		return media;
	}

	public void setMedia(int media) {
		this.media = media;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public String getInutilizado() {
		return inutilizado;
	}

	public void setInutilizado(String inutilizado) {
		this.inutilizado = inutilizado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filme == null) ? 0 : filme.hashCode());
		result = prime * result
				+ ((inutilizado == null) ? 0 : inutilizado.hashCode());
		result = prime * result + media;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Media other = (Media) obj;
		if (filme == null) {
			if (other.filme != null)
				return false;
		} else if (!filme.equals(other.filme))
			return false;
		if (inutilizado == null) {
			if (other.inutilizado != null)
				return false;
		} else if (!inutilizado.equals(other.inutilizado))
			return false;
		if (media != other.media)
			return false;
		return true;
	}

}
