package customscope;

import java.util.concurrent.ConcurrentHashMap;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Mapa customizado do escopo personalizado.
 * 
 * @author Matheus
 * 
 */
public class CustomScopeMap extends ConcurrentHashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7015671878220949588L;
	public static String SCOPE_NAME;
	private static String MAP_ID;

	static {
		CustomScopeMap.SCOPE_NAME = "custom";
		CustomScopeMap.MAP_ID = "customScopeMap";
	}

	private CustomScopeMap() {

	}

	/**
	 * Recupera uma intancia do mapa do escopo personalizado. Este metodo
	 * verifica se ja existe uma instancia do mapa deste escopo na sessao, caso
	 * positivo devolve esta instancia.
	 * 
	 * @return
	 */
	public static CustomScopeMap getInstance() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		
		if(session == null){
			session = (HttpSession) facesContext.getExternalContext().getSession(true);
		}
		
		CustomScopeMap map = (CustomScopeMap) session.getAttribute(CustomScopeMap.MAP_ID);

		if (map == null) {
			map = new CustomScopeMap();
			session.setAttribute(CustomScopeMap.MAP_ID, map);
		}

		return map;
	}

}
