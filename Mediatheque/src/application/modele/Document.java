package application.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Document implements Serializable, Comparable<Document> {

	private static final long serialVersionUID = -1342101432549855316L;
	private String strCodeDocument;
	private String strTitre;
	private LocalDate dateParution;
	private Etat etatDoc;
	private ArrayList<String> lstMots = new ArrayList<String>();
	private int intNbrPrets;

	public Document(String strCodeDocument, String strTitre, LocalDate dateParution, Etat etatDoc) {
		super();
		this.strCodeDocument = strCodeDocument;
		this.strTitre = strTitre;
		this.dateParution = dateParution;
		this.etatDoc = etatDoc;
		this.intNbrPrets = 0;
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
	
	//Détermine le type de document, est défini dans les sous-classes
	abstract public TypeDocument getTypeDocument();

	public ArrayList<String> getLstMots() {
		return lstMots;
	}

	@Override
	public int compareTo(Document d) {
		return this.strCodeDocument.compareTo(d.strCodeDocument);
	}

	public void incrementeIntNbrPrets() {
		this.intNbrPrets++;
	}

	public int getIntNbrPrets() {
		return intNbrPrets;
	}

	public void setIntNbrPrets(int intNbrPrets) {
		this.intNbrPrets = intNbrPrets;
	}

}