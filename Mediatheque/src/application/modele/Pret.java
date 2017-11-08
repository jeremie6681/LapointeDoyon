package application.modele;

import java.time.LocalDate;
import java.util.Date;

public class Pret {
	
	private static int intCompteurNoPret=1;
	private int intNoPret;
	private LocalDate datePret;
	private LocalDate dateRetourPrevue;
	private LocalDate dateEffectiveRetour;
	private Amende amende=null;
	private Document doc;
	
	public Pret(Document doc) {
		this.intNoPret = intCompteurNoPret;
		intCompteurNoPret++;
		this.datePret =LocalDate.now() ;
		this.doc=doc;
		doc.setEtatDoc(Etat.EMPRUNTE);
	}

	public Document getDoc() {
		return doc;
	}

	public Amende getAmende() {
		return amende;
	}

}
