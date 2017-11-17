package application.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;

public class Livre extends Document implements Serializable{
	
	private static int intNoDocs;//= ouRenduNoPersonnes();
	
	private static final long serialVersionUID = -4268118706365795753L;
	private String strAuteur;
	
	public Livre(String strNoCodeDocument, String strTitre, LocalDate dateParution, Etat etatDoc, String strAuteur) {
		super(strNoCodeDocument,strTitre, dateParution, etatDoc);
		this.strAuteur=strAuteur;
		
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
	public static void ouRenduNo() {
		
		int intNoDoc=1;
		if (intNoDocs!=1) {
		String strNo;
		Collections.sort(ListeDocuments.getInstance().mapDocument.get(TypeDocument.Livre));
		strNo =ListeDocuments.getInstance().mapDocument.get(TypeDocument.Livre).get((ListeDocuments.getInstance().mapDocument.get(TypeDocument.Livre)).size()-1).getStrCodeDocument();
		strNo= strNo.toUpperCase().replace(TypeDocument.Livre.getStrIndicateurType().toUpperCase(),"");
		intNoDoc= Integer.parseInt(strNo)+1;
		}
		intNoDocs= intNoDoc;
	}

}
