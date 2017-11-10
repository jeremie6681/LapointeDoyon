package application.modele;

import java.time.LocalDate;

public class Pret {
	
	private static int intCompteurNoPret=1;
	private int intNoPret;          //used?
	private LocalDate datePret;
	private LocalDate dateRetourPrevue;
	private LocalDate dateEffectiveRetour;
	private Amende amende=null;
	private Document doc;
	
	public Pret(Document doc) {
		TypeDocument typeEmprunte= null;
		this.intNoPret = intCompteurNoPret;
		intCompteurNoPret++;
		this.datePret =LocalDate.now() ;
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
	public  void retourDoc(){
		doc= null;
		dateEffectiveRetour=LocalDate.now();
	}
	public void gestionAmende(){
		
	}
}
