package application.modele;

import java.util.Date;

public class Pret {
	
	private static int intCompteurNoPret=1;
	private int intNoPret;
	private Date datePret;
	private Date dateRetourPrevue;
	private Date dateEffectiveRetour;
	private Amende amende=null;
	
	public Pret(int intNoPret) {
		super();
		this.intNoPret = intNoPret;
		this.datePret = new Date() ; 
	}

}
