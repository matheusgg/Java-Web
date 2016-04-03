import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


public class DadosUsuario implements Validator{ //interface que precisa ser implementada para a valida��o
	/* Objetos que representam os componentes da JSF */
	private UIInput nomeTextField;
	private UIInput emailTextField;
	private UIInput telefoneTextField;
	
	/* Atributo email */
	private String email;

	/* Getters e Setters */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UIInput getNomeTextField() {
		return nomeTextField;
	}

	public void setNomeTextField(UIInput nomeTextField) {
		this.nomeTextField = nomeTextField;
	}

	public UIInput getEmailTextField() {
		return emailTextField;
	}

	public void setEmailTextField(UIInput emailTextField) {
		this.emailTextField = emailTextField;
	}

	public UIInput getTelefoneTextField() {
		return telefoneTextField;
	}

	public void setTelefoneTextField(UIInput telefoneTextField) {
		this.telefoneTextField = telefoneTextField;
	}

	/*===================== M�todo Validador =====================*/
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String email = String.valueOf(value); //Aqui o valor � adquirido
		System.out.println("Dentro do Validator!" + email);
		
		if(!email.contains("@")){//compara��o
			//se o email estiver incorreto, um ValidatorException � lan�ado com uma 
			//FacesMessage que ser� capturada pela tag <message> na jsf
			throw new ValidatorException(new FacesMessage("Email inv�lido!\nPor Favor, entre com um email v�lido!"));
		}else{
			//caso o email esteja correto, o valor do campo � atribu�do ao atributo email
			this.email = email;
			System.out.println(this.email);
		}
	}
}
