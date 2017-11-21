package application.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public abstract class Document implements Serializable,Comparable<Document>{
	
	private static final long serialVersionUID = -1342101432549855316L;
	private String strCodeDocument;
	private String strTitre;
	private LocalDate dateParution;
	private Etat etatDoc;
	private ArrayList<String> lstMots = new ArrayList<String>();
	
	
	public Document(String strCodeDocument,String strTitre, LocalDate dateParution, Etat etatDoc) {
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


	public LocalDate getDateParution() {
		return dateParution;
	}


	public void setDateParution(LocalDate dateParution) {
		this.dateParution = dateParution;
	}


	public Etat getEtatDoc() {
		return etatDoc;
	}


	public void setEtatDoc(Etat etatDoc) {
		this.etatDoc = etatDoc;
	}


	
	public String toString() {
		return "Document [strCodeDocument=" + strCodeDocument + ", strTitre=" + strTitre + ", dateParution="
				+ dateParution + ", etatDoc=" + etatDoc + ", lstMots=" + lstMots + "]";
	}
	abstract public   TypeDocument getTypeDocument();
	//abstract  String setCodeDocument();


	public ArrayList<String> getLstMots() {
		return lstMots;
	}
	@Override
	public int compareTo(Document d) {
		return this.strCodeDocument.compareTo(d.strCodeDocument);
	}
	
	
}