package navigation;

import java.io.Serializable;

import javax.faces.application.NavigationHandler;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;

@Named
@ViewScoped
public class NavigationMB implements Serializable {

	private static final long serialVersionUID = 4852495852352007574L;

	public String testNavigation() {
		return "nav2";
	}

	public void navigate() {
		NavigationHandler nh = Faces.getApplication().getNavigationHandler();
		nh.handleNavigation(Faces.getContext(), null, "navigation2?faces-redirect=true");
	}

}
