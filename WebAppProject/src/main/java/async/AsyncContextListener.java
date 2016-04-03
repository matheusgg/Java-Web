package async;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

public class AsyncContextListener implements AsyncListener {

	@Override
	public void onComplete(AsyncEvent event) throws IOException {
		System.out.println("AsyncContextListener.onComplete()");
	}

	@Override
	public void onTimeout(AsyncEvent event) throws IOException {
		System.out.println("AsyncContextListener.onTimeout()");
		// event.getAsyncContext().complete();
	}

	@Override
	public void onError(AsyncEvent event) throws IOException {
		System.out.println("AsyncContextListener.onError()");
	}

	@Override
	public void onStartAsync(AsyncEvent event) throws IOException {
		System.out.println("AsyncContextListener.onStartAsync()");
	}

}
