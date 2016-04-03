package security.mapper;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import security.model.Usuario;

public class UserAttributesMapper implements AttributesMapper<Usuario> {

	@Override
	public Usuario mapFromAttributes(Attributes attributes) throws NamingException {
		Usuario usuario = new Usuario();
		NamingEnumeration<? extends Attribute> attrs = attributes.getAll();
		while (attrs.hasMoreElements()) {
			usuario.setNome((String) attrs.next().get());
		}
		return usuario;
	}

}
