package application.modele;

import java.util.ArrayList;
import java.util.Date;

public class Document {
	
	private String strCodeDocument;
	private String strTitre;
	private Date dateParution;
	private Etat etatDoc;
	private ArrayList<String> lstMots = new ArrayList<String>();
	
	
	public Document(String strCodeDocument, String strTitre, Date dateParution, Etat etatDoc) {
		super();
		this.strCodeDocument = strCodeDocument;
		this.strTitre = strTitre;
		this.dateParution = dateParution;
		this.etatDoc = etatDoc;
	}
}
