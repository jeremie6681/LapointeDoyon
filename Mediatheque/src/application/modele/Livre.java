package application.modele;

import java.util.Date;

public class Livre extends Document{
	
	private String strAuteur;
	
	public Livre(int intNoDoc, String strTitre, Date dateParution, Etat etatDoc, String strAuteur) {
		super(intNoDoc, strTitre, dateParution, etatDoc);
		this.strAuteur=strAuteur;
	}

}
