package application.modele;

import java.io.Serializable;
import java.util.Date;

public class Livre extends Document implements Serializable{
	
	private static final long serialVersionUID = -4268118706365795753L;
	private String strAuteur;
	
	public Livre(String strCodeDocument, String strTitre, Date dateParution, Etat etatDoc, String strAuteur) {
		super(strCodeDocument, strTitre, dateParution, etatDoc);
		this.strAuteur=strAuteur;
	}

	public String getStrAuteur() {
		return strAuteur;
	}

	public void setStrAuteur(String strAuteur) {
		this.strAuteur = strAuteur;
	}

}
