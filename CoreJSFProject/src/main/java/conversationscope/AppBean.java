package conversationscope;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Para utilizar o escopo de Conversacao padrao da especificacao Java EE e
 * necessario anotar a classe com @ConversationScoped, alem disso, e preciso
 * criar uma instancia da classe Conversation que sera injetada pelo container.
 * Na instancia injetada, e preciso invocar o metodo begin para elevar o bean
 * para o escopo de conversacao, caso contrario, o mesmo possuira o escopo de
 * requisicao.
 * 
 * @author Matheus
 * 
 */
@Named
@ConversationScoped
/*
 * @ViewScoped - A nova anotacao ViewScoped do JSF 2.2 faz a mesma tarefa do
 * ConversationScoped sem a necessidade de injecao de uma instancia de
 * Conversation
 */
public class AppBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7584330689090699003L;

	@Inject
	private Conversation conversation;

	private String welcomeMessege;
	private Float valor;
	private Integer pontos;

	public AppBean() {
		this.welcomeMessege = "Welcome to Java Server Faces 2.2";
		this.valor = 350f;
		this.pontos = 1;
		FacesContext.getCurrentInstance().getExternalContext().log("Construtor");
	}

	/**
	 * E precisso iniciar a conversacao para que o escopo tenha efeito.
	 */
	@PostConstruct
	public void init() {
		this.conversation.begin();
		FacesContext.getCurrentInstance().getExternalContext().log("PostConstrutor");
	}

	public void incrementPoint() {
		this.pontos++;
	}

	/**
	 * @return the welcomeMessege
	 */
	public String getWelcomeMessege() {
		return welcomeMessege;
	}

	/**
	 * @param welcomeMessege
	 *            the welcomeMessege to set
	 */
	public void setWelcomeMessege(String welcomeMessege) {
		this.welcomeMessege = welcomeMessege;
	}

	/**
	 * @return the valor
	 */
	public Float getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(Float valor) {
		this.valor = valor;
	}

	/**
	 * @return the pontos
	 */
	public Integer getPontos() {
		return pontos;
	}

	/**
	 * @param pontos
	 *            the pontos to set
	 */
	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

}
