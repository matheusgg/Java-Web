package listeners;

import java.io.IOException;

import javax.servlet.WriteListener;

public class CustomWriterListener implements WriteListener {

	@Override
	public void onWritePossible() throws IOException {
		System.out.println("CustomWriterListener.onWritePossible()");
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println("CustomWriterListener.onError()");
	}

}
