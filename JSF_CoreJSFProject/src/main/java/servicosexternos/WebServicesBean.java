package servicosexternos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.cdyne.ws.WeatherWS.ForecastReturn;
import com.cdyne.ws.WeatherWS.WeatherLocator;
import com.cdyne.ws.WeatherWS.WeatherSoap;

@Named
@ViewScoped
public class WebServicesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6802539108216950069L;

	private ForecastReturn forecastReturn;

	@PostConstruct
	public void consume() {
		try {
			WeatherLocator locator = new WeatherLocator();
			WeatherSoap soap = locator.getWeatherSoap();
			this.forecastReturn = soap.getCityForecastByZIP("90012");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().getExternalContext().log(e.getMessage());
		}
	}

	/**
	 * @return the forecastReturn
	 */
	public ForecastReturn getForecastReturn() {
		return forecastReturn;
	}

	/**
	 * @param forecastReturn
	 *            the forecastReturn to set
	 */
	public void setForecastReturn(ForecastReturn forecastReturn) {
		this.forecastReturn = forecastReturn;
	}

}
