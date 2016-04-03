package ejb;

import javax.ejb.Stateless;

@Stateless
public class AppBean2 {

	private AppBean2 appBean;

	@Override
	public String toString() {
		return super.getClass().getSimpleName();
	}

	/**
	 * @return the appBean
	 */
	public AppBean2 getAppBean() {
		return appBean;
	}

	/**
	 * @param appBean
	 *            the appBean to set
	 */
	public void setAppBean(AppBean2 appBean) {
		this.appBean = appBean;
	}
}
