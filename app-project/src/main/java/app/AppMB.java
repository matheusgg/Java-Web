package app;

import java.io.Serializable;
import java.util.Set;

import javax.faces.component.html.HtmlOutcomeTargetButton;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;

@Named
@ViewScoped
public class AppMB implements Serializable {

	private static final long serialVersionUID = 7185848515628686212L;

	public void constructURLs(ComponentSystemEvent event) {
		HtmlPanelGroup panel = (HtmlPanelGroup) event.getComponent().findComponent("panel");
		Set<String> paths = Faces.getResourcePaths("/");

		paths.stream().filter(path -> path.endsWith(".xhtml") && !path.contains("index.xhtml")).forEach(path -> {
			HtmlOutcomeTargetButton btn = new HtmlOutcomeTargetButton();
			String value = path.replace(".xhtml", "").replace("/", "");
			btn.setValue(value);
			btn.setOutcome(value);
			panel.getChildren().add(btn);
		});
	}
}
