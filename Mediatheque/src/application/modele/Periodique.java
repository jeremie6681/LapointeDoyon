package application.modele;

import java.util.Date;

public class Periodique extends Document {
	
	private int intNoVolume;
	private int intNoPeriodique;
	
	public Periodique(String strCodeDocument, String strTitre, Date dateParution, Etat etatDoc, int intNoVolume, int intNoPeriodique) {
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
