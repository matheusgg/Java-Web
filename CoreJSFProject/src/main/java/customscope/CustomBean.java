package customscope;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;

/**
 * Para criar um escopo personalizado, basta anotar o bean com CustomScoped e
 * informar o nome do escopo entro de uma El.
 * 
 * @author Matheus
 * 
 */
@ManagedBean
// Nome do escopo personalizado
@CustomScoped("#{custom}")
public class CustomBean {

}
