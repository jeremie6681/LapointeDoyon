package application.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class DVD extends Document implements Serializable{
	

	private static final long serialVersionUID = -1678710002762416560L;
	private short shNbDisques;
	private String strResalisateur;
	
	public DVD(String strCodeDocument, String strTitre, LocalDate dateParution, Etat etatDoc,short shNbDisque,String strResalisateur) {
		super(strCodeDocument, strTitre, dateParution, etatDoc);
		this.shNbDisques=shNbDisque;
		this.strResalisateur= strResalisateur;
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
}
