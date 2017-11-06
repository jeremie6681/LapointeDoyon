package application.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Document implements Serializable {
	
	private static final long serialVersionUID = -1342101432549855316L;
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


	public void setLstMots(String strMotCle) {
		lstMots.add(strMotCle);
	}


	public String getStrTitre() {
		return strTitre;
	}


	public void setStrTitre(String strTitre) {
		this.strTitre = strTitre;
	}


	public String getStrCodeDocument() {
		return strCodeDocument;
	}


	public void setStrCodeDocument(String strCodeDocument) {
		this.strCodeDocument = strCodeDocument;
	}


	public Date getDateParution() {
		return dateParution;
	}


	public void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}


	public Etat getEtatDoc() {
		return etatDoc;
	}


	public void setEtatDoc(Etat etatDoc) {
		this.etatDoc = etatDoc;
	}


	@Override
	public String toString() {
		return "Document [strCodeDocument=" + strCodeDocument + ", strTitre=" + strTitre + ", dateParution="
				+ dateParution + ", etatDoc=" + etatDoc + ", lstMots=" + lstMots + "]";
	}
	
}
