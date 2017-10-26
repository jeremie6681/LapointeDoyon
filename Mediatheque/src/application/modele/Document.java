package application.modele;

import java.util.ArrayList;
import java.util.Date;

public class Document {
	
	private int intNoDoc;
	private String strTitre;
	private Date dateParution;
	private Etat etatDoc;
	private ArrayList<String> lstMots = new ArrayList<String>();
	
	
	public Document(int intNoDoc, String strTitre, Date dateParution, Etat etatDoc) {
		super();
		this.intNoDoc = intNoDoc;
		this.strTitre = strTitre;
		this.dateParution = dateParution;
		this.etatDoc = etatDoc;
	}
}
