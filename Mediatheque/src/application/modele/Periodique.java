package application.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Periodique extends Document implements Serializable {
	

	private static final long serialVersionUID = 3408058369694863163L;
	private int intNoVolume;
	private int intNoPeriodique;
	
	public Periodique(String strCodeDocument, String strTitre, LocalDate dateParution, Etat etatDoc, int intNoVolume, int intNoPeriodique) {
		super(strCodeDocument, strTitre, dateParution, etatDoc);
		this.intNoVolume=intNoVolume;
		this.intNoPeriodique=intNoPeriodique;
	}

	public int getIntNoVolume() {
		return intNoVolume;
	}

	public void setIntNoVolume(int intNoVolume) {
		this.intNoVolume = intNoVolume;
	}

	public int getIntNoPeriodique() {
		return intNoPeriodique;
	}

	public void setIntNoPeriodique(int intNoPeriodique) {
		this.intNoPeriodique = intNoPeriodique;
	}
}
