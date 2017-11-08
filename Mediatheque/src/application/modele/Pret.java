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
	
	public Pret() {
		this.intNoPret = intCompteurNoPret;
		intCompteurNoPret++;
		this.datePret =LocalDate.now() ;
	}

}
