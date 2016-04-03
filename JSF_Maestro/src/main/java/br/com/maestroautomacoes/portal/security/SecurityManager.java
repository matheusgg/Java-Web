package br.com.maestroautomacoes.portal.security;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.maestroautomacoes.portal.logger.LogManager;
import br.com.maestroautomacoes.portal.model.dao.usuario.UsuarioDaoImpl;
import br.com.maestroautomacoes.portal.model.dao.util.AbstractDao;
import br.com.maestroautomacoes.portal.model.dao.util.HibernateUtil;
import br.com.maestroautomacoes.portal.model.usuario.Usuario;
import br.com.maestroautomacoes.portal.util.SiteUtil;

@Component
public class SecurityManager implements AuthenticationManager {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = String.valueOf(authentication.getPrincipal());
		String senha = String.valueOf(authentication.getCredentials());

		// Consulta na base de dados
		Usuario usuario = this.recuperaUsuario(email, senha);

		List<SimpleGrantedAuthority> permissoes = new ArrayList<SimpleGrantedAuthority>();
		if (usuario == null) {
			throw new UsernameNotFoundException(SiteUtil.getMessageFromProperty("msg_usuario_senha_invalida"));
		}

		// Adiciona as permissões do usuário
		permissoes.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		permissoes.add(new SimpleGrantedAuthority("ROLE_USER"));
		usuario.setPermissoes(permissoes);

		// Autentica o usuário no contexto de segurança do SpringSecurity
		authentication = new UsernamePasswordAuthenticationToken(usuario.getNome(), usuario, usuario.getPermissoes());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return authentication;
	}

	public boolean login(String email, String senha) {
		boolean logado = false;
		if (email.trim().length() > 0 && senha.trim().length() > 0) {
			try {
				Authentication auth = this.authenticate(new UsernamePasswordAuthenticationToken(email, senha));
				SiteUtil.addAttibuteInHttpSession("loginUsuarioLogado", email);
				logado = auth.isAuthenticated();
			} catch (UsernameNotFoundException e) {
				SiteUtil.addGrowlMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR, false);
				String mensagem = LogManager.makeStackTraceLog(e);
				LogManager.getLogger().error(mensagem);
			}
		}
		return logado;
	}

	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).removeAttribute("loginUsuarioLogado");
	}

	public static Usuario getUsuarioLogado() throws Exception {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
	}

	@SuppressWarnings("unchecked")
	private Usuario recuperaUsuario(String email, String senha) {
		UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
		Class<UsuarioDaoImpl> usuarioDaoClass = (Class<UsuarioDaoImpl>) usuarioDao.getClass();
		Class<AbstractDao<?>> abstractDaoClass = (Class<AbstractDao<?>>) usuarioDaoClass.getSuperclass();
		Session session = HibernateUtil.getSessionfactory().openSession();
		Usuario usuario = null;
		try {
			Field sessaoField = abstractDaoClass.getDeclaredField("sessao");
			sessaoField.setAccessible(true);
			sessaoField.set(usuarioDao, session);
			usuario = usuarioDao.recuperaUsuario(email, senha);
			sessaoField.setAccessible(false);
		} catch (Exception e) {
			String mensagem = LogManager.makeStackTraceLog(e);
			LogManager.getLogger().error(mensagem);
		} finally {
			session.close();
		}
		return usuario;
	}
}
