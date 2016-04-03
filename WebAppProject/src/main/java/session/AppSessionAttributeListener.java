package session;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

@WebListener
public class AppSessionAttributeListener implements HttpSessionAttributeListener, HttpSessionBindingListener {

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("AppSessionAttribute.valueBound()");
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("AppSessionAttribute.valueUnbound()");
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("AppSessionAttribute.attributeAdded()");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("AppSessionAttribute.attributeRemoved()");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("AppSessionAttribute.attributeReplaced()");
	}

}
