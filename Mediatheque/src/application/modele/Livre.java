package application.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Livre extends Document implements Serializable{
	
	private static int intNoDocs= 1;
	
	private static final long serialVersionUID = -4268118706365795753L;
	private String strAuteur;
	
	public Livre(String strNoCodeDocument, String strTitre, LocalDate dateParution, Etat etatDoc, String strAuteur) {
		super(strNoCodeDocument,strTitre, dateParution, etatDoc);
		this.strAuteur=strAuteur;
		intNoDocs++;
	}
	public Livre(String strTitre, LocalDate dateParution, String strAuteur) {
		super(setCodeDocument(),strTitre, dateParution, Etat.DISPONIBLE);
		this.strAuteur=strAuteur;
		intNoDocs++;
	}

	public String getStrAuteur() {
		return strAuteur;
	}

	public void setStrAuteur(String strAuteur) {
		this.strAuteur = strAuteur;
	}

	public  TypeDocument getTypeDocument() {
		return TypeDocument.Livre;
	}
	private static String setCodeDocument() {
		String strCodeDoc="";
		if(intNoDocs<10) {
		strCodeDoc=TypeDocument.Livre.getStrIndicateurType()+"0"+intNoDocs;
		}
		else {
		strCodeDoc=TypeDocument.Livre.getStrIndicateurType()+intNoDocs;
		}
		return strCodeDoc;
	}

}
