package dynamic;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class DynamicServletRequestListener implements ServletRequestListener {

	public DynamicServletRequestListener() {
		this.getClass();
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("DynamicHttpServletRequestListener.requestDestroyed()");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("DynamicHttpServletRequestListener.requestInitialized()");
	}

}
