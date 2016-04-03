package upgrade;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.WebConnection;

import listeners.CustomWriterListener;

public class CustomUpgradeHandler implements HttpUpgradeHandler {

	@Override
	public void init(WebConnection connection) {
		System.out.println("CustomUpgradeHandler.init()");
		try {
			ServletOutputStream os = connection.getOutputStream();
			os.setWriteListener(new CustomWriterListener());
			os.println("Protocol upgraded!!!!!");
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		System.out.println("CustomUpgradeHandler.destroy()");
	}

}
