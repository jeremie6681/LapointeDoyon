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
}
