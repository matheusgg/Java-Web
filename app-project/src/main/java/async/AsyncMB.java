package async;

import java.io.Serializable;
import java.util.concurrent.Future;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

@Named
@ViewScoped
public class AsyncMB implements Serializable {

	private static final long serialVersionUID = -4206761684718972049L;

	@Inject
	private AsyncBean asyncBean;

	public void test() {
		try {
			this.asyncBean.testVoidAsync();
			Future<Boolean> result = this.asyncBean.testRetornAsync();
			Messages.addInfo(null, result.get().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
