package application.modele;

import java.util.Date;

public class Periodique extends Document {
	
	private int intNoVolume;
	private int intNoPeriodique;
	
	public Periodique(int intNoDoc, String strTitre, Date dateParution, Etat etatDoc) {
		super(intNoDoc, strTitre, dateParution, etatDoc);
		this.intNoVolume=intNoVolume;
		this.intNoPeriodique=intNoPeriodique;
	}
}
