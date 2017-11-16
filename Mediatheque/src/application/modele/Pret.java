package application.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Pret implements Serializable{
	
	private static final long serialVersionUID = -7171557492998212016L;
	final private static double dblMontantPenalite=0.50; 
	private static int intCompteurNoPret=1;
	private int intNoPret;          //used?
	private LocalDate datePret;
	private LocalDate dateRetourPrevue;
	private LocalDate dateEffectiveRetour;  //inutile
	private Amende amende=null;
	private Document doc;
	private String strCodeDoc = doc.getStrTitre();
	
	public Pret(Document doc) {
		TypeDocument typeEmprunte= null;
		this.intNoPret = intCompteurNoPret;
		intCompteurNoPret++;
		this.datePret =LocalDate.now().minusMonths(3);
		this.doc=doc;
		doc.setEtatDoc(Etat.EMPRUNTE);
		dateRetourPrevue= LocalDate.now().plusDays((long)doc.getTypeDocument().getIntNbJoursEmprunt());
	}

	public Document getDoc() {
		return doc;
	}

	public Amende getAmende() {
		return amende;
	}
	public void setDoc(Document doc) {
		this.doc=doc;
	}
	
	public  void retourDoc(){
		doc= null;
		dateEffectiveRetour=LocalDate.now();
	}
	public void gestionAmende(){
		try {
		if (ChronoUnit.DAYS.between(datePret,LocalDate.now())>doc.getTypeDocument().getIntNbJoursEmprunt()){
			this.amende= new Amende(dateRetourPrevue, LocalDate.now(), dblMontantPenalite);
			this.doc.setEtatDoc(Etat.RETARD);
		}
		}catch(NullPointerException n) {
			
		}
	}

	public String getStrCodeDoc() {
		return strCodeDoc;
	}

	public int getIntNoPret() {
		return intNoPret;
	}

	public void setIntNoPret(int intNoPret) {
		this.intNoPret = intNoPret;
	}

	public LocalDate getDatePret() {
		return datePret;
	}

	public void setDatePret(LocalDate datePret) {
		this.datePret = datePret;
	}

	public LocalDate getDateRetourPrevue() {
		return dateRetourPrevue;
	}

	public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
		this.dateRetourPrevue = dateRetourPrevue;
	}

	public void setStrCodeDoc(String strCodeDoc) {
		this.strCodeDoc = strCodeDoc;
	}

		
}
