package hotel;

import javax.faces.flow.FlowScoped;
import javax.inject.Named;

@Named
@FlowScoped("hotel")
public class HotelBean {
	private String nomeHotel;
	private String detalhes;

	public HotelBean() {
		System.out.println("HotelBean");
	}

	/**
	 * @return the nomeHotel
	 */
	public String getNomeHotel() {
		return nomeHotel;
	}

	/**
	 * @param nomeHotel
	 *            the nomeHotel to set
	 */
	public void setNomeHotel(String nomeHotel) {
		this.nomeHotel = nomeHotel;
	}

	/**
	 * @return the detalhes
	 */
	public String getDetalhes() {
		return detalhes;
	}

	/**
	 * @param detalhes
	 *            the detalhes to set
	 */
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

}
