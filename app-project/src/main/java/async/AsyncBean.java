package async;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class AsyncBean {

	@Asynchronous
	public void testVoidAsync() throws InterruptedException {
		TimeUnit.SECONDS.sleep(5);
	}

	@Asynchronous
	public Future<Boolean> testRetornAsync() throws InterruptedException {
		TimeUnit.SECONDS.sleep(5);
		return new AsyncResult<Boolean>(true);
	}

}
