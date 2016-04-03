package listeners;

import java.util.logging.Logger;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class CustomActionListener implements ActionListener {

	private ActionListener listener;
	private Logger logger;

	public CustomActionListener(ActionListener listener) {
		this.listener = listener;
		this.logger = Logger.getLogger(this.getClass().getName());
	}

	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
		this.logger.info("Invoking Custom Action Listener...");
		this.listener.processAction(event);
	}

}
