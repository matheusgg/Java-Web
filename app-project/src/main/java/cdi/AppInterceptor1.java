package cdi;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Intercept
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class AppInterceptor1 implements Serializable {

	private static final long serialVersionUID = 125622303023075515L;

	@AroundInvoke
	public Object intercept(InvocationContext invocationContext) {
		System.out.println("Starting intercepting...");
		Object value = null;
		try {
			value = invocationContext.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
