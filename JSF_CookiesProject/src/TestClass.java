import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestClass {
	private Cookie cookie;
	private Cookie[] cookiesUsuario;
	
	public String verificaCookies(){	
		cookie = new Cookie("MeuprimeiroCookie", "Chupa!");
		cookie.setMaxAge(1);
		cookiesUsuario = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getCookies();
		if(cookiesUsuario != null){
			return "sucesso";
		}else{
			((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).addCookie(cookie);
			return "erro";
		}	
	}
}
