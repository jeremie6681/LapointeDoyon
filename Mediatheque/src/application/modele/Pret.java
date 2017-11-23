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
	//private String strCodeDoc = doc.getStrTitre();
	
	public Pret(Document doc) {
		TypeDocument typeEmprunte= null;
		this.intNoPret = intCompteurNoPret;
		intCompteurNoPret++;
		this.datePret =LocalDate.now();//a enlever ....seulement pour simuler un retard
		
		this.doc=doc;
		doc.setEtatDoc(Etat.EMPRUNTE);
		doc.incrementeIntNbrPrets();
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
	

	public void gestionAmende(){
		try {
		if (amende==null){
		if (ChronoUnit.DAYS.between(datePret,LocalDate.now())>doc.getTypeDocument().getIntNbJoursEmprunt()){
			this.amende= new Amende(dateRetourPrevue, LocalDate.now(), dblMontantPenalite,false);
			this.doc.setEtatDoc(Etat.RETARD);
		}
		}
		else {
			if(amende.getBooRetour()) {
			}
			else {
				this.amende= new Amende(dateRetourPrevue, LocalDate.now(), dblMontantPenalite,false);
				this.doc.setEtatDoc(Etat.RETARD);
			}
		}
		}catch(NullPointerException n) {
			
		}
	}
/*
	public String getStrCodeDoc() {
		return strCodeDoc;
	}
*/
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
	public LocalDate getDateEffectiveRetour() {
		return dateEffectiveRetour;
	}

	public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
		this.dateRetourPrevue = dateRetourPrevue;
	}
/*
	public void setStrCodeDoc(String strCodeDoc) {
		this.strCodeDoc = strCodeDoc;
	}
*/

	public void setAmende(Amende amende) {
		this.amende= amende;
		
	}
	
	public double getDblmontantPenalite() {
		return dblMontantPenalite;
	}
	public void setDateEffectiveRetour(LocalDate dateEffectiveRetour) {
		this.dateEffectiveRetour = dateEffectiveRetour;
	}

	@Override
	public String toString() {
		String strRetour="";
		strRetour+="\nTitre: "+doc.getStrTitre();
		strRetour+="\nDate d'emprunt: "+datePret.toString();
		strRetour+="\nDate de retour Prévue: "+dateRetourPrevue.toString();
		strRetour+="\nAmande?: ";
		if (amende==null) {
			strRetour+="non\n\n";
		}else {
		strRetour+="oui"+ String.format("%.2f", amende.getDblMontant()) + "$";	
		strRetour+="\nRetourné?: ";
		if (amende.getBooRetour()) {
			strRetour+="oui\n\n";
		}else {
			strRetour+="non\n\n";
		}
		}
		return strRetour;
	}
}
