package concurrency;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Named
@ViewScoped
public class ConcurrencyMB implements Serializable {

	private static final long serialVersionUID = 6347615041967938674L;

	@Resource(lookup = "java:comp/DefaultManagedExecutorService")
	private ManagedExecutorService mes;

	@Resource(lookup = "java:comp/UserTransaction")
	private UserTransaction transaction;

	@Resource(lookup = "java:comp/DefaultManagedScheduledExecutorService")
	private ManagedScheduledExecutorService mses;

	@Resource(lookup = "java:comp/DefaultManagedThreadFactory")
	private ManagedThreadFactory mtf;

	@PostConstruct
	public void init() {
		try {

			System.out.println(InitialContext.<ManagedExecutorService>doLookup("java:comp/DefaultManagedExecutorService"));
			System.out.println(InitialContext.<UserTransaction>doLookup("java:comp/UserTransaction"));
			System.out.println(InitialContext.<ManagedScheduledExecutorService>doLookup("java:comp/DefaultManagedScheduledExecutorService"));
			System.out.println(InitialContext.<ManagedThreadFactory>doLookup("java:comp/DefaultManagedThreadFactory"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runTask() {
		this.mes.execute(new CustomTask());
	}

	public void schedule() {
		// this.mses.schedule(new CustomTask(), new Trigger() {
		//
		// @Override
		// public boolean skipRun(LastExecution lastExecutionInfo, Date
		// scheduledRunTime) {
		// return new Date().after(scheduledRunTime);
		// }
		//
		// @Override
		// public Date getNextRunTime(LastExecution lastExecutionInfo, Date
		// taskScheduledTime) {
		// ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2014,
		// Month.DECEMBER, 8, 15, 14), ZoneId.of("GMT"));
		// return Date.from(zonedDateTime.toInstant());
		// }
		// });

		// this.mses.schedule(new CustomTask(), 5, TimeUnit.SECONDS);

		this.mses.scheduleAtFixedRate(new CustomTask(), 5, 2, TimeUnit.SECONDS);
	}

	/**
	 * @return the mes
	 */
	public ManagedExecutorService getMes() {
		return mes;
	}

	/**
	 * @param mes
	 *            the mes to set
	 */
	public void setMes(ManagedExecutorService mes) {
		this.mes = mes;
	}

	/**
	 * @return the transaction
	 */
	public UserTransaction getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction
	 *            the transaction to set
	 */
	public void setTransaction(UserTransaction transaction) {
		this.transaction = transaction;
	}

	/**
	 * @return the mses
	 */
	public ManagedScheduledExecutorService getMses() {
		return mses;
	}

	/**
	 * @param mses
	 *            the mses to set
	 */
	public void setMses(ManagedScheduledExecutorService mses) {
		this.mses = mses;
	}

	/**
	 * @return the mtf
	 */
	public ManagedThreadFactory getMtf() {
		return mtf;
	}

	/**
	 * @param mtf
	 *            the mtf to set
	 */
	public void setMtf(ManagedThreadFactory mtf) {
		this.mtf = mtf;
	}

}
