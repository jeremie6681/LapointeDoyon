package application.modele;

import java.util.Date;

public class Amende {
	
	private static int intCompteurNoAmende=1;
	private int intNoAmende;
	private double dblMontant;
	private boolean booPaye = false;		//est l'etat ...false = non-paye
	
	public Amende(Date dateRetourPrevu, Date dateDeRetourEffective, int dblMontantPenalite) {
		
		int intNbJoursRetard=(int)Math.ceil((dateRetourPrevu.getTime() - dateDeRetourEffective.getTime())/(1000*60*60*24));
		this.dblMontant = (intNbJoursRetard*dblMontantPenalite);
		this.intNoAmende = intCompteurNoAmende;
 }
}