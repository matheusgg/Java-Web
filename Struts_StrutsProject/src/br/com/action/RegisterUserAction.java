package br.com.action;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.beans.UserBean;

public class RegisterUserAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserBean bean = (UserBean) form;

		boolean emailValido = this.isEmailValido(bean.getEmail());

		if (emailValido) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("badaddress");
		}

	}

	private boolean isEmailValido(String email) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9\\._-]+@[a-zA-Z]+\\.[a-zA-Z]+");
		return pattern.matcher(email).matches();
	}
}
