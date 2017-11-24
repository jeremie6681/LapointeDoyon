package application.modele;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.collections.FXCollections;

public class DVD extends Document implements Serializable{
	
	private static int intNoDocs;//= ouRenduNoPersonnes();
	private static final long serialVersionUID = -1678710002762416560L;
	private short shNbDisques;
	private String strResalisateur;
	
	public DVD(String strCodeDocument, String strTitre, LocalDate dateParution, Etat etatDoc,short shNbDisque,String strResalisateur) {
		super(strCodeDocument, strTitre, dateParution, etatDoc);
		this.shNbDisques=shNbDisque;
		this.strResalisateur= strResalisateur;
		
	}
	public DVD(String strTitre, LocalDate dateParution,short shNbDisque,String strResalisateur) {
		super(setCodeDocument(), strTitre, dateParution, Etat.DISPONIBLE);
		this.shNbDisques=shNbDisque;
		this.strResalisateur= strResalisateur;intNoDocs++;
	}

	public short getShNbDisques() {
		return shNbDisques;
	}

	public void setShNbDisques(short shNbDisques) {
		this.shNbDisques = shNbDisques;
	}

	public String getStrResalisateur() {
		return strResalisateur;
	}

	public void setStrResalisateur(String strResalisateur) {
		this.strResalisateur = strResalisateur;
	}
	public  TypeDocument getTypeDocument() {
		return TypeDocument.Dvd;
	}
	private static String setCodeDocument() {
		String strCodeDoc="";
		if(intNoDocs<10) {
		strCodeDoc=TypeDocument.Dvd.getStrIndicateurType()+"0"+intNoDocs;
		}
		else {
		strCodeDoc=TypeDocument.Dvd.getStrIndicateurType()+intNoDocs;
		}
		return strCodeDoc;
	}
	public static void ouRenduNo(){
		
		int intNoDoc=1;
		if (intNoDocs!=1) {
		String strNo;
		FXCollections.sort(ListeDocuments.getInstance().mapDocument.get(TypeDocument.Dvd));
		strNo =ListeDocuments.getInstance().mapDocument.get(TypeDocument.Dvd).get((ListeDocuments.getInstance().mapDocument.get(TypeDocument.Dvd)).size()-1).getStrCodeDocument();
		strNo= strNo.toUpperCase().replace(TypeDocument.Dvd.getStrIndicateurType().toUpperCase(),"");
		intNoDoc= Integer.parseInt(strNo)+1;
		}
		intNoDocs= intNoDoc;
	}
	
}
