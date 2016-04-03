package cdi;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Agora e possivel especificar a prioridade do interceptor
 * 
 * @author Matheus
 * 
 */
@Priority(Interceptor.Priority.APPLICATION)
@Invoke
@Interceptor
public class Interceptador {

	@AroundInvoke
	public Object invoke(InvocationContext ctx) throws Exception {
		return ctx.proceed();
	}

}
