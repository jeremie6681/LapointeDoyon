package application.modele;

import java.util.Date;

public class DVD extends Document {
	
	private short shNbDisques;
	private String strResalisateur;
	
	public DVD(int intNoDoc, String strTitre, Date dateParution, Etat etatDoc,short shNbDisque,String strResalisateur) {
		super(intNoDoc, strTitre, dateParution, etatDoc);
		this.shNbDisques=shNbDisque;
		this.strResalisateur= strResalisateur;
	}
}
