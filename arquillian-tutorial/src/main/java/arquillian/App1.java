package arquillian;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class App1 implements Serializable {

	private static final long serialVersionUID = 6317514320048345797L;

	public String createMessage() {
		return "Message!";
	}

}
