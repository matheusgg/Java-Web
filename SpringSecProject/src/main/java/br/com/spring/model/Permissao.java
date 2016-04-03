package br.com.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@Table(name = "permissao")
public class Permissao implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1617525836148983578L;

	@Id
	@GeneratedValue
	private Long id;
	private String authority;

	@Override
	public String getAuthority() {
		return this.authority;
	}

}
