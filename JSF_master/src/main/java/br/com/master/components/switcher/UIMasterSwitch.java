package br.com.master.components.switcher;

import javax.faces.component.FacesComponent;
import javax.faces.component.UISelectBoolean;

@FacesComponent("master.switch")
public class UIMasterSwitch extends UISelectBoolean {

	public UIMasterSwitch() {
		super.setRendererType("master.switch");
	}

}
