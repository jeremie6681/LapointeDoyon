package application.modele;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;

import com.sun.crypto.provider.DHKeyAgreement;

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
		for(TypeDocument type : TypeDocument.values()) {
			if(type.getStrIndicateurType().equals(doc.getStrCodeDocument().substring(0, 3)))
				typeEmprunte = type;
		}
		dateRetourPrevue= LocalDate.now().plusDays((long)typeEmprunte.getIntNbJoursEmprunt());
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
