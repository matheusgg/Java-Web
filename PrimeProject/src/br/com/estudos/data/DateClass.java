package br.com.estudos.data;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.primefaces.event.DateSelectEvent;

public class DateClass {
	private Date de;
	private Date ate;
	
	public void dataSelecionada(DateSelectEvent event){
		System.out.println("Data selecionada");
		Calendar cal = new GregorianCalendar();
		cal.setTime(event.getDate());
		
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		
	}

	public Date getDe() {
		return de;
	}

	public void setDe(Date de) {
		this.de = de;
	}

	public Date getAte() {
		return ate;
	}

	public void setAte(Date ate) {
		this.ate = ate;
	}
	
	

}
